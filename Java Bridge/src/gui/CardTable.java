package gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import client.GUITable;

import java.util.ArrayList;

import cards.GUICard;

public class CardTable extends JPanel {
	private static final long serialVersionUID = 1L;

	private GUITable table;
	
	public CardTable(GUITable _table) {
		setPreferredSize(new Dimension(GUIConstants.TABLE_WIDTH,GUIConstants.TABLE_HEIGHT));
		setLayout(null);
		initComponents();
		table = _table;
	}
	
	private void initComponents() {
		// WIN SCREEN
		winScreen.setBounds(GUIConstants.MID_X - GUIConstants.WIN_LENGTH/2, GUIConstants.MID_Y - GUIConstants.WIN_HEIGHT/2, GUIConstants.WIN_LENGTH, GUIConstants.WIN_HEIGHT);
		winScreen.setFont(GUIConstants.WIN_FONT);
		winScreen.setForeground(Color.YELLOW);
		winScreen.setAlignmentX(Component.CENTER_ALIGNMENT);
		winScreen.setAlignmentY(Component.CENTER_ALIGNMENT);
		winScreen.setHorizontalTextPosition(JLabel.CENTER);
		winScreen.setVerticalTextPosition(JLabel.BOTTOM);
		add(winScreen);
		// END WIN SCREEN
		
		// NAME LABELS
		westName = new JLabel("WEST", JLabel.CENTER);
		westName.setFont(GUIConstants.NAME_FONT);
		westName.setBounds(GUIConstants.WEST_LABEL_X, GUIConstants.WEST_LABEL_Y, GUIConstants.NAME_LENGTH, GUIConstants.NAME_HEIGHT);
		westName.setForeground(new Color(255,255,255));
		northName = new JLabel("NORTH");
		northName.setFont(GUIConstants.NAME_FONT);
		northName.setBounds(GUIConstants.NORTH_LABEL_X, GUIConstants.NORTH_LABEL_Y, GUIConstants.NAME_LENGTH, GUIConstants.NAME_HEIGHT);
		northName.setForeground(new Color(255,255,255));
		eastName = new JLabel("EAST", JLabel.CENTER);
		eastName.setFont(GUIConstants.NAME_FONT);
		eastName.setBounds(GUIConstants.EAST_LABEL_X, GUIConstants.EAST_LABEL_Y, GUIConstants.NAME_LENGTH, GUIConstants.NAME_HEIGHT);
		eastName.setForeground(new Color(255,255,255));
		add(westName);
		add(northName);
		add(eastName);
		// END NAME LABELS
	}
	
	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		g.drawImage(GUIConstants.BACKGROUND_IMAGE,0,0,width,height,this);

