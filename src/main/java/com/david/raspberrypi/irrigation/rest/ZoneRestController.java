package com.david.raspberrypi.irrigation.rest;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.david.raspberrypi.irrigation.control.ActivityManager;
import com.david.raspberrypi.irrigation.persistence.domain.IrrigationZone;

@RestController
@RequestMapping("/rest/zone")
public class ZoneRestController extends GenericRestController<IrrigationZone>{
	
	@Autowired
	private ActivityManager am;
	
	/**
	 * Gets the currently active irrigation zone or null if none is active.
	 * @return
	 */
	@PostMapping("/enabled/{zoneId}")
	public ResponseEntity<String> setZoneEnabled(@PathVariable("zoneId") Integer zoneId, @RequestParam("enabled") boolean enabled ){
		Optional<IrrigationZone> optionalZone = repo.findById(zoneId);
		
		if (optionalZone.isPresent()) {
			IrrigationZone zone = optionalZone.get();
			zone.setEnabled(enabled);
			repo.save(zone);
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.badRequest().body("Zone with the id: " + zoneId + " does not exist.");
		}
		
	}

	@Override
	public IrrigationZone getActive() {
		return am.getActiveZone();
	}
}