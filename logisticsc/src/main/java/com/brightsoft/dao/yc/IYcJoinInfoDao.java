package com.brightsoft.dao.yc; 
import java.util.List;

import com.brightsoft.entity.YcJoinInfo;
import com.brightsoft.utils.yc.ISqlDao; 
/** 
* YcJoinInfo数据层 
* Author:luojing 
*/ 
public interface IYcJoinInfoDao extends ISqlDao<YcJoinInfo> {  

	/**
	 * 集合查询加盟商信息
	 * Author:luojing
	 * 2016年6月20日 上午10:32:13
	 */
	public List<YcJoinInfo> getYcJoin(YcJoinInfo join);
}
