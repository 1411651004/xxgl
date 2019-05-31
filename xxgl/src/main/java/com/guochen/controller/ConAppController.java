package com.guochen.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.guochen.model.TConapp;
import com.guochen.model.TContract;
import com.guochen.model.TNode;
import com.guochen.model.TOffer;
import com.guochen.model.TProject;
import com.guochen.model.TTemplate;
import com.guochen.model.TUser;
import com.guochen.page.Page;
import com.guochen.service.ConAppService;
import com.guochen.service.ContractService;
import com.guochen.service.NodeService;
import com.guochen.service.OfferService;
import com.guochen.service.ProjectService;
import com.guochen.service.TemplateService;
import com.guochen.service.UserService;
import com.guochen.utils.CommonUtils;
import com.guochen.utils.DocConverter;
import com.guochen.utils.HFWordUtil;

@Controller
@RequestMapping("/comapp")
public class ConAppController {
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private ContractService contractservice;
	@Autowired
	private ProjectService projectservice;
	@Autowired
	private OfferService offerservice;
	@Autowired
	private TemplateService templateservice;
	@Autowired
	private NodeService nodeservice;
	@Autowired
	private ConAppService conappservice;
	
	@RequestMapping("/save")
	@ResponseBody
	public int save(HttpServletRequest request, HttpServletResponse response,TConapp conApp){
		if(conApp.getId()!=null){
			TConapp curCon = conappservice.selectByPrimeryKey(conApp.getId());
			curCon.setAppEx(conApp.getAppEx());
			curCon.setAppTime(CommonUtils.date2Str(new Date()));
			curCon.setAppStatus("1");
			conappservice.update(curCon);
			
			//设置下一审批
			TConapp nextCon = new TConapp();
			nextCon.setAppStatus("0");
			nextCon.setContractId(curCon.getContractId());
			nextCon.setCreateTime(CommonUtils.date2Str(new Date()));
			nextCon.setNodeId(curCon.getNode().getRearNodeId());
			conappservice.save(nextCon);
		}
		return 1;
	}
	
	//退回上一级
	@RequestMapping("/doBack")
	@ResponseBody
	public int doBack(HttpServletRequest request, HttpServletResponse response,TConapp conApp){
		if(conApp.getId()!=null){
			TConapp curCon = conappservice.selectByPrimeryKey(conApp.getId());
			curCon.setAppEx(conApp.getAppEx());
			curCon.setAppTime(CommonUtils.date2Str(new Date()));
			curCon.setAppStatus("1");
			conappservice.update(curCon);
			
			//设置下一审批(退回，上一审批人)
			TConapp nextCon = new TConapp();
			nextCon.setAppStatus("0");
			nextCon.setContractId(curCon.getContractId());
			nextCon.setCreateTime(CommonUtils.date2Str(new Date()));
			nextCon.setNodeId(curCon.getNode().getFrontNodeId());
			conappservice.save(nextCon);
		}
		return 1;
	}
	
	//退回至业务经理
	@RequestMapping("/doBack2Manager")
	@ResponseBody
	public int doBack2Manager(HttpServletRequest request, HttpServletResponse response,TConapp conApp){
		if(conApp.getId()!=null){
			TConapp curCon = conappservice.selectByPrimeryKey(conApp.getId());
			curCon.setAppEx(conApp.getAppEx());
			curCon.setAppTime(CommonUtils.date2Str(new Date()));
			curCon.setAppStatus("1");
			conappservice.update(curCon);
			
			TContract con = contractservice.selectByPrimeryKey(curCon.getContractId());
			con.setAppStatus("1");
			contractservice.updateTContract(con);
			
			//设置下一审批
			TConapp nextCon = new TConapp();
			nextCon.setAppStatus("0");
			nextCon.setContractId(curCon.getContractId());
			nextCon.setCreateTime(CommonUtils.date2Str(new Date()));
			nextCon.setNodeId(curCon.getNode().getRearNodeId());
			conappservice.save(nextCon);
			conappservice.deleteByContractId(curCon.getContractId());
		}
		return 1;
	}
	
	@RequestMapping("/mgetVerifyList")
	@ResponseBody
	public List<HashMap<String,String>> mgetVerifyList(int userId,int pageNum,HttpServletRequest request, HttpServletResponse response){
		Page page = new Page();
		page.setPageNum(pageNum);
		List<TConapp> conappList = conappservice.mselectVerifyPageList(page, userId);
		/*if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			conappList = conappservice.mselectVerifyPageList(page, userId);
		}*/
		List<HashMap<String,String>> resultlist = new ArrayList<HashMap<String,String>>();
		TConapp conapp = null;
		for(int i=0;i<conappList.size();i++){
			HashMap<String,String> hs = new HashMap<String, String>();
			conapp = conappList.get(i);
			conapp.getContract().setOffer(offerservice.selectByPrimeryKey(conapp.getContract().getOfferId()));
			String proName = conapp.getContract().getProject().getProName();
			String isurgent = conapp.getContract().getProject().getAlternateField1();
			hs.put("proname", proName);//项目名称
			hs.put("appstatus", conapp.getAppStatus());//审批状态
			hs.put("appex", conapp.getAppEx());//审批意见
			hs.put("createtime", conapp.getCreateTime());//收到审批时间
			hs.put("isurgent", isurgent);//是否加急
			hs.put("projectId", conapp.getContract().getProject().getId()+"");
			resultlist.add(hs);
		}
		return resultlist;
	}
}
