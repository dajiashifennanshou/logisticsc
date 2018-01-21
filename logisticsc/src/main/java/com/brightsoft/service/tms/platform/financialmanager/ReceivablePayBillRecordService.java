package com.brightsoft.service.tms.platform.financialmanager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.tms.MoneyDiaryRecordMapper;
import com.brightsoft.dao.tms.ReceivablePayBillMapper;
import com.brightsoft.dao.tms.ReceivablePayBillRecordMapper;
import com.brightsoft.model.MoneyDiaryRecord;
import com.brightsoft.model.ReceivablePayBill;
import com.brightsoft.model.ReceivablePayBillRecord;
import com.brightsoft.model.User;

/**
 * 应收款账单 收款记录 service
 * @author yangshoubao
 *
 */
@Service
public class ReceivablePayBillRecordService {

	@Autowired
	private ReceivablePayBillRecordMapper receivablePayBillRecordMapper;
	
	@Autowired
	private ReceivablePayBillMapper receivablePayBillMapper;
	
	@Autowired
	private MoneyDiaryRecordMapper moneyDiaryRecordMapper;
	
	public int insert(ReceivablePayBillRecord record, User user){
		
		double money = 0;
		List<ReceivablePayBillRecord> receivablePayBillRecords = receivablePayBillRecordMapper.selectByBillId(record.getBillId());
		for (ReceivablePayBillRecord receivablePayBillRecord : receivablePayBillRecords) {
			money += receivablePayBillRecord.getReceiveMoney();
		}
		Integer isCompleted = record.getIsCompleted();
		if(record.getTotal().doubleValue() == (money + record.getReceiveMoney().doubleValue())){
			isCompleted = 1;
			record.setIsCompleted(1);
		}
		
		if(isCompleted != null && isCompleted == 1){
			// 修改 对应的应付款账单的结算状态为 已完结
			ReceivablePayBill receivablePayBill = new ReceivablePayBill();
			receivablePayBill.setId(record.getBillId());
			receivablePayBill.setIsCompleted(1);
			receivablePayBillMapper.updateByPrimaryKeySelective(receivablePayBill);
		}
		record.setOperateTime(new Date());
		int rows = receivablePayBillRecordMapper.insertSelective(record);
		
		// 添加现金日记记录
		MoneyDiaryRecord moneyDiaryRecord = new MoneyDiaryRecord();
		moneyDiaryRecord.setDate(new Date());
		moneyDiaryRecord.setType(1); // 0：收入， 1：支出
		moneyDiaryRecord.setCostSubject(record.getCostSubject());
		moneyDiaryRecord.setMoney(record.getReceiveMoney());
		moneyDiaryRecord.setPerson(record.getReceivePerson());
		moneyDiaryRecord.setCompany(record.getReceiveCompany());
		moneyDiaryRecord.setDepartNumber(record.getDepartNumber());
		moneyDiaryRecord.setOutletsId(user.getOutletsId());
		moneyDiaryRecord.setOperatePerson(user.getId());
		moneyDiaryRecordMapper.insert(moneyDiaryRecord);
		return rows;
	}
	
	public List<ReceivablePayBillRecord> selectByBillId(String billId){
		return receivablePayBillRecordMapper.selectByBillId(Long.parseLong(billId));
	}
}
