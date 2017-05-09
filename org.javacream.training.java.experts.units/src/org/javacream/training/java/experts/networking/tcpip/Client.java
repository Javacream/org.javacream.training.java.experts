package org.javacream.training.java.experts.networking.tcpip;

import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception {
		new Client().run("localhost", 9000);
	}

	public void run(String url, int port) throws Exception {
		IO io = null;
		try {
			io = new IO(new Socket(url, port));

			io.write(new MathTask(MathTask.Operation.SUM, 40, 2));
			MathResult sumResult = (MathResult)io.read();
			System.out.println(sumResult);

			io.write(new MathTask(MathTask.Operation.DIFF, 44, 2));
			MathResult diffResult = (MathResult)io.read();
			System.out.println(diffResult);
		}
		finally {
			io.close();
		}
	}
}
