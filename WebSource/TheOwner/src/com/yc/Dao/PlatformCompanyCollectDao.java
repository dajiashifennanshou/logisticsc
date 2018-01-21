package com.yc.Dao; 
import java.math.BigInteger;
import java.util.List;

import com.yc.Entity.PlatformCompanyCollect;
import com.yc.Tool.ISqlDao; 
/**
 * 物流商收藏
 * @Author:luojing
 * @2016年8月4日 下午3:33:16
 */
public interface PlatformCompanyCollectDao extends ISqlDao<PlatformCompanyCollect> { 
	/**
	 * 批量删除收藏物流商
	 * @Author:luojing
	 * @2016年8月15日 下午5:05:50
	 */
	public Integer multiDelCompanyCollect(List<BigInteger> list);
}
