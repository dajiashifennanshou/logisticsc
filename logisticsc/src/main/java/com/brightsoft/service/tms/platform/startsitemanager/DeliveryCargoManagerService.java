package com.brightsoft.service.tms.platform.startsitemanager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.brightsoft.common.enums.ExceptionStatusEnum;
import com.brightsoft.common.enums.LadingOrderStatusEnum;
import com.brightsoft.common.enums.OrderNumberTypeEnum;
import com.brightsoft.common.enums.OrderStatusEnum;
import com.brightsoft.common.enums.PayModeEnum;
import com.brightsoft.common.enums.SmsTemplateEnum;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.platform.PlatformOrderFollowMapper;
import com.brightsoft.dao.platform.PlatformOrderMapper;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.dao.platform.SmsTemplateMapper;
import com.brightsoft.dao.platform.XzqhInfoMapper;
import com.brightsoft.dao.tms.CustomerMapper;
import com.brightsoft.dao.tms.LadingOrderCargoInfoMapper;
import com.brightsoft.dao.tms.LadingOrderCostInfoMapper;
import com.brightsoft.dao.tms.LadingOrderFollowMapper;
import com.brightsoft.dao.tms.LadingOrderMapper;
import com.brightsoft.dao.tms.OrderSerialNumberMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.model.Customer;
import com.brightsoft.model.LadingOrder;
import com.brightsoft.model.LadingOrderCargoInfo;
import com.brightsoft.model.LadingOrderCostInfo;
import com.brightsoft.model.LadingOrderFollow;
import com.brightsoft.model.OrderSerialNumber;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformOrder;
import com.brightsoft.model.PlatformOrderFollow;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.SmsTemplate;
import com.brightsoft.model.User;
import com.brightsoft.service.base.SmsManagerService;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.sms.SmsParams;

/**
 * 
 * 提货管理 service
 */
@Service
public class DeliveryCargoManagerService {

	private static final Logger logger = Logger.getLogger(DeliveryCargoManagerService.class);
	
	@Autowired
	private LadingOrderMapper ladingOrderMapper;
	
	@Autowired
	private LadingOrderCargoInfoMapper ladingOrderCargoInfoMapper;
	
	@Autowired
	private LadingOrderCostInfoMapper ladingOrderCostInfoMapper;
	
	@Autowired
	private PlatformOrderMapper orderMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	@Autowired
	private OrderSerialNumberMapper orderSerialNumberMapper;
	
	@Autowired
	private PlatformOrderFollowMapper platformOrderFollowMapper;
	
	@Autowired
	private LadingOrderFollowMapper ladingOrderFollowMapper;
	
	@Autowired
	private XzqhInfoMapper xzqhInfoMapper;
	
	@Autowired
	private PlatformUserCompanyMapper platformUserCompanyMapper;
	
	@Autowired
	private PlatformOrderMapper platformOrderMapper;
	
	@Autowired
	private SmsTemplateMapper smsTemplateMapper;
	
