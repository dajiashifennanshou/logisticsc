package com.yc.Dao; 
import java.util.List;
import java.util.Map;

import com.yc.Entity.TempGoodsInfo;
import com.yc.Entity.YcZoneGoods;
import com.yc.Tool.ISqlDao; 
/** 
* YcZoneGoods数据层 
* Auther:FENG 
*/ 
public interface IYcZoneGoodsDao extends ISqlDao<YcZoneGoods> {  

	/**
	 * 分页查询货物入库信息
	 * @Author:luojing
	 * @2016年7月5日 下午3:19:20
	 */
	public List<YcZoneGoods> getInStorageList(Map<String,Object> map);
}
