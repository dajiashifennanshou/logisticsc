package com.yc.Controller.yeepay;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.LcPlatformOrder;
import com.yc.Entity.PlatformBankPayment;
import com.yc.Entity.TmsWayBillOrder;
import com.yc.Entity.YcDeliveryOrder;
import com.yc.Service.IYcDeliveryOrderService;
import com.yc.Service.PlatformBankPaymentService;
import com.yc.Service.PlatformOrderService;
import com.yc.Tool.DES;
import com.yc.Tool.DateUtil;
import com.yc.Tool.FengUtil;
import com.yc.Tool.yeepay.YeePayTool;
import com.yc.Tool.yeepay.ZGTDATA;
import com.yc.Tool.yeepay.ZGTUtils;
/**
 * 掌柜通  一键支付支付
 * @Author:luojing
 * @2016年8月9日 上午11:42:14
 */
@Controller
public class YeePayController {
	
	@Autowired
	private PlatformBankPaymentService bankPaymentService;
	@Autowired
	private IYcDeliveryOrderService deliveryOrderService;
	@Autowired
	private PlatformOrderService orderService;	

	/**
	 * 配送单支付
	 * @throws IOException 
	 * @throws ServletException 
	 * @Author:luojing
	 * @2016年8月9日 上午11:46:01
	 */
	@RequestMapping("app/getDeliveryOrderPay")
	public void getDeliveryOrderPay(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		try{
			YcDeliveryOrder order = (YcDeliveryOrder) FengUtil.getObject(request.getParameterMap(), new YcDeliveryOrder());
			String requestid = YeePayTool.getRequestId();//商户订单号
			request.setAttribute("requestid", requestid);
			request.setAttribute("productname", FengUtil.getEncode(DES.decrypt(request.getParameter("goodsName"))));
			request.setAttribute("amount", order.getDeliveryCost()+order.getInstallCost());//支付金额
			request.setAttribute("callbackurl", "http://app.xslwl56.com:8081/Distributor/app/getDeliveryOrderPayCallBack.yc");
			request.setAttribute("userno", order.getDealerId());
			request.setAttribute("productcat","安装配送");//商品种类
			request.setAttribute("productdesc","安装配送");//商品描述
			//第一步 生成密文data
			String data = ZGTDATA.getData(request);
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.PAYAPI_NAME);
			Map<String, String> responseMap	= ZGTUtils.httpPost(requestUrl, data);
			//第三步 判断请求是否成功，
			if(responseMap.containsKey("code")) {
				System.out.println(responseMap);
				FengUtil.RuntimeExceptionFeng("请求失败,请重新尝试");
			}
			//第四步 解密同步响应密文data，获取明文参数
			String responseData	= responseMap.get("data");
			Map<String, String> responseDataMap	= ZGTUtils.decryptData(responseData);
			//第五步 code=1时，方表示接口处理成功
			if(!"1".equals(responseDataMap.get("code"))) {
				FengUtil.RuntimeExceptionFeng(responseDataMap.get("msg"));
			}
			//第六步 hmac签名验证
			if(!ZGTUtils.checkHmac(responseDataMap, ZGTUtils.PAYAPI_RESPONSE_HMAC_ORDER)) {
				FengUtil.RuntimeExceptionFeng("签名验证失败!");
			}
			//第七步 进行业务处理
			String payurl	= responseDataMap.get("payurl");
			PlatformBankPayment bp = new PlatformBankPayment();
			bp.setCustomernumber("10013368774");
			bp.setRequestid(requestid);
			bp.setAmount(order.getDeliveryCost()+order.getInstallCost()+"");
			bp.setAssure("0");
			bp.setProductname("安装配送");
			bp.setProductcat("安装配送");
			bp.setProductdesc("安装配送");
			bp.setCallbackurl("http://app.xslwl56.com:8081/Distributor/app/getDeliveryOrderPayCallBack.yc");
			bp.setWebcallbackurl("");
			bp.setOrder_number(order.getDeliveryNo());
			bp.setOrder_type(1);
			bp.setTime(DateUtil.getDateTimeFormatString());
			bp.setState(Constant.PLATFORM_PAYMENT_STATE_1);
			bp.setPayproducttype(ZGTDATA.PAYTYPE);
			bp.setUser_id(order.getDealerId());
			bankPaymentService.addBankPayment(bp);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, payurl, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"网络异常", response);
		}
	}
	
	/**
	 * 配送单支付回调
	 * @Author:luojing
	 * @2016年8月9日 下午4:24:13
	 */
	@RequestMapping("app/getDeliveryOrderPayCallBack")
	public void getDeliveryOrderPayCallBack(HttpServletRequest request,HttpServletResponse response){
		try{
			//UTF-8编码
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out	= response.getWriter();
			//第一步 获取回调密文data
			String data	= request.getParameter("data");
			//因为ONEKEY-手机一键支付方式的页面回调，不会回调数据，在此demo中做一个简单的判断
			if(data == null) {
				return;
			}
			//第二步 解密密文data，获取明文参数
			Map<String, String> dataMap	= ZGTUtils.decryptData(data);
			//第三步 hmac签名验证
			if(!ZGTUtils.checkHmac(dataMap, ZGTUtils.PAYAPICALLBACK_HMAC_ORDER)) {
				out.println("<br>hmac check error!<br>");
				return;
			}
			String requestid = dataMap.get("requestid");
			PlatformBankPayment bp = bankPaymentService.getBankPayment(new PlatformBankPayment(requestid));
			bankPaymentService.updateBankPayment(new PlatformBankPayment(bp.getId(), Constant.PLATFORM_PAYMENT_STATE_0));//更改支付记录状态
			//修改配送单状态
			YcDeliveryOrder order = new YcDeliveryOrder();
			order.setDeliveryNo(bp.getOrder_number());
			order.setInsdelPayStatus(1);//配送单支付状态
			deliveryOrderService.updatePayStatus(order);
			//第四步 判断通知方式，如果是后台通知则需要回写SUCCESS 
			String notifytype = dataMap.get("notifytype");
			if("SERVER".equals(notifytype)) {
				out.println("SUCCESS");
				out.flush();
				out.close();
			} else {
				request.setAttribute("dataMap", dataMap);
				RequestDispatcher view	= request.getRequestDispatcher("jsp/payApiCallback.jsp");
				view.forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	/**
	 * 支付专线运输费用
	 * @Author:luojing
	 * @2016年8月12日 下午1:51:09
	 */
	@RequestMapping("app/paySpecialTransportation")
	public void paySpecialTransportation(HttpServletRequest request,HttpServletResponse response){
		try{
			TmsWayBillOrder order = (TmsWayBillOrder) FengUtil.getObject(request.getParameterMap(), new TmsWayBillOrder());
			String requestid = YeePayTool.getRequestId();//商户订单号
			request.setAttribute("requestid", requestid);
			request.setAttribute("productname", "运输费用");
			request.setAttribute("amount", order.getTotal());//支付金额
			request.setAttribute("callbackurl", "http://app.xslwl56.com:8081/Distributor/app/PayCallBackSpecialTransportation.yc");
			request.setAttribute("userno", order.getWay_bill_order_user());
			request.setAttribute("productcat","运输");//商品种类
			request.setAttribute("productdesc","运输");//商品描述
			//第一步 生成密文data
			String data = ZGTDATA.getData(request);
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.PAYAPI_NAME);
			Map<String, String> responseMap	= ZGTUtils.httpPost(requestUrl, data);
			//第三步 判断请求是否成功，
			if(responseMap.containsKey("code")) {
				FengUtil.RuntimeExceptionFeng("请求失败,请重新尝试");
			}
			//第四步 解密同步响应密文data，获取明文参数
			String responseData	= responseMap.get("data");
			Map<String, String> responseDataMap	= ZGTUtils.decryptData(responseData);
			//第五步 code=1时，方表示接口处理成功
			if(!"1".equals(responseDataMap.get("code"))) {
				FengUtil.RuntimeExceptionFeng(responseDataMap.get("msg"));
			}
			//第六步 hmac签名验证
			if(!ZGTUtils.checkHmac(responseDataMap, ZGTUtils.PAYAPI_RESPONSE_HMAC_ORDER)) {
				FengUtil.RuntimeExceptionFeng("签名验证失败!");
			}
			//第七步 进行业务处理
			String payurl	= responseDataMap.get("payurl");
			PlatformBankPayment bp = new PlatformBankPayment();
			bp.setCustomernumber("10013368774");
			bp.setRequestid(requestid);
			bp.setAmount(order.getTotal().toString());
			bp.setAssure("0");
			bp.setProductname("运输");
			bp.setProductcat("运输");
			bp.setProductdesc("运输");
			bp.setCallbackurl("http://app.xslwl56.com:8081/Distributor/app/PayCallBackSpecialTransportation.yc");
			bp.setWebcallbackurl("");
			bp.setOrder_number(order.getWay_bill_number());
			bp.setOrder_type(1);
			bp.setTime(DateUtil.getDateTimeFormatString());
			bp.setState(Constant.PLATFORM_PAYMENT_STATE_1);
			bp.setPayproducttype(ZGTDATA.PAYTYPE);
			bp.setUser_id(order.getWay_bill_order_user());
			bankPaymentService.addBankPayment(bp);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, payurl, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"网络异常", response);
		}
	}
	
	/**
	 * 支付专线运输费用回调
	 * @Author:luojing
	 * @2016年8月12日 下午1:52:41
	 */
	@RequestMapping("app/PayCallBackSpecialTransportation")
	public void PayCallBackSpecialTransportation(HttpServletRequest request,HttpServletResponse response){
		try{
			//UTF-8编码
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out	= response.getWriter();
			//第一步 获取回调密文data
			String data	= request.getParameter("data");
			//因为ONEKEY-手机一键支付方式的页面回调，不会回调数据，在此demo中做一个简单的判断
			if(data == null) {
				return;
			}
			//第二步 解密密文data，获取明文参数
			Map<String, String> dataMap	= ZGTUtils.decryptData(data);
			//第三步 hmac签名验证
			if(!ZGTUtils.checkHmac(dataMap, ZGTUtils.PAYAPICALLBACK_HMAC_ORDER)) {
				out.println("<br>hmac check error!<br>");
				return;
			}
			String requestid = dataMap.get("requestid");
			PlatformBankPayment bp = bankPaymentService.getBankPayment(new PlatformBankPayment(requestid));
			bankPaymentService.updateBankPayment(new PlatformBankPayment(bp.getId(), Constant.PLATFORM_PAYMENT_STATE_0));//更改支付记录状态
			//修改订单状态
			LcPlatformOrder order = new LcPlatformOrder();
			order.setIs_payment(Constant.PLATFORM_PAYMENT_STATE_0);
			order.setWay_bill_number(bp.getOrder_number());
			orderService.updatePlatformOrder(order);
			//第四步 判断通知方式，如果是后台通知则需要回写SUCCESS 
			String notifytype = dataMap.get("notifytype");
			if("SERVER".equals(notifytype)) {
				out.println("SUCCESS");
				out.flush();
				out.close();
			} else {
				request.setAttribute("dataMap", dataMap);
				RequestDispatcher view	= request.getRequestDispatcher("jsp/payApiCallback.jsp");
				view.forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,e.getMessage() , response);
		}
	}
}
