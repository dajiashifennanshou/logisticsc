package com.brightsoft.service.system.platform;

import javax.servlet.http.HttpSession;

import com.brightsoft.model.LineInfo;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.User;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

/**
 * 2016年3月17日 下午12:24:38
 *  货运交易系统--专线商铺管理
 * @author zhouna
 */
public interface OperationCompanyService {

	/**分页查询商铺信息
	 * @param session 
	 * @param platformUserCompany 
	 * */
	Result getAllPlatformCompanyinfo(Page<?> page, PlatformUserCompany platformUserCompany);

	/**通过id查看详细信息*/
	PlatformUserCompany platformCompanyById(Long id);
	
	/**通过recommended修改 启用  0  停用  1 */
	Integer CompanyinfochangeStatus(PlatformUserCompany platformUserCompany);

	/**当前公司的信息*/
	Result getlineshopCompang(User user, OutletsInfo outletsInfo, Page<?> page);

	/**获取当前线路*/
	Result getlineshoplines(User user, LineInfo lineInfo, Page<?> page);

	

}
