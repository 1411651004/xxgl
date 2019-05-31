package com.guochen.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.guochen.model.TCom;
import com.guochen.model.TFile;
import com.guochen.model.TMsg;
import com.guochen.model.TQuickMsg;
import com.guochen.model.TRole;
import com.guochen.model.TUser;
import com.guochen.page.Page;
import com.guochen.service.FileService;
import com.guochen.service.MsgService;
import com.guochen.utils.HttpRequest;
import com.guochen.utils.Jpush;
import com.shcm.bean.SendResultBean;

@Controller
@RequestMapping("/msg")
public class MsgController {
	private static Logger log = Logger.getLogger(MsgController.class);
	@Value("${mobile.ac}") 
	private String ac;
	@Value("${mobile.authkey}") 
	private String authkey;
	@Value("${mobile.cgid}") 
	private int cgid;
	@Value("${appKey}") 
	private String appKey;
	@Value("${masterSecret}") 
	private String masterSecret;
	
	@Autowired
	private MsgService msgservice;
	@Autowired
	private FileService fileservice;
	@RequestMapping("/msg")
	public String msg(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//查询快捷推送
		List<TQuickMsg> tquickmsg = msgservice.selectAll();
		List<TRole> trole = msgservice.selectAllRole();
		List<TUser> tuser = msgservice.selectAllUser();
		//组json
		JSONArray jsonarray = new JSONArray();
		//role部分
		for(int i=0;i<trole.size();i++){
			TRole role = trole.get(i);
			JSONObject jsonobject = new JSONObject();
			jsonobject.element("id",role.getId());
			jsonobject.element("pid",0);
			jsonobject.element("name",role.getRoleName());
			jsonobject.element("rid",-1);
			jsonarray.add(jsonobject);
		}
		//user部分
		for(int i=0;i<tuser.size();i++){
			TUser user = tuser.get(i);
			JSONObject jsonobject = new JSONObject();
			jsonobject.element("id", user.getId()+trole.size());
			jsonobject.element("pid",user.getRoleId());
			jsonobject.element("name",user.getLoginName());
			jsonobject.element("rid",user.getId());
			jsonobject.element("mobilephone", user.getAlternateField1());
			jsonarray.add(jsonobject);
		}
		request.setAttribute("treelist",jsonarray.toString());
		request.setAttribute("tquickmsg",tquickmsg);
		return "/msg/msg";
	}
	@RequestMapping(value = "/sendmobile",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String sendmobile(String remindCon,String ids,String phones){
		/*List<SendResultBean> listItem = HttpRequest.send(ac,authkey,cgid,phones.split(","), remindCon);
		String[] idarray = ids.split("\\|");
		if(listItem != null)
		{
			for(SendResultBean t:listItem)
			{
				if(t.getResult() < 1)
				{	
					for(int i=0;i<idarray.length;i++){
						TMsg tmsg = new TMsg();
						tmsg.setIsok("1");
						tmsg.setRemindCon(remindCon);
						tmsg.setUserId(Integer.parseInt(idarray[i]));
						msgservice.save(tmsg);
					}
					
					return "发送提交失败: " + t.getErrMsg();
				}else{
					for(int i=0;i<idarray.length;i++){
						TMsg tmsg = new TMsg();
						tmsg.setIsok("0");
						tmsg.setRemindCon(remindCon);
						tmsg.setUserId(Integer.parseInt(idarray[i]));
						msgservice.save(tmsg);
					}
					return "发送成功: 消息编号<" + t.getMsgId() + "> 总数<" + t.getTotal() + "> ";
				}
				
			}
		}*/
		return "发送成功";
	}
	@RequestMapping(value = "/appsend",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String appsend(String remindCon,String ids,String phones){
		String[] idarray = ids.split("\\|");
		for(String id:idarray){
			//Jpush.send(id, remindCon,appKey,masterSecret);
			TMsg tmsg = new TMsg();
			tmsg.setIsok("0");
			tmsg.setRemindCon(remindCon);
			tmsg.setUserId(Integer.parseInt(id));
			msgservice.save(tmsg);
		}
		return "推送成功";
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,TMsg msg){
		List<TMsg> msgList = msgservice.selectPageList(page, msg);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			msgList = msgservice.selectPageList(page, msg);
		}
		request.setAttribute("msgList", msgList);
		request.setAttribute("page", page);
		return "/msg/list";
	}
	@RequestMapping("/filelist")
	public String filelist(HttpServletRequest request, HttpServletResponse response,Page page){
		TUser user = (TUser)request.getSession().getAttribute("user");
		List list = fileservice.selectByUseridPage(page,user.getId());
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			list = fileservice.selectByUseridPage(page,user.getId());
		}
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		return "/myfile/list";
	}
	@RequestMapping("/getfilelist")
	public String getfilelist(HttpServletRequest request, HttpServletResponse response,Page page){
		TUser user = (TUser)request.getSession().getAttribute("user");
		List list = fileservice.selectByUseridPage(page,user.getId());
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			list = fileservice.selectByUseridPage(page,user.getId());
		}
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		return "/myfile/getList";
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
	@RequestMapping("/fileUpload2")
	@ResponseBody
    public HashMap  fileUpload2(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = file.getFileItem().getName();
		String fileType = fileName.substring(fileName.indexOf("."),fileName.length());
		String newFile = fileName+createcode();
		File realfile=new File(request.getRealPath("/")+"uploadFile/project/"+new String(newFile.getBytes("ISO-8859-1"),"UTF-8"));
		file.transferTo(realfile);
		HashMap map = new HashMap<String, String>();
		map.put("filename", new String(newFile.getBytes("ISO-8859-1"),"UTF-8"));
        return map; 
    }
	@RequestMapping("/getList")
	public String getList(HttpServletRequest request, HttpServletResponse response,Page page,TMsg msg){
		List<TMsg> msgList = msgservice.selectPageList(page, msg);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			msgList = msgservice.selectPageList(page, msg);
		}
		
