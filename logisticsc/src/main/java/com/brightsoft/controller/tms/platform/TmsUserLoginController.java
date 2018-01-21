package com.brightsoft.controller.tms.platform;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.exception.CompanyIsForbidException;
import com.brightsoft.exception.RoleIsDisabledException;
import com.brightsoft.model.Menu;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.MenuService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.TmsUserService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.MD5;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms")
public class TmsUserLoginController {
	
	@Autowired
	private TmsUserService user;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private TmsUserService tmsUserService;

	/**
	 * 登录认证
	 * @param loginName
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Result login(HttpSession session){
		Result result = new Result();
		PlatformUser user = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		if(user==null){
			result.setMsg("请先登录");
		}else{
			try{
				SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getLoginName(), user.getPassword()));
				User tmsUser = tmsUserService.selectByLnameAndPwd(user.getLoginName(),user.getPassword());
				result.setResult(true);
				session.setAttribute(SystemConstant.TMS_USER_SESSION, tmsUser);
				session.setAttribute("userType", "logst");
				System.out.println(tmsUser.getUserType()+"----------");
			}catch(UnknownAccountException e){
//				result.setMsg("你没有权限访问该资源");
				result.setMsg("请先申请成为专线商");
			}catch(CompanyIsForbidException e){
				result.setMsg("企业账号已被冻结");
			}catch(LockedAccountException e){
				result.setMsg("账号已被禁用，请联系管理员");
			}catch(RoleIsDisabledException e){
				result.setMsg("用户角色已被禁用，请联系管理员");
			}catch(AuthenticationException e){
//				result.setMsg("你还没权限访问该资源，请联系管理员");
				result.setMsg("请先申请成为专线商");
			}
		}
		return result;
	}
	
	@RequestMapping("/index")
	@ResponseBody
	public ModelAndView toIndex(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/tms/platform/index");
		HttpSession session = request.getSession();
		String paramOutletsId = request.getParameter("outletsId");
		if(StringUtils.isNotBlank(paramOutletsId)){
			session.setAttribute("outletsId", paramOutletsId);
		}
		
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user!=null){
			List<Menu> menus  = menuService.selectByUserId(user.getId());
			String menuJson = JSON.toJSONString(menus);
			mav.addObject("menuList", menuJson);
			
			Long outletsId = user.getOutletsId();
			if(user.getUserType() == Const.TMS_USER_TYPE_ENTERPRISE
					||user.getUserType() == Const.TMS_USER_TYPE_LOGISTICSC){
				List<OutletsInfo> outletsInfos = outletsService.selectByCompanyId(user.getCompanyId());
				mav.addObject("outletsInfos", outletsInfos);
			}else{
				OutletsInfo outletsInfo = outletsService.selectByPrimaryKey(outletsId);
				mav.addObject("outletsInfo", outletsInfo);
			}
		}
		return mav;
	}
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		//SecurityUtils.getSubject().logout();
		session.removeAttribute(SystemConstant.TMS_USER_SESSION);
		session.removeAttribute(SystemConstant.USER_SESSION);
		return "redirect:/";
	}
	
}
