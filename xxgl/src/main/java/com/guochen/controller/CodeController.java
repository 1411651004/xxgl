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

import com.guochen.model.TBuyer;
import com.guochen.model.TCode;
import com.guochen.model.TRole;
import com.guochen.model.TUser;
import com.guochen.page.Page;
import com.guochen.service.CodeService;
import com.guochen.service.RoleService;
import com.guochen.service.UserService;
import com.guochen.utils.CommonUtils;
import com.guochen.utils.Md5Util;

@Controller
@RequestMapping("/code")
public class CodeController {
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private CodeService codeservice;
	
	@RequestMapping("/seMelist")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,TCode code){
		List<TCode> codeList = codeservice.selectPageList(page,null);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			codeList = codeservice.selectPageList(page,null);
		}
		request.setAttribute("codeList", codeList);
		request.setAttribute("page", page);
		
		return "/code/seMelist";
	}

	@RequestMapping("/getSeMeList")
	public String getList(HttpServletRequest request, HttpServletResponse response,Page page,TCode code){
		
		List<TCode> codeList = codeservice.selectPageList(page,code);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			codeList = codeservice.selectPageList(page,code);
		}
		request.setAttribute("codeList", codeList);
		request.setAttribute("page", page);
		return "/code/getSeMeList";
	}
	
	
	@RequestMapping("/saveSeMe")
	@ResponseBody
	public int save(TCode code){
		code.setCreateTime(CommonUtils.date2Str(new Date()));
		code.setCodeType("seme");
		codeservice.save(code);
		return 1;
	}
	@RequestMapping("/add")
	public String addView(HttpServletRequest request,int codeId){
		if(codeId!=0){
			TCode code = codeservice.selectByPrimeryKey(codeId);
			request.setAttribute("code", code);
		}
		return "/code/add";
	}
	@RequestMapping("/deleteSeMe")
	@ResponseBody
	public int delete(TCode code){
		if(code.getId()!=null){
			codeservice.delete(code.getId());
			return 1;
		}
		return 0;
	}
	@RequestMapping("/del")
	@ResponseBody
	public int del(int codeId){
		if(codeId!=0){
			codeservice.delete(codeId);
			return 1;
		}
		return 0;
	}
	
	@RequestMapping("/updateSeMe")
	@ResponseBody
	public int update(TCode code){
		if(code.getId()!=null){
			TCode cd = codeservice.selectByPrimeryKey(code.getId());
			if(cd!=null){
				cd.setUpdateTime(CommonUtils.date2Str(new Date()));
				cd.setCodeName(code.getCodeName());
				cd.setCodeValue(code.getCodeValue());
				codeservice.updateByPrimaryKey(cd);
			}else{
				return 0;
			}
		}else{
			return 0;
		}
		return 1;
	}
	
}
