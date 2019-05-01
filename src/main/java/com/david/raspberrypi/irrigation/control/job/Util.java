package com.david.raspberrypi.irrigation.control.job;

import org.jboss.logging.Logger;

import com.david.raspberrypi.irrigation.control.ActivityManager;
import com.david.raspberrypi.irrigation.gpio.GpioUtil;
import com.david.raspberrypi.irrigation.persistence.domain.IrrigationZone;

public final class Util {
	
	private static final Logger LOGGER = Logger.getLogger(Util.class);
	private static final long MILLIS_PER_MINUTE = 60000;
			
	private Util() {} // Utility classes should never be instantiated
	
	public static void executeZone(IrrigationZone zone, Integer durationOverride, ActivityManager am) {
		
		int duration = durationOverride == null ? zone.getDuration() : durationOverride;
		
		am.setActiveZone(zone, duration);
		GpioUtil.activate(zone.getId());
		try {
			Thread.sleep(MILLIS_PER_MINUTE * duration);
		} catch (InterruptedException e) {
			LOGGER.error("Unable to finish waiting", e); 
		} finally {
			am.setActiveZone(null, null);
			GpioUtil.deactivate(zone.getId());
		}
	}
}
