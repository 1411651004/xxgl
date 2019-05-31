package com.guochen.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.guochen.model.TAuthority;
import com.guochen.page.Page;
import com.guochen.service.AuthorityService;
import com.guochen.utils.CommonUtils;
import com.guochen.utils.ZTreePojo;

@Controller
@RequestMapping("/authority")
public class AuthorityController {
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private AuthorityService authorityservice;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,TAuthority authority){
		//加载父级菜单
		authority.setParentId(0);
		List<TAuthority> parentList = authorityservice.selectList(authority);
		request.setAttribute("parentList", parentList);
		
		List<TAuthority> allList = authorityservice.selectList(null);
		List<ZTreePojo> treeList = new ArrayList<ZTreePojo>();
		if(allList!=null){
			TAuthority au = new TAuthority();
			ZTreePojo tree = new ZTreePojo();
			for (int i = 0; i < allList.size(); i++) {
				au = allList.get(i);
				tree = new ZTreePojo();
				tree.setChecked(au.getIsCheck()==1?true:false);
				tree.setId(au.getId());
				tree.setName(au.getAuName());
				tree.setOpen(false);
				tree.setpId(au.getParentId());
				tree.setUrl00(au.getUrl());
				treeList.add(tree);
			}
		}
		request.setAttribute("json4Ztree", JSONArray.fromObject(treeList));
		return "/authority/list";
	}
	
	@RequestMapping("/getList")
	public String getList(HttpServletRequest request, HttpServletResponse response,Page page,TAuthority authority){
		//加载父级菜单
				authority.setParentId(0);
				List<TAuthority> parentList = authorityservice.selectList(authority);
				request.setAttribute("parentList", parentList);
				
				List<TAuthority> allList = authorityservice.selectList(null);
				List<ZTreePojo> treeList = new ArrayList<ZTreePojo>();
				if(allList!=null){
					TAuthority au = new TAuthority();
					ZTreePojo tree = new ZTreePojo();
					for (int i = 0; i < allList.size(); i++) {
						au = allList.get(i);
						au = allList.get(i);
						tree = new ZTreePojo();
						tree.setChecked(au.getIsCheck()==1?true:false);
						tree.setId(au.getId());
						tree.setName(au.getAuName());
						tree.setOpen(false);
						tree.setpId(au.getParentId());
						treeList.add(tree);
					}
				}
				request.setAttribute("json4Ztree", JSONArray.fromObject(treeList));
				return "/authority/list";
	}

	
	
	@RequestMapping("/saveParent")
	@ResponseBody
	public int saveParent(TAuthority authority){
		authority.setCreateTime(CommonUtils.date2Str(new Date()));
		authority.setParentId(0);
		authorityservice.save(authority);
		return 1;
	}
	
	@RequestMapping("/saveChild")
	@ResponseBody
	public int saveChild(TAuthority authority){
		authority.setCreateTime(CommonUtils.date2Str(new Date()));
		authorityservice.save(authority);
		return 1;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public int update(TAuthority authority){
		if(authority.getId()!=null){
			TAuthority au = authorityservice.selectByPrimeryKey(authority.getId());
			if(au!=null){
				au.setAuName(authority.getAuName());
				au.setUpdateTime(CommonUtils.date2Str(new Date()));
				au.setUrl(authority.getUrl());
				authorityservice.updateByPrimaryKey(au);
			}else{
				return 0;
			}
		}else{
			return 0;
		}
		return 1;
	}
	
}
