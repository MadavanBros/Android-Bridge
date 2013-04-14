package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Scoreboard extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public Scoreboard() {
		setPreferredSize(new Dimension(GUIConstants.SCOREBOARD_WIDTH, GUIConstants.SCOREBOARD_HEIGHT));
		initComponents();
	}

	private void initComponents() {
		
		Border topBorder = BorderFactory.createRaisedBevelBorder();
		JPanel borderPanel = new JPanel();
		borderPanel.setBorder(topBorder);
		borderPanel.setPreferredSize(new Dimension(600,2));

		JPanel leftPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(GUIConstants.BIDPANEL_WIDTH,1));
		
		////////////////// LEFT (TRUMP) PANEL ///////////////////
		trumpLabel = new JLabel();
		trumpLabel.setFont(GUIConstants.TRUMP_FONT);
		trumpLabel.setVerticalTextPosition(JLabel.CENTER);
		trumpLabel.setHorizontalTextPosition(JLabel.LEFT);
		trumpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		trumpLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
		trumpLabel.setPreferredSize(new Dimension(150,1));
		trumpLabel.setForeground(GUIConstants.FOREGROUND);
		trumpLabel.setVisible(false);
		
		TrumpLabel.setFont(GUIConstants.TLABEL_FONT);
		TrumpLabel.setVerticalTextPosition(JLabel.BOTTOM);
		TrumpLabel.setHorizontalTextPosition(JLabel.CENTER);
		TrumpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		TrumpLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
		TrumpLabel.setPreferredSize(new Dimension(150,1));
		TrumpLabel.setForeground(GUIConstants.FOREGROUND);
		TrumpLabel.setVisible(false);
		
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(Box.createRigidArea(new Dimension(150,20)));
		leftPanel.add(TrumpLabel);
		leftPanel.add(trumpLabel);
		//////////////////////////////////////////////////////////
		
		////////////////// CENTER (SCORE) PANEL ///////////////////
		JPanel leftShift = new JPanel();
		JPanel score = new JPanel();
		JPanel rightShift = new JPanel();
		
		leftShift.setPreferredSize(new Dimension(150,1));
		rightShift.setPreferredSize(new Dimension(150,1));
		leftShift.setBackground(GUIConstants.BACKGROUND);
		score.setBackground(GUIConstants.BACKGROUND);
		rightShift.setBackground(GUIConstants.BACKGROUND);
		
		JLabel yourTeamLabel = new JLabel("Your Team", SwingConstants.CENTER);
		yourTeamLabel.setFont(GUIConstants.TEAM_FONT);
		yourTeamLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		yourTeamLabel.setForeground(GUIConstants.FOREGROUND);
		JLabel enemyTeamLabel = new JLabel("Enemy Team", SwingConstants.CENTER);
		enemyTeamLabel.setFont(GUIConstants.TEAM_FONT);
		enemyTeamLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		enemyTeamLabel.setForeground(GUIConstants.FOREGROUND);

		yourScore.setForeground(GUIConstants.FOREGROUND);
		yourScore.setFont(GUIConstants.SCORE_FONT);
		enemyScore.setForeground(GUIConstants.FOREGROUND);
		enemyScore.setFont(GUIConstants.SCORE_FONT);
		
		yourTotScore.setForeground(GUIConstants.FOREGROUND);
		yourTotScore.setFont(GUIConstants.TOT_FONT);
		enemyTotScore.setForeground(GUIConstants.FOREGROUND);
		enemyTotScore.setFont(GUIConstants.TOT_FONT);

		JLabel topDummy = new JLabel();
		JLabel midLabel = new JLabel("-", JLabel.CENTER);
		JLabel botLabel = new JLabel("-", JLabel.CENTER);
		midLabel.setFont(GUIConstants.SCORE_FONT);
		midLabel.setForeground(GUIConstants.FOREGROUND);
		botLabel.setFont(GUIConstants.TOT_FONT);
		botLabel.setForeground(GUIConstants.FOREGROUND);

		score.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.4;
		c.weighty = 0.4;
		c.gridx = 0;
		c.gridy = 0;
		score.add(yourTeamLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.2;
		c.weighty = 0.4;
		c.gridx = 1;
		c.gridy = 0;
		score.add(topDummy);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.4;
		c.weighty = 0.4;
		c.gridx = 2;
		c.gridy = 0;
		score.add(enemyTeamLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.4;
		c.weighty = 0.2;
		c.gridx = 0;
		c.gridy = 1;
		score.add(yourScore, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.2;
		c.weighty = 0.2;
		c.gridx = 1;
		c.gridy = 1;
		score.add(midLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.4;
		c.weighty = 0.2;
		c.gridx = 2;
		c.gridy = 1;
		score.add(enemyScore, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.3;
		c.weighty = 0.4;
		c.gridx = 0;
		c.gridy = 2;
		score.add(yourTotScore, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.4;
		c.weighty = 0.4;
		c.gridx = 1;
		c.gridy = 2;
		score.add(botLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.3;
		c.weighty = 0.4;
		c.gridx = 2;
		c.gridy = 2;
		score.add(enemyTotScore, c);

		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(leftShift, BorderLayout.WEST);
		centerPanel.add(score, BorderLayout.CENTER);
		centerPanel.add(rightShift, BorderLayout.EAST);
		
		//////////////////////////////////////////////////////////
		
		setLayout(new BorderLayout());
		add(borderPanel, BorderLayout.NORTH);
		add(leftPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
	
		leftPanel.setBackground(GUIConstants.BACKGROUND);
		centerPanel.setBackground(GUIConstants.BACKGROUND);
	}
	
	public void setTrump(int _num, int _suit) {
		trumpLabel.setText(_num+"");
		switch(_suit) {
			case (0):	trumpLabel.setIcon(GUIConstants.CLUBS_ICON); break;
			case (1):	trumpLabel.setIcon(GUIConstants.DIAMOND_ICON); break;
			case (2):	trumpLabel.setIcon(GUIConstants.HEART_ICON); break;
			case (3):	trumpLabel.setIcon(GUIConstants.SPADE_ICON); break;
			case (4):	trumpLabel.setText(_num+" NT");
		}
		trumpLabel.setVisible(true);
		TrumpLabel.setVisible(true);
	}

	public void clearTrump() {
		trumpLabel.setVisible(false);
		TrumpLabel.setVisible(false);
	}
	
	public void updateScore(String x, String y) {
		yourScore.setText(x);
		enemyScore.setText(y);
		yourScore.revalidate();
		enemyScore.revalidate();
	}
	
	public void updateTeamScore(String x, String y) {
		yourTotScore.setText(x);
		enemyTotScore.setText(y);
		validate();
	}

	private JLabel trumpLabel;
	private JLabel TrumpLabel = new JLabel("Trump");
	private JLabel yourScore = new JLabel("0", JLabel.CENTER);
	private JLabel enemyScore = new JLabel("0", JLabel.CENTER);
	private JLabel yourTotScore = new JLabel("0", JLabel.RIGHT);
	private JLabel enemyTotScore = new JLabel("0", JLabel.LEFT);
	
}
