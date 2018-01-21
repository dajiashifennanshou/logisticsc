package com.yc.Service;

import com.yc.Entity.PlatformCompanyCollect;
import com.yc.Tool.Pager;

/**
 * 物流商收藏
 * @Author:luojing
 * @2016年8月4日 下午3:34:42
 */
public interface PlatformCompanyCollectService {

	/**
	 * 查询收藏的物流商
	 * @Author:luojing
	 * @2016年8月4日 下午3:35:58
	 */
	public Pager<PlatformCompanyCollect> getPageCompanyCollect(Pager<PlatformCompanyCollect> pager,PlatformCompanyCollect cc);
	
	/**
	 * 单个查询
	 * @Author:luojing
	 * @2016年8月4日 下午4:34:59
	 */
	public PlatformCompanyCollect getCompanyCollect(PlatformCompanyCollect cc);
	
	/**
	 * 收藏物流商
	 * @Author:luojing
	 * @2016年8月4日 下午4:35:30
	 */
	public Integer addCompanyCollect(PlatformCompanyCollect cc);
	
	/**
	 * 删除收藏的物流商
	 * @Author:luojing
	 * @2016年8月4日 下午4:35:48
	 */
	public Integer delCompanyCollect(PlatformCompanyCollect cc);
	
	/**
	 * 批量删除收藏物流商
	 * @Author:luojing
	 * @2016年8月15日 下午5:05:50
	 */
	public Integer multiDelCompanyCollect(String ids);
	
}
