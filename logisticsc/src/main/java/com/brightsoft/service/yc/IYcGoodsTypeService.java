package com.brightsoft.service.yc; 
import java.util.List;

import com.brightsoft.entity.YcGoodsType;
import com.brightsoft.utils.yc.ISqlServer;
/** 
* YcGoodsType服务接口层 
* Auther:FENG 
*/ 
public interface IYcGoodsTypeService extends ISqlServer<YcGoodsType> {  

	/**
	 * 根据条件获取类型信息
	 * Author:FENG
	 * 2016年7月2日
	 * @param ygt
	 * @return
	 */
	public List<YcGoodsType> getGoodsTypeBy(YcGoodsType ygt);
}
