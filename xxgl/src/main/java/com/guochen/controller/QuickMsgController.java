package com.guochen.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guochen.model.TQuickMsg;
import com.guochen.page.Page;
import com.guochen.service.QuickmsgService;
import com.guochen.utils.CommonUtils;

@Controller
@RequestMapping("/quickMsg")
public class QuickMsgController {
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private QuickmsgService quickmsgservice;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,TQuickMsg quickmsg){
		List<TQuickMsg> quickmsgList = quickmsgservice.selectPageList(page,null);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			quickmsgList = quickmsgservice.selectPageList(page,null);
		}
		request.setAttribute("quickmsgList", quickmsgList);
		request.setAttribute("page", page);
		
		return "/quickmsg/quickMsglist";
	}

	@RequestMapping("/getList")
	public String getList(HttpServletRequest request, HttpServletResponse response,Page page,TQuickMsg quickmsg){
		
		List<TQuickMsg> quickmsgList = quickmsgservice.selectPageList(page,null);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			quickmsgList = quickmsgservice.selectPageList(page,null);
		}
		request.setAttribute("quickmsgList", quickmsgList);
		request.setAttribute("page", page);
		return "/quickMsg/getQuickMsgList";
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public int save(TQuickMsg quickmsg){
		quickmsgservice.save(quickmsg);
		return 1;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public int delete(TQuickMsg quickmsg){
		if(quickmsg.getId()!=null){
			quickmsgservice.delete(quickmsg.getId());
			return 1;
		}
		return 0;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public int update(TQuickMsg quickmsg){
		if(quickmsg.getId()!=null){
			TQuickMsg cd = quickmsgservice.selectByPrimeryKey(quickmsg.getId());
			if(cd!=null){
				cd.setRemindCon(quickmsg.getRemindCon());
				quickmsgservice.updateByPrimaryKey(cd);
			}else{
				return 0;
			}
		}else{
			return 0;
		}
		return 1;
	}
	
}
