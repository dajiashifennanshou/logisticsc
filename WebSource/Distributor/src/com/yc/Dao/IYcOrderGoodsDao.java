package com.yc.Dao; 
import java.util.List;

import com.yc.Entity.YcOrderGoods;
import com.yc.Tool.ISqlDao; 
/** 
* YcOrderGoods数据层 
* Auther:FENG 
*/ 
public interface IYcOrderGoodsDao extends ISqlDao<YcOrderGoods> {  
	
	public List<YcOrderGoods> getOrderGoodsByDeliveryNo(String deliveryNo);
}
