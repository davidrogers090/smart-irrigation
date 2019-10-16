package com.david.raspberrypi.irrigation.control;

import java.time.Duration;
import java.time.Instant;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

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

	public IrrigationZone getActiveZone() {
		return activeZone;
	}

	public Program getActiveProgram() {
		return activeProgram;
	}

	public void setActiveZone(IrrigationZone zone, Integer duration) {
		activeZone = zone;
		
		Instant completion = null;
		if (zone != null) {
			completion = Instant.now().plus(Duration.ofMinutes(duration));
		}
		Active<IrrigationZone> active = new Active<>(zone, completion);

		messageSender.convertAndSend("/active/zone", active);
	}

	public void setActiveProgram(Program program) {
		activeProgram = program;

		int minutes = 0;
		
		if (program != null && program.getZones() != null) {
			if (program.getDurationOverride() != null) {
				//If the duration override is set, do multiplication to estimate completion time
				minutes = program.getDurationOverride() * program.getZones().size();
			}
			else {
				//Otherwise, use addition
				for (IrrigationZone zone : program.getZones()) {
					minutes += zone.getDuration();
				}
			}
		}
		
		Active<Program> active = new Active<>(program, Instant.now().plus(Duration.ofMinutes(minutes)));
		messageSender.convertAndSend("/active/program", active);
	}

}
