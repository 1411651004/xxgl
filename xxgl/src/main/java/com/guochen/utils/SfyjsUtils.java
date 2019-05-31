package com.guochen.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 是否研究生的处理
 * @author Administrator
 *
 */
public class SfyjsUtils {
	private final static String value = "研究生";
	private final static Map<String,String> yjsMap = new HashMap<String,String>();
	private final static Map<String,String> notYjsMap = new HashMap<String,String>();
	static {
	    yjsMap.put("1","大学院1年生");
	    yjsMap.put("2","大学院2年生");
	    yjsMap.put("3","大学院3年生");
	    yjsMap.put("4","大学院4年生");
	    
	    notYjsMap.put("1","1年生");
	    notYjsMap.put("2","2年生");
	    notYjsMap.put("3","3年生");
	    notYjsMap.put("4","4年生");
	}
	
	public static String key2Value(String sfyjs, String key) {
		if(value.equals(sfyjs)) {
			return yjsMap.get(key);
		} else {
			return notYjsMap.get(key);
		}
	}
}
