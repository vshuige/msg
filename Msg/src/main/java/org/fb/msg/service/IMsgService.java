package org.fb.msg.service;

import java.util.Map;

/**
 * 短信发送，校验
 * DateTime: 2017年3月13日上午10:01:06
 * 
 * @author wh
 * @Copyright (c) 2016, wh All Rights Reserved.
 **/
public interface IMsgService {

	/**
	 * 发送短信
	 * @param req
	 * @return
	 */
	Map<String, Object> sendMsg(String moblile,String sendType, String proNo);
	
	/**
	 * 校验短信
	 * @param req
	 * @return
	 */
	boolean validateMsg(String moblile,String sendType, String proNo,String verCode);
	
}
