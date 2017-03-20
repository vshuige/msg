package org.fb.msg.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.fb.msg.dao.IPhoneMessageRecordDao;
import org.fb.msg.dao.IPhoneMsgTypeDao;
import org.fb.msg.entity.PhoneMessageRecord;
import org.fb.msg.entity.PhoneMsgType;
import org.fb.msg.enums.SysError;
import org.fb.msg.utils.RandomUtil;
import org.fb.msg.utils.SendMessageSocket;
import org.fb.msg.utils.StringUtils;
import org.fb.msg.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

/**
 * 短信发送与校验
 * DateTime: 2017年3月13日上午10:30:35
 * 
 * @author wh
 * @Copyright (c) 2016, wh All Rights Reserved.
 **/
@Service("mobileService")
public class MsgServiceIpml implements IMsgService{
	private static Logger logger = LoggerFactory.getLogger(MsgServiceIpml.class);
	
	private static final String INIT_STATUS = "0";//未校验或已发送
	private static final String OK_STATUS = "1";//已经校验
	
	@Resource
	private IPhoneMsgTypeDao phoneMsgTypeDao;
	@Resource
	private IPhoneMessageRecordDao phoneMessageRecordDao;

	public Map<String, Object> sendMsg(String mobile, String type,
			String proNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		logger.info("短信发送请求数据：" + JSON.toJSONString("Moblile:"+mobile+",ProNo:"+proNo+",SendType:"+type));
		if(!StringUtils.areNotEmpty(mobile,type,proNo)){
			logger.info("短信发送发送失败，存在空参数.Moblile:"+mobile
					+",ProNo:"+proNo+",SendType:"+type);
			
			result.put("returnCode", SysError._01.getName());
			result.put("returnDesc", SysError._01.getDesc());
			return result;
		}
		if ((mobile.length() != 11) || (!mobile.matches("[0-9]+"))) {
			logger.info("手机号格式有误.Moblile:"+mobile);
			result.put("returnCode", SysError._01.getName());
			result.put("returnDesc", SysError._01.getDesc());
			return result;
		}
		PhoneMsgType phoneMsgType = phoneMsgTypeDao.queryByType(type);
		if(phoneMsgType == null){
			logger.info("短信类型不支持："+type);
			result.put("returnCode", SysError._01.getName());
			result.put("returnDesc", SysError._01.getDesc());
			return result;
		}
		//生成验证码
		String phoneMsg = RandomUtil.generateRandomDigitString(6);//短信信息
		boolean flag = false;//短信是否超时或校验
		// 查询短信发送次数是否超限
		List<PhoneMessageRecord> pmrs = phoneMessageRecordDao.queryByMobileAndType(mobile, type, null);
		if(pmrs != null && pmrs.size() > 0){
			PhoneMessageRecord pmr = pmrs.get(0);
			String currDate = TimeUtil.getCurrDate();
			//校验最后一次发送日期是不是一天前
			if(currDate.compareTo(pmr.getSendtime().substring(0, 8)) > 0){
				//更新发送次数为0
				phoneMessageRecordDao.resetCount(pmr.getSerno());
				pmr.setCount("0");
			}
			//次数是否超限
			if(Integer.parseInt(pmr.getCount()) >= phoneMsgType.getLimitTimes()){
				logger.info("短信发送次数超限，次数："+pmr.getSendtime());
				result.put("returnCode", SysError._02.getName());
				result.put("returnDesc", SysError._02.getDesc());
				return result;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar currTime = Calendar.getInstance();
			currTime.add(Calendar.SECOND, (-1) * phoneMsgType.getMinSpace());
			//判断发送是否过于频繁
			if(pmr.getSendtime().compareTo(sdf.format(currTime.getTime())) > 0){
				logger.info("短信发送过于频繁，："+pmr.getSendtime());
				result.put("returnCode", SysError._03.getName());
				result.put("returnDesc", SysError._03.getDesc());
				return result;
			}
			//短信未超时且未校验，短信内容不变 
			currTime = Calendar.getInstance();
			currTime.add(Calendar.SECOND, (-1) * phoneMsgType.getLimitPeriod());
			if(INIT_STATUS.equals(pmr.getStatus()) 
					&& pmr.getCreatetime().compareTo(sdf.format(currTime.getTime())) > 0){
				phoneMsg = pmr.getPhonemsg();
			}else{
				flag = true;
			}
		}
		//生成验证码
		String msgReturn = null;
		try {
			msgReturn = SendMessageSocket.sendMsg(mobile, phoneMsgType.getTempletPrefix()
					+ phoneMsg + phoneMsgType.getTempletSuffix());
		} catch (Exception e) {
			logger.info("短信发送异常：",e);
		}
		if(msgReturn == null ){
			result.put("returnCode", SysError._01.getName());
			result.put("returnDesc", SysError._01.getDesc());
			return result;
		}
		if(pmrs != null && pmrs.size() > 0){//执行更新
			logger.info("更新短信内容:"+phoneMsg);
			PhoneMessageRecord pmr = pmrs.get(0);
			if(OK_STATUS.equals(pmr.getStatus()) || flag){
				pmr.setCreatetime(TimeUtil.getCurrDateTime());
			}
			pmr.setStatus(INIT_STATUS);
			pmr.setSendtime(TimeUtil.getCurrDateTime());
			pmr.setPhonemsg(phoneMsg);
			phoneMessageRecordDao.updatePhoneMessage(pmr);
		}else{//执行添加
			logger.info("添加短信内容："+phoneMsg);
			PhoneMessageRecord pmr = new PhoneMessageRecord();
			pmr.setCreatetime(TimeUtil.getCurrDateTime());
			pmr.setSendtime(TimeUtil.getCurrDateTime());
			pmr.setSerno(RandomUtil.generateID());//ID
			pmr.setStatus(INIT_STATUS);//状态 0：已发送；1：已验证
			pmr.setPhonenum(mobile);
			pmr.setSendtype(type);
			pmr.setCount("0");//发送次数
			pmr.setPhonemsg(phoneMsg);
			phoneMessageRecordDao.addPhoneMessage(pmr);
		}
		result.put("returnCode", SysError._00.getName());
		result.put("returnDesc", SysError._00.getDesc());
		return result;
	}

	public boolean validateMsg(String mobile, String type, String proNo,
			String verCode) {
		
		boolean result = false;
		
		logger.info("短信验证请求数据：" + "Moblile:"+mobile+",ProNo:"+proNo+",SendType:"+type+",VerCode:"+verCode);
		if(!StringUtils.areNotEmpty(mobile,proNo,
				type,verCode)){
			logger.info("短信验证发送失败，存在空参数.Moblile:"+mobile
					+",ProNo:"+proNo+",SendType:"+type+",VerCode:"+verCode);
			return result;
		}
		PhoneMsgType phoneMsgType = phoneMsgTypeDao.queryByType(type);
		if(phoneMsgType == null){
			logger.info("短信类型不支持："+type);
			return result;
		}
		
		List<PhoneMessageRecord> pmrs = phoneMessageRecordDao.queryByMobileAndType(mobile, type, verCode);
		if(pmrs != null && pmrs.size() >0 ){
			PhoneMessageRecord pmr = pmrs.get(0);
			if(OK_STATUS.equals(pmr.getStatus())){
				logger.info("短信已经被校验，校验时间：" + pmr.getCheckdate());
				return result;
			}else{
				Calendar currTime = Calendar.getInstance();
				currTime.add(Calendar.SECOND, (-1) * phoneMsgType.getLimitPeriod());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				if(pmr.getCreatetime().compareTo(sdf.format(currTime.getTime())) > 0){
					result = true;
					pmr.setStatus(OK_STATUS);
					pmr.setCheckdate(TimeUtil.getCurrDateTime());
					phoneMessageRecordDao.updateCheckInfo(pmr);
				}else{
					logger.info("验证码超时...");
				}
			}
		}else{
			logger.info("验证码错误...");
		}
		return result;
	}

}
