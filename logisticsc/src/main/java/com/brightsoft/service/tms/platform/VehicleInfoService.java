package com.brightsoft.service.tms.platform;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.tms.VehicleInfoMapper;
import com.brightsoft.model.DriverInfo;
import com.brightsoft.model.VehicleInfo;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class VehicleInfoService{

	@Autowired
	private VehicleInfoMapper vehicleInfoMapper;
	
	@Autowired
	private DriverInfoService driverInfoService;
	
	/**
	 * 网点获取车辆
	 * @param outletsId
	 * @param vehicleInfo
	 * @param page
	 * @return
	 */
	public Result selectByOutletsIdAndCondition(Long outletsId,SearchParams searchParams,Page<?> page){
		Result result = new Result();
		int results = vehicleInfoMapper.countRowsByOutletsId(searchParams, outletsId);
		List<VehicleInfo> rows = vehicleInfoMapper.selectByOutletsIdAndCondition(searchParams, page, outletsId);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	/**
	 * 专线获取车辆
	 * @param outletsId
	 * @param vehicleInfo
	 * @param page
	 * @return
	 */
	public Result selectByCompanyIdAndCondition(Long companyId,SearchParams searchParams,Page<?> page){
		Result result = new Result();
		int results = vehicleInfoMapper.countRowsByCompanyId(searchParams, companyId);
		List<VehicleInfo> rows = vehicleInfoMapper.selectByCompanyIdAndCondition(searchParams, page, companyId);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	/**
	 * 通过条件获取车辆信息
	 */
//	public Result selectBySelectedCondition(VehicleInfo vehicleInfo,Page<?> page,User user) {
//		Result result = new Result();
//		int results = vehicleInfoMapper.countRows(vehicleInfo,user);
//		List<VehicleInfo> vehicleInfos = vehicleInfoMapper.selectBySelectedCondition(vehicleInfo, page,user);
//		result.setResults(results);
//		result.setRows(vehicleInfos);
//		return result;
//	}

	/**
	 * 新增车辆信息
	 */
	public int insertVehicleInfo(VehicleInfo vehicleInfo,DriverInfo driverInfo) {
		VehicleInfo vehicleInfoTemp = vehicleInfoMapper.selectByOutletsIdAndVehicleNumber(vehicleInfo.getOutletsId(), vehicleInfo.getPlateNumber());
		if(vehicleInfoTemp == null){
			driverInfoService.insertDriverInfo(driverInfo);
			return vehicleInfoMapper.insert(vehicleInfo);
		}else{
			return 0;
		}
	}

	/**
	 * 更新车辆信息
	 */
	public int updateVehicleInfo(List<Long> vehicleId,List<Long> driverId) {
		return vehicleInfoMapper.deleteVehicleById(vehicleId);
	}

	/**
	 * 删除车辆信息
	 */
	public Boolean deleteVehicle(String[] vehicleIds) {
		Boolean flag = false;
		if(vehicleIds!=null
				&&vehicleIds.length>0){
			List<Long> idList = new ArrayList<Long>();
			for (String vehicleId : vehicleIds) {
				idList.add(Long.parseLong(vehicleId));
			}
			if(vehicleInfoMapper.deleteVehicleById(idList)>0){
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 根据网点ID获取车辆信息
	 * @param outletsId
	 * @return
	 */
	public List<VehicleInfo> selectByOutletsId(long outletsId){
		return vehicleInfoMapper.selectByOutletsId(outletsId);
	}
	/**
	 * 通过id获取车辆信息
	 * @param vehicleId
	 * @return
	 */
	public VehicleInfo selectVehicleByid(long vehicleId){
		return vehicleInfoMapper.selectVehicleById(vehicleId);
	}
	
	/**
	 * 更新车辆信息
	 * @param vehicleInfo
	 * @param driverInfo 
	 * @return
	 */
	public int updateVehicleInfo(VehicleInfo vehicleInfo,DriverInfo driverInfo){
		if(driverInfoService.updateDriverInfo(driverInfo)>0){
			return vehicleInfoMapper.updateByPrimaryKeySelective(vehicleInfo);
		}
		return 0;
	}
	
	/**
	 * 货运交易系统
	 * 获取车辆信息
	 * @param searchParams
	 * @param page
	 * @return
	 */
	public Result selectVehicleItems(SearchParams searchParams,Page<?> page){
		Result result = new Result();
		int results = vehicleInfoMapper.countRows4VehicleItems(searchParams);
		List<VehicleInfo> rows = vehicleInfoMapper.selectVehicleItems(searchParams, page);
		result.setRows(rows);
		result.setResults(results);
		return result;
	}
	
	/**
	 * 根据车牌号  查询车辆信息
	 * @param vehicleNumber
	 * @return
	 */
	public VehicleInfo selectByVehicleNumberAndOutletsId(String vehicleNumber, Long outletsId){
		return vehicleInfoMapper.selectByVehicleNumberAndOutletsId(vehicleNumber, outletsId);
	}
	
	/**
	 * 验证 车辆是否 已添加
	 * @param outletsId
	 * @param vehicleNumber
	 * @return
	 */
	public boolean validIsExistVehicle(Long outletsId, String vehicleNumber){
		VehicleInfo vehicleInfo = vehicleInfoMapper.selectByOutletsIdAndVehicleNumber(outletsId, vehicleNumber);
		if(vehicleInfo == null){
			return true;
		}
		return false;
	}
}
