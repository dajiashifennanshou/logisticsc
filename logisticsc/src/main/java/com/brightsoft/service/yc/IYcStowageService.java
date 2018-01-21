package com.brightsoft.service.yc; 
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.brightsoft.entity.ResultEntity;
import com.brightsoft.entity.YcStowage;
import com.brightsoft.utils.yc.FengRuntimeException;
import com.brightsoft.utils.yc.ISqlServer; 
/** 
* YcStowage服务接口层 
* Auther:FENG 
*/ 
public interface IYcStowageService extends ISqlServer<YcStowage> {  

	/**
	 * 根据条件获取所有的配载单信息
	 * Author:FENG
	 * 2016年6月24日
	 * @param ys
	 * @return
	 */
	public List<YcStowage> getAllStowageList(YcStowage ys);
	/**
	 * 配载单完成
	 * Author:FENG
	 * 2016年7月26日
	 * @param ys
	 * @return
	 */
	public ResultEntity stowageOver(YcStowage ys);
	/**
	 * 根据配载单号修改配载单状态
	 * Author:FENG
	 * 2016年6月30日
	 * @param stowageNo
	 * @return
	 */
	public Integer modStatusByNo(YcStowage ys);
	/**
	 * 更具配载单号修改配载状态
	 * Author:FENG
	 * 2016年7月25日
	 * @param request
	 * @param ys
	 * @return
	 */
	public ResultEntity addStowageInfo(HttpServletRequest request,YcStowage ys)throws FengRuntimeException ;
}
