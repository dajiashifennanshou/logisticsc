package com.brightsoft.service.tms.platform.startsitemanager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.common.enums.CostSubjectEnum;
import com.brightsoft.common.enums.DepartListStatusEnum;
import com.brightsoft.common.enums.OrderNumberTypeEnum;
import com.brightsoft.common.enums.OrderStatusEnum;
import com.brightsoft.common.enums.PayModeEnum;
import com.brightsoft.common.enums.ReceiveMoneyCostTypeEnum;
import com.brightsoft.common.enums.ReceiveMoneyTypeEnum;
import com.brightsoft.common.enums.SmsTemplateEnum;
import com.brightsoft.common.enums.WayBillOrderStatusEnum;
import com.brightsoft.controller.vo.DepartListSearchParams;
import com.brightsoft.dao.platform.PlatformOrderFollowMapper;
import com.brightsoft.dao.platform.PlatformOrderMapper;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.dao.platform.SmsTemplateMapper;
import com.brightsoft.dao.tms.DepartListMapper;
import com.brightsoft.dao.tms.DriverInfoMapper;
import com.brightsoft.dao.tms.OrderSerialNumberMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.dao.tms.ReceiveMoneyOrderMapper;
import com.brightsoft.dao.tms.ReceiveMoneyOrderRecordMapper;
import com.brightsoft.dao.tms.UserMapper;
import com.brightsoft.dao.tms.WayBillOrderCostInfoMapper;
import com.brightsoft.dao.tms.WayBillOrderFollowMapper;
import com.brightsoft.dao.tms.WayBillOrderMapper;
import com.brightsoft.model.DepartList;
import com.brightsoft.model.DriverInfo;
import com.brightsoft.model.OrderSerialNumber;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformOrder;
import com.brightsoft.model.PlatformOrderFollow;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.ReceivablePayBill;
import com.brightsoft.model.ReceiveMoneyOrder;
import com.brightsoft.model.ReceiveMoneyOrderRecord;
import com.brightsoft.model.SmsTemplate;
import com.brightsoft.model.User;
import com.brightsoft.model.WayBillOrder;
import com.brightsoft.model.WayBillOrderFollow;
import com.brightsoft.service.base.SmsManagerService;
import com.brightsoft.service.tms.platform.financialmanager.ReceivablePayBillService;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.sms.SmsParams;

/**
 * 发车清单 service
 * @author yangshoubao
 *
 */
@Service
public class DepartListService {

	private static final Logger logger = Logger.getLogger(DepartListService.class);
	
	@Autowired
	private DepartListMapper departListMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	@Autowired
	private OrderSerialNumberMapper orderSerialNumberMapper;
	
	@Autowired
	private WayBillOrderMapper wayBillOrderMapper;
	
	@Autowired
	private WayBillOrderCostInfoMapper wayBillOrderCostInfoMapper;
	
	@Autowired
	private ReceivablePayBillService receivablePayBillService;
	
	@Autowired
	private PlatformOrderMapper platformOrderMapper;
	
	@Autowired
	private PlatformOrderFollowMapper platformOrderFollowMapper;
	
	@Autowired
	private DriverInfoMapper driverInfoMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private WayBillOrderFollowMapper wayBillOrderFollowMapper;
	
	@Autowired
	private ReceiveMoneyOrderMapper receiveMoneyOrderMapper;
	
	@Autowired
	private PlatformUserCompanyMapper userCompanyMapper;
	
	@Autowired
	private SmsManagerService smsManagerService;
	
	@Autowired
	private SmsTemplateMapper smsTemplateMapper;
	 @Autowired
	private ReceiveMoneyOrderRecordMapper receiveMoneyOrderRecordMapper;
	 
	@Autowired
	private PlatformUserCompanyMapper platformUserCompanyMapper;
	
