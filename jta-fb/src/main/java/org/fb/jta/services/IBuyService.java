package org.fb.jta.services;

/**
 * @datetime 2017-3-8 下午10:20:12
 * @author wh
 **/

public interface IBuyService {
	
	/**
	 * 
	 * @param userId
	 * @param merchantId
	 * @param menoy
	 * @throws Exception 
	 */
	void buy(int userId, int merchantId, long menoy) ;

}



