package com.brightsoft.service.yc; 
import java.util.List;

import com.brightsoft.entity.YcStorageZone;
import com.brightsoft.utils.yc.ISqlServer; 
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
