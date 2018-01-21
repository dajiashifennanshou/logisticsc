package com.brightsoft.controller.tms.platform.syssetting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.PlatformBaseSearchParams;
import com.brightsoft.model.BasicData;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.BasicDataService;

/**
 * 基础数据管理
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/tms/basicdata")
public class BasicDataController extends BaseController{

	@Autowired
	private BasicDataService basicDataService;
	
	/**
	 * 跳转到TMS系统设置 基础数据管理
	 * @return
	 */
	@RequestMapping("/tobasicdatapage")
	public String toTmsBasicDataPage(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		BasicData basicData = basicDataService.selectByCompanyId(user.getCompanyId());
		request.setAttribute("basicData", basicData);
		return "/tms/platform/systemmanager/basicdata/basic-data";
	}
	
	/**
	 * 保存基础数据
	 * @param basicData
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String saveBasicData(BasicData basicData, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		basicData.setCompanyId(user.getCompanyId());
		int rows = basicDataService.saveBasicData(basicData);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 获取 基础数据列表 (货运交易系统)
	 * @param params
	 * @return
	 */
	@RequestMapping("/getbasicdataofplatform")
	@ResponseBody
	public Object getBasicDataOfPlatform(PlatformBaseSearchParams params){
		return basicDataService.selectByParamsOfPlatform(params);
	}
}
