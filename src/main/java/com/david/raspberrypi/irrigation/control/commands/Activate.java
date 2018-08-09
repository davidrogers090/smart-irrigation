package com.david.raspberrypi.irrigation.control.commands;

import org.jboss.logging.Logger;

import com.david.raspberrypi.irrigation.control.IrrigationZone;
import com.david.raspberrypi.irrigation.gpio.GpioUtil;

public class Activate implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(Activate.class);
	
	private IrrigationZone zone;
	
	public Activate(IrrigationZone zone){
		this.zone = zone;
	}
	
	@Override
	public void run() {
		LOGGER.info("Activating irrigation zone: " + zone.getName()); 
		GpioUtil.activate(zone.getPinId());
	}

}
