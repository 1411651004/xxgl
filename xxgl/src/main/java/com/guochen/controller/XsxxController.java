package com.guochen.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.guochen.model.Qyxx;
import com.guochen.model.Xsxx;
import com.guochen.model.Xtgl;
import com.guochen.page.Page;
import com.guochen.service.QyxxService;
import com.guochen.service.XsxxService;
import com.guochen.service.XtglService;
import com.guochen.utils.FileUtil;
import com.guochen.utils.HttpRequest;
import com.guochen.utils.IdcardInfoExtractor;
import com.guochen.utils.SfyjsUtils;
import com.shcm.bean.SendResultBean;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="/xsxx")
public class XsxxController {
	@Value("${mobile.ac}") 
	private String ac;
	@Value("${mobile.authkey}") 
	private String authkey;
	@Value("${mobile.cgid}") 
	private int cgid;
    @Autowired
    private XsxxService xsxxService;
    @Autowired
    private XtglService xtglService;
    @Autowired
    private QyxxService qyxxService;
    
    /**
     * 页面跳转到学生属性导出（按照配置）
     * @param request
     * @return
     */
    @RequestMapping("/proExportPage")
    public String proExportPage(HttpServletRequest request) {
    	Xtgl xtgl = new Xtgl();
    	xtgl.setCode("stuProNotShows");
		xtgl.setCodevalue("");
    	request.setAttribute("stuProNotShows", xtgl);
    	return "/xsxx/stuProExport";
    }
    
    /**
     * 导出学生信息
     * @param request
     * @return
     * @throws IOException 
     * @throws InvalidFormatException 
     */
    @RequestMapping("/proExportFile")
    @ResponseBody
    public String proExportFile(HttpServletRequest request, Xtgl xtgl){
    	JSONObject result = new JSONObject();
    	String basePath = request.getRealPath("/");
    	String notShowPros = xtgl.getCodevalue();
    	if(notShowPros == null) {
    		notShowPros = "";
    	}
    	notShowPros = ","+notShowPros+",";
    	Object jdId = request.getSession().getAttribute("jdId");
		if (jdId != null) {
			try {
				File file = new File(basePath + "uploadFile/template/学生信息.xlsx");
				Xsxx record = new Xsxx();
				record.setJd(jdId.toString());
				List<Xsxx> xsxxs = xsxxService.selectByXsxx(record);
				if(xsxxs == null || xsxxs.size() == 0) {
					result.element("status", "error");
					result.element("msg", "这个季度下没有学生信息。");
					return result.toString();
				}
				XSSFWorkbook book = new XSSFWorkbook(file);
				XSSFSheet sheet = book.getSheetAt(0);
				int rowInstance = 2;
				Row row = null, baseRow = sheet.createRow(rowInstance);

				for (int i = 0; i < xsxxs.size(); i++) {
					Xsxx item = xsxxs.get(i);
					row = sheet.createRow(rowInstance + 1 + i);
					// 填值
					fileExcel(notShowPros, baseRow, row, i, item);
				}
				book.write(new FileOutputStream(basePath + "uploadFile/tkxx/学生信息.xlsx"));
				result.element("fileName", "学生信息.xlsx");
				result.element("status", "success");
				result.element("msg", "生成文件成功。");
				return result.toString();
			} catch (Exception e) {
				result.element("status", "error");
				result.element("msg", "生成文件失败。");
			}
		}
    	
    	return result.toString();
    }
    
    /**
     * 跳转到属性显示设置的页面
     * @param request
     * @return
     */
    @RequestMapping("/proNotShow")
    public String proNotShow(HttpServletRequest request) {
    	// 不显示的字段
    	Xtgl xtgl = new Xtgl();
    	xtgl.setCode("stuProNotShows");
    	List<Xtgl> stuProNotShows = xtglService.selectByXtgl(xtgl);
    	if(stuProNotShows != null && stuProNotShows.size()>0) {
    		xtgl = stuProNotShows.get(0);
    	} else {
    		xtgl.setCodevalue("");
    	}
    	request.setAttribute("stuProNotShows", xtgl);
    	return "/xsxx/stuProNotShow";
    }
    
    @RequestMapping("/updateProNotShow")
    @ResponseBody
    public String updateProNotShow(HttpServletRequest request,Xtgl xtgl) {
    	// 不显示的字段
    	if(xtgl == null) {
    		xtgl = new Xtgl();
    	}
    	if(!"stuProNotShows".equals(xtgl.getCode())) {
    		// 读取
    		xtgl.setCode("stuProNotShows");
        	List<Xtgl> stuProNotShows = xtglService.selectByXtgl(xtgl);
        	if(stuProNotShows != null && stuProNotShows.size()>0) {
        		xtgl = stuProNotShows.get(0);
        	} else {
        		// 插入
        		xtgl.setCodevalue("");
        		xtglService.insertSelective(xtgl);
        	}
    	} else {
    		// 更新
    		xtglService.updateByPrimaryKeySelective(xtgl);
    	}
    	return "success";
    }
    
