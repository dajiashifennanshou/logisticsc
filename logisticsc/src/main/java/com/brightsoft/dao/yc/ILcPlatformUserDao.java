package com.brightsoft.dao.yc; 
import com.brightsoft.entity.LcPlatformUser;
import com.brightsoft.utils.yc.ISqlDao; 
/** 
* LcPlatformUser数据层 
* Auther:FENG 
*/ 
public interface ILcPlatformUserDao extends ISqlDao<LcPlatformUser> {  
	
	/**
	 * 查询
	 * Author:luojing
	 * 2016年6月27日 下午4:35:16
	 */
	public Integer addUserInfo(LcPlatformUser user);
	
	/**
	 * 查询
	 * Author:luojing
	 * 2016年6月27日 下午4:35:16
	 */
	public Integer updatePassword(LcPlatformUser user);
}
