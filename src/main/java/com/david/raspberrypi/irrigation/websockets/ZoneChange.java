package com.david.raspberrypi.irrigation.websockets;

import com.david.raspberrypi.irrigation.persistence.domain.IrrigationZone;

public class ZoneChange {

	private IrrigationZone zone;
	
	public ZoneChange(IrrigationZone zone){
		this.zone = zone;
	}

	public IrrigationZone getZone() {
		return zone;
	}

	public void setZone(IrrigationZone zone) {
		this.zone = zone;
	}
}
