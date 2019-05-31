package com.guochen.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.guochen.dao.TRemindMapper;
import com.guochen.dao.TUserMapper;
import com.guochen.model.TBuScope;
import com.guochen.model.TCom;
import com.guochen.model.TContract;
import com.guochen.model.TOffer;
import com.guochen.model.TProject;
import com.guochen.model.TRemind;
import com.guochen.model.TUser;
import com.guochen.page.Page;
import com.guochen.service.ComService;
import com.guochen.service.ContractService;
import com.guochen.service.OfferService;
import com.guochen.service.ProjectService;
import com.guochen.service.ScopeService;
import com.guochen.service.UserService;
import com.guochen.utils.CommonUtils;
import com.guochen.utils.Md5Util;
import com.guochen.utils.ZTreePojo;

@Controller
@RequestMapping("/com")
public class ComController {
	private static Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	private ComService comservice;
	@Autowired
	private TUserMapper tusermapper;
	@Autowired
	private TRemindMapper tremindmapper;
	@Autowired
	private ProjectService projectservice;
	@Autowired
	private OfferService offerservice;
	@Autowired
	private ContractService contractservice;
	@Autowired
	private UserService userservice;
	@Autowired
	private ScopeService scopeservice;
	
	
	/**
	 * 备选供应商
	 * @param request
	 * @param response
	 * @param page
	 * @param com
	 * @return
	 */
	@RequestMapping("/bakList")
	public String bakList(HttpServletRequest request, HttpServletResponse response,Page page,TCom com){
		com.setComType("0");
		com.setUserStatus("0");
		List<TCom> comList = comservice.selectPageList(page,com);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			comList = comservice.selectPageList(page,com);
		}
		request.setAttribute("comList", comList);
		request.setAttribute("page", page);
		return "/com/bakList";
	}
	
	@RequestMapping("/getBakList")
	public String getBakList(HttpServletRequest request, HttpServletResponse response,Page page,TCom com){
		com.setComType("0");
		com.setUserStatus("0");
		List<TCom> comList = comservice.selectPageList(page,com);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			comList = comservice.selectPageList(page,com);
		}
		request.setAttribute("comList", comList);
		request.setAttribute("page", page);
		return "/com/getBakList";
	}
	
	
	/**
	 * 合作供应商
	 * @param request
	 * @param response
	 * @param page
	 * @param com
	 * @return
	 */
	@RequestMapping("/cooList")
	public String cooList(HttpServletRequest request, HttpServletResponse response,Page page,TCom com){
		com.setComType("1");
		com.setUserStatus("0");
		List<TCom> comList = comservice.selectPageList(page,com);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			comList = comservice.selectPageList(page,com);
		}
		request.setAttribute("comList", comList);
		request.setAttribute("page", page);
		return "/com/cooList";
	}
	@RequestMapping("/getCooList")
	public String getCooList(HttpServletRequest request, HttpServletResponse response,Page page,TCom com){
		com.setComType("1");
		com.setUserStatus("0");
		List<TCom> comList = comservice.selectPageList(page,com);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			comList = comservice.selectPageList(page,com);
		}
		request.setAttribute("comList", comList);
		request.setAttribute("page", page);
		return "/com/getCooList";
	}
	
	
	/**
	 * 黑名单
	 * @param request
	 * @param response
	 * @param page
	 * @param com
	 * @return
	 */
	@RequestMapping("/blackList")
	public String blackList(HttpServletRequest request, HttpServletResponse response,Page page,TCom com){
		com.setUserStatus("1");
		List<TCom> comList = comservice.selectPageList(page,com);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			comList = comservice.selectPageList(page,com);
		}
		request.setAttribute("comList", comList);
		request.setAttribute("page", page);
		return "/com/blackList";
	}
	@RequestMapping("/getBlackList")
	public String getBlackList(HttpServletRequest request, HttpServletResponse response,Page page,TCom com){
		com.setUserStatus("1");
		if("4".equals(com.getComType())){
			com.setComType("");
		}
		List<TCom> comList = comservice.selectPageList(page,com);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			comList = comservice.selectPageList(page,com);
		}
		request.setAttribute("comList", comList);
		request.setAttribute("page", page);
		return "/com/getBlackList";
	}
	
	
	/**
	 * 待审核供应商
	 * @param request
	 * @param response
	 * @param page
	 * @param com
	 * @return
	 */
	@RequestMapping("/verifyList")
	public String verifyList(HttpServletRequest request, HttpServletResponse response,Page page,TCom com){
		com.setUserStatus("0");
		com.setComType("0");
		com.setAlternateField2("0");
		List<TCom> comList = comservice.selectPageList(page,com);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			comList = comservice.selectPageList(page,com);
		}
		request.setAttribute("comList", comList);
		request.setAttribute("page", page);
		return "/com/verifyList";
	}
	@RequestMapping("/getVerifyList")
	public String getVerifyList(HttpServletRequest request, HttpServletResponse response,Page page,TCom com){
		com.setUserStatus("0");
		com.setComType("0");
		com.setAlternateField2("0");
		List<TCom> comList = comservice.selectPageList(page,com);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			comList = comservice.selectPageList(page,com);
		}
		request.setAttribute("comList", comList);
		request.setAttribute("page", page);
		return "/com/getVerifyList";
	}

	/**
	 * 删除供应商信息
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(String[] ids){
		Map<String, Object> map = new HashMap<String, Object>();
		if(ids!=null){
			for (String id:ids) {
				TUser user = tusermapper.selectByComId(Integer.parseInt(id));
				tusermapper.deleteByPrimaryKey(user.getId());
				comservice.deleteByPrimaryKey(Integer.parseInt(id));
			}
		}
		map.put("code", 1);
		return map;
	}

	/**
	 * 拉黑
	 * @param ids
	 * @return
	 */
	@RequestMapping("/black")
	@ResponseBody
	public Map<String, Object> black(HttpServletRequest request,String[] ids,String forbidReason){
		TUser user = (TUser) request.getSession().getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		if(user==null){
			map.put("code", "操作失败，请重新登录");
			return map;
		}
		if(ids!=null){
			for (String id:ids) {
				TUser u = tusermapper.selectByComId(Integer.parseInt(id));
				if(u!=null){
					u.setForbidReason(forbidReason);
					u.setForbidTime(CommonUtils.date2Str(new Date()));
					u.setForbidUser(user.getLoginName());
					u.setUserStatus("1");
					comservice.blackByTUser(u);
				}
			}
		}
		map.put("code", 1);
		return map;
	}

	/**
	 * 撤销拉黑
	 * @param ids
	 * @return
	 */
	@RequestMapping("/white")
	@ResponseBody
	public Map<String, Object> white(String[] ids){
		Map<String, Object> map = new HashMap<String, Object>();
		if(ids!=null){
			for (String id:ids) {
				TUser u = tusermapper.selectByComId(Integer.parseInt(id));
				if(u!=null){
					u.setForbidReason("");
					u.setForbidTime("");
					u.setForbidUser("");
					u.setUserStatus("0");
					comservice.blackByTUser(u);
				}
			}
		}
		map.put("code", 1);
		return map;
	}
	
	/**
	 * 供应商审核
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/comVerify")
	public String comVerify(HttpServletRequest request, HttpServletResponse response,Integer id){
		TCom com = this.comservice.selectByPrimeryKey(id);
		String filesstr = com.getFiles();
		if(filesstr!=null&&filesstr.length()!=0){
			request.setAttribute("fileList",filesstr.split("\\|"));
		}
		request.setAttribute("com", com);
		return "/com/comVerify";
	}
	/**
	 * 供应商审核
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/comInfo")
	public String comInfo(HttpServletRequest request, HttpServletResponse response,Integer id){
		TCom com = this.comservice.selectByPrimeryKey(id);
		String filesstr = com.getFiles();
		if(filesstr!=null&&filesstr.length()!=0){
			request.setAttribute("fileList",filesstr.split("\\|"));
		}
		request.setAttribute("com", com);
		return "/com/comInfo";
	}
	
	
	/**
	 * 执行供应商信息审核
	 * @param request
	 * @param response
	 * @param id
	 * @param comType
	 * @param unpassReason
	 * @return
	 */
	@RequestMapping("/doVerifyCom")
	@ResponseBody
	public Map<String, Object> doVerifyCom(HttpServletRequest request, HttpServletResponse response,Integer id,String comType,String unpassReason){
		TCom com = this.comservice.selectByPrimeryKey(id);
		TRemind remind = new TRemind();
		//1-通过2-不通过
		if(comType.equals("2")){
			com.setAlternateField2("3");//审核未通过
			com.setComType("0");
			remind.setRemindCon("您的合作申请未通过审核，请重新申请。");
		}else if(comType.equals("1")){
			com.setAlternateField2("");//审核通过
			com.setComType(comType);
			remind.setRemindCon("恭喜您，您的合作申请已通过审核");
		}
		remind.setIsread("0");
		remind.setUserId(userservice.selectByComId(com.getId()).getId());
		tremindmapper.insert(remind);
		com.setUnpassReason(unpassReason);
		comservice.updateTcom(com);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1);
		return map;
	}
	
	
	/**
	 * 供应商申请合作
	 * @param request
	 * @param response
	 * @param com
	 * @return
	 */
	@RequestMapping("/newCom")
	public String newCom(HttpServletRequest request, HttpServletResponse response,TCom com){
		TUser user = (TUser) request.getSession().getAttribute("user");
		com = comservice.selectByPrimeryKey(user.getComId());
		if("0".equals(com.getComType())){//如果是备选供应商
			if("0".equals(com.getAlternateField2())){//已结提交审核
				request.setAttribute("acc", "2");
			}else if("3".equals(com.getAlternateField2())){//未通过
				request.setAttribute("acc", "3");
			}else{
				request.setAttribute("acc", "1");
			}
		}else{
			request.setAttribute("acc", "0");
		}
		request.setAttribute("com",com);
		//TODO
		
		//加载经营范围
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
		return "/com/newCom";
	}
	
	
	/**
	 * 文件上传
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/fileUpload2")
	@ResponseBody
    public HashMap  fileUpload2(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = file.getFileItem().getName();
		String fileType = fileName.substring(fileName.indexOf("."),fileName.length());
		String newFile = fileName+createcode();
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("filename", new String(newFile.getBytes("ISO-8859-1"),"UTF-8"));
		File realfile=new File(request.getRealPath("/")+"uploadFile/com/"+new String(newFile.getBytes("ISO-8859-1"),"UTF-8"));
		file.transferTo(realfile);
        return map; 
    }
	
	@RequestMapping("/fileUpload4OfferSheet")
	@ResponseBody
    public HashMap  fileUpload4OfferSheet(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String fileName = file.getFileItem().getName();
		String fileType = fileName.substring(fileName.indexOf("."),fileName.length());
		String newFile = fileName+createcode();
		File realfile=new File(request.getRealPath("/")+"uploadFile/offer/"+new String(newFile.getBytes("ISO-8859-1"),"UTF-8"));
		file.transferTo(realfile);
		HashMap map = new HashMap<String, String>();
		map.put("filename", new String(newFile.getBytes("ISO-8859-1"),"UTF-8"));
        return map; 
    }
	@RequestMapping("/fileUpload4json")
	@ResponseBody
	public String  fileUpload4json(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException,IOException {
		//转换成多部分request
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
		Map<String, MultipartFile> test=multiRequest.getFileMap();
		Map<String, String[]> map = multiRequest.getParameterMap();
		JSONObject filename = new JSONObject();
		//取得request中的所有文件名
		Iterator<String> iter = multiRequest.getFileNames();
		while(iter.hasNext()){
			//记录上传过程起始时的时间，用来计算上传时间
			int pre = (int) System.currentTimeMillis();
			//取得上传文件
			MultipartFile file = multiRequest.getFile(iter.next());
			if(file != null){
				if(file.getSize()>10485760){
					filename.element("code", 1);
					filename.element("msg", "文件大小不能超过10MB");
					return filename.toString();
				}
				//取得当前上传文件的文件名称
				String myFileName = file.getOriginalFilename();
				myFileName = new String(myFileName.getBytes("ISO-8859-1"),"UTF-8");
				//如果名称不为“”,说明该文件存在，否则说明该文件不存在
				if(myFileName.trim() !=""){
					System.out.println(myFileName);
					//重命名上传后的文件名
					String fileName = myFileName+createcode();
					filename.element("filename", fileName);
					filename.element("myFileName", myFileName);
					//定义上传路径
//                    String path = "H:/" + fileName;
					String path = request.getSession().getServletContext().getRealPath("/")+"uploadFile/com/" + fileName;
					File localFile = new File(path);
					try {
						file.transferTo(localFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
					filename.element("code", 0);
					filename.element("msg", "上传成功");
				}
			}
		}
		
		return filename.toString();
	}
	@RequestMapping("/fileUpload2json")
	@ResponseBody
	public String  fileUpload2json(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException,IOException {
		//判断 request 是否有文件上传,即多部分请求
        //转换成多部分request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
        JSONObject filename = new JSONObject();
        //取得request中的所有文件名
        Iterator<String> iter = multiRequest.getFileNames();
        while(iter.hasNext()){
            //记录上传过程起始时的时间，用来计算上传时间
            int pre = (int) System.currentTimeMillis();
            //取得上传文件
            MultipartFile file = multiRequest.getFile(iter.next());
            if(file != null){
            	if(file.getSize()>10485760){
            		filename.element("code", 1);
            		filename.element("msg", "文件大小不能超过10MB");
            		return filename.toString();
            	}
                //取得当前上传文件的文件名称
                String myFileName = file.getOriginalFilename();
                //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                if(myFileName.trim() !=""){
                    System.out.println(myFileName);
                    //重命名上传后的文件名
                    String fileName = myFileName+createcode();
                    filename.element("filename", fileName);
                    filename.element("myFileName", myFileName);
                    //定义上传路径
//                    String path = "H:/" + fileName;
                    String path = request.getSession().getServletContext().getRealPath("/")+"uploadFile/offer/" + fileName;
                    File localFile = new File(path);
                    try {
                        file.transferTo(localFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    filename.element("code", 0);
                    filename.element("msg", "上传成功");
                }
            }
        }
        
    return filename.toString();
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
	
	/**
	 * 保存供应商申请信息
	 * @param com
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public int save(HttpServletRequest request,TCom com){
		TUser user = (TUser) request.getSession().getAttribute("user");
		if(user!=null){
			com.setId(user.getComId());
			com.setComType("0");
			com.setAlternateField2("0");
			if(com.getFiles()!=null&&com.getFiles().length()!=0){
				com.setFiles(com.getFiles().substring(0,com.getFiles().length()-1));
			}
			comservice.updateTcom(com);
			user.setComId(com.getId());
			userservice.updateByPrimaryKey(user);
			request.getSession().setAttribute("user", user);
			return 1;
		}else{
			return 0;
		}
		
	}
	
	/**
	 * 加载供应商可参与项目
	 * @param request
	 * @param response
	 * @param page
	 * @param project
	 * @return
	 */
	@RequestMapping("/accProjects")
	public String accProjects(HttpServletRequest request, HttpServletResponse response,Page page,TProject project){
		TUser user = (TUser) request.getSession().getAttribute("user");
		List<TProject> projectList = projectservice.selectAccProjectPageList(page,user.getComId(),project.getProName());
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			projectList = projectservice.selectAccProjectPageList(page,user.getComId(),project.getProName());
		}
		request.setAttribute("projectList", projectList);
		request.setAttribute("page", page);
		return "/com/accProjects";
	}
	@RequestMapping("/getAccProjects")
	public String getAccProjects(HttpServletRequest request, HttpServletResponse response,Page page,TProject project){
		TUser user = (TUser) request.getSession().getAttribute("user");
		List<TProject> projectList = projectservice.selectAccProjectPageList(page,user.getComId(),project.getProName());
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			projectList = projectservice.selectAccProjectPageList(page,user.getComId(),project.getProName());
		}
		request.setAttribute("projectList", projectList);
		request.setAttribute("page", page);
		return "/com/getAccProjects";
	}

	
	/**
	 * 加载供应商已报价项目
	 * @param request
	 * @param response
	 * @param page
	 * @param project
	 * @return
	 */
	@RequestMapping("/offeredProjects")
	public String offeredProjects(HttpServletRequest request, HttpServletResponse response,Page page,TOffer offer){
		TUser user = (TUser) request.getSession().getAttribute("user");
		if(user!=null){
			offer.setUserId(user.getComId());
		}
		List<TOffer> offerList = offerservice.selectPageList(page,offer);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			offerList = offerservice.selectPageList(page,offer);
		}
		request.setAttribute("offerList", offerList);
		request.setAttribute("page", page);
		return "/com/offeredProjects";
	}
	@RequestMapping("/getOfferedProjects")
	public String getOfferedProjects(HttpServletRequest request, HttpServletResponse response,Page page,TOffer offer){
		TUser user = (TUser) request.getSession().getAttribute("user");
		if(user!=null){
			offer.setUserId(user.getComId());
		}
		if("0".equals(offer.getProStatus())){
			offer.setProStatus("");
		}
		List<TOffer> offerList = offerservice.selectPageList(page,offer);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			offerList = offerservice.selectPageList(page,offer);
		}
		request.setAttribute("offerList", offerList);
		request.setAttribute("page", page);
		return "/com/getOfferedProjects";
	}
	
	
	/**
	 * 加载供应商已中标项目
	 * @param request
	 * @param response
	 * @param page
	 * @param project
	 * @return
	 */
	@RequestMapping("/targetedProjects")
	public String offeredProjects(HttpServletRequest request, HttpServletResponse response,Page page,TContract contract){
		TUser user = (TUser) request.getSession().getAttribute("user");
		if(user!=null){
			contract.setUserId(user.getComId());
		}
		List<TContract> contractList = contractservice.selectPageList(page,contract);
		List<TContract> contractList1 = new ArrayList<TContract>();
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			contractList = contractservice.selectPageList(page,contract);
			
		}
		for(TContract contract1:contractList){
			contract1.setOffer(offerservice.selectByPrimeryKey(Integer.parseInt(contract1.getAlternateField1())));
			String fileName = contract1.getFilePath();
			if(fileName!=null&&fileName.length()>0){
				String pdfName = fileName.substring(0,fileName.indexOf("."))+".pdf";
				contract1.setFilePath(pdfName+"#"+fileName);
			}
			contractList1.add(contract1);
		}
		request.setAttribute("contractList", contractList1);
		request.setAttribute("page", page);
		return "/com/targetedProjects";
	}
	@RequestMapping("/getTargetedProjects")
	public String getOfferedProjects(HttpServletRequest request, HttpServletResponse response,Page page,TContract contract){
		TUser user = (TUser) request.getSession().getAttribute("user");
		if(user!=null){
			contract.setUserId(user.getComId());
		}
		if("0".equals(contract.getProStatus())){
			contract.setProStatus("");
		}
		List<TContract> contractList = contractservice.selectPageList(page,contract);
		List<TContract> contractList1 = new ArrayList<TContract>();
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			contractList = contractservice.selectPageList(page,contract);
		}
		for(TContract contract1:contractList){
			contract1.setOffer(offerservice.selectByPrimeryKey(Integer.parseInt(contract1.getAlternateField1())));
			String fileName = contract1.getFilePath();
			if(fileName!=null&&fileName.length()>0){
				String pdfName = fileName.substring(0,fileName.indexOf("."))+".pdf";
				contract1.setFilePath(pdfName+"#"+fileName);
			}
			contractList1.add(contract1);
		}
		request.setAttribute("contractList", contractList1);
		request.setAttribute("page", page);
		return "/com/getTargetedProjects";
	}
	
	
	@RequestMapping("/preOffer")
	public String preOffer(HttpServletRequest request, HttpServletResponse response,Page page,TOffer offer,String type){
		TUser user = (TUser) request.getSession().getAttribute("user");
		if(offer.getProId()!=null){
			TProject project = projectservice.selectByPrimeryKey(offer.getProId());
			if(project!=null){
				if(project.getFileId()!=null && !"".equals(project.getFileId())){
					String fileIds [] = project.getFileId().split("\\|");
					List<String> fileList = new ArrayList<String>();
					for(String fid:fileIds){
						fileList.add(fid);
					}
					request.setAttribute("fileList", fileList);
				}
				request.setAttribute("project", project);
			}
		}
		request.setAttribute("type", type);
		return "/com/preOffer";
	}
	
	@RequestMapping("/saveOffer")
	@ResponseBody
	public String saveOffer(HttpServletRequest request, HttpServletResponse response,TOffer offer){
		TUser user = (TUser) request.getSession().getAttribute("user");
		if(user==null || user.getComId()==null || user.getComId()==0){
			return "0";
		}
		double doubleOfAcceOffer = Double.parseDouble(offer.getAcceOffer())*Double.parseDouble(offer.getAcceOfferUnion());
		offer.setAcceOffer(doubleOfAcceOffer+"");
		double dobleOfFreOffer = Double.parseDouble(offer.getFreOffer())*Double.parseDouble(offer.getFreOfferUnion());
		offer.setFreOffer(dobleOfFreOffer+"");
		double dobleOfCashOffer = Double.parseDouble(offer.getCashOffer())*Double.parseDouble(offer.getCashOfferUnion());
		offer.setCashOffer(dobleOfCashOffer+"");
		offer.setCreateTime(CommonUtils.date2Str(new Date()));
		offer.setUserId(user.getComId());
		offer.setStatus("0");
		TCom com = (TCom) request.getSession().getAttribute("com");
		TOffer old = offerservice.selectByProIdAndUserId(offer.getProId(), com.getId());
		if(old == null){
			offerservice.save(offer);
			//投标数加1
			projectservice.addofferNum(offer.getProId());
		}else{
			offer.setId(old.getId());
			offerservice.updateTOffer(offer);
		}
		
		return "1";
	}
	@RequestMapping("/mgetOfferedProjects")
	@ResponseBody
	public List<HashMap<String,String>> mgetOfferedProjects(int userid,HttpServletRequest request, HttpServletResponse response,int pageNum){
		TOffer offer = new TOffer();
		List<HashMap<String,String>> resultlist = new ArrayList<HashMap<String,String>>();
		if(userid!=0){
			TCom com = comservice.selectByUserId(userid);
			if(com==null){
				return resultlist;
			}
			offer.setUserId(comservice.selectByUserId(userid).getId());
		}
		Page page = new Page();
		page.setPageNum(pageNum);
		List<TOffer> offerList = offerservice.selectPageList(page,offer);
		/*if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			offerList = offerservice.selectPageList(page,offer);
		}*/
		//组手机端数据
		for(int i=0;i<offerList.size();i++){
			HashMap<String,String> hs = new HashMap<String, String>();
			offer = offerList.get(i);
			TProject project = offer.getProject();
			if(project.getProStatus().equals("2")){//已结束的项目不显示在已报价项目中
				continue;
			}
			hs.put("proname", project.getProName());//项目名称
			hs.put("prostatus", project.getProStatus());//项目状态
			hs.put("prostarttime", project.getProStarttime());//项目开始时间
			hs.put("proendtime", project.getProEndtime());//项目截至时间
			hs.put("procode", project.getProCode());//项目编号
			hs.put("conphone", project.getConPhone());//联系电话
			hs.put("conname", project.getConName());//联系人
			hs.put("acceoffer", offer.getAcceOffer());//承兑报价
			hs.put("cashoffer", offer.getCashOffer());//现金报价
			hs.put("freoffer", offer.getFreOffer());//运费报价
			hs.put("remarks", offer.getRemarks());//报价备注
			hs.put("comname", offer.getCom().getComName());//供应商名称
			resultlist.add(hs);
		}
		return resultlist;
	}
	@RequestMapping("/mgetTargetedProjects")
	@ResponseBody
	public List<HashMap<String,String>> mgetTargetedProjects(int userid,int pageNum,HttpServletRequest request, HttpServletResponse response){
		TContract contract = new TContract();
		if(userid!=0){
			TUser user = userservice.selectByPrimeryKey(userid);
			contract.setUserId(user.getComId());
		}
		Page page = new Page();
		page.setPageNum(pageNum);
		List<TContract> contractList = contractservice.selectPageList(page,contract);
		/*if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			contractList = contractservice.selectPageList(page,contract);
		}*/
		List<HashMap<String,String>> resultlist = new ArrayList<HashMap<String,String>>();
		for(int i=0;i<contractList.size();i++){
			HashMap<String,String> hs = new HashMap<String, String>();
			contract = contractList.get(i);
			TOffer offer = offerservice.selectByPrimeryKey(Integer.parseInt(contract.getAlternateField1()));
			TProject project = offer.getProject();
			hs.put("proname", project.getProName());
			hs.put("prostatus", project.getProStatus());//项目状态
			hs.put("procode", project.getProCode());//项目编号
			hs.put("conphone", project.getConPhone());//联系电话
			hs.put("conname", project.getConName());//联系人
			hs.put("proendtime", project.getProEndtime());//结束时间
			hs.put("remarks", offer.getRemarks());//报价备注
			hs.put("acceoffer", offer.getAcceOffer());//承兑报价
			hs.put("cashoffer", offer.getCashOffer());//现金报价
			hs.put("freoffer", offer.getFreOffer());//运费报价
			hs.put("remarks", offer.getRemarks());//报价备注
			hs.put("comname", offer.getCom().getComName());//供应商名称
			resultlist.add(hs);
		}
		return resultlist;
	}

	@RequestMapping("/resetPwd")
	@ResponseBody
	public Map<String, Object> resetPwd(int id){
		Map<String, Object> map = new HashMap<String, Object>();
		TUser user = userservice.selectByComId(id);
		if(user != null){
			user.setLoginPwd(Md5Util.getMd5("000000"));
			userservice.updateByPrimaryKey(user);
		}
		map.put("code", 1);
		return map;
	}
	@RequestMapping("/mselectComById")
	@ResponseBody
	public Map<String,Object> mselectByPrimeryKey(int id){
		Map<String, Object> map = new HashMap<String, Object>();
		TCom com = comservice.selectByPrimeryKey(id);
		if(com==null){
			return map;
		}
		map.put("comname", com.getConName());//企业名称
		map.put("phone", com.getConPhone());//联系电话
		map.put("addr", com.getComAddr());//企业地址
		map.put("bankacct", com.getBankAccount());//开户行
		map.put("acct", com.getAccount());//账号
		map.put("txno", com.getTaxreceNo());//税号
		map.put("blpath", "uploadFile/com/"+com.getBusinessLicensePath());//营业执照
		map.put("tpath", "uploadFile/com/"+com.getTaxrecePath());//税务登记证
		map.put("orpath", "uploadFile/com/"+com.getOrcodePath());//组织机构代码证
		//http://1.180.135.18:8009/hdxy/uploadFile/com/%E9%A2%86%E8%B4%A7%E5%87%AD%E8%AF%81.jpg489123
		return map;
	}
	@RequestMapping("/exp")
	@ResponseBody
	public String exp(HttpServletRequest request, HttpServletResponse response){
		TCom com = new TCom();
		com.setComType("1");
		List<TCom> list = comservice.selectList(com);
		HSSFWorkbook wb = comservice.exp(list);
		try {
			OutputStream output=response.getOutputStream();  
			response.reset();  
			response.setContentType("application/msexcel");          
			response.setHeader("Content-disposition", "attachment; filename="+URLEncoder.encode("合作供应商.xls", "UTF-8"));  
			wb.write(output);  
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return "";
	}
	
}
