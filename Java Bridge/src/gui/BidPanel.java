package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

public class BidPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel[][] bids;
	private int bidRow;
	
	public BidPanel() {
		initComponents();
		setPreferredSize(new Dimension(GUIConstants.BIDPANEL_WIDTH,GUIConstants.BIDPANEL_HEIGHT));
	}
	
	private void initComponents() {
		Integer[] intArray = { 0, 1, 2, 3, 4 };
		suits = new JComboBox(intArray);
		ComboBoxRenderer renderer = new ComboBoxRenderer();
		suits.setPreferredSize(new Dimension(125,25));
		suits.setRenderer(renderer);
		
		number = new JTextField();
		number.setPreferredSize(new Dimension(50,25));
		
		bid = new JButton("Bid");
		pass = new JButton("Pass");
		doubl = new JButton("Double");
		
		add(suits);
		add(number);
		add(bid);
		add(doubl);
		add(pass);
		
		JLabel prevBids = new JLabel("~Previous Bids~", JLabel.CENTER);
		prevBids.setPreferredSize(new Dimension(GUIConstants.BIDPANEL_WIDTH, 20));
		prevBids.setFont(GUIConstants.BID_FONT);
		add(prevBids);
		
		JLabel south	= new JLabel("S", JLabel.CENTER);
		south.setPreferredSize(new Dimension(GUIConstants.BIDPANEL_WIDTH/5,20));
		south.setFont(GUIConstants.BID_FONT);
		JLabel west		= new JLabel("W", JLabel.CENTER);
		west.setPreferredSize(new Dimension(GUIConstants.BIDPANEL_WIDTH/5,20));
		west.setFont(GUIConstants.BID_FONT);
		JLabel north	= new JLabel("N", JLabel.CENTER);
		north.setPreferredSize(new Dimension(GUIConstants.BIDPANEL_WIDTH/5,20));
		north.setFont(GUIConstants.BID_FONT);
		JLabel east		= new JLabel("E", JLabel.CENTER);
		east.setPreferredSize(new Dimension(GUIConstants.BIDPANEL_WIDTH/5,20));
		east.setFont(GUIConstants.BID_FONT);

		add(south);
		add(west);
		add(north);
		add(east);
		
		// JPanel for the previous bids
		bids = new JLabel[10][4];
		bidRow = 0;
		for ( int i = 0; i < bids.length; i++ ) {
			for ( int j = 0; j < bids[0].length; j++ ) {
				bids[i][j] = new JLabel("", JLabel.CENTER);
				bids[i][j].setPreferredSize(new Dimension(GUIConstants.BIDPANEL_WIDTH/5,20));
				bids[i][j].setHorizontalTextPosition(JLabel.LEFT);
				bids[i][j].setVerticalTextPosition(JLabel.CENTER);
				add(bids[i][j]);
			}
		}
	}
	
	public String getBid() {
		ButtonListener b = new ButtonListener();
		bid.addActionListener(b);
		pass.addActionListener(b);
		doubl.addActionListener(b);
		synchronized (lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		bid.removeActionListener(b);
		pass.removeActionListener(b);
		doubl.removeActionListener(b);
		return bidString;
	}
	
	public void addBid( int i, String s ) {
		JLabel modBid = bids[bidRow][i];
		if ( s.equals("PASS") || s.equals("2X") )
			modBid.setText(s);
		else {
			modBid.setText(s.charAt(0)+"");
			switch(s.charAt(2)-'0') {
				case(0): modBid.setIcon(GUIConstants.CLUBS_SMALL); break;
				case(1): modBid.setIcon(GUIConstants.DIAMOND_SMALL); break;
				case(2): modBid.setIcon(GUIConstants.HEART_SMALL); break;
				case(3): modBid.setIcon(GUIConstants.SPADE_SMALL); break;
				case(4): modBid.setText(modBid.getText()+" NT"); break;
			}
		}
		if ( i == 3 )
			bidRow++;
		validate();
	}
	
	public void clearBids() {
		for ( int i = 0; i < bids.length; i++ )
			for ( int j = 0; j < bids[0].length; j++ )
				bids[i][j].setText("");
		validate();
	}
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean isValid = false;
			if ( e.getSource() == bid ) {
				String text = number.getText().trim();
				if ( text.length() == 1) {
					int val = text.charAt(0) - '0';
					if ( val <= 7 ) {
						bidString = val + " " + suits.getSelectedIndex();
						isValid = true;
					}
				}
			} else if ( e.getSource() == doubl ) {
				bidString = "2X";
				isValid = true;
			}
			else if ( e.getSource() == pass ) {
				bidString = "PASS";
				isValid = true;
			}
			if (isValid)
				synchronized(lock) { lock.notify(); }
		}
	}
	
	private class ComboBoxRenderer extends JLabel implements ListCellRenderer {
		private static final long serialVersionUID = 1L;
		public ComboBoxRenderer() {
			setOpaque(true);
			setVerticalTextPosition(JLabel.CENTER);
			setHorizontalTextPosition(JLabel.RIGHT);
		}
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			int selectedIndex = ((Integer) value).intValue();
			if ( isSelected ) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			switch(selectedIndex) {
				case(0): setIcon(GUIConstants.CLUBS_SMALL);
					setText("Clubs");
					break;
				case(1): setIcon(GUIConstants.DIAMOND_SMALL);
					setText("Diamonds");
					break;
				case(2): setIcon(GUIConstants.HEART_SMALL);
					setText("Hearts");
					break;
				case(3): setIcon(GUIConstants.SPADE_SMALL);
					setText("Spades");
					break;
				case(4): setIcon(null);
					setText("No Trump");
					break;
			}
			return this;
		}
	}
	
	private String bidString;
	private JButton bid;
	private JButton pass;
	private JButton doubl;
	
	private JComboBox suits;
	private JTextField number;
	
	private Object lock = new Object();
}
