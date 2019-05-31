package com.guochen.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

	private static final String UNIT = "��ǧ��ʰ��ǧ��ʰ��ǧ��ʰԪ�Ƿ�";
	private static final String DIGIT = "��Ҽ��������½��ƾ�";
	private static final double MAX_VALUE = 9999999999999.99D;

	/**
	 * �����ڰ���ʽת�����ַ���
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Str(Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return fmt.format(date);
	}
	/**
	 * �����ڰ���ʽת�����ַ���
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Strnyr(Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy��MM��dd��");
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
	           sb.append("0").append(str);// ��0
	           str = sb.toString();
	           strLen = str.length();
	     }
	    return str;
	}
	/**
	 * ���ַ������չ̶���ʽת��������
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
	 * �Ƚ������ַ�������
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
	 * ���������ַ�������֮���Сʱ������
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static String countTimeBetweenDays(String date1, String date2) {
		String retTime = "0Сʱ0����0��";
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
			retTime = hour + "Сʱ" + minute + "����" + second + "��";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return retTime;
	}

	/**
	 * �����ֽ��ת��Ϊ��д���
	 * 
	 * @param v
	 * @return
	 */
	public static String toBigAmount(double v) {
		if (v < 0 || v > MAX_VALUE) {
			return "�����Ƿ�!";
		}
		long l = Math.round(v * 100);
		if (l == 0) {
			return "��Ԫ��";
		}
		String strValue = l + "";
		// i����������
		int i = 0;
		// j�������Ƶ�λ
		int j = UNIT.length() - strValue.length();
		String rs = "";
		boolean isZero = false;
		for (; i < strValue.length(); i++, j++) {
			char ch = strValue.charAt(i);
			if (ch == '0') {
				isZero = true;
				if (UNIT.charAt(j) == '��' || UNIT.charAt(j) == '��'
						|| UNIT.charAt(j) == 'Ԫ') {
					rs = rs + UNIT.charAt(j);
					isZero = false;
				}
			} else {
				if (isZero) {
					rs = rs + "��";
					isZero = false;
				}
				rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);
			}
		}
		if (!rs.endsWith("��")) {
			rs = rs + "��";
		}
		rs = rs.replaceAll("����", "��");
		
		return v+"Ԫ����д��"+rs+")";
	}
	
	public static Double str2Double(String s){
		double d = 0D;
		try{
			d = Double.parseDouble(s);
		}catch (Exception e){
			System.out.println("�ַ�ת��Ϊdouble�쳣");
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
