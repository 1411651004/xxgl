package com.guochen.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.guochen.model.TNode;
import com.guochen.model.TNotice;
import com.guochen.model.TProject;
import com.guochen.model.TUser;
import com.guochen.page.Page;
import com.guochen.service.NodeService;
import com.guochen.service.NoticeService;
import com.guochen.service.ProjectService;
import com.guochen.service.UserService;
import com.guochen.utils.CommonUtils;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private ProjectService projectservice;
	@Autowired
	private NoticeService noticeservice;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,TNotice notice){
		List<TNotice> noticeList = noticeservice.selectPageList(page,null);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			noticeList = noticeservice.selectPageList(page,null);
		}
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("page", page);
		return "/notice/list";
	}

	@RequestMapping("/getList")
	public String getList(HttpServletRequest request, HttpServletResponse response,Page page,TNotice notice){
		
		if("4".equals(notice.getNoticeStatus())){
			notice.setNoticeStatus(null);
		}
		List<TNotice> noticeList = noticeservice.selectPageList(page,notice);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			noticeList = noticeservice.selectPageList(page,notice);
		}
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("page", page);
		return "/notice/getList";
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public int save(TNotice notice){
		notice.setCreateTime(CommonUtils.date2Str(new Date()));
		notice.setNoticeType("1");//默认中标公告
		notice.setNoticeStatus("0");//0新建
		noticeservice.save(notice);
		return 1;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public int update(TNotice notice){
		if(notice.getId()!=null){
			TNotice n = noticeservice.selectByPrimeryKey(notice.getId());
			if(n!=null){
				n.setNoticeStatus(notice.getNoticeStatus());
				n.setUpdateTime(CommonUtils.date2Str(new Date()));
				noticeservice.updateByPrimaryKey(n);
			}else{
				return 0;
			}
		}else{
			return 0;
		}
		return 1;
	}
	
	@RequestMapping("/addView")
	public String addView(){
		return "/notice/add";
	}

	@RequestMapping("/info")
	public String info(HttpServletRequest request){
		return "/notice/info";
	}
}
