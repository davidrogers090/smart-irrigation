package com.david.raspberrypi.irrigation.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
//import javax.persistence.Table;

@Entity
//@Table(name="program", schema="irrigation")
public class Program {

	@Id
	private int id;
	
	/**
	 * Ordered list of {@link IrrigationZone}
	 */
	@ManyToMany
	private List<IrrigationZone> zones = new ArrayList<>();
	
	private Integer durationOverride = null;
	
	private String name;
	
	public Program(int id, String name, List<IrrigationZone> zones, Integer durationOverride) {
		this.id = id;
		this.name = name;
		this.zones = zones;
		this.durationOverride = durationOverride;
	}
	
	public Program() {
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
