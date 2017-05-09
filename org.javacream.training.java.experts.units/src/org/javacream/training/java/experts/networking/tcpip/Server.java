package org.javacream.training.java.experts.networking.tcpip;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception {
		new Server().run(9000);
	}

	public void run(int port) throws Exception {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				System.out.println("Waiting...");
				final Socket socket = serverSocket.accept();
				System.out.println("Accepted...");
				new Thread() {
					public void run() {
						try {
							Server.this.runSession(new IO(socket));
						}
						catch(Exception e) {
							System.out.println(e);
						}
					}
				}.start();
			}
		}
		finally {
			if (serverSocket != null)
				serverSocket.close();
		}
	}

	private void runSession(IO io) {
		try {
			MathTask task = (MathTask)io.read();
			while (task != null) {
				System.out.println("--> " + task);
				MathResult result = this.process(task);
				System.out.println("<-- " + result);
				io.write(result);
				task = (MathTask)io.read();
			}
		}
		finally {
			io.close();
		}
	}

	private MathResult process(MathTask task) {
		switch (task.getOperation()) {
		case SUM: 
			return new MathResult(task.getX() + task.getY());
		case DIFF:
			return new MathResult(task.getX() - task.getY());
		default:
			throw new RuntimeException("unsupported operation: " + task.getOperation());
		}
	}
	
}