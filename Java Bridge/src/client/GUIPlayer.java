package client;

import java.util.ArrayList;

import cards.GUICard;
import cards.Suit;

public class GUIPlayer {

	private ArrayList<GUICard> hand;
	
	public GUIPlayer() {
		hand = new ArrayList<GUICard> (13);
	}
	
	public void addCard(GUICard c) {
		hand.add(c);
	}
	
	public void removeCard(GUICard c) {
		for ( int i = 0; i < hand.size(); i++ )
			if ( hand.get(i).compareTo(c) == 0 )
				poll(i);
	}
	
	public GUICard get(int i) {
		return hand.get(i);
	}
	
	public GUICard poll(int i) {
		return hand.remove(i);
	}
	
	public boolean hasSuit(Suit s) {
		boolean hasSuit = false;
		for ( int i = 0; i < hand.size(); i++ ) {
			if ( hand.get(i).getSuit().equals(s) )
				hasSuit = true;
		}
		return hasSuit;
	}
	
	public void clear() {
		hand.clear();
	}
	
	public String getCards() {
		String s = "";
		for ( GUICard c: hand ) {
			s += c + " ";
		}
		return s;
	}
	public ArrayList<GUICard> getCardsArray() {
		return hand;
	}
}
