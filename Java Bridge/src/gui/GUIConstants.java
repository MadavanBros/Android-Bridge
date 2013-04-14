package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import cards.GUICard;

public class GUIConstants {
	// FRAME CONSTANTS //
	public static final int DISPLAY_WIDTH	= 800;
	public static final int DISPLAY_HEIGHT	= 600;
	
	public static final String FONTNAME = "LucidaGrande";
	
	// CARD STUFF //
	public static final int CARD_WIDTH		= GUICard.BACK_IMAGE.getIconWidth();
	public static final int CARD_HEIGHT		= GUICard.BACK_IMAGE.getIconHeight();
	
	// TABLE CONSTANTS //
	public static final int GAP				= 10;
	public static final int TABLE_WIDTH		= 600;
	public static final int TABLE_HEIGHT	= 450;
	public static final int VERTICAL_CARD_GAP	= 24;
	public static final int HORIZONTAL_CARD_GAP	= 20;
	public static final int TOP_SHIFT		= (TABLE_HEIGHT - 12*VERTICAL_CARD_GAP   - CARD_HEIGHT)/2;
	public static final int RIGHT_SHIFT		= (TABLE_WIDTH  - 12*HORIZONTAL_CARD_GAP - CARD_WIDTH )/2;
	
	public static final int MID_X					= TABLE_WIDTH/2;
	public static final int MID_Y					= TABLE_HEIGHT/2;
	public static final int OVERLAP_HORIZONTAL_1 	= 9;
	public static final int OVERLAP_HORIZONTAL_2 	= 28;
	public static final int OVERLAP_VERTICAL_1		= 20;
	public static final int OVERLAP_VERTICAL_2		= OVERLAP_VERTICAL_1 + CARD_HEIGHT/3;

	public static final int NAME_LENGTH		= CARD_WIDTH;
	public static final int NAME_HEIGHT		= 11;
	public static final int WIN_LENGTH		= 500;
	public static final int WIN_HEIGHT		= 80;

	public static final Font NAME_FONT		= new Font(FONTNAME, Font.BOLD, NAME_HEIGHT);
	public static final Font WIN_FONT		= new Font(FONTNAME, Font.BOLD, WIN_HEIGHT);
	
	public static final int WEST_LABEL_X	= GAP;
	public static final int WEST_LABEL_Y	= TOP_SHIFT - NAME_HEIGHT;
	public static final int NORTH_LABEL_X	= TABLE_WIDTH - RIGHT_SHIFT + GAP;
	public static final int NORTH_LABEL_Y	= TOP_SHIFT - NAME_HEIGHT;
	public static final int EAST_LABEL_X	= TABLE_WIDTH - GAP - NAME_LENGTH;
	public static final int EAST_LABEL_Y	= TABLE_HEIGHT - TOP_SHIFT;
	
	public static Image BACKGROUND_IMAGE = null;
	static {
		try {
			BACKGROUND_IMAGE = ImageIO.read(GUIConstants.class.getResource("images/background.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// SCOREBOARD STUFF //
	public static final int SCOREBOARD_WIDTH	= TABLE_WIDTH;
	public static final int SCOREBOARD_HEIGHT	= 130;

	public static ImageIcon CLUBS_ICON;
	public static ImageIcon DIAMOND_ICON;
	public static ImageIcon HEART_ICON;
	public static ImageIcon SPADE_ICON;
	
	static {
		CLUBS_ICON		= new ImageIcon(GUICard.class.getResource("/gui/images/tref.gif"));
		DIAMOND_ICON	= new ImageIcon(GUICard.class.getResource("/gui/images/diamond.gif"));
		HEART_ICON		= new ImageIcon(GUICard.class.getResource("/gui/images/heart.gif"));
		SPADE_ICON		= new ImageIcon(GUICard.class.getResource("/gui/images/spade.gif"));
	}

	public static final Color BACKGROUND = new Color(40,40,60);
	public static final Color FOREGROUND = new Color(255,255,255);

	public static final int BASE_FONT_SIZE	= 13;
	public static final int TLABEL_FONT_SIZE= BASE_FONT_SIZE + 1;
	public static final int TEAM_FONT_SIZE	= BASE_FONT_SIZE - 1;
	public static final int TRUMP_FONT_SIZE	= BASE_FONT_SIZE + 20;
	public static final int SCORE_FONT_SIZE	= BASE_FONT_SIZE + 40;
	public static final int TOT_FONT_SIZE	= BASE_FONT_SIZE + 10;
	
	public static final Font TLABEL_FONT= new Font(FONTNAME,Font.BOLD,TLABEL_FONT_SIZE);
	public static final Font TEAM_FONT	= new Font(FONTNAME,Font.BOLD,TEAM_FONT_SIZE);
	public static final Font TRUMP_FONT	= new Font(FONTNAME,Font.BOLD,TRUMP_FONT_SIZE);
	public static final Font SCORE_FONT	= new Font(FONTNAME,Font.BOLD,SCORE_FONT_SIZE);
	public static final Font TOT_FONT	= new Font(FONTNAME,0,TOT_FONT_SIZE);
	
	// BIDDING STUFF //
	public static final int BIDPANEL_WIDTH	= 200;
	public static final int BIDPANEL_HEIGHT	= DISPLAY_HEIGHT;
	
	public static ImageIcon CLUBS_SMALL;
	public static ImageIcon DIAMOND_SMALL;
	public static ImageIcon HEART_SMALL;
	public static ImageIcon SPADE_SMALL;
	
	static {
		CLUBS_SMALL		= new ImageIcon(GUICard.class.getResource("/gui/images/tref_small.gif"));
		DIAMOND_SMALL	= new ImageIcon(GUICard.class.getResource("/gui/images/diamond_small.gif"));
		HEART_SMALL		= new ImageIcon(GUICard.class.getResource("/gui/images/heart_small.gif"));
		SPADE_SMALL		= new ImageIcon(GUICard.class.getResource("/gui/images/spade_small.gif"));
	}
	
	public static final Font BID_FONT = new Font(FONTNAME,Font.BOLD,BASE_FONT_SIZE);
}
