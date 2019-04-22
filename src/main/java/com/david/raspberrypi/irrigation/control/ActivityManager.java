package com.david.raspberrypi.irrigation.control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.david.raspberrypi.irrigation.control.commands.Activate;
import com.david.raspberrypi.irrigation.persistence.domain.IrrigationZone;
import com.david.raspberrypi.irrigation.persistence.domain.Program;

@Component
public class ActivityManager {

	private static final Logger LOGGER = Logger.getLogger(ActivityManager.class);
	
	@Autowired
	private SimpMessagingTemplate messageSender;
	
	/**
	 * The currently active irrigation zone. null implies nothing is active
	 */
	private IrrigationZone activeZone = null;
	
	/**
	 * The currently active program. null implies that no program is running
	 */
	private Program activeProgram = null;

	/**
	 * The executor that ensures that only one zone can be active at a time
	 */
	private ExecutorService executorService = Executors.newSingleThreadExecutor();

	/**
	 * Inner class to asynchronously update the activeZone variable
	 * @author David
	 *
	 */
	private class ActivateImpl extends Activate {

		public ActivateImpl(IrrigationZone zone, int duration) {
			super(zone, duration);
		}

		@Override
		public void onUpdate(IrrigationZone zone) {
			activeZone = zone;
			if (zone == null) zone = new IrrigationZone(-1, "None", -1);
			messageSender.convertAndSend("/active/zone", zone);
		}
		
	}
	
	/**
	 * Inner class to asynchronously update the activeProgram variable
	 * @author David
	 *
	 */
	private class UpdateActiveProgram implements Runnable {
		private Program program;
		public UpdateActiveProgram(Program program){
			this.program = program;
		}
		
		@Override
		public void run() {
			activeProgram = program;
		}
		
	}
	
	public void queueProgram(Program program){
		
		
		LOGGER.debug("Starting program: " + program);
		executorService.execute(new UpdateActiveProgram(program));
		for (IrrigationZone zone : program.getZones()){
			queueZone(zone, program.getDurationOverride());
		}
		executorService.execute(new UpdateActiveProgram(null));
	}

	
	/**
	 * Enters a zone into the activation queue.  Ensures that only one zone
	 * is active at once.
	 * @param zone the irrigation zone to activate
	 * @param durationOverride the duration to use or null to use defaults
	 */
	public void queueZone(IrrigationZone zone, Integer durationOverride){

		int duration = zone.getDuration();
		if (durationOverride != null){
			duration = durationOverride;
		}
		
		executorService.execute(new ActivateImpl(zone, duration));
	}

	public IrrigationZone getActiveZone() {
		return activeZone;
	}

	public Program getActiveProgram() {
		return activeProgram;
	}


}
