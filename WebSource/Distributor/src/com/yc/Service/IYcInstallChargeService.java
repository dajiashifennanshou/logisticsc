package com.yc.Service; 
import java.util.List;

import com.yc.Entity.YcInstallCharge;
import com.yc.Tool.ISqlServer;

/** 
* YcInstallCharge服务接口层 
* Auther:FENG 
*/ 
public interface IYcInstallChargeService extends ISqlServer<YcInstallCharge> {  

	/**
	 * 根据类型(1,2,3)获取安装费用集合
	 * Author:FENG
	 * 2016年7月2日
	 * @param type
	 * @return
	 */
	public List<YcInstallCharge> getYcInstallCostBy(String type,String branchNo);
}