    @RequestMapping("/changeStudentStatus")
    @ResponseBody
    public String changeStudentStatus(HttpServletRequest request){
    	String status = request.getParameter("status");
    	String ids = request.getParameter("ids");
		if (ids != null) {
			String[] idArr = ids.split(",");
			if (idArr != null && idArr.length > 0) {
				for (String id : idArr) {
					if (id != null && !"".equals(id)) {
						xsxxService.changeStudentStatus(id, status);
					}
				}
			}
		}
    	return "success";
    }
    
    
    @RequestMapping("/eclr")
    public String eclr(HttpServletRequest request, HttpServletResponse response,Xsxx record){
    	record = (Xsxx)request.getSession().getAttribute("user");
    	List<Xsxx> listxsxx = xsxxService.selectByXsxx(record);
    	if(listxsxx==null||listxsxx.size()==0){
    		response.setCharacterEncoding("UTF-8");
    		response.setHeader("Content-type", "text/html;charset=UTF-8");
    		PrintWriter out = null;
    		try {
    			out = response.getWriter();
    			out.print("<script>alert('没有查到您的信息，请联系工作人员！');window.close();</script>");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		} finally {
    			out.close();
    		}
    		return "";
    	}
    	record = listxsxx.get(0);
    	request.getSession().setAttribute("user", record);
    	Qyxx qyxx = new Qyxx();
    	qyxx.setType("0");
    	List<Qyxx> list = qyxxService.selectByQyxx(qyxx);
    	request.setAttribute("qyxxlist", list);
    	
    	// 不显示的字段
    	Xtgl xtgl = new Xtgl();
    	xtgl.setCode("stuProNotShows");
    	List<Xtgl> stuProNotShows = xtglService.selectByXtgl(xtgl);
    	if(stuProNotShows != null && stuProNotShows.size()>0) {
    		xtgl = stuProNotShows.get(0);
    	} else {
    		xtgl.setCodevalue("");
    	}
    	request.setAttribute("stuProNotShows", xtgl.getCodevalue());
    	
    	// 季度信息
    	List<Xtgl> xtgls = xtglService.getAllQuarters();
    	request.setAttribute("jdList", xtgls);
    	return "/stulogin/eclr";
    }
    @RequestMapping("/stulogin")
    public String stulogin(HttpServletRequest request, HttpServletResponse response,Xsxx record){
    	return "/stulogin/login";
    }
    @RequestMapping("/sh")
    public String sh(HttpServletRequest request, HttpServletResponse response,Xsxx record){
    	List<Xsxx> list = xsxxService.selectByXsxx(record);
    	if(list!=null&&list.size()>0){
    		request.setAttribute("record", list.get(0));
    	}
    	
    	// 学校信息
    	Qyxx qyxx = new Qyxx();
    	qyxx.setType("0");
    	List<Qyxx> qyxxs = qyxxService.selectByQyxx(qyxx);
    	request.setAttribute("qyxxlist", qyxxs);
    	
    	// 季度信息
    	List<Xtgl> xtgls = xtglService.getAllQuarters();
    	request.setAttribute("jdList", xtgls);
    	
    	response.setCharacterEncoding("UTF-8");
    	return "/stulogin/sh";
    }
    @RequestMapping("/ck")
    public String ck(HttpServletRequest request, HttpServletResponse response,Xsxx record){
    	// 学生信息
    	List<Xsxx> list = xsxxService.selectByXsxx(record);
    	if(list!=null&&list.size()>0){
    		request.setAttribute("record", list.get(0));
    	}
    	
    	// 学校信息
    	Qyxx qyxx = new Qyxx();
    	qyxx.setType("0");
    	List<Qyxx> qyxxs = qyxxService.selectByQyxx(qyxx);
    	request.setAttribute("qyxxlist", qyxxs);
    	
    	// 季度信息
    	List<Xtgl> xtgls = xtglService.getAllQuarters();
    	request.setAttribute("jdList", xtgls);
    	
    	response.setCharacterEncoding("UTF-8");
    	
    	return "/xsxx/ck";
    }
    @RequestMapping("/yz")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response,Xsxx record){
    	String yzm = request.getParameter("yzm");
    	JSONObject result = new JSONObject();
		if(yzm==null&&yzm.length()!=0){
			result.element("code", "1");
			result.element("msg", "验证码不能为空");
			return result.toString();
		}
		if(!request.getSession().getAttribute("validatecode").equals(yzm.toUpperCase())){
			result.element("code", "1");
			result.element("msg", "验证码输入错误");
			return result.toString();
		}
		List<Xsxx> list = xsxxService.selectByXsxx(record);
		Xsxx xsxx;
		if(list.size()==0){
			result.element("code", "1");
			result.element("msg", "无此身份证相关信息");
			return result.toString();
		}
		xsxx = list.get(0);
		if(record.getByzd1().equals(xsxx.getByzd1())){
			if(xsxx.getSta().equals("11")
					|| xsxx.getSta().equals("1")
					|| xsxx.getSta().equals("2")){
				result.element("code", "0");
				result.element("msg", "登录成功");
				result.element("sta", xsxx.getSta());
				request.getSession().setAttribute("user", xsxx);
			}else{
				result.element("code", "1");
				result.element("msg", "用户状态错误");
			}
		}else{
			result.element("code", "1");
			result.element("msg", "密码错误");
		}
    	return result.toString();
    }
    @RequestMapping("/fjlist")
    public String fjlist(HttpServletRequest request, HttpServletResponse response,Page page,Xsxx record){
    	if(record == null) {
    		record = new Xsxx();
    	}
    	
    	Object jdId = request.getSession().getAttribute("jdId");
    	if(jdId != null){
    		record.setJd(jdId.toString());
    	}
    	List<Xsxx> xsxxList = xsxxService.selectPageList(page,record);
    	if(page.getPlainPageNum()>page.getTotalPage()){
    		page.setPageNum(page.getTotalPage());
    		xsxxList = xsxxService.selectPageList(page,record);
    	}
    	request.setAttribute("xsxxList", xsxxList);
    	request.setAttribute("page", page);
    	request.setAttribute("record", record);
    	
    	
    	return "/xsxx/fjlist";
    }
    @RequestMapping("/getfjList")
    public String getfjList(HttpServletRequest request, HttpServletResponse response,Page page,Xsxx xsxx){
    	
    	List<Xsxx> xsxxList = xsxxService.selectPageList(page,xsxx);
    	if(page.getPlainPageNum()>page.getTotalPage()){
    		page.setPageNum(page.getTotalPage());
    		xsxxList = xsxxService.selectPageList(page,xsxx);
    	}
    	request.setAttribute("xsxxList", xsxxList);
    	request.setAttribute("page", page);
    	request.setAttribute("record", xsxx);
    	
    	return "/xsxx/getfjList";
    }
    @RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,Xsxx record){
    	record.setSfz("");
    	record.setXm("");
    	record.setXx("");
    	// 因为初次录入没有季度，这里排除一次录入。0-已初次录入，10-已发初次审核，11-已发送短信，1二次录入完成，2-审批完毕
    	if(!"0".equals(record.getSta())) {
    		// 季度设置
    		Object jdStr = request.getSession().getAttribute("jdId");
	    	if(jdStr != null) {
	    		record.setJd(jdStr.toString());
	    	}
    	}
    	List<Xtgl> xtglList = xtglService.selectPageList(page,null);
    	if(page.getPlainPageNum()>page.getTotalPage()){
    		page.setPageNum(page.getTotalPage());
    		xtglList = xtglService.selectPageList(page,null);
    	}
    	
    	List<Xsxx> xsxxList = null;
		if("1".equals(record.getSta())) {
			// 二次验证
			xsxxList = xsxxService.selectPageInputSecond(page,record);
			if(page.getPlainPageNum()>page.getTotalPage()){
				page.setPageNum(page.getTotalPage());
				xsxxList = xsxxService.selectPageInputSecond(page,record);
			}
		} else {
			xsxxList = xsxxService.selectPageList(page,record);
			if(page.getPlainPageNum()>page.getTotalPage()){
				page.setPageNum(page.getTotalPage());
				xsxxList = xsxxService.selectPageList(page,record);
			}
		}
		
		List<Xtgl> xtgls =  xtglService.getAllQuarters();
		Map<String,String> xtglMap = new HashMap<String ,String>();
		for(Xtgl x : xtgls) {
			xtglMap.put(x.getId().toString(),x.getCodevalue());
		}
		for(Xsxx x : xsxxList) {
			x.setJd(xtglMap.get(x.getJd()));
		}
		request.setAttribute("dxnr", xtglList.get(1).getCodevalue());
		request.setAttribute("xsxxList", xsxxList);
		request.setAttribute("page", page);
		request.setAttribute("record", record);
		
		
		return "/xsxx/list";
	}
	@RequestMapping("/getList")
	public String getList(HttpServletRequest request, HttpServletResponse response,Page page,Xsxx xsxx){
		if(!"0".equals(xsxx.getSta())) {
			// 季度设置
			Object jdStr = request.getSession().getAttribute("jdId");
			if(jdStr != null) {
				xsxx.setJd(jdStr.toString());
			}
		}
		List<Xsxx> xsxxList = null;
		if("1".equals(xsxx.getSta())) {
			// 二次录入
			xsxxList = xsxxService.selectPageInputSecond(page,xsxx);
			if(page.getPlainPageNum()>page.getTotalPage()){
				page.setPageNum(page.getTotalPage());
				xsxxList = xsxxService.selectPageInputSecond(page,xsxx);
			}
		} else {
			xsxxList = xsxxService.selectPageList(page,xsxx);
			if(page.getPlainPageNum()>page.getTotalPage()){
				page.setPageNum(page.getTotalPage());
				xsxxList = xsxxService.selectPageList(page,xsxx);
			}
		}
		
		List<Xtgl> xtgls =  xtglService.getAllQuarters();
		Map<String,String> xtglMap = new HashMap<String ,String>();
		for(Xtgl x : xtgls) {
			xtglMap.put(x.getId().toString(),x.getCodevalue());
		}
		for(Xsxx x : xsxxList) {
			x.setJd(xtglMap.get(x.getJd()));
		}
		request.setAttribute("xsxxList", xsxxList);
		request.setAttribute("page", page);
		request.setAttribute("record", xsxx);
		if("2".equals(xsxx.getSta())) {
			return "/xsxx/getecshList";
		}
		return "/xsxx/getList";
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
				/*if(file.getSize()>10485760){
					filename.element("code", 1);
					filename.element("msg", "文件大小不能超过10MB");
					return filename.toString();
				}*/
				//取得当前上传文件的文件名称
				String myFileName = file.getOriginalFilename();
				myFileName = new String(myFileName.getBytes("ISO-8859-1"),"UTF-8");
				//如果名称不为“”,说明该文件存在，否则说明该文件不存在
				if(myFileName.trim() !=""){
					//重命名上传后的文件名
					String fileName = myFileName+createcode();
					filename.element("filename", fileName);
					filename.element("myFileName", myFileName);
					//定义上传路径
//                    String path = "H:/" + fileName;
					String path = request.getSession().getServletContext().getRealPath("/")+"uploadFile/bmb/" + fileName;
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
	
	private String emptyCellStr(Cell cell) throws Exception {
		return emptyCellStr(cell,false);
	}
	@SuppressWarnings("deprecation")
	private String emptyCellStr(Cell cell, boolean require) throws Exception {
		switch(cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK :
			if(require) {
				throw new Exception("数据文件中第"+(cell.getRowIndex()+1)+"行"+(cell.getColumnIndex()+1)+"列为必填项，请检查文件并重新导入");
			}
			return null;
		case Cell.CELL_TYPE_BOOLEAN :
			return String.valueOf(cell.getBooleanCellValue());
		case Cell.CELL_TYPE_NUMERIC :
			double dd = cell.getNumericCellValue();
			BigDecimal bigDecimal = new BigDecimal(dd);
			return String.valueOf(bigDecimal);
		default :
			String str = cell.getStringCellValue();
			if(require && (str == null || "".equals(str.trim()))) {
				throw new Exception("数据文件中第"+(cell.getRowIndex()+1)+"行"+(cell.getColumnIndex()+1)+"列为必填项，请检查文件并重新导入");
			}
			if(str != null && str.trim().equals("")) {
				return null;
			}
			return str==null ? null : str.trim();
		}
	}
	
	@RequestMapping("/expfile")
	@ResponseBody
	public String  expfile(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException,IOException {
		//转换成多部分request
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
		Map<String, MultipartFile> test=multiRequest.getFileMap();
		Map<String, String[]> map = multiRequest.getParameterMap();
		JSONObject filename = new JSONObject();
		//取得request中的所有文件名
		Iterator<String> iter = multiRequest.getFileNames();
		String path="";
		while(iter.hasNext()){
			//记录上传过程起始时的时间，用来计算上传时间
			int pre = (int) System.currentTimeMillis();
			//取得上传文件
			MultipartFile file = multiRequest.getFile(iter.next());
			if(file != null){
				/*if(file.getSize()>10485760){
					filename.element("code", 1);
					filename.element("msg", "文件大小不能超过10MB");
					return filename.toString();
				}*/
				//取得当前上传文件的文件名称
				String myFileName = file.getOriginalFilename();
				myFileName = new String(myFileName.getBytes("ISO-8859-1"),"UTF-8");
				//如果名称不为“”,说明该文件存在，否则说明该文件不存在
				if(myFileName.trim() !=""){
					//重命名上传后的文件名
					String fileName = myFileName+createcode();
					filename.element("filename", fileName);
					filename.element("myFileName", myFileName);
					//定义上传路径
//                    String path = "H:/" + fileName;
					path = request.getSession().getServletContext().getRealPath("/")+"uploadFile/" + fileName;
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
		//解析文件
		if(!path.equals("")){
			File excelFile = new File(path); 
			FileInputStream is = new FileInputStream(excelFile); //文件流
			XSSFSheet sheet = new XSSFWorkbook(is).getSheetAt(0);
			List<Xsxx> xsxxs = new ArrayList<Xsxx>();
			Xsxx xsxx;
			int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数
			
			// 季度转换以及检查
			List<Xtgl> xtgls = xtglService.getAllQuarters();
			Map<String,Integer> xtglMap = new HashMap<String, Integer>();
			if(xtgls != null && xtgls.size() > 0){
				for(Xtgl xtgl : xtgls) {
					xtglMap.put(xtgl.getCodevalue(), xtgl.getId());
				}
			}
			// 学校信息转换及检查
			Qyxx qyxx = new Qyxx();
			qyxx.setType("0");
			List<Qyxx> qyxxs = qyxxService.selectByQyxx(qyxx);
			Map<String,String> qyxxMap = new HashMap<String, String>();
			if(qyxxs != null && qyxxs.size()>0) {
				for(Qyxx item : qyxxs){
					qyxxMap.put(item.getMc(), item.getMc());
				}
			}
			
			
			Integer jdId = null;
			for(int i=2;i<rowCount;i++){
				Row row = sheet.getRow(i);
				xsxx = new Xsxx();
				try{
					xsxx.setJd(emptyCellStr(row.getCell(0)));
					xsxx.setByzd3(emptyCellStr(row.getCell(1)));
					xsxx.setCjxm(emptyCellStr(row.getCell(2)));
					xsxx.setByzd2(emptyCellStr(row.getCell(3)));
					xsxx.setDlbh(emptyCellStr(row.getCell(4)));
					xsxx.setXm(emptyCellStr(row.getCell(5)));
					xsxx.setYwxm(emptyCellStr(row.getCell(6)));
					xsxx.setRwxm(emptyCellStr(row.getCell(7)));
					xsxx.setXx(emptyCellStr(row.getCell(8)));
					xsxx.setXb(emptyCellStr(row.getCell(9)));
					xsxx.setSr(emptyCellStr(row.getCell(10)));
					xsxx.setRxnf(emptyCellStr(row.getCell(11)));
					xsxx.setNj(emptyCellStr(row.getCell(12)));
					xsxx.setZy(emptyCellStr(row.getCell(13)));
					xsxx.setSfz(emptyCellStr(row.getCell(14), true));
					xsxx.setDh(emptyCellStr(row.getCell(15)));
					xsxx.setQq(emptyCellStr(row.getCell(16)));
					xsxx.setRydj(emptyCellStr(row.getCell(17)));
					xsxx.setYydj(emptyCellStr(row.getCell(18)));
					xsxx.setCfkg(emptyCellStr(row.getCell(19)));
					xsxx.setLdkg(emptyCellStr(row.getCell(20)));
					xsxx.setQchb(emptyCellStr(row.getCell(21)));
					xsxx.setDdhb(emptyCellStr(row.getCell(22)));
					xsxx.setHchb1(emptyCellStr(row.getCell(23)));
					xsxx.setHchb2(emptyCellStr(row.getCell(24)));
					xsxx.setZlpf(emptyCellStr(row.getCell(25)));
					xsxx.setGgsj(emptyCellStr(row.getCell(26)));
					xsxx.setDdrq(emptyCellStr(row.getCell(27)));
					xsxx.setJpf(emptyCellStr(row.getCell(28)));
					xsxx.setQzf(emptyCellStr(row.getCell(29)));
					xsxx.setZsf(emptyCellStr(row.getCell(30)));
					xsxx.setZj(emptyCellStr(row.getCell(31)));
					xsxx.setCfsjbz(emptyCellStr(row.getCell(32)));
					xsxx.setQtbz(emptyCellStr(row.getCell(33)));
				} catch (Exception e) {
					filename.element("msg", e.getMessage());
					filename.element("code", "1");
					return filename.toString();
				}
				
				if(xsxx.getXx() != null && !xsxx.getXx().trim().equals("")){
					xsxx.setXx(qyxxMap.get(xsxx.getXx()));
					if(xsxx.getXx() == null ){
						filename.element("msg", "数据文件中第"+(i+1)+"行，不存在这样的校名，请检查文件并重新导入");
						filename.element("code", "1");
						return filename.toString();
					}
				}
				jdId = xtglMap.get(xsxx.getJd());
//				if(jdId == null ){
//					filename.element("msg", "数据文件中第"+(i+1)+"行，不存在这样的季度名，请检查文件并重新导入");
//					filename.element("code", "1");
//					return filename.toString();
//				} else if(jdId != request.getSession().getAttribute("jdId")) {
//					filename.element("msg", "数据文件中第"+(i+1)+"行，季度的值与当前季度不符，请检查文件并重新导入");
//					filename.element("code", "1");
//					return filename.toString();
//				}
				if(jdId != null){
				    xsxx.setJd(jdId.toString());
				}
				xsxxs.add(xsxx);
				//xsxx.setSta("3");//直接进入最终档案库
			}
			for(Xsxx item : xsxxs) {
				xsxxService.updateByPrimaryKeySelective(item);
			}
			return filename.toString();
		}
		
		
		return filename.toString();
	}
	//public static void main(String[] args) {
	//	System.out.println("██████");
	//}
    @RequestMapping("add")
    @ResponseBody
    public int add(Xsxx xsxx,String flag,HttpServletResponse response,HttpServletRequest request) {
    	if("1".equals(flag)){
    		List<Xsxx> xlist = xsxxService.selectPageList(new Page(), xsxx);
    		if(xlist.size()!=0){
    			return 5;//身份证号已被占用，请联系管理员
    		}
    		xsxx.setSta("0");//初次录入
    		String str = xsxx.getSfz();
    		str=str.substring(str.length()-6,str.length());
    		xsxx.setByzd1(str);
    		return xsxxService.insertSelective(xsxx);
    	}else if("2".equals(flag)){
    		xsxx.setSta("1");//二次录入
    		String str = xsxx.getSfz();
    		str=str.substring(str.length()-6,str.length());
    		xsxx.setByzd1(str);
    		try {
				xsxx.setXm(new String(xsxx.getXm().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setXx(new String(xsxx.getXx().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setXz(new String(xsxx.getXz().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setXb(new String(xsxx.getXb().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setMz(new String(xsxx.getMz().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setNj(new String(xsxx.getNj().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setZy(new String(xsxx.getZy().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setFqlxdh(new String(xsxx.getFqlxdh().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setMqlxdh(new String(xsxx.getMqlxdh().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setFqxm(new String(xsxx.getFqxm().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setMqxm(new String(xsxx.getMqxm().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setRxnf(new String(xsxx.getRxnf().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setJtdz(new String(xsxx.getJtdz().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setBmb(new String(xsxx.getBmb().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setZlzg(new String(xsxx.getSfz().getBytes("iso8859-1"),"UTF-8"));//在留资格自动生成
				xsxx.setHzzp(new String(xsxx.getSfz().getBytes("iso8859-1"),"UTF-8")+".jpg");//护照照片名字为身份证号,后缀名为jpg
				xsxx.setSfyjs(new String(xsxx.getSfyjs().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setYwxm(new String(xsxx.getYwxm().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setRwxm(new String(xsxx.getRwxm().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setSfyhz(new String(xsxx.getSfyhz().getBytes("iso8859-1"),"UTF-8"));
				if(xsxx.getSfyhz().equals("是")){//如果有护照
					xsxx.setHzhm(new String(xsxx.getHzhm().getBytes("iso8859-1"),"UTF-8"));
					xsxx.setHzyxq(new String(xsxx.getHzyxq().getBytes("iso8859-1"),"UTF-8"));
				}else if(xsxx.getSfyhz().equals("否")){
					xsxx.setQdydr(new String(xsxx.getQdydr().getBytes("iso8859-1"),"UTF-8"));
					xsxx.setHzyxq("");
				}
				xsxx.setHyqk(new String(xsxx.getHyqk().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setSfqgrb(new String(xsxx.getSfqgrb().getBytes("iso8859-1"),"UTF-8"));
				if(xsxx.getSfqgrb().equals("是")){
					xsxx.setQrbcs(new String(xsxx.getQrbcs().getBytes("iso8859-1"),"UTF-8"));
					xsxx.setZhfrsj(new String(xsxx.getZhfrsj().getBytes("iso8859-1"),"UTF-8"));
				}
				xsxx.setSxsj(new String(xsxx.getSxsj().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setJd(new String(xsxx.getJd().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setYfcc(new String(xsxx.getYfcc().getBytes("iso8859-1"),"UTF-8"));
				xsxx.setYyks("1");
				xsxx.setRyks("1");
				xsxx.setYjsks("1");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
    		//自动生成在留资格
    		//读取源文件
    		String oldPath = request.getSession().getServletContext().getRealPath("/")+"uploadFile/template/在留资格申请信息表.xlsx";
			String newPath = request.getSession().getServletContext().getRealPath("/")+"uploadFile/zlzg/"+xsxx.getSfz()+".xlsx";
    		//复制一份新文件
			FileUtil.copyFile(oldPath, newPath);
    		//修改新文件
			File excelFile = new File(newPath); 
			try {
				FileInputStream is = new FileInputStream(excelFile); //文件流
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
				XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
				Row row = sheet.getRow(2);
				Cell cell = row.getCell(5);
				//识别身份证号
				IdcardInfoExtractor idcardInfo=new IdcardInfoExtractor(xsxx.getSfz());
				cell.setCellValue(idcardInfo.getYear());
				cell = row.getCell(10);
				cell.setCellValue(idcardInfo.getMonth());
				cell = row.getCell(13);
				cell.setCellValue(idcardInfo.getDay());
				row = sheet.getRow(3);
				cell = row.getCell(6);
				cell.setCellValue(xsxx.getXm());
				cell = row.getCell(18);
				cell.setCellValue(xsxx.getYwxm());
				row = sheet.getRow(4);
				cell = row.getCell(3);
				cell.setCellValue(xsxx.getXb());
				cell = row.getCell(8);
				if(xsxx.getHyqk().equals("未婚")){
					cell.setCellValue("█");
				}else{
					cell.setCellValue("□");
				}
				cell = row.getCell(12);
				if(xsxx.getHyqk().equals("既婚")){
					cell.setCellValue("█");
				}else{
					cell.setCellValue("□");
				}
				row = sheet.getRow(5);
				cell = row.getCell(4);
				cell.setCellValue(idcardInfo.getProvince());
				row = sheet.getRow(7);
				cell = row.getCell(2);
				cell.setCellValue(xsxx.getJtdz());
				//查证申请予定地加字段后再写
				row = sheet.getRow(8);
				cell = row.getCell(8);
				
				Qyxx qyxx = new Qyxx();
		    	qyxx.setType("0");
		    	qyxx.setMc(xsxx.getXx());
		    	List<Qyxx> list = qyxxService.selectByQyxx(qyxx);
		    	cell.setCellValue(list.get(0).getLq()==null?"":list.get(0).getLq());
				row = sheet.getRow(9);
				cell = row.getCell(7);
				if(xsxx.getSfyhz().equals("是")){
					cell.setCellValue("█");
					row = sheet.getRow(10);
					cell = row.getCell(9);
					cell.setCellValue(xsxx.getHzhm());
					cell = row.getCell(19);
					cell.setCellValue(xsxx.getHzyxq().substring(0,4));
					cell = row.getCell(23);
					cell.setCellValue(xsxx.getHzyxq().substring(5,7));
					cell = row.getCell(26);
					cell.setCellValue(xsxx.getHzyxq().substring(8,10));
				}else{
					cell.setCellValue("□");
				}
				row = sheet.getRow(9);
				cell = row.getCell(10);
				if(xsxx.getSfyhz().equals("否")){
					cell.setCellValue("█");
					cell = row.getCell(17);
					cell.setCellValue(xsxx.getQdydr().substring(0,4));
					cell = row.getCell(21);
					cell.setCellValue(xsxx.getQdydr().substring(5,7));
					cell = row.getCell(24);
					cell.setCellValue(xsxx.getQdydr().substring(8,10));
				}else{
					cell.setCellValue("□");
				}
				//以前是否去过日本
				row = sheet.getRow(11);
				cell = row.getCell(11);
				if(xsxx.getSfqgrb().equals("是")){
					cell.setCellValue("█");
					cell = row.getCell(22);
					cell.setCellValue(xsxx.getQrbcs());
					row = sheet.getRow(12);
					cell = row.getCell(7);
					cell.setCellValue(xsxx.getZhfrsj().substring(0,4));
					cell = row.getCell(11);
					cell.setCellValue(xsxx.getZhfrsj().substring(5,7));
					cell = row.getCell(14);
					cell.setCellValue(xsxx.getZhfrsj().substring(8,10));//
					cell = row.getCell(19);
					cell.setCellValue(xsxx.getZhfrsj().substring(13,17));
					cell = row.getCell(23);
					cell.setCellValue(xsxx.getZhfrsj().substring(18,20));
					cell = row.getCell(26);
					cell.setCellValue(xsxx.getZhfrsj().substring(21,23));
				}else{
					cell.setCellValue("□");
				}
				row = sheet.getRow(11);
				if(xsxx.getSfqgrb().equals("否")){
					cell=row.getCell(14);
					cell.setCellValue("█");
				}else{
					cell=row.getCell(14);
					cell.setCellValue("□");
				}
				//最后一行
				row = sheet.getRow(22);
				cell = row.getCell(1);
				String nj = "";
				
				if(xsxx.getNj().equals("1")){
					nj="1年生";
				}else if(xsxx.getNj().equals("2")){
					nj="2年生";
				}else if(xsxx.getNj().equals("3")){
					nj="3年生";
				}else if(xsxx.getNj().equals("4")){
					nj="4年生";
				}
				cell.setCellValue(xsxx.getXx()+xsxx.getZy()+(xsxx.getSfyjs().equals("研究生")?"大学院":"")+nj);
				FileOutputStream outputStream = new FileOutputStream(newPath);
				xssfWorkbook.write(outputStream);
				outputStream.close();
				xssfWorkbook=null;
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		xsxxService.updateByPrimaryKeySelective(xsxx);
    		response.setCharacterEncoding("UTF-8");
    		response.setHeader("Content-type", "text/html;charset=UTF-8");
    		PrintWriter out = null;
    		try {
    			out = response.getWriter();
    			out.print("<script>alert('信息录入成功！');window.close();</script>");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		} finally {
    			out.close();
    		}
    	}else{
    		response.setCharacterEncoding("UTF-8");
    		response.setHeader("Content-type", "text/html;charset=UTF-8");
    		PrintWriter out = null;
    		try {
    			out = response.getWriter();
    			out.print("<script>alert('参数错误！');</script>");
    			out.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		} finally {
    			out.close();
    		}
    		
    	}
    	return 1;
    }
    
    @RequestMapping("/xzfj")
    @ResponseBody
    public String xzfj(HttpServletRequest request) {
    	String ids = request.getParameter("ids");
    	String fjtype = request.getParameter("fjtype");
    	String str[] = ids.split(",");
    	String uploadfile = request.getSession().getServletContext().getRealPath("/")+"uploadFile/";
    	String tempPath=request.getSession().getServletContext().getRealPath("/")+"temp";
    	String zipPath=request.getSession().getServletContext().getRealPath("/")+"zip";
    	String newfilename;
    	String prefix;
    	String oldPath;
    	String newPath;
		File temp = new File(tempPath);//创建临时文件夹
		FileUtil.deleteFile(temp);
		if(!temp.exists()){temp.mkdirs();}
    	for(int i=0;i<str.length;i++){
    		Xsxx xsxx = new Xsxx();
    		xsxx.setId(Integer.parseInt(str[i]));
			xsxx=xsxxService.selectByXsxx(xsxx).get(0);
			temp = new File(tempPath+"/"+xsxx.getXm());
			if(!temp.exists()){temp.mkdirs();}
			
			if(fjtype.indexOf("1")>-1) {
				newfilename = xsxx.getBmb();
				if(newfilename != null && !"".equals(newfilename)) {
					newfilename = newfilename.substring(0,newfilename.length() - 6);//新文件名
					prefix="."+newfilename.substring(newfilename.lastIndexOf(".")+1);//后缀名
					oldPath = uploadfile+"bmb/" + xsxx.getBmb();
					newPath = tempPath+"/"+xsxx.getXm()+"/报名表" + prefix;
					FileUtil.copyFile(oldPath, newPath);
				}
			}
			if(fjtype.indexOf("2")>-1) {
				newfilename = xsxx.getZlzg();
				if(newfilename != null && !"".equals(newfilename)) {
					newfilename = newfilename.substring(0,newfilename.length() - 6);//新文件名
					prefix=".xlsx";//后缀名
					oldPath = uploadfile +"zlzg/"+ xsxx.getSfz()+prefix;
					newPath = tempPath+"/"+xsxx.getXm()+"/在留资格" + prefix;
					FileUtil.copyFile(oldPath, newPath);
				}
			}
			if(fjtype.indexOf("3")>-1) {
				newfilename = xsxx.getHzzp();
				if(newfilename!=null&&newfilename.length()!=0){
					newfilename = newfilename.substring(0,newfilename.length() - 6);//新文件名
					prefix=".jpg";//后缀名
					oldPath = uploadfile+"hzzp/"+ xsxx.getSfz()+prefix;
					newPath = tempPath+"/"+xsxx.getXm()+"/"+xsxx.getDlbh()+xsxx.getXm() + prefix;
					FileUtil.copyFile(oldPath, newPath);
				}
			}
			/*
			switch (fjtype) {
			case "1":
				newfilename = xsxx.getBmb();
				newfilename = newfilename.substring(0,newfilename.length() - 6);//新文件名
				prefix="."+newfilename.substring(newfilename.lastIndexOf(".")+1);//后缀名
				oldPath = uploadfile+"bmb/" + xsxx.getBmb();
				newPath = tempPath+"/"+xsxx.getXm()+"/报名表" + prefix;
				FileUtil.copyFile(oldPath, newPath);
				
				break;
			case "12":
				newfilename = xsxx.getBmb();
				newfilename = newfilename.substring(0,newfilename.length() - 6);//新文件名
				prefix="."+newfilename.substring(newfilename.lastIndexOf(".")+1);//后缀名
				oldPath = uploadfile+"bmb/" + xsxx.getBmb();
				newPath = tempPath+"/"+xsxx.getXm()+"/报名表" + prefix;
				FileUtil.copyFile(oldPath, newPath);
				newfilename = xsxx.getZlzg();
				newfilename = newfilename.substring(0,newfilename.length() - 6);//新文件名
				prefix=".xlsx";//后缀名
				oldPath = uploadfile +"zlzg/"+ xsxx.getSfz()+prefix;
				newPath = tempPath+"/"+xsxx.getXm()+"/在留资格" + prefix;
				FileUtil.copyFile(oldPath, newPath);
				break;
			case "123":
				newfilename = xsxx.getBmb();
				newfilename = newfilename.substring(0,newfilename.length() - 6);//新文件名
				prefix="."+newfilename.substring(newfilename.lastIndexOf(".")+1);//后缀名
				oldPath = uploadfile+"bmb/"  + xsxx.getBmb();
				newPath = tempPath+"/"+xsxx.getXm()+"/报名表" + prefix;
				FileUtil.copyFile(oldPath, newPath);
				newfilename = xsxx.getZlzg();
				newfilename = newfilename.substring(0,newfilename.length() - 6);//新文件名
				prefix=".xlsx";//后缀名
				oldPath = uploadfile+"zlzg/" + xsxx.getSfz()+prefix;
				newPath = tempPath+"/"+xsxx.getXm()+"/在留资格" + prefix;
				FileUtil.copyFile(oldPath, newPath);
				newfilename = xsxx.getHzzp();
				if(newfilename!=null&&newfilename.length()!=0){
					newfilename = newfilename.substring(0,newfilename.length() - 6);//新文件名
					prefix=".jpg";//后缀名
					oldPath = uploadfile+"hzzp/"+ xsxx.getSfz()+prefix;
					newPath = tempPath+"/"+xsxx.getXm()+"/"+xsxx.getDlbh()+xsxx.getXm() + prefix;
					FileUtil.copyFile(oldPath, newPath);
				}
				break;
			default:
				break;
			}
			*/
    	}
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    	String code = sdf.format(date);
    	try {
			FileUtil.zip(tempPath, zipPath, code+".zip");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	JSONObject result = new JSONObject();
    	result.element("code", "0");
    	result.element("msg", "操作成功");
    	result.element("file", "zip/"+code+".zip");
    	return result.toString();
    }
    
    @RequestMapping("sp")
    @ResponseBody
    public String sp(Xsxx record) {
    	String[] sfzs = record.getSfz().split(",");
    	for(int i=0;i<sfzs.length;i++){
    		record.setSfz(sfzs[i]);
    		xsxxService.updatestabysfz(record);
    	}
    	JSONObject result = new JSONObject();
    	result.element("code", "0");
    	result.element("msg", "操作成功");
    	return result.toString();
    }
    @RequestMapping("kftk")
    @ResponseBody
    public String kftk(Xsxx record) {
    	List<Xsxx> list = xsxxService.selectByXsxx(record);
    	if(list!=null&&list.size()>0){
    		record=list.get(0);
    		record.setKftk("0".equals(record.getKftk())?"1":"0");
    		xsxxService.updateByIdSelective(record);
    	}
    	JSONObject result = new JSONObject();
    	result.element("code", "0");
    	result.element("msg", "操作成功");
    	return result.toString();
    }
    @RequestMapping("exp")
    @ResponseBody
    public String exp(Xsxx record) {
    	record = xsxxService.selectByXsxx(record).get(0);
    	//学校材料落款日期
    	//学年
    	//学制
    	//入学年份
    	//学校材料-参加年月
    	//会社名
    	//学校材料在学-免税的落款日期
    	//学校材料在学-签证的落款日期
    	//学校材料-学分取得内容-中
    	//学校材料-学分取得内容-日
    	//时给
    	
    	JSONObject result = new JSONObject();
    	result.element("code", "0");
    	result.element("msg", "操作成功");
    	return result.toString();
    }
    @RequestMapping("update")
    @ResponseBody
    public int update(Xsxx xsxx) {
        
        return xsxxService.updateByIdSelective(xsxx);
    }
    @RequestMapping("updatesta")
    @ResponseBody
    public int updatesta(Xsxx xsxx) {
    	List<Xsxx> list = xsxxService.selectByXsxx(xsxx);
    	xsxx =list.get(0);
    	xsxx.setSta("2");
    	return xsxxService.updateByPrimaryKeySelective(xsxx);
    }
    
    @RequestMapping("delete")
    @ResponseBody
    public int delete(@RequestBody Integer id) {
        
        return xsxxService.deleteByPrimaryKey(id);
    }
    
    @RequestMapping(value = "/sendxgmobile",produces="text/html;charset=UTF-8")
    @ResponseBody
    public String sendxgmobile(String remindCon,String ids,String phones){
    	String[] str = phones.split(",");
//    	List<SendResultBean> listItem = HttpRequest.send(ac,authkey,cgid,phones.split(","), remindCon);
//    	int i = listItem.get(0).getResult();
    	int i = 1;
    	JSONObject result = new JSONObject();
    	result.element("code", "0");
    	switch (i) {
    	case 1:
    		result.element("code", "1");
    		result.element("msg","短信发送成功");
    		if(ids!=null){
    			Xsxx xsxx = new Xsxx();
    			xsxx.setId(Integer.parseInt(ids));
    			List<Xsxx> list = xsxxService.selectByXsxx(xsxx);
    	    	xsxx =list.get(0);
    	    	xsxx.setSta("11");
    	    	xsxxService.updateByPrimaryKeySelective(xsxx);
    		}
    		break;
    	case 0:
    		result.element("msg","账户格式不正确");
    		break;
    	case -1:
    		result.element("msg","服务器拒绝");
    		break;
    	case -2:
    		result.element("msg","秘钥不正确");
    		break;
    	case -3:
    		result.element("msg","秘钥已锁定");
    		break;
    	case -4:
    		result.element("msg","参数不正确");
    		break;
    	case -5:
    		result.element("msg","无此账户");
    		break;
    	case -6:
    		result.element("msg","账户已锁定或已过期");
    		break;
    	case -7:
    		result.element("msg","账户未开启接口发送");
    		break;
    	case -8:
    		result.element("msg","不可使用该通道组");
    		break;
    	case -9:
    		result.element("msg","账户余额不足");
    		break;
    	case -10:
    		result.element("msg","内部错误");
    		break;
    	case -11:
    		result.element("msg","扣费失败");
    		break;
    	default:
    		break;
    	}
    	
    	return result.toString();
    }
    @RequestMapping(value = "/sendmobile",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String sendmobile(String remindCon,String ids,String phones){
    	String[] str = phones.split(",");
		List<SendResultBean> listItem = HttpRequest.send(ac,authkey,cgid,str, remindCon);
		int i = listItem.get(0).getResult();
		JSONObject result = new JSONObject();
		result.element("code", "0");
		switch (i) {
		case 1:
			result.element("code", "1");
			result.element("msg","短信发送成功");
			break;
		case 0:
			result.element("msg","账户格式不正确");
			break;
		case -1:
			result.element("msg","服务器拒绝");
			break;
		case -2:
			result.element("msg","秘钥不正确");
			break;
		case -3:
			result.element("msg","秘钥已锁定");
			break;
		case -4:
			result.element("msg","参数不正确");
			break;
		case -5:
			result.element("msg","无此账户");
			break;
		case -6:
			result.element("msg","账户已锁定或已过期");
			break;
		case -7:
			result.element("msg","账户未开启接口发送");
			break;
		case -8:
			result.element("msg","不可使用该通道组");
			break;
		case -9:
			result.element("msg","账户余额不足");
			break;
		case -10:
			result.element("msg","内部错误");
			break;
		case -11:
			result.element("msg","扣费失败");
			break;
		default:
			break;
		}
		
		return result.toString();
	}
    @RequestMapping(value = "/sendcode",produces="text/html;charset=UTF-8")
    @ResponseBody
    public String sendcode(String dh){
    	String retStr = "";
		retStr = createcode();
		String remindCon = "您的验证码是"+retStr+",在5分钟内有效。如非本人操作请忽略本短信。";
		List<SendResultBean> listItem = HttpRequest.send(ac,authkey,cgid,new String[]{dh}, remindCon);
		JSONObject result = new JSONObject();
		result.element("code", "0");
		if(listItem.size()==0){
			result.element("code", "0");
			result.element("msg", "短信发送失败，请联系管理员");
			 
		}else if(listItem.get(0).getResult()==1){
		//if(true){
			 result.element("code", "1");
			 result.element("msg", "短信发送成功，请注意查收");
			 result.element("yzcode", retStr);
		 }
		result.element("code", "1");
		 result.element("msg", "短信发送成功，请注意查收");
		 result.element("yzcode", retStr);
    	return result.toString();
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
    private void fileExcel(String notShowPros, Row baseRow, Row row, int i, Xsxx item){
    	int colIndex=0;
    	colIndex = createCell(notShowPros,baseRow,row,i,item.getXm(),"姓名",colIndex,"xm");
		if(notShowPros.indexOf(",ywxm,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("英文姓名");
			row.createCell(colIndex++).setCellValue(item.getYwxm());
		}
		if(notShowPros.indexOf(",rwxm,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("日文姓名");
			row.createCell(colIndex++).setCellValue(item.getRwxm());
		}
		if(notShowPros.indexOf(",xb,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("性别");
			row.createCell(colIndex++).setCellValue(item.getXb());
		}
		if(notShowPros.indexOf(",mz,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("民族");
			row.createCell(colIndex++).setCellValue(item.getMz());
		}
		if(notShowPros.indexOf(",xx,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("学校");
			row.createCell(colIndex++).setCellValue(item.getXx());
		}
		if(notShowPros.indexOf(",zy,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("专业");
			row.createCell(colIndex++).setCellValue(item.getZy());
		}
		if(notShowPros.indexOf(",sfz,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("身份证号码");
			row.createCell(colIndex++).setCellValue(item.getSfz());
		}
		if(notShowPros.indexOf(",dh,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("电话");
			row.createCell(colIndex++).setCellValue(item.getDh());
		}
		if(notShowPros.indexOf(",sfyhz,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("是否有护照");
			row.createCell(colIndex++).setCellValue(item.getSfyhz());
		}
		if(notShowPros.indexOf(",hyqk,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("婚姻情况");
			row.createCell(colIndex++).setCellValue(item.getHyqk());
		}
		if(notShowPros.indexOf(",qdydr,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("取得预定日期");
			row.createCell(colIndex++).setCellValue(item.getQdydr());
		}
		if(notShowPros.indexOf(",hzhm,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("护照号码");
			row.createCell(colIndex++).setCellValue(item.getHzhm());
		}
		if(notShowPros.indexOf(",hzyxq,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("有效期");
			row.createCell(colIndex++).setCellValue(item.getHzyxq());
		}
		if(notShowPros.indexOf(",sfqgrb,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("是否去过日本");
			row.createCell(colIndex++).setCellValue(item.getSfqgrb());
		}
		if(notShowPros.indexOf(",qrbcs,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("去日本次数");
			row.createCell(colIndex++).setCellValue(item.getQrbcs());
		}
		if(notShowPros.indexOf(",zhfrsj,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("最近去日本时间");
			row.createCell(colIndex++).setCellValue(item.getZhfrsj());
		}
		if(notShowPros.indexOf(",rxnf,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("入学年份");
			row.createCell(colIndex++).setCellValue(item.getRxnf());
		}
		if(notShowPros.indexOf(",nj,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("年级");
			row.createCell(colIndex++).setCellValue(SfyjsUtils.key2Value(item.getSfyjs(), item.getNj()));
		}
		if(notShowPros.indexOf(",xz,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("学制");
			row.createCell(colIndex++).setCellValue(item.getXz());
		}
		if(notShowPros.indexOf(",sfyjs,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("学历");
			row.createCell(colIndex++).setCellValue(item.getSfyjs());
		}
		if(notShowPros.indexOf(",jd,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("季度");
			row.createCell(colIndex++).setCellValue(item.getJd());
		}
		if(notShowPros.indexOf(",sxsj,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("实习时间");
			row.createCell(colIndex++).setCellValue(item.getSxsj());
		}
		if(notShowPros.indexOf(",ryks,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("是否参加日语等级考试");
			row.createCell(colIndex++).setCellValue(item.getRyks());
		}
		if(notShowPros.indexOf(",yyks,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("是否参加英语四六级考试");
			row.createCell(colIndex++).setCellValue(item.getYyks());
		}
		if(notShowPros.indexOf(",yjsks,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("是否参加研究考试");
			row.createCell(colIndex++).setCellValue(item.getYjsks());
		}
		if(notShowPros.indexOf(",fqxm,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("父亲姓名");
			row.createCell(colIndex++).setCellValue(item.getFqxm());
		}
		if(notShowPros.indexOf(",fqlxdh,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("父亲电话");
			row.createCell(colIndex++).setCellValue(item.getFqlxdh());
		}
		if(notShowPros.indexOf(",mqxm,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("母亲姓名");
			row.createCell(colIndex++).setCellValue(item.getMqxm());
		}
		if(notShowPros.indexOf(",mqlxdh,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("母亲电话");
			row.createCell(colIndex++).setCellValue(item.getMqlxdh());
		}
		if(notShowPros.indexOf(",jtdz,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("家庭地址");
			row.createCell(colIndex++).setCellValue(item.getJtdz());
		}
		if(notShowPros.indexOf(",sg,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("身高");
			row.createCell(colIndex++).setCellValue(item.getSg());
		}
		if(notShowPros.indexOf(",tz,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("体重");
			row.createCell(colIndex++).setCellValue(item.getTz());
		}
		if(notShowPros.indexOf(",xw,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("胸围");
			row.createCell(colIndex++).setCellValue(item.getXw());
		}
		if(notShowPros.indexOf(",yw") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("腰围");
			row.createCell(colIndex++).setCellValue(item.getYw());
		}
		if(notShowPros.indexOf(",xh,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("鞋号");
			row.createCell(colIndex++).setCellValue(item.getXh());
		}
		if(notShowPros.indexOf(",yfcc,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("衣服尺寸");
			row.createCell(colIndex++).setCellValue(item.getYfcc());
		}
		if(notShowPros.indexOf(",byzd3,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("窗口");
			row.createCell(colIndex++).setCellValue(item.getByzd3());
		}
		if(notShowPros.indexOf(",byzd4,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("学生番号");
			row.createCell(colIndex++).setCellValue(item.getByzd4());
		}
		if(notShowPros.indexOf(",byzd5,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("姓氏");
			row.createCell(colIndex++).setCellValue(item.getByzd5());
		}
		if(notShowPros.indexOf(",zlpf,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("再留批复");
			row.createCell(colIndex++).setCellValue(item.getZlpf());
		}
		if(notShowPros.indexOf(",jfsj,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("缴费时间");
			row.createCell(colIndex++).setCellValue(item.getJfsj());
		}
		if(notShowPros.indexOf(",cjxm,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("参加项目");
			row.createCell(colIndex++).setCellValue(item.getCjxm());
		}
		if(notShowPros.indexOf(",qchb,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("启程航班");
			row.createCell(colIndex++).setCellValue(item.getQchb());
		}
		if(notShowPros.indexOf(",ddhb,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("到达航班");
			row.createCell(colIndex++).setCellValue(item.getDdhb());
		}
		if(notShowPros.indexOf(",hchb1,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("回程航班1");
			row.createCell(colIndex++).setCellValue(item.getHchb1());
		}
		if(notShowPros.indexOf(",hchb2,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("回程航班2");
			row.createCell(colIndex++).setCellValue(item.getHchb2());
		}
		if(notShowPros.indexOf(",ddrq,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("到达日本时间");
			row.createCell(colIndex++).setCellValue(item.getDdrq());
		}
		if(notShowPros.indexOf(",ggsj,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("归国时间");
			row.createCell(colIndex++).setCellValue(item.getGgsj());
		}
		if(notShowPros.indexOf(",jpf,") < 0){
			if(i == 0) baseRow.createCell(colIndex).setCellValue("机票费");
			row.createCell(colIndex++).setCellValue(item.getJpf());
		}
		colIndex = createCell(notShowPros,baseRow,row,i,item.getQzf(),"签证费",colIndex,"qzf");
		colIndex = createCell(notShowPros,baseRow,row,i,item.getZsf(),"住宿费",colIndex,"zsf");
		colIndex = createCell(notShowPros,baseRow,row,i,item.getQtbz(),"备注",colIndex,"qtbz");
    }
    
    private int createCell(String notShowPros, Row baseRow, Row row, int i, String cellValue, String colName, int col, String pro) {
    	if(notShowPros.indexOf(","+pro+",") < 0){
			if(i == 0) baseRow.createCell(col).setCellValue(colName);
			row.createCell(col).setCellValue(cellValue);
			return col++;
		}
    	return col;
    }
}