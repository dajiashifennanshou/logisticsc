package com.brightsoft.controller.tms.platform.startsitemanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.common.enums.CountCostModeEnum;
import com.brightsoft.common.enums.PayModeEnum;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.AdditionalServerConf;
import com.brightsoft.model.DriverInfo;
import com.brightsoft.model.LadingOrder;
import com.brightsoft.model.LadingOrderCargoInfo;
import com.brightsoft.model.LadingOrderCostInfo;
import com.brightsoft.model.LineInfo;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformOrder;
import com.brightsoft.model.PlatformOrderBill;
import com.brightsoft.model.User;
import com.brightsoft.service.platform.DictionaryService;
import com.brightsoft.service.platform.PlatformOrderAdditionalServerService;
import com.brightsoft.service.platform.PlatformOrderBillService;
import com.brightsoft.service.platform.PlatformOrderCargoService;
import com.brightsoft.service.platform.PlatformOrderServiceImpl;
import com.brightsoft.service.platform.XzqhServiceImpl;
import com.brightsoft.service.tms.platform.AdditionalServerServiceImpl;
import com.brightsoft.service.tms.platform.DriverInfoService;
import com.brightsoft.service.tms.platform.LineInfoService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.VehicleInfoService;
import com.brightsoft.service.tms.platform.startsitemanager.DeliveryCargoManagerService;
import com.brightsoft.service.tms.platform.startsitemanager.LadingOrderCargoInfoService;
import com.brightsoft.service.tms.platform.startsitemanager.LadingOrderCostInfoService;
import com.brightsoft.service.tms.platform.startsitemanager.LadingOrderService;

/**
 * 
 * 提货管理 Controller
 */
@Controller
@RequestMapping("/deliverycargo")
public class DeliveryCargoManagerController extends BaseController{

	@Autowired
	private PlatformOrderServiceImpl orderService;
	
	@Autowired
	private PlatformOrderAdditionalServerService orderAdditionServerService;
	
	@Autowired
	private PlatformOrderCargoService orderCargoService;
	
	@Autowired
	private PlatformOrderBillService orderBillService;
	
	@Autowired
	private DeliveryCargoManagerService deliveryCargoService;
	
	@Autowired
	private LineInfoService lineInfoService;
	
	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private VehicleInfoService vechicleService;
	
	@Autowired
	private XzqhServiceImpl xzqhService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private AdditionalServerServiceImpl additionalServerService;
	
	@Autowired
	private LadingOrderCargoInfoService ladingOrderCargoInfoService;
	
	@Autowired
	private LadingOrderCostInfoService ladingOrderCostInfoService;
	
	@Autowired
	private DriverInfoService driverInfoService;
	
	@Autowired
	private LadingOrderService ladingOrderService;
	
	/**
	 * 跳转到 TMS 发站管理 提货单
	 * @return
	 */
	@RequestMapping("/deliverycargomanager")
	public String toTmsDeliveryCargoManager(HttpServletRequest request){
		return "/tms/platform/startsitemanager/delivery-cargo-manager";
	}
	
	/**
	 * 获取货运交易系统订单信息
	 * @param orderNumber 订单编号
	 * @return
	 */
	@RequestMapping("/getplatformorderinfo")
	@ResponseBody
	public Object getPlatformOrderInfo(String orderNumber){
		PlatformOrder order = orderService.selectByOrderNumber(orderNumber);
		return order;
	}
	
	/**
	 * 获取货运交易系统账单信息
	 * @param orderNumber 订单编号
	 * @return
	 */
	@RequestMapping("/getplatformorderbillinfo")
	@ResponseBody
	public Object getPlatformOrderBillInfo(String orderNumber){
		PlatformOrderBill orderBill = orderBillService.selectByOrderNumber(orderNumber);
		return orderBill;
	}
	
	/**
	 * 获取货运交易系统订单增值服务信息
	 * @param orderNumber 订单编号
	 * @return
	 */
	@RequestMapping("/getplatformorderadditionalserverinfo")
	@ResponseBody
	public Object getPlatformOrderAdditionalServerInfo(String orderNumber){
		return orderAdditionServerService.selectByOrderNumber(orderNumber);
	}
	
	/**
	 * 获取车辆信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getvehicleinfo")
	@ResponseBody
	public Object getVehicleInfo(HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		return vechicleService.selectByOutletsId(user.getOutletsId());
	}
	
	/**
	 * 获取行政区划信息
	 * @param pid
	 * @return
	 */
	@RequestMapping("/getxzqhinfo")
	@ResponseBody
	public Object getXzqhInfo(String pid){
		return xzqhService.selectByPid(pid);
	}
	
