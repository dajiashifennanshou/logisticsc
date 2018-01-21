package com.yc.Tool.yeepay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ZGTDATA {
	//一键支付
	public static final String PAYTYPE="ONEKEY";
	
	/**
	 * 一键支付数据
	 * @throws Exception 
	 * @throws IOException 
	 * @Author:luojing
	 * @2016年8月9日 下午2:00:04
	 */
	public static String getData(HttpServletRequest request) throws IOException, Exception{
		//获取各个请求参数
		String requestid			= YeePayTool.formatStr(request.getAttribute("requestid").toString());//商户订单号
		String amount				= YeePayTool.formatStr(request.getAttribute("amount").toString());//支付金额
		String assure				= "0";//是否需要担保交易  1 担保  0不担保
		String productname			= YeePayTool.formatStr(request.getAttribute("productname").toString());//商品名称
		String productcat			= YeePayTool.formatStr(request.getAttribute("productcat").toString());//商品种类
		String productdesc			= YeePayTool.formatStr(request.getAttribute("productdesc").toString());//商品描述
		String divideinfo			= YeePayTool.formatStr(request.getParameter("divideinfo"));//分账详情
		String callbackurl			= YeePayTool.formatStr(request.getAttribute("callbackurl").toString());//后台通知地址
		String webcallbackurl		= YeePayTool.formatStr(request.getParameter("webcallbackurl"));//页面通知地址
		String bankid				= YeePayTool.formatStr(request.getParameter("bankid"));//银行编码
		String period				= YeePayTool.formatStr(request.getParameter("period"));//担保有效期时间
		String memo		  			= YeePayTool.formatStr(request.getParameter("memo"));//订单备注
		String payproducttype		= PAYTYPE;//支付方式  默认一键支付  
		String userno	  			= YeePayTool.formatStr(request.getAttribute("userno").toString());//用户标识
		String isbind	  			= YeePayTool.formatStr(request.getParameter("isbind"));//是否绑卡
		String bindid	  			= YeePayTool.formatStr(request.getParameter("bindid"));//绑卡ID
		String ip		  			= YeePayTool.formatStr(request.getRemoteAddr());//ip地址
		String cardname		  		= YeePayTool.formatStr(request.getParameter("cardname"));//持卡人姓名
		String idcard		  		= YeePayTool.formatStr(request.getParameter("idcard"));//身份证号
		String bankcardnum			= YeePayTool.formatStr(request.getParameter("bankcardnum"));//银行卡号
		String mobilephone		  	= YeePayTool.formatStr(request.getParameter("mobilephone"));//预留手机号
		String cvv2		  			= YeePayTool.formatStr(request.getParameter("cvv2"));
		String expiredate		  	= YeePayTool.formatStr(request.getParameter("expiredate"));
		String mcc		  			= YeePayTool.formatStr(request.getParameter("mcc"));
		String areacode		  		= YeePayTool.formatStr(request.getParameter("areacode"));
		String ledgerno  			= YeePayTool.formatStr(request.getParameter("ledgerno"));
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
}
