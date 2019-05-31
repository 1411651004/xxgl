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
import com.guochen.model.TRole;
import com.guochen.model.TUser;
import com.guochen.page.Page;
import com.guochen.service.BuyerService;
import com.guochen.service.RoleService;
import com.guochen.service.UserService;
import com.guochen.utils.CommonUtils;
import com.guochen.utils.Md5Util;

@Controller
@RequestMapping("/buyer")
public class BuyerController {
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private BuyerService buyerservice;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,TBuyer buyer){
		List<TBuyer> buyerList = buyerservice.selectPageList(page,null);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			buyerList = buyerservice.selectPageList(page,null);
		}
		request.setAttribute("buyerList", buyerList);
		request.setAttribute("page", page);
		
		return "/buyer/list";
	}

	@RequestMapping("/getList")
	public String getList(HttpServletRequest request, HttpServletResponse response,Page page,TBuyer buyer){
		
		List<TBuyer> buyerList = buyerservice.selectPageList(page,buyer);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			buyerList = buyerservice.selectPageList(page,buyer);
		}
		request.setAttribute("buyerList", buyerList);
		request.setAttribute("page", page);
		return "/buyer/getList";
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public int save(TBuyer buyer){
		buyer.setCreateTime(CommonUtils.date2Str(new Date()));
		buyerservice.save(buyer);
		return 1;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public int delete(TBuyer buyer){
		if(buyer.getId()!=null){
			buyerservice.delete(buyer.getId());
			return 1;
		}
		return 0;
	}
	@RequestMapping("/del")
	@ResponseBody
	public int delete(int buyerId){
		if(buyerId!=0){
			buyerservice.delete(buyerId);
			return 1;
		}
		return 0;
	}
	@RequestMapping("/add")
	public String addView(HttpServletRequest request,int buyerId){
		if(buyerId!=0){
			TBuyer tb = buyerservice.selectByPrimeryKey(buyerId);
			request.setAttribute("tb", tb);
		}
		return "/buyer/add";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public int update(TBuyer buyer){
		if(buyer.getId()!=null){
			TBuyer us = buyerservice.selectByPrimeryKey(buyer.getId());
			if(us!=null){
				us.setUpdateTime(CommonUtils.date2Str(new Date()));
				us.setBuyerAccount(buyer.getBuyerAccount());
				us.setBuyerAddr(buyer.getBuyerAddr());
				us.setBuyerBankAccount(buyer.getBuyerBankAccount());
				us.setBuyerName(buyer.getBuyerName());
				us.setBuyerPhone(buyer.getBuyerPhone());
				us.setBuyerTaxreceNo(buyer.getBuyerTaxreceNo());
				buyerservice.updateByPrimaryKey(us);
			}else{
				return 0;
			}
		}else{
			return 0;
		}
		return 1;
	}
	
}
