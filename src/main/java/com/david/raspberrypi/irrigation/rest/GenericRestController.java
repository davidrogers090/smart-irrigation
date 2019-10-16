package com.david.raspberrypi.irrigation.rest;

import java.util.List;
import java.util.Optional;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.david.raspberrypi.irrigation.control.schedule.Schedulable;
import com.david.raspberrypi.irrigation.control.schedule.ScheduleManager;
import com.david.raspberrypi.irrigation.rest.dto.Schedule;

/**
 * Abstract rest controller for use with {@link Schedulable} entities.
 * Extend this class to inherit schedule and activation operations.
 * @author David
 *
 * @param <T>
 */
public abstract class GenericRestController<T extends Schedulable> {

	@Autowired
	protected JpaRepository<T, Integer> repo;
	
	@Autowired
	private ScheduleManager scheduleManager;
	
	
	@GetMapping("/demo")
    public String demo() {
        return "This is definitely a good demo.";
    }
	/**
	 * Activates the specified zone by pin id.
	 * @param id
	 * @param minutes optional duration override. E.g.  {@code http://<hostname>/smart-irrigation/zone/active/1?minutes=5}
	 * @return
	 * @throws SchedulerException 
	 */
	@PutMapping("/active/{id}")
	public ResponseEntity<String> activateZone(@PathVariable("id") Integer id, @RequestParam("minutes") Optional<Integer> minutes ) throws SchedulerException{
		Optional<T> entity = repo.findById(id);
		
		if (!entity.isPresent()) {
			return ResponseEntity.badRequest().body("Requested zone has not been set up.");
		}
		
		scheduleManager.once(entity.get(), minutes.orElse(null));
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Gets the currently active irrigation zone or null if none is active.
	 * @return
	 */
	@GetMapping("/active")
	public abstract T getActive();
	
	
	/**
	 * Gets all configured irrigation zones.
	 * @return
	 */
	@GetMapping("")
	public List<T> getAll(){
		return repo.findAll();
	}
	
	@PutMapping("/schedule")
	public void schedule(@RequestBody Schedule<T> scheduleRequest) throws SchedulerException{
		scheduleManager.schedule(scheduleRequest.getPayload(), scheduleRequest.getCronExpression());
	}
	
	@PutMapping("/unschedule")
	public void unschedule(@RequestBody Schedule<T> scheduleRequest) throws SchedulerException{
		
		scheduleManager.unschedule(scheduleRequest.getPayload());
	}

}
