package com.brightsoft.service.platform;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.common.enums.CountCostModeEnum;
import com.brightsoft.common.enums.ExceptionStatusEnum;
import com.brightsoft.common.enums.OrderNumberTypeEnum;
import com.brightsoft.common.enums.OrderStatusEnum;
import com.brightsoft.common.enums.PayModeEnum;
import com.brightsoft.common.enums.SmsTemplateEnum;
import com.brightsoft.controller.vo.OrderSearchParams;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.controller.vo.StatisticsHelper;
import com.brightsoft.dao.platform.PlatformOrderAdditionalServerMapper;
import com.brightsoft.dao.platform.PlatformOrderAgencyFundMapper;
import com.brightsoft.dao.platform.PlatformOrderBillMapper;
import com.brightsoft.dao.platform.PlatformOrderCargoMapper;
import com.brightsoft.dao.platform.PlatformOrderCargoTempMapper;
import com.brightsoft.dao.platform.PlatformOrderComplainMapper;
import com.brightsoft.dao.platform.PlatformOrderEvaluationMapper;
import com.brightsoft.dao.platform.PlatformOrderFollowMapper;
import com.brightsoft.dao.platform.PlatformOrderMapper;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.dao.platform.PlatformUserMapper;
import com.brightsoft.dao.platform.SmsTemplateMapper;
import com.brightsoft.dao.tms.AdditionalServerConfMapper;
import com.brightsoft.dao.tms.LadingOrderMapper;
import com.brightsoft.dao.tms.LineInfoMapper;
import com.brightsoft.dao.tms.OrderSerialNumberMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.dao.tms.WayBillOrderMapper;
import com.brightsoft.model.AdditionalServerConf;
import com.brightsoft.model.LadingOrder;
import com.brightsoft.model.LineInfo;
import com.brightsoft.model.OrderSerialNumber;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.OutletsPriceRangeConf;
import com.brightsoft.model.PlatformDeliverGoods;
import com.brightsoft.model.PlatformHomeOrderCity;
import com.brightsoft.model.PlatformMineOrder;
import com.brightsoft.model.PlatformOrder;
import com.brightsoft.model.PlatformOrderAdditionalServer;
import com.brightsoft.model.PlatformOrderAgencyFund;
import com.brightsoft.model.PlatformOrderBill;
import com.brightsoft.model.PlatformOrderCargo;
import com.brightsoft.model.PlatformOrderCargoTemp;
import com.brightsoft.model.PlatformOrderFollow;
import com.brightsoft.model.PlatformOrderTracking;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserCommonCargo;
import com.brightsoft.model.PlatformUserCommonContact;
import com.brightsoft.model.PlatformUserCommonDriver;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.SmsTemplate;
import com.brightsoft.model.User;
import com.brightsoft.model.WayBillOrder;
import com.brightsoft.model.sysVoOrderCount;
import com.brightsoft.service.base.SmsManagerService;
import com.brightsoft.service.tms.platform.OutletsPriceRangeConfService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.WebConstant;
import com.brightsoft.utils.sms.SmsParams;

/**
 * 
 * 货运交易系统 订单 service
 */
@Service
public class PlatformOrderServiceImpl {
	
	private static final Logger logger = Logger.getLogger(PlatformOrderServiceImpl.class);
	
	@Autowired 
	public PlatformUserMapper userMapper;

	@Autowired
	private PlatformOrderMapper orderMapper;
	
	@Autowired
	private PlatformOrderAdditionalServerMapper additionalServerMapper;
	
	@Autowired
	private PlatformOrderCargoMapper orderCargoMapper;
	
	@Autowired
	private PlatformOrderCargoTempMapper platformOrderCargoTempMapper;
	
	@Autowired
	private XzqhServiceImpl xzqhServiceImpl;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private PlatformOrderAgencyFundMapper agencyFundMapper;
	
	@Autowired
	private LineInfoMapper lineInfoMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	@Autowired
	private AdditionalServerConfMapper additionalServerConfMapper;
	
	@Autowired
	private PlatformOrderBillMapper orderBillMapper;
	
	@Autowired
	private PlatformOrderEvaluationMapper evaluationMapper;
	
	@Autowired
	private PlatformOrderComplainMapper complainMapper;
	
	@Autowired
	private OrderSerialNumberMapper orderSerialNumberMapper;
	
	@Autowired
	private PlatformOrderCargoService platformOrderCargoService;
	
	@Autowired
	private PlatformOrderAdditionalServerService platformOrderAdditionalServerService;
	
	@Autowired
	private PlatformOrderFollowMapper platformOrderFollowMapper;
	
	@Autowired
	private PlatformUserCommonContactService platformUserCommonContactService;
	
	@Autowired
	private PlatformUserCommonDriverService platformUserCommonDriverService;
	
	@Autowired
	private PlatformUserCommonCargoService platformUserCommonCargoService;
	
	@Autowired
	private OutletsPriceRangeConfService outletsPriceRangeConfService;
	
	@Autowired
	private WayBillOrderMapper wayBillOrderMapper;
	
	@Autowired
	private LadingOrderMapper ladingOrderMapper;
	
	@Autowired
	private SmsManagerService smsManagerService;
	
	@Autowired
	private SmsTemplateMapper smsTemplateMapper;
	
	@Autowired
	private PlatformUserCompanyMapper platformUserCompanyMapper;
	
	public PlatformOrder selectByPrimaryKey(String id){
		return orderMapper.selectByPrimaryKey(Long.parseLong(id));
	}
	
	public PlatformOrder selectByOrderNumber(String orderNumber){
		PlatformOrder platformOrder = orderMapper.selectByOrderNumber(orderNumber);
		if(platformOrder != null){
			platformOrder.setConsignorProvinceVal(xzqhServiceImpl.selectValueById(platformOrder.getConsignorProvince()).getName());
			platformOrder.setConsignorCityVal(xzqhServiceImpl.selectValueById(platformOrder.getConsignorCity()).getName());
			platformOrder.setConsignorCountyVal(xzqhServiceImpl.selectValueById(platformOrder.getConsignorCounty()).getName());
			platformOrder.setConsigneeProvinceVal(xzqhServiceImpl.selectValueById(platformOrder.getConsigneeProvince()).getName());
			platformOrder.setConsigneeCityVal(xzqhServiceImpl.selectValueById(platformOrder.getConsigneeCity()).getName());
			platformOrder.setConsigneeCountyVal(xzqhServiceImpl.selectValueById(platformOrder.getConsigneeCounty()).getName());
			platformOrder.setVehicleTypeVal(dictionaryService.selectByPrimary(platformOrder.getVehicleType()).getName());
		}
		return platformOrder;
	}
	
