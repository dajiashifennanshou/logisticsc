package com.yc.Dao; 
import java.util.List;

import com.yc.Entity.XZQHInfo;
import com.yc.Tool.ISqlDao; 
/** 
* lc_xzqh_info数据层 
* Auther:FENG 
*/ 
public interface XZQHInfoDao extends ISqlDao<XZQHInfo> {  
	
	public List<XZQHInfo> getXZQH(String pid);
	
	/**
	 * 根据地名查询代码
	 * @Author:luojing
	 * @2016年7月28日 下午3:05:11
	 */
	public XZQHInfo getXZQHID(XZQHInfo zxqh);
	
	public String getXZQHIDBYID(String id);
}
