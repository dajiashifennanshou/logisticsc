package com.brightsoft.dao.yc; 
import com.brightsoft.entity.YcInstorage;
import com.brightsoft.utils.yc.ISqlDao; 
/** 
* YcInstorage数据层 
* Auther:FENG 
*/ 
public interface IYcInstorageDao extends ISqlDao<YcInstorage> {  
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
