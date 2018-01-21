package com.brightsoft.service.platform;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformOrderAgencyFundMapper;
import com.brightsoft.model.PlatformCollectionPayment;
import com.brightsoft.model.PlatformOrderAgencyFund;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.WebConstant;

@Service("platformOrderAgencyFund")
public class PlatformOrderAgencyFundServiceImpl implements PlatformOrderAgencyFundService{
	@Autowired
	private PlatformOrderAgencyFundMapper agencyFundMapper;

	public Page<?> selectBySelectedCondition(
			PlatformCollectionPayment collectionPayment, Page<?> page) {
		int results = agencyFundMapper.countRows(collectionPayment);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<PlatformCollectionPayment> collectionPayments = agencyFundMapper.selectBySelectedCondition(collectionPayment,page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, collectionPayments);
		page.setParams(map);
		return page;
	}
	/**
	 * 增加代收货款信息
	 */
	public boolean inserOrderAgencyFund(PlatformOrderAgencyFund agencyFund) {
		agencyFund.setOperateTime(new Date());
		agencyFund.setState(Const.PLATFORMUSER_ORDER_AGENCY_FUND_STATE_0);
		agencyFund.setUncollectedFund(agencyFund.getAgencyFund());
		agencyFund.setReceivedFund(0.0);
		if(agencyFundMapper.inserOrderAgencyFund(agencyFund)>0){
			return true;
		}
		return false;
	}
	
}
