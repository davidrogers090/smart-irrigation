package com.david.raspberrypi.irrigation.control;

public class IrrigationZone {

	private int pinId;
	private String name;
	private int duration;
	
	public IrrigationZone(int pinId, String name, int duration) {
		this.pinId = pinId;
		this.name = name;
		this.duration = duration;
	}

	public int getPinId() {
		return pinId;
	}

	public void setPinId(int pinId) {
		this.pinId = pinId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
