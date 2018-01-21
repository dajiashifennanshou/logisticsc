package com.brightsoft.service.yc; 
import java.util.List;

import com.brightsoft.entity.YcJoinInfo;
import com.brightsoft.utils.yc.ISqlServer; 
/** 
* YcJoinInfo服务接口层 
* Auther:luojing 
*/ 
public interface IYcJoinInfoService extends ISqlServer<YcJoinInfo> {  

	/**
	 * 集合查询加盟商信息
	 * Author:luojing
	 * 2016年6月20日 上午10:32:13
	 */
	public List<YcJoinInfo> getYcJoin(YcJoinInfo join);
}
