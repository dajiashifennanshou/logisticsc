package com.brightsoft.service.system.platform;

import javax.servlet.http.HttpSession;

import com.brightsoft.model.PlatformInsurance;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

/**
 * 2016年3月21日 下午6:15:43
 * @author zhouna
 */
public interface OperSogoManageService {
  
	/**查询运营货运交易系统的商户管理
	 * @param session 
	 * @param session */
	Result OperSogoListfindall( HttpSession session, Page<?> page, PlatformInsurance platformInsurance);

	/**通过id查看保险信息*/
	PlatformInsurance OperSogoById(long id);

}
