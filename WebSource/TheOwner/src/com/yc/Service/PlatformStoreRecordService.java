package com.yc.Service;

import com.yc.Entity.PlatformStoreRecord;
import com.yc.Tool.Pager;

/**
 * 线路收藏
 * @Author:luojing
 * @2016年8月4日 下午2:00:51
 */
public interface PlatformStoreRecordService {

	/**
	 * 收藏线路查询
	 * @Author:luojing
	 * @2016年8月4日 下午2:04:13
	 */
	public Pager<PlatformStoreRecord> getPagerStoreRecord(Pager<PlatformStoreRecord> pager,PlatformStoreRecord sr);

	/**
	 * 收藏线路
	 * @Author:luojing
	 * @2016年8月4日 下午2:04:51
	 */
	public Integer addStoreRecord(PlatformStoreRecord sr);
	
	/**
	 * 删除收藏线路
	 * @Author:luojing
	 * @2016年8月4日 下午2:05:27
	 */
	public Integer delStoreRecord(PlatformStoreRecord sr);
	
	/**
	 * 单个查询
	 * @Author:luojing
	 * @2016年8月4日 下午2:06:01
	 */
	public PlatformStoreRecord getStoreRecord(PlatformStoreRecord sr);
	
	/**
	 * 批量删除收藏线路
	 * @Author:luojing
	 * @2016年8月15日 下午5:24:47
	 */
	public Integer multiDelCollect(String ids);
}
