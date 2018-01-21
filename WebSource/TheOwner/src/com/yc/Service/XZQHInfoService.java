package com.yc.Service;

import java.util.List;

import com.yc.Entity.XZQHInfo;
import com.yc.Tool.ISqlServer;

/** 
* XZQHInfo服务接口层 
* Auther:FENG 
*/ 
public interface XZQHInfoService extends ISqlServer<XZQHInfo> {  
	
	/**
	 *  查询地址
	 * @Author:luojing
	 * @2016年7月28日 上午9:45:49
	 */
	public List<XZQHInfo> getXZQH();

}
