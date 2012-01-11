package com.google.code.proto.gcless;

public class CurrentCursor {

	private int currentPosition = 0;
	
	public void addToPosition(int bytes) {
		currentPosition += bytes;
	}
	
	public int getCurrentPosition() {
		return currentPosition;
	}
	
	
	
}
