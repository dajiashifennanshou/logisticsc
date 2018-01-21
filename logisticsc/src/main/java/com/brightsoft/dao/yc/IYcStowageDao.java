package com.brightsoft.dao.yc; 
import java.util.List;

import com.brightsoft.entity.YcStowage;
import com.brightsoft.utils.yc.ISqlDao; 
/** 
* YcStowage数据层 
* Auther:FENG 
*/ 
public interface IYcStowageDao extends ISqlDao<YcStowage> {  

	/**
	 * 根据条件获取所有的配载单信息
	 * Author:FENG
	 * 2016年6月24日
	 * @param ys
	 * @return
	 */
	public List<YcStowage> getAllStowageList(YcStowage ys);
	
	/**
	 * 根据配载单号修改配载单状态
	 * Author:FENG
	 * 2016年6月30日
	 * @param stowageNo
	 * @return
	 */
	public Integer modStatusByNo(YcStowage ys);
}
