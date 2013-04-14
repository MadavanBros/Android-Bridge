package cards;

public class Card {
	protected Rank rank;
	protected Suit suit;
	
	public Card (Rank r, Suit s) {
		this.rank = r;
		this.suit = s;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public int compareTo(Card c) {
		if ( suit.isTrump() && !c.suit.isTrump() ) {
			return 1;
		} else if (!suit.isTrump() && c.suit.isTrump() ) {
			return -1;
		} else if ( suit.equals(c.suit) ) {
			return this.rank.compare(c.rank);
		} else {
			return Integer.MAX_VALUE;
		}
	}
	
	public int compareWithoutTrump(Card c) {
		if ( c.suit.getVal() > this.suit.getVal() )
			return -1;
		else if ( c.suit.getVal() < this.suit.getVal() )
			return 1;
		else if ( c.rank.getVal() > this.rank.getVal() )
			return -1;
		else if ( c.rank.getVal() < this.rank.getVal() )
			return 1;
		else
			return 0;
		
	}
	
	public String toString() {
		return rank+" "+suit;
	}
}
