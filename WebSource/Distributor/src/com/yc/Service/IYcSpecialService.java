package com.yc.Service; 
import java.util.List;

import com.yc.Entity.YcSpecial;
import com.yc.Tool.ISqlServer; 
/** 
* YcSpecial服务接口层 
* Auther:FENG 
*/ 
public interface IYcSpecialService extends ISqlServer<YcSpecial> {
	
	/**
	 * 查询没有注册加盟商的专线用户
	 * Author:luojing
	 * 2016年6月23日 下午4:32:14
	 */
	public List<YcSpecial> getUnRegisterSpecial();

}
