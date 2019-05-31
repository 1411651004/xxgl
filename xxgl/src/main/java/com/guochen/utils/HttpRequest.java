package com.guochen.utils;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.shcm.bean.BalanceResultBean;
import com.shcm.bean.SendResultBean;
import com.shcm.send.DataApi;
import com.shcm.send.OpenApi;
@Controller
public class HttpRequest {
	private static String sOpenUrl = "http://smsapi.c123.cn/OpenPlatform/OpenApi";
	private static String sDataUrl = "http://smsapi.c123.cn/DataPlatform/DataApi";
	
	// 默认使用的签名编号(未指定签名编号时传此值到服务器)
	private static final int csid = 0;
	
	public static List<SendResultBean> sendOnce(String mobile, String content) throws Exception
	{
		// 发送短信
		return OpenApi.sendOnce(mobile, content, 0, 0, null);
		//return OpenApi.sendOnce(new String[]{"18297974783","15102110086"}, "测试内容", 0, 0, null);
		//return OpenApi.sendBatch("18297974783,15102110086", "测试内容{|}测试内容", 0, 0, null);
		//return OpenApi.sendBatch(new String[]{"18297974783","15102110086"}, new String[]{"测试内容","测试内容"}, 0, 0, null);
		//return OpenApi.sendParam("18297974783,15102110086", "测试内容{p1}", new String[]{"a{|}b"}, 0, 0, null);
		//return OpenApi.sendParam(new String[]{"18297974783","15102110086"}, "测试内容{p1}", new String[]{"a{|}b"}, 0, 0, null);
	}
	
	public static List<SendResultBean> send(String account,String authkey,int cgid,String[] mobile, String content)
	{
		// 发送参数
		OpenApi.initialzeAccount(sOpenUrl, account, authkey, cgid, csid);
		
		// 状态及回复参数
		DataApi.initialzeAccount(sDataUrl, account, authkey);
		
		List<SendResultBean> listItem = OpenApi.sendOnce(mobile, content, 0, 0, null);
		return listItem;
	}
}
