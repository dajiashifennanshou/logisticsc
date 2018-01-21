package com.brightsoft.controller.tms.platform;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.model.Menu;
import com.brightsoft.model.User;
import com.brightsoft.service.platform.PlatformUserCompanyService;
import com.brightsoft.service.tms.platform.MenuService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.TmsUserService;
import com.brightsoft.utils.Const;

@Controller
@RequestMapping("/tms")
public class IndexController extends BaseController{

	@Autowired
	private TmsUserService tmsUserService;
	
	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private PlatformUserCompanyService platformUserCompanyService;
	
	/**
	 * 设置默认网点信息
	 * @param session
	 * @param outletsId
	 * @return
	 */
	@RequestMapping("/setdefaultoutletsinfo")
	public String setDefalutOutletsInfo(HttpSession session, String outletsId,HttpServletRequest request){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user.getUserType() == Const.TMS_USER_TYPE_ENTERPRISE
				||user.getUserType() == Const.TMS_USER_TYPE_LOGISTICSC){
			if(StringUtils.isNotBlank(outletsId)){
				user.setOutletsId(Long.parseLong(outletsId));
			}else{
				user.setOutletsId(null);
			}
			session.removeAttribute(SystemConstant.TMS_USER_SESSION);
			session.setAttribute(SystemConstant.TMS_USER_SESSION, user);
		}
		return "redirect:/tms/index.shtml?outletsId="+outletsId;
	}
	
	/**
	 * 跳转到 TMS 首页
	 * @return
	 */
	@RequestMapping("/tofirstpage")
	public String toTmsFirstPage(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user!=null){
			List<Menu> menus  = menuService.selectByUserId(user.getId());
			String menuJson = JSON.toJSONString(menus);
			request.setAttribute("menuList", menuJson);
		}
		return "/tms/platform/first-page";
	}
	
	/**
	 * TMS首页 菜单权限控制
	 * @param name
	 * @param request
	 * @return
	 */
	@RequestMapping("/controlmenu")
	@ResponseBody
	public String controlMenu(String name, HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user == null){
			return null;
		}
		List<Menu> menus  = menuService.selectByUserId(user.getId());
		for (Menu menu : menus) {
			if(menu.getMenuName().equals(name)){
				return menu.getMenuUrl();
			}
			List<Menu> subMenus = menu.getSubMenus();
			for (Menu subMenu : subMenus) {
				if(subMenu.getMenuName().equals(name)){
					return subMenu.getMenuUrl();
				}
			}
		}
		return null;
	}
	
	@RequestMapping("/tostartsitemanager")
	public String toTmsStartSiteManager(){
		
		
		return "/tms/platform/startsitemanager/index";
	}
	
	@RequestMapping("/tosystemsetting")
	public String toTmsSystemSetting(){
		
		
		return "/tms/platform/systemsetting/index";
	}
	
	@RequestMapping("/systemmanager/tolinemanager")
	public String toTmsSystemManagerLineManager(){
		return "/tms/platform/systemmanager/line-manager";
	}
	
}
