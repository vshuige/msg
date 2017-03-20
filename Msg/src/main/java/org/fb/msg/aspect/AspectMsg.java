package org.fb.msg.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.fb.msg.enums.SysError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * DateTime: 2017年3月16日下午1:35:37
 * 
 * @author wh
 * @Copyright (c) 2016, wh All Rights Reserved.
 **/
@Component("AspectMsg")
@Aspect
public class AspectMsg {
	private static Logger logger = LoggerFactory.getLogger(AspectMsg.class);
	private static final String SENDMSG = "sendMsg";
	
	@Pointcut("execution(* com.smk.msg.service.*.*(..))")
	public void msg() {}
	
	@Around(value = "msg()")
	public Object aroundMsg(ProceedingJoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		
		if(SENDMSG.equals(methodName)){
			logger.info("----------------短信发送开始--------------");
		}else{
			logger.info("----------------短信验证开始--------------");
		}
		Object result = null;
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			logger.error("error:", e);
		}
		if(SENDMSG.equals(methodName)){
			if(result == null){
				Map<String, Object> returnMap =  new HashMap<String, Object>();
				returnMap.put("returnCode", SysError._01.getName());
				returnMap.put("returnDesc", SysError._01.getDesc());
				result = returnMap;
			}
			logger.info("----------------短信发送结束--------------");
		}else{
			if(result == null){
				result = false;
			}
			logger.info("----------------短信验证结束--------------");
		}
		return result;
	}
	
}
