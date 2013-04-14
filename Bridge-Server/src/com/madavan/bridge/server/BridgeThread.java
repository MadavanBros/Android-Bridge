package com.madavan.bridge.server;


import java.util.ArrayList;
import java.io.IOException;

import com.madavan.bridge.cards.Bid;
import com.madavan.bridge.cards.Card;
import com.madavan.bridge.cards.Deck;
import com.madavan.bridge.cards.Rank;
import com.madavan.bridge.cards.Suit;
import com.madavan.bridge.support.Command;

public class BridgeThread extends Thread {

  private ArrayList<BridgePlayer> _players;
  private Deck _deck;
  private int _curDealer;
  private int _curPlayer;
  
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
	  dealDeck(); // Reset, shuffle, and deal deck
    
    // Bidding
    //  - Pass is the bid CLUBS,0 (or we could do PASS)
    //  - Use Rank, Suit compareTo methods
	  bid();
    
    // Play Game
	  } catch (IOException e) {
		  e.printStackTrace();
	  }
  }
  
  private void dealDeck() throws IOException {
	  _deck.reset();
	  _deck.shuffle();

	  _curPlayer = _curDealer + 1;
	  for ( int i = 0; i < 52; i++ )
		  send((_curPlayer++)%4, Command.CARD, _deck.getNext().toString());
  }
  
  private void bid() throws IOException {
	  _curPlayer = _curDealer;
	  int numPasses = 0;
	  Bid greatestBid = null;
	  Bid bid;
	  while ( numPasses < 3 ) {
		  send(_curPlayer, Command.BID_TURN, "");
		  String sbid = _players.get((_curPlayer++)%4).readNext();
		  if (sbid.equals("PASS"))
			  numPasses++;
		  else {
			  bid = Bid.fromString(sbid);
			  if ( greatestBid == null || bid.compareTo(greatestBid) > 0 )
				  greatestBid = bid;
		  }
	  }
  }
  
  private int compareCard(Card c1, Card c2, Suit trump, Suit trick) {
    int rankCompare = c1.getRank().compareTo(c2.getRank());
    boolean c1Trump = c1.getSuit().equals(trump);
    boolean c2Trump = c2.getSuit().equals(trump);
	boolean c1Trick = c1.getSuit().equals(trick);
    boolean c2Trick = c2.getSuit().equals(trick);
    
    if(c1Trump && c2Trump)
      return rankCompare;
    else if(c1Trump)
      return 1;
    else if(c2Trump)
      return -1;
    
    if(c1Trick && !c2Trick)
      return 1;
    else if(!c1Trick && c2Trick)
      return -1;
    else
      return rankCompare;
  }
  
  private void send(int player, Command c, String msg) throws IOException {
	  _players.get(player).send(c+":"+msg+".");
  }
}
