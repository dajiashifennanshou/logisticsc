package com.brightsoft.controller.tms.platform.financialmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.common.enums.ReceiveMoneyCostTypeEnum;
import com.brightsoft.common.enums.ReceiveMoneyOrderStatusEnum;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.ReceiveMoneyOrderSearchParams;
import com.brightsoft.controller.vo.WayBillSearchParams;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.ReceiveMoneyOrder;
import com.brightsoft.model.ReceiveMoneyOrderRecord;
import com.brightsoft.model.User;
import com.brightsoft.repeat.RepeatSubmission;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.financialmanager.ReceiveMoneyOrderRecordService;
import com.brightsoft.service.tms.platform.financialmanager.ReceiveMoneyOrderService;
import com.brightsoft.service.tms.platform.startsitemanager.WayBillOrderService;

/**
 * 运单运费管理
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/tms/waybillfreight")
public class WayBllFreightController extends BaseController{
	
	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private WayBillOrderService wayBillOrderService;
	
	@Autowired
	private ReceiveMoneyOrderService receiveMoneyOrderService;
	
	@Autowired
	private ReceiveMoneyOrderRecordService receiveMoneyOrderRecordService;
	
	/**
	 * 跳转到 财务管理-运单运费列表
	 * @return
	 */
	@RequestMapping("/towaybillfreigth")
	public String toWayBillFreight(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		List<OutletsInfo> outletsInfos = outletsService.selectByCompanyId(user.getCompanyId());
		request.setAttribute("outletsInfos", outletsInfos);
		return "/tms/platform/financialmanager/add/way-bill-feright-list";
	}
	
	/**
	 * 获取运单费用列表数据
	 * @return
	 */
	@RequestMapping("/getwaybillfreightlist")
	@ResponseBody
	public Object getWayBillFreightList(WayBillSearchParams params, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user == null){
			return null;
		}
		params.setOutletsId(user.getOutletsId());
		//params.setPayMethodNo(PayModeEnum.IMMEDIATELY_PAY.getValue() + "");
		params.setCostType(ReceiveMoneyCostTypeEnum.FREIGHT.getValue() + "");
		return wayBillOrderService.selectWayBillFreightList(params);
	}
	
	/**
	 * 保存收款订单
	 * @param receiveMoneyOrder
	 * @return
	 */
	@RequestMapping("/savereceivemoneyorder")
	@ResponseBody
	public String saveReceiveMoneyOrder(ReceiveMoneyOrder receiveMoneyOrder, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = receiveMoneyOrderService.saveReceiveMoneyOrder(receiveMoneyOrder, user);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 跳转到 财务管理-运费收款订单列表
	 * @return
	 */
	@RequestMapping("/tofreightreceivemoneyorder")
	@RepeatSubmission(needSaveToken = true)
	public String toFreightReceiveMoneyOrder(HttpServletRequest request){
		request.setAttribute("receiveMoneyOrderStatusList", getReceiveMoneyOrderStatus());
		return "/tms/platform/financialmanager/add/feright-receive-money-order";
	}
	
	// 获取收款订单状态
	private List<Map<String, Object>> getReceiveMoneyOrderStatus(){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		ReceiveMoneyOrderStatusEnum[] statusEnums = ReceiveMoneyOrderStatusEnum.values();
		for (ReceiveMoneyOrderStatusEnum orderStatusEnum : statusEnums) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", orderStatusEnum.getValue());
			map.put("name", orderStatusEnum.getName());
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 获取运费 收款订单列表
	 * @param params
	 * @param session
	 * @return
	 */
	@RequestMapping("/getreceivemoneyorderlist")
	@ResponseBody
	public Object getReceiveMoneyOrderList(ReceiveMoneyOrderSearchParams params, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		Long outletsId = user.getOutletsId();
		if(outletsId == null){
			return null;
		}
		params.setOutletsId(outletsId);
		params.setCostType(ReceiveMoneyCostTypeEnum.FREIGHT.getValue() + "");
		return receiveMoneyOrderService.selectByParams(params);
	}
	
	/**
	 * 获取 收费运单 列表
	 * @param receiveMoneyOrderId
	 * @return
	 */
	@RequestMapping("/getreceivemoneywaybilllist")
	@ResponseBody
	public Object getReceiveMoneyWayBillList(String receiveMoneyOrderId){
		return wayBillOrderService.selectByReceiveMoneyOrderId(receiveMoneyOrderId);
	}
	
	/**
	 * 跳转到 财务管理-运单代收款列表
	 * @return
	 */
	@RequestMapping("/towaybillagencyfund")
	public String toWayBillAgencyFund(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		List<OutletsInfo> outletsInfos = outletsService.selectByCompanyId(user.getCompanyId());
		request.setAttribute("outletsInfos", outletsInfos);
		return "/tms/platform/financialmanager/add/way-bill-agency-fund-list";
	}
	
	/**
	 * 获取代收货款运单列表
	 * @return
	 */
	@RequestMapping("/getwaybillagencyfundlist")
	@ResponseBody
	public Object getWayBillAgencyFundList(WayBillSearchParams params, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user == null){
			return null;
		}
		params.setOutletsId(user.getOutletsId());
		params.setIsAgencyFund("1");
		params.setCostType(ReceiveMoneyCostTypeEnum.AGENCY_FUND.getValue() + "");
		return wayBillOrderService.selectWayBillFreightList(params);
	}
	
	/**
	 * 跳转到 财务管理-代收款收款订单列表
	 * @return
	 */
	@RequestMapping("/toagencyfundreceivemoneyorder")
	@RepeatSubmission(needSaveToken = true)
	public String toAgencyFundReceiveMoneyOrder(HttpServletRequest request){
		request.setAttribute("receiveMoneyOrderStatusList", getReceiveMoneyOrderStatus());
		return "/tms/platform/financialmanager/add/agency-fund-receive-money-order";
	}
	
	/**
	 * 获取代收款 收款订单列表
	 * @param params
	 * @param session
	 * @return
	 */
	@RequestMapping("/getagencyfundreceivemoneyorderlist")
	@ResponseBody
	public Object getAgencyFundReceiveMoneyOrderList(ReceiveMoneyOrderSearchParams params, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		Long outletsId = user.getOutletsId();
		if(outletsId == null){
			return null;
		}
		params.setOutletsId(outletsId);
		params.setCostType(ReceiveMoneyCostTypeEnum.AGENCY_FUND.getValue() + "");
		return receiveMoneyOrderService.selectByParams(params);
	}
	
	/**
	 * 保存收款订单收款记录
	 * @param receiveMoneyOrderRecord
	 * @return
	 */
	@RequestMapping("/savereceivemoneyorderrecord")
	@ResponseBody
	@RepeatSubmission(needUpdateToken = true)
	public String saveReceiveMoneyOrderRecord(ReceiveMoneyOrderRecord receiveMoneyOrderRecord, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = 0;
		try {
			rows = receiveMoneyOrderRecordService.save(receiveMoneyOrderRecord, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 获取 收款订单收款记录
	 * @param receiveMoneyOrderId
	 * @return
	 */
	@RequestMapping("/getreceivemoneyorderrecord")
	@ResponseBody
	public Object getReceiveMoneyOrderRecord(String receiveMoneyOrderId){
		return receiveMoneyOrderRecordService.selectByReceiveMoneyOrderId(receiveMoneyOrderId);
	}
}
