package org.fb.jta.dao.merchant;

import org.apache.ibatis.annotations.Param;

/**
 * @datetime 2017-3-8 下午10:20:12
 * @author wh
 **/

public interface IMerchantDao {

	void updateMenoy(@Param("id") Integer id, @Param("money") long money);
}



