package com.david.raspberrypi.irrigation.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.david.raspberrypi.irrigation.persistence.domain.IrrigationZone;

/**
 * Boilerplate code to automatically generate the persistence methods
 * for the {@link IrrigationZone} entity.
 * @author David
 *
 */
@Repository
public interface ZoneRepository extends JpaRepository<IrrigationZone, Integer>{

}