	 public List selectExportByParams(DepartListSearchParams params) {
	      List departLists = this.departListMapper.selectExportByParams(params);
	      ArrayList result = new ArrayList();
	      HashMap sheetName = new HashMap();
	      sheetName.put("sheetName", "发车单列表");
	      result.add(sheetName);
	      if(departLists != null && departLists.size() != 0) {
	         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         Iterator var7 = departLists.iterator();

	         while(var7.hasNext()) {
	            DepartList departList = (DepartList)var7.next();
	            HashMap map = new HashMap();
	            map.put("operateTime", dateFormat.format(departList.getOperateTime()));
	            map.put("departNumber", departList.getDepartNumber());
	            map.put("startOutletsName", departList.getStartOutletsName());
	            map.put("wayOutletsName", departList.getWayOutletsName());
	            map.put("targetOutletsName", departList.getTargetOutletsName());
	            String wayBillNumbers = departList.getWayBillNumbers();
	            String[] wayBillNumberArr = wayBillNumbers.split(",");
	            ArrayList list = new ArrayList();
	            String[] agencyFundTotal = wayBillNumberArr;
	            int var14 = wayBillNumberArr.length;

	            for(int receivableTotal = 0; receivableTotal < var14; ++receivableTotal) {
	               String wayBillOrders = agencyFundTotal[receivableTotal];
	               list.add(wayBillOrders);
	            }

	            List var36 = this.wayBillOrderMapper.selectByWayBillNumbers(list);
	            double var37 = 0.0D;
	            double var38 = 0.0D;
	            if(var36 != null && var36.size() > 0) {
	               Iterator agencyFundIsCompleted = var36.iterator();

	               while(agencyFundIsCompleted.hasNext()) {
	                  WayBillOrder isCompleted = (WayBillOrder)agencyFundIsCompleted.next();
	                  Double receivedFreightTotal = isCompleted.getTotal();
	                  if(receivedFreightTotal != null) {
	                     var37 += receivedFreightTotal.doubleValue();
	                  }

	                  Double agencyFund = isCompleted.getAgencyFund();
	                  if(agencyFund != null) {
	                     var38 += agencyFund.doubleValue();
	                  }
	               }
	            }

	            boolean var39 = true;
	            boolean var40 = true;
	            double var41 = 0.0D;
	            double receivedAgencyFundTotal = 0.0D;
	            String[] var26 = wayBillNumberArr;
	            int var25 = wayBillNumberArr.length;

	            for(int var24 = 0; var24 < var25; ++var24) {
	               String wayBillNumber = var26[var24];
	               PlatformOrder platformOrder = this.platformOrderMapper.selectByWayBillNumber(wayBillNumber);
	               ReceiveMoneyOrder receiveMoneyOrder2;
	               double cashReceivedMoney;
	               double posReceivedMoney;
	               List receiveMoneyOrderRecords;
	               ReceiveMoneyOrderRecord receiveMoneyOrderRecord;
	               Iterator var35;
	               if(platformOrder != null && platformOrder.getPayType().intValue() == PayModeEnum.IMMEDIATELY_PAY.getValue()) {
	                  var39 = true;
	                  var41 += platformOrder.getTotalCost().doubleValue();
	               } else {
	                  receiveMoneyOrder2 = this.receiveMoneyOrderMapper.selectByWayBillNumberAndCostType(wayBillNumber, Integer.valueOf(ReceiveMoneyCostTypeEnum.FREIGHT.getValue()));
	                  if(receiveMoneyOrder2 == null) {
	                     var39 = false;
	                  } else {
	                     if(receiveMoneyOrder2.getStatus().intValue() == 0) {
	                        var39 = false;
	                     }

	                     cashReceivedMoney = 0.0D;
	                     posReceivedMoney = 0.0D;
	                     receiveMoneyOrderRecords = this.receiveMoneyOrderRecordMapper.selectByReceiveMoneyOrderId(receiveMoneyOrder2.getId());
	                     if(receiveMoneyOrderRecords != null && receiveMoneyOrderRecords.size() > 0) {
	                        var35 = receiveMoneyOrderRecords.iterator();

	                        while(var35.hasNext()) {
	                           receiveMoneyOrderRecord = (ReceiveMoneyOrderRecord)var35.next();
	                           if(receiveMoneyOrderRecord.getReceiveMoneyType().intValue() == ReceiveMoneyTypeEnum.CASH_RECEIVE_MONEY.getValue()) {
	                              cashReceivedMoney += receiveMoneyOrderRecord.getReceiveMoney().doubleValue();
	                           } else if(receiveMoneyOrderRecord.getReceiveMoneyType().intValue() == ReceiveMoneyTypeEnum.POS_RECEIVE_MONEY.getValue()) {
	                              posReceivedMoney += receiveMoneyOrderRecord.getReceiveMoney().doubleValue();
	                           }
	                        }
	                     }

	                     var41 += cashReceivedMoney + posReceivedMoney;
	                  }
	               }

	               receiveMoneyOrder2 = this.receiveMoneyOrderMapper.selectByWayBillNumberAndCostType(wayBillNumber, Integer.valueOf(ReceiveMoneyCostTypeEnum.AGENCY_FUND.getValue()));
	               if(receiveMoneyOrder2 == null) {
	                  var40 = false;
	               } else {
	                  if(receiveMoneyOrder2.getStatus().intValue() == 0) {
	                     var40 = false;
	                  }

	                  cashReceivedMoney = 0.0D;
	                  posReceivedMoney = 0.0D;
	                  receiveMoneyOrderRecords = this.receiveMoneyOrderRecordMapper.selectByReceiveMoneyOrderId(receiveMoneyOrder2.getId());
	                  if(receiveMoneyOrderRecords != null && receiveMoneyOrderRecords.size() > 0) {
	                     var35 = receiveMoneyOrderRecords.iterator();

	                     while(var35.hasNext()) {
	                        receiveMoneyOrderRecord = (ReceiveMoneyOrderRecord)var35.next();
	                        if(receiveMoneyOrderRecord.getReceiveMoneyType().intValue() == ReceiveMoneyTypeEnum.CASH_RECEIVE_MONEY.getValue()) {
	                           cashReceivedMoney += receiveMoneyOrderRecord.getReceiveMoney().doubleValue();
	                        } else if(receiveMoneyOrderRecord.getReceiveMoneyType().intValue() == ReceiveMoneyTypeEnum.POS_RECEIVE_MONEY.getValue()) {
	                           posReceivedMoney += receiveMoneyOrderRecord.getReceiveMoney().doubleValue();
	                        }
	                     }
	                  }

	                  receivedAgencyFundTotal += cashReceivedMoney + posReceivedMoney;
	               }

	               if(var38 == 0.0D) {
	                  var40 = true;
	               }
	            }

	            map.put("receivableTotal", Double.valueOf(var37));
	            map.put("receivedFreightTotal", Double.valueOf(var41));
	            if(var39) {
	               map.put("isCompletedFreight", "是");
	            } else {
	               map.put("isCompletedFreight", "否");
	            }

	            map.put("balanceFreight", Double.valueOf(var37 - var41));
	            map.put("agencyFundTotal", Double.valueOf(var38));
	            map.put("receivedAgencyFundTotal", Double.valueOf(receivedAgencyFundTotal));
	            if(var40) {
	               map.put("isCompletedAgencyFund", "是");
	            } else {
	               map.put("isCompletedAgencyFund", "否");
	            }

	            map.put("balanceAgencyFund", Double.valueOf(var38 - receivedAgencyFundTotal));
	            map.put("vehicleNumber", departList.getVehicleNumber());
	            map.put("driverName", departList.getDriverName());
	            map.put("driverPhone", departList.getDriverPhone());
	            map.put("shouldPayDriverCost", departList.getShouldPayDriverCost());
	            result.add(map);
	         }

	         return result;
	      } else {
	         return result;
	      }
	   }
	/**
	 * 保存发车清单信息
	 * @param departList
	 * @return
	 */
	public int saveDepartListInfo(DepartList departList){
		
		departList.setOperateTime(new Date());
		departList.setStatus(DepartListStatusEnum.LADING.getValue());
		departList.setStartTime(DateTools.string2Date(departList.getStartTimeStr()));
		departList.setEndTime(DateTools.string2Date(departList.getEndTimeStr()));
		departList.setIsCompleted(0);
		
		String wayBillNumbers = departList.getWayBillNumbers();
		List<String> wayBillNumberList = new ArrayList<String>();
		String[] wayBillNumberArr = wayBillNumbers.split(",");
		for (String wayBillNumber : wayBillNumberArr) {
			wayBillNumberList.add(wayBillNumber);
		}
		//List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectByWayBillNumbers(wayBillNumberList);
		// 计算发车单 应收运费合计
		/*double receivableTotal = 0;
		for (WayBillOrder wayBillOrder : wayBillOrders) {
			Double total = wayBillOrder.getTotal();
			Integer payMethod = Integer.parseInt(wayBillOrder.getPayMethod());
			if(payMethod == PayModeEnum.ARRIVE_PAY.getValue() || payMethod == PayModeEnum.MULTIPLE_PAY.getValue()){
				if(total != null){
					receivableTotal += total;
				}
			}
		}
		departList.setReceivableTotal(receivableTotal);*/
		
		String departNumber = departList.getDepartNumber();
		if(StringUtils.isEmpty(departNumber)){
			departNumber = generateDepartureNumber(departList.getStartOutlets()); // 生成唯一的发车单号
			departList.setDepartNumber(departNumber);
			
			// 添加应付款记录
			Double total = departList.getShouldPayDriverCost();
			Double nowPay = departList.getNowPayDriverCost();
			Double arrivePay = departList.getArrivePayDriverCost();
			Double backPay = departList.getBackPayDriverCost();
			if((nowPay != null && nowPay != 0) || (backPay != null && backPay != 0)){
				ReceivablePayBill receivablePayBill = new ReceivablePayBill();
				receivablePayBill.setCostSubject(CostSubjectEnum.DRIVER_FREIGHT.getName());
				receivablePayBill.setIsCompleted(0);
				receivablePayBill.setDepartNumber(departNumber);
				receivablePayBill.setTotal(total);
				receivablePayBill.setCurrentPay(nowPay);
				receivablePayBill.setArrivePay(arrivePay);
				receivablePayBill.setBackPay(backPay);
				receivablePayBill.setCreateTime(new Date());
				receivablePayBill.setOutletsId(departList.getStartOutlets());
				receivablePayBillService.insert(receivablePayBill);
			}
			
			if(arrivePay != null && arrivePay != 0){
				ReceivablePayBill receivablePayBill = new ReceivablePayBill();
				receivablePayBill.setCostSubject(CostSubjectEnum.DRIVER_FREIGHT.getName());
				receivablePayBill.setIsCompleted(0);
				receivablePayBill.setDepartNumber(departNumber);
				receivablePayBill.setTotal(total);
				receivablePayBill.setCurrentPay(nowPay);
				receivablePayBill.setArrivePay(arrivePay);
				receivablePayBill.setBackPay(backPay);
				receivablePayBill.setCreateTime(new Date());
				receivablePayBill.setOutletsId(departList.getTargetOutlets());
				receivablePayBillService.insert(receivablePayBill);
			}
			
			return departListMapper.insert(departList);
		}else{
			return departListMapper.updateByDepartNumber(departList);
		}
	}
	
