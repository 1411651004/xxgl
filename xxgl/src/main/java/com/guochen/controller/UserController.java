package com.guochen.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guochen.model.TRole;
import com.guochen.model.TUser;
import com.guochen.page.Page;
import com.guochen.service.LoginService;
import com.guochen.service.RoleService;
import com.guochen.service.UserService;
import com.guochen.utils.CommonUtils;
import com.guochen.utils.Md5Util;

@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private UserService userservice;
	@Autowired
	private RoleService roleservice;
	@Autowired
	private LoginService loginservice;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,TUser user){
		List<TUser> userList = userservice.selectPageList(page,null);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			userList = userservice.selectPageList(page,null);
		}
		request.setAttribute("userList", userList);
		request.setAttribute("page", page);
		
		List<TRole> roleList = roleservice.selectList();
		if(roleList!=null && roleList.size()>0){
			TRole r = null;
			for(int i=0;i<roleList.size();i++){
				r = roleList.get(i);
				if("com".equals(r.getCode()) || "admin".equals(r.getCode())){
					roleList.remove(r);
				}
			}
		}
		request.setAttribute("roleList", roleList);
		
		return "/user/list";
	}

	@RequestMapping("/getList")
	public String getList(HttpServletRequest request, HttpServletResponse response,Page page,TUser user){
		
		List<TUser> userList = userservice.selectPageList(page,user);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			userList = userservice.selectPageList(page,user);
		}
		request.setAttribute("userList", userList);
		request.setAttribute("page", page);
		return "/user/getList";
	}
	@RequestMapping("/add")
	public String addView(HttpServletRequest request){
		List<TRole> roleList = roleservice.selectList();
		if(roleList!=null && roleList.size()>0){
			TRole r = null;
			for(int i=0;i<roleList.size();i++){
				r = roleList.get(i);
				if("com".equals(r.getCode()) || "admin".equals(r.getCode())){
					roleList.remove(r);
				}
			}
		}
		request.setAttribute("roleList", roleList);
		return "/user/add";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public int save(TUser user){
		user.setCreateTime(CommonUtils.date2Str(new Date()));
		user.setUserStatus("0");
		user.setLoginPwd(Md5Util.getMd5(user.getLoginPwd()));
		user.setComId(0);
		userservice.save(user);
		return 1;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public int delete(TUser user){
		if(user.getId()!=null){
			userservice.delete(user.getId());
			return 1;
		}
		return 0;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public int update(TUser user){
		if(user.getId()!=null){
			TUser us = userservice.selectByPrimeryKey(user.getId());
			if(us!=null){
				us.setUpdateTime(CommonUtils.date2Str(new Date()));
				us.setComId(user.getComId());
				us.setLoginPwd(Md5Util.getMd5(user.getLoginPwd()));
				us.setRoleId(user.getRoleId());
				userservice.updateByPrimaryKey(us);
			}else{
				return 0;
			}
		}else{
			return 0;
		}
		return 1;
	}
	
	@RequestMapping("/updateCurUserPwd")
	@ResponseBody
	public Map<String, String> updateCurUserPwd(HttpServletRequest request, String pwd, String oldPwd){
		Map<String, String> map = new HashMap<String, String>();
		map.put("rs", "1");
		if(oldPwd == null || oldPwd.trim().length()==0){
			map.put("rs", "0");
			map.put("msg", "�����벻��Ϊ��");
			return map;
		}
		TUser curUser = (TUser) request.getSession().getAttribute("user");
		if(pwd == null || pwd.trim().length()==0){
			map.put("rs", "0");
			map.put("msg", "�����벻��Ϊ��");
			return map;
		}else if(!pwd.matches("^[A-Za-z0-9]{5,10}$")){
			map.put("rs", "0");
			map.put("msg", "������5-10λ��СдӢ�Ļ��������");
			return map;
		}else if(!Md5Util.getMd5(oldPwd).equals(curUser.getLoginPwd())){
			map.put("rs", "0");
			map.put("msg", "ԭ�����������");
			return map;
		}
		curUser = loginservice.selectUserByLoginname(curUser.getLoginName());
		curUser.setLoginPwd(Md5Util.getMd5(pwd));
		userservice.updateByPrimaryKey(curUser);
		request.getSession().setAttribute("user", curUser);
		return map;
	}
	
}
