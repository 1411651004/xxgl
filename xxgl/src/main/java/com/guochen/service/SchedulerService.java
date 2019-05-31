package com.guochen.service;

import java.util.Date;
import java.util.Map;

import org.quartz.CronExpression;

public interface SchedulerService {
	public void schedule(String name, String cronExpression,String group);
	public void schedule(CronExpression cronExpression);
	public void schedule(String name, CronExpression cronExpression);
	public void schedule(String name, CronExpression cronExpression,String group);
	public void schedule(Map<String,String> map);
	public void pauseTrigger(String triggerName,String group);
	public void resumeTrigger(String triggerName,String group);
	public boolean removeTrigdger(String triggerName,String group);

}
