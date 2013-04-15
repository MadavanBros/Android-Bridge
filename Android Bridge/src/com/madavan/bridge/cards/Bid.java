package com.madavan.bridge.cards;

public class Bid implements Comparable<Bid> {

	private int _num;
	private Suit _suit;
	private int _player;

	public Bid(int player, int num, Suit suit) {
		_player = player;
		_num = num;
		_suit = suit;
	}

	public int getNum() {
		return _num;
	}

	public Suit getSuit() {
		return _suit;
	}

	public int getPlayer() {
		return _player;
	}

	@Override
	public int compareTo(Bid oth) {
		if (_num > oth.getNum()
				|| _num == oth.getNum()
				&& _suit.compareTo(oth.getSuit()) > 0)
			return 1;
		else if (_num == oth.getNum()
				&& _suit.compareTo(oth.getSuit()) == 0)
			return 0;
		else
			return -1;
	}

	@Override
	public String toString() {
		return _player + "," + _num + ","
				+ _suit.toString();
	}

	public static Bid fromString(String str) {
		String[] val = str.split(",");
		return new Bid(Integer.valueOf(val[0]), Integer.valueOf(val[1]),
				Suit.valueOf(val[2]));
	}

}
