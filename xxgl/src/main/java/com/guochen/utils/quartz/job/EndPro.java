package com.guochen.utils.quartz.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.guochen.model.TProject;
import com.guochen.service.ProjectService;
import com.guochen.utils.CommonUtils;

public class EndPro implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobDataMap dataMap = arg0.getJobDetail().getJobDataMap();
		WebApplicationContext appcontext=ContextLoader.getCurrentWebApplicationContext();
		ProjectService projectservice=(ProjectService) appcontext.getBean("projectServiceImpl");
		TProject project = projectservice.selectByPrimeryKey(dataMap.getInt("id"));
		project.setProStarttime(CommonUtils.date2Str(new Date()));
		project.setProStatus("2");
		projectservice.updateByPrimaryKey(project);
	}

}
