package com.david.raspberrypi.irrigation.control;

import java.util.ArrayList;
import java.util.List;

public class Program {

	/**
	 * Ordered list of {@link IrrigationZone}
	 */
	private List<IrrigationZone> zones = new ArrayList<>();
	
	private Integer durationOverride = null;
	
	private String name;
	
	public Program(String name) {
		this.name = name;
	}

	public List<IrrigationZone> getZones() {
		return zones;
	}

	public void setZones(List<IrrigationZone> zones) {
		this.zones = zones;
	}

	public Integer getDurationOverride() {
		return durationOverride;
	}

	public void setDurationOverride(Integer durationOverride) {
		this.durationOverride = durationOverride;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
