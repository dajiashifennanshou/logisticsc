package com.brightsoft.controller.system;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;




import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.brightsoft.Constant.Constant;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.model.SysMenu;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.system.SysMenuService;
import com.brightsoft.service.system.SysUserService;
import com.brightsoft.service.system.TestService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.MD5;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.yc.FengUtil;



@Controller
@RequestMapping("/")
public class SystemLoginController extends BaseController {
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysMenuService sysMenuService;

	@Autowired 
	private TestService TestService;

	@RequestMapping("/tologin")
	@ResponseBody
	public Result tologin(@RequestBody SysUser temuser,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("------------------------------------------------");
		Result ret = new Result();
		
		System.out.println(MD5.getMD5Code(temuser.getPassword()));
		SysUser user = sysUserService.selectByNameAndPwd(temuser.getUsername(), MD5.getMD5Code(temuser.getPassword()));
		if(user!=null && user.getAdminstatus() !=null){
			
			if(user.getAdminstatus() == Const.STATE_YES){
				request.getSession().setAttribute("user", user);
				ret.setResult(true);
			}else if(user.getAdminstatus() == Const.STATE_NO){
				ret.setMsg("用户已经被停用");
				ret.setResult(false);
			}
			
		}else{
			ret.setMsg("用户名或密码错误");
			ret.setResult(false);
		}
		
		return ret;
	}
	
	@RequestMapping("/ycLogin")
	public void ycLogin(@RequestBody SysUser temuser,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			SysUser user = sysUserService.selectByNameAndPwd(temuser.getUsername(), MD5.getMD5Code(temuser.getPassword()));
			if(user!=null && user.getAdminstatus() !=null){
				if(user.getIsBranch()==1){
					FengUtil.RuntimeExceptionFeng("该账户不是云仓管理人员..");
				}else{
					if(user.getAdminstatus() == Const.STATE_YES){
						request.getSession().setAttribute(Constant.YCLOGINUSER, user);
					}else if(user.getAdminstatus() == Const.STATE_NO){
						FengUtil.RuntimeExceptionFeng("该账户已被停用..");
					}
				}
			}else{
				FengUtil.RuntimeExceptionFeng("用户名或密码错误..");
			}
			FengUtil.OUTAPPSUCCESS(Constant.RESULT_SUCCESS_CODE, user, response);
		}catch(Exception e){
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
		
		
	}
	
	@RequestMapping("/login")
	public ModelAndView login(
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
//		SysUser user = sysUserService.selectByPrimaryKey(1l);

		mv.setViewName("/login");
			return mv;
	}

	
	@RequestMapping("/main")
	public ModelAndView main(
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		//sysUserService.getMenusByUser(sysUser);
		List<SysMenu> list = sysMenuService.getMenusByUser(user);
		String menuJson = JSON.toJSONString(list,
				SerializerFeature.DisableCircularReferenceDetect);


		mv.addObject("menuList", menuJson);
		mv.setViewName("/main/index");
			return mv;
	}
	
	/*@RequestMapping("/Testlogin")
	@ResponseBody
	public String Testlogin(
			HttpServletRequest request, HttpServletResponse response) {
		SysUser user = sysUserService.selectByPrimaryKey(1);

		System.out.println("aaaaa");
			return "1";
	}*/
	
	
	@RequestMapping("/demoLink")
	public ModelAndView register(
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		SysUser user = sysUserService.selectByPrimaryKey(1l);
		
//		Test test = TestService.asd(1l);
		//System.out.println(test.getName());
		//System.out.println("aaa");
		//mv.addObject("goodsTree", "aaaaa");
		mv.setViewName("/system/link");
			return mv;
	}
	
	@RequestMapping("/exitsystem")
	public ModelAndView exitSystem(HttpSession session){
		ModelAndView mv = new ModelAndView();
		session.removeAttribute("user");
		mv.setViewName("../../../login");
		return mv;
	}
}
