package com.yc.Service; 
import java.util.List;

import com.yc.Entity.YcOrderGoods; 
import com.yc.Tool.ISqlServer; 
/** 
* YcOrderGoods服务接口层 
* Auther:FENG 
*/ 
public interface IYcOrderGoodsService extends ISqlServer<YcOrderGoods> {  

	public List<YcOrderGoods> getOrderGoodsByDeliveryNo(String deliveryNo);
}
