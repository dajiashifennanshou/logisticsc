package com.brightsoft.service.tms.platform.financialmanager;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.ReceiveMoneyOrderStatusEnum;
import com.brightsoft.common.enums.ReceiveMoneyTypeEnum;
import com.brightsoft.dao.tms.MoneyDiaryRecordMapper;
import com.brightsoft.dao.tms.PosOrderRecordMapper;
import com.brightsoft.dao.tms.ReceiveMoneyOrderMapper;
import com.brightsoft.dao.tms.ReceiveMoneyOrderRecordMapper;
import com.brightsoft.model.MoneyDiaryRecord;
import com.brightsoft.model.PosOrderRecord;
import com.brightsoft.model.ReceiveMoneyOrder;
import com.brightsoft.model.ReceiveMoneyOrderRecord;
import com.brightsoft.model.User;

/**
 * 收款订单收款记录 service
 * @author yangshoubao
 *
 */
@Service
public class ReceiveMoneyOrderRecordService {

	@Autowired
	private ReceiveMoneyOrderRecordMapper receiveMoneyOrderRecordMapper;
	
	@Autowired
	private ReceiveMoneyOrderMapper receiveMoneyOrderMapper;
	
	@Autowired
	private MoneyDiaryRecordMapper moneyDiaryRecordMapper;
	
	@Autowired
	private PosOrderRecordMapper posOrderRecordMapper;
	
	/**
	 * 保存 收款订单收款记录(现金收款)
	 * @param receiveMoneyOrderRecord
	 * @return
	 */
	public int save(ReceiveMoneyOrderRecord receiveMoneyOrderRecord, User user){
		ReceiveMoneyOrder receiveMoneyOrder = receiveMoneyOrderMapper.selectByPrimaryKey(receiveMoneyOrderRecord.getReceiveMoneyOrderId());
		// 收款订单应收费用
		double money = receiveMoneyOrder.getMoney();
		// 收款订单已收费用
		double cashMoneyTotal = 0; // 现金收款总额
		double posMoneyTotal = 0; //POS机收款总额
		List<ReceiveMoneyOrderRecord> receiveMoneyOrderRecords = receiveMoneyOrderRecordMapper.selectByReceiveMoneyOrderId(receiveMoneyOrderRecord.getReceiveMoneyOrderId());
		if(receiveMoneyOrderRecords != null && receiveMoneyOrderRecords.size() > 0){
			for (ReceiveMoneyOrderRecord receiveMoneyOrderRecordTemp : receiveMoneyOrderRecords) {
				if(receiveMoneyOrderRecordTemp.getReceiveMoneyType() == ReceiveMoneyTypeEnum.CASH_RECEIVE_MONEY.getValue()){
					cashMoneyTotal += receiveMoneyOrderRecordTemp.getReceiveMoney();
				}
			}
		}
		List<PosOrderRecord> posOrderRecords = posOrderRecordMapper.selectByOrderNumber(receiveMoneyOrder.getOrderNumber());
		for (PosOrderRecord posOrderRecord : posOrderRecords) {
			posMoneyTotal += posOrderRecord.getAmount();
		}
		// 修改收款订单状态  是否完结
		// 如果 应收费用 = 已收现金费用 + 当前收款费用 + POS机已收费用 //修改收款订单状态为已完结
		if(money == (cashMoneyTotal + receiveMoneyOrderRecord.getReceiveMoney() + posMoneyTotal)){
			ReceiveMoneyOrder receiveMoneyOrderTemp = new ReceiveMoneyOrder();
			receiveMoneyOrderTemp.setId(receiveMoneyOrderRecord.getReceiveMoneyOrderId());
			receiveMoneyOrderTemp.setStatus(ReceiveMoneyOrderStatusEnum.FINISHED.getValue());
			receiveMoneyOrderMapper.updateByPrimaryKeySelective(receiveMoneyOrderTemp);
		}
		// 添加 收款订单收款记录
		String operatePerson = user.getTrueName();
		if(StringUtils.isEmpty(operatePerson)){
			operatePerson = user.getLoginName();
		}
		receiveMoneyOrderRecord.setReceiveMoneyType(ReceiveMoneyTypeEnum.CASH_RECEIVE_MONEY.getValue());
		receiveMoneyOrderRecord.setOperatePerson(operatePerson);
		receiveMoneyOrderRecord.setOperateTime(new Date());
		int rows = receiveMoneyOrderRecordMapper.insertSelective(receiveMoneyOrderRecord);
		// 添加现金日记记录
		MoneyDiaryRecord moneyDiaryRecord = new MoneyDiaryRecord();
		moneyDiaryRecord.setDate(new Date());
		moneyDiaryRecord.setType(0); // 0：收入， 1：支出
		if(receiveMoneyOrderRecord.getIsAgencyFund() == 1){
			moneyDiaryRecord.setCostSubject("代收款");
		}else{
			moneyDiaryRecord.setCostSubject("运费");
		}
		moneyDiaryRecord.setMoney(receiveMoneyOrderRecord.getReceiveMoney());
		moneyDiaryRecord.setPerson(receiveMoneyOrderRecord.getPayPerson());
		moneyDiaryRecord.setCompany(null);
		moneyDiaryRecord.setOutletsId(user.getOutletsId());
		moneyDiaryRecord.setOperatePerson(user.getId());
		moneyDiaryRecordMapper.insert(moneyDiaryRecord);
		return rows;
	}
	
	/**
	 * 查询 收款订单收款记录
	 * @param receiveMoneyOrderId
	 * @return
	 */
	public List<ReceiveMoneyOrderRecord> selectByReceiveMoneyOrderId(String receiveMoneyOrderId){
		return receiveMoneyOrderRecordMapper.selectByReceiveMoneyOrderId(Long.parseLong(receiveMoneyOrderId));
	}
}
