package com.david.raspberrypi.irrigation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.raspberrypi.irrigation.control.ActivityManager;
import com.david.raspberrypi.irrigation.persistence.domain.Program;

@RestController
@RequestMapping("/rest/program")
public class ProgramRestController extends GenericRestController<Program>{
	
	@Autowired
	private ActivityManager am;

	@Override
	public Program getActive() {
		return am.getActiveProgram();
	}
}