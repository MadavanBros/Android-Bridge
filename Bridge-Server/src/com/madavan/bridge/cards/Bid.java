package com.madavan.bridge.cards;

public class Bid implements Comparable<Bid> {

  private Integer _num;
  private Suit _suit;
  private Integer _player;

  public Bid(int player, int num, Suit suit) {
	  _player = player;
    _num = num;
    _suit = suit;
  }

  public int getRank() {
    return _num;
  }

  public Suit getSuit() {
    return _suit;
  }

  @Override
  public int compareTo(Bid oth) {
    if(_num.compareTo(oth.getRank()) > 0 ||
       _num.compareTo(oth.getRank()) == 0 &&
       _suit.compareTo(oth.getSuit()) >  0)
      return 1;
    else if(_num.compareTo(oth.getRank()) == 0 &&
            _suit.compareTo(oth.getSuit()) == 0)
      return 0;
    else
      return -1;
  }

  @Override
  public String toString() {
    return _player.toString() + "," + _num.toString() + "," + _suit.toString();
  }
  
  public static Bid fromString(String str) {
    String[] val = str.split(",");
    return new Bid(Integer.valueOf(val[0]), Integer.valueOf(val[1]), Suit.valueOf(val[2]));
  }
}
