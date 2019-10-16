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
import com.david.raspberrypi.irrigation.persistence.domain.IrrigationZone;
import com.david.raspberrypi.irrigation.persistence.repository.ZoneRepository;

@Component
@DisallowConcurrentExecution // We only want one zone active at a time to prevent water pressure issues
public class ActivateZone implements Job {

	private static final Logger LOGGER = Logger.getLogger(ActivateZone.class);
	
	@Autowired
	private ActivityManager am;
	
	@Autowired
	private ZoneRepository zr;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Integer id = (Integer) context.getTrigger().getJobDataMap().get("id");
		Integer durationOverride = (Integer) context.get("durationOverride");
		
		Optional<IrrigationZone> optional = zr.findById(id);
		
		IrrigationZone zone = optional.orElseThrow(() -> new JobExecutionException("Zone by id: " + id + " does not exist"));
		
		Util.executeZone(zone, durationOverride, am);
	}

}
