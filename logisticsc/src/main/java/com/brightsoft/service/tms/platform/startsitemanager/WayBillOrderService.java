package com.brightsoft.service.tms.platform.startsitemanager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.brightsoft.common.enums.DepartListStatusEnum;
import com.brightsoft.common.enums.ExceptionStatusEnum;
import com.brightsoft.common.enums.LadingOrderStatusEnum;
import com.brightsoft.common.enums.OrderNumberTypeEnum;
import com.brightsoft.common.enums.OrderStatusEnum;
import com.brightsoft.common.enums.OutDepartListStatusEnum;
import com.brightsoft.common.enums.PayModeEnum;
import com.brightsoft.common.enums.ReceiptStatusEnum;
import com.brightsoft.common.enums.ReceiveTypeEnum;
import com.brightsoft.common.enums.SmsTemplateEnum;
import com.brightsoft.common.enums.WayBillOrderStatusEnum;
import com.brightsoft.controller.vo.ReceiptBillSearchParams;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.controller.vo.WayBillSearchParams;
import com.brightsoft.dao.platform.PlatformOrderFollowMapper;
import com.brightsoft.dao.platform.PlatformOrderMapper;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.dao.platform.SmsTemplateMapper;
import com.brightsoft.dao.platform.XzqhInfoMapper;
import com.brightsoft.dao.tms.CustomerMapper;
import com.brightsoft.dao.tms.DepartListMapper;
import com.brightsoft.dao.tms.InsteadCollectMoneyMapper;
import com.brightsoft.dao.tms.LadingOrderMapper;
import com.brightsoft.dao.tms.OrderSerialNumberMapper;
import com.brightsoft.dao.tms.OutDepartListMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.dao.tms.ReceiveMoneyOrderRelationMapper;
import com.brightsoft.dao.tms.SignRecordMapper;
import com.brightsoft.dao.tms.TransferDepartListMapper;
import com.brightsoft.dao.tms.WayBillOrderCargoInfoMapper;
import com.brightsoft.dao.tms.WayBillOrderCostInfoMapper;
import com.brightsoft.dao.tms.WayBillOrderFollowMapper;
import com.brightsoft.dao.tms.WayBillOrderMapper;
import com.brightsoft.dao.tms.WayBillOutStoreRecordMapper;
import com.brightsoft.model.Customer;
import com.brightsoft.model.DepartList;
import com.brightsoft.model.InsteadCollectMoney;
import com.brightsoft.model.LadingOrder;
import com.brightsoft.model.OrderSerialNumber;
import com.brightsoft.model.OutDepartList;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformOrder;
import com.brightsoft.model.PlatformOrderFollow;
import com.brightsoft.model.PlatformOrderTracking;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.ReceiveMoneyOrderRelation;
import com.brightsoft.model.SignRecord;
import com.brightsoft.model.SmsTemplate;
import com.brightsoft.model.TransferDepartList;
import com.brightsoft.model.User;
import com.brightsoft.model.WayBillOrder;
import com.brightsoft.model.WayBillOrderCargoInfo;
import com.brightsoft.model.WayBillOrderCostInfo;
import com.brightsoft.model.WayBillOrderFollow;
import com.brightsoft.model.WayBillOutStoreRecord;
import com.brightsoft.service.base.SmsManagerService;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.sms.SmsParams;

/**
 * 运单管理 service
 * @author yangshoubao
 *
 */
@Service
public class WayBillOrderService {

	private static final Logger logger = Logger.getLogger(WayBillOrderService.class);
	
	@Autowired
	private WayBillOrderMapper wayBillOrderMapper;
	
	@Autowired
	private WayBillOrderCargoInfoMapper wayBillOrderCargoInfoMapper;
	
	@Autowired
	private WayBillOrderCostInfoMapper wayBillOrderCostInfoMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	@Autowired
	private OrderSerialNumberMapper orderSerialNumberMapper;
	
	@Autowired
	private DepartListMapper departListMapper;
	
	@Autowired
	private OutDepartListMapper outDepartListMapper;
	
	@Autowired
	private InsteadCollectMoneyMapper insteadCollectMoneyMapper;
	
	@Autowired
	private PlatformOrderMapper platformOrderMapper;
	
	@Autowired
	private LadingOrderMapper ladingOrderMapper;
	
	@Autowired
	private PlatformOrderFollowMapper platformOrderFollowMapper;
	
	@Autowired
	private ReceiveMoneyOrderRelationMapper receiveMoneyOrderRelationMapper;
	
	@Autowired
	private XzqhInfoMapper xzqhInfoMapper;
	
	@Autowired
	private WayBillOutStoreRecordMapper wayBillOutStoreRecordMapper;
	
	@Autowired
	private SignRecordMapper signRecordMapper;
	
	@Autowired
	private WayBillOrderFollowMapper wayBillOrderFollowMapper;
	
	@Autowired
	private TransferDepartListMapper transferDepartListMapper;
	
	@Autowired
	private PlatformUserCompanyMapper platformUserCompanyMapper;
	
	@Autowired
	private SmsTemplateMapper smsTemplateMapper;
	
