package com.david.raspberrypi.irrigation.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.david.raspberrypi.irrigation.persistence.domain.Program;

/**
 * Boilerplate code to automatically generate the persistence methods
 * for the {@link Program} entity.
 * @author David
 *
 */
@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer>{

}
