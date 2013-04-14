package com.madavan.bridge.cards;
import java.util.Collections;
import java.util.Stack;


public class Deck {

	private Stack<Card> _cards;
	
	public Deck() {
		reset();
	}
	
	public void shuffle() {
		Collections.shuffle(_cards);
	}
	
	public Card getNext() {
		return _cards.pop();
	}
	
	public int getNumCards() {
		return _cards.size();
	}
	
	public void reset() {
		_cards = new Stack<Card>();
		
		for(Rank rank : Rank.values())
			for(Suit suit : Suit.values())
				if(suit != Suit.NO_TRUMP)
					_cards.push(new Card(rank, suit));
	}
}
