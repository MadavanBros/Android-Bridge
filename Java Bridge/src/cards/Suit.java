package cards;

public enum Suit {

	CLUBS		(1, 'c'),
	DIAMONDS	(2, 'd'),
	HEARTS		(3, 'h'),
	SPADES		(4, 's');

	private char name;
	private int value;
	private boolean isTrump;
	
	Suit(int _value, char _name) {
		this.value = _value;
		this.name = _name;
	}
	
	public int getVal() {
		return value;
	}
	
	public boolean isTrump() {
		return isTrump;
	}
	
	public void setTrump(boolean b) {
		isTrump = b;
	}
	
	public char getName() {
		return name;
	}
}
