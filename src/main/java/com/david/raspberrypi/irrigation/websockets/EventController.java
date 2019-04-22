package com.david.raspberrypi.irrigation.websockets;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.david.raspberrypi.irrigation.persistence.domain.IrrigationZone;

@Controller
public class EventController {

	@MessageMapping("/zone")
	@SendTo("/active/zone")
	public ZoneChange zoneChanged(IrrigationZone zone) {
		return new ZoneChange(zone);
	}
}
