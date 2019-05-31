package com.guochen.controller;

import java.io.File;
import java.io.IOException;
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

import com.guochen.model.TFile;
import com.guochen.model.TFileModel;
import com.guochen.page.Page;
import com.guochen.service.FileService;
import com.guochen.utils.FileUtil;

@Controller
@RequestMapping("/down")
public class FileController {
	private static Logger log = Logger.getLogger(FileController.class);
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response,Page page,TFile file){
		file.setAlternateField1("1");
		List<TFile> fileList = fileService.selectPageList(page, file);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			fileList = fileService.selectPageList(page,file);
		}
		request.setAttribute("fileList", fileList);
		request.setAttribute("page", page);
		return "/file/list";
	}

	@RequestMapping("/getList")
	public String getList(HttpServletRequest request, HttpServletResponse response,Page page,TFile file){
		file.setAlternateField1("1");
		List<TFile> fileList = fileService.selectPageList(page, file);
		if(page.getPlainPageNum()>page.getTotalPage()){
			page.setPageNum(page.getTotalPage());
			fileList = fileService.selectPageList(page,file);
		}
		request.setAttribute("fileList", fileList);
		request.setAttribute("page", page);
		return "/file/getList";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public int save(HttpServletRequest request,String files1){
		String[] list = files1.split("\\|");
		if(list != null){
			for(String f : list){
				TFile file = new TFile();
				file.setAlternateField1("1");
				file.setFileName(f);
				file.setFilePath(f);
				file.setFileType(f.substring(f.indexOf("."),f.length()));
				fileService.add(file);
			}
		}
		return 0;
	}
	
	@RequestMapping("/fileUpload")
	@ResponseBody
    public Map<String, String> fileUpload(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String fileName = file.getFileItem().getName();
		fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
		String fileType = fileName.substring(fileName.indexOf("."),fileName.length());
		String newFile = System.currentTimeMillis()+"";
		File filePath = new File(request.getRealPath("/")+"uploadFile/file/");
		if(!filePath.exists()){
			filePath.mkdirs();
		}
		File realfile = new File(request.getRealPath("/")+"uploadFile/file/"+fileName);
		file.transferTo(realfile);

		Map<String, String> map = new HashMap<String, String>();
		map.put("fileName", fileName);
		map.put("filePath", newFile+fileType);
		map.put("fileType", fileName.substring(fileName.indexOf(".")+1,fileName.length()));
        return map; 
    }
	
	@RequestMapping("/fileUpload2json")
	@ResponseBody
	public String  fileUpload2json(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException,IOException {
			String fileName1 = "";
			String filePath1 = "";
			String fileType1 = "";
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
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if(myFileName.trim() !=""){
                        System.out.println(myFileName);
                        //重命名上传后的文件名
                        fileName1=myFileName;
                        filePath1=myFileName;
                        fileType1=fileName1.substring(fileName1.indexOf(".")+1,fileName1.length());
                        //定义上传路径
//                        String path = "H:/" + fileName;
                        String path = request.getSession().getServletContext().getRealPath("/")+"uploadFile/file/" +fileName1;
                        File localFile = new File(path);
                        try {
                            file.transferTo(localFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //写入数据库
                        TFile filedata=new TFile();
                        filedata.setAlternateField1("1");
                        filedata.setFileName(fileName1);
                        filedata.setFilePath(filePath1);
                        filedata.setFileType(fileType1);
                        fileService.add(filedata);
                        filename.element("code", 0);
                		filename.element("msg", "上传成功");
                		
                    }
                }
            }
            
        return filename.toString();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public int delete(Integer[] ids){
		fileService.deleteByIds(ids);
		return 0;
	}
	@RequestMapping("/del")
	@ResponseBody
	public int delete(Integer id){
		Integer[] ids = {id};
		fileService.deleteByIds(ids);
		return 0;
	}
	
	@RequestMapping("downloadproject")    
    public ResponseEntity<byte[]> downloadproject(HttpServletRequest request, HttpServletResponse response,String fileName) throws IOException {  
    	String s2 = new String(fileName.getBytes("UTF-8"),"UTF-8");
    	String fileType = fileName.substring(fileName.indexOf("."),fileName.length());
		File realfile=new File(request.getRealPath("/")+"uploadFile/project/"+fileName);
		if(realfile.isFile() && realfile.exists()){
			FileUtil.download(realfile,response,fileName.substring(0,fileName.length()-6));
			return null;
		}else{
			System.out.println(request.getRealPath("/")+"uploadFile/project/"+new String(fileName.getBytes("UTF-8"),"UTF-8")+"不是文件");
			return null;
		}
    }    
    
    @RequestMapping("downloadoffer")    
    public ResponseEntity<byte[]> downloadoffer(HttpServletRequest request, HttpServletResponse response,String fileName) throws IOException {  
    	String fileType = fileName.substring(fileName.indexOf("."),fileName.length());
		File realfile=new File(request.getRealPath("/")+"uploadFile/offer/"+fileName);
		if(realfile.isFile() && realfile.exists()){
			FileUtil.download(realfile,response,fileName.substring(0,fileName.length()-6));
			return null;
		}else{
			return null;
		}
    }    
    
    
    @RequestMapping("downloadcontractdoc")    
    public ResponseEntity<byte[]> downloadcontractdoc(HttpServletRequest request, HttpServletResponse response,String fileName) throws IOException {  
    	String fileType = fileName.substring(fileName.indexOf("."),fileName.length());
    	File realfile=new File(request.getRealPath("/")+"uploadFile/contract/doc/"+fileName);
    	if(realfile.isFile() && realfile.exists()){
    		FileUtil.download(realfile,response,null);
			return null;
    	}else{
    		return null;
    	}
    }    
    
    @RequestMapping("downloadcontractpdf")    
    public ResponseEntity<byte[]> downloadcontractpdf(HttpServletRequest request, HttpServletResponse response,String fileName) throws IOException {  
    	String fileType = fileName.substring(fileName.indexOf("."),fileName.length());
    	File realfile=new File(request.getRealPath("/")+"uploadFile/contract/pdf/"+fileName);
    	if(realfile.isFile() && realfile.exists()){
    		FileUtil.download(realfile,response,null);
			return null;
    	}else{
    		return null;
    	}
    }    
    
    @RequestMapping("downloadcontracttemplate")    
    public ResponseEntity<byte[]> downloadcontracttemplate(HttpServletRequest request, HttpServletResponse response,String fileName) throws IOException {  
    	String fileType = fileName.substring(fileName.indexOf("."),fileName.length());
    	File realfile=new File(request.getRealPath("/")+"uploadFile/contract/template/"+fileName);
    	if(realfile.isFile() && realfile.exists()){
    		FileUtil.download(realfile,response,null);
			return null;
    	}else{
    		return null;
    	}
    }    
    
    @RequestMapping("downloadcom")    
    public ResponseEntity<byte[]> downloadcom(HttpServletRequest request, HttpServletResponse response,String fileName) throws IOException {  
    	String fileType = fileName.substring(fileName.indexOf("."),fileName.length());
    	File realfile=new File(request.getRealPath("/")+"uploadFile/com/"+fileName);
    	if(realfile.isFile() && realfile.exists()){
    		FileUtil.download(realfile,response,null);
			return null;
    	}else{
    		return null;
    	}
    }    
}
