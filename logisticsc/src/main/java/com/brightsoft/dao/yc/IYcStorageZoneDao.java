package com.brightsoft.dao.yc; 
import java.util.List;
import java.util.Map;

import com.brightsoft.entity.YcStorageZone;
import com.brightsoft.utils.yc.ISqlDao; 
/** 
* YcStorageZone数据层 
* Auther:FENG 
*/ 
public interface IYcStorageZoneDao extends ISqlDao<YcStorageZone> {  
	/**
	 * 条件集合查询
	 * Author:luojing
	 * 2016年6月15日 下午2:46:51
	 */
	public List<YcStorageZone> getStorageZone(Map<String,Object> map);
}
