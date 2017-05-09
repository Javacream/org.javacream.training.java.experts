package org.javacream.training.java.experts.networking.tcpip;

import java.io.*;
import java.net.*;

public class IO {

	private final Socket socket;
	private final ObjectOutputStream out;
	private final ObjectInputStream in;

	public IO(Socket socket) {
		try {
			this.socket = socket;
			this.out = new ObjectOutputStream(this.socket.getOutputStream());
			this.in = new ObjectInputStream(this.socket.getInputStream());
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void write(Object obj) {
		try {
			this.out.writeObject(obj);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Object read() {
		try {
			return this.in.readObject();
		}
		catch (EOFException e) {
			return null;
		}
		catch (SocketException e) {
			return null;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void close() {
		try {
			if (this.socket != null)
				this.socket.close();
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
}