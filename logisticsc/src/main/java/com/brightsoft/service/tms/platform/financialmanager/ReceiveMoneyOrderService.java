package com.brightsoft.service.tms.platform.financialmanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.OrderNumberTypeEnum;
import com.brightsoft.common.enums.ReceiveMoneyOrderStatusEnum;
import com.brightsoft.common.enums.ReceiveMoneyTypeEnum;
import com.brightsoft.controller.vo.ReceiveMoneyOrderSearchParams;
import com.brightsoft.dao.tms.OrderSerialNumberMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.dao.tms.ReceiveMoneyOrderMapper;
import com.brightsoft.dao.tms.ReceiveMoneyOrderRecordMapper;
import com.brightsoft.dao.tms.ReceiveMoneyOrderRelationMapper;
import com.brightsoft.dao.tms.WayBillOrderMapper;
import com.brightsoft.model.OrderSerialNumber;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.ReceiveMoneyOrder;
import com.brightsoft.model.ReceiveMoneyOrderRecord;
import com.brightsoft.model.ReceiveMoneyOrderRelation;
import com.brightsoft.model.User;
import com.brightsoft.model.WayBillOrder;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Result;

/**
 * 收款订单 service
 * @author yangshoubao
 *
 */
@Service
public class ReceiveMoneyOrderService {

	@Autowired
	private ReceiveMoneyOrderMapper receiveMoneyOrderMapper;
	
	@Autowired
	private ReceiveMoneyOrderRelationMapper receiveMoneyOrderRelationMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	@Autowired
	private OrderSerialNumberMapper orderSerialNumberMapper;
	
	@Autowired
	private ReceiveMoneyOrderRecordMapper receiveMoneyOrderRecordMapper;
	
	@Autowired
	private WayBillOrderMapper wayBillOrderMapper;
	
	/**
	 * 保存收款订单信息
	 * @param receiveMoneyOrder
	 * @return
	 */
	public int saveReceiveMoneyOrder(ReceiveMoneyOrder receiveMoneyOrder, User user){
		receiveMoneyOrder.setOrderNumber(generateWayBillNumber(user.getOutletsId()));
		receiveMoneyOrder.setOperatePerson(user.getId());
		receiveMoneyOrder.setOperatePersonMobile(user.getLoginName());
		receiveMoneyOrder.setOperateTime(new Date());
		receiveMoneyOrder.setStatus(ReceiveMoneyOrderStatusEnum.NOT_FINISH.getValue());
		receiveMoneyOrder.setOutletsId(user.getOutletsId());
		int rows = receiveMoneyOrderMapper.insert(receiveMoneyOrder);
		String wayBillNumbers = receiveMoneyOrder.getWayBillNumbers();
		String[] wayBillNumberArr = wayBillNumbers.split(",");
		List<ReceiveMoneyOrderRelation> moneyOrderRelations = new ArrayList<ReceiveMoneyOrderRelation>();
		for (String wayBillNumber : wayBillNumberArr) {
			ReceiveMoneyOrderRelation moneyOrderRelation = new ReceiveMoneyOrderRelation();
			moneyOrderRelation.setReceiveMoneyOrderId(receiveMoneyOrder.getId());
			moneyOrderRelation.setWayBillNumber(wayBillNumber);
			moneyOrderRelations.add(moneyOrderRelation);
		}
		receiveMoneyOrderRelationMapper.batchInsert(moneyOrderRelations);
		return rows;
	}
	
	/**
	 * 生成 收款订单号
	 * @param outletsId
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
		params.put("numberType", OrderNumberTypeEnum.RECEIVE_MONEY_ORDER_NUMBER.getValue());
		params.put("companyCode", companyCode);
		params.put("outletsNumber", outletsNumber);
		
		OrderSerialNumber serialNumber = new OrderSerialNumber();
		serialNumber.setNumberType(OrderNumberTypeEnum.RECEIVE_MONEY_ORDER_NUMBER.getValue());
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
	 * 查询收款订单列表
	 * @param params
	 * @return
	 */
	public Result selectByParams(ReceiveMoneyOrderSearchParams params){
		List<ReceiveMoneyOrder> receiveMoneyOrders = receiveMoneyOrderMapper.selectByParams(params);
		if(receiveMoneyOrders != null && receiveMoneyOrders.size() > 0){
			for (ReceiveMoneyOrder receiveMoneyOrder : receiveMoneyOrders) {
				double cashReceivedMoney = 0;
				double posReceivedMoney = 0;
				List<ReceiveMoneyOrderRecord> receiveMoneyOrderRecords = receiveMoneyOrderRecordMapper.selectByReceiveMoneyOrderId(receiveMoneyOrder.getId());
				if(receiveMoneyOrderRecords != null && receiveMoneyOrderRecords.size() > 0){
					for (ReceiveMoneyOrderRecord receiveMoneyOrderRecord : receiveMoneyOrderRecords) {
						if(receiveMoneyOrderRecord.getReceiveMoneyType() == ReceiveMoneyTypeEnum.CASH_RECEIVE_MONEY.getValue()){
							cashReceivedMoney += receiveMoneyOrderRecord.getReceiveMoney();
						}else if(receiveMoneyOrderRecord.getReceiveMoneyType() == ReceiveMoneyTypeEnum.POS_RECEIVE_MONEY.getValue()){
							posReceivedMoney += receiveMoneyOrderRecord.getReceiveMoney();
						}
					}
				}
				receiveMoneyOrder.setCashReceivedMoney(cashReceivedMoney);
				receiveMoneyOrder.setPosReceivedMoney(posReceivedMoney);
				/*if(cashReceivedMoney == receiveMoneyOrder.getCashMoney() && posReceivedMoney == receiveMoneyOrder.getPosMoney()){
					// 设置 订单状态为【已完结】
					receiveMoneyOrder.setStatus(ReceiveMoneyOrderStatusEnum.FINISHED.getValue());
				}else{
					// 设置 订单状态为【未完结】
					receiveMoneyOrder.setStatus(ReceiveMoneyOrderStatusEnum.NOT_FINISH.getValue());
				}*/
				// 设置 发货人、收货人信息
				List<ReceiveMoneyOrderRelation> receiveMoneyOrderRelations = receiveMoneyOrderRelationMapper.selectByReceiveMoneyOrderId(receiveMoneyOrder.getId());
				if(receiveMoneyOrderRelations != null && receiveMoneyOrderRelations.size() > 0){
					WayBillOrder wayBillOrder = wayBillOrderMapper.selectByWayBillNumber(receiveMoneyOrderRelations.get(0).getWayBillNumber());
					receiveMoneyOrder.setConsignor(wayBillOrder.getConsignor());
					receiveMoneyOrder.setConsignorMobile(wayBillOrder.getConsignorMobile());
					receiveMoneyOrder.setConsignee(wayBillOrder.getConsignee());
					receiveMoneyOrder.setConsigneeMobile(wayBillOrder.getConsigneeMobile());
				}
			}
		}
		int count = receiveMoneyOrderMapper.selectByParamsCount(params);
		Result result = new Result();
		result.setRows(receiveMoneyOrders);
		result.setResults(count);
		return result;
	}
}
