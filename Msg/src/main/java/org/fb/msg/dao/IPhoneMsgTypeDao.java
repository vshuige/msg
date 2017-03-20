package org.fb.msg.dao;

import org.apache.ibatis.annotations.Param;
import org.fb.msg.entity.PhoneMsgType;

/**
 * 短信类型和模板
 * DateTime: 2017年3月13日下午2:22:16
 * 
 * @author wh
 * @Copyright (c) 2016, wh All Rights Reserved.
 **/
public interface IPhoneMsgTypeDao {

	/**
	 * 根据类型查询模板
	 * @param type
	 * @return
	 */
	PhoneMsgType queryByType(@Param("msgType")String msgType);
}
