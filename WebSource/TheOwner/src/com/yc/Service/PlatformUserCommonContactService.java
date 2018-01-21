package com.yc.Service;

import com.yc.Entity.PlatformUserCommonContact;
import com.yc.Tool.Pager;

/**
 * 常用联系人
 * @Author:luojing
 * @2016年8月4日 上午10:52:41
 */
public interface PlatformUserCommonContactService  {  

	/**
	 * 分页查询联系人
	 * @Author:luojing
	 * @2016年8月4日 上午11:06:36
	 */
	public Pager<PlatformUserCommonContact> getPageCommonContact(Pager<PlatformUserCommonContact> pager,PlatformUserCommonContact cc);

	/**
	 * 添加常用联系人
	 * @Author:luojing
	 * @2016年8月4日 上午11:17:27
	 */
	public Integer addCommonContact(PlatformUserCommonContact cc);
	
	/**
	 * 单个查询
	 * @Author:luojing
	 * @2016年8月4日 上午11:17:27
	 */
	public PlatformUserCommonContact getCommonContact(PlatformUserCommonContact cc);
	
	/**
	 * 删除常用联系人
	 * @Author:luojing
	 * @2016年8月4日 上午11:17:27
	 */
	public Integer delCommonContact(PlatformUserCommonContact cc);
}
