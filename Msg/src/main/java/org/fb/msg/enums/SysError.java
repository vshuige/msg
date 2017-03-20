package org.fb.msg.enums;

/**
 * 
 * DateTime: 2017年3月16日上午9:33:56
 * 
 * @author wh
 * @Copyright (c) 2016, wh All Rights Reserved.
 **/
public enum SysError {
	_00("成功"),
	_01("发送失败"),
	_02("发送次数超限"),
	_03("请求频繁");

	private String desc;
	SysError(String desc){
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
	public String getName(){
		return this.name().substring(1);
	}
}
