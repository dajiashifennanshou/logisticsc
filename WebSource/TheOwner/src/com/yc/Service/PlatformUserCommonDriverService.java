package com.yc.Service;

import com.yc.Entity.PlatformUserCommonDriver;
import com.yc.Tool.Pager;

/**
 * 常用司机
 * @Author:luojing
 * @2016年8月4日 上午11:35:59
 */
public interface PlatformUserCommonDriverService {

	/**
	 * 常用司机查询
	 * @Author:luojing
	 * @2016年8月4日 上午11:38:58
	 */
	public Pager<PlatformUserCommonDriver> getPagerCommonDriver(Pager<PlatformUserCommonDriver> pager,PlatformUserCommonDriver cd);

	/**
	 * 单个查询
	 * @Author:luojing
	 * @2016年8月4日 上午11:39:38
	 */
	public PlatformUserCommonDriver getCommonDriver(PlatformUserCommonDriver cd);
	
	/**
	 * 添加司机
	 * @Author:luojing
	 * @2016年8月4日 上午11:41:25
	 */
	public Integer addCommonDriver(PlatformUserCommonDriver cd);
	
	/**
	 * 删除司机
	 * @Author:luojing
	 * @2016年8月4日 上午11:41:46
	 */
	public Integer delCommonDriver(PlatformUserCommonDriver cd);
}
