package com.brightsoft.controller.payment.yeepay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brightsoft.controller.base.BaseController;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.system.SysUserService;
import com.brightsoft.utils.yeepay.ZGTUtils;

@Controller
@RequestMapping("/yeePayCallBack")
public class YeePayCallBackController extends BaseController{
	
	
	private ServletContext sc;
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * /支付 后台通知
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/payCallBack")
	protected void payCallBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out	= response.getWriter();

		//第一步 获取回调密文data
		String data	= request.getParameter("data");
		SysUser user = new SysUser();
		user.setRealname(data);
		user.setCreattime(new Date());
		sysUserService.insert(user);
		//因为ONEKEY-手机一键支付方式的页面回调，不会回调数据，在此demo中做一个简单的判断
		if(data == null) {
			out.println("ONEKEY PAY SUCCESS.");
			SysUser user1 = new SysUser();
			user1.setRealname("支付datanull");
			user1.setCreattime(new Date());
			sysUserService.insert(user1);
			return;
		}
		
		//第二步 解密密文data，获取明文参数
		Map<String, String> dataMap	= ZGTUtils.decryptData(data);
		
		//第三步 hmac签名验证
		if(!ZGTUtils.checkHmac(dataMap, ZGTUtils.PAYAPICALLBACK_HMAC_ORDER)) {
			out.println("<br>hmac check error!<br>");
			SysUser user2 = new SysUser();
			user2.setRealname("支checkHmacErro");
			user2.setCreattime(new Date());
			sysUserService.insert(user2);
			return;
		}
		
		//此处 处理业务
		
		//第四步 判断通知方式，如果是后台通知则需要回写SUCCESS 
		String notifytype	= dataMap.get("notifytype");
		if("SERVER".equals(notifytype)) {
			out.println("SUCCESS");
			out.flush();
			out.close();
		} else {
			request.setAttribute("dataMap", dataMap);
			RequestDispatcher view	= request.getRequestDispatcher("jsp/payApiCallback.jsp");
			view.forward(request, response);
		}
	}
	
	

	/**
	 * 转账
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/cashTransferCallBack")
	protected void cashTransferCallBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//UTF-8编码

		PrintWriter out	= response.getWriter();

		//第一步 获取回调密文data
		String data	= request.getParameter("data");
		
		//第二步 解密密文data，获取明文参数
		Map<String, String> dataMap	= ZGTUtils.decryptData(data);
		
		//第三步 hmac签名验证
		if(!ZGTUtils.checkHmac(dataMap, ZGTUtils.CASHTRANSFERAPICALLBACK_HMAC_ORDER)) {
			out.println("<br>hmac check error!<br>");
			return;
		}
		
		//此处 处理业务
		SysUser user = new SysUser();
		user.setCreattime(new Date());
		sysUserService.insert(user);
		//第四步 回写SUCCESS 
		out.println("SUCCESS");
		out.flush();
		out.close();
		
	}
	
	

	/**
	 * 分账回调
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/divideCallBack")
	protected void divideCallBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out	= response.getWriter();
		
		//第一步 获取回调密文data
		String data	= request.getParameter("data");
		SysUser user = new SysUser();
		user.setRealname(data);
		user.setCreattime(new Date());
		sysUserService.insert(user);
		//第二步 解密密文data，获取明文参数
		Map<String, String> dataMap	= ZGTUtils.decryptData(data);
		
		//第三步 hmac签名验证
		if(!ZGTUtils.checkHmac(dataMap, ZGTUtils.DIVIDEAPICALLBACK_HMAC_ORDER)) {
			out.println("<br>hmac check error!<br>");
			return;
		}
		
		SysUser user2 = new SysUser();
		user2.setRealname("分账回调2");
		user2.setCreattime(new Date());
		sysUserService.insert(user2);
		
		//此处 处理业务
		
		//第四步 回写SUCCESS 
		out.println("SUCCESS");
		out.flush();
		out.close();
		
	}
	
	
	/**
	 * 修改 用户回调
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/modifyCallBack")
	protected void modifyCallBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//UTF-8编码
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out	= response.getWriter();
		SysUser user = new SysUser();
		user.setRealname("修改用户1");
		user.setCreattime(new Date());
		sysUserService.insert(user);
		//第一步 获取回调密文data
		String data					= request.getParameter("data");
		
		//第二步 解密密文data，获取明文参数
		Map<String, String> dataMap	= ZGTUtils.decryptData(data);
		System.out.println("222");
		//第三步 hmac签名验证
		if(!ZGTUtils.checkHmac(dataMap, ZGTUtils.MODIFYREQUESTAPICALLBACK_HMAC_ORDER)) {
			System.out.println("333");
			out.println("<br>hmac check error!<br>");
			return;
		}
		
		//此处 处理业务
		
		//第四步 回写SUCCESS 
		out.println("SUCCESS");
		out.flush();
		out.close();
		
	}
}
