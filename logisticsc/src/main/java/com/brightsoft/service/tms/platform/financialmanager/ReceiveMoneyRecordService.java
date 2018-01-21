package com.brightsoft.service.tms.platform.financialmanager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.tms.DepartListMapper;
import com.brightsoft.dao.tms.receiveMoneyRecordMapper;
import com.brightsoft.model.DepartList;
import com.brightsoft.model.receiveMoneyRecord;

/**
 * 收款记录 service
 * @author yangshoubao
 *
 */
@Service
public class ReceiveMoneyRecordService {

	@Autowired
	private receiveMoneyRecordMapper receiveMoneyRecordMapper;
	
	@Autowired
	private DepartListMapper departListMapper;
	
	/**
	 * 添加收款记录
	 * @param receiveMoneyRecord
	 * @return
	 */
	public int insertReceiveMoneyRecord(receiveMoneyRecord receiveMoneyRecord){
		receiveMoneyRecord.setOperateTime(new Date());
		receiveMoneyRecord.setCostType(0);
		Integer isCompleted = receiveMoneyRecord.getIsCompleted();
		if(isCompleted == null){
			receiveMoneyRecord.setIsCompleted(0);
		}
		// 添加收款记录
		int rows = receiveMoneyRecordMapper.insert(receiveMoneyRecord);
		// 修改 发车单实收款 数据
		DepartList departList = departListMapper.selectByDepartNumber(receiveMoneyRecord.getDepartNumber());
		Double actualReceiveTotal = departList.getActualReceiveTotal();
		if(actualReceiveTotal != null){
			actualReceiveTotal += receiveMoneyRecord.getActualReceiveMoney();
		}else{
			actualReceiveTotal = receiveMoneyRecord.getActualReceiveMoney();
		}
		if(isCompleted != null && isCompleted == 1){
			departList.setIsCompleted(1);
		}
		departList.setActualReceiveTotal(actualReceiveTotal);
		departListMapper.updateByPrimaryKeySelective(departList);
		return rows;
	}
	
	/**
	 * 查询收款记录
	 * @param departNumber 发车单号
	 * @return
	 */
	public List<receiveMoneyRecord> selectByDepartNumber(String departNumber){
		
		DepartList departList = departListMapper.selectByDepartNumber(departNumber);
		String wayBillNumberStr = departList.getWayBillNumbers();
		
		return receiveMoneyRecordMapper.selectByWayBillNumbers(wayBillNumberStr.split(","));
	}
}
