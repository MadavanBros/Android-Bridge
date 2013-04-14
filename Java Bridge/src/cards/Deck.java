package cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	ArrayList<Card> deck;
	
	public Deck () {
		deck = new ArrayList<Card> (52);
		for ( Suit s: Suit.values() ) {
			for ( Rank r: Rank.values() ) {
				deck.add(new Card(r,s));
			}
		}
	}
	
	public void reset() {
		deck.clear();
		for ( Suit s: Suit.values() ) {
			for ( Rank r: Rank.values() ) {
				deck.add(new Card(r,s));
			}
		}
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public Card pop() {
		return deck.remove(0);
	}
	
	public boolean isEmpty() {
		return deck.isEmpty();
	}
	
	public Suit getTrump() {
		Suit trump = null;
		for ( Suit s: Suit.values() ) {
			if ( s.isTrump() )
				trump = s;
		}
		return trump;
	}
	
	public void setTrump(int _s) {
		for ( Suit s: Suit.values() ) {
			if ( s.equals(Suit.values()[_s]) )
				s.setTrump(true);
			else
				s.setTrump(false);
		}
	}
	
	public void setTrump(Suit _s) {
		for ( Suit s: Suit.values() ) {
			if ( s.equals(_s) )
				s.setTrump(true);
			else
				s.setTrump(false);
		}
	}
}
