package com.brightsoft.dao.yc; 
import java.util.List;

import com.brightsoft.entity.YcGoodsType;
import com.brightsoft.utils.yc.ISqlDao; 
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
