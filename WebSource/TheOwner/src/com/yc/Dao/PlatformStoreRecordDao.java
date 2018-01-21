package com.yc.Dao; 
import java.math.BigInteger;
import java.util.List;

import com.yc.Entity.PlatformStoreRecord;
import com.yc.Tool.ISqlDao; 
/**
 * 线路收藏
 * @Author:luojing
 * @2016年8月4日 下午1:59:25
 */
public interface PlatformStoreRecordDao extends ISqlDao<PlatformStoreRecord> {  
	/**
	 * 批量删除收藏线路
	 * @Author:luojing
	 * @2016年8月15日 下午5:24:47
	 */
	public Integer multiDelCollect(List<BigInteger> list);
}
