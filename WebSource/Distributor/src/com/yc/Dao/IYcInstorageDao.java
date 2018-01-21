package com.yc.Dao; 
import java.util.List;
import java.util.Map;

import com.yc.Entity.YcInstorage;
import com.yc.Tool.ISqlDao; 
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
	
	/**
	 * 查询运单来源
	 * @Author:luojing
	 * @2016年7月23日 下午5:47:55
	 */
	public YcInstorage getWaybillSource(String waybillNo);
	
	/**
	 * 获取分页货物数据
	 * Author:FENG
	 * 2016年7月23日
	 * @return
	 */
	public List<YcInstorage> getListByPage(Map<String,Object> map);
	
}
