package org.fb.msg.utils;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * DateTime: 2017年3月14日上午9:41:32
 * 
 * @author wh
 * @Copyright (c) 2016, wh All Rights Reserved.
 **/
public class SendMessageSocket {
	private static Logger logger = LoggerFactory.getLogger(SendMessageSocket.class);
	
	/**
	 * 
	 * @Title: sendMsg
	 * @Description: 发送验证码
	 * @author yang_df
	 * @since 2014年5月20日
	 * @param moblile
	 * @param content
	 * @throws Exception 
	 */
	public static String sendMsg(String moblile, String content) throws Exception {
		String endpoint = "http://192.168.0.1:8080/services/msgService";
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		call.setOperationName("service");
		call.setTimeout(2000);
		String headxml = "<?xml version='1.0' encoding='utf-8'?><request><head><messagetype>3015</messagetype><ver>3</ver><sysdatetime>"
				+ TimeUtil.getCurrDateTime() + "</sysdatetime></head><body>";
		String footxml = "</body></request>";
		String xml = headxml + "<mobile>" + moblile + "</mobile>" + "<msg>" + content + "</msg>" + footxml;
		logger.info("短信验证码发送报文：" + xml);
		xml = (String) call.invoke(new Object[] { xml });
		logger.info("短信验证码返回信息：" + xml);
		return xml;
	}
}
