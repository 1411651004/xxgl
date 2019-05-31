package com.guochen.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class HFWordUtil {
	
	

	public static String merge(String filePath) {
		if(".doc".equals(filePath.substring(filePath.indexOf("."),filePath.length()))){
			FileInputStream in = null;
			HWPFDocument hdt = null;
			try {
				in = new FileInputStream(filePath);
				hdt = new HWPFDocument(in);
				// 读取word文本内容
				Range range = hdt.getRange();
				in.close();
				in=null;
				return range.text();
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
		}else if(".docx".equals(filePath.substring(filePath.indexOf("."),filePath.length()))){
			
		}
		return "";
	}
	public static String generateWord2003(File srcFile, String targetPath,
			Map<String, String> map,String newName) {
		FileInputStream in = null;
		HWPFDocument hdt = null;
		try {
			in = new FileInputStream(srcFile);
			hdt = new HWPFDocument(in);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 读取word文本内容
		Range range = hdt.getRange();
		// 替换文本内容
		for (Map.Entry<String, String> entry : map.entrySet()) {
			range.replaceText(entry.getKey(), entry.getValue()==null?"":entry.getValue());
		}
		// 输出文件
		ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		String fileName = srcFile.getName();
//		String newName = System.currentTimeMillis()
//				+ fileName.substring(fileName.indexOf("."), fileName.length());
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(targetPath + newName, true);
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
		return newName;
	}

	public static String generateWord2007(File srcFile, String targetPath,
			Map<String, String> map,String newName) {
		XWPFDocument doc = generateWord(map, srcFile);
		String fileName = srcFile.getName();
//		String newName = System.currentTimeMillis()
//				+ fileName.substring(fileName.indexOf("."), fileName.length());
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(targetPath + newName));
			doc.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				doc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return newName;
	}

	/**
	 * 根据指定的参数值、模板，生成 word 文档
	 * 
	 * @param param
	 *            需要替换的变量
	 * @param template
	 *            模板
	 */
	private static XWPFDocument generateWord(Map<String, String> param,
			File templateFile) {
		XWPFDocument doc = null;
		try {
			OPCPackage pack = POIXMLDocument.openPackage(templateFile
					.getAbsolutePath());
			doc = new XWPFDocument(pack);
			if (param != null && param.size() > 0) {
				// 处理段落
				List<XWPFParagraph> paragraphList = doc.getParagraphs();
				processParagraphs(paragraphList, param, doc);
				// 处理表格
				Iterator<XWPFTable> it = doc.getTablesIterator();
				while (it.hasNext()) {
					XWPFTable table = it.next();
					List<XWPFTableRow> rows = table.getRows();
					for (XWPFTableRow row : rows) {
						List<XWPFTableCell> cells = row.getTableCells();
						for (XWPFTableCell cell : cells) {
							List<XWPFParagraph> paragraphListTable = cell
									.getParagraphs();
							processParagraphs(paragraphListTable, param, doc);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	/**
	 * 处理段落
	 * 
	 * @param paragraphList
	 */
	public static void processParagraphs(List<XWPFParagraph> paragraphList,
			Map<String, String> param, XWPFDocument doc) {
		if (paragraphList != null && paragraphList.size() > 0) {
			for (XWPFParagraph paragraph : paragraphList) {
				List<XWPFRun> runs = paragraph.getRuns();
				String text = "";
				for (XWPFRun run : runs) {
					text += run.getText(0);
				}
				if (text != null) {
					boolean isSetText = false;
					for (Entry<String, String> entry : param.entrySet()) {
						String key = entry.getKey();
						if (text.indexOf(key) != -1) {
							isSetText = true;
							Object value = entry.getValue();
							if (value instanceof String) {// 文本替换
								text = text.replace(key, value.toString());
							}
						}
					}
					if (isSetText) {
						int flag = 1;
						for (XWPFRun run : runs) {
							if (flag-- == 1) {
								run.setText(text, 0);
							} else {
								run.setText("", 0);
							}
						}
					}
				}
			}
		}
	}

/*	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("${projectName}", "众筹“黑真猪”");
		map.put("${projectAmount}", "20000");
		map.put("${projectDestribution}", "中国石台");
		map.put("${projectStatus}", "待发布");
		File srcFile = new File("F:\\template.docx");
		String targetPath = "F:" + File.separator + "target" + File.separator;
		if (srcFile.isDirectory() || !srcFile.exists()) {
			System.out.println("文件不存在");
		} else {
			String fileName = srcFile.getName();
			String resultFileName = "";
			if (".doc".equals(fileName.substring(fileName.indexOf("."),
					fileName.length()))) {
				resultFileName = HFWordUtil.generateWord2003(srcFile,
						targetPath, map,"");
			} else if (".docx".equals(fileName.substring(fileName.indexOf("."),
					fileName.length()))) {
				resultFileName = HFWordUtil.generateWord2007(srcFile,
						targetPath, map);
			}
			System.out.println(targetPath + resultFileName);
		}

	}*/
}