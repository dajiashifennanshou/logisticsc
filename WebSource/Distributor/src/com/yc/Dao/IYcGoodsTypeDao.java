package com.yc.Dao; 
import java.util.List;

import com.yc.Entity.YcGoodsType;
import com.yc.Tool.ISqlDao; 
/** 
* YcGoodsType数据层 
* Auther:FENG 
*/ 
public interface IYcGoodsTypeDao extends ISqlDao<YcGoodsType> {  

	/**
	 * 根据条件获取类型信息
	 * Author:FENG
	 * 2016年7月2日
	 * @param ygt
	 * @return
	 */
	public List<YcGoodsType> getGoodsTypeBy(YcGoodsType ygt);
}
