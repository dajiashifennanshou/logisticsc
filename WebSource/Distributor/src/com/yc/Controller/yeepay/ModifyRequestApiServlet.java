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
 * @since : 2015-10-01 20:15
 */

public class ModifyRequestApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModifyRequestApiServlet() {
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
		String requestid         	= formatStr(request.getParameter("requestid"));
		String ledgerno          	= formatStr(request.getParameter("ledgerno"));
		String bankaccountnumber 	= formatStr(request.getParameter("bankaccountnumber"));
		String bankname          	= formatStr(request.getParameter("bankname"));
		String accountname       	= formatStr(request.getParameter("accountname"));
		String bankaccounttype   	= formatStr(request.getParameter("bankaccounttype"));
		String bankprovince      	= formatStr(request.getParameter("bankprovince"));
		String bankcity          	= formatStr(request.getParameter("bankcity"));
		String minsettleamount   	= formatStr(request.getParameter("minsettleamount"));
		String riskreserveday    	= formatStr(request.getParameter("riskreserveday"));
		String manualsettle      	= formatStr(request.getParameter("manualsettle"));
		String callbackurl      	= formatStr(request.getParameter("callbackurl"));
		String bindmobile 	     	= formatStr(request.getParameter("bindmobile"));

		Map<String, String> params	= new HashMap<String, String>();
    	params.put("requestid", 		requestid);
    	params.put("ledgerno", 			ledgerno);
    	params.put("bankaccountnumber", bankaccountnumber);
    	params.put("bankname", 			bankname);
    	params.put("accountname", 		accountname);
    	params.put("bankaccounttype", 	bankaccounttype);
    	params.put("bankprovince", 		bankprovince);
    	params.put("bankcity", 			bankcity);                  
    	params.put("manualsettle", 		manualsettle);                  
    	params.put("minsettleamount", 	minsettleamount);
    	params.put("riskreserveday", 	riskreserveday);
    	params.put("callbackurl", 		callbackurl);                  
    	params.put("bindmobile", 		bindmobile);        

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.MODIFYREQUESTAPI_REQUEST_HMAC_ORDER);
		
		//第二步 发起请求
		String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.MODIFYREQUESTAPI_NAME);
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
		System.out.println("111");
		
		//第五步 code=1时，方表示接口处理成功
		if(!"1".equals(responseDataMap.get("code"))) {
			out.println("code = " + responseDataMap.get("code") + "<br>");
			out.println("msg  = " + responseDataMap.get("msg"));
			return;
		}
		
		//第六步 hmac签名验证
		if(!ZGTUtils.checkHmac(responseDataMap, ZGTUtils.MODIFYREQUESTAPI_RESPONSE_HMAC_ORDER)) {
			out.println("<br>hmac check error!<br>");
			return;
		}
		
		//第七步 进行业务处理
		request.setAttribute("responseDataMap", responseDataMap);
		RequestDispatcher view	= request.getRequestDispatcher("jsp/42modifyRequestApiResponse.jsp");
		view.forward(request, response);
	}
}