		drawCards(g);
	}
	
	private void drawCards(Graphics g) {
		ArrayList<GUICard> dummy = table.getDummyCards();
		ImageIcon card;
		for ( int i = 0; i < table.getCount(0); i++ ) {
			if ( table.getDummy() == 1 && dummy.size() > i )
				card = dummy.get(i).getFaceImage();
			else
				card = GUICard.BACK_IMAGE;
			card.paintIcon(null, g, GUIConstants.GAP, GUIConstants.TOP_SHIFT+GUIConstants.VERTICAL_CARD_GAP*i);
		}
		for ( int i = 12; i > 12 - table.getCount(1); i-- ) {
			if ( table.getDummy() == 2 && dummy.size() > 12 - i )
				card = dummy.get(12 - i).getFaceImage();
			else
				card = GUICard.BACK_IMAGE;
			card.paintIcon(null, g, GUIConstants.RIGHT_SHIFT+GUIConstants.HORIZONTAL_CARD_GAP*i, GUIConstants.GAP);
		}
		for ( int i = 12; i > 12 - table.getCount(2); i-- ) {
			if ( table.getDummy() == 3 && dummy.size() > 12 - i )
				card = dummy.get(12 - i).getFaceImage();
			else
				card = GUICard.BACK_IMAGE;
			card.paintIcon(null, g, GUIConstants.TABLE_WIDTH - GUIConstants.GAP - GUIConstants.CARD_WIDTH, GUIConstants.TOP_SHIFT+GUIConstants.VERTICAL_CARD_GAP*i);
		}
		
		ArrayList<GUICard> hand = table.getPlayer().getCardsArray();
		for ( int i = 0; i < hand.size(); i++ ) {
			if ( hand.get(i) != null )
				hand.get(i).getFaceImage().paintIcon(null, g, GUIConstants.RIGHT_SHIFT+GUIConstants.HORIZONTAL_CARD_GAP*i, GUIConstants.TABLE_HEIGHT - GUIConstants.GAP - GUIConstants.CARD_HEIGHT);
		}

		int starter = table.getStarter();
		for ( int i = 0; i < 4; i++ ) {
			int currentPlayer = (starter+i)%4;
			if ( table.get(i) != null ) {
				if ( currentPlayer == 0 )
					((GUICard) table.get(i)).getFaceImage().paintIcon(null, g, GUIConstants.MID_X+GUIConstants.OVERLAP_HORIZONTAL_2-GUIConstants.CARD_WIDTH, GUIConstants.MID_Y-GUIConstants.OVERLAP_VERTICAL_1);
				if ( currentPlayer == 1 )
					((GUICard) table.get(i)).getFaceImage().paintIcon(null, g, GUIConstants.MID_X+GUIConstants.OVERLAP_HORIZONTAL_1-GUIConstants.CARD_WIDTH, GUIConstants.MID_Y+GUIConstants.OVERLAP_VERTICAL_2-GUIConstants.CARD_HEIGHT);
				if ( currentPlayer == 2 )
					((GUICard) table.get(i)).getFaceImage().paintIcon(null, g, GUIConstants.MID_X-GUIConstants.OVERLAP_HORIZONTAL_2, GUIConstants.MID_Y+GUIConstants.OVERLAP_VERTICAL_1-GUIConstants.CARD_HEIGHT);
				if ( currentPlayer == 3 )
					((GUICard) table.get(i)).getFaceImage().paintIcon(null, g, GUIConstants.MID_X-GUIConstants.OVERLAP_HORIZONTAL_1, GUIConstants.MID_Y-GUIConstants.OVERLAP_VERTICAL_2);
			}
		}
	}
	
	public String getName() {
		String name = (String) JOptionPane.showInputDialog(
				this.getRootPane(),
				"What's your name?",
				"Customized Dialog",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				"");
		if ( name == null || !(name.length() > 0) )
			return getName();
		return name;
	}
	
	public String getTablePosition(Integer ...possibilities) {
		String pos = (String) JOptionPane.showInputDialog(
				this.getRootPane(),
				"Select table position (0 is dealer):",
				"Customized Dialog",
				JOptionPane.PLAIN_MESSAGE,
				null,
				possibilities,
				possibilities[0]);
		return pos;
	}
	
	public void setPlayerNames() {
		westName.setText(table.getName(0));
		northName.setText(table.getName(1));
		eastName.setText(table.getName(2));
		validate();
	}
	
	public int getPlayCard() {
		CardListener cl = new CardListener();
		addMouseListener(cl);
		synchronized (lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		removeMouseListener(cl);
		return selectCard;
	}
	
	public int getDummyCard() {
		DummyListener dl = new DummyListener();
		addMouseListener(dl);
		synchronized (lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		removeMouseListener(dl);
		return selectCard;
	}
	
	private class CardListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			if ( y > GUIConstants.TABLE_HEIGHT - GUIConstants.CARD_HEIGHT - GUIConstants.GAP && y < GUIConstants.DISPLAY_HEIGHT - GUIConstants.GAP ) {
				for ( int i = table.getPlayer().getCardsArray().size() - 1; i >= 0; i-- ) {
					if ( x > GUIConstants.RIGHT_SHIFT+GUIConstants.HORIZONTAL_CARD_GAP*i && x < GUIConstants.RIGHT_SHIFT+GUIConstants.HORIZONTAL_CARD_GAP*i+GUIConstants.CARD_WIDTH ) {
						selectCard = i;
						synchronized (lock) { lock.notify(); }
						break;
					}
				}
			}
		}
	}
	
	private class DummyListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			if ( y > GUIConstants.GAP && y < GUIConstants.CARD_HEIGHT + GUIConstants.GAP ) {
				for ( int i = 13 - table.getDummyCards().size(); i < 13; i++ ) {
					if ( x > GUIConstants.RIGHT_SHIFT+GUIConstants.HORIZONTAL_CARD_GAP*i && x < GUIConstants.RIGHT_SHIFT+GUIConstants.HORIZONTAL_CARD_GAP*i+GUIConstants.CARD_WIDTH ) {
						selectCard = 12 - i;
						synchronized (lock) { lock.notify(); }
						break;
					}
				}
			}
		}
	}
	
	public void printWin(String s) {
		winScreen.setText(s);
		winScreen.setVisible(true);
	}

	public void clearWin() {
		winScreen.setVisible(false);
	}
	
	protected final Object lock = new Object();
	
	// NAME VARIABLES
	private JLabel westName;
	private JLabel northName;
	private JLabel eastName;
	private JLabel winScreen = new JLabel("", SwingConstants.CENTER);

	// PLAY CARD VARIABLES
	protected int selectCard;
}
