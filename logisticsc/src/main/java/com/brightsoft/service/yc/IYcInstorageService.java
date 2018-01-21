package com.brightsoft.service.yc; 
import com.brightsoft.entity.YcInstorage;
import com.brightsoft.utils.yc.ISqlServer; 
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
	 * cId 入库的货物
	 * is 入库记录
	 * @Author:luojing
	 * @2016年7月1日 下午3:01:03
	 */
	public Integer addInstorage(YcInstorage is,String cId);

}
