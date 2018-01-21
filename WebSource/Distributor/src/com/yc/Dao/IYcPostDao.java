package com.yc.Dao; 
import java.util.List;

import com.yc.Entity.YcPost;
import com.yc.Tool.ISqlDao; 
/** 
* YcPost数据层 
* Auther:FENG 
*/ 
public interface IYcPostDao extends ISqlDao<YcPost> {  

	/**
	 * 职位集合查询
	 * Author:luojing
	 * 2016年6月16日 下午4:16:25
	 */
	public List<YcPost> getPost(YcPost post);
}
