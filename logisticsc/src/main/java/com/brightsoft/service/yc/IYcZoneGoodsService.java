package com.brightsoft.service.yc; 
import com.brightsoft.entity.YcGoodsStatistics;
import com.brightsoft.entity.YcZoneGoods;
import com.brightsoft.utils.yc.ISqlServer;
import com.brightsoft.utils.yc.Pager; 
public interface IYcZoneGoodsService extends ISqlServer<YcZoneGoods> {  
	
	public Integer reSetGoodsSum(String branchNo,String goodsId,String dealerId);

	/**
	 * 根据经销商/专线ID分页查询
	 * Author:luojing
	 * 2016年6月20日 下午4:44:13
	 */
	public Pager<YcGoodsStatistics> getGoodsStatistics(Pager<YcGoodsStatistics> pager,YcGoodsStatistics gs);
	
	/**
	 * 条件查询获物数量
	 * Author:luojing
	 * 2016年6月21日 上午9:33:18
	 */
	public Integer getGoodsStatisticsSumCount(YcGoodsStatistics gs);
	
	
	/**
	 * 根据运单号修改库储货状态为已出库
	 * Author:FENG
	 * 2016年6月23日
	 * @return
	 */
	public Integer  modWayBillStatusByNo(String wayBillNo);
}
