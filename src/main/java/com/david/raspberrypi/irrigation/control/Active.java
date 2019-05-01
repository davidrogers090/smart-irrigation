package com.david.raspberrypi.irrigation.control;

import java.time.Instant;

import com.david.raspberrypi.irrigation.control.schedule.Schedulable;

public class Active<V extends Schedulable> {

	private V activatable;
	private Instant finishTime;
	
	public Active(V activatable, Instant finishTime) {
		this.activatable = activatable;
		this.finishTime = finishTime;
	}
	
	public V getZone() {
		return activatable;
	}

	public void setZone(V zone) {
		this.activatable = zone;
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
