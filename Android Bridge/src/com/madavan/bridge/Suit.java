package com.madavan.bridge;

public enum Suit {

	CLUBS, DIAMONDS, HEARTS, SPADES, NO_TRUMP;
	
	// Is this suit the trump suit.
	private boolean _isTrump;
	// Is this suit the suit of the trick.
	private boolean _isTrick;
	
	public boolean isTrump() {
		return _isTrump;
	}
	
	// Sets this Suit as the Trump card, making sure
	// to set the current Trump to false.
	public void setTrump() {
		for(Suit s : Suit.values())
			s.setTrump(false);
		
		_isTrump = true;
	}
	
	// Sets this Suit as the Trick card, making sure
	// to set the current Trick to false.
	public boolean isTrick() {
		return _isTrick;
	}
	
	public void setTrick() {
		for(Suit s : Suit.values())
			s.setTrick(false);
				
		_isTrick = true;
	}
	
	private void setTrick(boolean isTrick) {
		_isTrick = isTrick;
	}
	
	private void setTrump(boolean isTrump) {
		_isTrump = isTrump;
	}
}
