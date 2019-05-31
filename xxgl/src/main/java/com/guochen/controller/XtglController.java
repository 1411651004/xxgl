package com.guochen.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.guochen.model.Xtgl;
import com.guochen.page.Page;
import com.guochen.service.XtglService;
import com.guochen.utils.FileUtil;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="/xtgl")
public class XtglController {
    
    @Autowired
    private XtglService xtglService;
    
    /**
     * 获取季度列表
     * @param request
     * @param response
     * @param page
     * @param record
     * @return
     */
    @RequestMapping("/getQuarters")
    @ResponseBody
    public List<Xtgl> getAllQuarters(HttpServletRequest request, HttpServletResponse response){
    	return xtglService.getAllQuarters();
    }
    
    /**
     * 设置当前季度
     * @param request
     * @param response
     * @return
     */
	@RequestMapping("/setQuarter")
	@ResponseBody
	public String setQuarter(HttpServletRequest request, HttpServletResponse response){
    	String result = null;
    	try{
	    	String id = request.getParameter("id");
	    	String codevalue = request.getParameter("codevalue");
	    	
	    	Xtgl xtgl = new Xtgl();
	    	xtgl.setId(Integer.parseInt(id));
	    	xtgl.setCode("jd");
	    	xtgl.setCodevalue(codevalue);
	    	request.getSession().setAttribute("jd", xtgl);
	    	request.getSession().setAttribute("jdId", xtgl.getId());
	    	result = "success";
    	} catch (Exception e) {
    		result = "fail";
		}
    	return result;
    }
    
    
    /**
     * 根据名字查询季度信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getQuarterByName")
    @ResponseBody
    public List<Xtgl> getQuarterByName(HttpServletRequest request, HttpServletResponse response){
    	String codeValue = request.getParameter("codevalue");
    	return xtglService.getQuarterByName(codeValue);
    }
    
    /**
     * 删除季度
     * @param request
     * @return
     */
    @RequestMapping("/deleteById")
    @ResponseBody
    public int deleteById(HttpServletRequest request) {
    	String idStr = request.getParameter("id");
    	int id = Integer.parseInt(idStr);
    	return xtglService.deleteByPrimaryKey(id);
    }
    
    /**
     * 新增季度
     * @param request
     * @return
     */
    @RequestMapping("/addQuarter")
    @ResponseBody
    public String addQuarter(HttpServletRequest request) {
    	String codevalue = request.getParameter("codevalue");
    	Xtgl xtgl = new Xtgl();
    	xtgl.setCodevalue(codevalue);
    	xtgl.setCode("jd");
    	List<Xtgl> list = xtglService.selectByXtgl(xtgl);
    	if(list == null || list.size()==0){
    		return String.valueOf(xtglService.insertSelective(xtgl));
    	} else {
    		return "repeat";
    	}
    }
    
    /**
     * 跳转到季度管理页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/jdgl")
    public String quartersManage(HttpServletRequest request, HttpServletResponse response){
    	return "/xtgl/jdgl";
    }
    
    @RequestMapping("/xtgl")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,Xtgl record){
		List<Xtgl> xtglList = xtglService.selectPageList(page,null);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			xtglList = xtglService.selectPageList(page,null);
		}
		request.setAttribute("tksq", xtglList.get(0).getCodevalue());
		request.setAttribute("page", page);
		
		
		return "/xtgl/xtgl";
	}
    @RequestMapping("/dxnr")
    public String dxnr(HttpServletRequest request, HttpServletResponse response,Page page,Xtgl record){
    	List<Xtgl> xtglList = xtglService.selectPageList(page,null);
    	if(page.getPlainPageNum()>page.getTotalPage()){
    		page.setPageNum(page.getTotalPage());
    		xtglList = xtglService.selectPageList(page,null);
    	}
    	request.setAttribute("dxnr", xtglList.get(1).getCodevalue());
    	request.setAttribute("page", page);
    	
    	
    	return "/xtgl/dxnr";
    }
    @RequestMapping("/drhz")
    public String drhz(HttpServletRequest request, HttpServletResponse response,Page page,Xtgl record){
    	return "/xtgl/drhz";
    }
    @RequestMapping("/schz")
    @ResponseBody
    public String schz(HttpServletRequest request, HttpServletResponse response) {
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
				try {
					myFileName = new String(myFileName.getBytes("ISO-8859-1"),"UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//如果名称不为“”,说明该文件存在，否则说明该文件不存在
				if(myFileName.trim() !=""){
					//重命名上传后的文件名
					String fileName = myFileName;
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
					FileUtil.upzipFile(localFile, request.getSession().getServletContext().getRealPath("/")+"uploadFile/hzzp/");
					filename.element("code", 0);
					filename.element("msg", "上传成功");
				}
			}
		}
		
		return filename.toString();

	}
	@RequestMapping("/getList")
	public String getList(HttpServletRequest request, HttpServletResponse response,Page page,Xtgl xtgl){
		
		List<Xtgl> xtglList = xtglService.selectPageList(page,xtgl);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			xtglList = xtglService.selectPageList(page,xtgl);
		}
		request.setAttribute("xtglList", xtglList);
		request.setAttribute("page", page);
		return "/xtgl/getList";
	}
    @RequestMapping("add")
    @ResponseBody
    public int add(@RequestBody Xtgl xtgl) {
        
        return xtglService.insertSelective(xtgl);
    }
    
    @RequestMapping("update")
    @ResponseBody
    public int update(Xtgl xtgl) {
        return xtglService.updateByPrimaryKeySelective(xtgl);
    }
    
    @RequestMapping("/delete")
    @ResponseBody
    public int delete(@RequestBody Integer id) {
        return xtglService.deleteByPrimaryKey(id);
    }
    
}