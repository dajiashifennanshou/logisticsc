package com.brightsoft.dao.yc; 
import java.util.List;

import com.brightsoft.entity.YcInstallCharge;
import com.brightsoft.utils.yc.ISqlDao; 
/** 
* YcInstallCharge数据层 
* Auther:FENG 
*/ 
public interface IYcInstallChargeDao extends ISqlDao<YcInstallCharge> {  
	/**
	 * 根据类型(1,2,3)获取安装费用集合
	 * Author:FENG
	 * 2016年7月2日
	 * @param type
	 * @return
	 */
	public List<YcInstallCharge> getYcInstallCostBy(String type,String branchNo);
}
