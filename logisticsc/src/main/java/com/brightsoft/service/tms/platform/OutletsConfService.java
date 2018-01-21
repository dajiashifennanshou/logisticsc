package com.brightsoft.service.tms.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.tms.OutletsPriceRangeConfMapper;
import com.brightsoft.model.OutletsPriceRangeConf;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class OutletsConfService {

	@Autowired
	private OutletsPriceRangeConfMapper outletsPriceRangeConfMapper;
	
	/**
	 * 网点获取网点价格范围
	 * @param searchParams
	 * @param page
	 * @param outletsId
	 * @return
	 */
	public Result selectByOutletsIdAndCondition(SearchParams searchParams,Page<?> page,Long outletsId){
		Result result = new Result();
		int results = outletsPriceRangeConfMapper.countRowsByOutletsId(searchParams, outletsId);
		List<OutletsPriceRangeConf> rows = outletsPriceRangeConfMapper.selectByOutletsIdAndCondition(searchParams, page, outletsId);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	
	/**
	 * 专线获取网点价格范围
	 * @param searchParams
	 * @param page
	 * @param companyId
	 * @return
	 */
	public Result selectByCompanyIdAndCondition(SearchParams searchParams,Page<?> page,Long companyId){
		Result result = new Result();
		int results = outletsPriceRangeConfMapper.countRowsByCompanyId(searchParams, companyId);
		List<OutletsPriceRangeConf> rows = outletsPriceRangeConfMapper.selectByCompanyIdAndCondition(searchParams, page, companyId);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	/**
	 * 通过网点获取提送货信息
	 * @param outletsId
	 * @return
	 */
	public Result selectByOutletsId(Page<?> page,long outletsId){
		Result result = new Result();
		int results = outletsPriceRangeConfMapper.countRows(outletsId);
		List<OutletsPriceRangeConf> rows = outletsPriceRangeConfMapper.selectByOutletsId(page, outletsId);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	
	/**
	 * 添加提送货
	 * @param outletsPriceRangeConfInsert
	 * @param outletsPriceRangeConfUpdate
	 * @return
	 */
	public int insert(OutletsPriceRangeConf outletsPriceRangeConf){
		OutletsPriceRangeConf rangeConf = outletsPriceRangeConfMapper.selectByOutletsIdAndCounty(outletsPriceRangeConf.getOutletsId(), outletsPriceRangeConf.getCounty());
		if(rangeConf == null){
			return outletsPriceRangeConfMapper.insert(outletsPriceRangeConf);
		}else{
			return 0;
		}
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public int deleteConf(List<Long> ids){
		return outletsPriceRangeConfMapper.deleteBatch(ids);
	}
	/**
	 * 更新提送货信息
	 * @param outletsPriceRangeConf
	 * @return
	 */
	public int updateConf(OutletsPriceRangeConf outletsPriceRangeConf){
		return outletsPriceRangeConfMapper.updateByPrimaryKeySelective(outletsPriceRangeConf);
	}
	
	/**
	 * 货运交易系统
	 * 获取提送货信息
	 * @param searchParams
	 * @param page
	 * @return
	 */
	public Result selectOutletsConfItems(SearchParams searchParams,Page<?> page){
		Result result = new Result();
		int results = outletsPriceRangeConfMapper.countRows4OutletsConfItems(searchParams);
		List<OutletsPriceRangeConf> rows = outletsPriceRangeConfMapper.selectOutletsConfItems(searchParams, page);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	
	/**
	 * 验证 提送货管理 区域配置是否 已存在
	 * @param county
	 * @return
	 */
	public boolean validIsExistCounty(Long outletsId, String county){
		OutletsPriceRangeConf outletsPriceRangeConf = outletsPriceRangeConfMapper.selectByOutletsIdAndCounty(outletsId, county);
		if(outletsPriceRangeConf == null){
			return true;
		}
		return false;
	}
}
