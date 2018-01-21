package com.brightsoft.service.tms.platform.financialmanager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.tms.InsteadCollectMoneyMapper;
import com.brightsoft.model.InsteadCollectMoney;

/**
 * 代收货款 service
 * @author yangshoubao
 *
 */
@Service
public class InsteadCollectMoneyService {

	@Autowired
	private InsteadCollectMoneyMapper insteadCollectMoneyMapper;
	
	/**
	 * 添加 代收货款记录
	 * @param insteadCollectMoney
	 * @return
	 */
	public int insert(InsteadCollectMoney insteadCollectMoney){
		insteadCollectMoney.setOperateTime(new Date());
		return insteadCollectMoneyMapper.insert(insteadCollectMoney);
	}
	
	/**
	 * 根据运单号 查询代收货款记录
	 * @param wayBillNumber
	 * @return
	 */
	public List<InsteadCollectMoney> selectByWayBillNumber(String wayBillNumber){
		return insteadCollectMoneyMapper.selectByWayBillNumber(wayBillNumber);
	}
}
