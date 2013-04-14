package com.madavan.bridge.android;

public class Card implements Comparable<Card> {

	private Rank _rank;
	private Suit _suit;
	
	public Card(Rank rank, Suit suit) {
		_rank = rank;
		_suit = suit;
	}
	
	@Override
	public int compareTo(Card oth) {
		int rankCompare = _rank.compareTo(oth.getRank());
		Suit othSuit    = oth.getSuit();
		
		// If the either of both of the suits are trump.
		if(_suit.isTrump() && othSuit.isTrump())
			return rankCompare;
		else if(_suit.isTrump())
			return 1;
		else if(othSuit.isTrump())
			return -1;
		
		// If the suits are the same, or different suits.
		if((_suit.isTrick()  &&  othSuit.isTrick()) ||
		   (!_suit.isTrick() && !othSuit.isTrick()))
			return rankCompare;
		else if(_suit.isTrick() && !othSuit.isTrick())
			return 1;
		else if(!_suit.isTrick() && othSuit.isTrick())
			return -1;
		
		// Default condition (never reached).
		return rankCompare;
	}
	
	public Rank getRank() {
		return _rank;
	}
	
	public Suit getSuit() {
		return _suit;
	}
	
	@Override
	public String toString() {
		return "CARD:" + _rank.toString() + "," + _suit.toString();
	}
	
	// example message CARD:TEN,DIAMONDS
	public static Card fromString(String msg) throws IllegalArgumentException {
		String cmd   = msg.substring(msg.indexOf(":") + 1);
		String[] val = cmd.split(",");
		return new Card(Rank.valueOf(val[0]), Suit.valueOf(val[1]));
	}
}
