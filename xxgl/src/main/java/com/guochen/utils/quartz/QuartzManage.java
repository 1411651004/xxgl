package com.guochen.utils.quartz;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

@Service
public class QuartzManage {
	private static SchedulerFactory sf;
	private static String JOB_GROUP_NAME = "group";
	private static String TRIGGER_GROUP_NAME = "trigger";
	public static void startJob(String jobName, Job job, String time)
			throws SchedulerException, ParseException {
		sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		JobDetail jobDetail = new JobDetail();
		jobDetail.setName(jobName+job.getClass());
		jobDetail.setGroup(JOB_GROUP_NAME);
		jobDetail.setJobClass(job.getClass());
		jobDetail.getJobDataMap().put("id", jobName);
		CronTrigger trigger = new CronTrigger(jobName+job.getClass(), TRIGGER_GROUP_NAME);
		trigger.setCronExpression(time);
		sched.scheduleJob(jobDetail, trigger);

		if (!sched.isShutdown()) {
			sched.start();
		}
	}

	/**
	 * 从Scheduler 移除当前的Job,修改Trigger
	 * 
	 * @param time
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public static void modifyJobTime(String jobName, Job job, String time)
			throws SchedulerException, ParseException {
		JobDetail jobDetail = new JobDetail();
		jobDetail.setName(jobName);
		jobDetail.setGroup(JOB_GROUP_NAME);
		jobDetail.setJobClass(job.getClass());
		jobDetail.getJobDataMap().put("proid", jobName);
		
		sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		Trigger trigger = sched.getTrigger(jobDetail.getName(),
				TRIGGER_GROUP_NAME);
		if (trigger != null) {
			CronTrigger ct = (CronTrigger) trigger;
			// 移除当前进程的Job
			sched.deleteJob(jobDetail.getName(), jobDetail.getGroup());
			// 修改Trigger
			ct.setCronExpression(time);
			System.out.println("CronTrigger getName " + ct.getJobName());
			// 重新调度jobDetail
			//start update by yanzs 20151120
			//sched.scheduleJob(jobDetail, ct);
			removeJob(jobDetail.getName());
			startJob(jobDetail.getName(), job, time);
			//end update by yanzs 20151120
		}
	}
	
//	public static void modifyJobTime(JobDetail jobDetail, String time)
//			throws SchedulerException, ParseException {
//		sf = new StdSchedulerFactory();
//		Scheduler sched = sf.getScheduler();
//		Trigger trigger = sched.getTrigger(jobDetail.getName(),
//				TRIGGER_GROUP_NAME);
//		if (trigger != null) {
//			CronTrigger ct = (CronTrigger) trigger;
//			// 移除当前进程的Job
//			sched.deleteJob(jobDetail.getName(), jobDetail.getGroup());
//			// 修改Trigger
//			ct.setCronExpression(time);
//			System.out.println("CronTrigger getName " + ct.getJobName());
//			// 重新调度jobDetail
//			sched.scheduleJob(jobDetail, ct);
//		}
//	}
  /** 
    * 移除一个任务(使用默认的任务组名，触发器名，触发器组名) 
    * @param jobName 
    * @throws SchedulerException 
    */  
   public static void removeJob(String jobName)   
                               throws SchedulerException{  
	   sf = new StdSchedulerFactory();
       Scheduler sched = sf.getScheduler();  
       sched.pauseTrigger(jobName,TRIGGER_GROUP_NAME);//停止触发器  
       sched.unscheduleJob(jobName,TRIGGER_GROUP_NAME);//移除触发器  
       sched.deleteJob(jobName,JOB_GROUP_NAME);//删除任务  
   }  

}
	
