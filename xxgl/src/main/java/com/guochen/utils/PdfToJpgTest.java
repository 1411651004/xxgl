package com.guochen.utils;
import java.awt.Image;  
import java.awt.Rectangle;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FilenameFilter;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.io.RandomAccessFile;  
import java.nio.ByteBuffer;  
import java.nio.channels.FileChannel;  
import java.util.Arrays;  
import java.util.Comparator;  
import javax.imageio.*;  
import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGImageEncoder;  
import com.sun.pdfview.PDFFile;  
import com.sun.pdfview.PDFPage;  

public class PdfToJpgTest {
	
	public static int maxPage = 100;
	public static int qingxidu = 2;
	

	public static boolean pdf2Images(PDFFile pdfFile,String imageSavePath,String fileName) {  
        if(pdfFile == null ) { //待转换文档不存在，返回false.  
            return false;  
        }  
          
        //将转换后图片存放于path路径下  
  
        String path = imageSavePath + "\\";  
        File filePath = new File(path);  
        if(!filePath.exists()){ //判断以文件名命名的文件夹是否存在.  
            filePath.mkdirs();  
        }  
          
        //取得当前文件夹下的所有jpg格式的文件名.  
        String[] imageNames = filePath.list();
        if(imageNames.length == 0) {  //当前文件夹下没有文件.  
            //将pdf文档按页转为图片.  
            String imagePath = "";  
            try {  
                //对转换页数进行限制,最多只转换前maxPage页.  
                int pages = pdfFile.getNumPages();  
                if(pages > maxPage){  
                    pages = maxPage;  
                }  
                  
                for (int i = 1; i <= pages; i++) {  
                    // draw the page to an image  
                    PDFPage page = pdfFile.getPage(i);  
                    // get the width and height for the doc at the default zoom  
                    Rectangle rect = new Rectangle(0,   
                                                   0,   
                                                   (int) page.getBBox().getWidth(),   
                                                   (int) page.getBBox().getHeight());
                    int width = rect.width*2;
                    int height = rect.height*2;
                    // generate the image  
                    Image img = page.getImage(rect.width*qingxidu, rect.height*qingxidu, // width & height  
                                              rect, // clip rect  
                                              null, // null for the ImageObserver  
                                              true, // fill background with white  
                                              true // block until drawing is done  
                                              );  
                   
                    BufferedImage tag = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);  
                    tag.getGraphics().drawImage(img,0,0,width,height,null);  
                    imagePath = path + i + ".jpg";  
                    FileOutputStream out = new FileOutputStream(imagePath);  // 输出到文件流.  
                    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
                    encoder.encode(tag);        // JPEG编码.  
                    out.close();  
                }                 
            }catch (Exception ex) {  
                ex.printStackTrace();  
                return false;  
            }  
        }  
          
        //取得当前文件夹下的所有jpg格式的文件名.  
        imageNames = filePath.list();
        //对文件名排序.  
        Arrays.sort(imageNames);  
          
        //servletRequest.setAttribute("state", "s");  
        //servletRequest.setAttribute("fileName", fileName);  
        //servletRequest.setAttribute("imageNames", imageNames);  
        System.out.println("fileName:"+fileName);
        System.out.println("imageNames:"+imageNames);
          
        return true;  
    }  
      
    //图片后缀名过滤类  
      
    //图片jpg过滤器类  
    class ImageFilter implements FilenameFilter {  
         public boolean isImageFile(String fileName){  
              if(fileName.toLowerCase().endsWith("jpg")) {  
                  return true;  
              }else {  
                  return false;  
              }         
         }  
           
         public ImageFilter() {}  
           
         public boolean accept(File dir,String name){  
             return isImageFile(name);  
          }  
    }  
      
    //文件名称比较类  
    class FileNameComparator implements Comparator {  
        public final int compare(Object first, Object second) {  
           String[] fir = ((String)first).split("\\.");  
           String[] sec = ((String)second).split("\\.");  
             
           int firstPage = Integer.parseInt(fir[0]);  
           int secondPage = Integer.parseInt(sec[0]);  
           int diff = firstPage - secondPage;  
           if (diff > 0)  
            return 1;  
           if (diff < 0)  
            return -1;  
           else  
            return 0;  
        }  
    } 
    
    public static void main(String[] args) {
    	String filePath = "F:/abc.pdf";
    	String imageSavePath = "F:/pdf/";
    	PDFFile pdfFile = getPdfFile(filePath);
    	pdf2Images(pdfFile, imageSavePath, "abc");
	}
    
    
    
    
    /** 
     * PDF文档读取.  
     * @param filePath -- 待读取PDF文件的路径. 
     * @return null 或者 PDFFile instance. 
     */  
    public static PDFFile getPdfFile(String filePath) {  
        try {  
            //load a pdf file from byte buffer.  
            File file = new File(filePath);  
            RandomAccessFile raf = new RandomAccessFile(file, "r");  
            FileChannel channel = raf.getChannel();  
            System.out.println(channel.size());
            ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());  
            PDFFile pdfFile = new PDFFile(buf);  
  
            return pdfFile;  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
        return null;  
    }  
}