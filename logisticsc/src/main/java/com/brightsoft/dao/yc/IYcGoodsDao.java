package com.brightsoft.dao.yc; 
import java.util.List;
import java.util.Map;

import com.brightsoft.entity.YcGoods;
import com.brightsoft.entity.YcGoodsStatistics;
import com.brightsoft.entity.YcZoneGoods;
import com.brightsoft.utils.yc.ISqlDao;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcGoods数据层 
* Auther:FENG 
*/ 
public interface IYcGoodsDao extends ISqlDao<YcGoods> {  

	public List<YcGoods> getGoodsByDeliveryNo(String dNo);
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
	 * 根据指定经销商和指定云仓获得的库区编号去查询所关联的所有货物
	 * Author:FENG
	 * 2016年6月17日
	 * @param goods
	 * @return
	 */
	public List<YcGoods> getAllGoodsByDealerId(String deliveId,String branchNo);
	/**
	 * 获取所有货物根据云仓
	 * Author:FENG
	 * 2016年7月22日
	 * @return
	 */
	public List<YcGoods> getGoodsByElse(YcGoods g);
	
	/**
	 * 根据配载单号获取货物信息
	 * Author:FENG
	 * 2016年6月30日
	 * @param stowageNo
	 * @return
	 */
	public List<YcGoods> getGoodsInfoByStowageNo(String stowageNo);
	
	/**
	 * 根据经销商等状态获取总记录数
	 * Author:FENG
	 * 2016年7月13日
	 * @param yg
	 * @return
	 */
	public Integer getSumCountByDealer(YcGoods yg);
	
	/**
	 * 根据条件获取分页数据
	 * Author:FENG
	 * 2016年5月11日
	 * @param pager
	 * @param id
	 * @return
	 */
	public List<YcGoods> getYcGoodsListByDealerId(Map<String,Object> map);
	
}
