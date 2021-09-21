package com.gupao.quartz.test;

import com.gupao.quartz.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Quartz的第一个案例
 */
public class Test01 {

    public static void main(String[] args) throws SchedulerException {
        // 1.创建一个对应的触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trgger1","group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2)
                        .repeatForever())
                .build();
        // 2.JobDetail

        JobDetail JobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .build();
        // 3.将 trigger 和 JobDetail 关联起来,并且启动起来
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        scheduler.scheduleJob(JobDetail,trigger);
        scheduler.start();

    }
}
