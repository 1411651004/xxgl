package com.guochen.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.sun.pdfview.PDFFile;


public class DocConverter {
	private static final int environment = 1;// 环境 1：windows 2:linux  
    public static int maxPage = 100;//最大页数
	public static int qingxidu = 2;//清晰度
    
    
	public static void doc2jpg(String docFilePath,String pdfFilePath,String imagePath) throws Exception {  
    	StringBuilder allow = new StringBuilder(".doc.docx.ppt.pptx.xls.xlsx");
    	String subfix = docFilePath.substring(docFilePath.lastIndexOf("."));
    	if(allow.indexOf(subfix) == -1){
    		return;
    	}
    	String [] images = {};
    	File docFile = new File(docFilePath);
    	String docFileName = docFile.getName().substring(0,docFile.getName().indexOf("."));
        if (docFile.exists()) {
        	File pdfFile = new File(pdfFilePath);
            if (!pdfFile.exists()) {  
                OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);  
                try {
                    connection.connect();  
                    DocumentConverter converter = new OpenOfficeDocumentConverter(connection);  
                    converter.convert(docFile, pdfFile);  
                    connection.disconnect();  
                    System.out.println("****pdf转换成功，PDF输出：" + pdfFile.getPath()+ "****");  
                    //PDF转换成图片
                    PDFFile temp = PdfToJpgTest.getPdfFile(pdfFile.getAbsolutePath());
                    PdfToJpgTest.pdf2Images(temp, imagePath, docFileName);
                    File imagesFolder = new File(imagePath+docFileName);
                    if(imagesFolder.isDirectory()){
                    	images = imagesFolder.list();
                    }
                } catch (java.net.ConnectException e) {  
                    e.printStackTrace();  
                    System.out.println("****转换器异常，openoffice服务未启动！****");  
                    throw e;  
                } catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {  
                    e.printStackTrace();  
                    System.out.println("****转换器异常，读取转换文件失败****");  
                    throw e;
                } catch (Exception e) {  
                    e.printStackTrace();  
                    throw e;  
                }  
            }else {  
                System.out.println("文件已转换");
                File imagesFolder = new File(imagePath+docFileName);
                if(imagesFolder.isDirectory()){
                	images = imagesFolder.list();
                }
            }  
        } else {  
            System.out.println("源文件不存在");  
        }  
        
    }  
   
    public static void main(String[] args) {
    	
	}
    
}
