package client;

import java.io.IOException;
import java.net.Socket;

import support.Connection;

public class ClientConnection extends Connection {
	
	public ClientConnection(Socket _socket) throws IOException {
		super(_socket);
	}
	
	public String receive() {
		String s = null;
		try {
			s = in.readLine();
		} catch(IOException e) {}
		return s;
	}
}
