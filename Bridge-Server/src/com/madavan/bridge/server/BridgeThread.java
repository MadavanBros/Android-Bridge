package com.madavan.bridge.server;


import java.util.ArrayList;
import java.io.IOException;

import com.madavan.bridge.cards.Card;
import com.madavan.bridge.cards.Deck;
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
    // Tell players game has begun
	  dealDeck(); // Reset, shuffle, and deal deck
    
    // Bidding
    //  - Pass is the bid CLUBS,0 (or we could do PASS)
    //  - Use Rank, Suit compareTo methods
    
    // Play Game
  }
  
  private void dealDeck() {
	  _deck.reset();
	  _deck.shuffle();

	  _curPlayer = _curDealer + 1;
	  for ( int i = 0; i < 52; i++ )
		  _players.get((_curPlayer++)%4).send(Command.CARD+":"+_deck.getNext()+".");
  }
  
  private void bid() {
	  _curPlayer = _curDealer;
	  int numPasses = 0;
	  while ( numPasses < 3 ) {
		  _players.get(_curPlayer).send(Command.BID+":GIVE ME YO BID.");
		  String sbid = _players.get((_curPlayer++)%4).readNext(); // TRY CATCH BLOCK NEEDED
		  if (sbid.equals("PASS"))
			  numPasses++;
		  else {
			  String[] bidParts = sbid.split(",");
			  Suit s = Suit.valueOf(bidParts[0]);
			  int num = Integer.valueOf(bidParts[1]);
			  
			  // Backup check to see if the bid is valid
			  if ( num <  )
		  }
	  }
  }
  
  private int compareCard(Card c1, Card c2, Suit trump, Suit trick) {
    int rankCompare = _rank.compareTo(oth.getRank());
    boolean c1Trump = card1.getSuit().equals(trump);
    boolean c2Trump = card2.getSuit().equals(trump);
	boolean c1Trick = card1.getSuit().equals(trick);
    boolean c2Trick = card2.getSuit().equals(trick);
    
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
}