	public Result selectOrderByParams(SearchParams params){
		
		Result result = new Result();
		
		List<PlatformOrder> orders = orderMapper.selectByParams(params);
		if(orders != null){
			for (PlatformOrder order : orders) {
				OrderStatusEnum[] enums = OrderStatusEnum.values();
				for (OrderStatusEnum orderStatusEnum : enums) {
					if(order.getState() == orderStatusEnum.getValue()){
						order.setStatusName(orderStatusEnum.getName());
						continue;
					}
				}
				PayModeEnum[] payModeEnums = PayModeEnum.values();
				for (PayModeEnum payModeEnum : payModeEnums) {
					if(order.getPayType() == payModeEnum.getValue()){
						order.setPayTypeName(payModeEnum.getName());
						continue;
					}
				}
				PlatformOrderTracking orderTracking = new PlatformOrderTracking();
				orderTracking.setCondition(order.getOrderNumber());
				List<PlatformOrderFollow> orderFollows = platformOrderFollowMapper.selectOrderFollow(orderTracking);
				if(orderFollows != null && orderFollows.size() > 0){
					order.setOperatePerson(orderFollows.get(orderFollows.size() - 1).getOperatePerson());
				}
				// 计算未付费用
				double unPrepaidCost = 0;
				Double totalCost = order.getTotalCost();
				Double takeCargoCost = order.getTakeCargoCost();
				if(totalCost != null){
					if(takeCargoCost == null){
						unPrepaidCost = totalCost;
					}else{
						unPrepaidCost = totalCost - takeCargoCost;
					}
				}
				order.setUnPrepaidCost(Double.parseDouble(String.format("%.2f", unPrepaidCost)));
				// 设置异常状态
				String wayBillNumber = order.getWayBillNumber();
				if(StringUtils.isNotEmpty(wayBillNumber)){
					// 查询运单 
					WayBillOrder wayBillOrder = wayBillOrderMapper.selectByWayBillNumber(wayBillNumber);
					if(wayBillOrder != null){
						order.setExceptionStatus(wayBillOrder.getExceptionStatus());
						// 设置 相应的异常信息
						Integer exceptionStatus = wayBillOrder.getExceptionStatus();
						if(exceptionStatus == ExceptionStatusEnum.REGISTER.getValue()){
							order.setExceptionRegister(true);
						}else if(exceptionStatus == ExceptionStatusEnum.HANDLER.getValue()){
							order.setExceptionHandler(true);
						}
					}else{
						LadingOrder ladingOrder = ladingOrderMapper.selectByWayBillNumber(wayBillNumber);
						order.setExceptionStatus(ladingOrder.getExceptionStatus());
						// 设置 相应的异常信息
						Integer exceptionStatus = ladingOrder.getExceptionStatus();
						if(exceptionStatus == ExceptionStatusEnum.REGISTER.getValue()){
							order.setExceptionRegister(true);
						}else if(exceptionStatus == ExceptionStatusEnum.HANDLER.getValue()){
							order.setExceptionHandler(true);
						}
					}
				}
			}
		}
		int count = orderMapper.selectByParamsCount(params);
		result.setRows(orders);
		result.setResults(count);
		return result;
	}
	
	/**
	 * 获取我要发货
	 */
	public Page<?> selectBySelectedDeliver(
			PlatformDeliverGoods deliverGoods, Page<?> page) {
		int results = orderMapper.countRows(deliverGoods);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<PlatformDeliverGoods> platformDeliverGoods = orderMapper.selectBySelectedCondition(deliverGoods, page);
		/*for (int i = 0; i < platformDeliverGoods.size(); i++) {
			platformDeliverGoods.get(i).setStartProvince(xzqhServiceImpl.selectValueById(platformDeliverGoods.get(i).getStartProvince()).getName());
			platformDeliverGoods.get(i).setStartCity(xzqhServiceImpl.selectValueById(platformDeliverGoods.get(i).getStartCity()).getName());
			platformDeliverGoods.get(i).setStartCounty(xzqhServiceImpl.selectValueById(platformDeliverGoods.get(i).getStartCounty()).getName());
			platformDeliverGoods.get(i).setEndProvince(xzqhServiceImpl.selectValueById(platformDeliverGoods.get(i).getEndProvince()).getName());
			platformDeliverGoods.get(i).setEndCity(xzqhServiceImpl.selectValueById(platformDeliverGoods.get(i).getEndCity()).getName());
			platformDeliverGoods.get(i).setEndCounty(xzqhServiceImpl.selectValueById(platformDeliverGoods.get(i).getEndCounty()).getName());
			platformDeliverGoods.get(i).setTransportMode(dictionaryService.selectByPrimaryId(Long.parseLong(platformDeliverGoods.get(i).getTransportMode()), DictionaryType.TRANSPORT_MODE).getName());
			platformDeliverGoods.get(i).setCountOrder(orderMapper.countOrderLine(platformDeliverGoods.get(i).getId()));
			platformDeliverGoods.get(i).setCountOrderEvaluation(evaluationMapper.countLineEvaluation(platformDeliverGoods.get(i).getId()));
		}*/
		for(int i = 0; i < platformDeliverGoods.size(); i++){
			String startProvince = platformDeliverGoods.get(i).getStartProvince();
			if(isInteger(startProvince)){
				platformDeliverGoods.get(i).setStartProvince(xzqhServiceImpl.selectValueById(startProvince).getName());
			}
			String startCity = platformDeliverGoods.get(i).getStartCity();
			if(isInteger(startCity)){
				platformDeliverGoods.get(i).setStartCity(xzqhServiceImpl.selectValueById(startCity).getName());
			}
			String startCounty = platformDeliverGoods.get(i).getStartCounty();
			if(isInteger(startCounty)){
				platformDeliverGoods.get(i).setStartCounty(xzqhServiceImpl.selectValueById(startCounty).getName());
			}
			String endProvince = platformDeliverGoods.get(i).getEndProvince();
			if(isInteger(endProvince)){
				platformDeliverGoods.get(i).setEndProvince(xzqhServiceImpl.selectValueById(endProvince).getName());
			}
			String endCity = platformDeliverGoods.get(i).getEndCity();
			if(isInteger(endCity)){
				platformDeliverGoods.get(i).setEndCity(xzqhServiceImpl.selectValueById(endCity).getName());
			}
			String endCounty = platformDeliverGoods.get(i).getEndCounty();
			if(isInteger(endCounty)){
				platformDeliverGoods.get(i).setEndCounty(xzqhServiceImpl.selectValueById(endCounty).getName());
			}
			platformDeliverGoods.get(i).setTransportMode(dictionaryService.selectByPrimaryId(Long.parseLong(platformDeliverGoods.get(i).getTransportMode()), DictionaryType.TRANSPORT_MODE).getName());
			platformDeliverGoods.get(i).setServerType(dictionaryService.selectByPrimaryId(Long.parseLong(platformDeliverGoods.get(i).getServerType()), DictionaryType.SERVER_TYPE).getName());
			platformDeliverGoods.get(i).setCountOrder(orderMapper.countOrderLine(platformDeliverGoods.get(i).getId()));
			platformDeliverGoods.get(i).setCountOrderEvaluation(evaluationMapper.countLineEvaluation(platformDeliverGoods.get(i).getId()));
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, platformDeliverGoods);
		page.setParams(map);
		return page;
	}
	
