package com.brightsoft.service.yc; 
import java.util.List;

import com.brightsoft.entity.YcOrderGoods;
import com.brightsoft.utils.yc.ISqlServer; 
/** 
* YcOrderGoods服务接口层 
* Auther:FENG 
*/ 
public interface IYcOrderGoodsService extends ISqlServer<YcOrderGoods> {  

	public List<YcOrderGoods> getOrderGoodsByDeliveryNo(String deliveryNo);
}