	@Autowired
	private SmsManagerService smsManagerService;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	public WayBillOrder selectById(Long id){
		return wayBillOrderMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 保存运单信息
	 * @param wayBillOrder
	 * @param wayBillOrderCargo
	 * @param wayBillOrderCostInfo
	 * @return
	 */
	public int saveWayBillOrder(String wayBillOrder, String wayBillOrderCargo, String wayBillOrderCostInfo, User user){
		WayBillOrder order =JSONObject.parseObject(wayBillOrder, WayBillOrder.class);
		List<WayBillOrderCargoInfo> orderCargoInfos = JSONArray.parseArray(wayBillOrderCargo, WayBillOrderCargoInfo.class);
		List<WayBillOrderCostInfo> orderCostInfos = JSONArray.parseArray(wayBillOrderCostInfo, WayBillOrderCostInfo.class);
		
		if(order.getId() == null){// 添加运单
			String wayBillNumber = order.getWayBillNumber();
			if(StringUtils.isEmpty(wayBillNumber)){
				wayBillNumber = generateWayBillNumber(order.getStartOutlets());
				order.setWayBillNumber(wayBillNumber);
			}
			// 保存运单基础数据信息
			order.setStatus(WayBillOrderStatusEnum.NEW_WAY_BILL_ORDER.getValue()); // 设置运单状态
			order.setSignStatus(0);
			if(order.getWayBillOrderTime() == null){
				order.setWayBillOrderTime(new Date());
			}
			if(order.getReceiptSlip() == 1){
				order.setReceiptStatus(0);
			}
			int rows = wayBillOrderMapper.insertSelective(order);
			// 保存发货人/收货人到客户管理中
			saveCustomerInfo(order, user.getCompanyId());
			// 添加运单跟踪记录
			WayBillOrderFollow wayBillOrderFollow = new WayBillOrderFollow();
			wayBillOrderFollow.setWayBillNumber(wayBillNumber);
			wayBillOrderFollow.setStatus(WayBillOrderStatusEnum.NEW_WAY_BILL_ORDER.getValue());
			wayBillOrderFollow.setHandleTime(new Date());
			wayBillOrderFollow.setHandleInfo("新开运单");
			wayBillOrderFollow.setOperatePerson(user.getId());
			String operatePersonName = user.getTrueName();
			if(StringUtils.isEmpty(operatePersonName)){
				operatePersonName = user.getLoginName();
			}
			wayBillOrderFollow.setOperatePersonName(operatePersonName);
			wayBillOrderFollowMapper.insert(wayBillOrderFollow);
			// 保存运单货物信息
			for (WayBillOrderCargoInfo cargoInfo : orderCargoInfos) {
				cargoInfo.setWayBillOrderId(order.getId());
				cargoInfo.setId(null);
			}
			wayBillOrderCargoInfoMapper.batchInsert(orderCargoInfos);
			// 保存运单费用信息
			if(orderCostInfos != null && orderCostInfos.size() > 0){
				for (WayBillOrderCostInfo costInfo : orderCostInfos) {
					costInfo.setWayBillOrderId(order.getId());
					costInfo.setId(null);
				}
				wayBillOrderCostInfoMapper.batchInsert(orderCostInfos);
			}
			
			String orderNumber = order.getOrderNumber();
			if(StringUtils.isNotEmpty(orderNumber)){
				// 为该运单号 对应的订单 添加运单号
				// 修改 订单状态为 货物入库
				PlatformOrder platformOrder = new PlatformOrder();
				platformOrder.setOrderNumber(orderNumber);
				platformOrder.setWayBillNumber(wayBillNumber);
				platformOrder.setState(OrderStatusEnum.CARGO_STORED.getValue());
				platformOrderMapper.updateByOrderNumberSelective(platformOrder);
				
				// 添加 订单跟踪记录
				PlatformOrderFollow platformOrderFollow = new PlatformOrderFollow();
				platformOrderFollow.setOrderNumber(orderNumber);
				platformOrderFollow.setWayBillNumber(wayBillNumber);
				platformOrderFollow.setStatus(OrderStatusEnum.CARGO_STORED.getValue());
				platformOrderFollow.setHandleTime(new Date());
				platformOrderFollow.setHandleInfo("货物入库");
				platformOrderFollow.setOperatePerson(user.getTrueName());
				platformOrderFollowMapper.insert(platformOrderFollow);
			}
			// 修改提货单状态 为 已开单入库 
			LadingOrder ladingOrder = new LadingOrder();
			ladingOrder.setWayBillNumber(wayBillNumber);
			ladingOrder.setStatus(LadingOrderStatusEnum.ALREADY_STORAGE.getValue());
			ladingOrderMapper.updateByWayBillNumberSelective(ladingOrder);
			
			// 发送短信
			SmsParams smsParams = new SmsParams();
			smsParams.setWaybillNumber(wayBillNumber);
			smsParams.setOrderNumber(orderNumber);
			smsParams.setOutletsName(outletsInfoMapper.selectById(user.getOutletsId()).getName());
			smsParams.setCompany(platformUserCompanyMapper.selectByOutletsId(user.getOutletsId()).getCompanyName());
			smsParams.setOrderStatus("货物入库");
			sendMessage(order.getConsignorMobile(), order.getConsigneeMobile(), SmsTemplateEnum.IN_STORAGE, smsParams);
			
			return rows;
		}else{// 修改运单
			int rows = wayBillOrderMapper.updateByPrimaryKeySelective(order);
			
			// 重新保存运单货物信息
			wayBillOrderCargoInfoMapper.deleteByWayBillOrderId(order.getId());
			for (WayBillOrderCargoInfo orderCargoInfo : orderCargoInfos) {
				orderCargoInfo.setWayBillOrderId(order.getId());
				orderCargoInfo.setId(null);
			}
			wayBillOrderCargoInfoMapper.batchInsert(orderCargoInfos);
			
			// 重新保存运单费用信息
			wayBillOrderCostInfoMapper.deleteByWayBillOrderId(order.getId());
			if(orderCostInfos != null && orderCostInfos.size() > 0){
				for (WayBillOrderCostInfo costInfo : orderCostInfos) {
					costInfo.setWayBillOrderId(order.getId());
					costInfo.setId(null);
				}
				wayBillOrderCostInfoMapper.batchInsert(orderCostInfos);
			}
			return rows;
		}
	}
	
	/**
	 * 生成 运单号
	 * @param lineId
	 * @return
	 */
	private String generateWayBillNumber(Long outletsId){
		// 获取公司组织代码    网点编号
		String orderNumber = null;
	 	OutletsInfo outletsInfo = outletsInfoMapper.selectOutletsAndCompanyCodeById(outletsId);
	 	String companyCode = outletsInfo.getCompanyCode();
		String outletsNumber = outletsInfo.getOutletsNumber();
		String dateStr = DateTools.getCurrentDateStr("yyMMdd");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("numberType", OrderNumberTypeEnum.WAY_BILL_NUMBER.getValue());
		params.put("companyCode", companyCode);
		params.put("outletsNumber", outletsNumber);
		
		OrderSerialNumber serialNumber = new OrderSerialNumber();
		serialNumber.setNumberType(OrderNumberTypeEnum.WAY_BILL_NUMBER.getValue());
		serialNumber.setCompanyCode(companyCode);
		serialNumber.setOutletsNumber(outletsNumber);
		serialNumber.setDateStr(dateStr);
		
		OrderSerialNumber orderSerialNumber = orderSerialNumberMapper.selectByParams(params);
		if(orderSerialNumber == null){
			String newNumber = "001";
			orderNumber = companyCode + outletsNumber + dateStr + newNumber;
			serialNumber.setSerialNumber(newNumber);
			orderSerialNumberMapper.insertSelective(serialNumber);
		}else{
			String number = orderSerialNumber.getSerialNumber();
			String newNumber = (Integer.parseInt(number) + 1) + "";
			if(newNumber.length() == 1){
				newNumber = "00" + newNumber;
			}else if(newNumber.length() == 2){
				newNumber = "0" + newNumber;
			}
			orderNumber = companyCode + outletsNumber + dateStr + newNumber;
			serialNumber.setSerialNumber(newNumber);
			serialNumber.setId(orderSerialNumber.getId());
			orderSerialNumberMapper.updateByPrimaryKeySelective(serialNumber);
		}
		return orderNumber;
	}
	
	/**
	 * 保存 客户信息
	 * @param order
	 */
	public void saveCustomerInfo(WayBillOrder order, Long companyId){
		// 保存 发货人信息
		Customer consignorCustomer = customerMapper.selectByPhoneAndOutletsId(order.getConsignorMobile(), order.getStartOutlets());
		if(consignorCustomer == null){
			Customer consignor = new Customer();
			consignor.setCustomerName(order.getConsignor());
			consignor.setPhone(order.getConsignorMobile());
			consignor.setAddress(order.getConsignorAddress());
			consignor.setCompanyName(order.getConsignorCompany());
			consignor.setCreateTime(new Date());
			consignor.setOutletsId(order.getStartOutlets());
			consignor.setCompanyId(companyId);
			consignor.setStatus(1);
			customerMapper.insertSelective(consignor);
		}
		// 保存收货人信息
		Customer consigneeCustomer = customerMapper.selectByPhoneAndOutletsId(order.getConsigneeMobile(), order.getStartOutlets());
		if(consigneeCustomer == null){
			Customer consignee = new Customer();
			consignee.setCustomerName(order.getConsignee());
			consignee.setPhone(order.getConsigneeMobile());
			consignee.setAddress(order.getConsigneeAddress());
			consignee.setCompanyName(order.getConsigneeCompany());
			consignee.setOutletsId(order.getStartOutlets());
			consignee.setCompanyId(companyId);
			consignee.setStatus(1);
			customerMapper.insertSelective(consignee);
		}
	}
	
	/**
	 * 发送短信
	 * @param orderNumber
	 * @param smsTemplateEnum
	 */
	private void sendMessage(String consignorMobile, String consigneeMobile, SmsTemplateEnum smsTemplateEnum, SmsParams smsParams){
		SmsTemplate smsTemplate = smsTemplateMapper.getSmsTemplateByAbbr(smsTemplateEnum.getValue());
		if(smsTemplate == null){
			logger.warn("【获取短信模板失败】");
			return;
		}
		if(smsTemplate.getIsEnabled() != null && smsTemplate.getIsEnabled() != 1){
			logger.warn("【短信模板未启用】");
			return;
		}
		if(smsTemplate.getSendConsignor() != null && smsTemplate.getSendConsignor() == 1){
			// 发送短信给发货人
			boolean flag = false;
			try {
				flag = smsManagerService.sendSms(consignorMobile, smsParams, smsTemplateEnum);
			} catch (Exception e) {
				logger.error("【发送短信给发货人失败】", e);
				return;
			}
			if(flag){
				logger.info("【发送短信给发货人成功】");
			}
		}
		if(smsTemplate.getSendConsignee() != null && smsTemplate.getSendConsignee() == 1){
			// 发送短信给收货人
			boolean flag = false;
			try {
				flag = smsManagerService.sendSms(consigneeMobile, smsParams, smsTemplateEnum);
			} catch (Exception e) {
				logger.error("【发送短信给收货人失败】", e);
				return;
			}
			if(flag){
				logger.info("【发送短信给收货人成功】");
			}
		}
	}
	
	/**
	 * 验证运单是否可编辑
	 * @param wayBillNumber
	 * @return
	 */
	public boolean validWayBillIsEdit(String wayBillNumber){
		LadingOrder ladingOrder = ladingOrderMapper.selectByWayBillNumber(wayBillNumber);
		WayBillOrder wayBillOrder = wayBillOrderMapper.selectByWayBillNumber(wayBillNumber);
		if(ladingOrder == null && wayBillOrder != null && wayBillOrder.getStatus() == 0 && StringUtils.isEmpty(wayBillOrder.getOrderNumber())){
			return true;
		}
		return false;
	}
	
	/**
	 *  查询运单 列表
	 * @param params
	 * @return
	 */
	/*public Result selectOrderByParams(String status, String startTime, String endTime, String condition){
		Map<String, Object> params = new HashMap<String, Object>();
		List<Integer> statusList = new ArrayList<Integer>();
		if(StringUtils.isNotEmpty(status)){
			statusList.add(Integer.parseInt(status));
			params.put("statusList", statusList);
		}
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("condition", condition);
		
		List<WayBillOrder> billOrders = wayBillOrderMapper.selectByParams(params);
		for (WayBillOrder wayBillOrder : billOrders) {
			WayBillOrderStatusEnum[] enums = WayBillOrderStatusEnum.values();
			for (WayBillOrderStatusEnum wayBillOrderStatusEnum : enums) {
				if(wayBillOrder.getStatus() == wayBillOrderStatusEnum.getValue()){
					wayBillOrder.setStatusName(wayBillOrderStatusEnum.getName());
				}
			}
		}
		int count = wayBillOrderMapper.selectByParamsCount(params);
		
		Result result = new Result();
		result.setResults(count);
		result.setRows(billOrders);
		
		return result;
	}*/
	
	/**
	 * 根据收货方式 查询运单 列表
	 * @param params
	 * @return
	 */
	/*public Result selectWayBillByReceiveType(String condition, ReceiveTypeEnum typeEnum){
		
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(WayBillOrderStatusEnum.ALREADY_REACH_CARGO.getValue());
		statusList.add(WayBillOrderStatusEnum.PART_REACH_CARGO.getValue());
		statusList.add(WayBillOrderStatusEnum.ALREADY_DISCHARGE.getValue());
		statusList.add(WayBillOrderStatusEnum.PART_DISCHARGE.getValue());
		statusList.add(WayBillOrderStatusEnum.ALREADY_OUT_SOURCE.getValue());
		statusList.add(WayBillOrderStatusEnum.RECEIVED.getValue());
		statusList.add(WayBillOrderStatusEnum.DISCARDED.getValue());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("condition", condition);
		params.put("receiveType", typeEnum.getValue()+"");
		params.put("statusList", statusList);
		
		List<WayBillOrder> billOrders = wayBillOrderMapper.selectByParams(params);
		for (WayBillOrder wayBillOrder : billOrders) {
			WayBillOrderStatusEnum[] enums = WayBillOrderStatusEnum.values();
			for (WayBillOrderStatusEnum wayBillOrderStatusEnum : enums) {
				if(wayBillOrder.getStatus() == wayBillOrderStatusEnum.getValue()){
					wayBillOrder.setStatusName(wayBillOrderStatusEnum.getName());
				}
			}
		}
		int count = wayBillOrderMapper.selectByParamsCount(params);
		
		Result result = new Result();
		result.setResults(count);
		result.setRows(billOrders);
		
		return result;
	}*/
	
	/**
	 * 查询自提/送货上门运单
	 * @param condition
	 * @return
	 */
	public Result selectByReceiveTypeAndCondition(WayBillSearchParams params, ReceiveTypeEnum typeEnum){
		
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(WayBillOrderStatusEnum.ALREADY_REACH_CARGO.getValue());
		statusList.add(WayBillOrderStatusEnum.PART_REACH_CARGO.getValue());
		statusList.add(WayBillOrderStatusEnum.ALREADY_DISCHARGE.getValue());
		statusList.add(WayBillOrderStatusEnum.PART_DISCHARGE.getValue());
		statusList.add(WayBillOrderStatusEnum.ALREADY_OUT_SOURCE.getValue());
		statusList.add(WayBillOrderStatusEnum.RECEIVED.getValue());
		statusList.add(WayBillOrderStatusEnum.DISCARDED.getValue());
		
		params.setStatusList(statusList);
		if(typeEnum != null){
			params.setReceiveType(typeEnum.getValue() + "");
		}
		
		List<WayBillOrder> billOrders = wayBillOrderMapper.selectByReceiveTypeAndCondition(params);
		for (WayBillOrder wayBillOrder : billOrders) {
			WayBillOrderStatusEnum[] enums = WayBillOrderStatusEnum.values();
			for (WayBillOrderStatusEnum wayBillOrderStatusEnum : enums) {
				if(wayBillOrder.getStatus() == wayBillOrderStatusEnum.getValue()){
					wayBillOrder.setStatusName(wayBillOrderStatusEnum.getName());
				}
			}
			// 设置付款方式名称
			PayModeEnum[] payModeEnums = PayModeEnum.values();
			for (PayModeEnum payModeEnum : payModeEnums) {
				if(Integer.parseInt(wayBillOrder.getPayMethod()) == payModeEnum.getValue()){
					wayBillOrder.setPayMethodName(payModeEnum.getName());
				}
			}
			// 设置 异常状态名称
			ExceptionStatusEnum[] exceptionStatusEnums = ExceptionStatusEnum.values();
			for (ExceptionStatusEnum exceptionStatusEnum : exceptionStatusEnums) {
				if(wayBillOrder.getExceptionStatus() == exceptionStatusEnum.getValue()){
					wayBillOrder.setExceptionStatusName(exceptionStatusEnum.getName());
					continue;
				}
			}
			Integer exceptionStatus = wayBillOrder.getExceptionStatus();
			if(exceptionStatus == ExceptionStatusEnum.REGISTER.getValue()){
				wayBillOrder.setExceptionRegister(true);
			}else if(exceptionStatus == ExceptionStatusEnum.HANDLER.getValue()){
				wayBillOrder.setExceptionHandler(true);
			}
			// 设置货物信息
			List<WayBillOrderCargoInfo> wayBillOrderCargoInfos = wayBillOrderCargoInfoMapper.selectByWayBillOrderId(wayBillOrder.getId());
			if(wayBillOrderCargoInfos != null && wayBillOrderCargoInfos.size() > 0){
				wayBillOrder.setCargoName(wayBillOrderCargoInfos.get(0).getName());
				wayBillOrder.setCargoNumber(wayBillOrderCargoInfos.get(0).getNumber());
				wayBillOrder.setCargoSetNumber(wayBillOrderCargoInfos.get(0).getSetNumber());
			}
			// 为运单设置 是否出库
			WayBillOutStoreRecord wayBillOutStoreRecord = wayBillOutStoreRecordMapper.selectByWayBillOrderId(wayBillOrder.getId());
			if(wayBillOutStoreRecord != null){
				wayBillOrder.setIsOutStore(1);
				wayBillOrder.setOutTime(wayBillOutStoreRecord.getOperateTime());
			}else{
				wayBillOrder.setIsOutStore(0);
			}
			// 设置 到达时间
			DepartList departList = departListMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
			if(departList != null){
				wayBillOrder.setEndTime(departList.getEndTime());
			}
			// 设置 签收时间
			SignRecord signRecord = signRecordMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
			if(signRecord != null){
				wayBillOrder.setSignTime(signRecord.getSignTime());
				wayBillOrder.setSignPerson(signRecord.getSignPerson());
				wayBillOrder.setSignPersonPhone(signRecord.getPhone());
			}
			// 设置 目的地
			String targetProviceName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetProvince()).getName();
			String targetCityName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCity()).getName();
			String targetCountyName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCounty()).getName();
			wayBillOrder.setTargetAddress(targetProviceName + " " + targetCityName + " " + targetCountyName);
			// 设置 操作人
			List<WayBillOrderFollow> wayBillOrderFollows = wayBillOrderFollowMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
			if(wayBillOrderFollows != null && wayBillOrderFollows.size() > 0){
				wayBillOrder.setOperatePerson(wayBillOrderFollows.get(0).getOperatePerson());
				wayBillOrder.setOperatePersonName(wayBillOrderFollows.get(0).getOperatePersonName());
			}
		}
		int count = wayBillOrderMapper.selectByReceiveTypeAndConditionCount(params);
		
		Result result = new Result();
		result.setResults(count);
		result.setRows(billOrders);
		
		return result;
	}
	
	/**
	 * 查询中转运单
	 * @param condition
	 * @param outletsId
	 * @return
	 */
	public Result selectTransferWayBillByCondition(WayBillSearchParams params){
		// 根据运单号、运单状态 查询对应的运单
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(WayBillOrderStatusEnum.ALREADY_REACH_CARGO.getValue());
		statusList.add(WayBillOrderStatusEnum.PART_REACH_CARGO.getValue());
		statusList.add(WayBillOrderStatusEnum.ALREADY_DISCHARGE.getValue());
		statusList.add(WayBillOrderStatusEnum.PART_DISCHARGE.getValue());
		params.setStatusList(statusList);
		List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectTransferWayBillByParams(params);
		for (WayBillOrder wayBillOrder : wayBillOrders) {
			// 设置付款方式名称
			PayModeEnum[] payModeEnums = PayModeEnum.values();
			for (PayModeEnum payModeEnum : payModeEnums) {
				if(Integer.parseInt(wayBillOrder.getPayMethod()) == payModeEnum.getValue()){
					wayBillOrder.setPayMethodName(payModeEnum.getName());
				}
			}
			// 设置 异常状态名称
			ExceptionStatusEnum[] exceptionStatusEnums = ExceptionStatusEnum.values();
			for (ExceptionStatusEnum exceptionStatusEnum : exceptionStatusEnums) {
				if(wayBillOrder.getExceptionStatus() == exceptionStatusEnum.getValue()){
					wayBillOrder.setExceptionStatusName(exceptionStatusEnum.getName());
					continue;
				}
			}
			Integer exceptionStatus = wayBillOrder.getExceptionStatus();
			if(exceptionStatus == ExceptionStatusEnum.REGISTER.getValue()){
				wayBillOrder.setExceptionRegister(true);
			}else if(exceptionStatus == ExceptionStatusEnum.HANDLER.getValue()){
				wayBillOrder.setExceptionHandler(true);
			}
			// 设置货物信息
			List<WayBillOrderCargoInfo> wayBillOrderCargoInfos = wayBillOrderCargoInfoMapper.selectByWayBillOrderId(wayBillOrder.getId());
			if(wayBillOrderCargoInfos != null && wayBillOrderCargoInfos.size() > 0){
				wayBillOrder.setCargoName(wayBillOrderCargoInfos.get(0).getName());
				wayBillOrder.setCargoNumber(wayBillOrderCargoInfos.get(0).getNumber());
				wayBillOrder.setCargoSetNumber(wayBillOrderCargoInfos.get(0).getSetNumber());
			}
			// 为运单设置 是否出库
			WayBillOutStoreRecord wayBillOutStoreRecord = wayBillOutStoreRecordMapper.selectByWayBillOrderId(wayBillOrder.getId());
			if(wayBillOutStoreRecord != null){
				wayBillOrder.setIsOutStore(1);
				wayBillOrder.setOutTime(wayBillOutStoreRecord.getOperateTime());
			}else{
				wayBillOrder.setIsOutStore(0);
			}
			// 设置 到达时间
			DepartList departList = departListMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
			if(departList != null){
				wayBillOrder.setEndTime(departList.getEndTime());
			}
			// 设置 签收时间
			SignRecord signRecord = signRecordMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
			if(signRecord != null){
				wayBillOrder.setSignTime(signRecord.getSignTime());
			}
			// 设置 目的地
			String targetProviceName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetProvince()).getName();
			String targetCityName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCity()).getName();
			String targetCountyName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCounty()).getName();
			wayBillOrder.setTargetAddress(targetProviceName + " " + targetCityName + " " + targetCountyName);
			// 设置 操作人
			List<WayBillOrderFollow> wayBillOrderFollows = wayBillOrderFollowMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
			if(wayBillOrderFollows != null && wayBillOrderFollows.size() > 0){
				wayBillOrder.setOperatePerson(wayBillOrderFollows.get(0).getOperatePerson());
				wayBillOrder.setOperatePersonName(wayBillOrderFollows.get(0).getOperatePersonName());
			}
			// 设置中转 出库时间
			TransferDepartList transferDepartList = transferDepartListMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
			if(transferDepartList != null){
				wayBillOrder.setOutTime(transferDepartList.getTransferTime());
			}
		}
		int count = wayBillOrderMapper.selectTransferWayBillByParamsCount(params);
		Result result = new Result();
		result.setResults(count);
		result.setRows(wayBillOrders);
		return result;
	}
	
	/**
	 * 查询 签收运单列表
	 * @param params
	 * @return
	 */
	public Result selectSignWayBillList(WayBillSearchParams params){
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(WayBillOrderStatusEnum.ALREADY_REACH_CARGO.getValue());
		statusList.add(WayBillOrderStatusEnum.PART_REACH_CARGO.getValue());
		statusList.add(WayBillOrderStatusEnum.ALREADY_DISCHARGE.getValue());
		statusList.add(WayBillOrderStatusEnum.PART_DISCHARGE.getValue());
		statusList.add(WayBillOrderStatusEnum.ALREADY_OUT_SOURCE.getValue());
		statusList.add(WayBillOrderStatusEnum.RECEIVED.getValue());
		statusList.add(WayBillOrderStatusEnum.DISCARDED.getValue());
		params.setStatusList(statusList);
		List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectSignWayBillList(params);
		for (WayBillOrder wayBillOrder : wayBillOrders) {
			WayBillOrderStatusEnum[] enums = WayBillOrderStatusEnum.values();
			for (WayBillOrderStatusEnum wayBillOrderStatusEnum : enums) {
				if(wayBillOrder.getStatus() == wayBillOrderStatusEnum.getValue()){
					wayBillOrder.setStatusName(wayBillOrderStatusEnum.getName());
				}
			}
			// 设置付款方式名称
			PayModeEnum[] payModeEnums = PayModeEnum.values();
			for (PayModeEnum payModeEnum : payModeEnums) {
				if(Integer.parseInt(wayBillOrder.getPayMethod()) == payModeEnum.getValue()){
					wayBillOrder.setPayMethodName(payModeEnum.getName());
				}
			}
			// 设置 异常状态名称
			ExceptionStatusEnum[] exceptionStatusEnums = ExceptionStatusEnum.values();
			for (ExceptionStatusEnum exceptionStatusEnum : exceptionStatusEnums) {
				if(wayBillOrder.getExceptionStatus() == exceptionStatusEnum.getValue()){
					wayBillOrder.setExceptionStatusName(exceptionStatusEnum.getName());
					continue;
				}
			}
			Integer exceptionStatus = wayBillOrder.getExceptionStatus();
			if(exceptionStatus == ExceptionStatusEnum.REGISTER.getValue()){
				wayBillOrder.setExceptionRegister(true);
			}else if(exceptionStatus == ExceptionStatusEnum.HANDLER.getValue()){
				wayBillOrder.setExceptionHandler(true);
			}
			// 设置货物信息
			List<WayBillOrderCargoInfo> wayBillOrderCargoInfos = wayBillOrderCargoInfoMapper.selectByWayBillOrderId(wayBillOrder.getId());
			if(wayBillOrderCargoInfos != null && wayBillOrderCargoInfos.size() > 0){
				wayBillOrder.setCargoName(wayBillOrderCargoInfos.get(0).getName());
				wayBillOrder.setCargoNumber(wayBillOrderCargoInfos.get(0).getNumber());
			}
			// 为运单设置 是否出库
			WayBillOutStoreRecord wayBillOutStoreRecord = wayBillOutStoreRecordMapper.selectByWayBillOrderId(wayBillOrder.getId());
			if(wayBillOutStoreRecord != null){
				wayBillOrder.setIsOutStore(1);
				wayBillOrder.setOutTime(wayBillOutStoreRecord.getOperateTime());
			}else{
				wayBillOrder.setIsOutStore(0);
			}
			// 设置 签收时间
			SignRecord signRecord = signRecordMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
			if(signRecord != null){
				wayBillOrder.setSignTime(signRecord.getSignTime());
			}
			// 设置 目的地
			String targetProviceName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetProvince()).getName();
			String targetCityName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCity()).getName();
			String targetCountyName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCounty()).getName();
			wayBillOrder.setTargetAddress(targetProviceName + " " + targetCityName + " " + targetCountyName);
			// 设置 操作人
			List<WayBillOrderFollow> wayBillOrderFollows = wayBillOrderFollowMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
			if(wayBillOrderFollows != null && wayBillOrderFollows.size() > 0){
				wayBillOrder.setOperatePerson(wayBillOrderFollows.get(0).getOperatePerson());
				wayBillOrder.setOperatePersonName(wayBillOrderFollows.get(0).getOperatePersonName());
			}
		}
		int count = wayBillOrderMapper.selectSignWayBillListCount(params);
		Result result = new Result();
		result.setRows(wayBillOrders);
		result.setResults(count);
		return result;
	}
	
	/**
	 * 根据多个 运单号 查询运单信息
	 * @param wayBillNumbers
	 * @return
	 */
	public List<WayBillOrder> selectByWayBillNumbers(String wayBillNumbers){
		String[] wayBillNumberArr = wayBillNumbers.split(",");
		
		List<String> wayBillNumberList = new ArrayList<String>();
		for (String wayBillNumber : wayBillNumberArr) {
			wayBillNumberList.add(wayBillNumber);
		}
		
		List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectByWayBillNumbers(wayBillNumberList);
		
		for (WayBillOrder wayBillOrder : wayBillOrders) {
			WayBillOrderStatusEnum[] enums = WayBillOrderStatusEnum.values();
			for (WayBillOrderStatusEnum wayBillOrderStatusEnum : enums) {
				if(wayBillOrder.getStatus() == wayBillOrderStatusEnum.getValue()){
					wayBillOrder.setStatusName(wayBillOrderStatusEnum.getName());
				}
			}
			// 设置 目的地
			String targetProviceName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetProvince()).getName();
			String targetCityName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCity()).getName();
			String targetCountyName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCounty()).getName();
			wayBillOrder.setTargetAddress(targetProviceName + " " + targetCityName + " " + targetCountyName);
			
		}
		return wayBillOrders;
	}
	
	/**
	 * 修改运单状态
	 * @param wayBillNumber
	 * @return
	 */
	/*public int updateStatusByWayBillNumber(String wayBillNumber, WayBillOrderStatusEnum statusEnum){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("wayBillNumber", wayBillNumber);
		params.put("status", statusEnum.getValue());
		return wayBillOrderMapper.updateStatusByWayBillNumber(params);
	}*/
	
	/**
	 * 保存 作废运单
	 * @param wayBillNumbers
	 * @return
	 */
	public int saveAbolishWayBill(String wayBillNumbers, User user){
		String[] wayBillNumberArr = wayBillNumbers.split(",");
		int count = 0;
		for (String wayBillNumber : wayBillNumberArr) {
			// 作废运单
			WayBillOrder wayBillOrder = new WayBillOrder();
			wayBillOrder.setWayBillNumber(wayBillNumber);
			wayBillOrder.setStatus(WayBillOrderStatusEnum.DISCARDED.getValue());
			count += wayBillOrderMapper.updateByWayBillNumberSelective(wayBillOrder);
			
			// 添加运单跟踪记录
			WayBillOrderFollow wayBillOrderFollow = new WayBillOrderFollow();
			wayBillOrderFollow.setWayBillNumber(wayBillNumber);
			wayBillOrderFollow.setStatus(WayBillOrderStatusEnum.DISCARDED.getValue());
			wayBillOrderFollow.setHandleTime(new Date());
			wayBillOrderFollow.setHandleInfo("作废运单");
			wayBillOrderFollow.setOperatePerson(user.getId());
			String operatePersonName = user.getTrueName();
			if(StringUtils.isEmpty(operatePersonName)){
				operatePersonName = user.getLoginName();
			}
			wayBillOrderFollow.setOperatePersonName(operatePersonName);
			wayBillOrderFollowMapper.insert(wayBillOrderFollow);
			
			// 作废提货单
			LadingOrder ladingOrder = new LadingOrder();
			ladingOrder.setWayBillNumber(wayBillNumber);
			ladingOrder.setStatus(LadingOrderStatusEnum.ALREADY_ABOLISH.getValue());
			ladingOrderMapper.updateByWayBillNumberSelective(ladingOrder);
			// 作废订单
			WayBillOrder wayBillOrderTemp = wayBillOrderMapper.selectByWayBillNumber(wayBillNumber);
			String orderNumber = wayBillOrderTemp.getOrderNumber();
			if(StringUtils.isNotEmpty(orderNumber)){
				PlatformOrder platformOrder = new PlatformOrder();
				platformOrder.setOrderNumber(orderNumber);
				platformOrder.setState(OrderStatusEnum.DISCARDED.getValue());
				platformOrderMapper.updateByOrderNumberSelective(platformOrder);
				// 添加订单跟踪记录
				PlatformOrderFollow orderFollow = new PlatformOrderFollow();
				orderFollow.setOrderNumber(orderNumber);
				orderFollow.setWayBillNumber(wayBillNumber);
				orderFollow.setStatus(OrderStatusEnum.DISCARDED.getValue());
				orderFollow.setHandleTime(new Date());
				orderFollow.setHandleInfo("作废运单");
				orderFollow.setOperatePerson(user.getTrueName());
				platformOrderFollowMapper.insert(orderFollow);
			}
			
		}
		return count;
	}
	
	/**
	 * 查询配载运单
	 * @param outletsId 网点Id
	 * @return
	 */
	public List<WayBillOrder> selectStowageWayBill(Long outletsId, String targetOutletsParam){
		Set<String> wayBillNumbers = new HashSet<String>();
		// 查询状态为 配载中的发车单
		List<DepartList> departs = departListMapper.selectByOutletsAndStatus(outletsId, DepartListStatusEnum.LADING.getValue());
		for (DepartList departList : departs) {
			String[] wayBillNumberArr = departList.getWayBillNumbers().split(",");
			for (String wayBillNumber : wayBillNumberArr) {
				wayBillNumbers.add(wayBillNumber);
			}
		}
		// 查询状态为配载中的外包发车单
		List<OutDepartList> outDepartLists = outDepartListMapper.selectByOutletsAndStatus(outletsId, OutDepartListStatusEnum.LADING.getValue());
		for (OutDepartList outDepartList : outDepartLists) {
			String[] wayBillNumberArr = outDepartList.getWayBillNumbers().split(",");
			for (String wayBillNumber : wayBillNumberArr) {
				wayBillNumbers.add(wayBillNumber);
			}
		}
		
		Long targetOutlets = StringUtils.isNotEmpty(targetOutletsParam) ? Long.parseLong(targetOutletsParam) : null;
		List<WayBillOrder> wayBills = wayBillOrderMapper.selectStowageWayBill(wayBillNumbers, WayBillOrderStatusEnum.NEW_WAY_BILL_ORDER.getValue(), outletsId, targetOutlets);
		for (WayBillOrder wayBillOrder : wayBills) {
			WayBillOrderStatusEnum[] enums = WayBillOrderStatusEnum.values();
			for (WayBillOrderStatusEnum wayBillOrderStatusEnum : enums) {
				if(wayBillOrder.getStatus() == wayBillOrderStatusEnum.getValue()){
					wayBillOrder.setStatusName(wayBillOrderStatusEnum.getName());
				}
			}
			// 设置付款方式名称
			PayModeEnum[] payModeEnums = PayModeEnum.values();
			for (PayModeEnum payModeEnum : payModeEnums) {
				if(Integer.parseInt(wayBillOrder.getPayMethod()) == payModeEnum.getValue()){
					wayBillOrder.setPayMethodName(payModeEnum.getName());
				}
			}
			// 设置货物信息
			List<WayBillOrderCargoInfo> wayBillOrderCargoInfos = wayBillOrderCargoInfoMapper.selectByWayBillOrderId(wayBillOrder.getId());
			if(wayBillOrderCargoInfos != null && wayBillOrderCargoInfos.size() > 0){
				wayBillOrder.setCargoName(wayBillOrderCargoInfos.get(0).getName());
				wayBillOrder.setCargoNumber(wayBillOrderCargoInfos.get(0).getNumber());
				wayBillOrder.setCargoSetNumber(wayBillOrderCargoInfos.get(0).getSetNumber());
			}
			// 设置 异常状态名称
			ExceptionStatusEnum[] exceptionStatusEnums = ExceptionStatusEnum.values();
			for (ExceptionStatusEnum exceptionStatusEnum : exceptionStatusEnums) {
				if(wayBillOrder.getExceptionStatus() == exceptionStatusEnum.getValue()){
					wayBillOrder.setExceptionStatusName(exceptionStatusEnum.getName());
					continue;
				}
			}
			// 设置 目的地
			String targetProviceName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetProvince()).getName();
			String targetCityName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCity()).getName();
			String targetCountyName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCounty()).getName();
			wayBillOrder.setTargetAddress(targetProviceName + " " + targetCityName + " " + targetCountyName);
			
			// 设置 操作人
			List<WayBillOrderFollow> wayBillOrderFollows = wayBillOrderFollowMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
			if(wayBillOrderFollows != null && wayBillOrderFollows.size() > 0){
				wayBillOrder.setOperatePerson(wayBillOrderFollows.get(0).getOperatePerson());
				wayBillOrder.setOperatePersonName(wayBillOrderFollows.get(0).getOperatePersonName());
			}
		}
		return wayBills;
	}
	
	/**
	 * 根据发车单号查询运单信息
	 * @param departNumber 发车单号
	 * @return
	 */
	public List<WayBillOrder> selectByDepartNumber(String departNumber){
		DepartList departList = departListMapper.selectByDepartNumber(departNumber);
		List<String> wayBillNumbers = new ArrayList<String>();
		String[] wayBillNumberArr = departList.getWayBillNumbers().split(",");
		for (String wayBillNumber : wayBillNumberArr) {
			wayBillNumbers.add(wayBillNumber);
		}
		
		List<WayBillOrder> wayBills = wayBillOrderMapper.selectByWayBillNumbers(wayBillNumbers);
		for (WayBillOrder wayBillOrder : wayBills) {
			WayBillOrderStatusEnum[] enums = WayBillOrderStatusEnum.values();
			for (WayBillOrderStatusEnum wayBillOrderStatusEnum : enums) {
				if(wayBillOrder.getStatus() == wayBillOrderStatusEnum.getValue()){
					wayBillOrder.setStatusName(wayBillOrderStatusEnum.getName());
				}
			}
			PayModeEnum[] payModeEnums = PayModeEnum.values();
			for (PayModeEnum payModeEnum : payModeEnums) {
				if(Integer.parseInt(wayBillOrder.getPayMethod()) == payModeEnum.getValue()){
					wayBillOrder.setPayMethodName(payModeEnum.getName());
				}
			}
			// 设置货物信息
			List<WayBillOrderCargoInfo> wayBillOrderCargoInfos = wayBillOrderCargoInfoMapper.selectByWayBillOrderId(wayBillOrder.getId());
			if(wayBillOrderCargoInfos != null && wayBillOrderCargoInfos.size() > 0){
				wayBillOrder.setCargoBrand(wayBillOrderCargoInfos.get(0).getBrand());
				wayBillOrder.setCargoName(wayBillOrderCargoInfos.get(0).getName());
				wayBillOrder.setCargoNumber(wayBillOrderCargoInfos.get(0).getNumber());
				wayBillOrder.setCargoSetNumber(wayBillOrderCargoInfos.get(0).getSetNumber());
			}
			// 设置 异常状态名称
			ExceptionStatusEnum[] exceptionStatusEnums = ExceptionStatusEnum.values();
			for (ExceptionStatusEnum exceptionStatusEnum : exceptionStatusEnums) {
				if(wayBillOrder.getExceptionStatus() == exceptionStatusEnum.getValue()){
					wayBillOrder.setExceptionStatusName(exceptionStatusEnum.getName());
					continue;
				}
			}
			// 设置 目的地
			String targetProviceName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetProvince()).getName();
			String targetCityName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCity()).getName();
			String targetCountyName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCounty()).getName();
			wayBillOrder.setTargetAddress(targetProviceName + " " + targetCityName + " " + targetCountyName);
			
			// 设置 操作人
			List<WayBillOrderFollow> wayBillOrderFollows = wayBillOrderFollowMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
			if(wayBillOrderFollows != null && wayBillOrderFollows.size() > 0){
				wayBillOrder.setOperatePerson(wayBillOrderFollows.get(0).getOperatePerson());
				wayBillOrder.setOperatePersonName(wayBillOrderFollows.get(0).getOperatePersonName());
			}
		}
		return wayBills;
	}
	
	/**
	 * 根据外包发车单号查询运单信息
	 * @param outDepartNumber 外包发车单号
	 * @return
	 */
	public List<WayBillOrder> selectByOutDepartNumber(String outDepartNumber){
		OutDepartList outDepartList = outDepartListMapper.selectByOutDepartNumber(outDepartNumber);
		List<String> wayBillNumbers = new ArrayList<String>();
		String[] wayBillNumberArr = outDepartList.getWayBillNumbers().split(",");
		for (String wayBillNumber : wayBillNumberArr) {
			wayBillNumbers.add(wayBillNumber);
		}
		
		List<WayBillOrder> wayBills = wayBillOrderMapper.selectByWayBillNumbers(wayBillNumbers);
		for (WayBillOrder wayBillOrder : wayBills) {
			WayBillOrderStatusEnum[] enums = WayBillOrderStatusEnum.values();
			for (WayBillOrderStatusEnum wayBillOrderStatusEnum : enums) {
				if(wayBillOrder.getStatus() == wayBillOrderStatusEnum.getValue()){
					wayBillOrder.setStatusName(wayBillOrderStatusEnum.getName());
				}
			}
			// 设置 目的地
			String targetProviceName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetProvince()).getName();
			String targetCityName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCity()).getName();
			String targetCountyName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCounty()).getName();
			wayBillOrder.setTargetAddress(targetProviceName + " " + targetCityName + " " + targetCountyName);
			
		}
		return wayBills;
	}
	
	/**
	 * 更改配送方式
	 * @param receiveType
	 * @param wayBillNumbers
	 * @return
	 */
	public int updateReceiveType(String receiveType, String wayBillNumbers){
		List<String> wayBillNumberList = JSONArray.parseArray(wayBillNumbers, String.class);
		return wayBillOrderMapper.updateReceiveTypeByWayBillNumbers(wayBillNumberList, Integer.parseInt(receiveType));
	}
	
	/**
	 * 查询运单列表
	 * @param params
	 * @return
	 */
	public Result selectByParmas(SearchParams params){
		Result result = new Result();
		List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectByBaseParmas(params);
		for (WayBillOrder wayBillOrder : wayBillOrders) {
			// 设置 运单费用信息
			List<WayBillOrderCostInfo> costInfos = wayBillOrderCostInfoMapper.selectByWayBillOrderId(wayBillOrder.getId());
			for (WayBillOrderCostInfo wayBillOrderCostInfo : costInfos) {
				if(wayBillOrderCostInfo.getCode().equals("agencyFund")){
					wayBillOrder.setAgencyFund(wayBillOrderCostInfo.getMoney());
				}else if(wayBillOrderCostInfo.getCode().equals("advanceCost")){
					wayBillOrder.setAdvanceCost(wayBillOrderCostInfo.getMoney());
				}
			}
			// 设置 运单代收货款信息
			List<InsteadCollectMoney> insteadCollectMoneys = insteadCollectMoneyMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
			Double actualCollectMoney = 0D;
			Double transferredMoney = 0D;
			for (InsteadCollectMoney insteadCollectMoney : insteadCollectMoneys) {
				if(insteadCollectMoney.getActualCollectMoney() != null){
					actualCollectMoney += insteadCollectMoney.getActualCollectMoney();
				}
				if(insteadCollectMoney.getTransferredMoney() != null){
					transferredMoney += insteadCollectMoney.getTransferredMoney();
				}
			}
			wayBillOrder.setActualCollectMoney(actualCollectMoney);
			wayBillOrder.setTransferredMoney(transferredMoney);
		}
		int count = wayBillOrderMapper.selectByBaseParamsCount(params);
		result.setResults(count);
		result.setRows(wayBillOrders);
		return result;
	}
	
	/**
	 * 通过运单号获取运单信息
	 * @param wayBillNumber
	 * @return
	 */
	public WayBillOrder selectByWayBillNumber(String wayBillNumber){
		WayBillOrder wayBillOrder = wayBillOrderMapper.selectByWayBillNumber(wayBillNumber);
		if(wayBillOrder == null){
			return null;
		}
		wayBillOrder.setTargetProvinceName(xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetProvince()).getName());
		wayBillOrder.setTargetCityName(xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCity()).getName());
		wayBillOrder.setTargetCountyName(xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCounty()).getName());
		PayModeEnum[] payModeEnums = PayModeEnum.values();
		for (PayModeEnum payModeEnum : payModeEnums) {
			if(Integer.parseInt(wayBillOrder.getPayMethod()) == payModeEnum.getValue()){
				wayBillOrder.setPayMethodName(payModeEnum.getName());
				break;
			}
		}
		// 设置公司名称
		PlatformUserCompany platformUserCompany = platformUserCompanyMapper.selectByOutletsId(wayBillOrder.getStartOutlets());
		wayBillOrder.setCompanyName(platformUserCompany.getCompanyName());
		// 设置发票类型
		PlatformOrder platformOrder = platformOrderMapper.selectByWayBillNumber(wayBillNumber);
		if(platformOrder != null){
			wayBillOrder.setReceiptType(platformOrder.getReceiptType());
		}
		// 设置接单时间
		String orderNumber = wayBillOrder.getOrderNumber();
		if(StringUtils.isNotEmpty(orderNumber)){
			PlatformOrderTracking orderTracking = new PlatformOrderTracking();
			List<PlatformOrderFollow> platformOrderFollows = platformOrderFollowMapper.selectOrderFollow(orderTracking);
			for (PlatformOrderFollow platformOrderFollow : platformOrderFollows) {
				if(platformOrderFollow.getStatus() == OrderStatusEnum.ALREADY_ACCEPT.getValue()){
					wayBillOrder.setReceiveOrderTime(platformOrderFollow.getHandleTime());
					break;
				}
			}
		}
		// 设置开单员
		List<WayBillOrderFollow> wayBillOrderFollows = wayBillOrderFollowMapper.selectByWayBillNumber(wayBillNumber);
		for (WayBillOrderFollow wayBillOrderFollow : wayBillOrderFollows) {
			if(wayBillOrderFollow.getStatus() == WayBillOrderStatusEnum.NEW_WAY_BILL_ORDER.getValue()){
				wayBillOrder.setWayBillPerson(wayBillOrderFollow.getOperatePersonName());
				break;
			}
		}
		return wayBillOrder;
	}
	
	/**
	 * 关闭运单
	 * @param wayBillNumber
	 * @return
	 */
	public Boolean update2CloseOrder(String[] wayBillNumbers){
		Boolean flag = false;
		int rows = 0;
		for (String wayBillNumber : wayBillNumbers) {
			WayBillOrder wayBillOrder = new WayBillOrder();
			wayBillOrder.setWayBillNumber(wayBillNumber);
			wayBillOrder.setExceptionStatus(ExceptionStatusEnum.NO.getValue());
			wayBillOrder.setStatus(WayBillOrderStatusEnum.CLOSE.getValue());
			rows += wayBillOrderMapper.updateByWayBillNumberSelective(wayBillOrder);
		}
		if(rows > 0){
			flag = true;
		}
		return flag;
	}

	// --------------------------------------------------------------------------------
	
	/**
	 * 查询运单
	 * @param params
	 * @return
	 */
	public Result selectByParams(WayBillSearchParams params){
		// 查询 运单数据
		List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectNewByParams(params);
		
		if(wayBillOrders != null && wayBillOrders.size() > 0){
			for (WayBillOrder wayBillOrder : wayBillOrders) {
				// 设置 运单状态名称 、将运单状态 标识数字翻译成字符
				WayBillOrderStatusEnum[] wayBillOrderStatusEnums = WayBillOrderStatusEnum.values();
				for (WayBillOrderStatusEnum wayBillOrderStatusEnum : wayBillOrderStatusEnums) {
					if(wayBillOrder.getStatus() == wayBillOrderStatusEnum.getValue()){
						wayBillOrder.setStatusName(wayBillOrderStatusEnum.getName());
						continue;
					}
				}
				// 设置回单状态名称 、将回单状态 标识数字翻译成字符
				ReceiptStatusEnum[] receiptStatusEnums = ReceiptStatusEnum.values();
				for (ReceiptStatusEnum receiptStatusEnum : receiptStatusEnums) {
					if(wayBillOrder.getReceiptSlip() == 1){
						if(wayBillOrder.getReceiptStatus() == receiptStatusEnum.getValue()){
							wayBillOrder.setReceiptStatusName(receiptStatusEnum.getName());
							continue;
						}
					}else{
						wayBillOrder.setReceiptStatusName("无");
						continue;
					}
				}
				// 设置付款方式名称
				PayModeEnum[] payModeEnums = PayModeEnum.values();
				for (PayModeEnum payModeEnum : payModeEnums) {
					if(Integer.parseInt(wayBillOrder.getPayMethod()) == payModeEnum.getValue()){
						wayBillOrder.setPayMethodName(payModeEnum.getName());
					}
				}
				// 设置货物信息
				List<WayBillOrderCargoInfo> wayBillOrderCargoInfos = wayBillOrderCargoInfoMapper.selectByWayBillOrderId(wayBillOrder.getId());
				if(wayBillOrderCargoInfos != null && wayBillOrderCargoInfos.size() > 0){
					wayBillOrder.setCargoName(wayBillOrderCargoInfos.get(0).getName());
					wayBillOrder.setCargoNumber(wayBillOrderCargoInfos.get(0).getNumber());
					wayBillOrder.setCargoSetNumber(wayBillOrderCargoInfos.get(0).getSetNumber());
				}
				// 设置代收货款记录数据
				if(params.getIsAgencyFund() != null && params.getIsAgencyFund().equals("1")){
					Double actualCollectMoney = 0D;
					Double transferredMoney = 0D;
					List<InsteadCollectMoney> insteadCollectMoneys = insteadCollectMoneyMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
					for (InsteadCollectMoney insteadCollectMoney : insteadCollectMoneys) {
						actualCollectMoney += insteadCollectMoney.getActualCollectMoney() != null ? insteadCollectMoney.getActualCollectMoney() : 0;
						transferredMoney += insteadCollectMoney.getTransferredMoney() != null ? insteadCollectMoney.getTransferredMoney() : 0;
					}
					wayBillOrder.setActualCollectMoney(actualCollectMoney);
					wayBillOrder.setTransferredMoney(transferredMoney);
				}
				// 设置 异常状态名称
				ExceptionStatusEnum[] exceptionStatusEnums = ExceptionStatusEnum.values();
				for (ExceptionStatusEnum exceptionStatusEnum : exceptionStatusEnums) {
					if(wayBillOrder.getExceptionStatus() == exceptionStatusEnum.getValue()){
						wayBillOrder.setExceptionStatusName(exceptionStatusEnum.getName());
						continue;
					}
				}
				Integer exceptionStatus = wayBillOrder.getExceptionStatus();
				if(exceptionStatus == ExceptionStatusEnum.REGISTER.getValue()){
					wayBillOrder.setExceptionRegister(true);
				}else if(exceptionStatus == ExceptionStatusEnum.HANDLER.getValue()){
					wayBillOrder.setExceptionHandler(true);
				}
				// 设置 目的地
				String targetProviceName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetProvince()).getName();
				String targetCityName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCity()).getName();
				String targetCountyName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCounty()).getName();
				wayBillOrder.setTargetAddress(targetProviceName + " " + targetCityName + " " + targetCountyName);
				
				// 设置 操作人
				List<WayBillOrderFollow> wayBillOrderFollows = wayBillOrderFollowMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
				if(wayBillOrderFollows != null && wayBillOrderFollows.size() > 0){
					wayBillOrder.setOperatePerson(wayBillOrderFollows.get(0).getOperatePerson());
					wayBillOrder.setOperatePersonName(wayBillOrderFollows.get(0).getOperatePersonName());
				}
			}
		}
		
		// 统计运单条数
		int count = wayBillOrderMapper.selectNewByParamsCount(params);
		Result result = new Result();
		result.setResults(count);
		result.setRows(wayBillOrders);
		result.setData(wayBillOrderMapper.selectNewByParamsTotal(params));
		return result;
	}
	/**
	 * 查询运单--到站的
	 * @param params
	 * @return
	 */
	public Result selectByParamsEnd(WayBillSearchParams params){
		// 查询 运单数据
		List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectNewByParamsEnd(params);
		
		if(wayBillOrders != null && wayBillOrders.size() > 0){
			for (WayBillOrder wayBillOrder : wayBillOrders) {
				// 设置 运单状态名称 、将运单状态 标识数字翻译成字符
				WayBillOrderStatusEnum[] wayBillOrderStatusEnums = WayBillOrderStatusEnum.values();
				for (WayBillOrderStatusEnum wayBillOrderStatusEnum : wayBillOrderStatusEnums) {
					if(wayBillOrder.getStatus() == wayBillOrderStatusEnum.getValue()){
						wayBillOrder.setStatusName(wayBillOrderStatusEnum.getName());
						continue;
					}
				}
				// 设置回单状态名称 、将回单状态 标识数字翻译成字符
				ReceiptStatusEnum[] receiptStatusEnums = ReceiptStatusEnum.values();
				for (ReceiptStatusEnum receiptStatusEnum : receiptStatusEnums) {
					if(wayBillOrder.getReceiptSlip() == 1){
						if(wayBillOrder.getReceiptStatus() == receiptStatusEnum.getValue()){
							wayBillOrder.setReceiptStatusName(receiptStatusEnum.getName());
							continue;
						}
					}else{
						wayBillOrder.setReceiptStatusName("无");
						continue;
					}
				}
				// 设置付款方式名称
				PayModeEnum[] payModeEnums = PayModeEnum.values();
				for (PayModeEnum payModeEnum : payModeEnums) {
					if(Integer.parseInt(wayBillOrder.getPayMethod()) == payModeEnum.getValue()){
						wayBillOrder.setPayMethodName(payModeEnum.getName());
					}
				}
				// 设置货物信息
				List<WayBillOrderCargoInfo> wayBillOrderCargoInfos = wayBillOrderCargoInfoMapper.selectByWayBillOrderId(wayBillOrder.getId());
				if(wayBillOrderCargoInfos != null && wayBillOrderCargoInfos.size() > 0){
					wayBillOrder.setCargoName(wayBillOrderCargoInfos.get(0).getName());
					wayBillOrder.setCargoNumber(wayBillOrderCargoInfos.get(0).getNumber());
					wayBillOrder.setCargoSetNumber(wayBillOrderCargoInfos.get(0).getSetNumber());
				}
				// 设置代收货款记录数据
				if(params.getIsAgencyFund() != null && params.getIsAgencyFund().equals("1")){
					Double actualCollectMoney = 0D;
					Double transferredMoney = 0D;
					List<InsteadCollectMoney> insteadCollectMoneys = insteadCollectMoneyMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
					for (InsteadCollectMoney insteadCollectMoney : insteadCollectMoneys) {
						actualCollectMoney += insteadCollectMoney.getActualCollectMoney() != null ? insteadCollectMoney.getActualCollectMoney() : 0;
						transferredMoney += insteadCollectMoney.getTransferredMoney() != null ? insteadCollectMoney.getTransferredMoney() : 0;
					}
					wayBillOrder.setActualCollectMoney(actualCollectMoney);
					wayBillOrder.setTransferredMoney(transferredMoney);
				}
				// 设置 异常状态名称
				ExceptionStatusEnum[] exceptionStatusEnums = ExceptionStatusEnum.values();
				for (ExceptionStatusEnum exceptionStatusEnum : exceptionStatusEnums) {
					if(wayBillOrder.getExceptionStatus() == exceptionStatusEnum.getValue()){
						wayBillOrder.setExceptionStatusName(exceptionStatusEnum.getName());
						continue;
					}
				}
				Integer exceptionStatus = wayBillOrder.getExceptionStatus();
				if(exceptionStatus == ExceptionStatusEnum.REGISTER.getValue()){
					wayBillOrder.setExceptionRegister(true);
				}else if(exceptionStatus == ExceptionStatusEnum.HANDLER.getValue()){
					wayBillOrder.setExceptionHandler(true);
				}
				// 设置 目的地
				String targetProviceName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetProvince()).getName();
				String targetCityName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCity()).getName();
				String targetCountyName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCounty()).getName();
				wayBillOrder.setTargetAddress(targetProviceName + " " + targetCityName + " " + targetCountyName);
				
				// 设置 操作人
				List<WayBillOrderFollow> wayBillOrderFollows = wayBillOrderFollowMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
				if(wayBillOrderFollows != null && wayBillOrderFollows.size() > 0){
					wayBillOrder.setOperatePerson(wayBillOrderFollows.get(0).getOperatePerson());
					wayBillOrder.setOperatePersonName(wayBillOrderFollows.get(0).getOperatePersonName());
				}
			}
		}
		
		// 统计运单条数
		int count = wayBillOrderMapper.selectNewByParamsCountEnd(params);
		Result result = new Result();
		result.setResults(count);
		result.setRows(wayBillOrders);
		result.setData(wayBillOrderMapper.selectNewByParamsTotalEnd(params));
		return result;
	}
	/**
	 * 查询导出运单
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectExportByParams(WayBillSearchParams params){
		// 查询 运单数据
		List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectNewExportByParams(params);
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		Map<String, Object> sheetName = new HashMap<String, Object>();
		sheetName.put("sheetName", "运单列表");
	    result.add(sheetName);
		if(wayBillOrders ==null || wayBillOrders.size() == 0){
			return result;
		}
		for (WayBillOrder wayBillOrder : wayBillOrders) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderNumber", wayBillOrder.getOrderNumber());
			map.put("wayBillNumber", wayBillOrder.getWayBillNumber());
			map.put("startOutlets", wayBillOrder.getStartOutletsName());
			map.put("targetOutlets", wayBillOrder.getTargetOutletsName());
			map.put("targetProvince", xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetProvince()).getName());
			map.put("targetCity", xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCity()).getName());
			if(xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCounty())!=null){
				map.put("targetCounty", xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCounty()).getName());
			}else{
				map.put("targetCounty", "");
			}
			map.put("consignor", wayBillOrder.getConsignor());
			map.put("consignorMobile", wayBillOrder.getConsignorMobile());
			map.put("consignorAddress", wayBillOrder.getConsignorAddress());
			map.put("consignorCompany", wayBillOrder.getConsignorCompany());
			map.put("consignee", wayBillOrder.getConsignee());
			map.put("consigneeMobile", wayBillOrder.getConsigneeMobile());
			map.put("consigneeAddress", wayBillOrder.getConsigneeAddress());
			map.put("consigneeCompany", wayBillOrder.getConsigneeCompany());
			String sendTypeName = null;
			if(wayBillOrder.getSendType() == 0){
				sendTypeName = "自送网点";
			}else if(wayBillOrder.getSendType() == 1){
				sendTypeName = "上门取货";
			}
			map.put("sendType", sendTypeName);
			String receiveTypeName = null;
			if(wayBillOrder.getReceiveType() == 0){
				receiveTypeName = "自提";
			}else if(wayBillOrder.getReceiveType() == 1){
				receiveTypeName = "送货上门";
			}
			map.put("receiveType", receiveTypeName);
			map.put("receiptSlip", wayBillOrder.getReceiptSlip() == 0 ? "否" : "是");
			map.put("receiptSlipNum", wayBillOrder.getReceiptSlipNum());
			map.put("cityDriverName", wayBillOrder.getCityDriverName());
			map.put("cityDriverPhone", wayBillOrder.getCityDriverPhone());
			map.put("cityVehicleNumber", wayBillOrder.getCityVehicleNumber());
			String payMethodName = null;
			PayModeEnum[] payModeEnums = PayModeEnum.values();
			for (PayModeEnum payModeEnum : payModeEnums) {
				if(payModeEnum.getValue() == Integer.parseInt(wayBillOrder.getPayMethod())){
					payMethodName = payModeEnum.getName();
					break;
				}
			}
			map.put("payMethod", payMethodName);
			String statusName = null;
			WayBillOrderStatusEnum[] wayBillSIgnStatusEnums = WayBillOrderStatusEnum.values();
			for (WayBillOrderStatusEnum wayBillOrderStatusEnum : wayBillSIgnStatusEnums) {
				if(wayBillOrderStatusEnum.getValue() == wayBillOrder.getStatus()){
					statusName = wayBillOrderStatusEnum.getName();
					break;
				}
			}
			map.put("status", statusName);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("wayBillOrderTime", format.format(wayBillOrder.getWayBillOrderTime()));
			map.put("remark", wayBillOrder.getRemark());
			result.add(map);
		}
		return result;
	}
	
	
	/**
	 * 查询导出回单
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> selectExportReceiptByParams(ReceiptBillSearchParams params){
		// 查询 运单数据
		List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectExportReceiptByParams(params);
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		Map<String, Object> sheetName = new HashMap<String, Object>();
		sheetName.put("sheetName", "回单列表");
	    result.add(sheetName);
		if(wayBillOrders ==null || wayBillOrders.size() == 0){
			return result;
		}
		for (WayBillOrder wayBillOrder : wayBillOrders) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderNumber", wayBillOrder.getOrderNumber());
			map.put("wayBillNumber", wayBillOrder.getWayBillNumber());
			map.put("startOutlets", wayBillOrder.getStartOutletsName());
			map.put("targetOutlets", wayBillOrder.getTargetOutletsName());
//			map.put("targetProvince", xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetProvince()).getName());
//			map.put("targetCity", xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCity()).getName());
//			map.put("targetCounty", xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCounty()).getName());
			map.put("consignor", wayBillOrder.getConsignor());
			map.put("consignorMobile", wayBillOrder.getConsignorMobile());
//			map.put("consignorAddress", wayBillOrder.getConsignorAddress());
//			map.put("consignorCompany", wayBillOrder.getConsignorCompany());
			map.put("consignee", wayBillOrder.getConsignee());
//			map.put("consigneeMobile", wayBillOrder.getConsigneeMobile());
//			map.put("consigneeAddress", wayBillOrder.getConsigneeAddress());
//			map.put("consigneeCompany", wayBillOrder.getConsigneeCompany());
//			String sendTypeName = null;
//			if(wayBillOrder.getSendType() == 0){
//				sendTypeName = "自送网点";
//			}else if(wayBillOrder.getSendType() == 1){
//				sendTypeName = "上门取货";
//			}
//			map.put("sendType", sendTypeName);
//			String receiveTypeName = null;
//			if(wayBillOrder.getReceiveType() == 0){
//				receiveTypeName = "自提";
//			}else if(wayBillOrder.getReceiveType() == 1){
//				receiveTypeName = "送货上门";
//			}
//			map.put("receiveType", receiveTypeName);
			map.put("receiptSlip", wayBillOrder.getReceiptSlip() == 0 ? "否" : "是");
//			map.put("receiptSlipNum", wayBillOrder.getReceiptSlipNum());
//			map.put("cityDriverName", wayBillOrder.getCityDriverName());
//			map.put("cityDriverPhone", wayBillOrder.getCityDriverPhone());
//			map.put("cityVehicleNumber", wayBillOrder.getCityVehicleNumber());
//			String payMethodName = null;
//			PayModeEnum[] payModeEnums = PayModeEnum.values();
//			for (PayModeEnum payModeEnum : payModeEnums) {
//				if(payModeEnum.getValue() == Integer.parseInt(wayBillOrder.getPayMethod())){
//					payMethodName = payModeEnum.getName();
//					break;
//				}
//			}
//			map.put("payMethod", payMethodName);
			String statusName = null;
			WayBillOrderStatusEnum[] wayBillSIgnStatusEnums = WayBillOrderStatusEnum.values();
			for (WayBillOrderStatusEnum wayBillOrderStatusEnum : wayBillSIgnStatusEnums) {
				if(wayBillOrderStatusEnum.getValue() == wayBillOrder.getStatus()){
					statusName = wayBillOrderStatusEnum.getName();
					break;
				}
			}
			map.put("status", statusName);
			String receiptStatusName = null;
			ReceiptStatusEnum[] receiptStatusEnums = ReceiptStatusEnum.values();
			for (ReceiptStatusEnum receiptStatusEnum : receiptStatusEnums) {
				Integer receiptStatus = wayBillOrder.getReceiptStatus();
				if(receiptStatus == null){
					receiptStatusName = "无";
				}else{
					if(receiptStatusEnum.getValue() == wayBillOrder.getReceiptStatus()){
						receiptStatusName = receiptStatusEnum.getName();
						break;
					}
				}
			}
			map.put("receiptStatus", receiptStatusName);
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			map.put("wayBillOrderTime", format.format(wayBillOrder.getWayBillOrderTime()));
//			map.put("remark", wayBillOrder.getRemark());
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 修改 回单状态
	 * @param wayBillNumber 运单号
	 * @param receiptStatus 回单状态
	 * @return
	 */
	public int updateReceiptStatus(String wayBillNumber, String receiptStatus){
		WayBillOrder wayBillOrder = new WayBillOrder();
		wayBillOrder.setWayBillNumber(wayBillNumber);
		wayBillOrder.setReceiptStatus(Integer.parseInt(receiptStatus));
		return wayBillOrderMapper.updateByWayBillNumberSelective(wayBillOrder);
	}
	
	/**
	 * 获取运单费用列表数据
	 * @return
	 */
	public Result selectWayBillFreightList(WayBillSearchParams params){
		Result result = new Result();
		List<Integer> startStatusList = new ArrayList<Integer>();
		startStatusList.add(WayBillOrderStatusEnum.NEW_WAY_BILL_ORDER.getValue());
		startStatusList.add(WayBillOrderStatusEnum.ALREADY_START_VEHICLE.getValue());
		startStatusList.add(WayBillOrderStatusEnum.ALREADY_REACH_CARGO.getValue());
		startStatusList.add(WayBillOrderStatusEnum.ALREADY_DISCHARGE.getValue());
		startStatusList.add(WayBillOrderStatusEnum.PART_DISCHARGE.getValue());
		startStatusList.add(WayBillOrderStatusEnum.ALREADY_OUT_SOURCE.getValue());
		startStatusList.add(WayBillOrderStatusEnum.RECEIVED.getValue());
		params.setStartStatusList(startStatusList);
		List<Integer> startPayMethodList = new ArrayList<Integer>();
		startPayMethodList.add(PayModeEnum.IMMEDIATELY_PAY.getValue());
		startPayMethodList.add(PayModeEnum.BACK_PAY.getValue());
		startPayMethodList.add(PayModeEnum.MONTH_SETTLEMENT.getValue());
		params.setStartPayMethodList(startPayMethodList);
		List<Integer> targetStatusList = new ArrayList<Integer>();
		targetStatusList.add(WayBillOrderStatusEnum.ALREADY_DISCHARGE.getValue());
		targetStatusList.add(WayBillOrderStatusEnum.PART_DISCHARGE.getValue());
		targetStatusList.add(WayBillOrderStatusEnum.ALREADY_OUT_SOURCE.getValue());
		targetStatusList.add(WayBillOrderStatusEnum.RECEIVED.getValue());
		params.setTargetStatusList(targetStatusList);
		List<Integer> targetPayMethodList = new ArrayList<Integer>();
		targetPayMethodList.add(PayModeEnum.ARRIVE_PAY.getValue());
		params.setTargetPayMethodList(targetPayMethodList);
		List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectWayBillFreightList(params);
		for (WayBillOrder wayBillOrder : wayBillOrders) {
			PayModeEnum[] modeEnums = PayModeEnum.values();
			for (PayModeEnum payModeEnum : modeEnums) {
				if(Integer.parseInt(wayBillOrder.getPayMethod()) == payModeEnum.getValue()){
					wayBillOrder.setPayMethodName(payModeEnum.getName());
					continue;
				}
			}
			System.out.println(wayBillOrder.getWayBillNumber());
			// 查询运单对应发车单信息
			DepartList departList = departListMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
			if(departList != null){
				wayBillOrder.setDepartNumber(departList.getDepartNumber());
				wayBillOrder.setStartTime(departList.getStartTime());
				wayBillOrder.setEndTime(departList.getEndTime());
			}
			// 查询运单对应的货物信息
			List<WayBillOrderCargoInfo> wayBillOrderCargoInfos = wayBillOrderCargoInfoMapper.selectByWayBillOrderId(wayBillOrder.getId());
			if(wayBillOrderCargoInfos != null && wayBillOrderCargoInfos.size() > 0){
				WayBillOrderCargoInfo wayBillOrderCargoInfo = wayBillOrderCargoInfos.get(0);
				wayBillOrder.setCargoBrand(wayBillOrderCargoInfo.getBrand());
				wayBillOrder.setCargoName(wayBillOrderCargoInfo.getName());
				wayBillOrder.setCargoNumber(wayBillOrderCargoInfo.getNumber());
				wayBillOrder.setCargoSetNumber(wayBillOrderCargoInfo.getSetNumber());
			}
			// 查询签收记录 设置 签收人、签收时间
			SignRecord signRecord = signRecordMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
			if(signRecord != null){
				wayBillOrder.setSignPerson(signRecord.getSignPerson());
				wayBillOrder.setSignTime(signRecord.getSignTime());
			}
			// 设置 目的地
			String targetProviceName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetProvince()).getName();
			String targetCityName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCity()).getName();
			String targetCountyName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCounty()).getName();
			wayBillOrder.setTargetAddress(targetProviceName + " " + targetCityName + " " + targetCountyName);
		}
		int count = wayBillOrderMapper.selectWayBillFreightListCount(params);
		result.setRows(wayBillOrders);
		result.setResults(count);
		return result;
	}
	
	/**
	 * 获取 发车单的所有运单号
	 * @param departLists
	 * @return
	 */
	/*private List<String> getWayBillNumbersByDepartLists(List<DepartList> departLists){
		List<String> wayBillNumberList = new ArrayList<String>();
		for (DepartList departList : departLists) {
			String wayBillNumbers = departList.getWayBillNumbers();
			String[] wayBillNumberArr = wayBillNumbers.split(",");
			for (String wayBillNumber : wayBillNumberArr) {
				wayBillNumberList.add(wayBillNumber);
			}
		}
		return wayBillNumberList;
	}*/
	
	/**
	 * 根据收费订单 查询运单信息
	 * @param receiveMoneyOrderId
	 * @return
	 */
	public List<WayBillOrder> selectByReceiveMoneyOrderId(String receiveMoneyOrderId){
		List<ReceiveMoneyOrderRelation> receiveMoneyOrderRelations = receiveMoneyOrderRelationMapper.selectByReceiveMoneyOrderId(Long.parseLong(receiveMoneyOrderId));
		if(receiveMoneyOrderRelations == null || receiveMoneyOrderRelations.size() == 0){
			return null;
		}
		List<String> wayBillNumbers = new ArrayList<String>();
		for (ReceiveMoneyOrderRelation receiveMoneyOrderRelation : receiveMoneyOrderRelations) {
			wayBillNumbers.add(receiveMoneyOrderRelation.getWayBillNumber());
		}
		List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectByWayBillNumbers(wayBillNumbers);
		if(wayBillOrders == null || wayBillOrders.size() == 0){
			return null;
		}
		for (WayBillOrder wayBillOrder : wayBillOrders) {
			PayModeEnum[] modeEnums = PayModeEnum.values();
			for (PayModeEnum payModeEnum : modeEnums) {
				if(Integer.parseInt(wayBillOrder.getPayMethod()) == payModeEnum.getValue()){
					wayBillOrder.setPayMethodName(payModeEnum.getName());
					continue;
				}
			}
			// 查询运单对应发车单信息
			DepartList departList = departListMapper.selectByWayBillNumber(wayBillOrder.getWayBillNumber());
			if(departList != null){
				wayBillOrder.setDepartNumber(departList.getDepartNumber());
				wayBillOrder.setStartTime(departList.getStartTime());
				wayBillOrder.setEndTime(departList.getEndTime());
			}
			// 查询运单对应的货物信息
			List<WayBillOrderCargoInfo> wayBillOrderCargoInfos = wayBillOrderCargoInfoMapper.selectByWayBillOrderId(wayBillOrder.getId());
			if(wayBillOrderCargoInfos != null && wayBillOrderCargoInfos.size() > 0){
				WayBillOrderCargoInfo wayBillOrderCargoInfo = wayBillOrderCargoInfos.get(0);
				wayBillOrder.setCargoBrand(wayBillOrderCargoInfo.getBrand());
				wayBillOrder.setCargoName(wayBillOrderCargoInfo.getName());
				wayBillOrder.setCargoNumber(wayBillOrderCargoInfo.getNumber());
				wayBillOrder.setCargoSetNumber(wayBillOrderCargoInfo.getSetNumber());
			}
			// 设置 目的地
			String targetProviceName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetProvince()).getName();
			String targetCityName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCity()).getName();
			String targetCountyName = xzqhInfoMapper.selectByPrimaryKey(wayBillOrder.getTargetCounty()).getName();
			wayBillOrder.setTargetAddress(targetProviceName + " " + targetCityName + " " + targetCountyName);
		}
		return wayBillOrders;
	}
}
