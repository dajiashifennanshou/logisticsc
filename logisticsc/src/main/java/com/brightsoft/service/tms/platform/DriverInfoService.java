package com.brightsoft.service.tms.platform;

import java.sql.DriverManager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.tms.DriverInfoMapper;
import com.brightsoft.model.DriverInfo;
import com.brightsoft.model.User;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.mysql.jdbc.Driver;

@Service
public class DriverInfoService{

	@Autowired
	private DriverInfoMapper driverInfoMapper;
	
	/**
	 * 网点获取司机信息
	 * @param driverInfo
	 * @param page
	 * @param outletsId
	 * @return
	 */
	public Result selectByOutletsIdAndCondition(DriverInfo driverInfo,Page<?> page,Long outletsId){
		Result result = new Result();
		int results = driverInfoMapper.countRowsByOutletsId(driverInfo, outletsId);
		List<DriverInfo> rows = driverInfoMapper.selectByOutletsIdAndCondition(driverInfo, page, outletsId);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	
	/**
	 * 专线获取司机信息
	 * @param driverInfo
	 * @param page
	 * @param companyId
	 * @return
	 */
	public Result selectByCompanyIdAndCondition(DriverInfo driverInfo,Page<?> page,Long companyId){
		Result result = new Result();
		int results = driverInfoMapper.countRowsByCompanyId(driverInfo, companyId);
		List<DriverInfo> rows = driverInfoMapper.selectByCompanyIdAndCondition(driverInfo, page, companyId);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	
	/**
	 * 获取司机信息
	 */
	public Result selectBySelectedCondition(DriverInfo driverInfo, Page<?> page,User user){
		Result result = new Result();
		int results = driverInfoMapper.countRows(driverInfo,user);
		List<DriverInfo> driverInfos = driverInfoMapper.selectBySelectedCondition(driverInfo, page ,user);
		result.setRows(driverInfos);
		result.setResults(results);
		return result;
	}
	
	/**
	 * 新增司机信息
	 */
	public int insertDriverInfo(DriverInfo driverInfo) {
		return driverInfoMapper.insert(driverInfo);
	}

	/**
	 * 更新司机信息
	 */
	public int updateDriverInfo(DriverInfo driverInfo) {
		return driverInfoMapper.updateByPrimaryKeySelective(driverInfo);
	}

	/**
	 * 删除司机信息
	 */
	public int deleteDriverById(List<Long> driverId) {
		return driverInfoMapper.deleteDriverById(driverId);
	}
	
	/*public DriverInfo updateDriverById(long driverId){
		return driverInfoMapper.updateByPrimaryKey(record)
	}*/
	
	/**
	 * 根据网点ID查询 司机信息
	 * @param outletsId
	 * @return
	 */
	public List<DriverInfo> selectByOutletsId(Long outletsId){
		return driverInfoMapper.selectByOutletsId(outletsId);
	}
	
	/**
	 * 根据公司ID查询 司机信息
	 * @param companyId
	 * @return
	 */
	public List<DriverInfo> selectByCompanyId(Long companyId){
		return driverInfoMapper.selectByCompanyId(companyId);
	}
	
	/**
	 * 根据ID 查询 司机信息
	 * @param id
	 * @return
	 */
	public DriverInfo selectById(Long id){
		return driverInfoMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 货运交易系统
	 * 获取司机详细信息
	 * @return
	 */
	public Result selectDriverItems(SearchParams searchParams,Page<?> page){
		Result result = new Result();
		int results = driverInfoMapper.countRows4DriverItems(searchParams);
		List<DriverInfo> rows = driverInfoMapper.selectDriverItems(searchParams, page);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
}
