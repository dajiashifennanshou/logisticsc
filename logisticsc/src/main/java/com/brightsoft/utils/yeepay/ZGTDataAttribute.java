package com.brightsoft.utils.yeepay;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ZGTDataAttribute {
	
	
	public static String formatStr(Object obj) {
		String text = null;
		if(obj !=null){
			text =obj.toString();
		}
		return text == null ? "" : text.trim();
	}

	public static String buildPayData(HttpServletRequest request){
		//获取各个请求参数
		String requestid			= formatStr(request.getAttribute("requestid"));	
    	String amount				= formatStr(request.getAttribute("amount"));
    	String assure				= formatStr(request.getAttribute("assure"));
    	String productname			= formatStr(request.getAttribute("productname"));
    	String productcat			= formatStr(request.getAttribute("productcat"));
    	String productdesc			= formatStr(request.getAttribute("productdesc"));
    	String divideinfo			= formatStr(request.getAttribute("divideinfo"));
    	String callbackurl			= formatStr(request.getAttribute("callbackurl"));
    	String webcallbackurl		= formatStr(request.getAttribute("webcallbackurl"));
    	String bankid				= formatStr(request.getAttribute("bankid"));
    	String period				= formatStr(request.getAttribute("period"));
    	String memo		  			= formatStr(request.getAttribute("memo"));
    	String payproducttype		= formatStr(request.getAttribute("payproducttype"));
    	String userno	  			= formatStr(request.getAttribute("userno"));
    	String isbind	  			= formatStr(request.getAttribute("isbind"));
    	String bindid	  			= formatStr(request.getAttribute("bindid"));
    	String ip		  			= formatStr(request.getAttribute("ip"));
    	String cardname		  		= formatStr(request.getAttribute("cardname"));
    	String idcard		  		= formatStr(request.getAttribute("idcard"));
    	String bankcardnum			= formatStr(request.getAttribute("bankcardnum"));
    	String mobilephone		  	= formatStr(request.getAttribute("mobilephone"));
    	String cvv2		  			= formatStr(request.getAttribute("cvv2"));
    	String expiredate		  	= formatStr(request.getAttribute("expiredate"));
    	String mcc		  			= formatStr(request.getAttribute("mcc"));
    	String areacode		  		= formatStr(request.getAttribute("areacode"));
    	String ledgerno  			= formatStr(request.getAttribute("ledgerno"));

		Map<String, String> params	= new HashMap<String, String>();
		params.put("requestid", 	requestid);
		params.put("amount", 		amount);
		params.put("assure", 		assure);
		params.put("productname", 	productname);
		params.put("productcat", 	productcat);
		params.put("productdesc", 	productdesc);
		params.put("divideinfo", 	divideinfo);
		params.put("callbackurl", 	callbackurl);
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
		String requestid           = formatStr(request.getAttribute("requestid"));
		String bindmobile          = formatStr(request.getAttribute("bindmobile"));
		String customertype        = formatStr(request.getAttribute("customertype"));
		String signedname          = formatStr(request.getAttribute("signedname"));
		String linkman             = formatStr(request.getAttribute("linkman"));
		String idcard              = formatStr(request.getAttribute("idcard"));
		String businesslicence     = formatStr(request.getAttribute("businesslicence"));
		String legalperson         = formatStr(request.getAttribute("legalperson"));
		String minsettleamount     = formatStr(request.getAttribute("minsettleamount"));
		String riskreserveday      = formatStr(request.getAttribute("riskreserveday"));
		String bankaccountnumber   = formatStr(request.getAttribute("bankaccountnumber"));
		String bankname            = formatStr(request.getAttribute("bankname"));
		String accountname         = formatStr(request.getAttribute("accountname"));
		String bankaccounttype     = formatStr(request.getAttribute("bankaccounttype"));
		String bankprovince        = formatStr(request.getAttribute("bankprovince"));
		String bankcity            = formatStr(request.getAttribute("bankcity"));
		String manualsettle        = formatStr(request.getAttribute("manualsettle"));
		String deposit             = formatStr(request.getAttribute("deposit"));
		String email               = formatStr(request.getAttribute("email"));

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
		String requestid         	= formatStr(request.getAttribute("requestid"));
		String ledgerno          	= formatStr(request.getAttribute("ledgerno"));
		String bankaccountnumber 	= formatStr(request.getAttribute("bankaccountnumber"));
		String bankname          	= formatStr(request.getAttribute("bankname"));
		String accountname       	= formatStr(request.getAttribute("accountname"));
		String bankaccounttype   	= formatStr(request.getAttribute("bankaccounttype"));
		String bankprovince      	= formatStr(request.getAttribute("bankprovince"));
		String bankcity          	= formatStr(request.getAttribute("bankcity"));
		String minsettleamount   	= formatStr(request.getAttribute("minsettleamount"));
		String riskreserveday    	= formatStr(request.getAttribute("riskreserveday"));
		String manualsettle      	= formatStr(request.getAttribute("manualsettle"));
		String callbackurl      	= formatStr(request.getAttribute("callbackurl"));
		String bindmobile 	     	= formatStr(request.getAttribute("bindmobile"));

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
		String requestid			= formatStr(request.getAttribute("requestid"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("requestid", 	requestid);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.QUERYMODIFYREQUESTAPI_REQUEST_HMAC_ORDER);
		
		return data;
	}
	
	public static String buildSendSmsData(HttpServletRequest request){
		//获取请求参数
		String orderrequestid		= formatStr(request.getAttribute("orderrequestid"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("orderrequestid", orderrequestid);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.SENDSMSAPI_REQUEST_HMAC_ORDER);
		return data;
	}
	
	public static String buildConfirmSmsData(HttpServletRequest request){
		//获取请求参数
		String orderrequestid		= formatStr(request.getAttribute("orderrequestid"));	
		String smscode				= formatStr(request.getAttribute("smscode"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("orderrequestid", orderrequestid);
		params.put("smscode", smscode);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.CONFIRMSMSAPI_REQUEST_HMAC_ORDER);
		return data;
	}
	
	public static String buildQueryOrderData(HttpServletRequest request){
		//获取请求参数
		String requestid = formatStr(request.getAttribute("requestid"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("requestid", 	requestid);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.QUERYORDERAPI_REQUEST_HMAC_ORDER);
		return data;
	}
	
	public static String buildTransferData(HttpServletRequest request){
		//获取请求参数
		String requestid			= formatStr(request.getAttribute("requestid"));
    	String ledgerno	  			= formatStr(request.getAttribute("ledgerno"));       
    	String amount				= formatStr(request.getAttribute("amount"));
    	String sourceledgerno	  	= formatStr(request.getAttribute("sourceledgerno")); 	

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
		String requestid			= formatStr(request.getAttribute("requestid"));

		Map<String, String> params	= new HashMap<String, String>();
		params.put("requestid",	requestid);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.TRANSFERQUERYAPI_REQUEST_HMAC_ORDER);
		return data;
	}
	
	public static String buildDivideData(HttpServletRequest request){
		
		//获取请求参数
		String requestid			= formatStr(request.getAttribute("requestid"));	
		String orderrequestid		= formatStr(request.getAttribute("orderrequestid"));	
		String divideinfo			= formatStr(request.getAttribute("divideinfo"));
		String callbackurl			= formatStr(request.getAttribute("callbackurl"));

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
		//String requestid			= formatStr(request.getAttribute("requestid"));	
		String orderrequestid		= formatStr(request.getAttribute("orderrequestid"));	
		String dividerequestid		= formatStr(request.getAttribute("dividerequestid"));	
		String ledgerno				= formatStr(request.getAttribute("ledgerno"));	

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
		String requestid			= formatStr(request.getAttribute("requestid"));	
		String orderrequestid		= formatStr(request.getAttribute("orderrequestid"));	
    	String amount				= formatStr(request.getAttribute("amount"));
    	String divideinfo			= formatStr(request.getAttribute("divideinfo"));
    	String confirm				= formatStr(request.getAttribute("confirm"));
    	String memo		  			= formatStr(request.getAttribute("memo"));	

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
		String orderrequestid		= formatStr(request.getAttribute("orderrequestid"));	
		String refundrequestid		= formatStr(request.getAttribute("refundrequestid"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("orderrequestid", orderrequestid);
		params.put("refundrequestid", refundrequestid);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.QUERYREFUNDAPI_REQUEST_HMAC_ORDER);
		return data;
	}
	
	public static String buildSettleConfirmData(HttpServletRequest request){
		//获取请求参数
		String orderrequestid		= formatStr(request.getAttribute("orderrequestid"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("orderrequestid", orderrequestid);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.SETTLECONFIRMAPI_REQUEST_HMAC_ORDER);
		return data;
	}
	
	public static String buildQueryBalanceData(HttpServletRequest request){
		
		//获取请求参数
		String ledgerno		= formatStr(request.getAttribute("ledgerno"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("ledgerno", ledgerno);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.QUERYBALANCEAPI_REQUEST_HMAC_ORDER);
		
		return data;
	}
	
	public static String buildCashTransferData(HttpServletRequest request){
		
		//获取请求参数
		String requestid			= formatStr(request.getAttribute("requestid"));	
		String ledgerno				= formatStr(request.getAttribute("ledgerno"));	
		String amount				= formatStr(request.getAttribute("amount"));	
		String callbackurl			= formatStr(request.getAttribute("callbackurl"));	
		String feetype				= formatStr(request.getAttribute("feetype"));

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
		String cashrequestid = formatStr(request.getAttribute("cashrequestid"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("cashrequestid", cashrequestid);

		//第一步 生成密文data
		String data	= ZGTUtils.buildData(params, ZGTUtils.QUERYCASHTRANSFERAPI_REQUEST_HMAC_ORDER);
		
		
		return data;
	}
	
	
	public static String buildQuerySettlementData(HttpServletRequest request){
		
		//获取请求参数
		String ledgerno				= formatStr(request.getAttribute("ledgerno"));	
		String date					= formatStr(request.getAttribute("date"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("ledgerno", ledgerno);
		params.put("date", date);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.QUERYSETTLEMENTAPI_REQUEST_HMAC_ORDER);
		
		return data;
	}
	
	
	public static String buildQueryBindCardsData(HttpServletRequest request){
		
		//获取请求参数
		String userno				= formatStr(request.getAttribute("userno"));	
		String bindid				= formatStr(request.getAttribute("bindid"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("userno", userno);
		params.put("bindid", bindid);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.QUERYBINDCARDSAPI_REQUEST_HMAC_ORDER);
		
		return data;
	}
	public static String buildUnbindCardData(HttpServletRequest request){
		
		//获取请求参数
		String userno				= formatStr(request.getAttribute("userno"));	
		String bindid				= formatStr(request.getAttribute("bindid"));	
		String cause				= formatStr(request.getAttribute("cause"));	

		Map<String, String> params	= new HashMap<String, String>();
		params.put("userno", userno);
		params.put("bindid", bindid);
		params.put("cause", cause);

		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.UNBINDCARDAPI_REQUEST_HMAC_ORDER);
		
		return data;
	}
	
	
	/**
	 * 
	 * @param ledgerno
	 * @param fileType
	 * @return
	 */
	public static Map<String, Object> buildUploadData(String ledgerno,String filetype,File file){
		String key = ZGTUtils.getKeyForHmac();
		StringBuffer signature = new StringBuffer();
		String customernumber = ZGTUtils.getCustomernumber();
		signature.append(customernumber).append(ledgerno).append(filetype);
		String hmac = Digest.hmacSign(signature.toString(), key);
		System.out.println(hmac);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("customernumber", customernumber);
		dataMap.put("ledgerno", ledgerno);
		dataMap.put("filetype", filetype);
		dataMap.put("hmac", hmac); // hmac 按照 properties 中声明的顺序进行签名
		String dataJsonString = com.alibaba.fastjson.JSON.toJSONString(dataMap); // map 中数据转为 json 格式
		String content = AESUtil.encrypt(dataJsonString, key);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customernumber", customernumber);
		params.put("data", content);// 加密 json 格式数据作为 value 赋值给 data 参数
		params.put("file", file);
		
		return params;
	}
}
