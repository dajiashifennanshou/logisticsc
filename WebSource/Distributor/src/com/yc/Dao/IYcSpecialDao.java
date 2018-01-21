package com.yc.Dao; 
import java.util.List;

import com.yc.Entity.YcSpecial;
import com.yc.Tool.ISqlDao; 
/** 
* YcSpecial数据层 
* Auther:FENG 
*/ 
public interface IYcSpecialDao extends ISqlDao<YcSpecial> {  
	/**
	 * 查询没有注册加盟商的专线用户
	 * Author:luojing
	 * 2016年6月23日 下午4:32:14
	 */
	public List<YcSpecial> getUnRegisterSpecial();
}
