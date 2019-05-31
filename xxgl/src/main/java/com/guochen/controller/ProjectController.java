package com.guochen.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.guochen.model.TBuyer;
import com.guochen.model.TCode;
import com.guochen.model.TCom;
import com.guochen.model.TConapp;
import com.guochen.model.TContract;
import com.guochen.model.TNode;
import com.guochen.model.TOffer;
import com.guochen.model.TProject;
import com.guochen.model.TTemplate;
import com.guochen.model.TUser;
import com.guochen.page.Page;
import com.guochen.service.BuyerService;
import com.guochen.service.ComService;
import com.guochen.service.ConAppService;
import com.guochen.service.ContractService;
import com.guochen.service.NodeService;
import com.guochen.service.OfferService;
import com.guochen.service.ProjectService;
import com.guochen.service.TemplateService;
import com.guochen.utils.CommonUtils;
import com.guochen.utils.quartz.QuartzManage;
import com.guochen.utils.quartz.job.EndPro;
import com.guochen.utils.quartz.job.StartPro;

@Controller
@RequestMapping("/project")
public class ProjectController {
	private static Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	private ProjectService projectservice;
	@Autowired
	private OfferService offerservice;	
	@Autowired
	private ComService comservice;
	@Autowired
	private TemplateService templateservice;
	@Autowired
	private ContractService contractservice;
	@Autowired
	private ConAppService conappservice;
	@Autowired
	private NodeService nodeservice;
	@Autowired
	private BuyerService buyerservice;
	
	
	@RequestMapping("/newProject")
	public String newProject(HttpServletRequest request, HttpServletResponse response,TProject project){
		if(project.getId()==null){
			project = new TProject();
			Date dt=new Date();
			SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
			int num = projectservice.selectCountByDate(matter1.format(dt)+"%", matter1.format(dt));
			matter1=new SimpleDateFormat("yyMMdd");
			String proCode="ZB"+matter1.format(dt)+(num+1);
			project.setProCode(proCode);
		}else{
			project = projectservice.selectByPrimeryKey(project.getId());
			TBuyer tbuyer = projectservice.selectBuyerById(project.getBuyerId());
			request.setAttribute("buyer", tbuyer);
		}
		List<TCode> listcode = projectservice.selectSeme();
		request.setAttribute("project",project);
		request.setAttribute("listcode", listcode);
		//加载甲方列表
		List<TBuyer> buyerList = buyerservice.listAll();
		request.setAttribute("buyerList", buyerList);
		
		return "/project/NewProject";
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,TProject project){
		List<TProject> projectList = projectservice.selectPageList(page,null);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			projectList = projectservice.selectPageList(page,null);
		}
		request.setAttribute("projectList", projectList);
		request.setAttribute("page", page);
		return "/project/list";
	}

	@RequestMapping("/getList")
	public String getList(HttpServletRequest request, HttpServletResponse response,Page page,TProject project){
		if("4".equals(project.getProStatus())){
			project.setProStatus("");
		}
		List<TProject> projectList = projectservice.selectPageList(page,project);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			projectList = projectservice.selectPageList(page,project);
		}
		request.setAttribute("projectList", projectList);
		request.setAttribute("page", page);
		return "/project/getList";
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
//                        String path = "H:/" + fileName;
                        String path = request.getSession().getServletContext().getRealPath("/")+"uploadFile/project/" + fileName;
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
                //记录上传该文件后的时间
                int finaltime = (int) System.currentTimeMillis();
                System.out.println(finaltime - pre);
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
	@RequestMapping("/save")
	@ResponseBody
	public String save(TProject project){
		project.setFileId(project.getFileId().substring(0,project.getFileId().length()-1));
		String starttime = project.getProStarttime();
		//获取当前时间两分钟之后的时间
		Date date = new Date();
		date.setMinutes(date.getMinutes()+2);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date starttimedate = null;
		Date endtimedate = null;
		try {
			starttimedate = df.parse(starttime);
			if(starttimedate.getTime()<=date.getTime()){
				return "开始时间必须在两分钟之后";
			}
			endtimedate = df.parse(project.getProEndtime());
			if(starttimedate.getTime()>endtimedate.getTime()){
				return "结束时间必须晚于开始时间";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//project.setFileId(project.getFileId().substring(0,project.getFileId().length()-1));
		String re = projectservice.save(project);
		SimpleDateFormat format1 = new SimpleDateFormat(
				"ss mm HH dd MM ? yyyy");
		
		try {
			QuartzManage.startJob(project.getId()+"" ,
					new StartPro(), format1.format(starttimedate));
			QuartzManage.startJob(project.getId()+"" ,
					new EndPro(), format1.format(endtimedate));
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		QuartzManage.startJob(project.getId() ,
//				new StopPro(), format1.format(endtimedate));
		
		return re;
	}

	@RequestMapping("/startProject")
	@ResponseBody
	public Map<String, Object> startProject(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		TProject project = projectservice.selectByPrimeryKey(id);
		project.setProStarttime(CommonUtils.date2Str(new Date()));
		project.setProStatus("1");
		projectservice.updateByPrimaryKey(project);
		try {
			QuartzManage.removeJob(project.getId()+StartPro.class.toString());
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("code", 1);
		return map;
	}
	
	@RequestMapping("/endProject")
	@ResponseBody
	public Map<String, Object> endProject(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		TProject project = projectservice.selectByPrimeryKey(id);
		project.setProStatus("2");
		projectservice.updateByPrimaryKey(project);
		try {
			QuartzManage.removeJob(project.getId()+EndPro.class.toString());
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("code", 1);
		return map;
	}

	
	@RequestMapping("/callBackProject")
	@ResponseBody
	public Map<String, Object> callBackProject(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		TProject project = projectservice.selectByPrimeryKey(id);
		project.setProStatus("0");
		
		projectservice.updateByPrimaryKey(project);
		map.put("code", 1);
		return map;
	}
	
	
	/**
	 * 加载待选择供应商列表
	 * @param request
	 * @param response
	 * @param page
	 * @param project
	 * @return
	 */
	@RequestMapping("/selectTargetCom")
	public String selectTargetCom(HttpServletRequest request, HttpServletResponse response,Page page,TProject project){
		List<TProject> projectList = projectservice.selectToSelectComPageList(page,project);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			projectList = projectservice.selectToSelectComPageList(page,project);
		}
		request.setAttribute("projectList", projectList);
		request.setAttribute("page", page);
		return "/project/selectTargetCom";
	}

	@RequestMapping("/getSelectTargetCom")
	public String getSelectTargetCom(HttpServletRequest request, HttpServletResponse response,Page page,TProject project){
		
		List<TProject> projectList = projectservice.selectToSelectComPageList(page,project);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			projectList = projectservice.selectToSelectComPageList(page,project);
		}
		request.setAttribute("projectList", projectList);
		request.setAttribute("page", page);
		return "/project/getSelectTargetCom";
	}
	@RequestMapping("/mgetSelectTargetCom")
	public String mgetSelectTargetCom(HttpServletRequest request, HttpServletResponse response,Page page,TProject project){
		
		List<TProject> projectList = projectservice.selectToSelectComPageList(page,project);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			projectList = projectservice.selectToSelectComPageList(page,project);
		}
		request.setAttribute("projectList", projectList);
		request.setAttribute("page", page);
		return "/project/getSelectTargetCom";
	}
	
	/**
	 * 选择供应商第一步
	 * @param request
	 * @param response
	 * @param page
	 * @param project
	 * @return
	 */
	@RequestMapping("/preSelectTargetCom")
	public String preSelectTargetCom(HttpServletRequest request, HttpServletResponse response,Page page,TOffer offer){
		
		if(offer.getProId()!=null){
			TProject project = this.projectservice.selectByPrimeryKey(offer.getProId());
			request.setAttribute("project", project);
			offer.setStatus("0");
			List<TOffer> offerList = this.offerservice.selectAllList(offer);
			request.setAttribute("offerList", offerList);
			//解析招标文件
			if(project.getFileId()!=null && !"".equals(project.getFileId())){
				String files [] = project.getFileId().split("\\|");
				List<String> fileList = new ArrayList<String>();
				for(String f:files){
					fileList.add(f);
				}
				request.setAttribute("fileList", fileList);
			}
		}
		
		return "/project/preSelectTargetCom";
	}

	@RequestMapping("/doBreakDeal")
	@ResponseBody
	public Map<String, Object> doBreakDeal(HttpServletRequest request, HttpServletResponse response,Page page,TOffer offer){
		Map<String, Object> map = new HashMap<String, Object>();
		TOffer o = offerservice.selectByPrimeryKey(offer.getId());
		offerservice.updateTOffer(o);
		TCom com = o.getCom();
		com.setBreakNo(com.getBreakNo()+1);
		comservice.updateTcom(com);
		
		map.put("code", 1);
		return map;
	}
	
	/**
	 * 选择供应商第2步
	 * @param request
	 * @param response
	 * @param page
	 * @param project
	 * @return
	 */
	@RequestMapping("/confirmSelectTargetCom")
	public String confirmSelectTargetCom(HttpServletRequest request, HttpServletResponse response,String [] ids){
		
		if(ids!=null && ids.length>0){
			TOffer o = this.offerservice.selectByPrimeryKey(Integer.parseInt(ids[0]));
			request.setAttribute("offer", o);
			
			//根据项目ID加载合同
			TContract contrat = contractservice.selectByProjectId(o.getProId());
			if(contrat==null){
				contrat = new TContract();
			}
			contrat.setOfferId(o.getId());
			contrat.setOffer(o);
			request.setAttribute("contrat", contrat);
		}
		//加载合同模板列表
		List<TTemplate> templateList = templateservice.listAll();
		request.setAttribute("templateList", templateList);
		
		//加载结算方式列表
		List<TCode> codeList = projectservice.selectSeme();
		request.setAttribute("codeList", codeList);
		return "/project/confirmSelectTargetCom";
	}

	@RequestMapping("/showConApps")
	public String showConApps(HttpServletRequest request, HttpServletResponse response,Integer id){
		
		TContract contract = contractservice.selectByProjectId(id);
		if(contract!=null){
			List<TConapp> conappList = conappservice.listAll(contract.getId());
			request.setAttribute("conappList", conappList);
		}
		
		return "/project/showConApps";
	}
	
	
	
	
	/**
	 * 待审核项目
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping("/toVerifyList")
	public String toVerifyList(HttpServletRequest request, HttpServletResponse response,Page page,TProject project){
		TUser user = (TUser) request.getSession().getAttribute("user");
		project.setUserId(user.getId());
		List<TProject> projectList = projectservice.selectToVerfyPageList(page, project);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			projectList = projectservice.selectToVerfyPageList(page, project);
		}
		request.setAttribute("projectList", projectList);
		request.setAttribute("page", page);
		return "/project/toVerifyList";
	}
	/**
	 * 已审核项目
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 */
	@RequestMapping("/toVerifyList1")
	public String toVerifyList1(HttpServletRequest request, HttpServletResponse response,Page page,TProject project){
		TUser user = (TUser) request.getSession().getAttribute("user");
		project.setUserId(user.getId());
		List<TProject> projectList = projectservice.selectToVerfyPageList1(page, project);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			projectList = projectservice.selectToVerfyPageList1(page, project);
		}
		request.setAttribute("projectList", projectList);
		request.setAttribute("page", page);
		return "/conapp/verifiedList";
	}

	@RequestMapping("/getToVerifyList")
	public String getToVerifyList(HttpServletRequest request, HttpServletResponse response,Page page,TProject project){
		TUser user = (TUser) request.getSession().getAttribute("user");
		project.setUserId(user.getId());
		List<TProject> projectList = projectservice.selectToVerfyPageList(page, project);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			projectList = projectservice.selectToVerfyPageList(page, project);
		}
		request.setAttribute("projectList", projectList);
		request.setAttribute("page", page);
		return "/conapp/getVerifiedList";
	}
	@RequestMapping("/getToVerifyList1")
	public String getToVerifyList1(HttpServletRequest request, HttpServletResponse response,Page page,TProject project){
		TUser user = (TUser) request.getSession().getAttribute("user");
		project.setUserId(user.getId());
		List<TProject> projectList = projectservice.selectToVerfyPageList1(page, project);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			projectList = projectservice.selectToVerfyPageList1(page, project);
		}
		request.setAttribute("projectList", projectList);
		request.setAttribute("page", page);
		return "/conapp/getVerifiedList";
	}
	
	@RequestMapping("/toProjectVerify")
	public String toProjectVerify(HttpServletRequest request, HttpServletResponse response,TProject project){
		if(project.getId()!=null){
			project = projectservice.selectByPrimeryKey(project.getId());
			request.setAttribute("project", project);
			//解析招标文件
			if(project.getFileId()!=null && !"".equals(project.getFileId())){
				String files [] = project.getFileId().split("\\|");
				List<String> fileList = new ArrayList<String>();
				for(String f:files){
					fileList.add(f);
				}
				request.setAttribute("fileList", fileList);
			}
			TContract contract = contractservice.selectByProjectId(project.getId());
			contract.setOffer(offerservice.selectByPrimeryKey(Integer.parseInt(contract.getAlternateField1())));
			request.setAttribute("contract", contract);
			
			List<TConapp> conAppList = conappservice.listAll(contract.getId());
			for(TConapp conApp:conAppList){
				if("0".equals(conApp.getAppStatus())){
					request.setAttribute("conApp", conApp);
				}
			}
			request.setAttribute("conAppList", conAppList);
			
			String fileName = contract.getFilePath();
			String pdfName = fileName.substring(0,fileName.indexOf("."))+".pdf";
			request.setAttribute("pdfName", pdfName);
			String ischk = request.getParameter("ischk");
			if(ischk==null){
				//判断当前节点是否为第二个节点,如果是则不显示退回上一级按钮
				TConapp curApp = conappservice.selectCurrentByContractId(contract.getId());
				TNode frontNode = nodeservice.selectByPrimeryKey(curApp.getNode().getFrontNodeId());
				if(frontNode!=null && frontNode.getFrontNodeId()==0){
					request.setAttribute("hideBack", 1);
				}
			}
			request.setAttribute("ischk", ischk);
		}
		
		return "/project/toProjectVerify";
	}
	
	@RequestMapping("/mtender")
	@ResponseBody
	public Object mtender(int pageno,String status){
		return projectservice.selectAllOnPage(pageno,status);
	}
	@RequestMapping("/projectlist")
	public String getlist(HttpServletRequest request,String starttime,String endtime,String proName,String proStatus){
		List<TProject> list = projectservice.getlist(starttime,endtime,proName,proStatus);
		request.setAttribute("list", list);
		return "/projectlist";
	}
	@RequestMapping("/mgetToVerifyList")
	public String mgetToVerifyList(int userid,int pagenum,HttpServletRequest request, HttpServletResponse response,Page page,TProject project){
		if(userid!=0){
			project.setUserId(userid);
		}
		List<TProject> projectList = projectservice.selectToVerfyPageList(page, project);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			projectList = projectservice.selectToVerfyPageList(page, project);
		}
		request.setAttribute("projectList", projectList);
		request.setAttribute("page", page);
		return "/project/getToVerifyList";
	}
		@RequestMapping("/mgetVerifyProject")
	@ResponseBody
	public Map<String,Object> mgetVerifyProject(int projectId,HttpServletRequest request, HttpServletResponse response){
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		if(projectId!=0){
			TProject project = projectservice.selectByPrimeryKey(projectId);
			if(project==null){
				return returnMap;
			}
			TContract contract = contractservice.selectByProjectId(project.getId());
			if(contract==null){
				return returnMap;
			}
			contract.setOffer(offerservice.selectByPrimeryKey(Integer.parseInt(contract.getAlternateField1())));
			returnMap.put("proname", project.getProName());//项目名称
			List<TConapp> conAppList = conappservice.listAll(contract.getId());
			for(TConapp conApp:conAppList){
				if("0".equals(conApp.getAppStatus())){
					returnMap.put("conappid", conApp.getId());
				}
			}
			
			returnMap.put("selectedcomid", contract.getOffer().getCom().getId());//选中供应商ID
			TOffer offer = new TOffer();
			offer.setProId(projectId);
			List<TOffer> offerList = offerservice.selectAllList(offer);
			List<HashMap<String,Object>> offerMapList = new ArrayList<HashMap<String,Object>>();
			for (int i = 0; i < offerList.size(); i++) {
				offer = offerList.get(i);
				HashMap<String,Object> om = new HashMap<String, Object>();
				om.put("comname", offer.getCom().getComName());//供应商名称
				om.put("comid", offer.getCom().getId());//供应商ID
				om.put("cashoffer", offer.getCashOffer());//现金报价
				om.put("acceoffer", offer.getAcceOffer());//承兑报价
				om.put("freoffer", offer.getFreOffer());//运费报价
				om.put("businesslicensepath", offer.getCom().getBusinessLicensePath());
				om.put("orcodepath", offer.getCom().getOrcodePath());
				om.put("taxrecepath", offer.getCom().getTaxrecePath());
				if(contract.getAlternateField1()!=null&&contract.getAlternateField1().length()>0){
					if(contract.getAlternateField1().equals(offer.getId())){
						om.put("isbiz", "0");
					}else{
						om.put("isbiz", "1");
					}
				}
				offerMapList.add(om);
			}
			returnMap.put("offerList", offerMapList);//报价情况
			
			List<TConapp> conappList = conappservice.listAll(contract.getId());
			List<HashMap<String,Object>> conappMapList = new ArrayList<HashMap<String,Object>>();
			TConapp conapp = null;
			for (int i = 0; i < conappList.size(); i++) {
				conapp = conappList.get(i);
				HashMap<String,Object> om = new HashMap<String, Object>();
				om.put("appnodename", conapp.getNode()==null?"":conapp.getNode().getNodeName());//审批节点名称
				om.put("appstatus", conapp.getAppStatus());//审批状态
				om.put("appex", conapp.getAppEx());//审批意见
				om.put("apptime", conapp.getAppTime());//审批时间
				if(conapp.getNode()!=null){
					conappMapList.add(om);
				}
			}
			returnMap.put("conappList", conappMapList);//审批情况
			String contractFileName = contract.getFilePath().substring(0,contract.getFilePath().indexOf("."));
			File realfile=new File(request.getRealPath("/")+"uploadFile/contract/pdf/"+contractFileName+".pdf");
			if(realfile.exists()){
				returnMap.put("contractfilepath", "uploadFile/contract/pdf/"+contractFileName+".pdf");//合同文件路径
			}else{
				returnMap.put("contractfilepath", "");//合同文件路径
			}
			File realfile2=new File(request.getRealPath("/")+"uploadFile/offer/"+contract.getOffer().getOfferSheetPath());
			if(realfile2.exists()){
				returnMap.put("offersheetfilepath", "uploadFile/offer/"+contract.getOffer().getOfferSheetPath());//报价单文件路径
			}else{
				returnMap.put("offersheetfilepath", "");//报价单文件路径
			}
			//判断当前节点是否为第二个节点,如果是则不显示退回上一级按钮
			TConapp curApp = conappservice.selectCurrentByContractId(contract.getId());
			TNode frontNode = nodeservice.selectByPrimeryKey(curApp.getNode().getFrontNodeId());
			if(frontNode!=null && frontNode.getFrontNodeId()==0){
				returnMap.put("hideBack","1");//为1时不显示退回上一级
			}else{
				returnMap.put("hideBack","0");
			}
		}
		
		return returnMap;
	}
	@RequestMapping("/proinfo")
	public String proinfo(HttpServletRequest request,String returnpath,String id){
		TProject project = projectservice.selectByPrimeryKey(Integer.parseInt(id));
		request.setAttribute("project", project);
		request.setAttribute("returnpath", returnpath);
		//解析招标文件
		if(project.getFileId()!=null && !"".equals(project.getFileId())){
			String files [] = project.getFileId().split("\\|");
			List<String> fileList = new ArrayList<String>();
			for(String f:files){
				fileList.add(f);
			}
			request.setAttribute("fileList", fileList);
		}
		return "/project/proinfo";
		
	}
}
