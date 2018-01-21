package com.brightsoft.service.yc; 
import javax.servlet.http.HttpServletRequest;

import com.brightsoft.entity.ResultEntity;
import com.brightsoft.entity.YcOutStorage;
import com.brightsoft.utils.yc.ISqlServer; 
/** 
* YcOutStorage服务接口层 
* Auther:FENG 
*/ 
public interface IYcOutStorageService extends ISqlServer<YcOutStorage> {  
	/**
	 * 出库
	 * Author:FENG
	 * 2016年7月22日
	 * @param request
	 * @param os
	 * @return
	 */
	public ResultEntity outStowage(HttpServletRequest request,YcOutStorage os);
}
