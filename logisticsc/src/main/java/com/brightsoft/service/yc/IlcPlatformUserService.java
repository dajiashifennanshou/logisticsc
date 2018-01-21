package com.brightsoft.service.yc;

import com.brightsoft.entity.LcPlatformUser;

/** 
* LcPlatformUser服务接口层 
* Auther:FENG 
*/ 
public interface IlcPlatformUserService{  
	
	/**
	 * 查询
	 * Author:luojing
	 * 2016年6月27日 下午4:35:16
	 */
	public LcPlatformUser getSingleInfo(LcPlatformUser user);
	
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
