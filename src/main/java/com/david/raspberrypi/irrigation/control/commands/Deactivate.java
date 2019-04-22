package com.david.raspberrypi.irrigation.control.commands;

import org.jboss.logging.Logger;

import com.david.raspberrypi.irrigation.gpio.GpioUtil;
import com.david.raspberrypi.irrigation.persistence.domain.IrrigationZone;

public class Deactivate implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(Deactivate.class);
	
	private IrrigationZone zone;
	
	public Deactivate(IrrigationZone zone){
		this.zone = zone;
	}
	
	@Override
	public void run() {
		LOGGER.info("Deactivating irrigation zone: " + zone.getName()); 
		//GpioUtil.deactivate(zone.getPinId());
	}

}
