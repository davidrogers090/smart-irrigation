package com.david.raspberrypi.irrigation.control;

import java.time.Instant;

import com.david.raspberrypi.irrigation.persistence.domain.IrrigationZone;

public class ActiveZone extends IrrigationZone {

	private Instant finishTime;

	public ActiveZone(int pinId, String name, int duration, Instant finishTime) {
		super(pinId, name, duration);
		this.finishTime = finishTime;
	}
	
	/**
	 * @return the finishTime
	 */
	public Instant getFinishTime() {
		return finishTime;
	}

	/**
	 * @param finishTime the finishTime to set
	 */
	public void setFinishTime(Instant finishTime) {
		this.finishTime = finishTime;
	} 
}
