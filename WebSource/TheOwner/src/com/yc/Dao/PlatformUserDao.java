package com.yc.Dao; 
import java.math.BigInteger;

import com.yc.Entity.PlatformUser;
import com.yc.Tool.ISqlDao; 
/** 
* LcPlatformUser数据层 
* Auther:FENG 
*/ 
public interface PlatformUserDao extends ISqlDao<PlatformUser> {  
	
	/**
	 * 获取过滤后的数据
	 * Author:FENG
	 * 2016年8月18日
	 * @param id
	 * @return
	 */
	public PlatformUser getEleUserInfo(BigInteger id);
}
