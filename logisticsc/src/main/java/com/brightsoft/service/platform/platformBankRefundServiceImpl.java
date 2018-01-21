package com.brightsoft.service.platform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.platformBankRefundMapper;
import com.brightsoft.model.platformBankRefund;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.WebConstant;

@Service
public class platformBankRefundServiceImpl {
	
	@Autowired
	private platformBankRefundMapper bankRefundMapper;
	
	public boolean insertRefund(platformBankRefund bankRefund){
		if(bankRefundMapper.insertSelective(bankRefund) >0){
			return true;
		}
		return false;
	}
	
	public Page<?> selectBySelectedBankRefund(platformBankRefund bankRefund,Page<?> page){
		int results = bankRefundMapper.countBankRefund(bankRefund);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<platformBankRefund> bankRefunds = bankRefundMapper.selectBySelectedConditionBankRefund(bankRefund, page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, bankRefunds);
		page.setParams(map);
		return page;
	}
	public Result selectPaymentList(platformBankRefund bankRefund,Page<?> page){
		Result result = new Result();
		List<platformBankRefund> bankRefunds = bankRefundMapper.selectBankRefundList(bankRefund, page);
		int results = bankRefundMapper.countBankRefundList(bankRefund);
		result.setResults(results);
		result.setRows(bankRefunds);
		return result;
	}
}
