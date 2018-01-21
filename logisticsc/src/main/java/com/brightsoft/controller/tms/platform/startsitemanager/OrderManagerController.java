package com.brightsoft.controller.tms.platform.startsitemanager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.common.enums.OrderStatusEnum;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.tms.AdditionalServerConfMapper;
import com.brightsoft.dao.tms.LineInfoMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.model.AdditionalServerConf;
import com.brightsoft.model.LadingOrder;
import com.brightsoft.model.LineInfo;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.OutletsPriceRangeConf;
import com.brightsoft.model.PlatformDictionary;
import com.brightsoft.model.PlatformMineOrder;
import com.brightsoft.model.PlatformOrder;
import com.brightsoft.model.PlatformOrderAdditionalServer;
import com.brightsoft.model.PlatformOrderBill;
import com.brightsoft.model.User;
import com.brightsoft.model.platformBankPayment;
import com.brightsoft.model.platformBankRefund;
import com.brightsoft.service.platform.DictionaryService;
import com.brightsoft.service.platform.PlatformOrderAdditionalServerService;
import com.brightsoft.service.platform.PlatformOrderBillService;
import com.brightsoft.service.platform.PlatformOrderCargoService;
import com.brightsoft.service.platform.PlatformOrderServiceImpl;
import com.brightsoft.service.platform.platformBankPaymentServiceImpl;
import com.brightsoft.service.tms.platform.OutletsPriceRangeConfService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.startsitemanager.LadingOrderService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.yeepay.ZGTDataAttribute;
import com.brightsoft.utils.yeepay.ZGTUtils;

/**
 * 
 * TMS订单管理 Controller
 */
@Controller
@RequestMapping("/tmsorder")
public class OrderManagerController extends BaseController {

	@Autowired
	private PlatformOrderServiceImpl orderService;
	
	@Autowired
	private PlatformOrderCargoService platformOrderCargoService;
	
	@Autowired
	private PlatformOrderAdditionalServerService platformOrderAdditionalServerService;
	
	@Autowired
	private PlatformOrderBillService platformOrderBillService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private AdditionalServerConfMapper additionalServerConfMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	@Autowired
	private LineInfoMapper lineInfoMapper;
	
	@Autowired
	private OutletsPriceRangeConfService outletsPriceRangeConfService;
	
	@Autowired
	private LadingOrderService ladingOrderService;
	
	@Autowired
	private PlatformOrderServiceImpl orderServiceImpl;
	
	@Autowired
	private platformBankPaymentServiceImpl platformBankPayment;
	
	@Autowired
	private OutletsService outletsService;
	/**
	 * 跳转到 专线营运系统 发站管理  - 货运交易系统订单
	 * @return
	 */
	@RequestMapping("/totmsorderpage")
	public String toTmsOrderManagerPage(HttpServletRequest request){
		request.setAttribute("orderStatusList", getOrderStatusList());
		return "/tms/platform/startsitemanager/order-manager";
	}
	
	// 获取 订单状态列表
	private List<Map<String, Object>> getOrderStatusList(){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		OrderStatusEnum[] orderStatusEnums = OrderStatusEnum.values();
		for (OrderStatusEnum orderStatusEnum : orderStatusEnums) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", orderStatusEnum.getValue());
			map.put("name", orderStatusEnum.getName());
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 获取 订单状态
	 * @return
	 */
	@RequestMapping("/getorderstatus")
	@ResponseBody
	public String getOrderStatus(){
		String result = "[";
		OrderStatusEnum[] enums = OrderStatusEnum.values();
		for (int i = 0; i < enums.length; i++) {
			OrderStatusEnum oe = enums[i];
			if(i < enums.length - 1){
				result += "{'name':'" + oe.getName() + "','value':'" + oe.getValue() + "'},";
			}else{
				result += "{'name':'" + oe.getName() + "','value':'" + oe.getValue() + "'}";
			}
		}
		result += "]";
		return result;
	}
	
	/**
	 * 订单查询
	 * @param orderStatus 订单状态
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param condition 条件
	 * @return
	 */
	@RequestMapping("/searchOrder")
	@ResponseBody
	public Object searchOrder(SearchParams params, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user.getOutletsId() == null||user.getOutletsId().equals("")){
			List<OutletsInfo> outs =outletsService.selectOutlesNotBind(user.getCompanyId());
			String ids = "";
			for(int i =0;i<outs.size();i++){
				if(i == 0){
					ids += outs.get(i).getId();
				}else{
					ids += ","+outs.get(i).getId();
				}
			}
			if(outs == null || outs.size() == 0){
				params.setOutletsIds("no");
			}else{
				params.setOutletsIds(ids);
			}
		}else{
			params.setOutletsIds(user.getOutletsId()+"");
		}
		return orderService.selectOrderByParams(params);
	}
	
