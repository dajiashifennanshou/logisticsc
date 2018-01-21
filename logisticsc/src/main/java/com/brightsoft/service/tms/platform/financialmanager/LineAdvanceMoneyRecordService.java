package com.brightsoft.service.tms.platform.financialmanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.tms.LineInfoMapper;
import com.brightsoft.dao.tms.lineAdvanceMoneyRecordMapper;
import com.brightsoft.model.LineInfo;
import com.brightsoft.model.lineAdvanceMoneyRecord;

/**
 * 线路充值记录 service
 * @author yangshoubao
 *
 */
@Service
public class LineAdvanceMoneyRecordService {

	@Autowired
	private lineAdvanceMoneyRecordMapper advanceMoneyRecordMapper;
	
	@Autowired
	private LineInfoMapper lineInfoMapper;
	
	/**
	 * 添加 充值记录
	 * @param lineAdvanceMoneyRecord
	 * @return
	 */
	public int insert(lineAdvanceMoneyRecord lineAdvanceMoneyRecord){
		
		// 计算线路余额
		lineAdvanceMoneyRecord.getLineId();
		LineInfo lineInfo = lineInfoMapper.selectByPrimaryKey(lineAdvanceMoneyRecord.getLineId());
		Double remainMoney = lineInfo.getRemainMoney();
		if(remainMoney != null){
			remainMoney += lineAdvanceMoneyRecord.getAdvanceMoney();
		}else{
			remainMoney = lineAdvanceMoneyRecord.getAdvanceMoney();
		}
		lineInfo.setRemainMoney(remainMoney);
		// 修改线路余额
		lineInfoMapper.updateByPrimaryKeySelective(lineInfo);
		
		return advanceMoneyRecordMapper.insert(lineAdvanceMoneyRecord);
	}
	
	/**
	 * 根据线路ID查询充值记录
	 * @param lineId
	 * @return
	 */
	public List<lineAdvanceMoneyRecord> selectByLineId(String lineId){
		return advanceMoneyRecordMapper.selectByLineId(Long.parseLong(lineId));
	}
}
