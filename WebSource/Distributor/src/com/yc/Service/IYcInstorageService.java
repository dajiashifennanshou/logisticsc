package com.yc.Service; 
import com.yc.Entity.YcInstorage; 
import com.yc.Tool.ISqlServer; 
/** 
* YcInstorage服务接口层 
* Auther:FENG 
*/ 
public interface IYcInstorageService extends ISqlServer<YcInstorage> {
	
	/**
	 * 删除入库记录
	 * @Author:luojing
	 * @2016年7月1日 上午10:45:29
	 */
	public Integer delInstorage(String ids);
	
	/**
	 * 运单入库
	 * cId 不入库的货物
	 * is 入库记录
	 * @Author:luojing
	 * @2016年7月1日 下午3:01:03
	 */
	public Integer addInstorage(YcInstorage is,String cId);

}