	/**
	 * 生成 发车单号
	 * @param outletsId
	 * @return
	 */
	private String generateDepartureNumber(Long outletsId){
		// 获取公司组织代码    网点编号
		String orderNumber = null;
	 	OutletsInfo outletsInfo = outletsInfoMapper.selectOutletsAndCompanyCodeById(outletsId);
	 	String companyCode = outletsInfo.getCompanyCode();
		String outletsNumber = outletsInfo.getOutletsNumber();
		String dateStr = DateTools.getCurrentDateStr("yyMM");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("numberType", OrderNumberTypeEnum.DEPART_ORDER_NUMBER.getValue());
		params.put("companyCode", companyCode);
		params.put("outletsNumber", outletsNumber);
		
		OrderSerialNumber serialNumber = new OrderSerialNumber();
		serialNumber.setNumberType(OrderNumberTypeEnum.DEPART_ORDER_NUMBER.getValue());
		serialNumber.setCompanyCode(companyCode);
		serialNumber.setOutletsNumber(outletsNumber);
		serialNumber.setDateStr(dateStr);
		
		OrderSerialNumber orderSerialNumber = orderSerialNumberMapper.selectByParams(params);
		if(orderSerialNumber == null){
			String newNumber = "001";
			orderNumber = SystemConstant.DEPARTURE_ORDER_NUMBER_PREFIX + companyCode + outletsNumber + dateStr + newNumber;
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
			orderNumber = SystemConstant.DEPARTURE_ORDER_NUMBER_PREFIX + companyCode + outletsNumber + dateStr + newNumber;
			serialNumber.setSerialNumber(newNumber);
			serialNumber.setId(orderSerialNumber.getId());
			orderSerialNumberMapper.updateByPrimaryKeySelective(serialNumber);
		}
		return orderNumber;
	}
	
