package com.yc.Service;

import com.yc.Entity.LcBulletinInfo;
import com.yc.Tool.Pager;

/** 
* lc_bulletin_info服务接口层 
* Auther:FENG 
*/ 
public interface BulletinInfoService {  
	/**
	 * 分页查询
	 * @Author:luojing
	 * @2016年7月23日 下午3:24:59
	 */
	public Pager<LcBulletinInfo> getListPagerInfo(Pager<LcBulletinInfo> pager);
}
