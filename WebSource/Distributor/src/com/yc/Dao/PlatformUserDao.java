package com.yc.Dao; 
import com.yc.Entity.LcPlatformUser;
import com.yc.Tool.ISqlDao; 
/** 
* LcPlatformUser数据层 
* Auther:FENG 
*/ 
public interface PlatformUserDao extends ISqlDao<LcPlatformUser> {  
	
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
