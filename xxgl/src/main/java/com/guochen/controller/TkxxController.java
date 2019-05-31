package com.guochen.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guochen.model.Tkxx;
import com.guochen.model.Xsxx;
import com.guochen.page.Page;
import com.guochen.service.TkxxService;
import com.guochen.service.XsxxService;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="/tkxx")
public class TkxxController {
	@Autowired
    private XsxxService xsxxService;
    @Autowired
    private TkxxService tkxxService;
    @RequestMapping("/tkexp")
    @ResponseBody
    public String tkexp(HttpServletRequest request){
    	JSONObject result = new JSONObject();
    	result.element("status", "success");
    	String basePath = request.getRealPath("/");
    	
    	// 模板
    	String templetPath = basePath+"uploadFile/template/退款申请.xlsx";
    	File templetFile = new File(templetPath);
    	if(!templetFile.exists() || !templetFile.canRead()) {
    		// 模板不在
    		result.element("status", "fail");
    		result.element("msg", "退款模板缺失，请联系管理员。");
    	}
    	
    	// 目标文件
    	String desPath = basePath+"uploadFile/tkxx/";
    	File desFile = new File(desPath);
    	if(!desFile.exists()){
    		desFile.mkdirs();
    	}
    	Date date = new Date();
    	String desFileName = "退款申请"+ date.getYear() +"-"+(date.getMonth()+1)+".xlsx";
    	desPath += desFileName;
    	desFile = new File(desPath);
    	result.element("fileName", desFileName);
    	
    	// 输入数据
    	List<Tkxx> tkxxs = tkxxService.selectAll();
    	try {
    		XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(templetFile));
    		XSSFSheet sheet = book.getSheetAt(0);
			Tkxx tkxx = null;
			Row row = null;
			int startRow = 3;
			for(int i = 0; i<tkxxs.size(); i++) {
				tkxx = tkxxs.get(i);
				row = sheet.getRow(i+startRow);
				row.getCell(1).setCellValue(i+1);
				row.getCell(2).setCellValue(tkxx.getXm());
				row.getCell(3).setCellValue(tkxx.getByzd1());
				row.getCell(4).setCellValue(tkxx.getTkyh());
				row.getCell(5).setCellValue(tkxx.getFhxx());
				row.getCell(6).setCellValue(tkxx.getTkzh());
				row.getCell(7).setCellValue(tkxx.getHm());
				row.getCell(8).setCellValue(tkxx.getJe());
			}
			book.write(new FileOutputStream(desFile));
		} catch (IOException e) {
			result.element("status", "error");
			result.element("msg", "生成文件失败。");
		}
    	
    	return result.toString();
    }
    
    @RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,Tkxx record){
		List<Tkxx> tkxxList = tkxxService.selectPageList(page,null);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			tkxxList = tkxxService.selectPageList(page,null);
		}
		request.setAttribute("tkxxList", tkxxList);
		request.setAttribute("page", page);
		
		
		return "/tkxx/list";
	}
	@RequestMapping("/getList")
	public String getList(HttpServletRequest request, HttpServletResponse response,Page page,Tkxx tkxx){
		
		List<Tkxx> tkxxList = tkxxService.selectPageList(page,tkxx);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			tkxxList = tkxxService.selectPageList(page,tkxx);
		}
		request.setAttribute("tkxxList", tkxxList);
		request.setAttribute("page", page);
		return "/tkxx/getList";
	}
    @RequestMapping("tksq")
    @ResponseBody
    public String tksq(Tkxx tkxx) {
    	JSONObject result = new JSONObject();
    	result.element("code", "2");
        //验证是否有该身份证
    	Xsxx xsxx = new Xsxx();
    	xsxx.setSfz(tkxx.getSfz());
    	List<Xsxx> list = xsxxService.selectByXsxx(xsxx);
    	if(list!=null&&list.get(0)!=null){
    		xsxx=list.get(0);
    		if(xsxx.getKftk().equals("0")){
    			Tkxx tk = new Tkxx();
    			tkxx.setSfz(tkxx.getSfz());
    			List<Tkxx> tklist = tkxxService.selectByTkxx(tkxx);
    			if(tklist!=null&&tklist.size()==0){
    				//把退款申请数据存入数据库
    				tkxxService.insertSelective(tkxx);
		    		xsxx.setKftk("2");
		    		xsxxService.updateByIdSelective(xsxx);
    				result.element("code", "1");
    			}
    		}
    	}
        return result.toString();
    }
    
    @RequestMapping("update")
    @ResponseBody
    public int update(Tkxx tkxx) {
        
        return tkxxService.updateByPrimaryKeySelective(tkxx);
    }
    
    @RequestMapping("delete")
    @ResponseBody
    public int delete(@RequestBody Integer id) {
        
        return tkxxService.deleteByPrimaryKey(id);
    }
    
}