	/**
	 * 接单
	 * @param orderNumbers 订单编号集合
	 * @return
	 */
	@RequestMapping("/receiveorder")
	@ResponseBody
	public String receiveOrder(String orderNumbers, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = 0;
		try {
			rows = orderService.saveReceiveOrder(orderNumbers, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rows > 0){
			return SUCCESS_STATUS;
		}else{
			return FAILURE_STATUS;
		}
	}
	
	/**
	 * 拒绝订单
	 * @param orderNumbers 订单编号集合
	 * @return
	 */
	@RequestMapping("/refuseorder")
	@ResponseBody
	public String refuseOrder(String orderNumbers, HttpSession session,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = 0;
		try {
			List<String> orderNumberList = JSONArray.parseArray(orderNumbers, String.class);
			for (int i = 0; i < orderNumberList.size(); i++) {
				//获取订单号支付记录
				PlatformMineOrder order = orderServiceImpl.selectBankOrder(orderNumberList.get(i));
				if(order.getIsPayment() == 0){
					platformBankPayment bankPayment = null;
					bankPayment = platformBankPayment.selectBankPaymentByOrderNumbe(order.getOrderNumber(),Const.PLATFORM_BANK_ORDER_TYPE_1);
					if(null ==bankPayment){
						return FAILURE_STATUS;
					}else{
						StringBuffer sb = new StringBuffer();
						sb.append("RD");
						sb.append(DateTools.dateToStrCustomer(new Date(),DateTools.PATTERN_YYYYMMDDHHMMSSSSS));
						platformBankRefund bankRefund = new platformBankRefund();
						bankRefund.setCustomernumber("10013368774");
						bankRefund.setRequestid(sb.toString());
						bankRefund.setOrderrequestid(bankPayment.getRequestid());
						bankRefund.setAmount(bankPayment.getAmount());
						bankRefund.setOrderNumber(order.getOrderNumber());
						bankRefund.setTime(new Date());
						bankRefund.setState(Const.PLATFORM_ORDER_BANK_REFUND_0);
						bankRefund.setUsername(user.getLoginName());
						bankRefund.setOrderType(bankPayment.getOrderType());
						bankRefund.setRefundType(Const.PLATFORM_ORDER_BANK_REFUND_REFUND_TYPE_1);
						request.setAttribute("requestid",sb.toString());
						request.setAttribute("orderrequestid",bankRefund.getOrderrequestid());
						request.setAttribute("amount",bankRefund.getAmount());
						request.setAttribute("confirm","1");
						String data = ZGTDataAttribute.buildRefundData(request);
						//第二步 发起请求
						String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.REFUNDAPI_NAME);
						Result result	= ZGTUtils.httpPost(requestUrl, data, ZGTUtils.REFUNDAPI_RESPONSE_HMAC_ORDER);
						//第七步 进行业务处理
						Map<String, String> responseMap = (Map<String, String>) result.getData();
						if(!responseMap.get("code").equals("1")) {
							return FAILURE_STATUS;
						}else{
							rows = orderService.saveRefuseOrder(orderNumberList.get(i), user);
							if(rows <= 0){
								return FAILURE_STATUS;
							}
						}
				}
			}else{
				rows = orderService.saveRefuseOrder(orderNumberList.get(i), user);
				if(rows <= 0){
					return FAILURE_STATUS;
				}
			}
		}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rows > 0){
			return SUCCESS_STATUS;
		}else{
			return FAILURE_STATUS;
		}
	}
	
