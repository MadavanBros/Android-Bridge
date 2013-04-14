package client;

import java.util.ArrayList;

import cards.GUICard;
import cards.Rank;
import cards.Suit;

import support.Table;

public class GUITable extends Table {

	private String[] playerNames;
	private GUIPlayer player;
	private GUIPlayer dummyPlayer;
	private int[] count;
	
	public GUITable() {
		super();
		playerNames = new String[3];
		player = new GUIPlayer();
		dummyPlayer = new GUIPlayer();
		count = new int[3];
		count[0] = 0; count[1] = 0; count[2] = 0;
	}	
	
	public int size() {
		return cards.size();
	}
	
	public void setCount(int _count) {
		for ( int i = 0; i < 3; i++ )
			count[i] = _count;
	}
	
	public void setCount(int i, int _count) {
		count[i] = _count;
	}
	
	public void decCount(int i) {
		count[i]--;
	}
	
	public int getCount(int i) {
		return count[i];
	}

	public GUIPlayer getPlayer() {
		return player;
	}
	
	public void setNames(String[] _names) {
		playerNames = _names;
	}
	
	public String[] getNames() {
		return playerNames;
	}
	
	public String getName(int i) {
		return playerNames[i];
	}
	
	public void setDummyCards(String s) {
		String[] cards = s.split("\\s+");
		for ( int i = 0; i < cards.length; i++ ) {
			if ( cards[i].equals("null") ) {
				dummyPlayer.addCard(null);
				i++;
			} else {
				dummyPlayer.addCard(new GUICard(Rank.valueOf(cards[i]), Suit.valueOf(cards[++i])));
			}
		}
	}
	
	public GUIPlayer getDummyPlayer() {
		return dummyPlayer;
	}
	
	public ArrayList<GUICard> getDummyCards() {
		return dummyPlayer.getCardsArray();
	}
	
	public void reset() {
		super.reset();
		player.clear();
		setCount(0);
	}
}
