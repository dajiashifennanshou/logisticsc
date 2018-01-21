package com.yc.Dao; 
import java.util.List;

import com.yc.Entity.PlatformOrderCargo; 
import com.yc.Tool.ISqlDao; 
/** 
* LcPlatformOrderCargo数据层 
* Auther:FENG 
*/ 
public interface PlatformOrderCargoDao extends ISqlDao<PlatformOrderCargo> {  

	/**
	 * 获取列表信息，根据条件
	 * Author:FENG
	 * 2016年8月4日
	 * @param cargo
	 * @return
	 */
	List<PlatformOrderCargo> getListInfoBy(PlatformOrderCargo cargo);
}
