package com.david.raspberrypi.irrigation.control.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.david.raspberrypi.irrigation.rest.dto.Schedule;

@Component
public class ScheduleManager {
	
	@Autowired
	private Scheduler scheduler;
	
	/**
	 * Convenience method to call schedule without a durationOverride.
	 * @param schedulable
	 * @param cronExpression
	 * @throws SchedulerException
	 */
	public void schedule(Schedulable schedulable, String cronExpression) throws SchedulerException {
		schedule(schedulable, cronExpression, null);
	}
	
	public void schedule(Schedulable schedulable, String cronExpression, Integer durationOverride) throws SchedulerException{
		JobDataMap data = generateJobData(schedulable, durationOverride);
		
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(generateTriggerKey(schedulable))
                .startNow()
                .forJob(generateJobKey(schedulable))
                .usingJobData(data)
                .withSchedule(scheduleBuilder)
                .build();
        
        scheduler.scheduleJob(trigger);
	}
	
	public void unschedule(Schedulable schedulable) throws SchedulerException {
		scheduler.unscheduleJob(generateTriggerKey(schedulable));
	}
	
	/**
	 * Runs the job once, as soon as possible.
	 * @param schedulable
	 * @throws SchedulerException
	 */
	public void once(Schedulable schedulable, Integer durationOverride) throws SchedulerException {
		JobDataMap data = generateJobData(schedulable, durationOverride);
		scheduler.triggerJob(generateJobKey(schedulable), data);
	}
	
	/**
	 * Returns a list of scheduled items by type.
	 * @param type the type of item (program, zone)
	 * @return
	 * @throws SchedulerException
	 */
	public List<Schedule<Integer>> getScheduled(Schedulable.Type type) throws SchedulerException {
		Set<TriggerKey> keys = scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(type.toString()));
		return getSchedule(keys);
	}
	
	private List<Schedule<Integer>> getSchedule(Set<TriggerKey> keys) throws SchedulerException{
		List<Schedule<Integer>> scheduledPrograms = new ArrayList<>();
		for (TriggerKey key : keys) {
			Schedule<Integer> schedule = new Schedule<>();
			
			CronTrigger ct = (CronTrigger) scheduler.getTrigger(key); 
			schedule.setCronExpression(ct.getCronExpression());
			
			Integer id = (Integer) ct.getJobDataMap().get("id");
			schedule.setPayload(id);
			
			scheduledPrograms.add(schedule);
		}

		return scheduledPrograms;
	}
	
	private JobDataMap generateJobData(Schedulable schedulable, Integer durationOverride) {
		JobDataMap jdm = new JobDataMap();
		jdm.put("id", schedulable.getId());
		
		if (durationOverride != null) {
			jdm.put("durationOverride", durationOverride);
		}
		return jdm;
	}
	
	private JobKey generateJobKey(Schedulable schedulable) {
		return new JobKey(schedulable.getType().toString());
	}
	
	private TriggerKey generateTriggerKey(Schedulable schedulable) {
		return new TriggerKey("trigger-" + String.valueOf(schedulable.getId()), schedulable.getType().toString());
	}
}
