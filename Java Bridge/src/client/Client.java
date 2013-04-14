package client;

import gui.PanelInterface;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import cards.GUICard;
import cards.Rank;
import cards.Suit;

import support.Command;

public class Client extends Thread {

	ClientConnection connection;
	GUITable table;
	PanelInterface panelConnect;
	
	public Client(String _host, int _port, GUITable _table, PanelInterface _panelConnect ) {
		try {
			Socket s = new Socket(_host,_port);
			connection = new ClientConnection(s);
		} catch (UnknownHostException e) {
			System.err.println("Don't know host");
		} catch( IOException e ) {
			System.err.println("Can't connect to port: 4444");
		}
		table = _table;
		panelConnect = _panelConnect;
	}

	private String getPlayerName() {
		return panelConnect.getCardTable().getName();
	}
	
	private String getTablePos(String _available) {
		String[] positions = _available.split("\\s+");
		Integer[] pos = new Integer[positions.length];
		for ( int i = 0; i < positions.length; i++ )
			pos[i] = positions[i].charAt(0) - '0';
		return panelConnect.getCardTable().getTablePosition(pos);
	}
	
	private void setPlayerNames(String s) {
		table.setNames(s.split("\\s+"));
		panelConnect.getCardTable().setPlayerNames();
	}
	
	private void setCards(String s) {
		String[] cards = s.split("\\s+");
		GUIPlayer player = table.getPlayer();
		for ( int i = 0; i < cards.length; i++ ) {
			player.addCard(new GUICard(Rank.valueOf(cards[i]), Suit.valueOf(cards[++i])));
		}
		table.setCount(cards.length/2);
		panelConnect.redrawTable();
	}
	
	private String getBid() {
		return panelConnect.getBidPanel().getBid();
	}
	private void addBid(String s) {
		panelConnect.getBidPanel().addBid( s.charAt(0) - '0', s.substring(1).trim() );
	}
	private void finalBid(String s) {
		panelConnect.getScoreboard().setTrump(s.charAt(0)-'0',s.charAt(2)-'0');
	}

	private void setDummy(String s) {
		String[] cards = s.split("\\s+");
		GUIPlayer dummy = table.getDummyPlayer();
		table.setDummy(cards[0].charAt(0) - '0');
		for ( int i = 1; i < cards.length; i++ ) {
			dummy.addCard(new GUICard(Rank.valueOf(cards[i]), Suit.valueOf(cards[++i])));
		}
		table.setCount(cards.length/2);
		panelConnect.redrawTable();
	}
	
	private String getCard() {
		boolean isValid = false;
		int card = 14;
		while ( !isValid ) {
			card = panelConnect.getCardTable().getPlayCard();	
			if ( table.size() != 4 && table.size() > 0 && !table.get(0).getSuit().equals(table.getPlayer().get(card).getSuit())
					&& table.getPlayer().hasSuit(table.get(0).getSuit()) )
				isValid = false;
			else
				isValid = true;
		}
		table.getPlayer().poll(card);
		return card+"";
	}
	private String getDummyCard() {
		boolean isValid = false;
		int card = 14;
		while ( !isValid ) {
			card = panelConnect.getCardTable().getDummyCard();
			if ( table.getDummyCards().size() < card )
				isValid = false;
			else if  ( table.size() != 4 && table.size() > 0 && !table.get(0).getSuit().equals(table.getDummyPlayer().get(card).getSuit())
					&& table.getDummyPlayer().hasSuit(table.get(0).getSuit()) )
				isValid = false;
			else
				isValid = true;
		}
		return card+"";
	}
	private void drawDummy(String _card) {
		table.getDummyPlayer().poll(Integer.valueOf(_card));
	}
	private void drawCard(String _card) {
		String[] card = _card.split("\\s+");
		GUICard playCard = new GUICard(Rank.valueOf(card[1]), Suit.valueOf(card[2]));
		int leftPlayers = card[0].charAt(0) - '0';
		if ( table.size() == 0 || table.size() == 4 ) {
			table.setStarter(leftPlayers);
			table.clearCards();
		}
		if (leftPlayers != 0)
			table.decCount(leftPlayers - 1);
		else
			table.getPlayer().removeCard(playCard);
		table.addCard(new GUICard(playCard));
		panelConnect.redrawTable();
	}
	
	private void updateScore(String _score) {
		String[] indivScore = _score.split("\\s+");
		panelConnect.getScoreboard().updateScore(indivScore[0], indivScore[1]);
	}
	
	private void fixCount(String _count) {
		String[] counts = _count.split("\\s+");
		for ( int i = 0; i < 3; i++ )
			table.setCount(i, Integer.valueOf(counts[i]));
	}
	
	private void reset() {
		table.clearCards();
		panelConnect.getBidPanel().clearBids();
		panelConnect.getScoreboard().clearTrump();
	}
	
	public void parseCommand(String s) {
		int loc = s.indexOf(":");
		Command c;
		String params = "";
		if ( loc != -1 ) {
			c = Command.valueOf(s.substring(0,loc));
			params = s.substring(loc+1);
		} else {
			c = Command.valueOf(s);
		}
		switch (c){
			case GETNAME: connection.send(getPlayerName());	// Ask and send the name of the player
				break;
			case GETPOS: connection.send(getTablePos(params));	// Ask and send the position of the player on the table
				break;
			case SENDCARDS: setCards(params);				// Store the cards of the player
				break;
			case SENDNAMES: setPlayerNames(params);			// Store and write player's names on board
				break;
			case GETBID: connection.send(getBid());			// Ask user to input and send bid
				break;
			case PUSHBID: addBid(params);					// Add a player's bid to previous bids
				break;
			case FINALBID: finalBid(params);				// set the final bid (draw trump and shit)
				break;
			case SENDDUMMY: setDummy(params);				// reveal the dummy's cards
				break;
			case PLAYCARD: connection.send(getCard());		// get the card that the user wants to play
				break;
			case PLAYDUMMY: connection.send(getDummyCard());// get the card that the dummy wants to play
				break;
			case DUMMYCARD: drawDummy(params);				// remove the dummy's card from his deck
				break;
			case PUSHCARD: drawCard(params);				// play the card on the table
				break;
			case SCORE: updateScore(params);				// update the score on the scoreboard
				break;
			case FIXCOUNT: fixCount(params);				// fix the count in the case of a crash
				break;	
			case RESET: reset();
				break;
			default:
				break;
		}
	}

	@Override
	public void run() {
		while(true) {
			String s;
			if ( (s = connection.receive()) != null ) {
				parseCommand(s);
			}
		}
	}
}
