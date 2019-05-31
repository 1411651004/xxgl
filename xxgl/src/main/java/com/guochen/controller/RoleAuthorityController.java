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
import com.guochen.model.TRole;
import com.guochen.model.TRoleAuthority;
import com.guochen.page.Page;
import com.guochen.service.AuthorityService;
import com.guochen.service.RoleAuthorityService;
import com.guochen.service.RoleService;
import com.guochen.utils.CommonUtils;
import com.guochen.utils.ZTreePojo;

@Controller
@RequestMapping("/roleauthority")
public class RoleAuthorityController {
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private RoleAuthorityService roleauthorityservice;
	@Autowired
	private AuthorityService authorityservice;
	@Autowired
	private RoleService roleservice;
	
	@RequestMapping("/manage")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,int roleId){
		if(roleId!=0){
			TRole role = roleservice.selectByPrimeryKey(roleId);
			if(role==null){
				return null;
			}
			request.setAttribute("role", role);
			TAuthority authority = new TAuthority();
			List<TAuthority> allList = authorityservice.selectList(authority);
			//List<TAuthority> parentList = new ArrayList<TAuthority>();
			List<TAuthority> roleList =  authorityservice.selectAuthorityByRoleId(roleId);
			if(allList!=null && roleList!=null){
				for(TAuthority au:allList){
					for(TAuthority ay:roleList){
						if(au.getId().equals(ay.getId())){
							au.setIsCheck(1);
						}
					}
					//if(au.getParentId()==0){
					//	parentList.add(au);
					//}
				}
			}
			/*if(parentList!=null && allList!=null){
				for(TAuthority au:parentList){
					List<TAuthority> childrenList = new ArrayList<TAuthority>();
					for(TAuthority al:allList){
						if(al.getParentId()==au.getId()){
							childrenList.add(al);
						}
					}
					au.setChildrenList(childrenList);
				}
			}*/
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
					treeList.add(tree);
				}
			}
			request.setAttribute("json4Ztree", JSONArray.fromObject(treeList));
		}else{
			TAuthority authority = new TAuthority();
			List<TAuthority> allList = authorityservice.selectList(authority);
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
					treeList.add(tree);
				}
			}
			request.setAttribute("json4Ztree", JSONArray.fromObject(treeList));
		}
		
		return "/role/add";
	}

	
	@RequestMapping("/save")
	@ResponseBody
	public int save(TRoleAuthority roleauth){
		if(roleauth.getRoleId()!=null && roleauth.getAuthorityId()!=null){
			TRoleAuthority obj = roleauthorityservice.selectByRoleAndAuthorityId(roleauth);
			if(obj==null){
				roleauthorityservice.save(roleauth);
				return 1;
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public int delete(TRoleAuthority roleauth){
		if(roleauth.getRoleId()!=null && roleauth.getAuthorityId()!=null){
			TRoleAuthority obj = roleauthorityservice.selectByRoleAndAuthorityId(roleauth);
			if(obj!=null){
				roleauthorityservice.deleteByPrimaryKey(obj.getId());
				return 1;
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
	
	@RequestMapping("/saveOrDelete")
	@ResponseBody
	public int saveOrDelete(TRoleAuthority roleauth){
		if(roleauth.getRoleId()!=null && roleauth.getAuthorityId()!=null){
			TRoleAuthority obj = roleauthorityservice.selectByRoleAndAuthorityId(roleauth);
			if(obj!=null){
				roleauthorityservice.deleteByPrimaryKey(obj.getId());
			}else{
				roleauth.setCreateTime(CommonUtils.date2Str(new Date()));
				roleauthorityservice.save(roleauth);
			}
			return 1;
		}else{
			return 0;
		}
	}
	@RequestMapping("/deleteAndSave")
	@ResponseBody
	public int deleteAndSave(String str,int roleId){
		if(roleId!=0){
			roleauthorityservice.deleteByRoleId(roleId);
			String[] strarr = str.split("\\|");
			for(String s:strarr){
				if(s.length()==0){continue;}
				TRoleAuthority roleauth=new TRoleAuthority();
				roleauth.setAuthorityId(Integer.parseInt(s));
				roleauth.setRoleId(roleId);
				roleauthorityservice.save(roleauth);
			}
		}else{
			String[] strarr = str.split("\\|");
			for(String s:strarr){
				TRoleAuthority roleauth=new TRoleAuthority();
				roleauth.setAuthorityId(Integer.parseInt(s));
				roleauth.setRoleId(roleId);
				roleauthorityservice.save(roleauth);
			}
		}
		return 0;
	}
	@RequestMapping("/del")
	@ResponseBody
	public int delete(Integer roleId){
		if(roleId!=0){
			roleauthorityservice.deleteByRoleId(roleId);
		}
		return 0;
	}
	
}
