package com.brightsoft.service.yc; 
import java.util.List;

import com.brightsoft.entity.YcStowageDelivery;
import com.brightsoft.utils.yc.ISqlServer; 
/** 
* YcStowageDelivery服务接口层 
* Auther:FENG 
*/ 
public interface IYcStowageDeliveryService extends ISqlServer<YcStowageDelivery> {  

	/**
	 * 根据添加获取所有信息
	 * Author:FENG
	 * 2016年6月22日
	 * @return
	 */
	public List<YcStowageDelivery> getYcDeliveryOrderAllList(YcStowageDelivery yc);
}
