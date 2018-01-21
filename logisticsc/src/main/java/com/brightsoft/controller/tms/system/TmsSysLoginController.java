package com.brightsoft.controller.tms.system;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.model.SysMenu;
import com.brightsoft.model.SysUser;
import com.brightsoft.model.User;
import com.brightsoft.service.system.SysMenuService;
import com.brightsoft.service.system.SysUserService;
import com.brightsoft.utils.MD5;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/sys")
public class TmsSysLoginController {

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping("/tologin")
	public ModelAndView toLogin(){
		ModelAndView mav = new ModelAndView("/tms/system/login");
		return mav;
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public Result login(@RequestBody SysUser user,HttpSession session){
		Result result = new Result();
		user = sysUserService.selectByNameAndPwd(user.getUsername(), MD5.getMD5Code(user.getPassword()));
		if(user != null){
			session.setAttribute(SystemConstant.TMS_SYS_USER_SESSION, user);
			result.setResult(true);
		}else{
			result.setMsg("用户名或密码错误");
			result.setResult(false);
		}
		return result;
	}
	
	@RequestMapping("/main")
	public ModelAndView toMain(HttpSession session){
		ModelAndView mav = new ModelAndView("/tms/system/main/index");
		SysUser user = (SysUser)session.getAttribute(SystemConstant.TMS_SYS_USER_SESSION);
		List<SysMenu> list = sysMenuService.getMenusByUser(user);
		String menuJson = JSON.toJSONString(list,
				SerializerFeature.DisableCircularReferenceDetect);
		mav.addObject("menuList", menuJson);
		mav.addObject("sysUser", user);
		return mav;
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public void logout(){
		SecurityUtils.getSubject().logout();
	}
	
	/**
	 * 判断是否是网点管理员
	 * @param session
	 * @return
	 */
	@RequestMapping("/isOutlets")
	@ResponseBody
	public Result isOutlets(HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			if(user.getOutletsId() != null){
				result.setResult(true);
			}else{
				result.setMsg("请选择网点");
			}
		}else{
			result.setMsg("请先登陆");
		}
		return result;
	}
}
