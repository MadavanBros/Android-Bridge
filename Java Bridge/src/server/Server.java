package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {

	private Bridge b;
	private ServerSocket serverSocket;
	private ArrayList<ServerConnection> connections;
	
	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		connections = new ArrayList<ServerConnection>(4);
	}
	
	public void initialize() {
		ServerConnection client = null;
		ArrayList<Integer> pos = new ArrayList<Integer> (4);
		pos.add(0);
		pos.add(1);
		pos.add(2);
		pos.add(3);
		try {
			while ( connections.size() < 4 ) {
				client = new ServerConnection(serverSocket.accept());
				client.askName();
				int posChoice = client.askPosition(pos);
				pos.set(posChoice, null);
				connections.set(posChoice, client);
			}
		} catch (IOException e) {
			System.err.println("Accept failed");
		}
		b = new Bridge(connections);
	}
	
	public void run() {
		Socket client = null;
		try {
			while (true) {
				client = serverSocket.accept();
				for ( int i = 0; i < connections.size(); i++ ) {
					ServerConnection c = connections.get(i);
					if ( c.getIp().equals(client.getInetAddress()) ) {
						c.setSocket(client);
						b.fix(i);
						c.notifyLock();
						break;
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Accept Failed");
		}
	}
	
	public void runBridge() {
		b.play();
	}
	
	public static void main(String[] args) {
		System.out.println("Server online");
		Server server = new Server(4444);
		server.initialize();
		server.start(); // reconnecter
		server.runBridge();
	}

}
