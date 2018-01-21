package com.yc.Controller.yeepay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.Tool.yeepay.ZGTUtils;

/**
 * @author: yingjie.wang    
 * @since : 2015-10-03 16:32
 */

public class DivideApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DivideApiServlet() {
        super();
    }

	public String formatStr(String text) {
		return text == null ? "" : text.trim();
	}
	
	//get请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	//post请求
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//UTF-8编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out	= response.getWriter();

		//获取请求参数
		String requestid			= formatStr(request.getParameter("requestid"));	
		String orderrequestid		= formatStr(request.getParameter("orderrequestid"));	
		String divideinfo			= formatStr(request.getParameter("divideinfo"));
		String callbackurl			= formatStr(request.getParameter("callbackurl"));

		Map<String, String> params	= new HashMap<String, String>();
		params.put("requestid", requestid);
		params.put("orderrequestid", orderrequestid);
		params.put("divideinfo", divideinfo);
		params.put("callbackurl", callbackurl);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.DIVIDEAPI_REQUEST_HMAC_ORDER);
		
		//第二步 发起请求
		String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.DIVIDEAPI_NAME);
		Map<String, String> responseMap	= ZGTUtils.httpPost(requestUrl, data);
		
		System.out.println(responseMap);
		
		//第三步 判断请求是否成功，
		if(responseMap.containsKey("code")) {
			out.println(responseMap);
			return;
		}

		//第四步 解密同步响应密文data，获取明文参数
		String responseData	= responseMap.get("data");
		Map<String, String> responseDataMap	= ZGTUtils.decryptData(responseData);
		
		System.out.println("易宝的同步响应：" + responseMap);
		System.out.println("data解密后明文：" + responseDataMap);
		
		//第五步 code=1时，方表示接口处理成功
		if(!"1".equals(responseDataMap.get("code"))) {
			out.println("code = " + responseDataMap.get("code") + "<br>");
			out.println("msg  = " + responseDataMap.get("msg"));
			return;
		}
		
		//第六步 hmac签名验证
		if(!ZGTUtils.checkHmac(responseDataMap, ZGTUtils.DIVIDEAPI_RESPONSE_HMAC_ORDER)) {
			out.println("<br>hmac check error!<br>");
			return;
		}
		
		//第七步 进行业务处理
		request.setAttribute("responseDataMap", responseDataMap);
		RequestDispatcher view	= request.getRequestDispatcher("jsp/410divideApiResponse.jsp");
		view.forward(request, response);
	}
}
