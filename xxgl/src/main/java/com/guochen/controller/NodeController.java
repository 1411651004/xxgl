package com.guochen.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.guochen.model.TNode;
import com.guochen.model.TUser;
import com.guochen.service.NodeService;
import com.guochen.service.UserService;
import com.guochen.utils.CommonUtils;

@Controller
@RequestMapping("/node")
public class NodeController {
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private NodeService nodeservice;
	@Autowired
	private UserService userservice;
	
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response,TNode node){
		List<TNode> nodeList = nodeservice.listAllNodes(node);
		
		request.setAttribute("nodeList", nodeList);
		//审批人列表中只加载审批人员角色,因角色无编码，此处根据ID获取
		TUser user= new TUser();
		user.setRoleId(2);
		JSONArray jsonarray = new JSONArray();
		JSONObject jsonobject = new JSONObject();
		jsonobject.element("id",2);
		jsonobject.element("pid",0);
		jsonobject.element("name","审批人员");
		jsonobject.element("rid",-1);
		List<TUser> userList = userservice.listAllUsers(user);
//		//user部分
//		for(int i=0;i<userList.size();i++){
//			user = userList.get(i);
//			jsonobject = new JSONObject();
//			jsonobject.element("id", user.getId());
//			jsonobject.element("pid",user.getRoleId());
//			jsonobject.element("name",user.getLoginName());
//			jsonobject.element("rid",user.getId());
//			jsonobject.element("mobilephone", user.getAlternateField1());
//			jsonarray.add(jsonobject);
//		}
		request.setAttribute("userList", userList);
		return "/node/nodeset";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public int save(TNode node,HttpServletRequest request){
		//清空表
		nodeservice.delexceptfirst();
		//初始化数据
		node = new TNode();
		node.setFrontNodeId(0);
		node.setRearNodeId(2);
		node.setUserId(7);
		node.setNodeName("业务经理");
		nodeservice.save(node);
		//插入数据
		String param = request.getParameter("param");
		param = param.substring(0,param.length()-1);
		String [] datas = param.split(",");
		for(int i=0;i<datas.length;i++){
			node = new TNode();
			node.setFrontNodeId(i+1);
			if(i==datas.length-1){
				node.setRearNodeId(9999);
			}else{
				node.setRearNodeId(i+3);
			}
			node.setUserId(Integer.parseInt(datas[i]));
			node.setNodeName("test");
			nodeservice.save(node);
		}
		
		
		
		return 1;
		/*if(node.getId()==null){
			//保存操作
			if(node.getFrontNodeId()==0){
				TNode frontnode = nodeservice.selectByFrontId(0);
				//TNode rearnode = nodeservice.selectByParentId(frontnode.getId());
				int i = nodeservice.save(node);
				frontnode.setFrontNodeId(node.getId());
				//rearnode.setRearNodeId(node.getId());
				nodeservice.updateByPrimaryKey(frontnode);
				//nodeservice.updateByPrimaryKey(rearnode);
				return i;
			}else if(node.getRearNodeId()==9999){
				TNode rearnode = nodeservice.selectByParentId(9999);
				int i = nodeservice.save(node);
				rearnode.setRearNodeId(node.getId());
				nodeservice.updateByPrimaryKey(rearnode);
				return i;
			}else{
				TNode rearnode = nodeservice.selectByParentId(node.getRearNodeId());
				TNode frontnode = nodeservice.selectByFrontId(node.getFrontNodeId());
				int i = nodeservice.save(node);
				rearnode.setRearNodeId(node.getId());
				frontnode.setFrontNodeId(node.getId());
				nodeservice.updateByPrimaryKey(rearnode);
				nodeservice.updateByPrimaryKey(frontnode);
				return i;
			}
		}else{
			return nodeservice.updateByPrimaryKey(node);
		}*/
		
	}
	
	/**
	 * 删除节点
	 * @param node
	 * @return
	 */
	@RequestMapping("/delNode")
	@ResponseBody
	public int delNode(TNode node){
		if(node.getId()!=null){
			//保存操作
			TNode nd = nodeservice.selectByPrimeryKey(node.getId());
			//判断是否为开始节点或者结束节点
			if(nd.getFrontNodeId()==0 || nd.getRearNodeId()==9999){
				return 2;
			}
			TNode rearnode = nodeservice.selectByParentId(node.getId());
			TNode frontnode = nodeservice.selectByFrontId(node.getId());
			rearnode.setRearNodeId(frontnode.getId());
			frontnode.setFrontNodeId(rearnode.getId());
			nodeservice.updateByPrimaryKey(rearnode);
			nodeservice.updateByPrimaryKey(frontnode);
			nodeservice.del(node.getId());
			return 1;
		}else{
			return 0;
		}
	}
	
}
