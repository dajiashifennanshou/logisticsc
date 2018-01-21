package com.yc.Dao; 
import java.util.List;

import com.yc.Entity.YcCarType;
import com.yc.Tool.ISqlDao; 
/** 
* YcCarType数据层 
* Auther:FENG 
*/ 
public interface IYcCarTypeDao extends ISqlDao<YcCarType> {  

	/**
	 * 根据条件获取所有记录
	 * Author:FENG
	 * 2016年7月2日
	 * @param yc
	 * @return
	 */
	public List<YcCarType> getYcCarTypeListBy(YcCarType yc);
}
