package com.yc.Service;

import com.yc.Entity.YcOutStorage;
import com.yc.Tool.Pager;

/** 
* YcOutStorage服务接口层 
* Auther:FENG 
*/ 
public interface IYcOutStorageService {  
	public Pager<YcOutStorage> getListPagerInfo(Pager<YcOutStorage> pager,YcOutStorage ycoutstorage);
}
