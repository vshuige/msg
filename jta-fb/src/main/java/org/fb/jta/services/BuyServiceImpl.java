package org.fb.jta.services;

import org.fb.jta.dao.merchant.IMerchantDao;
import org.fb.jta.dao.user.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @datetime 2017-3-8 下午10:20:12
 * @author wh
 **/
@Service("buyService")
public class BuyServiceImpl implements IBuyService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IMerchantDao merchantDao;
	
	public void buy(int userId, int merchantId, long money) {
		userDao.updateMenoy(userId, money);
//		int i = 1/0;//测试事务回滚
		merchantDao.updateMenoy(merchantId,money);
	}

}



