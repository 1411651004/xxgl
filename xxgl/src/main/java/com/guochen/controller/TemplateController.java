package com.guochen.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.guochen.model.TBuyer;
import com.guochen.model.TCode;
import com.guochen.model.TCom;
import com.guochen.model.TOffer;
import com.guochen.model.TProject;
import com.guochen.model.TTemplate;
import com.guochen.page.Page;
import com.guochen.service.ComService;
import com.guochen.service.OfferService;
import com.guochen.service.ProjectService;
import com.guochen.service.TemplateService;
import com.guochen.utils.CommonUtils;
import com.guochen.utils.DocConverter;
import com.guochen.utils.FileUtil;

@Controller
@RequestMapping("/template")
public class TemplateController {
	private static Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	private TemplateService templateservice;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,TTemplate template){
		List<TTemplate> templateList = templateservice.selectPageList(page,null);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			templateList = templateservice.selectPageList(page,null);
		}
		request.setAttribute("templateList", templateList);
		request.setAttribute("page", page);
		return "/template/list";
	}

	@RequestMapping("/getList")
	public String getList(HttpServletRequest request, HttpServletResponse response,Page page,TTemplate template){
		
		List<TTemplate> templateList = templateservice.selectPageList(page,template);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			templateList = templateservice.selectPageList(page,template);
		}
		request.setAttribute("templateList", templateList);
		request.setAttribute("page", page);
		return "/template/getList";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public int save(TTemplate template){
		if(template.getId()!=null){
			return templateservice.updateByPrimaryKey(template);
		}else{
			return templateservice.save(template);
		}
	}
	@RequestMapping("/add")
	public String addView(){
		return "/template/add";
	}
	@RequestMapping(value="/del",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String del(int id){
		
		String data="";
		if(id==0){
			data= "请选择有效的记录";
		}
		int i = templateservice.deleteByPrimaryKey(id);
		if(i==1){
			data= "删除成功";
		}else{
			data= "删除失败";
		}
		return data;
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
    public String  fileUpload2(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String fileName = file.getFileItem().getName();
		String fileType = fileName.substring(fileName.indexOf("."),fileName.length());
		String newFile = System.currentTimeMillis()+"";
		File realfile=new File(request.getRealPath("/")+"uploadFile/contract/template/"+newFile+fileType);
		file.transferTo(realfile);
		
        return newFile+""; 
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
                    String fileType = myFileName.substring(myFileName.indexOf("."),myFileName.length());
            		String newFile = System.currentTimeMillis()+"";
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if(myFileName.trim() !=""){
                        System.out.println(myFileName);
                        //重命名上传后的文件名
                        String fileName = newFile+fileType;
                        filename.element("filename", fileName);
                        //定义上传路径
//                        String path = "H:/" + fileName;
                        String path = request.getSession().getServletContext().getRealPath("/")+"uploadFile/contract/template/" +newFile+fileType;
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
	@RequestMapping("download")    
    public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response,String templateName) throws IOException {  
    	String fileType = templateName.substring(templateName.indexOf("."),templateName.length());
		File realfile=new File(request.getRealPath("/")+"uploadFile/contract/template/"+templateName);
		if(realfile.isFile() && realfile.exists()){
			FileUtil.download(realfile,response,null);
			return null;
		}else{
			return null;
		}
    }    
}
