package org.javacream.training.java.experts.networking.tcpip;

import java.io.Serializable;

public class MathResult implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final int value;

	public MathResult(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [" + this.value + "]";
	}
}
