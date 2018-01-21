package com.yc.Service; 
import com.yc.Entity.YcGoodsType; 
import com.yc.Tool.ISqlServer; 
import java.util.List;
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
