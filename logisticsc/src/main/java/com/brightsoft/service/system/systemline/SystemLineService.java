package com.brightsoft.service.system.systemline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.platform.SystemLineInfoMapper;
import com.brightsoft.model.SystemLineInfo;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class SystemLineService {

	@Autowired
	private SystemLineInfoMapper systemLineInfoMapper;
	
	/**
	 * 获取线路信息列表
	 * @return
	 */
	public Result selectByCondition4Page(SearchParams searchParams,Page<?> page){
		Result result = new Result();
		int results = systemLineInfoMapper.countRows4Page(searchParams);
		List<SystemLineInfo> rows = systemLineInfoMapper.selectByCondition4Page(searchParams, page);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	
	/**
	 *货运交易系统添加线路信息
	 */
	public boolean insert(SystemLineInfo systemLineInfo){
		boolean flag = false;
		if(systemLineInfoMapper.insert(systemLineInfo)>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 货运交易系统更改线路信息
	 * @param systemLineInfo
	 * @return
	 */
	public boolean update(SystemLineInfo systemLineInfo){
		boolean flag = false;
		if(systemLineInfoMapper.updateByPrimaryKeySelective(systemLineInfo)>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 货运交易系统删除线路信息
	 * @param lineIds
	 * @return
	 */
	public boolean delete(List<Long> lineIds){
		boolean flag = false;
		if(systemLineInfoMapper.deleteBatch(lineIds)>0){
			flag = true;
		}
		return flag;
	}
}
