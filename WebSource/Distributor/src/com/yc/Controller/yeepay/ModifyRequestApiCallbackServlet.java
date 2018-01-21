package com.yc.Controller.yeepay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.Tool.yeepay.ZGTUtils;

/**
 * @author: yingjie.wang    
 * @since : 2015-10-01 21:05
 */

public class ModifyRequestApiCallbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModifyRequestApiCallbackServlet() {
        super();
    }

	//后台通知方式GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//UTF-8编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out	= response.getWriter();

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
		
		//第四步 回写SUCCESS 
		out.println("SUCCESS");
		out.flush();
		out.close();
		
	}
}
