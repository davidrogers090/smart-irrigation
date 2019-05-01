package com.david.raspberrypi.irrigation.control.schedule;

public interface Schedulable {

	public enum Type {
		PROGRAM, ZONE
	}
	
	public int getId();
	
	public Type getType();
}
