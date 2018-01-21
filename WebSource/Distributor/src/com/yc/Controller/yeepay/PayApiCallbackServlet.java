package com.yc.Controller.yeepay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.Tool.yeepay.ZGTUtils;

/**
 * @author: yingjie.wang    
 * @since : 2015-10-01 17:00
 */

public class PayApiCallbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PayApiCallbackServlet() {
        super();
    }

	//掌柜通页面及后台通知方式均为GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//UTF-8编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out	= response.getWriter();

		//第一步 获取回调密文data
		String data					= request.getParameter("data");

		//因为ONEKEY-手机一键支付方式的页面回调，不会回调数据，在此demo中做一个简单的判断
		if(data == null) {
			out.println("ONEKEY PAY SUCCESS.");
			return;
		}
		
		//第二步 解密密文data，获取明文参数
		Map<String, String> dataMap	= ZGTUtils.decryptData(data);
		
		//第三步 hmac签名验证
		if(!ZGTUtils.checkHmac(dataMap, ZGTUtils.PAYAPICALLBACK_HMAC_ORDER)) {
			out.println("<br>hmac check error!<br>");
			return;
		}
		
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
}
