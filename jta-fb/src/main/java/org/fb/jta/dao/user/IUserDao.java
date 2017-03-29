package org.fb.jta.dao.user;

import org.apache.ibatis.annotations.Param;
import org.fb.jta.entity.User;

/**
 * @datetime 2017-3-8 下午10:20:12
 * @author wh
 **/


public interface IUserDao {

	User getUser(int id);
	
	void updateMenoy(@Param("id") Integer id, @Param("money") long money);
}
