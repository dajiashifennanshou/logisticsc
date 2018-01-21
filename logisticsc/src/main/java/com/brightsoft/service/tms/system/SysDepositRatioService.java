package com.brightsoft.service.tms.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.tms.DepositRatioMapper;
import com.brightsoft.model.DepositRatio;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class SysDepositRatioService {

	@Autowired
	private DepositRatioMapper depositRatioMapper;
	
	/**
	 * 查询提送货信息
	 * @param page
	 * @param outletsPriceRangeConf
	 * @return
	 */
	public Result selectByCondition(Page<?> page,DepositRatio depositRatio){
		Result result = new Result();
		int results = depositRatioMapper.countSelectedRows(depositRatio);
		List<DepositRatio> rows = depositRatioMapper.selectBySelectedCondition(page, depositRatio);
		for(int i=0,len=rows.size();i<len;i++){
			DepositRatio dr = rows.get(i);
			String lineName = "从："+dr.getStartOutletsName()+"</br>到："+dr.getEndOutletsName();
			rows.get(i).setLineName(lineName);
		}
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	/**
	 * 审核
	 * @param depositRatio
	 * @return
	 */
	public int update4Audit(List<DepositRatio> list){
		return depositRatioMapper.audit(list);
	}
}
