package com.brightsoft.dao.yc; 

import java.util.List;

import com.brightsoft.entity.YcStorageBranch;
import com.brightsoft.utils.yc.ISqlDao; 
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
