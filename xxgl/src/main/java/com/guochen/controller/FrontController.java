package com.guochen.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guochen.dao.TUserMapper;
import com.guochen.model.TCom;
import com.guochen.model.TContract;
import com.guochen.model.TFile;
import com.guochen.model.TNotice;
import com.guochen.model.TProject;
import com.guochen.model.TUser;
import com.guochen.page.Page;
import com.guochen.service.ComService;
import com.guochen.service.ContractService;
import com.guochen.service.FileService;
import com.guochen.service.FrontService;
import com.guochen.service.NoticeService;
import com.guochen.service.ProjectService;
import com.guochen.utils.HttpRequest;
import com.guochen.utils.Md5Util;
import com.shcm.bean.SendResultBean;

@Controller
@RequestMapping("/front")
public class FrontController {
	@Value("${mobile.ac}") 
	private String ac;
	@Value("${mobile.authkey}") 
	private String authkey;
	@Value("${mobile.cgid}") 
	private int cgid;
	private static Logger log = Logger.getLogger(FrontController.class);
	@Autowired
	private ComService comservice;
	@Autowired
	private FrontService frontservice;
	@Autowired
	private ProjectService projectservice;
	@Autowired
	private ContractService contractservice;
	@Autowired
	private TUserMapper tusermapper;
	@Autowired
	private FileService fileService;
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response){
		//查询招标公告list
		List<TProject> list0 = frontservice.selectTenderNotice();
		//查询中标公告list
		//List<TContract> list1 = frontservice.selectBidNotice();
		TNotice notice = new TNotice();
		notice.setNoticeStatus("1");
		List<TNotice> list1 = noticeService.selectList(notice);
		request.setAttribute("list0", list0);
		request.setAttribute("list1", list1);
		return "/front/index";
	}
	public static void main(String[] args) {
		List list = new ArrayList();
		list.add(1);
		list.subList(0, 10);
	}
	@RequestMapping("/buyList")
	public String buyList(HttpServletRequest request, HttpServletResponse response){
		//查询招标公告list
		List<TProject> list0 = frontservice.selectTenderNotice();
		//查询中标公告list
		//List<TContract> list1 = frontservice.selectBidNotice();
		TNotice notice = new TNotice();
		notice.setNoticeStatus("1");
		List<TNotice> list1 = noticeService.selectList(notice);
		if(list0.size()>9){
			list0 = list0.subList(0,9);
		}
		if(list1.size()>9){
			list1 = list1.subList(0,9);
		}
		List list2=list1;
		if(list2.size()>5){
			list2=list2.subList(0, 5);
		}
		request.setAttribute("list0", list0);
		request.setAttribute("list1", list1);
		request.setAttribute("list2", list2);
		return "/front/buyList";
	}
	@RequestMapping("/download")
	public String download(HttpServletRequest request, HttpServletResponse response,
			Page page, TFile file){
		//查询招标公告list
		List<TProject> list0 = frontservice.selectTenderNotice();
		//查询中标公告list
		//List<TContract> list1 = frontservice.selectBidNotice();
		TNotice notice = new TNotice();
		notice.setNoticeStatus("1");
		List<TNotice> list1 = noticeService.selectList(notice);
		if(list0.size()>9){
			list0 = list0.subList(0,9);
		}
		if(list1.size()>9){
			list1 = list1.subList(0,9);
		}
		List list2=list1;
		if(list2.size()>5){
			list2=list2.subList(0, 5);
		}
		request.setAttribute("list0", list0);
		request.setAttribute("list1", list1);
		request.setAttribute("list2", list2);
		file.setAlternateField1("1");
		List<TFile> fileList = fileService.selectPageList(page, file);
		request.setAttribute("fileList", fileList);
		return "/front/download";
	}
	@RequestMapping("/getFileList")
	public String getFileList(HttpServletRequest request, HttpServletResponse response,
			Page page, TFile file){
		file.setAlternateField1("1");
		List<TFile> fileList = fileService.selectPageList(page, file);
		request.setAttribute("fileList", fileList);
		return "/front/fileList";
	}
	@RequestMapping("/contactUs")
	public String contactUs(HttpServletRequest request, HttpServletResponse response){
		List<TContract> list1 = frontservice.selectBidNotice();
		if(list1.size()>5){
			list1 = list1.subList(0,5);
		}
		request.setAttribute("list1", list1);
		//查询招标公告list
		List<TProject> list0 = frontservice.selectTenderNotice();
		//查询中标公告list
		//List<TContract> list1 = frontservice.selectBidNotice();
		TNotice notice = new TNotice();
		notice.setNoticeStatus("1");
		List<TNotice> list3 = noticeService.selectList(notice);
		if(list0.size()>9){
			list0 = list0.subList(0,9);
		}
		if(list3.size()>9){
			list3 = list3.subList(0,9);
		}
		List list2=list3;
		if(list2.size()>5){
			list2=list2.subList(0, 5);
		}
		request.setAttribute("list0", list0);
		request.setAttribute("list2", list2);
		return "/front/contactUs";
	}
	@RequestMapping("/reg")
	public String reg(HttpServletRequest request, HttpServletResponse response){
		List<TContract> list2 = frontservice.selectBidNotice();
		if(list2.size()>5){
			list2=list2.subList(0, 5);
		}
		request.setAttribute("list2", list2);
		return "/front/reg";
	}
	@RequestMapping("/reguser")
	@ResponseBody
	public String reguser(HttpServletRequest request, HttpServletResponse response){
		TCom com = new TCom();
		com.setComName(request.getParameter("comName"));
		com.setConPhone(request.getParameter("tel"));
		com.setAlternateField1(request.getParameter("email"));
		com.setComType("0");
		comservice.save(com);
		TUser user = new TUser();
		user.setLoginName(request.getParameter("loginName"));
		user.setLoginPwd(Md5Util.getMd5(request.getParameter("password")));
		user.setUserStatus("0");
		user.setRoleId(3);
		user.setComId(com.getId());
		user.setAlternateField1(request.getParameter("tel"));
		tusermapper.insert(user);
		
		return "注册成功";
	}
	@RequestMapping("/valiloginName")
	@ResponseBody
	public Map<String , String> valiloginName(HttpServletRequest request, HttpServletResponse response){
		Map<String , String> map = new HashMap<String, String>();
		if(request.getParameter("name").equals("loginName")){
			TUser user = tusermapper.selectByLoginname(request.getParameter("param"));
			if(user!=null){
				map.put("info", "用户名已被注册");
				map.put("status", "n");  
			}else{
				map.put("info", "通过信息验证！");
				map.put("status", "y");  
				
			}
		}else if(request.getParameter("name").equals("tel")){
			TUser user = tusermapper.selectByPhone(request.getParameter("param"));
			if(user!=null){
				map.put("info", "该手机号已被注册");
				map.put("status", "n");  
			}else{
				map.put("info", "通过信息验证！");
				map.put("status", "y");  
				
			}
		}
		return map;
	}
	@RequestMapping("/sendcode")
	@ResponseBody
	public String sendcode(HttpServletRequest request, HttpServletResponse response){
		String[] phone = new String[]{request.getParameter("tel")};
		TUser user = tusermapper.selectByPhone(phone[0]);
		if(user!=null){
			return "该手机号已被注册";
		}
		String retStr = "";
		 retStr = createcode();
		 String remindCon = "您的验证码是"+retStr+",在5分钟内有效。如非本人操作请忽略本短信。";
		 List<SendResultBean> listItem = HttpRequest.send(ac,authkey,cgid,phone, remindCon);
		 if(listItem != null)
			{
				for(SendResultBean t:listItem)
				{
					if(t.getResult() < 1)
					{	
						return "发送验证码失败";
					}else{
						return retStr;
					}
					
				}
			}
		 return retStr;
	}
	private String createcode() {
		String retStr;
		String strTable = true ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		 int len = strTable.length();
		 boolean bDone = true;
		 do {
		  retStr = "";
		  int count = 0;
		  for (int i = 0; i < 6; i++) {
		  double dblR = Math.random() * len;
		  int intR = (int) Math.floor(dblR);
		  char c = strTable.charAt(intR);
		  if (('0' <= c) && (c <= '9')) {
		   count++;
		  }
		  retStr += strTable.charAt(intR);
		  }
		  if (count >= 2) {
		  bDone = false;
		  }
		 } while (bDone);
		return retStr;
	}
	@RequestMapping("/buyDetail")
	public String buyDetail(HttpServletRequest request, HttpServletResponse response,int id,String type){
//		List<TContract> list1 = frontservice.selectBidNotice();
		TNotice search = new TNotice();
		search.setNoticeStatus("1");
		List<TNotice> list1 = noticeService.selectList(search);
		if(list1.size()>5){
			list1 = list1.subList(0,5);
		}
		request.setAttribute("list1", list1);
		if(type!=null&&type.equals("tender")){
			TProject pro = projectservice.selectByPrimeryKey(id);
			request.setAttribute("pro", pro);
		}else if(type!=null&&type.equals("bid")){
//			TContract con = contractservice.selectByPrimeryKey(id);
			TNotice notice = noticeService.selectByPrimeryKey(id);
			if(notice!=null && "1".equals(notice.getNoticeStatus())){
				request.setAttribute("pro", notice);
			}
		}
		request.setAttribute("type",type);
		return "/front/buyDetail";
	}
	@RequestMapping("/zbtl")
	public String zbtl(){
		return "/front/zb";
	}
}
