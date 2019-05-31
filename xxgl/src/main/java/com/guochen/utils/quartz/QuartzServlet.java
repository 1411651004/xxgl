package com.guochen.utils.quartz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzServlet extends HttpServlet {
	Scheduler scheduler = null;

	@Override
	public void init() throws ServletException {
		Logger log = Logger.getLogger(this.getClass());
		try {
			log.info("quartz定时任务准备启动……");
			SchedulerFactory sf = new StdSchedulerFactory();
			scheduler = sf.getScheduler();

			scheduler.start();
			log.info("quartz定时任务启动完成……");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		super.init();
	}
}
