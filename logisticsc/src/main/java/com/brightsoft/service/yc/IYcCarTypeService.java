package com.brightsoft.service.yc; 
import java.util.List;

import com.brightsoft.entity.YcCarType;
import com.brightsoft.utils.yc.ISqlServer; 
/** 
* YcCarType服务接口层 
* Auther:FENG 
*/ 
public interface IYcCarTypeService extends ISqlServer<YcCarType> {  

	/**
	 * 根据条件获取所有记录
	 * Author:FENG
	 * 2016年7月2日
	 * @param yc
	 * @return
	 */
	public List<YcCarType> getYcCarTypeListBy(YcCarType yc);
}
