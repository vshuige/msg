package org.fb.msg.utils;

import java.security.SecureRandom;

/**
 * 
 * DateTime: 2017年3月14日上午9:34:32
 * 
 * @author wh
 * @Copyright (c) 2016, wh All Rights Reserved.
 **/
public class RandomUtil {
	/**
	 * 产生指定长度的无规律数字字符串
	 * 
	 * @param aLength
	 *            生成的随机数的长度
	 * @return 生成的随机字符串 throws 卡号生成异常
	 */
	public static String generateRandomDigitString(int aLength) {
		SecureRandom tRandom = new SecureRandom();
		long tLong;
		String aString = "";

		tRandom.nextLong();
		tLong = Math.abs(tRandom.nextLong());
		aString = (String.valueOf(tLong)).trim();
		while (aString.length() < aLength) {
			tLong = Math.abs(tRandom.nextLong());
			aString += (String.valueOf(tLong)).trim();
		}
		aString = aString.substring(0, aLength);

		return aString;
	}
	
	public static String generateID(){
		return TimeUtil.getNowTime("yyyyMMddHHmmssSSS") + "001" + generateRandomDigitString(12);
	}
}
