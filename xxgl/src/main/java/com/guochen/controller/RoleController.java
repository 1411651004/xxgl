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

import com.guochen.model.TRole;
import com.guochen.page.Page;
import com.guochen.service.RoleService;
import com.guochen.utils.CommonUtils;

@Controller
@RequestMapping("/role")
public class RoleController {
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private RoleService roleservice;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,TRole role){
		List<TRole> roleList = roleservice.selectPageList(page,null);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			roleList = roleservice.selectPageList(page,null);
		}
		request.setAttribute("roleList", roleList);
		request.setAttribute("page", page);
		return "/role/list";
	}

	@RequestMapping("/getList")
	public String getList(HttpServletRequest request, HttpServletResponse response,Page page,TRole role){
		
		List<TRole> roleList = roleservice.selectPageList(page,role);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			roleList = roleservice.selectPageList(page,role);
		}
		request.setAttribute("roleList", roleList);
		request.setAttribute("page", page);
		return "/role/getList";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public int update(TRole role){
		if(role.getId()!=null){
			TRole ro = roleservice.selectByPrimeryKey(role.getId());
			if(ro!=null){
				ro.setUpdateTime(CommonUtils.date2Str(new Date()));
				ro.setRoleName(role.getRoleName());
				roleservice.updateByPrimaryKey(ro);
				return ro.getId();
			}else{
				return 0;
			}
		}else{
			//����
			TRole roleNew = new TRole();
			roleNew.setCreateTime(CommonUtils.date2Str(new Date()));
			roleNew.setUpdateTime(CommonUtils.date2Str(new Date()));
			roleNew.setRoleName(role.getRoleName());
			roleNew.setInterior("0");
			roleservice.save(roleNew);
			return roleNew.getId();
		}
	}
	@RequestMapping("/del")
	@ResponseBody
	public int del(Integer roleId){
		if(roleId.intValue()!=0){
			roleservice.deleteByPrimaryKey(roleId);
		}
		return 0;
	}
}
