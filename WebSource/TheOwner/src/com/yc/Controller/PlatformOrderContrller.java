package com.yc.Controller;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Canstant.enums.BankOrderType;
import com.yc.Dao.AdditionalServerConfMapper;
import com.yc.Entity.AdditionalServerConf;
import com.yc.Entity.LcPlatformOrderBill;
import com.yc.Entity.PlatformBankPayment;
import com.yc.Entity.PlatformDictionary;
import com.yc.Entity.PlatformOrder;
import com.yc.Entity.PlatformOrderCargo;
import com.yc.Entity.PlatformOrderEvaluation;
import com.yc.Entity.ResultEntity;
import com.yc.Entity.TmsLineInfo;
import com.yc.Entity.TmsOutletsInfo;
import com.yc.Entity.XZQHInfo;
import com.yc.Service.DictionaryService;
import com.yc.Service.ITmsOutletsInfoService;
import com.yc.Service.PlatformBankPaymentService;
import com.yc.Service.PlatformOrderBillService;
import com.yc.Service.PlatformOrderCargoService;
import com.yc.Service.PlatformOrderEvaluationService;
import com.yc.Service.PlatformOrderService;
import com.yc.Service.TmsLineInfoService;
import com.yc.Service.XZQHInfoService;
import com.yc.Tool.DateUtil;
import com.yc.Tool.FengRuntimeException;
import com.yc.Tool.FengUtil;
import com.yc.Tool.Pager;
import com.yc.Tool.yeepay.YeePayTool;
import com.yc.Tool.yeepay.ZGTDATA;
import com.yc.Tool.yeepay.ZGTUtils;

/**
 * 平台订单
 * @Author:luojing
 * @2016年7月27日 下午2:23:05
 */
