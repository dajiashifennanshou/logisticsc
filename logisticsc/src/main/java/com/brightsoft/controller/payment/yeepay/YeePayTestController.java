package com.brightsoft.controller.payment.yeepay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.controller.base.BaseController;

@Controller
@RequestMapping("/yeePayTest")
public class YeePayTestController extends BaseController{

	@RequestMapping("/paymain")
	public ModelAndView paymain(
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
//		SysUser user = sysUserService.selectByPrimaryKey(1l);
		String  path = request.getContextPath();
		mv.setViewName("/payment/yeepaytest/index");
		return mv;
	}
	
	@RequestMapping("/toPayApiRequest")
	public ModelAndView topay(
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
//		SysUser user = sysUserService.selectByPrimaryKey(1l);
		
		mv.setViewName("/payment/yeepaytest/44payApiRequest");
		return mv;
	}
	
	
	@RequestMapping("/toRegister")
	public ModelAndView toRegister(
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
//		SysUser user = sysUserService.selectByPrimaryKey(1l);
		
		mv.setViewName("/payment/yeepaytest/41registerApiRequest");
		return mv;
	}
	
	
	@RequestMapping("/toModify")
	public ModelAndView toModify(
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
//		SysUser user = sysUserService.selectByPrimaryKey(1l);
		
		mv.setViewName("/payment/yeepaytest/41registerApiRequest");
		return mv;
	}
	
}
