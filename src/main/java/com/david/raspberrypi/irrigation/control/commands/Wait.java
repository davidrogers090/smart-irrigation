package com.david.raspberrypi.irrigation.control.commands;

import org.jboss.logging.Logger;

public class Wait implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(Wait.class);
	private static final long MILLIS_PER_MINUTE = 60000;
	
	private int duration;
	
	/**
	 * Causes the thread to sleep.
	 * @param duration in minutes
	 */
	public Wait(int duration){
		this.duration = duration;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(MILLIS_PER_MINUTE * duration);
		} catch (InterruptedException e) {
			LOGGER.error("Unable to finish waiting", e); 
		}
	}

}
