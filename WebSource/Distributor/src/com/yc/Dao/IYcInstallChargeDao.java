package com.yc.Dao; 
import java.util.List;

import com.yc.Entity.YcInstallCharge;
import com.yc.Tool.ISqlDao; 
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
	/**
	 * 根据单个类型获取安装费用
	 * Author:FENG
	 * 2016年7月2日
	 * @param type
	 * @return
	 */
	public YcInstallCharge getYcSingleCostBy(String type);
	
	
}
