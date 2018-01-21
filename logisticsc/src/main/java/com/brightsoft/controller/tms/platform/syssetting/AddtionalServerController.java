package com.brightsoft.controller.tms.platform.syssetting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.model.AdditionalServerConf;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.AdditionalServerServiceImpl;

/**
 * 
 * 系统设置 - 增值服务管理Controller
 */
@Controller
@RequestMapping("/tms/additionalserver")
public class AddtionalServerController extends BaseController{

	@Autowired
	private AdditionalServerServiceImpl additionalServerService;
	
	/**
	 * 跳转到TMS系统设置 增值服务管理
	 * @return
	 */
	@RequestMapping("/topage")
	public String toTmsSystemManagerAdditionalServer(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		Long outletsId = user.getOutletsId();
		if(outletsId != null){
			AdditionalServerConf additionalServer = additionalServerService.selectByOutletsId(outletsId);
			request.setAttribute("additionalServer", additionalServer);
		}
		return "/tms/platform/systemmanager/additionalserver/additional-server";
	}
	
	/**
	 * 获取增值服务信息
	 * @return
	 */
	/*@RequestMapping("/getadditionalserverinfo")
	@ResponseBody
	public String getAdditionalServerInfo(HttpSession session){
		Long companyId = ((User)session.getAttribute(SystemConstant.TMS_USER_SESSION)).getCompanyId();
		AdditionalServerConf additionalServer = additionalServerService.selectByCompanyId(companyId);
		return JSONObject.toJSONString(additionalServer);
	}*/
	
	/**
	 * 保存 增值服务配置信息
	 * @param additionalServer
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(AdditionalServerConf additionalServer, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		Long companyId = user.getCompanyId();
		Long outletsId = user.getOutletsId();
		if(outletsId == null){
			return FAILURE_STATUS;
		}
		additionalServer.setCompanyId(companyId);
		additionalServer.setOutletsId(outletsId);
		int rows = additionalServerService.save(additionalServer);
		if(rows > 0){
			return SUCCESS_STATUS;
		}else{
			return FAILURE_STATUS;
		}
	}
	
	/**
	 * 获取增值服务信息列表 (货运交易系统)
	 * @return
	 */
	@RequestMapping("/getadditionalserverofplatform")
	@ResponseBody
	public Object getAdditionalServerOfPlatform(BaseSearchParams params){
		return additionalServerService.selectByParamsOfPlatform(params);
	}
}
