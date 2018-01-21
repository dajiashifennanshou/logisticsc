package com.yc.Service; 

import java.util.List;

import com.yc.Entity.YcStorageBranch;
import com.yc.Tool.ISqlServer; 
/** 
* YcStorageBranch服务接口层 
* Auther:luojing 
*/ 
public interface IYcStorageBranchService extends ISqlServer<YcStorageBranch> {  
	
	/**
	 * 集合查询云仓编号
	 * Author:luojing
	 * 2016年6月14日 下午1:38:57
	 */
	public List<YcStorageBranch> getBranchNoList();
	
}
