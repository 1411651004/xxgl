package com.guochen.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

	private static final String UNIT = "万千佰拾亿千佰拾万千佰拾元角分";
	private static final String DIGIT = "零壹贰叁肆伍陆柒捌玖";
	private static final double MAX_VALUE = 9999999999999.99D;

	/**
	 * 将日期按格式转换成字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Str(Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return fmt.format(date);
	}
	/**
	 * 将日期按格式转换成字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Strnyr(Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy年MM月dd日");
		return fmt.format(date);
	}
	public static String date2Strny(Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");
		return fmt.format(date);
	}
	
	public static String addZeroForNum(String str, int strLength) {
	    int strLen = str.length();
	    StringBuffer sb = null;
	     while (strLen < strLength) {
	           sb = new StringBuffer();
	           sb.append("0").append(str);// 左补0
	           str = sb.toString();
	           strLen = str.length();
	     }
	    return str;
	}
	/**
	 * 将字符串按照固定格式转换成日期
	 * 
	 * @param str
	 * @return
	 */
	public static Date str2Date(String str) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		try {
			d = fmt.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 比较两个字符串日期
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int dateCompare(String date1, String date2) {
		try {
			Date d1 = str2Date(date1);
			Date d2 = str2Date(date2);
			return d1.compareTo(d2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 计算两个字符串日期之间的小时分钟秒
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static String countTimeBetweenDays(String date1, String date2) {
		String retTime = "0小时0分钟0秒";
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1;
		try {
			d1 = fmt.parse(date1);
			Date d2 = fmt.parse(date2);
			Long spanTime = d2.getTime() - d1.getTime();
			int hour = (int) (spanTime / (1000 * 60 * 60));
			spanTime = spanTime - hour * 1000 * 60 * 60;
			int minute = (int) (spanTime / (1000 * 60));
			spanTime = spanTime - minute * 1000 * 60;
			int second = (int) (spanTime / 1000);
			retTime = hour + "小时" + minute + "分钟" + second + "秒";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return retTime;
	}

	/**
	 * 将数字金额转换为大写金额
	 * 
	 * @param v
	 * @return
	 */
	public static String toBigAmount(double v) {
		if (v < 0 || v > MAX_VALUE) {
			return "参数非法!";
		}
		long l = Math.round(v * 100);
		if (l == 0) {
			return "零元整";
		}
		String strValue = l + "";
		// i用来控制数
		int i = 0;
		// j用来控制单位
		int j = UNIT.length() - strValue.length();
		String rs = "";
		boolean isZero = false;
		for (; i < strValue.length(); i++, j++) {
			char ch = strValue.charAt(i);
			if (ch == '0') {
				isZero = true;
				if (UNIT.charAt(j) == '亿' || UNIT.charAt(j) == '万'
						|| UNIT.charAt(j) == '元') {
					rs = rs + UNIT.charAt(j);
					isZero = false;
				}
			} else {
				if (isZero) {
					rs = rs + "零";
					isZero = false;
				}
				rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);
			}
		}
		if (!rs.endsWith("分")) {
			rs = rs + "整";
		}
		rs = rs.replaceAll("亿万", "亿");
		
		return v+"元（大写："+rs+")";
	}
	
	public static Double str2Double(String s){
		double d = 0D;
		try{
			d = Double.parseDouble(s);
		}catch (Exception e){
			System.out.println("字符转换为double异常");
		}
		return d;
	}
	public static String ybkg(String str,int len){
		if(str!=null&&str.length()!=0){
			int kgs = (len-str.length())*2;
			StringBuffer sb = new StringBuffer(str);
			for(int i=0;i<kgs;i++){
				sb.append(" ");
			}
			return sb.toString();
		}
		return "";
	}
}
