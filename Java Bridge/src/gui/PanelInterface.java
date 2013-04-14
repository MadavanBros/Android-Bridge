package gui;

public class PanelInterface {

	private BidPanel bidPanel;
	private CardTable cardTable;
	private Scoreboard scoreboard;
	
	public PanelInterface(BidPanel _bidPanel, CardTable _cardTable, Scoreboard _scoreboard) {
		bidPanel = _bidPanel;
		cardTable = _cardTable;
		scoreboard = _scoreboard;
	}
	
	public BidPanel getBidPanel() {
		return bidPanel;
	}
	
	public CardTable getCardTable() {
		return cardTable;
	}
	
	public Scoreboard getScoreboard() {
		return scoreboard;
	}
	
	public void redrawTable() {
		cardTable.repaint();
	}
	
	public void redrawScoreboard() {
		scoreboard.repaint();
	}
	
}
