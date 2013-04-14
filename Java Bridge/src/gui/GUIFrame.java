package gui;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.*;

import client.GUITable;

import client.Client;

public class GUIFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	Client client;
	BidPanel bidpanel;
	CardTable guitable;
	Scoreboard scoreboard;
	PanelInterface panelConnect;
	GUITable table;
	String host;
	int port;
	
	public GUIFrame() {
		readProperties();
		bidpanel = new BidPanel();
		table = new GUITable();
		guitable = new CardTable(table);
		scoreboard = new Scoreboard();
		panelConnect = new PanelInterface(bidpanel, guitable, scoreboard);
		client = new Client(host, port, table, panelConnect);
		initComponents();
		client.start();
	}
	
	private void initComponents() {
		
		setTitle("Bridge");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(GUIConstants.DISPLAY_WIDTH,GUIConstants.DISPLAY_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(bidpanel, BorderLayout.WEST);
		content.add(scoreboard, BorderLayout.SOUTH);
		content.add(guitable, BorderLayout.CENTER);

		setContentPane(content);
		
		setVisible(true);
		
	}
	
	private void readProperties() {
		Properties prop = new Properties();
		try {
    		prop.load(new FileInputStream("config.properties"));
    		host = prop.getProperty("host");
    		port = Integer.parseInt(prop.getProperty("port"));
		} catch (IOException e) {
			host = "127.0.0.1";
			port = 4444;
		} catch (NumberFormatException e) {
			port = 4444;
		}
	}
	
	public static void main(String[] args) {
		new GUIFrame();
	}
}
