package com.brightsoft.controller.system.platform;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.model.LineInfo;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.SysUser;
import com.brightsoft.model.User;
import com.brightsoft.service.system.platform.OperationCompanyService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

/**
 * 2016年3月17日 上午11:59:44
 * 货运交易系统--专线商铺管理
 * @author zhouna
 */
@Controller
@RequestMapping("/system/yypt/info")
public class OperationCompanyController {
	
	@Autowired
	private OperationCompanyService operationCompanyService;
	
	/**货运交易系统的商铺管理*/
	@RequestMapping("/list")
	public String getAllPlatformCompanyinfo() {
		return "/system/platform/lineshopcCompany/lineshoplist";
	}
	
	/**货运交易系统的商铺管理*/
	@RequestMapping(value= "/listdata",method= RequestMethod.POST)
	@ResponseBody
	public Result getAllPlatformCompanyinfoData(Page<?> page,PlatformUserCompany platformUserCompany) {
		Result result =  operationCompanyService.getAllPlatformCompanyinfo(page,platformUserCompany);
		result.setResult(true);
		return result;
	}
	
	/**通过id查看详细信息
	 * @throws IOException 
	 * @throws ServletException */
	@RequestMapping("/infobyid")
	@ResponseBody
	public Object platformeditlist(Long id) {
		PlatformUserCompany platformUserCompany = operationCompanyService.platformCompanyById(id);
		return platformUserCompany;
	}

	
	/**通过货运交易系统recommended
	 * 启用  0
	 * 停用  1
	 * */
	@RequestMapping("/infoStatus")
	@ResponseBody
	public Result CompanyinfochangeStatus(PlatformUserCompany platformUserCompany) {
		Result result = new Result();
			Integer companyinfochangeStatus = operationCompanyService.CompanyinfochangeStatus(platformUserCompany);
			if (companyinfochangeStatus == null) {
				result.setResult(false);
			}else {
				result.setResult(true);
			}
		return result;
	}
	
	/**
	 * 跳转到公司详情页面
	 * 2016年3月23日 下午2:35:39
	 * @return 
	 * @author zhouna
	 */
	@RequestMapping("/lineshopment")
	public String lineshopment(HttpSession session,HttpServletRequest request) {
		String id = request.getParameter("id");
		long comyid = Long.valueOf(id);
		PlatformUserCompany platformUserCompany = operationCompanyService.platformCompanyById(comyid);
		request.setAttribute("platformUserCompany", platformUserCompany);
		return "/system/platform/lineshopcCompany/lineshopmanage";
	}
	
	/**
	 * 当前公司的信息
	 * 2016年3月23日 下午2:36:21
	 * @param page
	 * @param outletsInfo
	 * @param session
	 * @return 
	 * @author zhouna
	 */
	@RequestMapping("/getoutlets")
	@ResponseBody
	public Result getlineshopCompang(HttpSession session,Page<?> page,OutletsInfo outletsInfo){
		Result result = new Result();
		SysUser sysuser = (SysUser) session.getAttribute(SystemConstant.YYPT_USER_SESSION);
		if(sysuser!=null){
			User user = new User();
			user.setId(sysuser.getId());
			result = operationCompanyService.getlineshopCompang(user, outletsInfo, page);
		}
		return result;
	}
	
	/**
	 * 获取当前线路
	 * 2016年3月23日 下午3:49:58
	 * @param page
	 * @param lineInfo
	 * @param session
	 * @return 
	 * @author zhouna
	 */
	@RequestMapping("/getlines")
	@ResponseBody
	public Result getlineshoplines(Page<?> page,LineInfo lineInfo,HttpSession session, @Param("companyId")long companyId){
		Result result = new Result();
		SysUser sysuser = (SysUser) session.getAttribute(SystemConstant.YYPT_USER_SESSION);
		if(sysuser!=null){
			User user = new User();
			user.setId(sysuser.getId());
			user.setCompanyId(companyId);
			result = operationCompanyService.getlineshoplines(user, lineInfo, page);
		}
		return result;
		
  	}
	
}
