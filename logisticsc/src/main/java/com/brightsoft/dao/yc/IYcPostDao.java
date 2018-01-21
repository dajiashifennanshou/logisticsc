package com.brightsoft.dao.yc; 
import java.util.List;

import com.brightsoft.entity.YcPost;
import com.brightsoft.utils.yc.ISqlDao; 
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
