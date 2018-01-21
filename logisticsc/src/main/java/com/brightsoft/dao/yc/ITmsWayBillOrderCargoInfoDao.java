package com.brightsoft.dao.yc; 
import java.util.List;
import java.util.Map;

import com.brightsoft.entity.TmsWayBillOrderCargoInfo;
import com.brightsoft.utils.yc.ISqlDao; 
/** 
* TmsWayBillOrderCargoInfo数据层 
* Auther:FENG 
*/ 
public interface ITmsWayBillOrderCargoInfoDao extends ISqlDao<TmsWayBillOrderCargoInfo> {  

	/**
	 * 货物信息集合查询
	 * Author:luojing
	 * 2016年6月24日 下午6:00:10
	 */
	public List<TmsWayBillOrderCargoInfo> getWayBillOrderCargoInfoPager(Map<String,Object> map);
	
}