	@Autowired
	private SmsManagerService smsManagerService;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	/**
	 * 保存提货单数据
	 * @param ladingOrder 提货单基础数据
	 * @param ladingOrderCargo 提货单货物数据
	 * @param ladingOrderCostInfo 提货单费用信息
	 * @return
	 */
	public int saveLadingOrder(String ladingOrder, String ladingOrderCargo, String ladingOrderCostInfo, User user){
		LadingOrder order =JSONObject.parseObject(ladingOrder, LadingOrder.class);
		List<LadingOrderCargoInfo> orderCargoInfos = JSONArray.parseArray(ladingOrderCargo, LadingOrderCargoInfo.class);
		List<LadingOrderCostInfo> orderCostInfos = JSONArray.parseArray(ladingOrderCostInfo, LadingOrderCostInfo.class);
		
		// 生成唯一运单号 (派车提货)
		String wayBillNumber = generateWayBillNumber(order.getStartOutlets());
		order.setWayBillNumber(wayBillNumber);
		// 保存提货单信息
		order.setStatus(LadingOrderStatusEnum.NO_DISPATCH_VEHICLE.getValue()); // 设置提货单状态
		if(order.getLadingOrderTime() == null){
			order.setLadingOrderTime(new Date());
		}
		int rows = ladingOrderMapper.insertSelective(order);
		// 保存发货人/收货人到客户管理中
		saveCustomerInfo(order, user.getCompanyId());
		
		// 添加 提货单跟踪记录
		LadingOrderFollow ladingOrderFollow = new LadingOrderFollow();
		ladingOrderFollow.setWayBillNumber(wayBillNumber);
		ladingOrderFollow.setStatus(LadingOrderStatusEnum.NO_DISPATCH_VEHICLE.getValue());
		ladingOrderFollow.setHandleTime(new Date());
		ladingOrderFollow.setHandleInfo("开提货单");
		ladingOrderFollow.setOperatePerson(user.getId());
		String operatePersonName = user.getTrueName();
		if(StringUtils.isEmpty(operatePersonName)){
			operatePersonName = user.getLoginName();
		}
		ladingOrderFollow.setOperatePersonName(operatePersonName);
		ladingOrderFollowMapper.insert(ladingOrderFollow);
		
		// 若是货运交易系统的订单 则为该订单添加运单号， 修改订单的司机信息
		String orderNumber = order.getOrderNumber();
		if(StringUtils.isNotEmpty(orderNumber)){
			PlatformOrder platformOrder = new PlatformOrder();
			platformOrder.setOrderNumber(orderNumber);
			platformOrder.setWayBillNumber(wayBillNumber);
			platformOrder.setDriverName(order.getDriverName());
			platformOrder.setDriverPhone(order.getDriverMobile());
			platformOrder.setVehicleNumber(order.getLicensePlateNo());
			orderMapper.updateByOrderNumberSelective(platformOrder);
		}
		
		// 保存提货单货物信息
		for (LadingOrderCargoInfo orderCargoInfo : orderCargoInfos) {
			orderCargoInfo.setId(null);
			orderCargoInfo.setLadingOrderId(order.getId());
		}
		ladingOrderCargoInfoMapper.batchInsert(orderCargoInfos);
		// 保存提货单费用信息
		if(orderCostInfos != null && orderCostInfos.size() > 0){
			for (LadingOrderCostInfo orderCostInfo : orderCostInfos) {
				orderCostInfo.setLadingOrderId(order.getId());
			}
			ladingOrderCostInfoMapper.batchInsert(orderCostInfos);
		}
		
		return rows;
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
	public void saveCustomerInfo(LadingOrder order, Long companyId){
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
	 * 获取派车单状态
	 * @return
	 */
	public String getLadingOrderStatus(){
		String result = "[";
		LadingOrderStatusEnum[] enums = LadingOrderStatusEnum.values();
		for (int i = 0; i < enums.length; i++) {
			LadingOrderStatusEnum oe = enums[i];
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
	 * 查询提货单信息列表
	 * @param ladingOrderStatus
	 * @param startTime
	 * @param endTiem
	 * @param condition
	 * @return
	 */
	public Result selectLadingOrderByParam(SearchParams params){
		
		Result result = new Result();
		List<LadingOrder> ladingOrders = ladingOrderMapper.selectByParams(params);
		
		if(ladingOrders != null){
			LadingOrderStatusEnum[] enums = LadingOrderStatusEnum.values();
			for (LadingOrder ladingOrder : ladingOrders) {
				for (LadingOrderStatusEnum ladingOrderStatus : enums) {
					if(ladingOrder.getStatus() == ladingOrderStatus.getValue()){
						ladingOrder.setStatusName(ladingOrderStatus.getName());
						break;
					}
				}
				// 查询 最近一条跟踪记录
				List<LadingOrderFollow> ladingOrderFollows = ladingOrderFollowMapper.selectByWayBillNumber(ladingOrder.getWayBillNumber());
				if(ladingOrderFollows != null && ladingOrderFollows.size() > 0){
					ladingOrder.setOperatePerson(ladingOrderFollows.get(0).getOperatePerson());
					ladingOrder.setOperatePersonName(ladingOrderFollows.get(0).getOperatePersonName());
				}
				// 设置 异常状态名称
				ExceptionStatusEnum[] exceptionStatusEnums = ExceptionStatusEnum.values();
				for (ExceptionStatusEnum exceptionStatusEnum : exceptionStatusEnums) {
					if(ladingOrder.getExceptionStatus() == exceptionStatusEnum.getValue()){
						ladingOrder.setExceptionStatusName(exceptionStatusEnum.getName());
						continue;
					}
				}
				// 设置 相应的异常信息
				Integer exceptionStatus = ladingOrder.getExceptionStatus();
				if(exceptionStatus == ExceptionStatusEnum.REGISTER.getValue()){
					ladingOrder.setExceptionRegister(true);
				}else if(exceptionStatus == ExceptionStatusEnum.HANDLER.getValue()){
					ladingOrder.setExceptionHandler(true);
				}
			}
		}
		int count = ladingOrderMapper.selectByParamsCount(params);
		result.setRows(ladingOrders);
		result.setResults(count);
		
		return result;
	}
	
	/**
	 * 根据运单号 查询提货单信息
	 * @param wayBillNumber
	 * @return
	 */
	public LadingOrder selectByWayBillNumber(String wayBillNumber){
		LadingOrder ladingOrder = ladingOrderMapper.selectByWayBillNumber(wayBillNumber);
		if(ladingOrder != null){
			PayModeEnum[] payModeEnums = PayModeEnum.values();
			for (PayModeEnum payModeEnum : payModeEnums) {
				if(ladingOrder.getPayMethod() == payModeEnum.getValue()){
					ladingOrder.setPayMethodName(payModeEnum.getName());
					break;
				}
			}
			ladingOrder.setTargetProvinceName(xzqhInfoMapper.selectByPrimaryKey(ladingOrder.getTargetProvince()).getName());
			ladingOrder.setTargetCityName(xzqhInfoMapper.selectByPrimaryKey(ladingOrder.getTargetCity()).getName());
			ladingOrder.setTargetCountyName(xzqhInfoMapper.selectByPrimaryKey(ladingOrder.getTargetCounty()).getName());
			
			// 设置 公司名称
			PlatformUserCompany platformUserCompany = platformUserCompanyMapper.selectByOutletsId(ladingOrder.getStartOutlets());
			ladingOrder.setCompanyName(platformUserCompany.getCompanyName());
			// 设置 发票类型
			String orderNumber = ladingOrder.getOrderNumber();
			if(StringUtils.isNotEmpty(orderNumber)){
				PlatformOrder platformOrder = platformOrderMapper.selectByOrderNumber(orderNumber);
				ladingOrder.setReceiptType(platformOrder.getReceiptType());
				// 设置预约提货时间
				ladingOrder.setDeliveryDate(platformOrder.getDeliveryDate());
				ladingOrder.setDeliveryStartTime(platformOrder.getDeliveryStartTime());
				ladingOrder.setDeliveryEndTime(platformOrder.getDeliveryEndTime());
			}
			// 设置开单员
			List<LadingOrderFollow> ladingOrderFollows = ladingOrderFollowMapper.selectByWayBillNumber(wayBillNumber);
			for (LadingOrderFollow ladingOrderFollow : ladingOrderFollows) {
				if(ladingOrderFollow.getStatus() == LadingOrderStatusEnum.NO_DISPATCH_VEHICLE.getValue()){
					ladingOrder.setLadingOrderPerson(ladingOrderFollow.getOperatePersonName());
					break;
				}
			}
		}
		
		return ladingOrderMapper.selectByWayBillNumber(wayBillNumber);
	}
	
	/**
	 * 修改派车单状态
	 * @param ladingOrder
	 * @return
	 */
	public Integer updateLadingOrderStatus(String wayBillNumber, Integer status){
		
		// 1、修改派车单状态为[已派车],则修改订单状态为[提货中]
		if(LadingOrderStatusEnum.ALREADY_DISPATCH_VEHICLE.getValue() == status){
			updatePlatformOrderStatusByWayBillNumber(wayBillNumber, OrderStatusEnum.TAkING_CARGO.getValue());
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("wayBillNumber", wayBillNumber);
		params.put("status", status);
		return ladingOrderMapper.updateStatusByWayBillNumber(params);
	}
	
	/**
	 * 派车提货
	 * @param wayBillNumber
	 * @param user
	 * @return
	 */
	public int saveDeliveryTakeCargo(String wayBillNumber, User user){
		// 修改派车单状态为[已派车]
		LadingOrder ladingOrder = new LadingOrder();
		ladingOrder.setWayBillNumber(wayBillNumber);
		ladingOrder.setStatus(LadingOrderStatusEnum.ALREADY_DISPATCH_VEHICLE.getValue());
		int rows = ladingOrderMapper.updateByWayBillNumberSelective(ladingOrder);
		
		// 添加 提货单跟踪记录
		LadingOrderFollow ladingOrderFollow = new LadingOrderFollow();
		ladingOrderFollow.setWayBillNumber(wayBillNumber);
		ladingOrderFollow.setStatus(LadingOrderStatusEnum.ALREADY_DISPATCH_VEHICLE.getValue());
		ladingOrderFollow.setHandleTime(new Date());
		ladingOrderFollow.setHandleInfo("派车提货");
		ladingOrderFollow.setOperatePerson(user.getId());
		String operatePersonName = user.getTrueName();
		if(StringUtils.isEmpty(operatePersonName)){
			operatePersonName = user.getLoginName();
		}
		ladingOrderFollow.setOperatePersonName(operatePersonName);
		ladingOrderFollowMapper.insert(ladingOrderFollow);
		
		PlatformOrder order = orderMapper.selectByWayBillNumber(wayBillNumber);
		if(order != null){ // 若是 货运交易系统的订单
			// 修改订单状态为[提货中]
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("wayBillNumber", wayBillNumber);
			map.put("status", OrderStatusEnum.TAkING_CARGO.getValue());
			orderMapper.updateOrderStatusByWayBillNumber(map);
			// 添加订单跟踪记录
			PlatformOrderFollow orderFollow = new PlatformOrderFollow();
			orderFollow.setOrderNumber(order.getOrderNumber());
			orderFollow.setWayBillNumber(wayBillNumber);
			orderFollow.setStatus(OrderStatusEnum.TAkING_CARGO.getValue());
			orderFollow.setHandleTime(new Date());
			orderFollow.setHandleInfo("派车提货");
			orderFollow.setOperatePerson(user.getTrueName());
			platformOrderFollowMapper.insert(orderFollow);
		}
		// 发送短信
		sendMessage(wayBillNumber, SmsTemplateEnum.TAKE_CARGO, "派车提货");
		return rows;
	}
	
	/**
	 * 发送短信
	 * @param wayBillNumber
	 * @param smsTemplateEnum
	 */
	private void sendMessage(String wayBillNumber, SmsTemplateEnum smsTemplateEnum, String statusName){
		LadingOrder ladingOrder = ladingOrderMapper.selectByWayBillNumber(wayBillNumber);
		if(ladingOrder == null){
			logger.warn("【未找到提货单】");
			return;
		}
		String orderNumber = ladingOrder.getOrderNumber();
		if(StringUtils.isEmpty(orderNumber)){
			return;
		}
		
		
		SmsTemplate smsTemplate = smsTemplateMapper.getSmsTemplateByAbbr(smsTemplateEnum.getValue());
		if(smsTemplate == null){
			logger.warn("【获取短信模板失败】");
			return;
		}
		if(smsTemplate.getIsEnabled() != null && smsTemplate.getIsEnabled() != 1){
			logger.warn("【短信模板未启用】");
			return;
		}
		PlatformOrder platformOrder = orderMapper.selectByOrderNumber(orderNumber);
		// 获取 发货人、收货人 手机号
		String consignorMobile = platformOrder.getConsignorPhoneNumber();
		String consigneeMobile = platformOrder.getConsigneePhoneNumber();
		// 获取上线网点名称，公司名称
		OutletsInfo outletsInfo = outletsInfoMapper.selectById(platformOrder.getStartOutlets());
		String outletsName = outletsInfo.getName();
		PlatformUserCompany company = platformUserCompanyMapper.selectByOutletsId(platformOrder.getStartOutlets());
		String companyName = company.getCompanyName();
		
		SmsParams smsParams = new SmsParams();
		smsParams.setOrderNumber(orderNumber);
		smsParams.setWaybillNumber(wayBillNumber);
		smsParams.setCompany(companyName);
		smsParams.setOutletsName(outletsName);
		smsParams.setOrderStatus(statusName);
		smsParams.setCarNumber(platformOrder.getVehicleNumber());
		smsParams.setLinkPhone(platformOrder.getDriverPhone());
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
	 * 根据 运单号修改货运交易系统订单
	 * @param wayBillNumber
	 * @param status
	 */
	private void updatePlatformOrderStatusByWayBillNumber(String wayBillNumber, Integer status){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wayBillNumber", wayBillNumber);
		map.put("status", status);
		orderMapper.updateOrderStatusByWayBillNumber(map);
	}
	
	/**
	 * 作废提货单
	 * @param wayBillNumber
	 * @return
	 */
	public int abolishDeliveryOrder(String wayBillNumber, User user){
		LadingOrder ladingOrder = ladingOrderMapper.selectByWayBillNumber(wayBillNumber);
		String orderNumber = ladingOrder.getOrderNumber();
		// 如果是货运交易系统的订单 则修改对应订单的状态为【已作废】
		if(StringUtils.isNotEmpty(orderNumber)){
			PlatformOrder platformOrder = new PlatformOrder();
			platformOrder.setOrderNumber(orderNumber);
			platformOrder.setState(OrderStatusEnum.DISCARDED.getValue());
			orderMapper.updateByOrderNumberSelective(platformOrder);
			// 添加订单跟踪记录
			PlatformOrderFollow orderFollow = new PlatformOrderFollow();
			orderFollow.setOrderNumber(orderNumber);
			orderFollow.setWayBillNumber(wayBillNumber);
			orderFollow.setStatus(OrderStatusEnum.DISCARDED.getValue());
			orderFollow.setHandleTime(new Date());
			orderFollow.setHandleInfo("作废提货单");
			orderFollow.setOperatePerson(user.getTrueName());
			platformOrderFollowMapper.insert(orderFollow);
		}
		// 修改派车单的状态为 【已作废】
		LadingOrder ladingOrderTemp = new LadingOrder();
		ladingOrderTemp.setId(ladingOrder.getId());
		ladingOrderTemp.setStatus(LadingOrderStatusEnum.ALREADY_ABOLISH.getValue());
		int rows = ladingOrderMapper.updateByPrimaryKeySelective(ladingOrderTemp);
		
		// 添加 提货单跟踪记录
		LadingOrderFollow ladingOrderFollow = new LadingOrderFollow();
		ladingOrderFollow.setWayBillNumber(wayBillNumber);
		ladingOrderFollow.setStatus(LadingOrderStatusEnum.ALREADY_ABOLISH.getValue());
		ladingOrderFollow.setHandleTime(new Date());
		ladingOrderFollow.setHandleInfo("作废");
		ladingOrderFollow.setOperatePerson(user.getId());
		String operatePersonName = user.getTrueName();
		if(StringUtils.isEmpty(operatePersonName)){
			operatePersonName = user.getLoginName();
		}
		ladingOrderFollow.setOperatePersonName(operatePersonName);
		ladingOrderFollowMapper.insert(ladingOrderFollow);
		return rows;
	}
}
