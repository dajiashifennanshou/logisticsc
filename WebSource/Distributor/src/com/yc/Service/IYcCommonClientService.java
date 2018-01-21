package com.yc.Service; 
import com.yc.Entity.YcCommonClient; 
import com.yc.Tool.Pager; 
/** 
* YcCommonClient服务接口层 
* Auther:FENG 
*/ 
public interface IYcCommonClientService {
	
	/**
	 * 根据条件获取分页数据
	 * Author:FENG
	 * 2016年5月11日
	 * @param pager
	 * @param id
	 * @return
	 */
	public Pager<YcCommonClient> getListPagerInfo(Pager<YcCommonClient> pager,YcCommonClient cc);

}
