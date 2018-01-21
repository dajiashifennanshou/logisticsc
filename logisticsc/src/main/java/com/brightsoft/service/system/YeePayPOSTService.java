package com.brightsoft.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.ReceiveMoneyOrderStatusEnum;
import com.brightsoft.dao.tms.PosOrderCancelRecordMapper;
import com.brightsoft.dao.tms.PosOrderRecordMapper;
import com.brightsoft.dao.tms.ReceiveMoneyOrderMapper;
import com.brightsoft.dao.tms.ReceiveMoneyOrderRecordMapper;
import com.brightsoft.model.PosOrderCancelRecord;
import com.brightsoft.model.PosOrderRecord;
import com.brightsoft.model.ReceiveMoneyOrder;
import com.brightsoft.model.ReceiveMoneyOrderRecord;
import com.brightsoft.utils.yeepay.POSCODMSCancelOrderRequest;

@Service
public class YeePayPOSTService {

	@Autowired
	private PosOrderCancelRecordMapper posOrderCancelRecordMapper;
	
	@Autowired
	private ReceiveMoneyOrderMapper receiveMoneyOrderMapper;
	
	@Autowired
	private PosOrderRecordMapper posOrderRecordMapper;
	
	@Autowired
	private ReceiveMoneyOrderRecordMapper receiveMoneyOrderRecordMapper;
	
	/**
	 * POS撤销交易 业务处理
	 * @param hotelObj
	 */
	public void saveCancelOrder(POSCODMSCancelOrderRequest hotelObj){
		/**
 		 * 业务处理
 		 * 1、添加POS订单撤销记录
 		 * 2、修改TMS收款订单的状态为 已撤销
 		 * 3、修改POS订单记录为 已撤销
 		 * 4、修改TMS收款订单记录为 已撤销
 		 */
		// 1、添加POS机订单撤销记录
		PosOrderCancelRecord posOrderCancelRecord = new PosOrderCancelRecord();
		posOrderCancelRecord.setEmployeeId(hotelObj.getPosCancelOrderRequestBody().getEmployeeID());
		posOrderCancelRecord.setPosSn(hotelObj.getPosCancelOrderRequestBody().getPosSn());
		posOrderCancelRecord.setOrderNumber(hotelObj.getPosCancelOrderRequestBody().getOrderNo());
		posOrderCancelRecord.setAmount(Double.parseDouble(hotelObj.getPosCancelOrderRequestBody().getAmount()));
		posOrderCancelRecord.setPosRequestId(hotelObj.getPosCancelOrderRequestBody().getPosRequestID());
		posOrderCancelRecord.setReferNo(hotelObj.getPosCancelOrderRequestBody().getReferNo());
		posOrderCancelRecord.setPayTypeCode(hotelObj.getPosCancelOrderRequestBody().getPayTypeCode());
		posOrderCancelRecord.setPayMethod(hotelObj.getPosCancelOrderRequestBody().getPayMethod());
		posOrderCancelRecord.setChequeNo(hotelObj.getPosCancelOrderRequestBody().getChequeNo());
		posOrderCancelRecord.setBankCardNo(hotelObj.getPosCancelOrderRequestBody().getBankCardNo());
		posOrderCancelRecord.setBankCardName(hotelObj.getPosCancelOrderRequestBody().getBankCardName());
		posOrderCancelRecord.setBankCardType(hotelObj.getPosCancelOrderRequestBody().getBankCardType());
		posOrderCancelRecord.setBankOrderNo(hotelObj.getPosCancelOrderRequestBody().getBankOrderNo());
		posOrderCancelRecord.setYeepayOrderNo(hotelObj.getPosCancelOrderRequestBody().getYeepayOrderNo());
		posOrderCancelRecord.setCustomerNo(hotelObj.getPosCancelOrderRequestBody().getCustomerNo());
		posOrderCancelRecord.setPosMenue(hotelObj.getPosCancelOrderRequestBody().getPosMenue());
		posOrderCancelRecordMapper.insert(posOrderCancelRecord);
		//2、修改TMS收款订单的状态为 已撤销
		ReceiveMoneyOrder receiveMoneyOrder = receiveMoneyOrderMapper.selectByOrderNumber(hotelObj.getPosCancelOrderRequestBody().getOrderNo());
		if(receiveMoneyOrder != null){
			ReceiveMoneyOrder receiveMoneyOrderTemp = new ReceiveMoneyOrder();
			receiveMoneyOrderTemp.setId(receiveMoneyOrder.getId());
			receiveMoneyOrderTemp.setStatus(ReceiveMoneyOrderStatusEnum.CANCELED.getValue());
			receiveMoneyOrderMapper.updateByPrimaryKeySelective(receiveMoneyOrderTemp);
			//3、修改POS订单记录为 已撤销
			List<PosOrderRecord> posOrderRecords = posOrderRecordMapper.selectByOrderNumber(hotelObj.getPosCancelOrderRequestBody().getOrderNo());
			if(posOrderRecords != null && posOrderRecords.size() > 0){
				PosOrderRecord posOrderRecord = new PosOrderRecord();
				posOrderRecord.setId(posOrderRecords.get(0).getId());
				posOrderRecord.setIsCancel(1);
				posOrderRecordMapper.updateByPrimaryKeySelective(posOrderRecord);
			}
			//4、修改TMS收款订单记录为 已撤销
			List<ReceiveMoneyOrderRecord> receiveMoneyOrderRecords = receiveMoneyOrderRecordMapper.selectByReceiveMoneyOrderId(receiveMoneyOrder.getId());
			ReceiveMoneyOrderRecord receiveMoneyOrderRecord = null;
			if(receiveMoneyOrderRecords != null && receiveMoneyOrderRecords.size() > 0){
				for (ReceiveMoneyOrderRecord receiveMoneyOrderRecordTemp : receiveMoneyOrderRecords) {
					if(receiveMoneyOrderRecordTemp.getReceiveMoneyType() == 1){
						receiveMoneyOrderRecord = receiveMoneyOrderRecordTemp;
						break;
					}
				}
			}
			if(receiveMoneyOrderRecord != null){
				ReceiveMoneyOrderRecord receiveMoneyOrderRecordUpdate = new ReceiveMoneyOrderRecord();
				receiveMoneyOrderRecordUpdate.setId(receiveMoneyOrderRecord.getId());
				receiveMoneyOrderRecordUpdate.setIsCancel(1);
				receiveMoneyOrderRecordMapper.updateByPrimaryKeySelective(receiveMoneyOrderRecordUpdate);
			}
		}
	}
}
