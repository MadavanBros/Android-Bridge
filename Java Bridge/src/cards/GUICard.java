package cards;

import javax.swing.ImageIcon;

public class GUICard extends Card {

	private static final String IMAGE_PATH = "/cardimages/";
	public static final ImageIcon BACK_IMAGE;

	private static final Class<GUICard> CLASS = GUICard.class;
    private static final String PACKAGE_NAME;
    private static final ClassLoader CLSLDR;

	static {
		PACKAGE_NAME = CLASS.getPackage().getName();
		CLSLDR = CLASS.getClassLoader();
		String urlPath = PACKAGE_NAME + IMAGE_PATH + "b.gif";
		java.net.URL imageURL = CLSLDR.getResource(urlPath);
		BACK_IMAGE = new ImageIcon(imageURL);
	}
	
	private ImageIcon face_image;
	
	public GUICard(Rank r, Suit s) {
		super(r, s);
		setImage();
	}
	
	public GUICard(GUICard playCard) {
		super(playCard.getRank(), playCard.getSuit());
		setImage();
	}

	public ImageIcon getFaceImage() {
		return face_image;
	}

	private void setImage() {
		String cardFilename = this.rank.getName() + "" + this.suit.getName() + ".gif";
		String path = PACKAGE_NAME + IMAGE_PATH + cardFilename;
		java.net.URL imageURL = CLSLDR.getResource(path);
		face_image = new ImageIcon(imageURL);
	}

}
