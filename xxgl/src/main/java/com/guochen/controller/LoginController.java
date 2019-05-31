package com.guochen.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guochen.dao.TRemindMapper;
import com.guochen.model.TAuthority;
import com.guochen.model.TCom;
import com.guochen.model.TRemind;
import com.guochen.model.TRole;
import com.guochen.model.TUser;
import com.guochen.page.Page;
import com.guochen.service.ComService;
import com.guochen.service.ContractService;
import com.guochen.service.LoginService;
import com.guochen.service.OfferService;
import com.guochen.service.ProjectService;
import com.guochen.service.UserService;
import com.guochen.utils.CommonUtils;

@Controller
@RequestMapping("/login")
public class LoginController {
	private static Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	private LoginService loginservice;
	@Autowired
	private TRemindMapper tremindmapper;
	@Autowired
	private ComService comservice;
	@Autowired
	private ContractService contractservice;
	@Autowired
	private UserService userservice;
	@Autowired
	private ProjectService proservice;
	@Autowired
	private OfferService offerservice;
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response,TUser user) {
		String yzm = request.getParameter("yzm");
		System.out.println(CommonUtils.addZeroForNum(contractservice.selectCountByMouth()+"", 4));
		if(yzm==null&&yzm.length()!=0){
			return "验证码不能为空";
		}
		if(!yzm.toUpperCase().equals(request.getSession().getAttribute("validatecode"))){
			return "验证码输入错误";
		}
		// TODO Auto-generated method stub
		String str = loginservice.login(user,request.getParameter("iscookie")==null?"0":request.getParameter("iscookie"));
		if(str.equals("")){
			user = loginservice.selectUserByLoginname(user.getLoginName());
			request.getSession().setAttribute("user",user);
			TRole role = loginservice.selectRoleByRoleId(user.getRoleId());
			request.getSession().setAttribute("role", role);
			List<TAuthority> list = loginservice.selectAuthorityByRoleId(role.getId());
			request.getSession().setAttribute("authorutyList", list);
			TCom com = loginservice.selectComByComId(user.getComId());
			request.getSession().setAttribute("com", com);
			try {
				Cookie nameCookie=new Cookie("name",URLEncoder.encode(user.getLoginName(),"utf-8"));  
				Cookie pswCookie=new Cookie("psw",user.getLoginPwd());
				nameCookie.setPath(request.getContextPath()+"/");  
				pswCookie.setPath(request.getContextPath()+"/"); 
				if(request.getParameter("savepwd")!=null&&request.getParameter("savepwd").equals("1")){
					nameCookie.setMaxAge(365*24*60*60);    
					pswCookie.setMaxAge(365*24*60*60);
				}else{
					nameCookie.setMaxAge(0);    
					pswCookie.setMaxAge(0);
				}
				response.addCookie(nameCookie);  
		        response.addCookie(pswCookie); 
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			return "";
		}else{
			return str;
		}
	}
	@RequestMapping("/mlogin")
	@ResponseBody
	public Object mobilelogin(TUser user){
		String str = loginservice.login(user,"0");
		if(str.equals("")){//��¼�ɹ�
			user = loginservice.selectUserByLoginname(user.getLoginName());//��ѯuser��Ϣ
			return user;
		}else{
			return str;
		}
		
	}
	/**
	 * 需要优化
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/loadAuth")
	@ResponseBody
	public String loadAuth(HttpServletRequest request,TUser user){
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		TRole role=(TRole)request.getSession().getAttribute("role");
		List<TAuthority> parentlist = loginservice.selectParentAuthorityByRoleId(role.getId());
		List<TAuthority> childlist = null;
		JSONArray parentjsonarray = new JSONArray();
		/*JSONObject houtai = new JSONObject();
		houtai.element("pid",0);
		houtai.element("title","后台首页");
		houtai.element("icon","larry-houtaishouye");
		houtai.element("href", "/main");
		parentjsonarray.add(houtai);*/
		for(TAuthority auth:parentlist){
			JSONObject jsonobject = new JSONObject();
			jsonobject.element("pid",auth.getId());
			jsonobject.element("title",auth.getAuName());
			jsonobject.element("icon","larry-zidingyicaidan");
			childlist=loginservice.selectChildAuthorityByRoleId(role.getId());
			JSONArray childjsonarray = new JSONArray();
			for(TAuthority childauth:childlist){
				if(childauth.getParentId()!=auth.getId()){
					continue;
				}
				JSONObject childjsonobject = new JSONObject();
				childjsonobject.element("title",childauth.getAuName());
				childjsonobject.element("icon","larry-zidingyicaidan");
				childjsonobject.element("href", basePath+childauth.getUrl());
				childjsonarray.add(childjsonobject);
			}
			jsonobject.element("children",childjsonarray);
			jsonobject.element("spread","true");
			parentjsonarray.add(jsonobject);
		}
		return parentjsonarray.toString();
	}
	@RequestMapping("/userexit")
	@ResponseBody
	public String userexit(HttpServletRequest request,TUser user){
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("role");
		request.getSession().removeAttribute("authorutyList");
		request.getSession().removeAttribute("com");
		return "";
	}
	@RequestMapping("/initdata")
	@ResponseBody
	public List<TRemind> initdata(HttpServletRequest request,TUser user){
		List<TRemind> list = tremindmapper.selectNotReadByUserId(((TUser)(request.getSession().getAttribute("user"))).getId());
		if(list.size()>3){
			list = list.subList(0,3);
		}
		for(int i=0;i<list.size();i++){
			TRemind remind = list.get(i);
			remind.setIsread("1");//改为已读
			tremindmapper.updateByPrimaryKey(remind);
		}
		return list;
	}
	@RequestMapping("/main")
	public String reportdata(HttpServletRequest request){
		if(((TUser)request.getSession().getAttribute("user")).getRoleId()==1){//管理员
			TCom com = new TCom();
			com.setUserStatus("0");
			com.setComType("0");
			com.setAlternateField2("0");
			Page page = new Page();
			List<TCom> comList = comservice.selectPageList(page,com);
			request.setAttribute("comList", comList);
			Calendar cal = Calendar.getInstance();
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM");
			//本月新增用户数量
			int usercount = userservice.selectUserCount();
			request.setAttribute("usercount", usercount);
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
			//本月招标数量
			int procount = proservice.selectProCount();
			request.setAttribute("procount", procount);
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
			//本月成交金额
			long concount = contractservice.selectConCount();
			request.setAttribute("concount", concount);
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
			//本月参与竞价用户数
			int offercount = offerservice.selectOfferCount();
			request.setAttribute("offercount", offercount);
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
			return "/project/main";
		}else if(((TUser)request.getSession().getAttribute("user")).getRoleId()==2){//审批人员
			return "/project/qita";
		}else if(((TUser)request.getSession().getAttribute("user")).getRoleId()==3){//供应商
			return "/project/qita";
		}else if(((TUser)request.getSession().getAttribute("user")).getRoleId()==4){//业务经理
			return "/project/qita";
		}
		return "";
	}
	

}