	/**
	 * 获取线路网点信息
	 * @param lineId 线路ID
	 * @return
	 */
	@RequestMapping("/getlineoutletsinfo")
	@ResponseBody
	public Object getLineOutletsInfo(String lineId){
		LineInfo lineInfo = lineInfoService.selectByPrimaryKey(Long.parseLong(lineId));
		// 查询起始网点信息
		OutletsInfo startOutlets = outletsService.selectByPrimaryKey(lineInfo.getStartOutlets());
		// 查询目的网点信息
		OutletsInfo endOutlets = outletsService.selectByPrimaryKey(lineInfo.getEndOutlets());
		List<OutletsInfo> outletsInfos = new ArrayList<OutletsInfo>();
		outletsInfos.add(startOutlets);
		outletsInfos.add(endOutlets);
		return outletsInfos;
	}
	
	/**
	 * 获取当前登录用户的网点信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getcurrentoutletsinfo")
	@ResponseBody
	public Object getCurrentOutletsInfo(HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		Long outletsId = user.getOutletsId();
		if(outletsId == null){
			return null;
		}else{
			return outletsService.selectByPrimaryKey(outletsId);
		}
	}
	
	/**
	 * 获取专线公司的网点信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getoutletsinfo")
	@ResponseBody
	public Object getOutletsInfo(HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		Long companyId = user.getCompanyId();
		List<OutletsInfo> outletsInfos = outletsService.selectByCompanyId(companyId);
		return outletsInfos;
	}
	
	/**
	 * 跳转到 TMS 发站管理 提货单
	 * @return
	 */
	@RequestMapping("/deliverycargoorder")
	public String toTmsDeliveryCargoOrder(String orderNumber, HttpServletRequest request){
		request.setAttribute("orderNumber", orderNumber);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		AdditionalServerConf additionalServer = additionalServerService.selectByOutletsId(user.getOutletsId());
		// 企业增值服务 配置费率信息
		List<DriverInfo> driverInfos = driverInfoService.selectByOutletsId(user.getOutletsId());
		request.setAttribute("additionalServer", additionalServer);
		request.setAttribute("driverInfos", driverInfos);
		request.setAttribute("payMethods", getPayMethod());
		return "/tms/platform/startsitemanager/delivery-cargo-order";
	}
	
