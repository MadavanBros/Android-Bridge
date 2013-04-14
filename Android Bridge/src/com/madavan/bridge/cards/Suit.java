package com.madavan.bridge.cards;

public enum Suit {

	CLUBS, DIAMONDS, HEARTS, SPADES, NO_TRUMP;
	
	private boolean _isTrump, _isTrick;
	
	public boolean isTrump() {
		return _isTrump;
	}
	
	public boolean isTrick() {
		return _isTrick;
	}
	
	// Sets Suit as the Trump (making sure to reset all other Suits).
	public void setTrump() {
		for(Suit s : Suit.values())
			s.setTrump(false);
		
		this.setTrump(true);
	}
	
	// Sets Suit as the Trick (making sure to reset all other Suits).
	public void setTrick() {
		for(Suit s : Suit.values())
			s.setTrick(false);
				
		this.setTrick(true);
	}
	
	private void setTrick(boolean isTrick) {
		_isTrick = isTrick;
	}
	
	private void setTrump(boolean isTrump) {
		_isTrump = isTrump;
	}
}
