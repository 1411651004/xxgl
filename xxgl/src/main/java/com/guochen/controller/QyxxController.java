package com.guochen.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import sun.awt.FwDispatcher;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
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

import com.guochen.model.Bfgl;
import com.guochen.model.Qyxx;
import com.guochen.model.Xsxx;
import com.guochen.page.Page;
import com.guochen.service.QyxxService;
import com.guochen.service.XsxxService;
import com.guochen.utils.FileUtil;
import com.guochen.utils.HFWordUtil;
import com.guochen.utils.SfyjsUtils;
@Controller
@RequestMapping(value="/qyxx")
public class QyxxController {
	
	@Value("${myEmailAccount}") 
	private String myEmailAccount;
	@Value("${myEmailPassword}") 
	private String myEmailPassword;
	@Value("${myEmailSMTPHost}") 
	private String myEmailSMTPHost;
	@Value("${mail.transport.protocol}") 
	private String mailtransportprotocol;
	@Value("${mail.smtp.auth}") 
	private String mailsmtpauth;
	@Value("${sendname}") 
	private String sendname;
	
    @Autowired
    private QyxxService qyxxService;
    @Autowired
    private XsxxService xsxxService;
    
    @RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,Qyxx record){
		List<Qyxx> qyxxList = qyxxService.selectPageList(page,record);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			qyxxList = qyxxService.selectPageList(page,record);
		}
		request.setAttribute("qyxxList", qyxxList);
		request.setAttribute("page", page);
		request.setAttribute("record", record);
		
		
		return "/qyxx/list";
	}
    @RequestMapping("/zllist")
    public String zllist(HttpServletRequest request, HttpServletResponse response,Page page,Qyxx record){
    	List<Qyxx> qyxxList = qyxxService.selectPageList(page,record);
    	if(page.getPlainPageNum()>page.getTotalPage()){
    		page.setPageNum(page.getTotalPage());
    		qyxxList = qyxxService.selectPageList(page,record);
    	}
    	request.setAttribute("qyxxList", qyxxList);
    	request.setAttribute("page", page);
    	request.setAttribute("record", record);
    	
    	
    	return "/qyxx/zllist";
    }
    @RequestMapping(value = "/sendmobile",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String sendmobile(String remindCon,String phones,String ids,String title){
    	if(sendMail(remindCon, phones, ids, title)){
    		//成功
    		JSONObject result = new JSONObject();
    		result.element("code", "0");
    		result.element("msg", "发送成功！");
    		return result.toString();
    	}else{
    		//失败
    		JSONObject result = new JSONObject();
    		result.element("code", "1");
    		result.element("msg", "发送失败，请联系管理员！");
    		return result.toString();
    	}
    }
	private boolean sendMail(String remindCon, String phones, String ids,
			String title) {
		boolean b = false;
		Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", mailtransportprotocol);   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", mailsmtpauth);            // 需要请求认证
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);//关闭详细日志打印
        MimeMessage message = new MimeMessage(session);
        try {
			message.setFrom(new InternetAddress(myEmailAccount, sendname, "UTF-8"));
			String[] str = phones.split(",");
			String[] names = ids.split(",");
			for(int i=0;i<str.length;i++){
				message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(str[i], names[i], "UTF-8"));
			}
			message.setSubject(title, "UTF-8");
			message.setContent(remindCon, "text/html;charset=UTF-8");
			message.setSentDate(new Date());
			message.saveChanges();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Transport transport = session.getTransport();
			transport.connect(myEmailAccount, myEmailPassword);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			b=true;
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	@RequestMapping("/getList")
	public String getList(HttpServletRequest request, HttpServletResponse response,Page page,Qyxx qyxx){
		
		List<Qyxx> qyxxList = qyxxService.selectPageList(page,qyxx);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			qyxxList = qyxxService.selectPageList(page,qyxx);
		}
		request.setAttribute("qyxxList", qyxxList);
		request.setAttribute("page", page);
		request.setAttribute("record", qyxx);
		return "/qyxx/getList";
	}
	@RequestMapping("/zlgetList")
	public String getzlList(HttpServletRequest request, HttpServletResponse response,Page page,Qyxx qyxx){
		
		List<Qyxx> qyxxList = qyxxService.selectPageList(page,qyxx);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			qyxxList = qyxxService.selectPageList(page,qyxx);
		}
		request.setAttribute("qyxxList", qyxxList);
		request.setAttribute("page", page);
		request.setAttribute("record", qyxx);
		return "/qyxx/getzlList";
	}
    @RequestMapping("/add")
    public String add(HttpServletRequest request,Qyxx qyxx) {
    	if(qyxx.getId()!=null){
    		qyxx=qyxxService.selectByQyxx(qyxx).get(0);
    	}
        request.setAttribute("record", qyxx);
        return "/qyxx/add";
    }
    @RequestMapping("/fwjl")
    public String fwjl(HttpServletRequest request, Qyxx qyxx) {
    	if(qyxx.getId()!=null){
    		qyxx=qyxxService.selectByQyxx(qyxx).get(0);
    	}
    	Bfgl bfgl = new Bfgl();
    	bfgl.setQyxxId(qyxx.getId());
    	List<Bfgl> bfgls = qyxxService.selectBfgl(bfgl);
    	request.setAttribute("record", qyxx);
    	request.setAttribute("bfgls", bfgls);
    	return "/qyxx/fwjl";
    }
    
    @RequestMapping("/addFwjl")
    @ResponseBody
    public String addFwjl(HttpServletRequest request,Bfgl bfgl) {
    	if(bfgl.getId()!=null){
    		qyxxService.updateBfglByPrimaryKey(bfgl);
    	}else{
    		qyxxService.insertBfgl(bfgl);
    	}
    	JSONObject json = new JSONObject();
    	json.element("code", 0);
    	json.element("msg", "操作成功");
    	return json.toString();
    }
    
	@RequestMapping("/save")
    @ResponseBody
    public String save(HttpServletRequest request,Qyxx qyxx) {
    	if(qyxx.getId()!=null){
    		qyxxService.updateByPrimaryKeySelective(qyxx);
    	}else{
    		qyxxService.insertSelective(qyxx);
    	}
    	JSONObject json = new JSONObject();
    	json.element("code", 0);
    	json.element("msg", "操作成功");
    	return json.toString();
    }
	private Row getRow(XSSFSheet sheet, int rowIndex ){
		Row row = sheet.getRow(rowIndex);
		return row == null ? sheet.createRow(rowIndex) : row;
	}
	private Cell getCell(Row row, int colIndex ){
		Cell cell = row.getCell(colIndex);
		return cell == null ? row.createCell(colIndex) : cell;
	}
	
	private String mergeCell(XSSFSheet sheet, int startRow,int endRow,int startCol,int endCol){
		try{
			sheet.addMergedRegion(new CellRangeAddress(startRow,endRow,startCol,endCol));
			return null;
		}catch (IllegalStateException e) {
			return e.getMessage();
		}
	}
	
    @RequestMapping("/exportQyxx")
    @ResponseBody
    public String exportQyxx(HttpServletRequest request) {
    	String ids = request.getParameter("ids");
    	String basePath = request.getRealPath("/");
    	JSONObject json = new JSONObject();
    	if(ids != null && !"".equals(ids.trim())) {
    		
    		File dir = new File(basePath+"uploadFile/qyxxTemp");
    		if(dir.exists()) {
    			FileUtil.deleteFile(dir);
    		}
    		FileUtil.deleteFile(new File(basePath+"uploadFile/zip/院校相关信息.zip"));
    		dir.mkdirs();
    		
    		List<Qyxx> qyxxs = qyxxService.selectByIds(ids.split(","));
    		List<Bfgl> bfgls = null;
    		Bfgl bfgl = null;
    		int rowIndex = 0;
    		
			try {
				File totalFile = new File(basePath + "uploadFile/template/院校汇总表.xlsx");
				XSSFWorkbook totalBook = new XSSFWorkbook(totalFile);
				XSSFSheet totalSheet = totalBook.getSheetAt(0);
				Row row = null;
				Cell cell = null;
				int preRowIndex = 1,totalIndex = 1;
				String dq = null;
				for (Qyxx qyxx : qyxxs) {
					// ==> 汇总信息
					if(qyxx.getDq() == null){
						qyxx.setDq("");
					}
					row = getRow(totalSheet,totalIndex);
					if(!qyxx.getDq().equals(dq)) {
						dq = qyxx.getDq();
						if(totalIndex - preRowIndex > 1) {
							// 合并
							mergeCell(totalSheet, preRowIndex, totalIndex-1, 2, 2);
						}
						preRowIndex = totalIndex;
						getCell(row,2).setCellValue(qyxx.getDq());
					}
					getCell(row,0).setCellValue(totalIndex);
					getCell(row,1).setCellValue(totalIndex - preRowIndex + 1);
					getCell(row,3).setCellValue(qyxx.getMc());
					getCell(row,4).setCellValue(qyxx.getKcysj());
					getCell(row,5).setCellValue(qyxx.getRyzyrs());
					getCell(row,6).setCellValue(qyxx.getXyqsqk());
					totalIndex++;
					// <== 汇总信息
					
					// ==> 档案信息
					try {
						File file = new File(basePath + "uploadFile/template/院校档案.xlsx");
						XSSFWorkbook book = new XSSFWorkbook(file);
						XSSFSheet sheet = book.getSheetAt(0);
						rowIndex = 0;
						row = getRow(sheet, rowIndex++);
						getCell(row, 0).setCellValue(qyxx.getMc());
						row = getRow(sheet, rowIndex++);
						getCell(row, 1).setCellValue(qyxx.getXyqsqk());
						getCell(row, 3).setCellValue(qyxx.getYxtd());
						getCell(row, 6).setCellValue(qyxx.getQtjg());
						row = getRow(sheet, rowIndex++);
						getCell(row, 1).setCellValue(qyxx.getFzr());
						getCell(row, 3).setCellValue(qyxx.getLxfs());
						getCell(row, 6).setCellValue(qyxx.getYx());
						row = getRow(sheet, rowIndex++);
						getCell(row, 1).setCellValue(qyxx.getFzls2());
						getCell(row, 3).setCellValue(qyxx.getLxfs2());
						getCell(row, 6).setCellValue(qyxx.getYx2());
						row = getRow(sheet, rowIndex++);
						getCell(row, 1).setCellValue(qyxx.getFzls3());
						getCell(row, 3).setCellValue(qyxx.getLxfs3());
						getCell(row, 6).setCellValue(qyxx.getYx3());
						row = getRow(sheet, rowIndex++);
						getCell(row, 1).setCellValue(qyxx.getRyzyrs());
						getCell(row, 3).setCellValue(qyxx.getKcysj());
						bfgl = new Bfgl();
						bfgl.setQyxxId(qyxx.getId());
						bfgls = qyxxService.selectBfgl(bfgl);
						CellStyle style = null;
						if (bfgls != null && bfgls.size() > 0) {
							for (int i = 0; i < bfgls.size(); i++) {
								bfgl = bfgls.get(i);
								row = getRow(sheet, rowIndex++);
								if(style == null) {
									style = getCell(row, 0).getCellStyle();
								}
								getCell(row, 0).setCellValue("拜访时间");
								getCell(row, 0).setCellStyle(style);
								getCell(row, 1).setCellValue(bfgl.getBfsj());
								getCell(row, 1).setCellStyle(style);
								getCell(row, 2).setCellValue("是否宣讲");
								getCell(row, 2).setCellStyle(style);
								getCell(row, 3).setCellValue(bfgl.getSfxj());
								getCell(row, 3).setCellStyle(style);
								getCell(row, 4).setCellStyle(style);
								mergeCell(sheet, rowIndex-1, rowIndex-1, 3, 4);
								getCell(row, 5).setCellValue("听讲人数");
								getCell(row, 5).setCellStyle(style);
								getCell(row, 6).setCellValue(bfgl.getTjrs());
								getCell(row, 6).setCellStyle(style);
	
								row = getRow(sheet, rowIndex);
								getCell(row, 0).setCellStyle(style);
								getCell(row, 1).setCellStyle(style);
								getCell(row, 2).setCellStyle(style);
								getCell(row, 3).setCellStyle(style);
								getCell(row, 4).setCellStyle(style);
								getCell(row, 5).setCellStyle(style);
								getCell(row, 6).setCellStyle(style);
								mergeCell(sheet, rowIndex, rowIndex, 0, 6);
								rowIndex++;
								getCell(row, 0).setCellValue("重要事件：" + bfgl.getZysj());

	
								row = getRow(sheet, rowIndex);
								getCell(row, 0).setCellStyle(style);
								getCell(row, 1).setCellStyle(style);
								getCell(row, 2).setCellStyle(style);
								getCell(row, 3).setCellStyle(style);
								getCell(row, 4).setCellStyle(style);
								getCell(row, 5).setCellStyle(style);
								getCell(row, 6).setCellStyle(style);
								mergeCell(sheet, rowIndex, rowIndex, 0, 6);
								rowIndex++;
								getCell(row, 0).setCellValue("详细情况：" + bfgl.getXxqk());

							}
						}
						// TODO: 保存档案信息
						book.write(new FileOutputStream(basePath + "uploadFile/qyxxTemp/"+qyxx.getId()+qyxx.getMc()+"-院校档案.xlsx"));
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					// <== 档案信息
				}
				if(totalIndex - preRowIndex > 1 ) {
					// 合并
					mergeCell(totalSheet, preRowIndex, totalIndex-1, 2, 2);
				}
				// TODO: 保存汇总信息
				totalBook.write(new FileOutputStream(basePath + "uploadFile/qyxxTemp/院校汇总表.xlsx"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if(dir.exists()) {
				try {
					FileUtil.zip(dir.getAbsolutePath(), basePath+"uploadFile/zip", "院校相关信息.zip");
					json.element("fileName", "院校相关信息.zip");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
    	}
    	json.element("code", 0);
    	json.element("msg", "导出成功");
    	return json.toString();
    }
    @RequestMapping("/expword")
    @ResponseBody
    public String expword(HttpServletRequest request,Qyxx qyxx) {
    	JSONObject json = new JSONObject();
    	if(qyxx.getId()!=null){
    		//查询企业信息
    		qyxx = qyxxService.selectByQyxx(qyxx).get(0);
    		//查询该学校的学生信息
    		Xsxx xsxx = new Xsxx();
    		xsxx.setXx(qyxx.getMc());
    		xsxx.setSta("2");
    		// 当前季度
    		Object jdId = request.getSession().getAttribute("jdId");
    		if(jdId != null){
    			xsxx.setJd(jdId.toString());
    		}
    		List<Xsxx> stulist = xsxxService.selectByXsxx(xsxx);//学生List
    		//获取企业数
    		List<Xsxx> qylist = xsxxService.selectCountByQyxx(xsxx);
    		int qynum = qylist.size();
    		Map<String, String> map = new HashMap<String, String>();
			map.put("${qynum}", qynum+"");//企业数
			map.put("${qynumaddone}", qynum+1+"");//企业数+1
			map.put("${xxmc-z}", qyxx.getMc());//学校名称-中文
			map.put("${xfnr-z-1}", qyxx.getByzd4().split("\n").length>=1?qyxx.getByzd4().split("\n")[0]:"");//学分取得内容-中文1
			map.put("${xfnr-z-2}", qyxx.getByzd4().split("\n").length>=2?qyxx.getByzd4().split("\n")[1]:"");//学分取得内容-中文2
			map.put("${xfnr-z-3}", qyxx.getByzd4().split("\n").length>=3?qyxx.getByzd4().split("\n")[2]:"");//学分取得内容-中文3
			map.put("${xfnr-z-4}", qyxx.getByzd4().split("\n").length>=4?qyxx.getByzd4().split("\n")[3]:"");//学分取得内容-中文4
			map.put("${xfnr-z-5}", qyxx.getByzd4().split("\n").length>=5?qyxx.getByzd4().split("\n")[4]:"");//学分取得内容-中文5
			map.put("${xfnr-z-6}", qyxx.getByzd4().split("\n").length>=6?qyxx.getByzd4().split("\n")[5]:"");//学分取得内容-中文6
			map.put("${xfnr-z-7}", qyxx.getByzd4().split("\n").length>=7?qyxx.getByzd4().split("\n")[6]:"");//学分取得内容-中文7
			map.put("${xfnr-z-8}", qyxx.getByzd4().split("\n").length>=8?qyxx.getByzd4().split("\n")[7]:"");//学分取得内容-中文8
/*			map.put("${xfnr-z-9}", qyxx.getByzd4().split("\n").length>=9?qyxx.getByzd4().split("\n")[8]:"");//学分取得内容-中文9
			map.put("${xfnr-z-10}", qyxx.getByzd4().split("\n").length>=10?qyxx.getByzd4().split("\n")[9]:"");//学分取得内容-中文10
			map.put("${xfnr-z-11}", qyxx.getByzd4().split("\n").length>=11?qyxx.getByzd4().split("\n")[10]:"");//学分取得内容-中文11
			map.put("${xfnr-z-12}", qyxx.getByzd4().split("\n").length>=12?qyxx.getByzd4().split("\n")[11]:"");//学分取得内容-中文12
*/			
			map.put("${xfnr-r-1}", qyxx.getByzd5().split("\n").length>=1?qyxx.getByzd5().split("\n")[0]:"");//学分取得内容-日语1
			map.put("${xfnr-r-2}", qyxx.getByzd5().split("\n").length>=2?qyxx.getByzd5().split("\n")[1]:"");//学分取得内容-日语2
			map.put("${xfnr-r-3}", qyxx.getByzd5().split("\n").length>=3?qyxx.getByzd5().split("\n")[2]:"");//学分取得内容-日语3
			map.put("${xfnr-r-4}", qyxx.getByzd5().split("\n").length>=4?qyxx.getByzd5().split("\n")[3]:"");//学分取得内容-日语4
			map.put("${xfnr-r-5}", qyxx.getByzd5().split("\n").length>=5?qyxx.getByzd5().split("\n")[4]:"");//学分取得内容-日语5
			map.put("${xfnr-r-6}", qyxx.getByzd5().split("\n").length>=6?qyxx.getByzd5().split("\n")[5]:"");//学分取得内容-日语6
			map.put("${xfnr-r-7}", qyxx.getByzd5().split("\n").length>=7?qyxx.getByzd5().split("\n")[6]:"");//学分取得内容-日语7
			map.put("${xfnr-r-8}", qyxx.getByzd5().split("\n").length>=8?qyxx.getByzd5().split("\n")[7]:"");//学分取得内容-日语8
/*			map.put("${xfnr-r-9}", qyxx.getByzd5().split("\n").length>=9?qyxx.getByzd5().split("\n")[8]:"");//学分取得内容-日语9
			map.put("${xfnr-r-10}", qyxx.getByzd5().split("\n").length>=10?qyxx.getByzd5().split("\n")[9]:"");//学分取得内容-日语10
			map.put("${xfnr-r-11}", qyxx.getByzd5().split("\n").length>=11?qyxx.getByzd5().split("\n")[10]:"");//学分取得内容-日语11
			map.put("${xfnr-r-12}", qyxx.getByzd5().split("\n").length>=12?qyxx.getByzd5().split("\n")[11]:"");//学分取得内容-日语12
*/			
			map.put("${dxjj-z-1}", qyxx.getByzd8().split("\n").length>=1?qyxx.getByzd8().split("\n")[0]:"");//大学简介-中文1
			map.put("${dxjj-z-2}", qyxx.getByzd8().split("\n").length>=2?qyxx.getByzd8().split("\n")[1]:"");//大学简介-中文2
			map.put("${dxjj-z-3}", qyxx.getByzd8().split("\n").length>=3?qyxx.getByzd8().split("\n")[2]:"");//大学简介-中文3
			map.put("${dxjj-z-4}", qyxx.getByzd8().split("\n").length>=4?qyxx.getByzd8().split("\n")[3]:"");//大学简介-中文4
			map.put("${dxjj-r-1}", qyxx.getByzd9().split("\n").length>=1?qyxx.getByzd9().split("\n")[0]:"");//大学简介-日语1
			map.put("${dxjj-r-2}", qyxx.getByzd9().split("\n").length>=2?qyxx.getByzd9().split("\n")[1]:"");//大学简介-日语2
			map.put("${dxjj-r-3}", qyxx.getByzd9().split("\n").length>=3?qyxx.getByzd9().split("\n")[2]:"");//大学简介-日语3
			map.put("${dxjj-r-4}", qyxx.getByzd9().split("\n").length>=4?qyxx.getByzd9().split("\n")[3]:"");//大学简介-日语4
			map.put("${xxmc-r}", qyxx.getRymc());//学校名称-日语
			map.put("${xxcl-ztrq}", qyxx.getByzd1());//学校材料-整体日期
			map.put("${xxcl-qzrq}", qyxx.getByzd3());//学校材料-签证日期
			map.put("${xxcl-msrq}", qyxx.getByzd2());//学校材料-免税日期
			map.put("${xxcl-xscfyf}",qyxx.getXscfyf());
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy年M月d日");
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日");
			try {
				Date date = format2.parse(qyxx.getByzd1());
				map.put("${ztrq}", format1.format(date));
				date = format2.parse(qyxx.getByzd2());
				map.put("${msrq}", format1.format(date));
				date = format2.parse(qyxx.getByzd3());
				map.put("${qzrq}", format1.format(date));
				map.put("${xscfyf}", qyxx.getXscfyf());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String basePath = request.getRealPath("/");
			File file = new File(basePath+"uploadFile/doc");
			deleteFile(file);
			if(!file.exists()){file.mkdirs();}
    		generateContractFile(basePath, "0-材料说明及清单.doc", map,"0-材料说明及清单.doc");
    		generateContractFile(basePath, "1-院校简介手册（原件）.doc", map,"1-院校简介手册（原件）.doc");
    		generateContractFile(basePath, "2-湖南农业大学院校简介.docx", map,"2-"+qyxx.getMc()+"院校简介.docx");
    		generateContractFile(basePath, "3-2016年毕业生就职情况.doc", map,"3-2016年毕业生就职情况.doc");
    		//循环学生信息
    		for(int i=0;i<stulist.size();i++){
    			Xsxx item = stulist.get(i);
    			map.put("${xsxm}", item.getXm());
    			map.put("${rwxm}", item.getRwxm());
    			map.put("${ywxm}", item.getYwxm());
    			map.put("${xn}", SfyjsUtils.key2Value(item.getSfyjs(), item.getNj()));
    			map.put("${rxnf}", item.getRxnf());
    			map.put("${xz}", item.getXz());
    			map.put("${cjxm}", item.getCjxm());
    			map.put("${qymc}", item.getByzd2());
    			map.put("${bysj}", (Integer.parseInt(item.getRxnf().substring(0,item.getRxnf().length() - 1))+Integer.parseInt(item.getXz()))+"");
    			generateContractFile(basePath, "4-报批-在学证明及推荐状（三份在学证明文字一样，仅落款日期不同）.doc", map,"待合并报批"+i+".doc");
    			generateContractFile(basePath, "4-免税-在学证明及推荐状（三份在学证明文字一样，仅落款日期不同）.doc", map,"待合并免税"+i+".doc");
    			generateContractFile(basePath, "4-签证-在学证明及推荐状（三份在学证明文字一样，仅落款日期不同）.doc", map,"待合并签证"+i+".doc");
/*    			generateContractFile(basePath, "4-报批-在学证明及推荐状（三份在学证明文字一样，仅落款日期不同）.doc", map,"4-"+item.getXm()+"-报批-在学证明及推荐状（三份在学证明文字一样，仅落款日期不同）.doc");
    			generateContractFile(basePath, "4-免税-在学证明及推荐状（三份在学证明文字一样，仅落款日期不同）.doc", map,"4-"+item.getXm()+"-免税-在学证明及推荐状（三份在学证明文字一样，仅落款日期不同）.doc");
    			generateContractFile(basePath, "4-签证-在学证明及推荐状（三份在学证明文字一样，仅落款日期不同）.doc", map,"4-"+item.getXm()+"-签证-在学证明及推荐状（三份在学证明文字一样，仅落款日期不同）.doc");
*/    			generateContractFile(basePath, "5-学分取得证明.doc", map,"5-"+item.getXm()+"-学分取得证明.doc");
    		}
    		//合并word
    		/*merge(stulist, basePath,"报批");
    		merge(stulist, basePath,"免税");
    		merge(stulist, basePath,"签证");*/
    		//删除无关文件
    		
    		
    		//循环企业信息
    		for(Xsxx item : qylist){
    			if(item == null) continue;
    			Qyxx qy = new Qyxx();
    			qy.setMc(item.getByzd2());
    			List<Qyxx> qytemplist = qyxxService.selectByQyxx(qy);
    			if(qytemplist==null||qytemplist.size()==0){
    				json.element("code", 1);
    	    		json.element("msg", "("+item.getByzd2()+")数据库中无此企业信息");
    	    		return json.toString();
    			}
    			qy = qytemplist.get(0);//根据企业名称查询全部企业信息
    			map.put("${sg}", qy.getSg());//时给
    			map.put("${qymc}", qy.getMc());//企业名称
    			generateContractFile(basePath, "6-产学协议ANA万座.doc", map,"6-产学协议"+qy.getMc()+".doc");
    		}
    		try {
				FileUtil.zip(basePath+"uploadFile\\doc", basePath+"uploadFile\\zip", "文件.zip");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		json.element("code", 0);
    		json.element("msg", "文件生成成功");
    		json.element("file", "文件生成成功");
    	}else{
    		json.element("code", 1);
    		json.element("msg", "无此企业信息");
    	}
    	return json.toString();
    }
	private String merge(List<Xsxx> stulist, String basePath,String targetName) {
		FileInputStream in = null;
		HWPFDocument hdt = null;
		try {
			in = new FileInputStream(basePath+"待合并"+targetName+"0.doc");
			hdt = new HWPFDocument(in);
			// 读取word文本内容
			Range range = hdt.getRange();
			for(int i=1;i<stulist.size();i++){
				//合并报批
				range.insertAfter(HFWordUtil.merge(basePath+"待合并"+targetName+i+".doc"));
			}
			// 输出文件
			ByteArrayOutputStream ostream = new ByteArrayOutputStream();
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(basePath + "4-"+targetName+"-在学证明及推荐状（三份在学证明文字一样，仅落款日期不同）.doc", true);
				hdt.write(ostream);
				// 输出字节流
				out.write(ostream.toByteArray());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					out.close();
					ostream.close();
					//hdt.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//合并免税
			//合并签证
			in.close();
			in=null;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
				in=null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "";
	}
    //递归删除文件夹
    private void deleteFile(File file) {
     if (file.exists()) {//判断文件是否存在
      if (file.isFile()) {//判断是否是文件
       file.delete();//删除文件 
      } else if (file.isDirectory()) {//否则如果它是一个目录
       File[] files = file.listFiles();//声明目录下所有的文件 files[];
       for (int i = 0;i < files.length;i ++) {//遍历目录下所有的文件
        this.deleteFile(files[i]);//把每个文件用这个方法进行迭代
       }
       file.delete();//删除文件夹
      }
     } else {
      System.out.println("所删除的文件不存在");
     }
    }
    @RequestMapping("update")
    @ResponseBody
    public int update(@RequestBody Qyxx qyxx) {
        
        return qyxxService.updateByPrimaryKeySelective(qyxx);
    }
	private String copyFile2Temp(String basePath,String template,String newName){
		Long curTime = System.currentTimeMillis();
		try {
			FileInputStream fis = new FileInputStream(basePath+"uploadFile/template/"+template);
			FileOutputStream fos = new FileOutputStream(basePath+"uploadFile/temp/"+curTime+newName);
	        byte[] bs = new byte[1024];
	        int len = -1;
	        while ((len = fis.read(bs)) != -1) {
	            fos.write(bs, 0, len);
	        }
	        fos.close();
	        fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return curTime+newName;
	}
	private String generateContractFile(String basePath,String template,Map<String, String> map,String newName){
		String contractFile = "";
		//String tempName = copyFile2Temp(basePath, template,newName);
		File realfile=new File(basePath+"uploadFile/template/"+template);
		if(realfile.exists()){
			if(".doc".equals(template.substring(template.indexOf("."),template.length()))){
				contractFile = HFWordUtil.generateWord2003(realfile, basePath+"/uploadFile/doc/", map,newName);
			}else if(".docx".equals(template.substring(template.indexOf("."),template.length()))){
				contractFile = HFWordUtil.generateWord2007(realfile, basePath+"/uploadFile/doc/", map,newName);
			}
		}
		return contractFile;
	}
    @RequestMapping("delete")
    @ResponseBody
    public String delete(Integer id) {
        
    	qyxxService.deleteByPrimaryKey(id);
    	JSONObject json = new JSONObject();
    	json.element("code", 0);
    	json.element("msg", "操作成功");
    	return json.toString();
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
					String path = request.getSession().getServletContext().getRealPath("/")+"uploadFile/" + fileName;
					File localFile = new File(path);
					try {
						file.transferTo(localFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
					filename.element("code", 0);
					filename.element("msg", "上传成功");
					JSONObject jsondata = new JSONObject();
					String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
					jsondata.element("src", basePath+"uploadFile/"+fileName);
					filename.element("data", jsondata);
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
}