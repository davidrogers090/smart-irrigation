package com.david.raspberrypi.irrigation.control.commands;

import org.jboss.logging.Logger;

import com.david.raspberrypi.irrigation.gpio.GpioUtil;
import com.david.raspberrypi.irrigation.persistence.domain.IrrigationZone;

public abstract class Activate implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(Activate.class);
	private static final long MILLIS_PER_MINUTE = 60000;
	
	private IrrigationZone zone;
	private int duration;
	
	public Activate(IrrigationZone zone, int duration){
		this.zone = zone;
		this.duration = duration;
	}
	
	@Override
	public void run() {
		LOGGER.info("Activating irrigation zone: " + zone.getName()); 
		onUpdate(zone);
		//GpioUtil.activate(zone.getPinId());
		try {
			Thread.sleep(MILLIS_PER_MINUTE * duration);
		} catch (InterruptedException e) {
			LOGGER.error("Unable to finish waiting", e); 
		} finally {
			onUpdate(null);
			//GpioUtil.deactivate(zone.getPinId());
		}
	}
	
	public abstract void onUpdate(IrrigationZone zone);

}
