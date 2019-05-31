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
import com.guochen.model.TBuScope;
import com.guochen.page.Page;
import com.guochen.service.ScopeService;
import com.guochen.utils.CommonUtils;
import com.guochen.utils.ZTreePojo;

@Controller
@RequestMapping("/scope")
public class ScopeController {
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private ScopeService scopeservice;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,TBuScope scope){
		//加载父级菜单
		scope.setParentId(0);
		List<TBuScope> parentList = scopeservice.selectList(scope);
		request.setAttribute("parentList", parentList);
		
		List<TBuScope> allList = scopeservice.selectList(null);
		List<ZTreePojo> treeList = new ArrayList<ZTreePojo>();
		if(allList!=null){
			TBuScope sc = new TBuScope();
			ZTreePojo tree = new ZTreePojo();
			for (int i = 0; i < allList.size(); i++) {
				sc = allList.get(i);
				tree = new ZTreePojo();
				tree.setChecked(sc.getIsCheck()==1?true:false);
				tree.setId(sc.getId());
				tree.setName(sc.getNodeName());
				tree.setOpen(false);
				tree.setpId(sc.getParentId());
				treeList.add(tree);
			}
		}
		request.setAttribute("json4Ztree", JSONArray.fromObject(treeList));
		return "/scope/list";
	}
	
	@RequestMapping("/getList")
	public String getList(HttpServletRequest request, HttpServletResponse response,Page page,TBuScope scope){
		List<TBuScope> scopeList = scopeservice.getTree();
		request.setAttribute("scopeList", scopeList);
		return "/scope/getList";
	}

	
	
	@RequestMapping("/saveParent")
	@ResponseBody
	public int saveParent(TBuScope scope){
		scope.setCreateTime(CommonUtils.date2Str(new Date()));
		scope.setParentId(0);
		scopeservice.save(scope);
		return 1;
	}
	
	@RequestMapping("/saveChild")
	@ResponseBody
	public int saveChild(TBuScope scope){
		scope.setCreateTime(CommonUtils.date2Str(new Date()));
		scopeservice.save(scope);
		return 1;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public int update(TBuScope scope){
		if(scope.getId()!=null){
			TBuScope au = scopeservice.selectByPrimeryKey(scope.getId());
			if(au!=null){
				au.setNodeName(scope.getNodeName());
				au.setUpdateTime(CommonUtils.date2Str(new Date()));
				scopeservice.updateByPrimaryKey(au);
			}else{
				return 0;
			}
		}else{
			return 0;
		}
		return 1;
	}
	
}
