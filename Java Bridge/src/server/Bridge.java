package server;

import java.util.ArrayList;

import support.Command;
import support.ScoreCounter;
import support.Table;

import cards.*;

public class Bridge {

	private ScoreCounter sc;
	private Deck deck;
	private Table table;
	private ArrayList<Player> players;
	private ArrayList<String> bids;
	
	public Bridge(ArrayList<ServerConnection> _connections) {
		sc = new ScoreCounter();
		deck = new Deck();
		table = new Table();
		players = new ArrayList<Player> (4);
		for ( ServerConnection c: _connections )
			players.add(new Player(c));
		bids = new ArrayList<String>();
		push(Command.RESET, "");
	}
	
	private void readyDeck() { deck.reset(); deck.shuffle(); }
	private void prep() {
		for ( Player p: players ) {
			p.clear();
		}
		table.setDummy(4);
		sc.resetScore();
		sendScore();
		readyDeck();
		table.clearCards();
	}
	private void sendNames() {
		String names;
		for ( int i = 0; i < 4; i++ ) {
			names = "";
			for ( int j = 1; j < 4; j++ ) {
				names += players.get((i+j)%4).connection.getName() + " ";
			}
			push(i, Command.SENDNAMES, names);
		}
	}
	private void deal() {
		int dealer = table.getDealer();
		for ( int i = 0; i < 13; i++ ) {
			players.get((dealer+1)%4).addCard(deck.pop());
			players.get((dealer+2)%4).addCard(deck.pop());
			players.get((dealer+3)%4).addCard(deck.pop());
			players.get((dealer+0)%4).addCard(deck.pop());
		}
		for ( int i = 0; i < 4; i++ ) {
			players.get(i).organize();
			push(i, Command.SENDCARDS, players.get(i).getCards());
		}
	}
	private void bid() {
		int numPasses = 0;
		int turns = 0;
		int pos = table.getDealer();
		String bid;
		String lastBid = "";
		Integer[] first = new Integer[5];
		while ( numPasses < 3 || turns < 4 ) {
			bid = pushAndGet(pos, Command.GETBID, "");
			pushPlayerInc(Command.PUSHBID, pos, bid);

			bids.add(bid);
			if ( bid.trim().equals("PASS") )
				numPasses++;
			else if ( bid.trim().equals("2X")) { }
			else {
				lastBid = bid;
				numPasses = 0;
				if ( first[bid.charAt(2)-'0'] == null )
					first[bid.charAt(2)-'0'] = pos;
			}
			pos = (pos+1)%4;
			turns++;
		}
		table.setStarter((first[lastBid.charAt(2)-'0']+1)%4);
		table.setDummy((table.getStarter()+1)%4);
		if ( lastBid.charAt(2)-'0' == 4 )
			deck.setTrump(null);
		else
			deck.setTrump( lastBid.charAt(2)-'0' );
		table.setBidCount(lastBid.charAt(0)-'0');
		push(Command.FINALBID, lastBid);
	}
	
	private void playCard(int i) {
		String cardNum = "";
		if ( i == table.getDummy() ) {
			cardNum = pushAndGet((i+2)%4, Command.PLAYDUMMY, "");
		}
		else {
			cardNum = pushAndGet(i, Command.PLAYCARD, "");
		}
		Card card = players.get(i).poll(Integer.valueOf(cardNum));
		pushPlayerInc(Command.PUSHCARD, i, card.toString());
		if ( i == table.getDummy() )
			push(Command.DUMMYCARD, cardNum);
		table.addCard(card);
	}
	
	private void getWinner() {
		int winner = table.getStarter();
		Card best = table.get(0);
		for ( int i = 1; i < 4; i++ ) {
			if ( best.compareTo(table.get(i)) < 0 ) {
				winner = (table.getStarter() + i)%4;
				best = table.get(i);
			}
		}
		table.setStarter(winner);
		sc.addPoint(winner%2+1);
	}
	
	private void sendScore() {
		for ( int i = 0; i < 4; i++ ) {
			if ( i%2 == 0 )
				push(i, Command.SCORE, sc.getScore(1) + " " + sc.getScore(2));
			else
				push(i, Command.SCORE, sc.getScore(2) + " " + sc.getScore(1));
		}
	}
	
	private void push(int i, Command c, String s) {
		players.get(i).connection.send(c.toString()+":"+s);
	}
	private void push(Command c, String s) {
		for ( Player p: players )
			p.connection.send(c.toString()+":"+s);
	}
	private void pushPlayerInc(Command c, int j, String s) {
		for ( int i = 0; i < players.size(); i++ )
			players.get(i).connection.send(c.toString()+":"+(j-i+4)%4+" "+s);
	}
	private String pushAndGet(int i, Command c, String s) {
		return players.get(i).connection.sendAndReceive(c.toString()+":"+s);
	}
	
	public void play() {
		sendNames();
		boolean keepPlaying = true;
		while (keepPlaying) {
			prep();
			deal();
			bid();
			// FIRST HAND
			playCard(table.getStarter());
			pushPlayerInc(Command.SENDDUMMY, table.getDummy(), players.get(table.getDummy()).getCards());
			for ( int i = 1; i < 4; i++ )
				playCard((table.getStarter()+i)%4);
			getWinner();
			sendScore();
			// Other hands
			for ( int i = 1; i < 13; i++ ) {
				table.clearCards();
				for ( int j = 0; j < 4; j++ )
					playCard((table.getStarter()+j)%4);
				getWinner();
				sendScore();
			}
		}
	}
	
	public void fix(int i) {
		String names = "";
		for ( int j = 1; j < 4; j++ )
			names += players.get((i+j)%4).connection.getName() + " ";
		push(i, Command.SENDNAMES, names);
		for ( int j = 0; j < bids.size(); j++ )
			push(i, Command.PUSHBID, (j+table.getDealer())%4 + " " + bids.get(j));
		if ( table.getBidCount() != 0 ) {
			if ( deck.getTrump() != null )
				push(i, Command.FINALBID, table.getBidCount()+" "+(deck.getTrump().getVal()-1));
			else
				push(i, Command.FINALBID, table.getBidCount()+" 4");
		}
		if ( table.getDummy() != 4 )
			push(i, Command.SENDDUMMY, (table.getDummy() - i + 4)%4 + " " + players.get(table.getDummy()).getCards());
		push(i, Command.FIXCOUNT, players.get((i+1)%4).getCardsArray().size() + " "
								+ players.get((i+2)%4).getCardsArray().size() + " "
								+ players.get((i+3)%4).getCardsArray().size());
		for ( int j = 0; j < table.numCards(); j++ )
			push(i, Command.PUSHCARD, (j - i + 4)%4 + " " + table.get(j).toString());
		push(i, Command.SENDCARDS, players.get(i).getCards());
	}
	
}
