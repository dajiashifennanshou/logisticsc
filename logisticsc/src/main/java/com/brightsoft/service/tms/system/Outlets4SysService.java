package com.brightsoft.service.tms.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.tms.CustomerMapper;
import com.brightsoft.dao.tms.DriverInfoMapper;
import com.brightsoft.dao.tms.LineInfoMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.dao.tms.VehicleInfoMapper;
import com.brightsoft.model.Customer;
import com.brightsoft.model.DriverInfo;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class Outlets4SysService {

	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private LineInfoMapper lineInfoMapper;
	
	@Autowired
	private VehicleInfoMapper vehicleInfoMapper;
	
	@Autowired
	private DriverInfoMapper driverInfoMapper;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	/**
	 * 查询所有网点
	 * @param outletsInfo
	 * @param page
	 * @return
	 */
	public Result selectByCondition(OutletsInfo outletsInfo,Page<?> page){
		return outletsService.selectBySelectedCondition(null, outletsInfo, page);
	}
	
	/**
	 * 通过outletsId获取线路信息
	 * @param page
	 * @param outletsId
	 * @return
	 */
	public Result selectLineInfoByOutletsId(Page<?> page,long outletsId){
		Result result = new Result();
//		int results = lineInfoMapper.countRowsByOutletsId4Page(outletsId);
//		List<LineInfo> rows = lineInfoMapper.selectByOutletsId4Page(page, outletsId);
//		result.setResults(results);
//		result.setRows(rows);
		return result;
	}
	
	/**
	 * 通过outletsId获取车辆信息
	 * @param page
	 * @param outletsId
	 * @return
	 */
	/*public Result selectVehicleInfoByOutletsId(Page<?> page,long outletsId){
		Result result = new Result();
		int results = vehicleInfoMapper.countRowsByOutletsId(outletsId);
		List<VehicleInfo> rows = vehicleInfoMapper.selectByOutletsId4Page(page, outletsId);
		result.setRows(rows);
		result.setResults(results);
		return result;
	}*/
	
	/**
	 * 通过outletsId获取客户信息
	 * @param page
	 * @param outletsId
	 * @return
	 */
	public Result selectCustomerByOutletsId(Page<?> page,long outletsId){
		Result result = new Result();
//		int results = customerMapper.countRowsByOutletsId(outletsId);
		List<Customer> rows = customerMapper.selectByOutletsId(page, outletsId);
//		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	
	/**
	 * 通过outletsId获取司机信息
	 * @param page
	 * @param outletsId
	 * @return
	 */
	public Result selectDriverInfoByOutlets(Page<?> page,long outletsId){
		Result result = new Result();
//		int results = driverInfoMapper.countRowsByOutletsId(outletsId);
		List<DriverInfo> rows = driverInfoMapper.selectByOutletsId4Page(page, outletsId);
//		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	
	public OutletsInfo selectOutletsDetailById(Long outletsId){
		return outletsInfoMapper.selectOutletsDetailById(outletsId);
	}
}
