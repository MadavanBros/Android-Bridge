package com.madavan.bridge.cards;

public class Card {

	private Rank _rank;
	private Suit _suit;
	private Integer _player;

	public Card(Rank rank, Suit suit) {
		_rank = rank;
		_suit = suit;
	}
	
	public Card(int player, Rank rank, Suit suit) {
		_player = player;
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
	
	public void setPlayer(int player) {
		_player = player;
	}
	
	public int getPlayer() {
		return _player;
	}

	@Override
	public String toString() {
		return _player.toString() + "," + _rank.toString() + "," + _suit.toString();
	}

	// example message TEN,DIAMONDS
	public static Card fromString(String str) throws IllegalArgumentException {
		String[] val = str.split(",");
		return new Card(Integer.valueOf(val[0]), Rank.valueOf(val[1]), Suit.valueOf(val[2]));
	}
}
