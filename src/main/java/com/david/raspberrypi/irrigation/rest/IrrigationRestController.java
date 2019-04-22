package com.david.raspberrypi.irrigation.rest;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.raspberrypi.irrigation.control.ActivityManager;
import com.david.raspberrypi.irrigation.persistence.domain.IrrigationZone;

@RestController
@RequestMapping("/rest")
public class IrrigationRestController {
	
	@Autowired
	private ActivityManager am;
	
	/**
	 * Path to access this method: http://<hostname>:8080/smart-irrigation/rest/hello
	 * @return
	 */
	@GetMapping("/hello")
    public Collection<String> sayHello() {
        return IntStream.range(0, 10)
          .mapToObj(i -> "Hello number " + i)
          .collect(Collectors.toList());
    }
	
	@GetMapping("/demo")
    public String demo() {
        return "This is definitely a good demo.";
    }
	
	@PutMapping("/zone/active/{zoneId}")
	public String activateZone(@PathVariable(value = "zoneId") Integer zoneId){
		
		IrrigationZone zone = new IrrigationZone(zoneId, "ZoneNameTest" + zoneId, 1);
		am.queueZone(zone, null);
		
		return null;
	}
	
	@GetMapping("/zone/active")
	public IrrigationZone getActiveZone(){
		IrrigationZone zone = am.getActiveZone();
		
		return zone;
	}
	
	@GetMapping("/zone")
	public IrrigationZone getAllZones(){
		return null;
	}
}
