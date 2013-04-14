package com.madavan.bridge.cards;

public class Card {

	private Rank _rank;
	private Suit _suit;
	
	public Card(Rank rank, Suit suit) {
		_rank = rank;
		_suit = suit;
	}
	
	/*
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
	}*/
	
	public Rank getRank() {
		return _rank;
	}
	
	public Suit getSuit() {
		return _suit;
	}
	
	@Override
	public String toString() {
		return _rank.toString() + "," + _suit.toString();
	}
	
	// example message TEN,DIAMONDS
	public static Card fromString(String str) throws IllegalArgumentException {
		String[] val = str.split(",");
		return new Card(Rank.valueOf(val[0]), Suit.valueOf(val[1]));
	}
}
