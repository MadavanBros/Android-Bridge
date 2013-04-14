package support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection {
	
	protected Socket socket;
	protected PrintWriter out;
	protected BufferedReader in;
	
	public Connection(Socket _socket) throws IOException {
		this.socket = _socket;
		this.out = new PrintWriter(socket.getOutputStream(),true);
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println("Connected from: "+socket.getInetAddress()+" on port "
				+socket.getPort()+" to port "+socket.getLocalPort()+" on "+socket.getLocalAddress());
	}
	
	public void send(String s) {
		if ( s != null ) {
			out.println(s);
		}
	}
	
}