	/**
	 * 发车出库 (配载时直接发车出库)
	 * @param departList
	 * @return
	 */
	public int saveDeparture(DepartList departList, User user){
		
		departList.setOperateTime(new Date());
		departList.setStatus(DepartListStatusEnum.TRANSPORTING.getValue());
		departList.setStartTime(DateTools.string2Date(departList.getStartTimeStr()));
		departList.setEndTime(DateTools.string2Date(departList.getEndTimeStr()));
		departList.setIsCompleted(0);
		
		String wayBillNumbers = departList.getWayBillNumbers();
		List<String> wayBillNumberList = new ArrayList<String>();
		String[] wayBillNumberArr = wayBillNumbers.split(",");
		for (String wayBillNumber : wayBillNumberArr) {
			wayBillNumberList.add(wayBillNumber);
		}
		List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectByWayBillNumbers(wayBillNumberList);
		// 计算发车单 应收运费合计
		double receivableTotal = 0;
		// 计算代收货款
		for (WayBillOrder wayBillOrder : wayBillOrders) {
			Double freight = wayBillOrder.getFreight();
			if(freight != null){
				receivableTotal += freight;
			}
			// 修改运单状态为【已发车】
			WayBillOrder wayBillOrderTemp = new WayBillOrder();
			wayBillOrderTemp.setId(wayBillOrder.getId());
			wayBillOrderTemp.setStatus(WayBillOrderStatusEnum.ALREADY_START_VEHICLE.getValue());
			wayBillOrderMapper.updateByPrimaryKeySelective(wayBillOrderTemp);
			// 添加运单跟踪记录
			WayBillOrderFollow wayBillOrderFollow = new WayBillOrderFollow();
			wayBillOrderFollow.setWayBillNumber(wayBillOrder.getWayBillNumber());
			wayBillOrderFollow.setStatus(WayBillOrderStatusEnum.ALREADY_START_VEHICLE.getValue());
			wayBillOrderFollow.setHandleTime(new Date());
			wayBillOrderFollow.setHandleInfo("发出出库");
			wayBillOrderFollow.setOperatePerson(user.getId());
			String operatePersonName = user.getTrueName();
			if(StringUtils.isEmpty(operatePersonName)){
				operatePersonName = user.getLoginName();
			}
			wayBillOrderFollow.setOperatePersonName(operatePersonName);
			wayBillOrderFollowMapper.insert(wayBillOrderFollow);
			// 如果 该运单为货运交易系统下单，则修改货运交易系统订单状态为【运输中】
			String orderNumber = wayBillOrder.getOrderNumber();
			if(StringUtils.isNotEmpty(orderNumber)){
				PlatformOrder order = new PlatformOrder();
				order.setOrderNumber(orderNumber);
				order.setState(OrderStatusEnum.TRANSPORTING.getValue());
				platformOrderMapper.updateByOrderNumberSelective(order);
				// 添加订单跟踪记录
				PlatformOrderFollow platformOrderFollow = new PlatformOrderFollow();
				platformOrderFollow.setOrderNumber(orderNumber);
				platformOrderFollow.setWayBillNumber(wayBillOrder.getWayBillNumber());
				platformOrderFollow.setStatus(OrderStatusEnum.TRANSPORTING.getValue());
				platformOrderFollow.setHandleTime(new Date());
				platformOrderFollow.setHandleInfo("发车出库");
				platformOrderFollow.setOperatePerson(user.getTrueName());
				platformOrderFollowMapper.insert(platformOrderFollow);
			}
		}
		departList.setReceivableTotal(receivableTotal);
		
		String departNumber = generateDepartureNumber(departList.getStartOutlets()); // 生成唯一的发车单号
		departList.setDepartNumber(departNumber);
		
		// 添加应付款记录
		Double total = departList.getShouldPayDriverCost();
		Double nowPay = departList.getNowPayDriverCost();
		Double arrivePay = departList.getArrivePayDriverCost();
		Double backPay = departList.getBackPayDriverCost();
		if((nowPay != null && nowPay != 0) || (backPay != null && backPay != 0)){
			ReceivablePayBill receivablePayBill = new ReceivablePayBill();
			receivablePayBill.setCostSubject(CostSubjectEnum.DRIVER_FREIGHT.getName());
			receivablePayBill.setIsCompleted(0);
			receivablePayBill.setDepartNumber(departNumber);
			receivablePayBill.setTotal(total);
			receivablePayBill.setCurrentPay(nowPay);
			receivablePayBill.setArrivePay(arrivePay);
			receivablePayBill.setBackPay(backPay);
			receivablePayBill.setCreateTime(new Date());
			receivablePayBill.setOutletsId(departList.getStartOutlets());
			receivablePayBillService.insert(receivablePayBill);
		}
		
		if(arrivePay != null && arrivePay != 0){
			ReceivablePayBill receivablePayBill = new ReceivablePayBill();
			receivablePayBill.setCostSubject(CostSubjectEnum.DRIVER_FREIGHT.getName());
			receivablePayBill.setIsCompleted(0);
			receivablePayBill.setDepartNumber(departNumber);
			receivablePayBill.setTotal(total);
			receivablePayBill.setCurrentPay(nowPay);
			receivablePayBill.setArrivePay(arrivePay);
			receivablePayBill.setBackPay(backPay);
			receivablePayBill.setCreateTime(new Date());
			receivablePayBill.setOutletsId(departList.getTargetOutlets());
			receivablePayBillService.insert(receivablePayBill);
		}
		// 发送短信
		sendMessage(wayBillNumberList, SmsTemplateEnum.OUT_STORAGE, "已发车出库");
		return departListMapper.insert(departList);
	}
	
	/**
	 * 发车出库 (发车单管理-发车出库)
	 * @param departList
	 * @return
	 */
	public int updateDeparture(String departLists, User user){
		int rows = 0;
		List<DepartList> list = JSONArray.parseArray(departLists, DepartList.class);
		for (DepartList departList : list) {
			// 修改发车单状态为 运输中
			rows += departListMapper.updateStatusByDepartNumber(departList.getDepartNumber(), DepartListStatusEnum.TRANSPORTING.getValue(),"");
			String[] wayBillNumberArr = departList.getWayBillNumbers().split(",");
			for (String wayBillNumber : wayBillNumberArr) {
				WayBillOrder wayBillOrder = wayBillOrderMapper.selectByWayBillNumber(wayBillNumber);
				// 修改运单状态为【已发车】
				WayBillOrder wayBillOrderTemp = new WayBillOrder();
				wayBillOrderTemp.setId(wayBillOrder.getId());
				wayBillOrderTemp.setStatus(WayBillOrderStatusEnum.ALREADY_START_VEHICLE.getValue());
				wayBillOrderMapper.updateByPrimaryKeySelective(wayBillOrderTemp);
				// 添加运单跟踪记录
				WayBillOrderFollow wayBillOrderFollow = new WayBillOrderFollow();
				wayBillOrderFollow.setWayBillNumber(wayBillNumber);
				wayBillOrderFollow.setStatus(WayBillOrderStatusEnum.ALREADY_START_VEHICLE.getValue());
				wayBillOrderFollow.setHandleTime(new Date());
				wayBillOrderFollow.setHandleInfo("发出出库");
				wayBillOrderFollow.setOperatePerson(user.getId());
				String operatePersonName = user.getTrueName();
				if(StringUtils.isEmpty(operatePersonName)){
					operatePersonName = user.getLoginName();
				}
				wayBillOrderFollow.setOperatePersonName(operatePersonName);
				wayBillOrderFollowMapper.insert(wayBillOrderFollow);
				// 如果 该运单为货运交易系统下单，则修改货运交易系统订单状态为【运输中】
				String orderNumber = wayBillOrder.getOrderNumber();
				if(StringUtils.isNotEmpty(orderNumber)){
					PlatformOrder order = new PlatformOrder();
					order.setOrderNumber(orderNumber);
					order.setState(OrderStatusEnum.TRANSPORTING.getValue());
					platformOrderMapper.updateByOrderNumberSelective(order);
					// 添加订单跟踪记录
					PlatformOrderFollow platformOrderFollow = new PlatformOrderFollow();
					platformOrderFollow.setOrderNumber(orderNumber);
					platformOrderFollow.setWayBillNumber(wayBillNumber);
					platformOrderFollow.setStatus(OrderStatusEnum.TRANSPORTING.getValue());
					platformOrderFollow.setHandleTime(new Date());
					platformOrderFollow.setHandleInfo("发车出库");
					platformOrderFollow.setOperatePerson(user.getTrueName());
					platformOrderFollowMapper.insert(platformOrderFollow);
				}
			}
		}
		// 发送短信
		List<String> wayBillNumbers = new ArrayList<String>();
		for (DepartList departList : list) {
			String[] wayBillNumberArr = departList.getWayBillNumbers().split(",");
			for (String wayBillNumber : wayBillNumberArr) {
				wayBillNumbers.add(wayBillNumber);
			}
		}
		sendMessage(wayBillNumbers, SmsTemplateEnum.OUT_STORAGE, "已发车出库");
		return rows;
	}
	
	
	/**
	 * 发送短信
	 * @param orderNumber
	 * @param smsTemplateEnum
	 */
	private void sendMessage(List<String> wayBillNumbers, SmsTemplateEnum smsTemplateEnum, String statusName){
		SmsTemplate smsTemplate = smsTemplateMapper.getSmsTemplateByAbbr(smsTemplateEnum.getValue());
		if(smsTemplate == null){
			logger.warn("【获取短信模板失败】");
			return;
		}
		if(smsTemplate.getIsEnabled() != null && smsTemplate.getIsEnabled() != 1){
			logger.warn("【短信模板未启用】");
			return;
		}
		if(wayBillNumbers == null || wayBillNumbers.size() == 0){
			return;
		}
		for (String wayBillNumber : wayBillNumbers) {
			WayBillOrder wayBillOrder = wayBillOrderMapper.selectByWayBillNumber(wayBillNumber);
			SmsParams smsParams = new SmsParams();
			smsParams.setOrderNumber(wayBillOrder.getOrderNumber());
			smsParams.setWaybillNumber(wayBillNumber);
			smsParams.setCompany(platformUserCompanyMapper.selectByOutletsId(wayBillOrder.getStartOutlets()).getCompanyName());
			smsParams.setOutletsName(outletsInfoMapper.selectById(wayBillOrder.getStartOutlets()).getName());
			smsParams.setOrderStatus(statusName);
			if(smsTemplate.getSendConsignor() != null && smsTemplate.getSendConsignor() == 1){
				// 发送短信给发货人
				boolean flag = false;
				try {
					flag = smsManagerService.sendSms(wayBillOrder.getConsignorMobile(), smsParams, smsTemplateEnum);
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
					flag = smsManagerService.sendSms(wayBillOrder.getConsigneeMobile(), smsParams, smsTemplateEnum);
				} catch (Exception e) {
					logger.error("【发送短信给收货人失败】", e);
					return;
				}
				if(flag){
					logger.info("【发送短信给收货人成功】");
				}
			}
		}
		
	}
	
