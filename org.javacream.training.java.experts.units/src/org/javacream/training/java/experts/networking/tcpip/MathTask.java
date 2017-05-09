package org.javacream.training.java.experts.networking.tcpip;

import java.io.Serializable;

public class MathTask implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum Operation {
		SUM, DIFF
	}

	private final Operation operation;
	private final int x;
	private final int y;

	public MathTask(Operation operation, int x, int y) {
		this.operation = operation;
		this.x = x;
		this.y = y;
	}

	public Operation getOperation() {
		return this.operation;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + " [" + this.operation + ", " + this.x + ", " + this.y + "]";
	}
	
}
