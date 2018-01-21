package com.brightsoft.utils.yeepay;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class ZGTData {
	
	
	public static String formatStr(String text) {
		return text == null ? "" : text.trim();
	}

	public static String buildPayData(HttpServletRequest request){
		//获取各个请求参数
		String requestid			= formatStr(request.getParameter("requestid"));	
    	String amount				= formatStr(request.getParameter("amount"));
    	String assure				= formatStr(request.getParameter("assure"));
    	String productname			= formatStr(request.getParameter("productname"));
    	String productcat			= formatStr(request.getParameter("productcat"));
    	String productdesc			= formatStr(request.getParameter("productdesc"));
    	String divideinfo			= formatStr(request.getParameter("divideinfo"));
    	//String callbackurl			= formatStr(request.getParameter("callbackurl"));
    	String webcallbackurl		= formatStr(request.getParameter("webcallbackurl"));
    	String bankid				= formatStr(request.getParameter("bankid"));
    	String period				= formatStr(request.getParameter("period"));
    	String memo		  			= formatStr(request.getParameter("memo"));
    	String payproducttype		= formatStr(request.getParameter("payproducttype"));
    	String userno	  			= formatStr(request.getParameter("userno"));
    	String isbind	  			= formatStr(request.getParameter("isbind"));
    	String bindid	  			= formatStr(request.getParameter("bindid"));
    	String ip		  			= formatStr(request.getParameter("ip"));
    	String cardname		  		= formatStr(request.getParameter("cardname"));
    	String idcard		  		= formatStr(request.getParameter("idcard"));
    	String bankcardnum			= formatStr(request.getParameter("bankcardnum"));
    	String mobilephone		  	= formatStr(request.getParameter("mobilephone"));
    	String cvv2		  			= formatStr(request.getParameter("cvv2"));
    	String expiredate		  	= formatStr(request.getParameter("expiredate"));
    	String mcc		  			= formatStr(request.getParameter("mcc"));
    	String areacode		  		= formatStr(request.getParameter("areacode"));
    	String ledgerno  			= formatStr(request.getParameter("ledgerno"));

		Map<String, String> params	= new HashMap<String, String>();
		params.put("requestid", 	requestid);
		params.put("amount", 		amount);
		params.put("assure", 		assure);
		params.put("productname", 	productname);
		params.put("productcat", 	productcat);
		params.put("productdesc", 	productdesc);
		params.put("divideinfo", 	divideinfo);
		params.put("callbackurl", 	ZGTUtils.buildCallBackUrl(request, ZGTUtils.PAY_CAllBACK));
		params.put("webcallbackurl", webcallbackurl);
		params.put("bankid",		bankid);
		params.put("period", 		period);
		params.put("memo", 			memo);
		params.put("payproducttype", payproducttype);
		params.put("userno", 		userno);
		params.put("isbind", 		isbind);
		params.put("bindid", 		bindid);
		params.put("ip", 			ip);
		params.put("cardname", 		cardname);
		params.put("idcard", 		idcard);
		params.put("bankcardnum", 	bankcardnum);
		params.put("mobilephone", 	mobilephone);
		params.put("cvv2", 			cvv2);
		params.put("expiredate", 	expiredate);
		params.put("mcc", 			mcc);
		params.put("areacode", 		areacode);
		params.put("ledgerno", 		ledgerno);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.PAYAPI_REQUEST_HMAC_ORDER);
		
		return data;
	}
	
	public static String buildRegisterData(HttpServletRequest request){
		
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
    	params.put("email123", 			email);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.REGISTERAPI_REQUEST_HMAC_ORDER);
		
		return data;
	}
	
	public static String buildModifyData(HttpServletRequest request){
		
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
		
		return data;
	}
	
	public static String buildQueryModifyData(HttpServletRequest request){
		
		//获取请求参数
		String requestid			= formatStr(request.getParameter("requestid"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("requestid",requestid);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.QUERYMODIFYREQUESTAPI_REQUEST_HMAC_ORDER);
		
		return data;
	}
	
	public static String buildSendSmsData(HttpServletRequest request){
		//获取请求参数
		String orderrequestid		= formatStr(request.getParameter("orderrequestid"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("orderrequestid", orderrequestid);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.SENDSMSAPI_REQUEST_HMAC_ORDER);
		return data;
	}
	
	public static String buildConfirmSmsData(HttpServletRequest request){
		//获取请求参数
		String orderrequestid		= formatStr(request.getParameter("orderrequestid"));	
		String smscode				= formatStr(request.getParameter("smscode"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("orderrequestid", orderrequestid);
		params.put("smscode", smscode);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.CONFIRMSMSAPI_REQUEST_HMAC_ORDER);
		return data;
	}
	
	public static String buildQueryOrderData(HttpServletRequest request){
		//获取请求参数
		String requestid = formatStr(request.getParameter("requestid"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("requestid",requestid);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.QUERYORDERAPI_REQUEST_HMAC_ORDER);
		return data;
	}
	
	public static String buildTransferData(HttpServletRequest request){
		//获取请求参数
		String requestid			= formatStr(request.getParameter("requestid"));
    	String ledgerno	  			= formatStr(request.getParameter("ledgerno"));       
    	String amount				= formatStr(request.getParameter("amount"));
    	String sourceledgerno	  	= formatStr(request.getParameter("sourceledgerno")); 	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("requestid",	requestid);
		params.put("ledgerno",	ledgerno);
		params.put("amount",	amount);
		params.put("sourceledgerno", sourceledgerno);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.TRANSFERAPI_REQUEST_HMAC_ORDER);
		return data;
	}
	
	public static String buildTransferQueryData(HttpServletRequest request){
		//获取请求参数
		String requestid			= formatStr(request.getParameter("requestid"));

		Map<String, String> params	= new HashMap<String, String>();
		params.put("requestid",	requestid);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.TRANSFERQUERYAPI_REQUEST_HMAC_ORDER);
		return data;
	}
	
	public static String buildDivideData(HttpServletRequest request){
		
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
		
		return data;
	}
	
	public static String buildQueryDivideData(HttpServletRequest request){
		
		//获取请求参数
		//String requestid			= formatStr(request.getParameter("requestid"));	
		String orderrequestid		= formatStr(request.getParameter("orderrequestid"));	
		String dividerequestid		= formatStr(request.getParameter("dividerequestid"));	
		String ledgerno				= formatStr(request.getParameter("ledgerno"));	

		Map<String, String> params	= new HashMap<String, String>();
		//params.put("requestid", requestid);
		params.put("orderrequestid", orderrequestid);
		params.put("dividerequestid", dividerequestid);
		params.put("ledgerno", ledgerno);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.QUERYDIVIDEAPI_REQUEST_HMAC_ORDER);
		return data;
	}
	
	public static String buildRefundData(HttpServletRequest request){
		
		//获取请求参数
		String requestid			= formatStr(request.getParameter("requestid"));	
		String orderrequestid		= formatStr(request.getParameter("orderrequestid"));	
    	String amount				= formatStr(request.getParameter("amount"));
    	String divideinfo			= formatStr(request.getParameter("divideinfo"));
    	String confirm				= formatStr(request.getParameter("confirm"));
    	String memo		  			= formatStr(request.getParameter("memo"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("requestid", 	requestid);
		params.put("orderrequestid",orderrequestid);
		params.put("amount", 		amount);
		params.put("divideinfo", 	divideinfo);
		params.put("confirm", 		confirm);
		params.put("memo", 			memo);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.REFUNDAPI_REQUEST_HMAC_ORDER);
		return data;
	}
	
	public static String buildQueryRefundData(HttpServletRequest request){
		//获取请求参数
		String orderrequestid		= formatStr(request.getParameter("orderrequestid"));	
		String refundrequestid		= formatStr(request.getParameter("refundrequestid"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("orderrequestid", orderrequestid);
		params.put("refundrequestid", refundrequestid);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.QUERYREFUNDAPI_REQUEST_HMAC_ORDER);
		return data;
	}
	
	public static String buildSettleConfirmData(HttpServletRequest request){
		//获取请求参数
		String orderrequestid		= formatStr(request.getParameter("orderrequestid"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("orderrequestid", orderrequestid);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.SETTLECONFIRMAPI_REQUEST_HMAC_ORDER);
		return data;
	}
	
	public static String buildQueryBalanceData(HttpServletRequest request){
		
		//获取请求参数
		String ledgerno		= formatStr(request.getParameter("ledgerno"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("ledgerno", ledgerno);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.QUERYBALANCEAPI_REQUEST_HMAC_ORDER);
		
		return data;
	}
	
	public static String buildCashTransferData(HttpServletRequest request){
		
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
		
		return data;
	}
	public static String buildQueryCashTransferData(HttpServletRequest request){
		
		//获取请求参数
		String cashrequestid = formatStr(request.getParameter("cashrequestid"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("cashrequestid", cashrequestid);

		//第一步 生成密文data
		String data	= ZGTUtils.buildData(params, ZGTUtils.QUERYCASHTRANSFERAPI_REQUEST_HMAC_ORDER);
		
		
		return data;
	}
	
	
	public static String buildQuerySettlementData(HttpServletRequest request){
		
		//获取请求参数
		String ledgerno				= formatStr(request.getParameter("ledgerno"));	
		String date					= formatStr(request.getParameter("date"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("ledgerno", ledgerno);
		params.put("date", date);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.QUERYSETTLEMENTAPI_REQUEST_HMAC_ORDER);
		
		return data;
	}
	
	
	public static String buildQueryBindCardsData(HttpServletRequest request){
		
		//获取请求参数
		String userno				= formatStr(request.getParameter("userno"));	
		String bindid				= formatStr(request.getParameter("bindid"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("userno", userno);
		params.put("bindid", bindid);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.QUERYBINDCARDSAPI_REQUEST_HMAC_ORDER);
		
		return data;
	}
	public static String buildUnbindCardData(HttpServletRequest request){
		
		//获取请求参数
		String userno				= formatStr(request.getParameter("userno"));	
		String bindid				= formatStr(request.getParameter("bindid"));	
		String cause				= formatStr(request.getParameter("cause"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("userno", userno);
		params.put("bindid", bindid);
		params.put("cause", cause);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.UNBINDCARDAPI_REQUEST_HMAC_ORDER);
		
		return data;
	}

}
