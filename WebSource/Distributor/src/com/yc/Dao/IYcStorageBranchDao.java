package com.yc.Dao; 

import java.util.List;

import com.yc.Entity.YcStorageBranch;
import com.yc.Tool.ISqlDao; 
/** 
* YcStorageBranch数据层 
* Auther:luojing 
*/ 
public interface IYcStorageBranchDao extends ISqlDao<YcStorageBranch> {  
	
	/**
	 * 集合查询云仓编号
	 * Author:luojing
	 * 2016年6月14日 下午1:38:57
	 */
	public List<YcStorageBranch> getBranchNoList();
}
