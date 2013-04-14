package support;

import java.util.ArrayList;

import cards.Card;

public class Table {
	protected int dealer;
	protected int starter;
	protected int dummy;
	protected int bidCount;
	protected ArrayList<Card> cards;
	
	public Table() {
		dealer = 0;
		starter = 0;
		dummy = 4;
		bidCount = 0;
		cards = new ArrayList<Card> (4);
	}
	
	public void incDealer() {
		dealer = (dealer+1)%4;
	}
	public int getDealer() {
		return dealer;
	}
	public void setDealer(int i) {
		dealer = i;
	}
	
	public void setStarter(int _starter) {
		starter = _starter;
	}
	public int getStarter() {
		return starter;
	}
	
	public void setDummy(int _dummy) {
		dummy = _dummy;
	}
	public int getDummy() {
		return dummy;
	}
	
	public void setBidCount(int _bidCount) {
		bidCount = _bidCount;
	}
	public int getBidCount() {
		return bidCount;
	}

	public void addCard(Card c) {
		cards.add(c);
	}
	public void setCard(int i, Card c) {
		cards.set(i, c);
	}
	public Card get(int i) {
		if (cards.size() < i+1)
			return null;
		return cards.get(i);
	}
	public int numCards() {
		return cards.size();
	}
	public void clearCards() {
		cards.clear();
	}
	
	public void reset() {
		clearCards();
		starter = 0;
		dummy = 4;
		bidCount = 0;
	}
}
