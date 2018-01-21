package com.brightsoft.dao.yc; 
import java.util.List;
import java.util.Map;

import com.brightsoft.entity.YcGoodsStatistics;
import com.brightsoft.entity.YcZoneGoods;
import com.brightsoft.utils.yc.ISqlDao; 
/** 
* YcZoneGoods数据层 
* Auther:FENG 
*/ 
public interface IYcZoneGoodsDao extends ISqlDao<YcZoneGoods> {  

	//当修改时，重设货物数量
	public Integer reSetGoodsSum(String branchNo,String goodsId,String dealerId);
	
	/**
	 * 根据运单号修改库储货状态为已出库
	 * Author:FENG
	 * 2016年6月23日
	 * @return
	 */
	public Integer  modWayBillStatusByNo(String wayBillNo);
	/**
	 * 根据经销商/专线ID分页查询
	 * Author:luojing
	 * 2016年6月20日 下午4:44:13
	 */
	public List<YcGoodsStatistics> getGoodsStatistics(Map<String,Object> map);

	/**
	 * 条件查询获物数量
	 * Author:luojing
	 * 2016年6月21日 上午9:33:18
	 */
	public Integer getGoodsStatisticsSumCount(YcGoodsStatistics gs);
	
	/**
	 * 获取总条数
	 * Author:luojing
	 * 2016年6月21日 上午9:33:18
	 */
	public Integer getGoodsStatisticsCount(YcGoodsStatistics gs);
	
	
}
