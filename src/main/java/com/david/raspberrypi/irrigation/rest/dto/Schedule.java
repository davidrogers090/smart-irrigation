package com.david.raspberrypi.irrigation.rest.dto;


public class Schedule<V> {
	
	private V payload;
	
	private String cronExpression;

	public V getPayload() {
		return payload;
	}

	public void setPayload(V payload) {
		this.payload = payload;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	
}