	/**
	 * 查询发车单列表
	 * @param param
	 * @return
	 */
	/*public Result selectByParam(DepartListSearchParams params){
		//List<DepartList> departLists = departListMapper.selectByParam(param);
		
		List<DepartList> departLists = departListMapper.selectByParams(params);
		
		for (DepartList departList : departLists) {
			DepartListStatusEnum[] enums = DepartListStatusEnum.values();
			for (DepartListStatusEnum departListStatusEnum : enums) {
				if(departList.getStatus() == departListStatusEnum.getValue()){
					departList.setStatusName(departListStatusEnum.getName());
					continue;
				}
			}
		}
		//int count = departListMapper.selectByParamCount(param);
		int count = departListMapper.selectByParamsCount(params);
		Result result = new Result();
		result.setRows(departLists);
		result.setResults(count);
		return result;
	}*/
	
	/**
	 * 修改 发车单状态
	 * @param ids
	 * @param statusEnum
	 * @return
	 */
	/*public int updateDepartStatus(String ids, DepartListStatusEnum statusEnum){
		int count = 0;
		JSONArray idArr = JSONArray.parseArray(ids);
		for (int i = 0; i < idArr.size(); i++) {
			Long id = Long.parseLong(idArr.get(i).toString());
			DepartList depart = new DepartList();
			depart.setId(id);
			depart.setStatus(statusEnum.getValue());
			count += departListMapper.updateByPrimaryKeySelective(depart);
		}
		return count;
	}*/
	
	/**
	 * 根据发车单号 查询发车单信息
	 * @param departNumber 发车单号
	 * @return
	 */
	public DepartList selectByDepartBumber(String departNumber){
		DepartList departList = departListMapper.selectByDepartNumber(departNumber);
		DriverInfo driverInfo = driverInfoMapper.selectByPrimaryKey(departList.getDriver());
		departList.setDriverName(driverInfo.getDriverName());
		departList.setDriverPhone(driverInfo.getPhoneNumber());
		departList.setDriverBankName(driverInfo.getBankName());
		departList.setDriverBankNumber(driverInfo.getBankNumber());
		PlatformUserCompany userCompany = userCompanyMapper.selectByOutletsId(departList.getStartOutlets());
		departList.setCompanyName(userCompany.getCompanyName());
		return departList;
	}
	
	/**
	 * 车辆签到
	 * @param departLists
	 * @return
	 */
	public int saveSign(String departLists, User user){
		int rows = 0;
		List<DepartList> list = JSONArray.parseArray(departLists, DepartList.class);
		for (DepartList departList : list) {
			// 如果当前网点为目的网点  则修改发车单状态为【 已到站】
			if(departList.getTargetOutlets().longValue() == user.getOutletsId().longValue()){
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			    String dateNowStr = sdf.format(date);  
				rows += departListMapper.updateStatusByDepartNumber(departList.getDepartNumber(), DepartListStatusEnum.ALREADY_ARRIVED.getValue(),dateNowStr);
			}else{
				rows = 1;
			}
			String[] wayBillNumberArr = departList.getWayBillNumbers().split(",");
			for (String wayBillNumber : wayBillNumberArr) {
				WayBillOrder wayBillOrder = wayBillOrderMapper.selectByWayBillNumber(wayBillNumber);
				if(wayBillOrder.getTargetOutlets().longValue() == user.getOutletsId().longValue()){
					// 修改运单状态为【已到货】
					WayBillOrder wayBillOrderTemp = new WayBillOrder();
					wayBillOrderTemp.setId(wayBillOrder.getId());
					wayBillOrderTemp.setStatus(WayBillOrderStatusEnum.ALREADY_REACH_CARGO.getValue());
					wayBillOrderMapper.updateByPrimaryKeySelective(wayBillOrderTemp);
					// 添加运单跟踪记录
					WayBillOrderFollow wayBillOrderFollow = new WayBillOrderFollow();
					wayBillOrderFollow.setWayBillNumber(wayBillNumber);
					wayBillOrderFollow.setStatus(WayBillOrderStatusEnum.ALREADY_REACH_CARGO.getValue());
					wayBillOrderFollow.setHandleTime(new Date());
					wayBillOrderFollow.setHandleInfo("运单已到站");
					wayBillOrderFollow.setOperatePerson(user.getId());
					String operatePersonName = user.getTrueName();
					if(StringUtils.isEmpty(operatePersonName)){
						operatePersonName = user.getLoginName();
					}
					wayBillOrderFollow.setOperatePersonName(operatePersonName);
					wayBillOrderFollowMapper.insert(wayBillOrderFollow);
					// 如果 该运单为货运交易系统下单，则修改货运交易系统订单状态为【已到达】
					String orderNumber = wayBillOrder.getOrderNumber();
					if(StringUtils.isNotEmpty(orderNumber)){
						PlatformOrder order = new PlatformOrder();
						order.setOrderNumber(orderNumber);
						order.setState(OrderStatusEnum.ARRIVED.getValue());
						platformOrderMapper.updateByOrderNumberSelective(order);
						// 添加订单跟踪记录
						PlatformOrderFollow platformOrderFollow = new PlatformOrderFollow();
						platformOrderFollow.setOrderNumber(orderNumber);
						platformOrderFollow.setWayBillNumber(wayBillNumber);
						platformOrderFollow.setStatus(OrderStatusEnum.ARRIVED.getValue());
						platformOrderFollow.setHandleTime(new Date());
						platformOrderFollow.setHandleInfo("发车出库");
						platformOrderFollow.setOperatePerson(user.getTrueName());
						platformOrderFollowMapper.insert(platformOrderFollow);
					}
				}
			}
		}
		return rows;
	}
	
