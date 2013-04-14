package cards;

public enum Rank {
	
	TWO		(1, '2'),
	THREE	(2, '3'),
	FOUR	(3, '4'),
	FIVE	(4, '5'),
	SIX		(5, '6'),
	SEVEN	(6, '7'),
	EIGHT	(7, '8'),
	NINE	(8, '9'),
	TEN		(9, 't'),
	JACK	(10, 'j'),
	QUEEN	(11, 'q'),
	KING	(12, 'k'),
	ACE		(13, 'a');

	private int value;
	private char name;
	
	Rank(int _value, char _name) {
		this.value = _value;
		this.name = _name;
	}
	
	public int compare(Rank r) {
		if ( this.value < r.value )
			return -1;
		else if ( this.value > r.value )
			return 1;
		else
			return 0;
	}
	
	public int getVal() {
		return value;
	}
	
	public char getName() {
		return name;
	}
	
}
