package com.david.raspberrypi.irrigation.control.job;

import java.util.Optional;

import org.jboss.logging.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.david.raspberrypi.irrigation.control.ActivityManager;
import com.david.raspberrypi.irrigation.gpio.GpioUtil;
import com.david.raspberrypi.irrigation.persistence.domain.IrrigationZone;
import com.david.raspberrypi.irrigation.persistence.domain.Program;
import com.david.raspberrypi.irrigation.persistence.repository.ProgramRepository;
import com.david.raspberrypi.irrigation.persistence.repository.ZoneRepository;

@Component
@DisallowConcurrentExecution
public class ActivateProgram implements Job {

	private static final Logger LOGGER = Logger.getLogger(ActivateProgram.class);
	
	@Autowired
	private ActivityManager am;
	
	@Autowired
	private ProgramRepository pr;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Integer id = (Integer) context.get("id");
		
		Optional<Program> optional = pr.findById(id);
		
		Program program = optional.orElseThrow(() -> new JobExecutionException("Program by id: " + id + " does not exist"));
		
		executeProgram(program);
	}
	
	private void executeProgram(Program program) {
		am.setActiveProgram(program);
		
		for (IrrigationZone zone : program.getZones()) {
			Util.executeZone(zone, program.getDurationOverride(), am);
		}
		
		am.setActiveProgram(null);
	}

}
