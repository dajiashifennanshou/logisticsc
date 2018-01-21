package com.yc.Service;

import java.math.BigInteger;

import com.yc.Entity.PlatformUser;

/** 
* LcPlatformUser服务接口层 
* Auther:FENG 
*/ 
public interface PlatformUserService{  
	
	/**
	 * 查询
	 * Author:luojing
	 * 2016年6月27日 下午4:35:16
	 */
	public PlatformUser getPlatformUserInfo(PlatformUser user);
	
	/**
	 * 获取过滤后的数据
	 * Author:FENG
	 * 2016年8月18日
	 * @param id
	 * @return
	 */
	public PlatformUser getEleUserInfo(BigInteger id);
	/**
	 * 添加
	 * Author:luojing
	 * 2016年6月27日 下午4:35:16
	 */
	public Integer addUserInfo(PlatformUser user);
	
	/**
	 * 修改
	 * Author:luojing
	 * 2016年6月27日 下午4:35:16
	 */
	public Integer updatePassword(PlatformUser user);
}
