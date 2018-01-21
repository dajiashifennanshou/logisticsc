package com.brightsoft.service.platform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.platformBankSplitMapper;
import com.brightsoft.model.platformBankSplit;
import com.brightsoft.model.platformVoSplitInsurance;
import com.brightsoft.model.platformVoSplitOrder;
import com.brightsoft.model.platformVoSplitPos;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.WebConstant;

@Service
public class platformBankSplitServiceImpl {

	@Autowired
	private platformBankSplitMapper bankSplitMapper;
	
	public boolean insertBankSplit(platformBankSplit bankSplit){
		if(bankSplitMapper.insert(bankSplit)>0){
			return true;
		}
		return false;
	}
	
	public platformBankSplit selectBankSplit(String requestid){
		return bankSplitMapper.selectBankSplit(requestid);
	}
	
	public boolean updateBankSplit(platformBankSplit record){
		if(bankSplitMapper.updateByPrimaryKeySelective(record)>0){
			return true;
		}
		return false;
	}
	
	public Result selectSplitOrderState(platformVoSplitOrder platformVoSplitOrder,Page<?> page,List<Integer> statusList){
		Result result = new Result();
		List<platformVoSplitOrder> orders = bankSplitMapper.selectSplitOrderState(platformVoSplitOrder, page,statusList);
		int results = bankSplitMapper.countSplitOrderState(platformVoSplitOrder,statusList);
		result.setResults(results);
		result.setRows(orders);
		return result;
	}
	
	public Result selectBankSplitList(platformVoSplitOrder platformVoSplitOrder,Page<?> page,List<Integer> statusList){
		Result result = new Result();
		List<platformVoSplitOrder> orders = bankSplitMapper.selectBankSplitList(platformVoSplitOrder, page,statusList);
		int results = bankSplitMapper.countBankSplit(platformVoSplitOrder,statusList);
		result.setResults(results);
		result.setRows(orders);
		return result;
	}
	
	public Result selectBankInsurance(platformVoSplitInsurance insurance,Page<?> page){
		Result result = new Result();
		List<platformVoSplitInsurance> insurances = bankSplitMapper.selectBankInsurance(insurance, page);
		int results = bankSplitMapper.countBankInsurance(insurance);
		result.setResults(results);
		result.setRows(insurances);
		return result;
	}
	
	public Result selectBankPos(platformVoSplitPos splitPos,Page<?> page){
		Result result = new Result();
		List<platformVoSplitPos> platformVoSplitPos = bankSplitMapper.selectBankPos(splitPos, page);
		int results = bankSplitMapper.countBankPos(splitPos);
		result.setResults(results);
		result.setRows(platformVoSplitPos);
		return result;
	}
	
	public Page<?> selectBySelectedPayment(platformVoSplitPos splitPos,Page<?> page){
		int results = bankSplitMapper.countBankPos(splitPos);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<platformVoSplitPos> platformVoSplitPos = bankSplitMapper.selectBankPos(splitPos, page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, platformVoSplitPos);
		page.setParams(map);
		return page;
	}
}
