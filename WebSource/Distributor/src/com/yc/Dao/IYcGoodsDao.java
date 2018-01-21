package com.yc.Dao; 
import java.util.List;
import java.util.Map;

import com.yc.Entity.YcGoods;
import com.yc.Tool.ISqlDao; 
/** 
* YcGoods数据层 
* Auther:FENG 
*/ 
public interface IYcGoodsDao extends ISqlDao<YcGoods> {  

	/**
	 * 根据配送单查询
	 * @Author:luojing
	 * @2016年7月13日 上午11:46:41
	 */
	public List<YcGoods> getGoodsByDeliveryNo(String dNo);
	
	
	public List<YcGoods> getGoodsByWayNo(String wayBillNo);
	public List<YcGoods> getGoodsByDealerId(String deliverId,String branchNo,String outStorageStatus,String goodIds);
	/**
	 * 根据货物id修改货物出库状态为已出库
	 * Author:FENG
	 * 2016年6月23日
	 * @param wayBillNo
	 * @return
	 */
	public Integer modOutStatusByNo(Integer goodId);
	
	/**
	 * 分页查询
	 * @Author:luojing
	 * @2016年7月12日 上午10:49:53
	 */
	public List<YcGoods> getPageGoodsByDealerId(Map<String,Object> map);
	
	/**
	 * 根据配载单号获取货物信息
	 * Author:FENG
	 * 2016年6月30日
	 * @param stowageNo
	 * @return
	 */
	public List<YcGoods> getGoodsInfoByStowageNo(String stowageNo);
	
	/**
	 * 根据运单号查询货物信息
	 * @Author:luojing
	 * @2016年7月12日 下午3:27:42
	 */
	public List<YcGoods> getGoodsInfoByWayBillNo(String wayBillNo);
	
}
