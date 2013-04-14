package server;

import java.util.ArrayList;

import cards.Card;

public class Player {

	private ArrayList<Card> hand;
	public ServerConnection connection;
	
	public Player(ServerConnection _connection) {
		hand = new ArrayList<Card> (13);
		connection = _connection;
	}
	
	public void addCard(Card c) {
		hand.add(c);
	}
	
	public Card get(int i) {
		return hand.get(i);
	}
	
	public Card poll(int i) {
		return hand.remove(i);
	}
	
	public void organize() {
		Card c;
		int holePos;
		for ( int i = 0; i < hand.size(); i++ ) {
			c = hand.get(i);
			holePos = i;
			while(holePos > 0 && 0 > c.compareWithoutTrump(hand.get(holePos-1))) {
				hand.set(holePos, hand.get(holePos - 1));
				holePos--;
			}
			hand.set(holePos, c);
		}
	}
	
	public void clear() {
		hand.clear();
	}
	
	public String getCards() {
		String s = "";
		for ( Card c: hand ) {
			s += c + " ";
		}
		return s;
	}
	public ArrayList<Card> getCardsArray() {
		return hand;
	}
}