	private boolean isInteger(String value){
		try {
			Integer.parseInt(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 获取我的订单
	 */
	public Page<?> selectBySelectedOrder(
			PlatformMineOrder mineOrder, Page<?> page) {
		int results = orderMapper.countRowsOrder(mineOrder);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<PlatformMineOrder> mineOrders = orderMapper.selectBySelectedConditionOrder(mineOrder, page);
		for (int i = 0; i < mineOrders.size(); i++) {
			mineOrders.get(i).setStartProvince(xzqhServiceImpl.selectValueById(mineOrders.get(i).getStartProvince()).getName());
			mineOrders.get(i).setStartCity(xzqhServiceImpl.selectValueById(mineOrders.get(i).getStartCity()).getName());
			mineOrders.get(i).setStartCounty(xzqhServiceImpl.selectValueById(mineOrders.get(i).getStartCounty()).getName());
			mineOrders.get(i).setEndProvince(xzqhServiceImpl.selectValueById(mineOrders.get(i).getEndProvince()).getName());
			mineOrders.get(i).setEndCity(xzqhServiceImpl.selectValueById(mineOrders.get(i).getEndCity()).getName());
			mineOrders.get(i).setEndCounty(xzqhServiceImpl.selectValueById(mineOrders.get(i).getEndCounty()).getName());
			//mineOrders.get(i).setState(dictionaryService.selectByPrimaryId(Long.parseLong(mineOrders.get(i).getState()),DictionaryType.ORDER_STATE).getName());
			if(evaluationMapper.selectEvaluation(mineOrders.get(i).getOrderNumber()) >0){
				mineOrders.get(i).setIsEvaluation(Const.PLATFORMUSER_ORDER_EVALUATION_1);
			}else{
				mineOrders.get(i).setIsEvaluation(Const.PLATFORMUSER_ORDER_EVALUATION_0);
			}
			if(complainMapper.selectComplain(mineOrders.get(i).getOrderNumber()) >0){
				mineOrders.get(i).setIsComplain(Const.PLATFORMUSER_ORDER_COMPLAIN_1);
			}else{
				mineOrders.get(i).setIsComplain(Const.PLATFORMUSER_ORDER_COMPLAIN_0);
			}
			OrderStatusEnum[] orderStatusEnums = OrderStatusEnum.values();
			for (OrderStatusEnum orderStatusEnum : orderStatusEnums) {
				if(Integer.parseInt(mineOrders.get(i).getState()) == orderStatusEnum.getValue()){
					mineOrders.get(i).setState(orderStatusEnum.getName());
					break;
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, mineOrders);
		page.setParams(map);
		return page;
	}
	
	/**
	 * 生成 订单号
	 * @param lineId
	 * @return
	 */
	private String generateOrderNumber(Long lineId){
		// 获取公司组织代码    网点编号
		String orderNumber = null;
	 	OutletsInfo outletsInfo = outletsInfoMapper.selectOutletsAndCompanyCodeByLineId(lineId);
	 	String companyCode = outletsInfo.getCompanyCode();
		//String outletsNumber = outletsInfo.getOutletsNumber();
		String dateStr = DateTools.getCurrentDateStr("yyMMdd");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("numberType", OrderNumberTypeEnum.PLATFORM_ORDER_NUMBER.getValue());
		params.put("companyCode", companyCode);
		//params.put("outletsNumber", outletsNumber);
		OrderSerialNumber serialNumber = new OrderSerialNumber();
		serialNumber.setNumberType(OrderNumberTypeEnum.PLATFORM_ORDER_NUMBER.getValue());
		serialNumber.setCompanyCode(companyCode);
		//serialNumber.setOutletsNumber(outletsNumber);
		serialNumber.setDateStr(dateStr);
		
		OrderSerialNumber orderSerialNumber = orderSerialNumberMapper.selectByParams(params);
		if(orderSerialNumber == null){
			String newNumber = "001";
			orderNumber = SystemConstant.PLATFORM_ORDER_NUMBER_PREFIX + companyCode
					 + dateStr + newNumber;
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
			orderNumber = SystemConstant.PLATFORM_ORDER_NUMBER_PREFIX + companyCode
					 + dateStr + newNumber;
			serialNumber.setSerialNumber(newNumber);
			serialNumber.setId(orderSerialNumber.getId());
			orderSerialNumberMapper.updateByPrimaryKeySelective(serialNumber);
		}
		return orderNumber;
	}
	
	/**
	 * 订单 下单
	 * @param order
	 * @param orderAddServer
	 * @param cargoInfos
	 */
	public Result saveOrder(String order, String orderAddServer, String cargoInfos, PlatformUser user){
		
		Result result = new Result();
		
		PlatformOrder platformOrder = JSONObject.parseObject(order, PlatformOrder.class);
		// 生成订单号
		String orderNumber = generateOrderNumber(platformOrder.getTmsLineId());
		platformOrder.setOrderNumber(orderNumber);
		
		PlatformOrderAdditionalServer additionalServer = JSONObject.parseObject(orderAddServer, PlatformOrderAdditionalServer.class);
		
		Integer isCollectionDelivery = additionalServer.getIsCollectionDelivery();
		if(isCollectionDelivery != null && isCollectionDelivery == Const.PLATFORM_ORDER_IS_COLLECTION_DELIVERY){
			// 用户选择 代收货款 则生成代收货款单
			addAgencyFund(orderNumber, additionalServer.getCollectionDeliveryMoney());
		}
		
		// 构建账单对象
		PlatformOrderBill platformOrderBill = buildPaltformOrderBill(additionalServer, platformOrder);
		orderBillMapper.insert(platformOrderBill);
		
		platformOrder.setState(OrderStatusEnum.WAIT_ACCEPT.getValue()); // 设置订单状态
		platformOrder.setOrderTime(new Date());
		platformOrder.setEstimateTotal(platformOrderBill.getEstimateTotalCost());
		if(platformOrder.getSendCargoType() == 1 && platformOrder.getPayType() == 0){
			Double takeCargoCost = platformOrderBill.getEstimateTakeCargoCost();
			platformOrder.setTakeCargoCost(takeCargoCost); // 设置 提货费
			platformOrder.setIsDraft(0);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("money", takeCargoCost);
			map.put("orderNumber", orderNumber);
			result.setData(map);
		}
		
		//platformOrder.setAdvancePrice(platformOrderBill.getTotalCost());
		// 插入订单数据
		int rows = orderMapper.insertSelective(platformOrder);
		
		// 添加 订单跟踪记录
		PlatformOrderFollow platformOrderFollow = new PlatformOrderFollow();
		platformOrderFollow.setOrderNumber(orderNumber);
		platformOrderFollow.setStatus(OrderStatusEnum.WAIT_ACCEPT.getValue());
		platformOrderFollow.setHandleTime(new Date());
		platformOrderFollow.setHandleInfo("预约订单");
		String trueName = user.getTrueName();
		if(StringUtils.isEmpty(trueName)){
			trueName = user.getLoginName();
		}
		platformOrderFollow.setOperatePerson(trueName);
		platformOrderFollowMapper.insert(platformOrderFollow);
		
		if(platformOrder.getIsCommonConsignor() == 1){ // 选择保存为常用发货人, 添加常用发货人
			PlatformUserCommonContact commonContact = new PlatformUserCommonContact();
			commonContact.setContactsName(platformOrder.getConsignorName());
			commonContact.setPhoneNumber(platformOrder.getConsignorPhoneNumber());
			commonContact.setTelephone(platformOrder.getConsignorTelephone());
			commonContact.setAddress(platformOrder.getConsignorAddress());
			commonContact.setProvince(platformOrder.getConsignorProvince());
			commonContact.setCity(platformOrder.getConsignorCity());
			commonContact.setCounty(platformOrder.getConsignorCounty());
			commonContact.setContactsType(0);
			commonContact.setUserId(user.getId());
			platformUserCommonContactService.insertCommonContact(commonContact);
		}
		if(platformOrder.getIsCommonConsignee() == 1){ // 选择保存为常用收货人, 添加常用收货人
			PlatformUserCommonContact commonContact = new PlatformUserCommonContact();
			commonContact.setContactsName(platformOrder.getConsigneeName());
			commonContact.setPhoneNumber(platformOrder.getConsigneePhoneNumber());
			commonContact.setTelephone(platformOrder.getConsigneeTelephone());
			commonContact.setAddress(platformOrder.getConsigneeAddress());
			commonContact.setProvince(platformOrder.getConsigneeProvince());
			commonContact.setCity(platformOrder.getConsigneeCity());
			commonContact.setCounty(platformOrder.getConsigneeCounty());
			commonContact.setContactsType(1);
			commonContact.setUserId(user.getId());
			platformUserCommonContactService.insertCommonContact(commonContact);
		}
		if(platformOrder.getIsCommonDriver() == 1){
			PlatformUserCommonDriver commonDriver = new PlatformUserCommonDriver();
			String driverName = platformOrder.getDriverName();
			String driverPhone = platformOrder.getDriverPhone();
			String vehicleNumber = platformOrder.getVehicleNumber();
			if(StringUtils.isNotEmpty(driverName) && StringUtils.isNotEmpty(driverPhone) && StringUtils.isNotEmpty(vehicleNumber)){
				commonDriver.setDriverName(driverName);
				commonDriver.setPhoneNumber(driverPhone);
				commonDriver.setLicenseNumber(vehicleNumber);
				commonDriver.setVehicleType(platformOrder.getVehicleType() + "");
				commonDriver.setUserId(user.getId());
				platformUserCommonDriverService.insertCommonDriver(commonDriver);
			}
		}
		
		Long orderId = platformOrder.getId();
		
		additionalServer.setOrderId(orderId);
		// 插入订单增值服务数据
		additionalServerMapper.insert(additionalServer);
		
		LineInfo lineInfo = lineInfoMapper.selectByPrimaryKey(platformOrder.getTmsLineId());
		List<PlatformOrderCargo> orderCargos = JSONArray.parseArray(cargoInfos, PlatformOrderCargo.class);
		for (PlatformOrderCargo orderCargo : orderCargos) {
			
			System.out.println("----------------------------------------------------------------"+orderCargo.getGoodsType());
			Double weightPrice = 0D;
			Integer number = orderCargo.getNumber();
			if(number == null){
				number = orderCargo.getSetNumber();
			}
			Double totalWeight = orderCargo.getSingleWeight() * number;
			if(totalWeight <= 1){
				weightPrice = totalWeight * lineInfo.getHeavyCargoPriceLow();
			}else if(totalWeight > 1 && totalWeight <= 3){
				weightPrice = totalWeight * lineInfo.getHeavyCargoPriceMid();
			}else{
				weightPrice = totalWeight * lineInfo.getHeavyCargoPriceHigh();
			}
			Double volumePrice = 0D;
			Double totalVolume = orderCargo.getSingleVolume() * number;
			if(totalWeight <= 1){
				volumePrice = totalVolume * lineInfo.getBulkyCargoPriceLow();
			}else if(totalWeight > 1 && totalWeight <= 3){
				volumePrice = totalVolume * lineInfo.getBulkyCargoPriceMid();
			}else{
				volumePrice = totalVolume * lineInfo.getBulkyCargoPriceHigh();
			}
			if(weightPrice > volumePrice){
				orderCargo.setCountCostMode(CountCostModeEnum.WEIGHT.getValue());
			}else{
				orderCargo.setCountCostMode(CountCostModeEnum.VOLUME.getValue());
			}
			orderCargo.setOrderId(orderId);
			
			Integer isCommon = orderCargo.getIsCommon();
			if(isCommon == 1){ // 如果选择常用货物 则添加该货物到常用货物
				PlatformUserCommonCargo commonCargo = new PlatformUserCommonCargo();
				commonCargo.setCargoName(orderCargo.getName());
				commonCargo.setSingleWeight(orderCargo.getSingleWeight());
				commonCargo.setSingleVolume(orderCargo.getSingleVolume());
				commonCargo.setPackingInfo(orderCargo.getPackageType() + "");
				commonCargo.setCargoBrand(orderCargo.getBrand());
				commonCargo.setModel(orderCargo.getModel());
				commonCargo.setState(1);
				commonCargo.setCreateTime(new Date());
				commonCargo.setUserId(user.getId());
				platformUserCommonCargoService.insertCommonCargo(commonCargo);
			}
		}
		// 插入订单货物数据
		orderCargoMapper.batchInsert(orderCargos);
		
		if(rows > 0){
			result.setResult(true);
		}else{
			result.setResult(false);
		}
		return result;
	}
	
	/**
	 * 添加 代收货款信息
	 * @param orderNumber
	 * @param agencyFund
	 */
	private void addAgencyFund(String orderNumber, Double agencyFund){
		if(agencyFund == null){
			return;
		}
		PlatformOrderAgencyFund orderAgencyFund = new PlatformOrderAgencyFund();
		orderAgencyFund.setOrderNumber(orderNumber);
		orderAgencyFund.setAgencyFund(agencyFund);
		orderAgencyFund.setReceivedFund(0D);
		orderAgencyFund.setUncollectedFund(agencyFund);
		orderAgencyFund.setOperateTime(new Date());
		orderAgencyFund.setState(Const.PLATFORMUSER_ORDER_AGENCY_FUND_STATE_0);
		agencyFundMapper.inserOrderAgencyFund(orderAgencyFund);
	}
	
	/**
	 * 保存 支付提货费
	 * @param orderNumber
	 * @param money
	 */
	public int savePayTakeCargoCost(String orderNumber){
		// TODO 调用银行接口 支付 费用
		// TODO 添加支付记录
		// 修改订单
		PlatformOrder platformOrder = new PlatformOrder();
		platformOrder.setOrderNumber(orderNumber);
		platformOrder.setIsDraft(1);
		return orderMapper.updateByOrderNumberSelective(platformOrder);
	}
	
	/**
	 * 构建账单对象
	 * @param additionalServer 增值服务对象
	 * @param platformOrder 订单对象
	 * @return
	 */
	private PlatformOrderBill buildPaltformOrderBill(PlatformOrderAdditionalServer additionalServer, PlatformOrder platformOrder){
		// 计算运费
		Double freight = 0.0;

		LineInfo lineInfo = lineInfoMapper.selectByPrimaryKey(platformOrder.getTmsLineId());
		
		Double weight_freight = 0D;
		
		double totalWeight = platformOrder.getEstimateWeight();
		if(totalWeight <= 1){
			weight_freight = lineInfo.getHeavyCargoPriceLow() * totalWeight;
		}else if(totalWeight > 1 && totalWeight <= 3){
			weight_freight = lineInfo.getHeavyCargoPriceMid() * totalWeight;
		}else{
			weight_freight = lineInfo.getHeavyCargoPriceHigh() * totalWeight;
		}
		Double volume_freight = 0D;
		double totalVolume = platformOrder.getEstimateVolume();
		if(totalVolume <= 1){
			volume_freight = lineInfo.getBulkyCargoPriceLow() * totalVolume;
		}else if(totalVolume > 1 && totalVolume <= 3){
			volume_freight = lineInfo.getBulkyCargoPriceMid() * totalVolume;
		}else{
			volume_freight = lineInfo.getBulkyCargoPriceHigh() * totalVolume;
		}

		if(weight_freight > volume_freight){
			freight = weight_freight;
		}else{
			freight = volume_freight;
		}
		
		OutletsInfo outletsInfo = outletsInfoMapper.selectByPrimaryKey(lineInfo.getOutletsId());
		
		AdditionalServerConf additionalServerConf = additionalServerConfMapper.selectByOutletsId(outletsInfo.getId());
		// 装货费用 
		Double loadCargoPrice = 0.0;
		Integer isLoadCargo = additionalServer.getIsLoadCargo();
		if(isLoadCargo != null && isLoadCargo == 1){ // 选择装货
			// 计算不上楼费用
			Double weigthCargoPrice = additionalServerConf.getHeavyCargo() * totalWeight;
			Double volumeCargoPrice = additionalServerConf.getBulkyCargo() * totalVolume;
			Double lowestPrice = additionalServerConf.getNoUpstairsLowPrice(); // 获取TMS增值服务配置表的最低价格
			Double[] price = {weigthCargoPrice,volumeCargoPrice,lowestPrice};
			loadCargoPrice = getDoubleMax(price);
			// 如果上楼 计算上楼收费
			Integer loadCargoFloor = additionalServer.getLoadCargoFloor();
			if(loadCargoFloor > 0){
				// 判断是否有电梯
				Integer loadCargoIsElevator = additionalServer.getLoadCargoIsElevator();
				if(loadCargoIsElevator != null && loadCargoIsElevator == 1){ // 有电梯
					loadCargoPrice += loadCargoPrice * additionalServerConf.getElevatorAdditional() / 100;
				}else{ // 无电梯
					loadCargoPrice += loadCargoPrice * additionalServerConf.getNoElevatorAdditional() * loadCargoFloor / 100;
				}
				double lowestPriceUpstairs = additionalServerConf.getGoUpstairsLowPrice();
				if(lowestPriceUpstairs > loadCargoPrice){
					loadCargoPrice = lowestPriceUpstairs;
				}
			}
		}
		// 卸货费用 
		Double unloadCargoPrice = 0.0;
		Integer isUnLoadCargo = additionalServer.getIsUnloadCargo();
		if(isUnLoadCargo != null && isUnLoadCargo == 1){ // 选择卸货
			// 计算不上楼费用
			Double weigthCargoPrice = additionalServerConf.getHeavyCargo() * totalWeight;
			Double volumeCargoPrice = additionalServerConf.getBulkyCargo() * totalVolume;
			Double lowestPrice = additionalServerConf.getNoUpstairsLowPrice(); // 获取TMS增值服务配置表的最低价格
			Double[] price = {weigthCargoPrice,volumeCargoPrice,lowestPrice};
			unloadCargoPrice = getDoubleMax(price);
			// 如果上楼 计算上楼收费
			Integer unloadCargoFloor = additionalServer.getUnloadCargoFloor();
			if(unloadCargoFloor > 0){
				// 判断是否有电梯
				Integer unloadCargoIsElevator = additionalServer.getUnloadCargoIsElevator();
				if(unloadCargoIsElevator != null && unloadCargoIsElevator == 1){ // 有电梯
					unloadCargoPrice += unloadCargoPrice * additionalServerConf.getElevatorAdditional() / 100;
				}else{ // 无电梯
					unloadCargoPrice += unloadCargoPrice * additionalServerConf.getNoElevatorAdditional() * unloadCargoFloor / 100;
				}
				double lowestPriceUpstairs = additionalServerConf.getGoUpstairsLowPrice();
				if(lowestPriceUpstairs > unloadCargoPrice){
					unloadCargoPrice = lowestPriceUpstairs;
				}
			}
		}
		
		// 保险费
		Double insurance = 0D;
		Double insuranceMoney = platformOrder.getInsuranceMoney();
		if(insuranceMoney != null){
			insurance = insuranceMoney * additionalServerConf.getLineInsuranceRatio();
			insurance = Math.ceil(insurance / 100);
		}
		// 代收货款手续费
		Double agencyFundPoundage = 0D;
		Integer isCollectionDelivery = additionalServer.getIsCollectionDelivery();
		if(isCollectionDelivery != null && isCollectionDelivery == 1){
			Double collectionDeliveryMoney = additionalServer.getCollectionDeliveryMoney();
			if(collectionDeliveryMoney != null){
				agencyFundPoundage = collectionDeliveryMoney * additionalServerConf.getCollectionDeliveryRatio() / 100;
			}
		}
		// 提货费
		Double takeCargoCost = 0D;
		if(platformOrder.getSendCargoType() == 1){ // 选择上门取货  计算提货费
			List<OutletsPriceRangeConf> outletsPriceRangeConfs = outletsPriceRangeConfService.selectByOutletsId(lineInfo.getStartOutlets());
			if(outletsPriceRangeConfs != null && outletsPriceRangeConfs.size() > 0){
				for (OutletsPriceRangeConf outletsPriceRangeConf : outletsPriceRangeConfs) {
					if(platformOrder.getConsignorCounty().equals(outletsPriceRangeConf.getCounty())){
						double weightCostTemp = totalWeight * outletsPriceRangeConf.getWeight();
						double volumeCostTmep = totalVolume * outletsPriceRangeConf.getVolume();
						if(weightCostTemp > volumeCostTmep){
							takeCargoCost = weightCostTemp;
						}else{
							takeCargoCost = volumeCostTmep;
						}
					}
				}
			}
		}
		// 送货费
		Double sendCargoCost = 0D;
		List<OutletsPriceRangeConf> outletsPriceRangeConfs2 = outletsPriceRangeConfService.selectByOutletsId(lineInfo.getEndOutlets());
		if(outletsPriceRangeConfs2 != null && outletsPriceRangeConfs2.size() > 0){
			for (OutletsPriceRangeConf outletsPriceRangeConf : outletsPriceRangeConfs2) {
				if(platformOrder.getConsigneeCounty().equals(outletsPriceRangeConf.getCounty())){
					double weightCostTemp = totalWeight * outletsPriceRangeConf.getWeight();
					double volumeCostTmep = totalVolume * outletsPriceRangeConf.getVolume();
					if(weightCostTemp > volumeCostTmep){
						sendCargoCost = weightCostTemp;
					}else{
						sendCargoCost = volumeCostTmep;
					}
				}
			}
		}
		
		freight = convertDouble2Decimal(freight);
		takeCargoCost = convertDouble2Decimal(takeCargoCost);
		sendCargoCost = convertDouble2Decimal(sendCargoCost);
		loadCargoPrice = convertDouble2Decimal(loadCargoPrice);
		unloadCargoPrice = convertDouble2Decimal(unloadCargoPrice);
		insurance = convertDouble2Decimal(insurance);
		agencyFundPoundage = convertDouble2Decimal(agencyFundPoundage);

		Double estimateTotal = freight + takeCargoCost + sendCargoCost + loadCargoPrice + unloadCargoPrice + insurance + agencyFundPoundage;
		
		PlatformOrderBill platformOrderBill = new PlatformOrderBill();
		platformOrderBill.setOrderNumber(platformOrder.getOrderNumber());
		platformOrderBill.setEstimateFreight(freight); // 预估运费
		platformOrderBill.setEstimateTakeCargoCost(takeCargoCost); // 预约提货费
		platformOrderBill.setEstimateSendCargoCost(sendCargoCost); // 送货费
		platformOrderBill.setEstimateLoadCargoCost(loadCargoPrice); // 装货费
		platformOrderBill.setEstimateUnloadCargoCost(unloadCargoPrice); // 卸货费
		platformOrderBill.setInsurance(insurance); // 保险费
		platformOrderBill.setAgencyFundPoundage(agencyFundPoundage); // 代收货款 手续费
		platformOrderBill.setEstimateTotalCost(convertDouble2Decimal(estimateTotal));
		platformOrderBill.setIsConfirm(0);
		
		return platformOrderBill;
	}
	
	/**
	 * 四舍五入 保留两位小数
	 * @param value
	 * @return
	 */
	private double convertDouble2Decimal(Double value){
		if(value == null){
			return 0D;
		}
		return Double.parseDouble(new java.text.DecimalFormat("#.00").format(value));
	}
	
	private double getDoubleMax(Double[] d){
		for(int i = 0; i < d.length - 1; i++){
			for (int j = 0; j < d.length - i - 1; j++) {
				if(d[j] < d[j + 1]){
					double temp = d[j];
					d[j] = d[j + 1];
					d[j + 1] = temp;
				}
			}
		}
		return d[0];
	}
	
	/**
	 * 接单 
	 * @param orderNumbers
	 * @return
	 */
	public int saveReceiveOrder(String orderNumbers, User user){
		List<String> orderNumberList = JSONArray.parseArray(orderNumbers, String.class);
		int rows = 0;
		for (String orderNumber : orderNumberList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderNumber", orderNumber);
			map.put("status", OrderStatusEnum.ALREADY_ACCEPT.getValue());
			rows += orderMapper.updateOrderStatusByOrderNumber(map);
			
			// 添加订单跟踪记录
			PlatformOrderFollow orderFollow = new PlatformOrderFollow();
			orderFollow.setOrderNumber(orderNumber);
			orderFollow.setStatus(OrderStatusEnum.ALREADY_ACCEPT.getValue());
			orderFollow.setHandleTime(new Date());
			orderFollow.setHandleInfo("接单");
			orderFollow.setOperatePerson(user.getTrueName());
			platformOrderFollowMapper.insert(orderFollow);
			
			// 发送短信
			sendMessage(orderNumber, SmsTemplateEnum.ORDER_REC_REF, "接单", null);
		}
		
		return rows;
	}
	
	/**
	 * 发送短信
	 * @param orderNumber
	 * @param smsTemplateEnum
	 */
	private void sendMessage(String orderNumber, SmsTemplateEnum smsTemplateEnum, String statusName, Double freight){
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
		smsParams.setCompany(companyName);
		smsParams.setOutletsName(outletsName);
		smsParams.setOrderStatus(statusName);
		smsParams.setFreight(freight);
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
	 * 拒绝订单
	 * @param orderNumbers
	 * @param user
	 * @return
	 */
	public int saveRefuseOrder(String orderNumber, User user){
		int rows = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNumber", orderNumber);
		map.put("status", OrderStatusEnum.REFUSED.getValue());
		rows = orderMapper.updateOrderStatusByOrderNumber(map);
		
		// 添加订单跟踪记录
		PlatformOrderFollow orderFollow = new PlatformOrderFollow();
		orderFollow.setOrderNumber(orderNumber);
		orderFollow.setStatus(OrderStatusEnum.REFUSED.getValue());
		orderFollow.setHandleTime(new Date());
		orderFollow.setHandleInfo("拒绝订单");
		orderFollow.setOperatePerson(user.getTrueName());
		platformOrderFollowMapper.insert(orderFollow);
		
		// 发送短信
		sendMessage(orderNumber, SmsTemplateEnum.ORDER_REC_REF, "拒绝订单", null);
		
		return rows;
	}
	
	/**
	 * 订单作废
	 * @param orderNumbers
	 * @param user
	 * @return
	 */
	public int saveDiscardOrder(String orderNumbers, User user){
		List<String> orderNumberList = JSONArray.parseArray(orderNumbers, String.class);
		int rows = 0;
		for (String orderNumber : orderNumberList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderNumber", orderNumber);
			map.put("status", OrderStatusEnum.DISCARDED.getValue());
			rows += orderMapper.updateOrderStatusByOrderNumber(map);
		}
		return rows;
	}
	
	/**
	 * 验证提货订单状态
	 * @param id
	 * @return
	 */
	public boolean validateOrderStatusDeliveryCargo(String orderNumber){
		PlatformOrder order = orderMapper.selectByOrderNumber(orderNumber);
		if(order.getState().equals(OrderStatusEnum.ALREADY_ACCEPT.getValue())){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取订单详情
	 * @param id
	 * @return
	 */
	public PlatformOrder selectOrderItems(long id){
		PlatformOrder platformOrder = orderMapper.selectById(id);
		if(platformOrder == null){
			return null;
		}
		platformOrder.setConsigneeProvinceVal(xzqhServiceImpl.selectValueById(platformOrder.getConsigneeProvince()).getName());
		platformOrder.setConsigneeCityVal(xzqhServiceImpl.selectValueById(platformOrder.getConsigneeCity()).getName());
		platformOrder.setConsigneeCountyVal(xzqhServiceImpl.selectValueById(platformOrder.getConsigneeCounty()).getName());
		List<PlatformOrderCargo> orderCargos = platformOrder.getPlatformOrderCargos();
		if(orderCargos != null && orderCargos.size() > 0){
			for (PlatformOrderCargo platformOrderCargo : orderCargos) {
				platformOrderCargo.setTotalWeight(platformOrderCargo.getSingleWeight());
				platformOrderCargo.setTotalVolume(platformOrderCargo.getSingleVolume());
			}
		}
		return orderMapper.selectById(id);
	}

	
	/**
	 * 用户订单数量排名
	 * @param id
	 * @return
	 */
//	public List<Map<String, Object>> getOrderUserRanking(Page<?> page){
//		List<Map<String, Object>> map  = orderMapper.getOrderUserRanking(page);
//		return map;
//	}
	public Result getOrderUserRanking(String loginName,Page<?> page){
		Result result = new Result();
		List<Map<String, Object>> map  =orderMapper.getOrderUserRanking(loginName, page);
		int results = orderMapper.getCountOrderUserRanking(loginName);
		result.setResults(results);
		result.setRows(map);
		return result;
	}
	/**
	 * 公司接收数量排名
	 * @param id
	 * @return
	 */
//	public List<Map<String, Object>> getOrderCompanyRanking(Page<?> page){
//		List<Map<String, Object>> map  = orderMapper.getOrderCompanyRanking(page);
//		return map;
//	}
	public Result getOrderCompanyRanking(String companyName,String companyCode,String loginName,Page<?> page){
		Result result = new Result();
		List<Map<String, Object>> map  =orderMapper.getOrderCompanyRanking(companyName, companyCode, loginName, page);
		int results = orderMapper.getCountOrderCompanyRanking(companyName, companyCode, loginName);
		result.setResults(results);
		result.setRows(map);
		return result;
	}
	/**
	 * 用户订单数量排名
	 * @param id
	 * @return
	 */
//    public int getCountOrderUserRanking(Page<?> page){
//    	int num  = orderMapper.getCountOrderUserRanking(page);
//    	return num;
//    }
//    
    
    /**
     * 公司接单数量排名
     * @param id
     * @return
     */
//    public int getCountOrderCompanyRanking(Page<?> page){
//    	int num  = orderMapper.getCountOrderCompanyRanking(page);
//    	return num;
//    }
	
	/**
	 * 总数 草稿
	 * @param userId
	 * @return
	 */
	public int selectOrderisDraft(Long userId){
		return orderMapper.selectOrderisDraft(userId);
	}
	/**
	 * 订单状态
	 * @param userId
	 * @param state
	 * @return
	 */
	public int selectOrderState(Long userId,Integer state){
		return orderMapper.selectOrderState(userId, state);
	}
	/**
	 * 总数 确认价格
	 * @param userId
	 * @return
	 */
	public int selectOrderFinalPrice(Long userId){
		return orderMapper.selectOrderFinalPrice(userId);
	}
	/**
	 * 总数 是否投保
	 * @param userId
	 * @return
	 */
	public int selectOrderIsInsurance(Long userId){
		return orderMapper.selectOrderIsInsurance(userId);
	}
	
	/**
	 * 获取热门专线
	 * @return
	 */
	public List<PlatformOrder> selectHotLogst(){
		List<PlatformOrder> orderList = orderMapper.selectHotLogs();
		return orderList;
	}
	
	/**
	 * 获取最近的下单记录
	 * @return
	 */
	public List<PlatformOrder> selectOrderRecently(){
		return orderMapper.selectOrderRecently();
	}

	
	/**
	 * 获取当前公司  所有城市网点信息
	 * @param companyId
	 * @param name
	 * @return
	 */
	public List<OutletsInfo> selectOutle(Long companyId,String name){
		List<OutletsInfo> outletsInfos = outletsInfoMapper.selectOutlets(companyId, name);
		for (int i = 0; i < outletsInfos.size(); i++) {
			outletsInfos.get(i).setProvinceValue(xzqhServiceImpl.selectValueById(outletsInfos.get(i).getProvince()).getName());
			outletsInfos.get(i).setCityValue(xzqhServiceImpl.selectValueById(outletsInfos.get(i).getCity()).getName());
			outletsInfos.get(i).setCountyValue(xzqhServiceImpl.selectValueById(outletsInfos.get(i).getCounty()).getName());
		}
		return outletsInfos;
	}
	
	/**
	 * 查询 订单列表 (货运交易系统)
	 * @param params
	 * @return
	 */
	public Result selectByParamsOfPlatform(OrderSearchParams params){
		List<PlatformOrder> orders = orderMapper.selectByParamsOfPlatform(params);
		if(orders != null && orders.size() > 0){
			for (PlatformOrder order : orders) {
				OrderStatusEnum[] orderStatusEnums = OrderStatusEnum.values();
				for (OrderStatusEnum orderStatusEnum : orderStatusEnums) {
					if(order.getState() == orderStatusEnum.getValue()){
						order.setStatusName(orderStatusEnum.getName());
						continue;
					}
				}
			}
		}
		
		int count = orderMapper.selectByParamsCountOfPlatform(params);
		Result result = new Result();
		result.setResults(count);
		result.setRows(orders);
		return result;
	}
	
	/**
	 * 获取订单总数
	 * @return
	 */
	public int selectOrderAmount(){
		return orderMapper.countOrder();
	}

	/**
	 * 运营货运交易系统：
	 * 获取订单按月统计统计
	 * @return
	 */
	public List<StatisticsHelper> selectOrderStatistics(){
		return orderMapper.selectOrderStatistics();
	}
	
	/**
	 *导出我的订单 
	 * @param orderNumber
	 * @return
	 */
	public List<Map<String, Object>> exportMineOrder(PlatformMineOrder mineOrder){
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		List<String> orderNumber = orderMapper.selectOrder(mineOrder);
		if(orderNumber.size() > 0){
			 Map<String, Object> map = new HashMap<String, Object>();
		     map.put("sheetName", "mineBill");
		     listmap.add(map);
			for (int i = 0; i < orderNumber.size(); i++) {
				PlatformOrder platformOrder = selectByOrderNumber(orderNumber.get(i));
				PlatformOrderAdditionalServer platformOrderAdditionalServer = platformOrderAdditionalServerService.selectByOrderNumber(orderNumber.get(i));
				List<PlatformOrderCargo> platformOrderCargos = platformOrderCargoService.selectByOrderNumber(orderNumber.get(i));
				for (int j = 0; j < platformOrderCargos.size(); j++) {
					 Map<String, Object> mapValue = new HashMap<String, Object>();
					 mapValue.put("orderNumber",platformOrder.getOrderNumber());
					 mapValue.put("fahuoxinxi","发货信息");
					 mapValue.put("consignorName",platformOrder.getConsignorName());
					 mapValue.put("consignorAddress",platformOrder.getConsignorProvinceVal()+"-"+platformOrder.getConsignorCityVal()+"-"+platformOrder.getConsignorCountyVal()+"-"+platformOrder.getConsignorAddress());
					 mapValue.put("consignorPhoneNumber",platformOrder.getConsigneePhoneNumber());
					 mapValue.put("consignorTelephone",platformOrder.getConsignorTelephone());
					 mapValue.put("chengpeixinxi","城配司机");
					 mapValue.put("driverName",platformOrder.getDriverName());
					 mapValue.put("driverPhone",platformOrder.getDriverPhone());
					 mapValue.put("vehicleNumber",platformOrder.getVehicleNumber());
					 mapValue.put("vehicleType",platformOrder.getVehicleTypeVal());
					 mapValue.put("shouhuoxinxi","收货信息");
					 mapValue.put("consigneeName",platformOrder.getConsigneeName());
					 mapValue.put("consigneeAddress",platformOrder.getConsigneeProvinceVal()+"-"+platformOrder.getConsigneeCityVal()+"-"+platformOrder.getConsigneeCountyVal()+"-"+platformOrder.getConsigneeAddress());
					 mapValue.put("consigneePhoneNumber",platformOrder.getConsigneePhoneNumber());
					 mapValue.put("consigneeTelephone",platformOrder.getConsigneeTelephone());
					 mapValue.put("huowuxinxi","货物信息");
					 mapValue.put("name",platformOrderCargos.get(j).getName());
					 mapValue.put("brand",platformOrderCargos.get(j).getBrand());
					 mapValue.put("model",platformOrderCargos.get(j).getModel());
					 mapValue.put("number",platformOrderCargos.get(j).getNumber());
					 mapValue.put("setNumber",platformOrderCargos.get(j).getSetNumber());
					 mapValue.put("singleWeight",platformOrderCargos.get(j).getSingleWeight());
					 mapValue.put("singleVolume",platformOrderCargos.get(j).getSingleVolume());
					 mapValue.put("packageTypeName",platformOrderCargos.get(j).getPackageTypeName());
					 mapValue.put("peisongfangshi","配送方式");
					 if(platformOrder.getSendCargoType() == 1){
						 mapValue.put("sendCargoType","上门取货");
						 SimpleDateFormat myFmt=new SimpleDateFormat("yy/MM/dd HH:mm");        
				         String date = myFmt.format(platformOrder.getDeliveryDate());
						 mapValue.put("deliver",date+"-"+platformOrder.getDeliveryStartTime()+"-"+platformOrder.getDeliveryEndTime());
					 }else if(platformOrder.getSendCargoType() == 0){
						 mapValue.put("sendCargoType","自送网点");
						 mapValue.put("deliver","xxxx");
					 }
					 if(platformOrder.getReceiveCargoType() == 1){
						 mapValue.put("receiveCargoType","送货上门");
					 }else if(platformOrder.getReceiveCargoType() == 0){
						 mapValue.put("receiveCargoType","客户自提"); 
					 }
					 mapValue.put("zengzhifuwu","增值服务");
					 if(null == platformOrderAdditionalServer.getIsReceipt()){
						 mapValue.put("isReceipt","否");
					 }else if(platformOrderAdditionalServer.getIsReceipt() == 1){
						 mapValue.put("isReceipt","是");
					 }else{
						 mapValue.put("isReceipt","否");
					 }
					 if(null == platformOrderAdditionalServer.getIsImExStore()){
						 mapValue.put("isImExStore","否");
					 }else if(platformOrderAdditionalServer.getIsImExStore() == 1){
						 mapValue.put("isImExStore","是");
					 }else{
						 mapValue.put("isImExStore","否");
					 }
					 if(null == platformOrderAdditionalServer.getIsLoadCargo()){
						 mapValue.put("isLoadCargo","否");
						 mapValue.put("loadCargoFloor","");
						 mapValue.put("loadCargoIsEleVator","");
					 }else if(platformOrderAdditionalServer.getIsLoadCargo() == 1){
						 mapValue.put("isLoadCargo","是");
						 mapValue.put("loadCargoFloor",platformOrderAdditionalServer.getLoadCargoFloor());
						 if(null != platformOrderAdditionalServer.getLoadCargoIsElevator() && platformOrderAdditionalServer.getLoadCargoIsElevator() == 1){
							 mapValue.put("loadCargoIsEleVator","是");
						 }else{
							 mapValue.put("loadCargoIsEleVator","否");
						 }
					 }else{
						 mapValue.put("isLoadCargo","否");
						 mapValue.put("loadCargoFloor","");
						 mapValue.put("loadCargoIsEleVator","");
					 }
					 if(null == platformOrderAdditionalServer.getIsCollectionDelivery()){
						 mapValue.put("isCollectionDelivery","否");
						 mapValue.put("collectionDeliveryMoney","");
					 }else if(platformOrderAdditionalServer.getIsCollectionDelivery() == 1){
						 mapValue.put("isCollectionDelivery","是");
						 mapValue.put("collectionDeliveryMoney",platformOrderAdditionalServer.getCollectionDeliveryMoney());
					 }else{
						 mapValue.put("isCollectionDelivery","否");
						 mapValue.put("collectionDeliveryMoney","");
					 }
					 mapValue.put("zonghexinxifuwu","是");
					 if(null == platformOrderAdditionalServer.getIsUnloadCargo()){
						 mapValue.put("isUnloadCargo","否");
						 mapValue.put("unloadCargoFloor","");
						 mapValue.put("unloadCargoIsEleVator","");
					 }else if(platformOrderAdditionalServer.getIsUnloadCargo() == 1){
						 mapValue.put("isUnloadCargo","是");
						 mapValue.put("unloadCargoFloor",platformOrderAdditionalServer.getUnloadCargoFloor());
						 if(platformOrderAdditionalServer.getUnloadCargoIsElevator() != null && platformOrderAdditionalServer.getUnloadCargoIsElevator() == 1){
							 mapValue.put("unloadCargoIsEleVator","是");
						 }else{
							 mapValue.put("unloadCargoIsEleVator","否");
						 }
						
					 }else{
						 mapValue.put("isUnloadCargo","否");
						 mapValue.put("unloadCargoFloor","");
						 mapValue.put("unloadCargoIsEleVator","");
					 }
					 mapValue.put("huowubaoxian","货物保险");
					 if(null == platformOrder.getIsInsurance()){
						 mapValue.put("isInsurance","否");
						 mapValue.put("insuranceMoney","");
					 }else if(platformOrder.getIsInsurance() == 1){
						 mapValue.put("isInsurance","是");
						 mapValue.put("insuranceMoney",platformOrder.getInsuranceMoney());
					 }else{
						 mapValue.put("isInsurance","否");
						 mapValue.put("insuranceMoney","");
					 }
					 mapValue.put("fapiaoxinxi","发票信息");
					 if(platformOrder.getReceiptType() == 0){
						 mapValue.put("receiptType","不需要");
						 mapValue.put("receiptTitle",""); 
						 mapValue.put("receiptCompanyName","");
					 }else if(platformOrder.getReceiptType() == 1){
						 mapValue.put("receiptType","普通发票");
						 if(platformOrder.getReceiptTitle() == 0){
							 mapValue.put("receiptTitle","个人");
						 }else if(platformOrder.getReceiptTitle() == 1){
							 mapValue.put("receiptTitle","单位"); 
							 mapValue.put("receiptCompanyName",platformOrder.getReceiptCompanyName()); 
						 }
					 }else if(platformOrder.getReceiptType() == 2){
						 mapValue.put("receiptType","增值税发票");
						 mapValue.put("receiptTitle",""); 
						 mapValue.put("receip tCompanyName","");
					 }
					 mapValue.put("zhifufangshi","支付方式");
					  if(platformOrder.getPayType() == 0){
						 mapValue.put("payType","现付");
					 }else if(platformOrder.getPayType() == 1){
						 mapValue.put("payType","到付");
					 }else if(platformOrder.getPayType() == 2){
						 mapValue.put("payType","使用预付款");
					 }
					 listmap.add(mapValue);
				}
			}
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
		     map.put("sheetName", "mineBill");
		     listmap.add(map);
		}
		return listmap;
	}
	
	public int selectOrderfinalPrice(){
		return orderMapper.selectPlatformOrderfinalPrice();
	}
	public int selectOrderCount(){
		return orderMapper.selectOrderCount();
	}
	/**
	 * 获取热门城市
	 * @return
	 */
	public List<PlatformHomeOrderCity> selectHomeOrderCity(){
		List<PlatformHomeOrderCity> orderCities = orderMapper.selectHomeOrderCity();
		for (int i = 0; i < orderCities.size(); i++) {
			orderCities.get(i).setStartProvinceValue(xzqhServiceImpl.selectValueById(orderCities.get(i).getStartProvince()).getName());
			orderCities.get(i).setStartCityValue(xzqhServiceImpl.selectValueById(orderCities.get(i).getStartCity()).getName());
			orderCities.get(i).setStartCountyValue(xzqhServiceImpl.selectValueById(orderCities.get(i).getStartCounty()).getName());
		}
		return orderCities;
	}
	
	/**
	 * 保存 确认订单信息
	 * @param orderCargoStr
	 * @param orderBillStr
	 * @return
	 */
	public int saveConfirmOrder(String orderCargoStr, String orderBillStr, User user){
		// 添加 订单 账单数据
		PlatformOrderBill platformOrderBill = JSONObject.parseObject(orderBillStr, PlatformOrderBill.class);
		PlatformOrder order = orderMapper.selectByOrderNumber(platformOrderBill.getOrderNumber());
		Integer payType = order.getPayType();
		if(payType != null && payType == 0){ // 支付方式为 【现付】修改 账单信息已确认
			platformOrderBill.setIsConfirm(1);
		}
		platformOrderBill.setState(0);
		platformOrderBill.setCreateTime(new Date());
		orderBillMapper.updateByOrderNumberSelective(platformOrderBill);
		// 修改 订单状态
		PlatformOrder platformOrder = new PlatformOrder();
		platformOrder.setOrderNumber(platformOrderBill.getOrderNumber());
		platformOrder.setState(OrderStatusEnum.CONFIRM_CARGO_INFO.getValue());
		int rows = orderMapper.updateByOrderNumberSelective(platformOrder);
		// 添加 订单跟踪记录
		PlatformOrderFollow platformOrderFollow = new PlatformOrderFollow();
		platformOrderFollow.setOrderNumber(platformOrderBill.getOrderNumber());
		platformOrderFollow.setStatus(OrderStatusEnum.CONFIRM_CARGO_INFO.getValue());
		platformOrderFollow.setHandleTime(new Date());
		platformOrderFollow.setHandleInfo("订单议价");
		String trueName = user.getTrueName();
		if(StringUtils.isEmpty(trueName)){
			trueName = user.getLoginName();
		}
		platformOrderFollow.setOperatePerson(trueName);
		platformOrderFollowMapper.insert(platformOrderFollow);
		// 保存确认货物信息
		List<PlatformOrderCargo> orderCargos = JSONArray.parseArray(orderCargoStr, PlatformOrderCargo.class);
		// 将货主 预约时填写的货物信息 保存到 订单货物信息临时表中
		List<PlatformOrderCargo> orderCargosTemp = orderCargoMapper.selectByOrderNumber(platformOrderBill.getOrderNumber());
		List<PlatformOrderCargoTemp> orderCargoTemps = convertToOrderCargoTemp(orderCargosTemp);
		platformOrderCargoTempMapper.batchInsert(orderCargoTemps);
		// 重新保存 确认后的订单货物信息
		orderCargoMapper.deleteByOrderNumber(platformOrderBill.getOrderNumber());
		for (PlatformOrderCargo orderCargo : orderCargos) {
			orderCargo.setId(null);
		}
		orderCargoMapper.batchInsert(orderCargos);
		
		// 发送短信
		sendMessage(platformOrderBill.getOrderNumber(), SmsTemplateEnum.NEGOTIATING_PRICES, "议价", platformOrderBill.getTotalCost());
		return rows;
	}
	
	/**
	 * 将 订单货物集合 转换成 订单货物临时集合
	 * @param orderCargos
	 * @return
	 */
	private List<PlatformOrderCargoTemp> convertToOrderCargoTemp(List<PlatformOrderCargo> orderCargos){
		if(orderCargos == null || orderCargos.size() == 0){
			return null;
		}
		List<PlatformOrderCargoTemp> orderCargoTemps = new ArrayList<PlatformOrderCargoTemp>();
		for (PlatformOrderCargo platformOrderCargo : orderCargos) {
			PlatformOrderCargoTemp orderCargoTemp = new PlatformOrderCargoTemp();
			orderCargoTemp.setName(platformOrderCargo.getName());
			orderCargoTemp.setBrand(platformOrderCargo.getBrand());
			orderCargoTemp.setModel(platformOrderCargo.getModel());
			orderCargoTemp.setPackageType(platformOrderCargo.getPackageType());
			orderCargoTemp.setNumber(platformOrderCargo.getNumber());
			orderCargoTemp.setSetNumber(platformOrderCargo.getSetNumber());
			orderCargoTemp.setSingleWeight(platformOrderCargo.getSingleWeight());
			orderCargoTemp.setSingleVolume(platformOrderCargo.getSingleVolume());
			orderCargoTemp.setCountCostMode(platformOrderCargo.getCountCostMode());
			orderCargoTemp.setOrderId(platformOrderCargo.getOrderId());
			orderCargoTemps.add(orderCargoTemp);
		}
		return orderCargoTemps;
	}
	//支付
	public boolean doOrderMoney(PlatformOrderBill bill){
			bill.setState(Const.PLATFORMUSER_ORDER_AGENCY_FUND_STATE_1);
			if(orderBillMapper.updateOrderBillState(bill)>0){
				return true;
			}
		return false;
	}
	public boolean updateOrderPayment(String orderNumber){
		if(orderMapper.updateOrderPayment(orderNumber) > 0){
			return true;
		}
		return false;
	}
	public PlatformMineOrder selectBankOrder(String orderNumber){
		return orderMapper.selectBankOrder(orderNumber);
	}
	
	public int[] syscountOrder(){
		String[] x= {"1","2","3","4","5","6","7","8","9","10","11","12"};
		int[] y =new int[12];
		List<sysVoOrderCount> counts = orderMapper.syscountOrder();
		for (int i = 0; i <x.length; i++) {
			for (int j = 0; j < counts.size(); j++) {
				if(x[i].equals(counts.get(j).getMonth())){
					y[i]=counts.get(j).getCount();
					break;
				}else{
					y[i]=0;
				}
			}
		}
		return y;
	}
}
