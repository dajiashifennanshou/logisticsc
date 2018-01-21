package com.yc.Service;

import java.math.BigInteger;

import com.yc.Entity.PlatformUserTemporaryCompany;

/**
 * 公司临时信息
 * @Author:luojing
 * @2016年8月16日 上午10:58:22
 */
public interface PlatformUserTemporaryCompanyService {  
	
	/**
	 * 添加公司临时信息
	 * @Author:luojing
	 * @2016年8月16日 下午2:03:16
	 */
	public boolean addTempCompan(BigInteger userId,PlatformUserTemporaryCompany company);
	
	/**
	 * 查询
	 * @Author:luojing
	 * @2016年8月18日 下午4:54:53
	 */
	public PlatformUserTemporaryCompany getUserTemporaryCompany(PlatformUserTemporaryCompany company);

}