	/**
	 * 作废订单
	 * @param orderNumbers 订单编号集合
	 * @return
	 */
	@RequestMapping("/discardorder")
	@ResponseBody
	public String discardOrder(String orderNumbers, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = 0;
		try {
			rows = orderService.saveDiscardOrder(orderNumbers, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rows > 0){
			return SUCCESS_STATUS;
		}else{
			return FAILURE_STATUS;
		}
	}
	
	/**
	 * 验证提货订单
	 * @param orderNumber
	 * @return
	 */
	@RequestMapping("/validateorderstatusdeliverycargo")
	@ResponseBody
	public String validateOrderStatusDeliveryCargo(String orderNumber){
		boolean b = orderService.validateOrderStatusDeliveryCargo(orderNumber);
		if(b){
			return SUCCESS_STATUS;
		}else{
			return FAILURE_STATUS;
		}
	}
	/**
	 * 查看订单详细信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/orderitems")
	public ModelAndView toListOrderItems(Long id){
		ModelAndView mav = new ModelAndView("/tms/platform/startsitemanager/order-cargo-items");
		if(id!=null){
			PlatformOrder platformOrder = orderService.selectOrderItems(id);
			String jsonCargo = JSONObject.toJSONString(platformOrder.getPlatformOrderCargos());
			if(platformOrder != null){
				PlatformOrderBill orderBill = platformOrderBillService.selectByOrderNumber(platformOrder.getOrderNumber());
				mav.addObject("orderBill", orderBill);	
			}
			mav.addObject("jsonCargo", jsonCargo);
			mav.addObject("orderItems", platformOrder);
		}
		return mav;
	}
	
	/**
	 * 跳转到订单货物确认页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/ordercargoconfirm")
	public ModelAndView toOrderCargoConfirm(Long id){
		ModelAndView mav = new ModelAndView("/tms/platform/startsitemanager/order-cargo-confirm");
		if(id!=null){
			PlatformOrder platformOrder = orderService.selectOrderItems(id);
			String jsonCargo = JSONObject.toJSONString(platformOrder.getPlatformOrderCargos());
			List<PlatformDictionary> vehicleTypes = dictionaryService.selectDictDataByType(DictionaryType.VEHICLE_TYPE);
			mav.addObject("jsonCargo", jsonCargo);
			mav.addObject("orderItems", platformOrder);
			mav.addObject("vehicleTypes", vehicleTypes);
			
			LineInfo lineInfo = lineInfoMapper.selectByPrimaryKey(platformOrder.getTmsLineId());
			OutletsInfo outletsInfo = outletsInfoMapper.selectById(lineInfo.getOutletsId());
			// 增值服务配置信息
			AdditionalServerConf additionalServerConf = additionalServerConfMapper.selectByOutletsId(outletsInfo.getId());
			mav.addObject("additionalServerConf", JSONObject.toJSONString(additionalServerConf));
			// 提送货配置
			List<OutletsPriceRangeConf> startOutletsPriceRangeConfs = outletsPriceRangeConfService.selectByOutletsId(lineInfo.getStartOutlets());
			List<OutletsPriceRangeConf> endOutletsPriceRangeConfs = outletsPriceRangeConfService.selectByOutletsId(lineInfo.getEndOutlets());
			mav.addObject("startOutletsPriceRangeConfs", JSONArray.toJSONString(startOutletsPriceRangeConfs));
			mav.addObject("endOutletsPriceRangeConfs", JSONArray.toJSONString(endOutletsPriceRangeConfs));
			// 用户选择的增值服务
			PlatformOrderAdditionalServer additionalServer = platformOrderAdditionalServerService.selectByOrderNumber(platformOrder.getOrderNumber());
			mav.addObject("additionalServer", JSONObject.toJSONString(additionalServer));
			// 提货单 车辆信息
			String wayBillNumber = platformOrder.getWayBillNumber();
			if(StringUtils.isNotEmpty(wayBillNumber)){
				LadingOrder ladingOrder = ladingOrderService.selectByWayBillNumber(wayBillNumber);
				mav.addObject("ladingOrder", ladingOrder);
			}
		}
		return mav;
	}
	
	/**
	 * 获取 货运交易系统订单信息
	 * @param orderNumber 订单号
	 * @return
	 */
	@RequestMapping("/getorderbyordernumber")
	@ResponseBody
	public Object getOrderByOrderNumber(String orderNumber){
		return orderService.selectByOrderNumber(orderNumber);
	}
	
	/**
	 * 获取 货运交易系统订单货物信息
	 * @param orderNumber 订单号
	 * @return
	 */
	@RequestMapping("/getordercargobyordernumber")
	@ResponseBody
	public Object getOrderCargoByOrderNumber(String orderNumber){
		return platformOrderCargoService.selectByOrderNumber(orderNumber);
	}
	
	/**
	 * 获取 货运交易系统订单增值服务信息
	 * @param orderNumber 订单号
	 * @return
	 */
	@RequestMapping("/getorderadditionalserverbyordernumber")
	@ResponseBody
	public Object getOrderAdditionalServerByOrderNumber(String orderNumber){
		return platformOrderAdditionalServerService.selectByOrderNumber(orderNumber);
	}
	
	/**
	 * 获取 货运交易系统账单信息
	 * @param orderNumber 订单号
	 * @return
	 */
	@RequestMapping("/getorderbillbyordernumber")
	@ResponseBody
	public Object getOrderBillByOrderNumber(String orderNumber){
		return platformOrderBillService.selectByOrderNumber(orderNumber);
	}
	
	/**
	 * 保存 确认订单信息
	 * @param cityDriverStr
	 * @param orderCargoStr
	 * @param orderBillStr
	 * @return
	 */
	@RequestMapping("/saveconfirmorder")
	@ResponseBody
	public String saveConfirmOrder(String orderCargoStr, String orderBillStr, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = orderService.saveConfirmOrder(orderCargoStr, orderBillStr, user);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 验证订单 是否已支付
	 * @param orderNumber
	 * @return
	 */
	@RequestMapping("/validateorderispay")
	@ResponseBody
	public boolean validateOrderIsPay(String orderNumber){
		PlatformOrderBill platformOrderBill = platformOrderBillService.selectByOrderNumber(orderNumber);
		if(platformOrderBill == null){
			return false;
		}
		Integer isPayment = platformOrderBill.getIsPayment();
		if(isPayment == 0){
			return true;
		}
		return false;
	}
}
