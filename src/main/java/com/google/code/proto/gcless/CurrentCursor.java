package com.google.code.proto.gcless;

public class CurrentCursor {

	private int currentPosition = 0;
	private int processUpToPosition = -1;
	
	public void addToPosition(int bytes) {
		currentPosition += bytes;
	}
	
	public int getCurrentPosition() {
		return currentPosition;
	}
	
	public int getProcessUpToPosition() {
		return processUpToPosition;
	}
	
	public void setProcessUpToPosition(int processUpToPosition) {
		this.processUpToPosition = processUpToPosition;
	}
	
}
