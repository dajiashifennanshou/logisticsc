package com.brightsoft.controller.payment.yeepay;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.controller.base.BaseController;
import com.brightsoft.utils.Result;

import com.brightsoft.utils.yeepay.DownloadOrderDocumentUtils;

import com.brightsoft.utils.yeepay.ZGTData;
import com.brightsoft.utils.yeepay.ZGTDataAttribute;
import com.brightsoft.utils.yeepay.ZGTUtils;

@Controller
@RequestMapping("/yeePay")
public class YeePayController extends BaseController{
	
	
	private ServletContext sc;

	/**
	 * 支付
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value="/pay",method= RequestMethod.POST)
	@ResponseBody
	protected Result pay(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
				Result result = new Result();
				String payproducttype		= formatStr(request.getParameter("payproducttype"));
				//第一步 生成密文data
				String data			= ZGTData.buildPayData(request);
				
				//第二步 发起请求
				String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.PAYAPI_NAME);
				Map<String, String> responseMap	= ZGTUtils.httpPost(requestUrl, data);
				
				//System.out.println(requestUrl + "?customernumber=" + ZGTUtils.getCustomernumber() + "&data=" + data);
				
				//第三步 判断请求是否成功，
				if(responseMap.containsKey("code")) {
					//out.println(responseMap);
					return result ;
				}

				//第四步 解密同步响应密文data，获取明文参数
				String responseData	= responseMap.get("data");
				Map<String, String> responseDataMap	= ZGTUtils.decryptData(responseData);
				
//				System.out.println("易宝的同步响应：" + responseMap);
//				System.out.println("data解密后明文：" + responseDataMap);
				//result.setMsg(responseDataMap.get("code"));
				result.setData(responseDataMap);
				//第五步 code=1时，方表示接口处理成功
				if(!"1".equals(responseDataMap.get("code"))) {
					//out.println("code = " + responseDataMap.get("code") + "<br>");
					//out.println("msg  = " + responseDataMap.get("msg"));
					return result ;
				}
				
				//第六步 hmac签名验证
				if(!ZGTUtils.checkHmac(responseDataMap, ZGTUtils.PAYAPI_RESPONSE_HMAC_ORDER)) {
					result.setMsg("验证签名失败");
					return result ;
				}
				
				//第七步 进行业务处理
				//payapi支付接口，当支付方式为SALES-网银、或ONEKEY-手机一键时，需要跳转到易宝的收银台页面完成支付
				if(payproducttype.equals("SALES") || payproducttype.equals("ONEKEY")) {
					String payurl	= responseDataMap.get("payurl");
					System.out.println("payurl======" + payurl);
					response.sendRedirect(payurl);
					return result ;
				} else {
					//DIRECT无卡直连，当为发送短信验证码模式时，必须调用4.5短信验证码发送接口、4.6短信验证码确认接口才能完成支付。
					//DIRECT无卡直连，当为不发送短信验证码模式时，返回code=1，则表示扣款成功。
					//LEDGER账户支付，返回code=1，则表示扣款成功。
					//System.out.println(responseDataMap);
					request.setAttribute("responseDataMap", responseDataMap);
					RequestDispatcher view	= request.getRequestDispatcher("44payApiResponse.jsp");
					view.forward(request, response);
					return result ;
				}
				
			
	}
	
	
	/**
	 * 子账户注册
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/register",method= RequestMethod.POST)
	@ResponseBody
	protected Result register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String data = ZGTData.buildRegisterData(request);
			
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.REGISTERAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data,ZGTUtils.REGISTERAPI_RESPONSE_HMAC_ORDER);
					
			//第七步 进行业务处理
			
			
			return result ; 
		}
	
		/**
		 * 账户信息修改
		 * @param request
		 * @param response
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/modify",method= RequestMethod.POST)
		@ResponseBody
		protected Result modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 

			String data = ZGTData.buildModifyData(request);
			
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.MODIFYREQUESTAPI_NAME);
			Result result= ZGTUtils.httpPost(requestUrl, data,ZGTUtils.MODIFYREQUESTAPI_RESPONSE_HMAC_ORDER);
			
			//第七步 进行业务处理

			return result ;
		}
		
		/**
		 * 账户信息修改查询接口
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/queryModify",method= RequestMethod.POST)
		@ResponseBody
		protected Result queryModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String data = ZGTData.buildQueryModifyData(request);
			
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.QUERYMODIFYREQUESTAPI_NAME);
			Result result = ZGTUtils.httpPost(requestUrl, data,ZGTUtils.QUERYMODIFYREQUESTAPI_RESPONSE_HMAC_ORDER);

			
			//第七步 进行业务处理
		
			return result ;
		}
		
		
		/**
		 * 无卡直连短信验证码发送接口
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/sendSms",method= RequestMethod.POST)
		@ResponseBody
		protected Result sendSms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String data = ZGTData.buildSendSmsData(request);
			
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.SENDSMSAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data,ZGTUtils.SENDSMSAPI_RESPONSE_HMAC_ORDER);
			
			
			
			//第七步 进行业务处理
			
			return result ;
		}
		
		
		/**
		 * 无卡直连短信验证码确认接口
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/confirmSms",method= RequestMethod.POST)
		@ResponseBody
		protected Result confirmSms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
			String data = ZGTData.buildConfirmSmsData(request);
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.CONFIRMSMSAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data ,ZGTUtils.CONFIRMSMSAPI_RESPONSE_HMAC_ORDER);

			
			//第七步 进行业务处理

			return result ;
		}
		
		/**
		 * 订单查询接口
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/queryOrder",method= RequestMethod.POST)
		@ResponseBody
		protected Result queryOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			String data = ZGTData.buildQueryOrderData(request);
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.QUERYORDERAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data,ZGTUtils.QUERYORDERAPI_RESPONSE_HMAC_ORDER);

			//第七步 进行业务处理

			return result;
		}
		
		
		/**
		 * 转账接口transfer
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/transfer",method= RequestMethod.POST)
		@ResponseBody
		protected Result transfer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
			String data = ZGTData.buildTransferData(request);
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.TRANSFERAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data,ZGTUtils.TRANSFERAPI_RESPONSE_HMAC_ORDER);
			
			//第七步 进行业务处理

			return result;
		}
		
		
		/**
		 * 转账查询接口
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/transferQuery",method= RequestMethod.POST)
		@ResponseBody
		protected Result transferQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


			String data = ZGTData.buildTransferQueryData(request);
			
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.TRANSFERQUERYAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data,ZGTUtils.TRANSFERQUERYAPI_RESPONSE_HMAC_ORDER);

			//第七步 进行业务处理

			return result;
		}
		
		
		/**
		 * 分账接口
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/divide",method= RequestMethod.POST)
		@ResponseBody
		protected Result divide(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 

			String data = ZGTData.buildDivideData(request);
			
			//第二步 发起请求
			String requestUrl = ZGTUtils.getRequestUrl(ZGTUtils.DIVIDEAPI_NAME);
			Result result = ZGTUtils.httpPost(requestUrl, data,ZGTUtils.DIVIDEAPI_RESPONSE_HMAC_ORDER);
			//第七步 进行业务处理

			return result;
		}
		
		/**
		 * 分账查询接口queryDivide
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/queryDivide",method= RequestMethod.POST)
		@ResponseBody
		protected Result queryDivide(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			String data = ZGTData.buildQueryDivideData(request);
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.QUERYDIVIDEAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data,ZGTUtils.QUERYDIVIDEAPI_RESPONSE_HMAC_ORDER);

			
			//第七步 进行业务处理

			return result;
		}
		
		
		/**
		 * 订单退款接口
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/refund",method= RequestMethod.POST)
		@ResponseBody
		protected Result refund(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String data = ZGTData.buildRefundData(request);
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.REFUNDAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data, ZGTUtils.REFUNDAPI_RESPONSE_HMAC_ORDER);
			//第七步 进行业务处理

			return result;
		}
		
		
		/**
		 * 订单退款查询接口
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/queryRefund",method= RequestMethod.POST)
		@ResponseBody
		protected Result queryRefund(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String data = ZGTData.buildQueryRefundData(request);
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.QUERYREFUNDAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data, ZGTUtils.QUERYREFUNDAPI_RESPONSE_HMAC_ORDER);
		
			//第七步 进行业务处理

			return result;
		}
		
		
		/**
		 * 担保确认接口
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/settleConfirm",method= RequestMethod.POST)
		@ResponseBody
		protected Result settleConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			

			
			String data = ZGTData.buildSettleConfirmData(request);
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.SETTLECONFIRMAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data, ZGTUtils.SETTLECONFIRMAPI_RESPONSE_HMAC_ORDER);
			
			//第七步 进行业务处理

			return result;
		}
		
		
		/**
		 * 余额查询接口
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/queryBalance",method= RequestMethod.POST)
		@ResponseBody
		protected Result queryBalance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String data = ZGTData.buildQueryBalanceData(request);
			
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.QUERYBALANCEAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data, ZGTUtils.QUERYBALANCEAPI_RESPONSE_HMAC_ORDER);

			
			//第七步 进行业务处理

			return result;
		}
		
		
		/**
		 * 提现接口
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/cashTransfer",method= RequestMethod.POST)
		@ResponseBody
		protected Result cashTransfer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String data =  ZGTData.buildCashTransferData(request);
			
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.CASHTRANSFERAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data,ZGTUtils.CASHTRANSFERAPI_RESPONSE_HMAC_ORDER);
			
			//第七步 进行业务处理

			return result;
		}
		
		/**
		 * 提现查询接口
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/queryCashTransfer",method= RequestMethod.POST)
		@ResponseBody
		protected Result queryCashTransfer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String data = ZGTData.buildQueryCashTransferData(request);
			
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.QUERYCASHTRANSFERAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data, ZGTUtils.QUERYCASHTRANSFERAPI_RESPONSE_HMAC_ORDER);

			
			//第七步 进行业务处理

			return result;
		}
		
		
		
		/**
		 * 结算结果查询接口
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/querySettlement",method= RequestMethod.POST)
		@ResponseBody
		protected Result querySettlement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String data = ZGTData.buildQuerySettlementData(request);
			
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.QUERYSETTLEMENTAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data, ZGTUtils.QUERYSETTLEMENTAPI_RESPONSE_HMAC_ORDER);
			
			//第七步 进行业务处理

			return result;
		}
		
		
		
		/**查询绑卡列表接口
		 * queryBindCards
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/queryBindCards",method= RequestMethod.POST)
		@ResponseBody
		protected Result queryBindCards(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String data = ZGTData.buildQueryBindCardsData(request);
			
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.QUERYBINDCARDSAPI_NAME);
			System.out.println(requestUrl + "?customernumber=" + ZGTUtils.getCustomernumber() + "&data=" + data);
			Result result	= ZGTUtils.httpPost(requestUrl, data,ZGTUtils.QUERYBINDCARDSAPI_RESPONSE_HMAC_ORDER);

			
			//第七步 进行业务处理

			return result;
		}
		
		
		/**
		 * 解绑接口
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/unbindCard",method= RequestMethod.POST)
		@ResponseBody
		protected Result unbindCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String data = ZGTData.buildUnbindCardData(request);
			
			//第二步 发起请求
			String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.UNBINDCARDAPI_NAME);
			Result result	= ZGTUtils.httpPost(requestUrl, data,ZGTUtils.UNBINDCARDAPI_RESPONSE_HMAC_ORDER);

			
			//第七步 进行业务处理

			return result;
		}
		
		
		
		/**
		 *  对账文件下载接口
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/downloadOrderDocument",method= RequestMethod.POST)
		@ResponseBody
		protected Result downloadOrderDocument(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Result result = new Result();
			

			//获取请求参数
			String checkDate	= formatStr(request.getParameter("checkDate"));
			String orderType	= formatStr(request.getParameter("orderType"));
			String fileType		= formatStr(request.getParameter("fileType"));
			
			Map<String, String> params	= new HashMap<String, String>();
			params.put("checkDate", checkDate);
			params.put("orderType", orderType);
			params.put("fileType", fileType);

			//获得项目绝对路径	
			//String realPath 	= this.getServletConfig().getServletContext().getRealPath("/"); 
			String realPath 	= ""; 
			
			//对账文件的存储路径
			String path			= realPath + File.separator + "ZgtOrderDocument";
			//System.out.println("path:" + path);

			//获取对账文件
			String filePath		= DownloadOrderDocumentUtils.getPathOfDownloadOrderDocument(params, path);

			request.setAttribute("filePath", filePath);
			RequestDispatcher view	= request.getRequestDispatcher("jsp/421downloadOrderDocumentApiResponse.jsp");
			view.forward(request, response);
			return result;
		}
		
		
		/**
		 * 分账方资质上传接口
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/uploadaaa",method= RequestMethod.POST)
		@ResponseBody
		protected Result upload(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			Result result = new Result();
			ZGTDataAttribute.buildUploadData("123", "", null);
			//UploadImageManager.pushMessage(null);
			return result;
		}
		
		
		
		
		/**
		 * /掌柜通页面及后台通知
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/payCallBack",method= RequestMethod.POST)
		protected void payCallBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
