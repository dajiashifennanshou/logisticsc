package com.yc.Service; 
import java.util.List;

import com.yc.Entity.PlatformOrderCargo; 
import com.yc.Tool.ISqlServer; 
/** 
* LcPlatformOrderCargo服务接口层 
* Auther:FENG 
*/ 
public interface PlatformOrderCargoService extends ISqlServer<PlatformOrderCargo> {  

	/**
	 * 获取列表信息，根据条件
	 * Author:FENG
	 * 2016年8月4日
	 * @param cargo
	 * @return
	 */
	List<PlatformOrderCargo> getListInfoBy(PlatformOrderCargo cargo);
}