	// 获取 付款方式
	private List<Map<String, Object>> getPayMethod(){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		PayModeEnum[] payModeEnums = PayModeEnum.values();
		for (PayModeEnum payModeEnum : payModeEnums) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", payModeEnum.getValue());
			map.put("name", payModeEnum.getName());
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 根据订单编号 查询货运交易系统订单货物信息
	 * @param orderNumber
	 * @return
	 */
	@RequestMapping("/getordercargoinfo")
	@ResponseBody
	public Object getOrderCargoInfo(String orderNumber){
		if(StringUtils.isEmpty(orderNumber)){
			return null;
		}
		return orderCargoService.selectByOrderNumber(orderNumber);
	}
	
	/**
	 * 保存提货单数据
	 * @param ladingOrder 提货单基础数据
	 * @param ladingOrderCargo 提货单货物数据
	 * @param ladingOrderCostInfo 提货单费用信息
	 * @return
	 */
	@RequestMapping("/saveladingorder")
	@ResponseBody
	public String saveLadingOrder(String ladingOrder, String ladingOrderCargo, String ladingOrderCostInfo, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = deliveryCargoService.saveLadingOrder(ladingOrder, ladingOrderCargo, ladingOrderCostInfo, user);
		if(rows > 0){
			return SUCCESS_STATUS;
		}else{
			return FAILURE_STATUS;
		}
	}
	
	/**
	 * 获取派车单状态
	 * @return
	 */
	@RequestMapping("/getladingorderstatus")
	@ResponseBody
	public String getLadingOrderStatus(){
		return deliveryCargoService.getLadingOrderStatus();
	}
	
	/**
	 * 查询提货单信息列表
	 * @param ladingOrderStatus
	 * @param startTime
	 * @param endTiem
	 * @param condition
	 * @return
	 */
	@RequestMapping("/searchladingorder")
	@ResponseBody
	public Object searchLadingOrder(SearchParams params, HttpSession session){
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
		return deliveryCargoService.selectLadingOrderByParam(params);
	}
	
	/**
	 * 根据运单号 货物提货单信息
	 * @param wayBillNumber
	 * @return
	 */
	@RequestMapping("/getladingorderbywaybillnumber")
	@ResponseBody
	public Object getLadingOrderByWayBillNumber(String wayBillNumber){
		return deliveryCargoService.selectByWayBillNumber(wayBillNumber);
	}
	
	/**
	 * 派车提货
	 * @param ladingOrder
	 * @return
	 */
	@RequestMapping("/deliverytakecargo")
	@ResponseBody
	public String deliveryTakeCargo(String wayBillNumber, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = 0;
		try {
			rows = deliveryCargoService.saveDeliveryTakeCargo(wayBillNumber, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 作废
	 * @param ladingOrder
	 * @return
	 */
	@RequestMapping("/abolish")
	@ResponseBody
	public String abolish(String wayBillNumber, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		Integer rows = deliveryCargoService.abolishDeliveryOrder(wayBillNumber, user);
		if(rows != null && rows > 0){
			return SUCCESS_STATUS;
		}else{
			return FAILURE_STATUS;
		}
	}
	
	/**
	 * 修改派车单状态
	 * @param ladingOrder
	 * @return
	 */
	@RequestMapping("/updateladingorderstatus")
	@ResponseBody
	public String updateLadingOrderStatus(String wayBillNumber, Integer status, String reason){
		Integer rows = deliveryCargoService.updateLadingOrderStatus(wayBillNumber, status);
		if(rows != null && rows > 0){
			return SUCCESS_STATUS;
		}else{
			return FAILURE_STATUS;
		}
	}
	
	/**
	 * 获取付款方式
	 * @return
	 */
	@RequestMapping("/getpaymode")
	@ResponseBody
	public Object getPayMode(){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		PayModeEnum[] modes = PayModeEnum.values();
		for (PayModeEnum mode : modes) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", mode.getName());
			map.put("value", mode.getValue());
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 获取包装类型
	 * @return
	 */
	@RequestMapping("/getpackagetype")
	@ResponseBody
	public Object getPackageType(){
		return dictionaryService.selectDictDataByType(DictionaryType.CARGO_PACKAGE_TYPE);
	}
	
	/**
	 * 获取计费方式
	 * @return
	 */
	@RequestMapping("/getcountcostmode")
	@ResponseBody
	public Object getCountCostMode(){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		CountCostModeEnum[] countCostModes = CountCostModeEnum.values();
		for (CountCostModeEnum countCostMode : countCostModes) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", countCostMode.getName());
			map.put("value", countCostMode.getValue());
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 跳转到提货打 打印预览页面
	 * @return
	 */
	@RequestMapping("/todeliverycargoorderpreview")
	public String toDeliveryCargoOrderPreview(HttpServletRequest request){
		String wayBillNumber = request.getParameter("wayBillNumber");
		LadingOrder ladingOrder = deliveryCargoService.selectByWayBillNumber(wayBillNumber);
		List<LadingOrderCargoInfo> ladingOrderCargoInfos = ladingOrderCargoInfoService.selectByWayBillNumber(wayBillNumber);
		List<LadingOrderCostInfo> ladingOrderCostInfos = ladingOrderCostInfoService.selectByWayBillNumber(wayBillNumber);
		request.setAttribute("ladingOrder", ladingOrder);
		request.setAttribute("ladingOrderCargoInfos", ladingOrderCargoInfos);
		request.setAttribute("ladingOrderCostInfos", JSONArray.toJSONString(ladingOrderCostInfos));
		return "/tms/platform/startsitemanager/delivery-cargo-order-preview";
	}
	
	/**
	 * 查看详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/todeliverycargoorderdetail")
	public String toDeliveryCargoOrderDetail(long id, HttpServletRequest request){
		LadingOrder ladingOrder = ladingOrderService.selectById(id);
		if(ladingOrder != null){
			List<LadingOrderCargoInfo> ladingOrderCargoInfos = ladingOrderCargoInfoService.selectByWayBillNumber(ladingOrder.getWayBillNumber());
			List<LadingOrderCostInfo> ladingOrderCostInfos = ladingOrderCostInfoService.selectByWayBillNumber(ladingOrder.getWayBillNumber());
			request.setAttribute("ladingOrderCargoInfos", JSONObject.toJSONString(ladingOrderCargoInfos));
			request.setAttribute("ladingOrderCostInfos", JSONObject.toJSONString(ladingOrderCostInfos));
		}
		request.setAttribute("ladingOrder", ladingOrder);
		return "/tms/platform/startsitemanager/delivery-cargo-order-detail";
	}
}
