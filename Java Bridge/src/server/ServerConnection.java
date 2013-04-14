package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import support.Command;
import support.Connection;

public class ServerConnection extends Connection {

	/* Identifiers */
	private String name;
	private InetAddress ip;
	
	public ServerConnection(Socket _socket) throws IOException {
		super(_socket);
	}
	
	public String sendAndReceive(String s) {
		out.println(s);
		String input = null;
		Boolean isNull = false;
		try {
			input = in.readLine();
			if ( input == null )
				isNull = true;
		} catch(IOException e) {
			isNull = true;
		}
		
		if (isNull) {
			synchronized (lock) {
				try {
					lock.wait();
					input = sendAndReceive(s);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return input;
	}
	
	public void askName() {
		name = sendAndReceive(Command.GETNAME+":");
	}
	public String getName() {
		return name;
	}
	
	public int askPosition(ArrayList<Integer> pos) {
		String s = "";
		for ( Integer i: pos )
			if ( i != null )
				s += i+" ";
		return sendAndReceive(Command.GETPOS+":"+s).charAt(0)-'0';
	}
	
	public InetAddress getIp() {
		return ip;
	}
	
	public void setSocket(Socket _socket) throws IOException {
		this.socket = _socket;
		this.out = new PrintWriter(socket.getOutputStream(),true);
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println("Reconnected from: "+socket.getInetAddress()+" on port "
				+socket.getPort()+" to port "+socket.getLocalPort()+" on "+socket.getLocalAddress());
	}
	
	public void notifyLock() {
		synchronized(lock) { lock.notify(); }
	}
	
	private Object lock = new Object();
}