		request.setAttribute("msgList", msgList);
		request.setAttribute("page", page);
		return "/msg/getList";
	}
	@RequestMapping("/pushfile")
	@ResponseBody
	public String pushfile(HttpServletRequest request, HttpServletResponse response){
		String files = ""+request.getParameter("files");
		String ids = ""+request.getParameter("ids");
		String flag="pushfile";
		TFile filedata = new TFile();
		filedata.setAlternateField1(flag);
		filedata.setAlternateField2(ids);
		filedata.setFileName(files);
		filedata.setFilePath(files);
		filedata.setFileType(flag);
		fileservice.add(filedata);
		return "推送成功";
	}
	
	/**
	 * 根据用户输入加载候选名单列表
	 * @param request
	 * @param u_name
	 * @return
	 */
	@RequestMapping("/loadUserList")
	@ResponseBody
	public String loadUserList(HttpServletRequest request,String u_name){
		List<TUser> tuser = msgservice.selectAllUser();
		List<TRole> trole = msgservice.selectAllRole();
		JSONArray jsonarray = new JSONArray();
		JSONArray jsonarrayRole = new JSONArray();
		JSONArray jsonarrayUser = new JSONArray();
		//role部分，角色树
		for(int i=0;i<trole.size();i++){
			TRole role = trole.get(i);
			JSONObject jsonobject = new JSONObject();
			jsonobject.element("id",role.getId());
			jsonobject.element("pid",0);
			jsonobject.element("name",role.getRoleName());
			jsonobject.element("rid",-1);
			jsonarrayRole.add(jsonobject);
		}
		//user部分，账户树
		for(int i=0;i<tuser.size();i++){
			TUser user = tuser.get(i);
			if(u_name!=null && !"".equals(u_name.trim())){
				if(user.getLoginName().toLowerCase().contains(u_name.toLowerCase())){
					JSONObject jsonobject = new JSONObject();
					jsonobject.element("id", user.getId()+trole.size());
					jsonobject.element("pid",user.getRoleId());
					jsonobject.element("name",user.getLoginName());
					jsonobject.element("rid",user.getId());
					jsonobject.element("mobilephone", user.getAlternateField1());
					jsonarrayUser.add(jsonobject);
				}
			}else{
				JSONObject jsonobject = new JSONObject();
				jsonobject.element("id", user.getId()+trole.size());
				jsonobject.element("pid",user.getRoleId());
				jsonobject.element("name",user.getLoginName());
				jsonobject.element("rid",user.getId());
				jsonobject.element("mobilephone", user.getAlternateField1());
				jsonarrayUser.add(jsonobject);
			}
		}
		
		//判断角色子树有没有叶子节点，如果没有，删除该树，用户查询不加载无效目录
		for(int i=0;i<jsonarrayRole.size();i++){
			JSONObject jsonobjectI = (JSONObject) jsonarrayRole.get(i);
			boolean delFlag = true;
			for(int j=0;j<jsonarrayUser.size();j++){
				JSONObject jsonobjectJ = (JSONObject) jsonarrayUser.get(j);
				if(jsonobjectI.get("id").equals(jsonobjectJ.get("pid"))){
					delFlag = false;
				}
			}
			if(delFlag){
				jsonarrayRole.remove(jsonobjectI);
				i--;
			}
		}
		jsonarray.addAll(jsonarrayRole);
		jsonarray.addAll(jsonarrayUser);
		return jsonarray.toString();
	}
}
