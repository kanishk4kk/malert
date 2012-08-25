package com.sysquare.malert.web.scheduler;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;



public class AlertCronScheduler {

    public AlertCronScheduler()throws Exception{
        SchedulerFactory sf=new StdSchedulerFactory();
        Scheduler sched=sf.getScheduler();
        sched.start();
        JobDetail jd=new JobDetail("myjob",Scheduler.DEFAULT_GROUP,MailScheduerJob.class);
        SimpleTrigger st=new SimpleTrigger("mytrigger",Scheduler.DEFAULT_GROUP,new Date(), null,SimpleTrigger.REPEAT_INDEFINITELY,600L*1000L);
        sched.scheduleJob(jd, st);

        /* SchedulerFactory sf=new StdSchedulerFactory();
	  Scheduler sched=sf.getScheduler();
	  JobDetail jd=new JobDetail("MailScheduerJob","MailGroup",MailScheduerJob.class);
	  //CronTrigger ct=new CronTrigger("cronTrigger","group2","0 0/5 * * * ?");
	  CronTrigger ct=new CronTrigger("cronTrigger","mAlertGroup","0 0/1 0 * * ?");

	  sched.scheduleJob(jd,ct);
	  sched.start();*/

    }
    public static void main(String args[]){
        try{
            new AlertCronScheduler();
        }catch(Exception e){}  
    }
}	



