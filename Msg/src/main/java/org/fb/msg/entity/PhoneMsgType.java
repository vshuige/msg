package org.fb.msg.entity;

import java.io.Serializable;

/**
 * 
 * DateTime: 2017年3月13日下午2:19:34
 * 
 * @author wh
 * @Copyright (c) 2016, wh All Rights Reserved.
 **/
public class PhoneMsgType implements Serializable{
	private static final long serialVersionUID = 2979664687160942181L;
	//短信类型
	private String msgType;
	//模板
	private String templetPrefix;
	//模板
	private String templetSuffix;
	//限制次数
	private Integer limitTimes;
	//有效时间
	private Integer limitPeriod;
	//两次短信发送最短时间
	private Integer minSpace;

	public String getTempletPrefix() {
		return templetPrefix;
	}
	public void setTempletPrefix(String templetPrefix) {
		this.templetPrefix = templetPrefix;
	}
	public String getTempletSuffix() {
		return templetSuffix;
	}
	public void setTempletSuffix(String templetSuffix) {
		this.templetSuffix = templetSuffix;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public Integer getLimitTimes() {
		return limitTimes;
	}
	public void setLimitTimes(Integer limitTimes) {
		this.limitTimes = limitTimes;
	}
	public Integer getLimitPeriod() {
		return limitPeriod;
	}
	public Integer getMinSpace() {
		return minSpace;
	}
	public void setLimitPeriod(Integer limitPeriod) {
		this.limitPeriod = limitPeriod;
	}
	public void setMinSpace(Integer minSpace) {
		this.minSpace = minSpace;
	}
	
}
