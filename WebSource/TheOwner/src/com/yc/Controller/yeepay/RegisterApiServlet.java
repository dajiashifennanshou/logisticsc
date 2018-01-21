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
 * @since : 2015-10-01 19:05
 */

public class RegisterApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterApiServlet() {
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
		String requestid           = formatStr(request.getParameter("requestid"));
		String bindmobile          = formatStr(request.getParameter("bindmobile"));
		String customertype        = formatStr(request.getParameter("customertype"));
		String signedname          = formatStr(request.getParameter("signedname"));
		String linkman             = formatStr(request.getParameter("linkman"));
		String idcard              = formatStr(request.getParameter("idcard"));
		String businesslicence     = formatStr(request.getParameter("businesslicence"));
		String legalperson         = formatStr(request.getParameter("legalperson"));
		String minsettleamount     = formatStr(request.getParameter("minsettleamount"));
		String riskreserveday      = formatStr(request.getParameter("riskreserveday"));
		String bankaccountnumber   = formatStr(request.getParameter("bankaccountnumber"));
		String bankname            = formatStr(request.getParameter("bankname"));
		String accountname         = formatStr(request.getParameter("accountname"));
		String bankaccounttype     = formatStr(request.getParameter("bankaccounttype"));
		String bankprovince        = formatStr(request.getParameter("bankprovince"));
		String bankcity            = formatStr(request.getParameter("bankcity"));
		String manualsettle        = formatStr(request.getParameter("manualsettle"));
		String deposit             = formatStr(request.getParameter("deposit"));
		String email               = formatStr(request.getParameter("email"));

		Map<String, String> params = new HashMap<String, String>();
    	params.put("requestid", 		requestid);
    	params.put("bindmobile", 		bindmobile);
    	params.put("customertype", 		customertype);
    	params.put("signedname", 		signedname);
    	params.put("linkman", 			linkman);
    	params.put("idcard", 			idcard);
    	params.put("businesslicence", 	businesslicence);
    	params.put("legalperson", 		legalperson);
    	params.put("minsettleamount", 	minsettleamount);
    	params.put("riskreserveday", 	riskreserveday);
    	params.put("bankaccountnumber", bankaccountnumber);
    	params.put("bankname", 			bankname);
    	params.put("accountname", 		accountname);
    	params.put("bankaccounttype", 	bankaccounttype);
    	params.put("bankprovince", 		bankprovince);
    	params.put("bankcity", 			bankcity);                  
    	params.put("manualsettle", 		manualsettle);                  
    	params.put("deposit", 			deposit);
    	params.put("email", 			email);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.REGISTERAPI_REQUEST_HMAC_ORDER);
		
		//第二步 发起请求
		String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.REGISTERAPI_NAME);
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
		if(!ZGTUtils.checkHmac(responseDataMap, ZGTUtils.REGISTERAPI_RESPONSE_HMAC_ORDER)) {
			out.println("<br>hmac check error!<br>");
			return;
		}
		
		//第七步 进行业务处理
		request.setAttribute("responseDataMap", responseDataMap);
		RequestDispatcher view	= request.getRequestDispatcher("jsp/41registerApiResponse.jsp");
		view.forward(request, response);
	}
}
