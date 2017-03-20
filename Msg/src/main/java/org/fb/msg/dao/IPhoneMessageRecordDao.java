package org.fb.msg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.fb.msg.entity.PhoneMessageRecord;

/**
 * 短信接口
 * DateTime: 2017年3月13日上午10:34:24
 * 
 * @author wh
 * @Copyright (c) 2016, wh All Rights Reserved.
 **/
public interface IPhoneMessageRecordDao {
	
	/**
	 * 查询短信
	 * @param phonenum
	 * @param sendtype
	 * @return
	 */
	List<PhoneMessageRecord> queryByMobileAndType(@Param("phonenum")String phonenum, 
			@Param("sendtype")String sendtype, @Param("phonemsg")String phonemsg);

	/**
	 * 添加短信记录
	 * @param psr
	 */
	void addPhoneMessage(@Param("pmr")PhoneMessageRecord pmr);
	
	/**
	 * 更新短信验证码
	 * @param psr
	 */
	void updatePhoneMessage(@Param("pmr")PhoneMessageRecord pmr);
	/**
	 * 更新校验状态
	 * @param psr
	 */
	void updateCheckInfo(@Param("pmr")PhoneMessageRecord pmr);
	
	/**
	 * 重置次数
	 * @param psr
	 */
	void resetCount(@Param("serno")String serno);
}
