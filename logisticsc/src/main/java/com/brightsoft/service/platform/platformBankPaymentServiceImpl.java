package com.brightsoft.service.platform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.platformBankPaymentMapper;
import com.brightsoft.model.platformBankPayment;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.WebConstant;

/**
 * 订单支付接口
 * @author ThinkPad
 *
 */
@Service
public class platformBankPaymentServiceImpl {
	
	@Autowired
	private platformBankPaymentMapper bankPaymentMapper;
	
	public boolean insertBankPayment(platformBankPayment bankPayment){
		if(bankPaymentMapper.insertSelective(bankPayment) > 0){
			return true;
		}
		return false;
	}
	
	public platformBankPayment selectBankPayment(String requestid){
		return bankPaymentMapper.selectBankPayment(requestid);
	}
	
	public boolean update(platformBankPayment bankPayment){
		if(bankPaymentMapper.updateByPrimaryKeySelective(bankPayment)>0){
			return true;
		}
		return false;
	}
	
	public Page<?> selectBySelectedPayment(
			platformBankPayment bankPayment, Page<?> page) {
		int results = bankPaymentMapper.countPayment(bankPayment);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<platformBankPayment> mineOrders = bankPaymentMapper.selectBySelectedConditionPayment(bankPayment, page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, mineOrders);
		page.setParams(map);
		return page;
	}
	
	public Page<?> selectBySelectedPaymentFrom(
			platformBankPayment bankPayment, Page<?> page) {
		int results = bankPaymentMapper.countPaymentForm(bankPayment);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<platformBankPayment> mineOrders = bankPaymentMapper.selectBySelectedConditionPaymentForm(bankPayment, page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, mineOrders);
		page.setParams(map);
		return page;
	}
	
	public Result selectPaymentList(platformBankPayment bankPayment, Page<?> page){
		Result result = new Result();
		List<platformBankPayment> mineOrders = bankPaymentMapper.selectPaymentList(bankPayment, page);
		int results = bankPaymentMapper.countPaymentList(bankPayment);
		result.setResults(results);
		result.setRows(mineOrders);
		return result;
	}
	public boolean selectBankPaymentByUserId(Long userId){
		if(bankPaymentMapper.selectBankPaymentByUserId(userId)>0){
			return true;
		}
		return false;
	}
	public platformBankPayment selectBankPaymentByOrderNumbe(String orderNumber,Integer orderType){
		return bankPaymentMapper.selectBankPaymentByOrderNumber(orderNumber,orderType);
	}
	
	public int selectPaymentAmount(platformBankPayment bankPayment){
		Integer amount;
		amount = bankPaymentMapper.selectPaymentAmount(bankPayment);
		return amount;
	}
}