	/**
	 * 验证途径网点是否卸货完毕
	 * @param departLists
	 * @return
	 */
	public boolean validWayOutletsIsUnloadCargo(String departLists, User user){
		List<DepartList> list = JSONArray.parseArray(departLists, DepartList.class);
		List<String> wayBillNumberList = new ArrayList<String>();
		List<Long> wayOutletsList = new ArrayList<Long>();
		for (DepartList departList : list) {
			if(user.getOutletsId().longValue() == departList.getTargetOutlets().longValue()){
				// 如果当前网点为目的网点 则判断途径网点的运单状态
				String wayBillNumbers = departList.getWayBillNumbers();
				String[] wayBillNumberArr = wayBillNumbers.split(",");
				for (String wayBillNumber : wayBillNumberArr) {
					wayBillNumberList.add(wayBillNumber);
				}
				String wayOutlets = departList.getWayOutlets();
				if(StringUtils.isNotEmpty(wayOutlets)){
					String[] wayOutletsArr = wayOutlets.split(",");
					for (String outlets : wayOutletsArr) {
						wayOutletsList.add(Long.parseLong(outlets));
					}
				}
			}
		}
		if(wayBillNumberList.size() != 0 && wayOutletsList.size() != 0){
			// 查询所有发车单对应的运单
			List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectByWayBillNumbers(wayBillNumberList);
			for (WayBillOrder wayBillOrder : wayBillOrders) {
				for (Long wayOutlets : wayOutletsList) {
					if(wayBillOrder.getTargetOutlets().longValue() == wayOutlets.longValue()){
						// 当前运单为途径网点运单 判断运单状态是否 已卸货
						Integer status = wayBillOrder.getStatus();
						if(status.intValue() != WayBillOrderStatusEnum.ALREADY_DISCHARGE.getValue() &&
								status.intValue() != WayBillOrderStatusEnum.PART_DISCHARGE.getValue() &&
								status.intValue() != WayBillOrderStatusEnum.ALREADY_OUT_SOURCE.getValue() && 
								status.intValue() != WayBillOrderStatusEnum.RECEIVED.getValue() &&
								status.intValue() != WayBillOrderStatusEnum.DISCARDED.getValue() && 
								status.intValue() != WayBillOrderStatusEnum.CLOSE.getValue()){
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 整车入库
	 * @param departLists 发车单号
	 */
	public int saveWholeVehicleStorage(String departLists, User user){
		
		int rows = 0;
		List<DepartList> list = JSONArray.parseArray(departLists, DepartList.class);
		List<String> wayBillNumberMessages = new ArrayList<String>();
		for (DepartList departList : list) {
			
			// 如果当前网点为目的网点  则修改发车单状态为【已卸货】
			if(departList.getTargetOutlets().longValue() == user.getOutletsId().longValue()){
				rows += departListMapper.updateStatusByDepartNumber(departList.getDepartNumber(), DepartListStatusEnum.UNLOADED.getValue(),"");
			}else{
				rows = 1;
			}
			String[] wayBillNumberArr = departList.getWayBillNumbers().split(",");
			for (String wayBillNumber : wayBillNumberArr) {
				WayBillOrder wayBillOrder = wayBillOrderMapper.selectByWayBillNumber(wayBillNumber);
				// 如果当前网点为目的网点
				if(wayBillOrder.getTargetOutlets().longValue() == user.getOutletsId().longValue()){
					wayBillNumberMessages.add(wayBillNumber);
					//修改运单状态为【已卸货】
					WayBillOrder wayBillOrderTemp = new WayBillOrder();
					wayBillOrderTemp.setId(wayBillOrder.getId());
					wayBillOrderTemp.setStatus(WayBillOrderStatusEnum.ALREADY_DISCHARGE.getValue());
					wayBillOrderMapper.updateByPrimaryKeySelective(wayBillOrderTemp);
					// 添加运单跟踪记录
					WayBillOrderFollow wayBillOrderFollow = new WayBillOrderFollow();
					wayBillOrderFollow.setWayBillNumber(wayBillNumber);
					wayBillOrderFollow.setStatus(WayBillOrderStatusEnum.ALREADY_DISCHARGE.getValue());
					wayBillOrderFollow.setHandleTime(new Date());
					wayBillOrderFollow.setHandleInfo("运单已卸货");
					wayBillOrderFollow.setOperatePerson(user.getId());
					String operatePersonName = user.getTrueName();
					if(StringUtils.isEmpty(operatePersonName)){
						operatePersonName = user.getLoginName();
					}
					wayBillOrderFollow.setOperatePersonName(operatePersonName);
					wayBillOrderFollowMapper.insert(wayBillOrderFollow);
				}
			}
		}
		// 发送短信
		sendMessage(wayBillNumberMessages, SmsTemplateEnum.UNLOAD_IN_STORAGE, "已卸货入库");
		return rows;
	}
	
	/**
	 * 部分入库
	 * @param departNumber 发车单号
	 * @param wayBillNumber 运单号
	 * @return
	 */
	public int savePartStorage(String departNumber, String wayBillNumber, User user){
		// 修改运单状态为【已卸货】
		WayBillOrder wayBillOrder = new WayBillOrder();
		wayBillOrder.setWayBillNumber(wayBillNumber);
		wayBillOrder.setStatus(WayBillOrderStatusEnum.ALREADY_DISCHARGE.getValue());
	 	int rows = wayBillOrderMapper.updateByWayBillNumberSelective(wayBillOrder);
	 	// 添加运单跟踪记录
		WayBillOrderFollow wayBillOrderFollow = new WayBillOrderFollow();
		wayBillOrderFollow.setWayBillNumber(wayBillNumber);
		wayBillOrderFollow.setStatus(WayBillOrderStatusEnum.ALREADY_DISCHARGE.getValue());
		wayBillOrderFollow.setHandleTime(new Date());
		wayBillOrderFollow.setHandleInfo("运单已卸货");
		wayBillOrderFollow.setOperatePerson(user.getId());
		String operatePersonName = user.getTrueName();
		if(StringUtils.isEmpty(operatePersonName)){
			operatePersonName = user.getLoginName();
		}
		wayBillOrderFollow.setOperatePersonName(operatePersonName);
		wayBillOrderFollowMapper.insert(wayBillOrderFollow);
		// 验证发车单包含的运单状态是否全部更改为已卸货，若所有运单都为已卸货 则修改发车单的状态为已卸货
		DepartList departList = departListMapper.selectByDepartNumber(departNumber);
		String[] wayBillNumberArr = departList.getWayBillNumbers().split(",");
		List<String> list = new ArrayList<String>();
		for (String str : wayBillNumberArr) {
			list.add(str);
		}
		boolean flag = true;
		List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectByWayBillNumbers(list);
		for (WayBillOrder wayBillOrderTemp : wayBillOrders) {
			if(wayBillOrderTemp.getStatus() != WayBillOrderStatusEnum.ALREADY_DISCHARGE.getValue()){
				flag = false;
			}
		}
		if(flag){
			// 修改发车单状态为已卸货
			departListMapper.updateStatusByDepartNumber(departNumber, DepartListStatusEnum.UNLOADED.getValue(),"");
		}
		// 发送短信
		List<String> wayBillNumbers = new ArrayList<String>();
		wayBillNumbers.add(wayBillNumber);
		sendMessage(wayBillNumbers, SmsTemplateEnum.UNLOAD_IN_STORAGE, "已卸货入库");
		return rows;
	}
	
	
	/**
	 * 查询 发车单信息
	 * @param params
	 * @return
	 */
	public Result selectByParams(DepartListSearchParams params){
		List<DepartList> departLists = departListMapper.selectByParams(params);
		if(departLists != null && departLists.size() > 0){
			for (DepartList departList : departLists) {
				// 设置发车单状态
				Long outletsId = params.getTargetOutlets();
				if(outletsId != null && outletsId.longValue() != departList.getStartOutlets().longValue() && outletsId != departList.getTargetOutlets().longValue()){
					int arriveStatus = 0;
					int unloadStatus = 0;
					// 获取 发车单下的所有运单
					String wayBillNumbers = departList.getWayBillNumbers();
					String[] wayBillNumberArr = wayBillNumbers.split(",");
					for (String wayBillNumber : wayBillNumberArr) {
						WayBillOrder wayBillOrder = wayBillOrderMapper.selectByWayBillNumber(wayBillNumber);
						// 如果 运单目的网点 为 当前网点【途径网点】
						if(wayBillOrder.getTargetOutlets().longValue() == outletsId.longValue()){
							Integer status = wayBillOrder.getStatus();
							if(status != WayBillOrderStatusEnum.ALREADY_REACH_CARGO.getValue()){
								arriveStatus = 1;
							}
							if(status != WayBillOrderStatusEnum.ALREADY_DISCHARGE.getValue()){
								unloadStatus = 1;
							}
						}
					}
					if(unloadStatus == 0){
						departList.setStatus(DepartListStatusEnum.UNLOADED.getValue());
					}
					if(arriveStatus == 0){
						departList.setStatus(DepartListStatusEnum.ALREADY_ARRIVED.getValue());
					}
				}
				
				DepartListStatusEnum[] departListStatusEnums = DepartListStatusEnum.values();
				for (DepartListStatusEnum departListStatusEnum : departListStatusEnums) {
					if(departList.getStatus() == departListStatusEnum.getValue()){
						departList.setStatusName(departListStatusEnum.getName());
						continue;
					}
				}
				// 计算应收运费合计
				// 计算代收货款合计
				String wayBillNumbers = departList.getWayBillNumbers();
				String[] wayBillNumberArr = wayBillNumbers.split(",");
				List<String> list = new ArrayList<String>();
				for (String wayBillNumber : wayBillNumberArr) {
					list.add(wayBillNumber);
				}
				List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectByWayBillNumbers(list);
				double receivableTotal = 0;
				double agencyFundTotal = 0;
				if(wayBillOrders != null && wayBillOrders.size() > 0){
					for (WayBillOrder wayBillOrder : wayBillOrders) {
						Double total = wayBillOrder.getTotal();
						if(total != null){
							receivableTotal += total;
						}
						Double agencyFund = wayBillOrder.getAgencyFund();
						if(agencyFund != null){
							agencyFundTotal += agencyFund;
						}
					}
				}
				departList.setReceivableTotal(receivableTotal);
				departList.setAgencyFundTotal(agencyFundTotal);
				// 设置操作用户名称
				User user = userMapper.selectByPrimaryKey(departList.getOperatePerson());
				String userName = user.getTrueName();
				if(StringUtils.isEmpty(userName)){
					userName = user.getLoginName();
				}
				departList.setOperatePersonName(userName);
				// 设置运费 是否完结
				// 设置 代收货款 是否完结
				int isCompleted = 1;
				int agencyFundIsCompleted = 1;
				for (String wayBillNumber : wayBillNumberArr) {
					
					PlatformOrder platformOrder = platformOrderMapper.selectByWayBillNumber(wayBillNumber);
					// 如果 是 货运交易系统下单 且 支付方式 为 现付
					if(platformOrder != null && platformOrder.getPayType() == PayModeEnum.IMMEDIATELY_PAY.getValue()){
						isCompleted = 1;
					}else{
						ReceiveMoneyOrder receiveMoneyOrder = receiveMoneyOrderMapper.selectByWayBillNumberAndCostType(wayBillNumber, ReceiveMoneyCostTypeEnum.FREIGHT.getValue());
						if(receiveMoneyOrder == null){
							isCompleted = 0;
						}else{
							if(receiveMoneyOrder.getStatus() == 0){
								isCompleted = 0;
							}
						}
					}
					
					ReceiveMoneyOrder receiveMoneyOrder2 = receiveMoneyOrderMapper.selectByWayBillNumberAndCostType(wayBillNumber, ReceiveMoneyCostTypeEnum.AGENCY_FUND.getValue());
					if(receiveMoneyOrder2 == null){
						agencyFundIsCompleted = 0;
					}else{
						if(receiveMoneyOrder2.getStatus() == 0){
							agencyFundIsCompleted = 0;
						}
					}
					if(agencyFundTotal == 0){
						agencyFundIsCompleted = 1;
					}
				}
				departList.setIsCompleted(isCompleted);
				departList.setAgencyFundIsCompleted(agencyFundIsCompleted);
			}
		}
		
		int count = departListMapper.selectByParamsCount(params);
		Result result = new Result();
		result.setResults(count);
		result.setRows(departLists);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("shouldTotal", departListMapper.selectShouldByParamsTotal(params));
		data.put("actualTotal", departListMapper.selectActualByParamsTotal(params));
		
		result.setData(data);
		return result;
	}
	/**
	 * 查询到站运单信息
	 * @param params
	 * @return
	 */
	public Result selectByParamsArrive(DepartListSearchParams params){
		List<DepartList> departLists = departListMapper.selectByParamsArrive(params);
		if(departLists != null && departLists.size() > 0){
			for (DepartList departList : departLists) {
				// 设置发车单状态
				Long outletsId = params.getTargetOutlets();
				if(outletsId != null && outletsId.longValue() != departList.getStartOutlets().longValue() && outletsId != departList.getTargetOutlets().longValue()){
					int arriveStatus = 0;
					int unloadStatus = 0;
					// 获取 发车单下的所有运单
					String wayBillNumbers = departList.getWayBillNumbers();
					String[] wayBillNumberArr = wayBillNumbers.split(",");
					for (String wayBillNumber : wayBillNumberArr) {
						WayBillOrder wayBillOrder = wayBillOrderMapper.selectByWayBillNumber(wayBillNumber);
						// 如果 运单目的网点 为 当前网点【途径网点】
						if(wayBillOrder.getTargetOutlets().longValue() == outletsId.longValue()){
							Integer status = wayBillOrder.getStatus();
							if(status != WayBillOrderStatusEnum.ALREADY_REACH_CARGO.getValue()){
								arriveStatus = 1;
							}
							if(status != WayBillOrderStatusEnum.ALREADY_DISCHARGE.getValue()){
								unloadStatus = 1;
							}
						}
					}
					if(unloadStatus == 0){
						departList.setStatus(DepartListStatusEnum.UNLOADED.getValue());
					}
					if(arriveStatus == 0){
						departList.setStatus(DepartListStatusEnum.ALREADY_ARRIVED.getValue());
					}
				}
				
				DepartListStatusEnum[] departListStatusEnums = DepartListStatusEnum.values();
				for (DepartListStatusEnum departListStatusEnum : departListStatusEnums) {
					if(departList.getStatus() == departListStatusEnum.getValue()){
						departList.setStatusName(departListStatusEnum.getName());
						continue;
					}
				}
				// 计算应收运费合计
				// 计算代收货款合计
				String wayBillNumbers = departList.getWayBillNumbers();
				String[] wayBillNumberArr = wayBillNumbers.split(",");
				List<String> list = new ArrayList<String>();
				for (String wayBillNumber : wayBillNumberArr) {
					list.add(wayBillNumber);
				}
				List<WayBillOrder> wayBillOrders = wayBillOrderMapper.selectByWayBillNumbers(list);
				double receivableTotal = 0;
				double agencyFundTotal = 0;
				if(wayBillOrders != null && wayBillOrders.size() > 0){
					for (WayBillOrder wayBillOrder : wayBillOrders) {
						Double total = wayBillOrder.getTotal();
						if(total != null){
							receivableTotal += total;
						}
						Double agencyFund = wayBillOrder.getAgencyFund();
						if(agencyFund != null){
							agencyFundTotal += agencyFund;
						}
					}
				}
				departList.setReceivableTotal(receivableTotal);
				departList.setAgencyFundTotal(agencyFundTotal);
				// 设置操作用户名称
				User user = userMapper.selectByPrimaryKey(departList.getOperatePerson());
				String userName = user.getTrueName();
				if(StringUtils.isEmpty(userName)){
					userName = user.getLoginName();
				}
				departList.setOperatePersonName(userName);
				// 设置运费 是否完结
				// 设置 代收货款 是否完结
				int isCompleted = 1;
				int agencyFundIsCompleted = 1;
				for (String wayBillNumber : wayBillNumberArr) {
					
					PlatformOrder platformOrder = platformOrderMapper.selectByWayBillNumber(wayBillNumber);
					// 如果 是 货运交易系统下单 且 支付方式 为 现付
					if(platformOrder != null && platformOrder.getPayType() == PayModeEnum.IMMEDIATELY_PAY.getValue()){
						isCompleted = 1;
					}else{
						ReceiveMoneyOrder receiveMoneyOrder = receiveMoneyOrderMapper.selectByWayBillNumberAndCostType(wayBillNumber, ReceiveMoneyCostTypeEnum.FREIGHT.getValue());
						if(receiveMoneyOrder == null){
							isCompleted = 0;
						}else{
							if(receiveMoneyOrder.getStatus() == 0){
								isCompleted = 0;
							}
						}
					}
					
					ReceiveMoneyOrder receiveMoneyOrder2 = receiveMoneyOrderMapper.selectByWayBillNumberAndCostType(wayBillNumber, ReceiveMoneyCostTypeEnum.AGENCY_FUND.getValue());
					if(receiveMoneyOrder2 == null){
						agencyFundIsCompleted = 0;
					}else{
						if(receiveMoneyOrder2.getStatus() == 0){
							agencyFundIsCompleted = 0;
						}
					}
					if(agencyFundTotal == 0){
						agencyFundIsCompleted = 1;
					}
				}
				departList.setIsCompleted(isCompleted);
				departList.setAgencyFundIsCompleted(agencyFundIsCompleted);
			}
		}
		
		int count = departListMapper.selectByParamsCountArrive(params);
		Result result = new Result();
		result.setResults(count);
		result.setRows(departLists);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("shouldTotal", departListMapper.selectShouldByParamsTotalArrive(params));
		data.put("actualTotal", departListMapper.selectActualByParamsTotalArrive(params));
		
		result.setData(data);
		return result;
	}
	/**
	 * 根据发车单号 修改发车单状态
	 * @param departNumbers
	 * @param statusEnum
	 * @return
	 */
	public int updateStatusByDepartNumbers(String departNumbers, DepartListStatusEnum statusEnum){
		List<String> deparNumberList = JSONArray.parseArray(departNumbers, String.class);
		int rows = 0;
		for (String departNumber : deparNumberList) {
			rows += departListMapper.updateStatusByDepartNumber(departNumber, statusEnum.getValue(),"");
		}
		return rows;
	}
}
