package com.brightsoft.service.tms.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.brightsoft.controller.vo.PlatformBaseSearchParams;
import com.brightsoft.dao.tms.CustomWayBillCostMapper;
import com.brightsoft.model.CustomWayBillCost;
import com.brightsoft.utils.Result;

/**
 * 自定义运单费用service
 * @author yangshoubao
 *
 */
@Service
public class CustomWayBillCostService {

	@Autowired
	private CustomWayBillCostMapper customWayBillCostMapper;
	
	/**
	 * 根据公司ID查询自定义运单费用
	 * @param companyId
	 * @return
	 */
	public List<CustomWayBillCost> selectByCompanyId(Long companyId){
		return customWayBillCostMapper.selectByCompanyId(companyId);
	}
	
	/**
	 * 保存 自定义运单费用信息
	 * @param customWayBillCost
	 * @return
	 */
	public int save(CustomWayBillCost customWayBillCost){
		if(customWayBillCost.getId() == null){
			return customWayBillCostMapper.insertSelective(customWayBillCost);
		}else{
			return customWayBillCostMapper.updateByPrimaryKey(customWayBillCost);
		}
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delete(String ids){
		List<String> idArr = JSONArray.parseArray(ids, String.class);
		int rows = 0;
		for (String id : idArr) {
			rows += customWayBillCostMapper.deleteByPrimaryKey(Long.parseLong(id));
		}
		return rows;
	}
	
	/**
	 * 查询自定义运单费用(货运交易系统)
	 * @param params
	 * @return
	 */
	public Result selectByParamsOfPlatform(PlatformBaseSearchParams params){
		List<CustomWayBillCost> customWayBillCosts = customWayBillCostMapper.selectByParamsOfPlatform(params);
		int count = customWayBillCostMapper.selectByParamsCountOfPlatform(params);
		Result result = new Result();
		result.setResults(count);
		result.setRows(customWayBillCosts);
		return result;
	}
}
