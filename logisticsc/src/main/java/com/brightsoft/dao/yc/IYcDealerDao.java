package com.brightsoft.dao.yc; 
import java.util.List;

import com.brightsoft.entity.YcDealer;
import com.brightsoft.utils.yc.ISqlDao; 
/** 
* YcDealer数据层 
* Auther:FENG 
*/ 
public interface IYcDealerDao extends ISqlDao<YcDealer> {  
	/**
	 * 查询没有注册加盟商的经销商用户
	 * Author:luojing
	 * 2016年6月23日 下午5:20:10
	 */
	public List<YcDealer> getUnRegisterDealer();
	
	/**
	 * 获取所有的经销商
	 * Author:FENG
	 * 2016年6月30日
	 * @return
	 */
	public List<YcDealer> getAllDealer();
}