@Controller
public class PlatformOrderContrller {
	
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private PlatformOrderService platformOrderService;
	@Autowired
	private PlatformOrderCargoService iLcPlatformOrderCargoService;
	@Autowired
	private PlatformBankPaymentService bankPaymentService;
	@Autowired
	private AdditionalServerConfMapper additionalServerConfMapper;
	@Autowired
	private PlatformOrderBillService orderBillService;
	@Autowired 
	private PlatformOrderEvaluationService iLcPlatformOrderEvaluationService;
	@Autowired
	private ITmsOutletsInfoService iTmsOutletsInfoService;
	@Autowired
	private XZQHInfoService xzqh;
	@Autowired
	private TmsLineInfoService tmsLineInfoService;
	/**
	 * 添加订单
	 * Author:luojing
	 * 2016年6月28日 下午1:56:08
	 */
	@RequestMapping("app/addOrder")
	public void addOrder(PlatformOrder order,String cargos,String orderAddServer,HttpServletRequest request,HttpServletResponse response){
		ResultEntity re=new ResultEntity();
		try{
			re=platformOrderService.addPlatFormOrder(request, order,orderAddServer, cargos);
			FengUtil.OUTAPPSUCCESS(re.getCode(), re.getData(),re.getMsg(), response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,"暂无结果", response);
		}
	}
	/**
	 * 获取包装类型
	 * Author:luojing
	 * 2016年6月28日 下午1:56:08
	 */
	@RequestMapping("app/getPackType")
	public void getPackType(PlatformOrder order,String cargos,HttpServletRequest request,HttpServletResponse response){
		try{
			// 获取包装类型信息
			List<PlatformDictionary> packageTypes = dictionaryService.selectDictDataByType("cargo_package_type");
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, packageTypes, response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,"异常", response);
		}
	}
	/**
	 * 获取增值服务配置
	 * Author:luojing
	 * 2016年6月28日 下午1:56:08 33
	 */
	@RequestMapping("app/AdditionalConf")
	public void AdditionalConf(Long TmsLineId,HttpServletRequest request,HttpServletResponse response){
		try{
			// 获取线路增值服务配置
			AdditionalServerConf additionalServerConf = additionalServerConfMapper.selectByLineId(TmsLineId);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, additionalServerConf, response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,"异常", response);
		}
	}
	
	/**
	 * 集合查询订单信息
	 * Author:luojing
	 * 2016年6月28日 下午2:23:30
	 */
	@RequestMapping("app/getUserOrder")
	public void getUserOrder(PlatformOrder order,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response){
		try{
			Pager<PlatformOrder> pager = new Pager<PlatformOrder>(page, rows);
			pager = platformOrderService.getPageOrder(pager, order);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, pager, response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,"暂无结果", response);
		}
	}
	
	
	/**
	 * 查询订单详情（订单号查询）
	 * Author:luojing
	 * 2016年6月28日 下午2:23:30
	 */
	@RequestMapping("app/getUserOrderDetails")
	public void getUserOrderDetails(PlatformOrder order,HttpServletRequest request,HttpServletResponse response){
		try{
			order=platformOrderService.getOrder(order);
			//订单商品信息
			List<PlatformOrderCargo> cargoList = new ArrayList<PlatformOrderCargo>();
			PlatformOrderCargo cargo=new PlatformOrderCargo();
			cargo.setOrder_id(order.getId());
			//获取货物信息
			cargoList=iLcPlatformOrderCargoService.getListInfoBy(cargo);
			PlatformOrderEvaluation pe=new PlatformOrderEvaluation();
			pe.setOrder_number(order.getOrder_number());
			pe=iLcPlatformOrderEvaluationService.getSingleInfo(pe);
			if(pe==null){
				order.setIs_evaluation(1);
			}else{
				order.setIs_evaluation(0);
				order.setOrderEvaluation(pe);
			}
			//获取线路
			TmsLineInfo tli=new TmsLineInfo();
			tli.setId(order.getTms_line_id());
			tli=tmsLineInfoService.getLineInfo(tli);
			//获取网点地址开始
			TmsOutletsInfo toi=new TmsOutletsInfo();
			//起始网点
			toi.setId(tli.getStart_outlets());
			toi=iTmsOutletsInfoService.getSingleInfo(toi);
			XZQHInfo xz=new XZQHInfo();
			//省
			xz.setId(toi.getProvince());
			toi.setProvince(xzqh.getSingleInfo(xz).getName());
			//市
			xz.setId(toi.getCounty());
			toi.setCounty(xzqh.getSingleInfo(xz).getName());
			//县
			xz.setId(toi.getCity());;
			toi.setCity(xzqh.getSingleInfo(xz).getName());
			String startAdd=toi.getProvince()+toi.getCounty()+toi.getCity()+toi.getAddress();
			//结束网点
			toi.setId(tli.getEnd_outlets());
			toi=iTmsOutletsInfoService.getSingleInfo(toi);
			//省
			xz.setId(toi.getProvince());
			toi.setProvince(xzqh.getSingleInfo(xz).getName());
			//市
			xz.setId(toi.getCounty());
			toi.setCounty(xzqh.getSingleInfo(xz).getName());
			//县
			xz.setId(toi.getCity());;
			toi.setCity(xzqh.getSingleInfo(xz).getName());
			String endAdd=toi.getProvince()+toi.getCounty()+toi.getCity()+toi.getAddress();
			
			order.setStartLineAddr(startAdd);
			order.setEndLineAddr(endAdd);
			//获取网点地址结束
			
			order.setCargoLst(cargoList);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, order, response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,"暂无结果", response);
		}
	}
	
	
	/**
	 * 取消订单
	 * @Author:luojing
	 * @2016年8月17日 下午1:51:30
	 */
	@RequestMapping("app/cancelOrder")
	public void cancelOrder(PlatformOrder order,HttpServletRequest request,HttpServletResponse response){
		try{
			if(order.getState()==11){
				ResultEntity re= platformOrderService.updateOrder(order);
				FengUtil.OUTAPPSUCCESS(re.getCode(), response);
			}else{
				FengUtil.RuntimeExceptionFeng("参数错误");
			}
		}catch(FengRuntimeException e){
			e.printErrorInfo();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,"暂无结果", response);
		}
	}
	
	/**
	 * 订单支付
	 * @Author:luojing
	 * @2016年8月16日 下午5:38:46
	 */
	@RequestMapping("app/orderPay")
	public void orderPay(PlatformOrder order,HttpServletRequest request,HttpServletResponse response){
		try{
			Integer payType = Integer.parseInt(request.getParameter("payType"));
			PlatformBankPayment bp = new PlatformBankPayment();
			bp.setCustomernumber("10013368774");
			if(payType==Constant.PLATFORM_ORDER_TYPE_1){
				//预约 
				request.setAttribute("productname",BankOrderType.APPOINTMENT.getName());
				request.setAttribute("productcat",BankOrderType.APPOINTMENT.getName());
				request.setAttribute("productdesc",BankOrderType.APPOINTMENT.getName());
				request.setAttribute("amount", order.getTake_cargo_cost());//预约提货费
				
				bp.setProductname(BankOrderType.APPOINTMENT.getName());
				bp.setProductcat(BankOrderType.APPOINTMENT.getName());
				bp.setProductdesc(BankOrderType.APPOINTMENT.getName());
				bp.setOrder_type(Constant.PLATFORM_ORDER_TYPE_1);
				bp.setAmount(order.getTake_cargo_cost().toString());
				
			}else if(payType==Constant.PLATFORM_ORDER_TYPE_2){
				//运费
				request.setAttribute("productname",BankOrderType.FREIGHT.getName());
				request.setAttribute("productcat",BankOrderType.FREIGHT.getName());
				request.setAttribute("productdesc",BankOrderType.FREIGHT.getName());
				request.setAttribute("amount", request.getParameter("total_price"));//运费
				
				bp.setProductname(BankOrderType.FREIGHT.getName());
				bp.setProductcat(BankOrderType.FREIGHT.getName());
				bp.setProductdesc(BankOrderType.FREIGHT.getName());
				bp.setOrder_type(Constant.PLATFORM_ORDER_TYPE_2);
				bp.setAmount(request.getParameter("total_price"));
			}
			String requestid = YeePayTool.getRequestId();
			String backUrl = "http://app.xslwl56.com:8082/TheOwner/app/orderPayCallback.yc";
			request.setAttribute("requestid", requestid);
			request.setAttribute("callbackurl", backUrl);
			request.setAttribute("userno", order.getUser_id());
			
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
			bp.setRequestid(requestid);
			bp.setAssure("0");
			bp.setCallbackurl(backUrl);
			bp.setWebcallbackurl("");
			bp.setOrder_number(order.getOrder_number());
			bp.setTime(DateUtil.getDateTimeFormatString());
			bp.setState(Constant.PLATFORM_PAYMENT_STATE_1);
			bp.setPayproducttype(ZGTDATA.PAYTYPE);
			bp.setUser_id(order.getUser_id());
			if(bankPaymentService.getBankPayment(bp)==null){
				bankPaymentService.addBankPayment(bp);
			}
			//第七步 进行业务处理
			String payurl	= responseDataMap.get("payurl");
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, payurl, response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 订单支付回调
	 * @Author:luojing
	 * @2016年8月16日 下午5:45:50
	 */
	@RequestMapping("app/orderPayCallback")
	public void orderPayCallback(HttpServletRequest request,HttpServletResponse response){
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
			
			//修改账单信息表支付状态
			LcPlatformOrderBill bill = new LcPlatformOrderBill();
			bill.setOrder_number(bp.getOrder_number());
			bill.setIs_payment(Constant.PLATFORM_PAYMENT_STATE_0);
			orderBillService.updateOrderBill(bill);
			
			if(bp.getOrder_type().equals(Constant.PLATFORM_ORDER_TYPE_1)){
				//预约 
				//修改订单支付状态
				PlatformOrder order = new PlatformOrder();
				order.setIs_draft(1);
				order.setOrder_number(bp.getOrder_number());
				order.setIs_payment(Constant.PLATFORM_PAYMENT_STATE_0);
				platformOrderService.updateOrder(order);
				
			}else if(bp.getOrder_type().equals(Constant.PLATFORM_ORDER_TYPE_2)){
				//运费
				//修改订单支付状态
				PlatformOrder order = new PlatformOrder();
				order.setOrder_number(bp.getOrder_number());
				order.setState(3);//议价
				order.setIs_payment(Constant.PLATFORM_PAYMENT_STATE_0);
				platformOrderService.updateOrder(order);
			}
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
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 根据上线网点id和下线网点id获取地址
	 * @Author:luojing
	 * @2016年8月17日 下午1:51:30
	 */
	@RequestMapping("app/getOutlesAddress")
	public void cancelOrders(BigInteger startId,BigInteger endId,HttpServletRequest request,HttpServletResponse response){
		try{
			TmsOutletsInfo toi=new TmsOutletsInfo();
			//起始网点
			toi.setId(startId);
			toi=iTmsOutletsInfoService.getSingleInfo(toi);
			if(toi==null){
				FengUtil.RuntimeExceptionFeng("此网点未获取到...");
			}
			XZQHInfo xz=new XZQHInfo();
			//省
			xz.setId(toi.getProvince());
			toi.setProvince(xzqh.getSingleInfo(xz).getName());
			//市
			xz.setId(toi.getCounty());
			toi.setCounty(xzqh.getSingleInfo(xz).getName());
			//县
			xz.setId(toi.getCity());;
			toi.setCity(xzqh.getSingleInfo(xz).getName());
			String startAdd=toi.getProvince()+toi.getCounty()+toi.getCity()+toi.getAddress();
			//结束网点
			toi.setId(endId);
			toi=iTmsOutletsInfoService.getSingleInfo(toi);
			if(toi==null){
				FengUtil.RuntimeExceptionFeng("此网点未获取到...");
			}
			//省
			xz.setId(toi.getProvince());
			toi.setProvince(xzqh.getSingleInfo(xz).getName());
			//市
			xz.setId(toi.getCounty());
			toi.setCounty(xzqh.getSingleInfo(xz).getName());
			//县
			xz.setId(toi.getCity());;
			toi.setCity(xzqh.getSingleInfo(xz).getName());
			String endAdd=toi.getProvince()+toi.getCounty()+toi.getCity()+toi.getAddress();
			
			Map<String,String> map=new HashMap<String,String>();
			map.put("startAdd", startAdd);
			map.put("endAdd", endAdd);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, map, response);
		}catch(FengRuntimeException e){
			e.printErrorInfo();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,"暂无结果", response);
		}
	}
}
