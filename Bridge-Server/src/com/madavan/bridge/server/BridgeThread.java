package com.madavan.bridge.server;

import java.util.ArrayList;
import java.io.IOException;

import com.madavan.bridge.cards.Bid;
import com.madavan.bridge.cards.Card;
import com.madavan.bridge.cards.Deck;
import com.madavan.bridge.cards.Suit;
import com.madavan.bridge.support.Command;

public class BridgeThread extends Thread {

	private ArrayList<BridgePlayer> _players;
	private Deck _deck;
	private Bid _trumpBid;
	private int _curDealer;
	private int _curPlayer;
	private int _dummy;

	public BridgeThread(ArrayList<BridgePlayer> players) {
		_players = players;
		_deck = new Deck();
		_curDealer = 0;
		_curPlayer = 0;
	}

	@Override
	public void run() {
		try {
			// Tell players game has begun
			// Reset, shuffle, and deal deck
			dealDeck(); 
			// Bidding
			bid();
			// Play Game
			play();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void dealDeck() throws IOException {
		_deck.reset();
		_deck.shuffle();

		_curPlayer = _curDealer + 1;
		for (int i = 0; i < 52; i++) {
			Card nextCard = _deck.getNext();
			nextCard.setPlayer((_curPlayer) % 4);
			send((_curPlayer++) % 4, Command.DEAL, nextCard.toString());
		}
	}

	private void bid() throws IOException {
		_curPlayer = _curDealer;
		int numPasses = 0;
		_trumpBid = null;
		ArrayList<Bid> bids = new ArrayList<Bid> ();
		
		while (numPasses < 3) {
			send(_curPlayer, Command.BID_TURN, "");
			String sbid = _players.get(_curPlayer).readNext();
			
			if (sbid.equals("PASS")) {
				numPasses++;
				bids.add(null);
			}
			else if (sbid.equals("DOUBLE")) {
				bids.add(null);
				numPasses = 0;
			}
			else {
				Bid bid = Bid.fromString(sbid);
				if (_trumpBid == null || bid.compareTo(_trumpBid) > 0)
					_trumpBid = bid;
				bids.add(bid);
				numPasses = 0;
			}
			sendAll(Command.BID, _curPlayer + "," + sbid);
		}
		
		for ( int i = _trumpBid.getPlayer()%2; i < bids.size(); i+=2 )
			if ( bids.get(i) != null && _trumpBid.getSuit().equals(bids.get(i).getSuit()) )
				_dummy = (bids.get(i).getPlayer()+2)%4;
	}
	
	private void play() throws IOException {
		_curPlayer = _trumpBid.getPlayer();
		Suit trick;
		Card card1;
		Card card2;
		for ( int i = 0; i < 13; i++ ) {
			card1 = playCard(_curPlayer++);
			trick = card1.getSuit();
			for ( int j = 0; j < 3; j++ ) {
				card2 = playCard(_curPlayer++);
				if ( compareCard(card1, card2, _trumpBid.getSuit(), trick) < 0 )
					card1 = card2;
			}
			_curPlayer = card1.getPlayer();
		}
	}
	
	private Card playCard(int player) throws IOException {
		Card card = null;
		if (player != _dummy) {
			send(player, Command.PLAY_TURN, "");
			card = Card.fromString(_players.get(player).readNext());
		}
		else {
			send((player+2)%4, Command.PLAY_DUMMY, "");
			card = Card.fromString(_players.get((player+2)%4).readNext());
		}
		sendAll(Command.CARD, player + "," + card.toString());
		return card;
	}

	private int compareCard(Card c1, Card c2, Suit trump, Suit trick) {
		int rankCompare = c1.getRank().compareTo(c2.getRank());
		boolean c1Trump = c1.getSuit().equals(trump);
		boolean c2Trump = c2.getSuit().equals(trump);
		boolean c1Trick = c1.getSuit().equals(trick);
		boolean c2Trick = c2.getSuit().equals(trick);

		if (c1Trump && c2Trump)
			return rankCompare;
		else if (c1Trump)
			return 1;
		else if (c2Trump)
			return -1;

		if (c1Trick && !c2Trick)
			return 1;
		else if (!c1Trick && c2Trick)
			return -1;
		else
			return rankCompare;
	}

	private void send(int player, Command c, String msg) throws IOException {
		_players.get(player).send(c + ":" + msg + ".");
	}
	
	private void sendAll(Command c, String msg) throws IOException {
		for ( BridgePlayer p : _players )
			p.send(c + ":" + msg + ".");
	}

}
