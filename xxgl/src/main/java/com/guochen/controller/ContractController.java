package com.guochen.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guochen.model.TConapp;
import com.guochen.model.TContract;
import com.guochen.model.TNode;
import com.guochen.model.TOffer;
import com.guochen.model.TProject;
import com.guochen.model.TTemplate;
import com.guochen.service.ConAppService;
import com.guochen.service.ContractService;
import com.guochen.service.NodeService;
import com.guochen.service.OfferService;
import com.guochen.service.ProjectService;
import com.guochen.service.TemplateService;
import com.guochen.utils.CommonUtils;
import com.guochen.utils.DocConverter;
import com.guochen.utils.FileUtil;
import com.guochen.utils.HFWordUtil;

@Controller
@RequestMapping("/contract")
public class ContractController {
	private static Logger log = Logger.getLogger(ContractController.class);
	
	@Autowired
	private ContractService contractservice;
	@Autowired
	private ProjectService projectservice;
	@Autowired
	private OfferService offerservice;
	@Autowired
	private TemplateService templateservice;
	@Autowired
	private NodeService nodeservice;
	@Autowired
	private ConAppService conappservice;
	
	/*@RequestMapping("/save")
	@ResponseBody
	public int save(HttpServletRequest request, HttpServletResponse response,TContract contract,String bjxz){
		if(contract.getId()==null){
			TOffer offer = offerservice.selectByPrimeryKey(contract.getOfferId());
			contract.setProId(offer.getProId());
			contract.setUserId(offer.getUserId());
			contract.setAlternateField1(offer.getId()+"");
			contract.setAppStatus("0");
			contract.setCreateTime(CommonUtils.date2Str(new Date()));
			TTemplate template = templateservice.selectByPrimeryKey(contract.getTemplateId());
			contract.setProjectNo("GZHG-JY-"+contract.getJc()+"-"+CommonUtils.date2Strny(new Date())+"-"+CommonUtils.addZeroForNum(contractservice.selectCountByMouth()+"", 4));
			Map<String, String> map = new HashMap<String, String>();
			map.put("${buyerName}", CommonUtils.ybkg(offer.getProject().getBuyer().getBuyerName(),10));
			map.put("${projectNo}", contract.getProjectNo());
			//offer.getProject().getId()+""
			map.put("${comName}", CommonUtils.ybkg(offer.getCom()==null?"":offer.getCom().getComName(), 10));
			map.put("${signDate}", CommonUtils.date2Strnyr(new Date()));
			map.put("${tradeCon}", contract.getCon());
			map.put("${buyerAddr}", offer.getProject().getBuyer().getBuyerAddr());
			map.put("${comAddr}", offer.getCom()==null?"":offer.getCom().getComAddr());
			map.put("${buyerPhone}", offer.getProject().getBuyer().getBuyerPhone());
			map.put("${comPhone}", offer.getCom()==null?"":offer.getCom().getComPhone());
			map.put("${buyerBankAccount}", offer.getProject().getBuyer().getBuyerBankAccount());
			map.put("${combankAccount}", offer.getCom()==null?"":offer.getCom().getBankAccount());
			map.put("${buyerAccount}", offer.getProject().getBuyer().getBuyerAccount());
			map.put("${comaccount}", offer.getCom()==null?"":offer.getCom().getAccount());
			map.put("${buytaxno}", offer.getProject().getBuyer().getBuyerTaxreceNo());
			map.put("${comtaxno}", offer.getCom()==null?"":offer.getCom().getTaxreceNo());
			map.put("${deliveryTime}", contract.getDeliveryTime());
			String jg = "0";
			if(bjxz.equals("cash")){
				jg = offer.getCashOffer();
			}else if(bjxz.equals("acce")){
				jg = offer.getAcceOffer();
			}
			map.put("${deliveryFee}", CommonUtils.toBigAmount(Double.parseDouble(jg)*0.3));
			map.put("${contractSeme}", contract.getSeMe());
			String basePath = request.getRealPath("/")+"uploadFile/contract/";
			String contractFile = generateContractFile(basePath,template.getTemplatePath(),map);
			contract.setFilePath(contractFile);
			contractservice.save(contract);
			
			String tempName = contractFile.substring(0,contractFile.indexOf("."));
			//合同已生成，合同文件转换为PDF和图片格式
			try {
				DocConverter.doc2jpg(basePath+"doc/"+contractFile,
						basePath+"pdf/"+tempName+".pdf", 
						basePath+"image/"+tempName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//加载是否加急
			String isemergency = (String) request.getParameter("isemergency");
			TProject project = offer.getProject();
			if("1".equals(isemergency)){
				project.setAlternateField1("1");//加急
			}else{
				project.setAlternateField1("0");//普通
			}
			projectservice.updateByPrimaryKey(project);
		}else{
			TOffer offer = offerservice.selectByPrimeryKey(contract.getOfferId());
			contract=contractservice.selectByPrimeryKey(contract.getId());
			contract.setProId(offer.getProId());
			contract.setUserId(offer.getUserId());
			contract.setAlternateField1(offer.getId()+"");
			contract.setAppStatus("0");
			contract.setCreateTime(CommonUtils.date2Str(new Date()));
			TTemplate template = templateservice.selectByPrimeryKey(contract.getTemplateId());
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("${buyerName}", offer.getProject().getBuyer().getBuyerName());
			map.put("${projectNo}", contract.getProjectNo());
			System.out.println("-------------------------------已存入值"+contract.getProjectNo());
			map.put("${comName}", offer.getCom().getComName());
			map.put("${signDate}", CommonUtils.date2Strnyr(new Date()));
			map.put("${tradeCon}", contract.getCon());
			map.put("${buyerAddr}", offer.getProject().getBuyer().getBuyerAddr());
			map.put("${comAddr}", offer.getCom().getComAddr());
			map.put("${buyerPhone}", offer.getProject().getBuyer().getBuyerPhone());
			map.put("${comPhone}", offer.getCom().getComPhone());
			map.put("${buyerBankAccount}", offer.getProject().getBuyer().getBuyerBankAccount());
			map.put("${combankAccount}", offer.getCom().getBankAccount());
			map.put("${buyerAccount}", offer.getProject().getBuyer().getBuyerAccount());
			map.put("${comaccount}", offer.getCom().getAccount());
			map.put("${buytaxno}", offer.getProject().getBuyer().getBuyerTaxreceNo());
			map.put("${comtaxno}", offer.getCom().getTaxreceNo());
			map.put("${deliveryTime}", contract.getDeliveryTime());
			String jg = "0";
			if(bjxz.equals("cash")){
				jg = offer.getCashOffer();
			}else if(bjxz.equals("acce")){
				jg = offer.getAcceOffer();
			}
			map.put("${deliveryFee}", CommonUtils.toBigAmount(Double.parseDouble(jg)*0.3));
			map.put("${contractSeme}", contract.getSeMe());
			String basePath = request.getRealPath("/")+"uploadFile/contract/";
			String contractFile = generateContractFile(basePath,template.getTemplatePath(),map);
			contract.setFilePath(contractFile);
			contractservice.updateTContract(contract);
			
			String tempName = contractFile.substring(0,contractFile.indexOf("."));
			//合同已生成，合同文件转换为PDF和图片格式
			try {
				DocConverter.doc2jpg(basePath+"doc/"+contractFile,
						basePath+"pdf/"+tempName+".pdf", 
						basePath+"image/"+tempName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//加载是否加急
			String isemergency = (String) request.getParameter("isemergency");
			TProject project = offer.getProject();
			if("1".equals(isemergency)){
				project.setAlternateField1("1");//加急
			}else{
				project.setAlternateField1("0");//普通
			}
			projectservice.updateByPrimaryKey(project);
		}
		//合同生成，开始走审批流程
		TNode startNode = nodeservice.selectStartNode();
		TConapp conApp = new TConapp();
		conApp.setAppEx("提交");
		conApp.setAppStatus("1");
		conApp.setAppTime(CommonUtils.date2Str(new Date()));
		conApp.setContractId(contract.getId());
		conApp.setCreateTime(CommonUtils.date2Str(new Date()));
		conApp.setNodeId(startNode.getId());
		conappservice.save(conApp);
		
		TNode nextNode = nodeservice.selectByPrimeryKey(startNode.getRearNodeId());
		TConapp nextApp = new TConapp();
		nextApp.setAppEx("");
		nextApp.setAppStatus("0");
		nextApp.setAppTime("");
		nextApp.setContractId(contract.getId());
		nextApp.setCreateTime(CommonUtils.date2Str(new Date()));
		nextApp.setNodeId(nextNode.getId());
		conappservice.save(nextApp);
		
		
		
			//合同生成，继续走审批流程
			TConapp curapp = conappservice.selectCurrentByContractId(contract.getId());
			curapp.setAppStatus("1");
			curapp.setAppEx("业务经理重新提交");
			curapp.setAppTime(CommonUtils.date2Str(new Date()));
			conappservice.update(curapp);
			
			TNode nextNode = nodeservice.selectByPrimeryKey(curapp.getNode().getRearNodeId());
			TConapp nextApp = new TConapp();
			nextApp.setAppEx("");
			nextApp.setAppStatus("0");
			nextApp.setAppTime("");
			nextApp.setContractId(contract.getId());
			nextApp.setCreateTime(CommonUtils.date2Str(new Date()));
			nextApp.setNodeId(nextNode.getId());
			conappservice.save(nextApp);
			
			//加载是否加急
			String isemergency = (String) request.getAttribute("isemergency");
			TProject project = offer.getProject();
			if("1".equals(isemergency)){
				project.setAlternateField1("1");//加急
			}else{
				project.setAlternateField1("0");//普通
			}
			projectservice.updateByPrimaryKey(project);
		}
		
		return 1;
	}*/
	
	
/*	private String generateContractFile(String basePath,String template,Map<String, String> map){
		String contractFile = "";
		String tempName = copyFile2Temp(basePath, template);
		File realfile=new File(basePath+"temp/"+tempName);
		if(realfile.exists()){
			if(".doc".equals(template.substring(template.indexOf("."),template.length()))){
				contractFile = HFWordUtil.generateWord2003(realfile, basePath+"doc/", map);
			}else if(".docx".equals(template.substring(template.indexOf("."),template.length()))){
				contractFile = HFWordUtil.generateWord2007(realfile, basePath+"doc/", map);
			}
		}
		return contractFile;
	}*/
	
	private String copyFile2Temp(String basePath,String template){
		Long curTime = System.currentTimeMillis();
		try {
			FileInputStream fis = new FileInputStream(basePath+"template/"+template);
			FileOutputStream fos = new FileOutputStream(basePath+"temp/"+curTime+template);
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
        return curTime+template;
	}
	
	@RequestMapping("downloadContractFile")    
	public ResponseEntity<byte[]> downloadContractFile(HttpServletRequest request, HttpServletResponse response,String fileName) throws IOException {  
    	String fileType = fileName.substring(fileName.indexOf("."),fileName.length());
    	File realfile = null;
    	if(".doc".equals(fileType) || ".docx".equals(fileType)){
    		realfile=new File(request.getRealPath("/")+"uploadFile/contract/doc/"+fileName);
    	}else if(".pdf".equals(fileType)){
    		realfile=new File(request.getRealPath("/")+"uploadFile/contract/pdf/"+fileName);
    	}
		if(realfile!=null && realfile.isFile() && realfile.exists()){
			FileUtil.download(realfile,response,null);
			return null;
		}else{
			return null;
		}
    }    
	public HttpServletResponse download(File file, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(filename, "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

}
