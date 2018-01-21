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
 * @since : 2015-10-03 22:00
 */

public class CashTransferApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CashTransferApiServlet() {
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
		String ledgerno				= formatStr(request.getParameter("ledgerno"));	
		String amount				= formatStr(request.getParameter("amount"));	
		String callbackurl			= formatStr(request.getParameter("callbackurl"));	
		String feetype				= formatStr(request.getParameter("feetype"));

		Map<String, String> params	= new HashMap<String, String>();
		params.put("requestid", requestid);
		params.put("ledgerno", ledgerno);
		params.put("amount", amount);
		params.put("callbackurl", callbackurl);
		params.put("feetype", feetype);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.CASHTRANSFERAPI_REQUEST_HMAC_ORDER);
		
		//第二步 发起请求
		String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.CASHTRANSFERAPI_NAME);
		Map<String, String> responseMap	= ZGTUtils.httpPost(requestUrl, data);
		
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
		if(!ZGTUtils.checkHmac(responseDataMap, ZGTUtils.CASHTRANSFERAPI_RESPONSE_HMAC_ORDER)) {
			out.println("<br>hmac check error!<br>");
			return;
		}
		
		//第七步 进行业务处理
		request.setAttribute("responseDataMap", responseDataMap);
		RequestDispatcher view	= request.getRequestDispatcher("jsp/416cashTransferApiResponse.jsp");
		view.forward(request, response);
	}
}
