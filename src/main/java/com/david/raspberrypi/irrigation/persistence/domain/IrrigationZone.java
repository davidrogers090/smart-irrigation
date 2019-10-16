package com.david.raspberrypi.irrigation.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.david.raspberrypi.irrigation.control.schedule.Schedulable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //Need this to prevent some issues while sending to the client
public class IrrigationZone implements Schedulable {

	@Id
	@NotNull
	private int id;
	private String name;
	private int duration;
	
	@Column(columnDefinition="boolean default true")
	private boolean enabled = true;
	
	public IrrigationZone(int id, String name, int duration) {
		this.id = id;
		this.name = name;
		this.duration = duration;
	}
	
	/**
	 * Required for Hibernate
	 */
	public IrrigationZone() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int pinId) {
		this.id = pinId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	@Transient
	public Type getType() {
		return Type.ZONE;
	}

}
