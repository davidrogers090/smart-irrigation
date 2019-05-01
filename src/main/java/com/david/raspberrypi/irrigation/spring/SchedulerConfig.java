package com.david.raspberrypi.irrigation.spring;

import java.io.IOException;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.david.raspberrypi.irrigation.control.job.ActivateProgram;
import com.david.raspberrypi.irrigation.control.job.ActivateZone;
import com.david.raspberrypi.irrigation.control.schedule.Schedulable;

@Configuration
public class SchedulerConfig {
	
	@Bean
    public SpringBeanJobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }
 
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(SpringBeanJobFactory jobFactory, @Qualifier("ZONE") JobDetail zone, @Qualifier("PROGRAM") JobDetail program)
            throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(jobFactory);
        factory.setJobDetails(zone, program);
//        factory.setTriggers(simpleJobTrigger);
        return factory;
    }
 
//    @Bean
//    public SimpleTriggerFactoryBean oneTimeJobTrigger(JobDetail jobDetail) {
// 
//        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
//        factoryBean.setJobDetail(jobDetail);
//        factoryBean.setStartDelay(0L);
//        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
//        factoryBean.setRepeatInterval(1000);
//        return factoryBean;
//    }
 
    @Bean(name="ZONE")
    public JobDetailFactoryBean activateZoneJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(ActivateZone.class);
        factoryBean.setName(Schedulable.Type.ZONE.toString());
        factoryBean.setDurability(true);
        return factoryBean;
    }
    
    @Bean(name="PROGRAM")
    public JobDetailFactoryBean activateProgramJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(ActivateProgram.class);
        factoryBean.setName(Schedulable.Type.PROGRAM.toString());
        factoryBean.setDurability(true);
        return factoryBean;
    }
}
