package com.brightsoft.dao.yc; 
import java.util.List;

import com.brightsoft.entity.YcOrderGoods;
import com.brightsoft.utils.yc.ISqlDao; 
/** 
* YcOrderGoods数据层 
* Auther:FENG 
*/ 
public interface IYcOrderGoodsDao extends ISqlDao<YcOrderGoods> {  
	public List<YcOrderGoods> getOrderGoodsByDeliveryNo(String deliveryNo);
}
