package com.guochen.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guochen.service.ContractService;
import com.guochen.service.OfferService;
import com.guochen.service.ProjectService;
import com.guochen.service.UserService;

@Controller
@RequestMapping("/report")
public class ReportController {
	private static Logger log = Logger.getLogger(ReportController.class);
	@Autowired
	private UserService userservice;
	@Autowired
	private ProjectService proservice;
	@Autowired
	private ContractService contractservice;
	@Autowired
	private OfferService offerservice;
	
	@RequestMapping("/report")
	public String report(HttpServletRequest request){
		//每月用户数量
		List<Map> userlist = userservice.selectUserCountByMouth();
		JSONArray jsonarr = JSONArray.fromObject(userlist);
		request.setAttribute("userlist", jsonarr.toString());
		//每月招标数量
		List<Map> prolist = proservice.selectProCountByMouth();
		jsonarr = JSONArray.fromObject(prolist);
		request.setAttribute("prolist", jsonarr.toString());
		//每月成交金额
		List<Map> conlist = contractservice.selectConCountByMouth();
		jsonarr = JSONArray.fromObject(conlist);
		request.setAttribute("conlist", jsonarr.toString());
		//每月投标数量
		List<Map> offerlist = offerservice.selectOfferCountByMouth();
		jsonarr = JSONArray.fromObject(conlist);
		request.setAttribute("conlist", jsonarr.toString());
		return "/report/report";
	}
	@RequestMapping("/reportdata")
	public String reportdata(HttpServletRequest request){
		Calendar cal = Calendar.getInstance();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM");
		//每月用户数量
		List<Map> userlist = userservice.selectUserCountByMouth();
		List<Integer> userlistdata = new ArrayList<Integer>();
		List mouthdata1 = new ArrayList();
		int k = 0;
		for(int i=-5;i<=0;i++){
			cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, i);
			mouthdata1.add(format.format(cal.getTime()));
			k=0;
			for(Map map :userlist){
				if(format.format(cal.getTime()).equals(map.get("mouth"))){
					userlistdata.add(Integer.parseInt(map.get("co")+""));
					k=1;
				}
			}
			//没赋值
			if(k==0){
				userlistdata.add(0);
			}
			cal.add(Calendar.MONTH, -1);
		}
		request.setAttribute("userlistdata", userlistdata);
		request.setAttribute("jsonuserlist", JSONArray.fromObject(userlistdata).toString());
		//每月招标数量
		List<Map> prolist = proservice.selectProCountByMouth();
		List<Integer> prolistdata = new ArrayList<Integer>();
		for(int i=-5;i<=0;i++){
			cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, i);
			k=0;
			for(Map map :prolist){
				if(format.format(cal.getTime()).equals(map.get("dt"))){
					prolistdata.add(Integer.parseInt(map.get("num")+""));
					k=1;
				}
			}
			//没赋值
			if(k==0){
				prolistdata.add(0);
			}
		}
		request.setAttribute("prolistdata", prolistdata);
		request.setAttribute("jsonprolist", JSONArray.fromObject(prolistdata).toString());
		//每月成交金额
		List<Map> conlist = contractservice.selectConCountByMouth();
		List<Double> conlistdata = new ArrayList<Double>();
		Double d = new Double(0.00);
		for(int i=-5;i<=0;i++){
			cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, i);
			k=0;
			for(Map map :conlist){
				if(Double.parseDouble(map.get("acsum")+"")>d){
					d=Double.parseDouble(map.get("acsum")+"");
				}
				if(format.format(cal.getTime()).equals(map.get("mo"))){
					conlistdata.add(Double.parseDouble(map.get("acsum")+""));
					k=1;
				}
			}
			//没赋值
			if(k==0){
				conlistdata.add(0.00);
			}
		}
		request.setAttribute("conlistdata", conlistdata);
		request.setAttribute("jsonconlist", JSONArray.fromObject(conlistdata).toString());
		//每月投标数量
		List<Map> offerlist = offerservice.selectOfferCountByMouth();
		List<Integer> offerlistdata = new ArrayList<Integer>();
		for(int i=-5;i<=0;i++){
			cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, i);
			k=0;
			for(Map map :offerlist){
				if(format.format(cal.getTime()).equals(map.get("mou"))){
					offerlistdata.add(Integer.parseInt(map.get("co")+""));
					k=1;
				}
			}
			//没赋值
			if(k==0){
				offerlistdata.add(0);
			}
		}
		request.setAttribute("offerlistdata", offerlistdata);
		request.setAttribute("jsonofferlist", JSONArray.fromObject(offerlistdata).toString());
		request.setAttribute("mouthdata", mouthdata1);
		/*for(int i=0;i<6;i++){
			HashMap hs = new HashMap();
			hs.put("mouth", mouthdata1.get(i));//月份
			hs.put("pronum", prolistdata.get(i));//招标数
			hs.put("offernum", offerlistdata.get(i));//投标数
		}*/
		d = Math.ceil(d/2*3);
		request.setAttribute("d", d);
		return "/report/reportdata";
	}
}
