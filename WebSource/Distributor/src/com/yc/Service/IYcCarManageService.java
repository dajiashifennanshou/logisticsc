package com.yc.Service; 
import java.util.List;

import com.yc.Entity.YcCarManage; 
import com.yc.Tool.ISqlServer; 
/** 
* YcCarManage服务接口层 
* Auther:FENG 
*/ 
public interface IYcCarManageService extends ISqlServer<YcCarManage> {  
	public List<YcCarManage> getYcCarAllList(YcCarManage car);
	/**
	 * 根据车牌号修改车辆使用状态
	 * Author:FENG
	 * 2016年6月24日
	 * @param ycm
	 * @return
	 */
	public Integer modCarByNo(YcCarManage ycm);
	/**
	 * 根据配载单的编号去修改车辆的状态为未使用
	 * Author:FENG
	 * 2016年6月24日
	 * @param s
	 * @param sNo
	 * @return
	 */
	public Integer modCarStatusByStowage(Integer s,String sNo);
}
