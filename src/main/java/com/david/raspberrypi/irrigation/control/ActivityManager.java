package com.david.raspberrypi.irrigation.control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.logging.Logger;

import com.david.raspberrypi.irrigation.control.commands.*;

public class ActivityManager {

	private static final Logger LOGGER = Logger.getLogger(ActivityManager.class);
	
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
	private class UpdateActiveZone implements Runnable {
		private IrrigationZone zone;
		public UpdateActiveZone(IrrigationZone zone){
			this.zone = zone;
		}
		
		@Override
		public void run() {
			activeZone = zone;
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
		
		executorService.execute(new Activate(zone));
		executorService.execute(new UpdateActiveZone(zone));
		executorService.execute(new Wait(duration));
		executorService.execute(new Deactivate(zone));
		executorService.execute(new UpdateActiveZone(null));
	}

	public IrrigationZone getActiveZone() {
		return activeZone;
	}

	public void setActiveZone(IrrigationZone activeZone) {
		this.activeZone = activeZone;
	}

	public Program getActiveProgram() {
		return activeProgram;
	}

	public void setActiveProgram(Program activeProgram) {
		this.activeProgram = activeProgram;
	}


}
