package com.yc.Dao; 
import java.util.List;

import com.yc.Entity.YcStowageDelivery;
import com.yc.Tool.ISqlDao; 
/** 
* YcStowageDelivery数据层 
* Auther:FENG 
*/ 
public interface IYcStowageDeliveryDao extends ISqlDao<YcStowageDelivery> {  

	/**
	 * 根据添加获取所有信息
	 * Author:FENG
	 * 2016年6月22日
	 * @return
	 */
	public List<YcStowageDelivery> getYcDeliveryOrderAllList(YcStowageDelivery yc);
}
