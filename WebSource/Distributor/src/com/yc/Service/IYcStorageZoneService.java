package com.yc.Service; 
import java.util.List;

import com.yc.Entity.YcStorageZone;
import com.yc.Tool.ISqlServer; 
/** 
* YcStorageZone服务接口层 
* Auther:FENG 
*/ 
public interface IYcStorageZoneService extends ISqlServer<YcStorageZone> {  

	/**
	 * 条件集合查询
	 * Author:luojing
	 * 2016年6月15日 下午2:46:51
	 */
	public List<YcStorageZone> getStorageZone(YcStorageZone sz);
}
