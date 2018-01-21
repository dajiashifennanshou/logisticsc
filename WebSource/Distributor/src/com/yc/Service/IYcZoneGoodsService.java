package com.yc.Service; 

import java.math.BigInteger;

import com.yc.Entity.TempGoodsInfo;
import com.yc.Tool.Pager; 
public interface IYcZoneGoodsService {  
	
	/**
	 * 分页查询货物入库信息
	 * @Author:luojing
	 * @2016年7月5日 下午3:19:20
	 */
	public Pager<TempGoodsInfo> getInStorageList(Pager<TempGoodsInfo> pager,BigInteger dealerId);
}
