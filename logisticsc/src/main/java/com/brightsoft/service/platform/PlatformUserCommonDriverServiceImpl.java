package com.brightsoft.service.platform;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.dao.platform.PlatformUserCommonDriverMapper;
import com.brightsoft.model.PlatformUserCommonDriver;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.WebConstant;

@Service("platformUserCommonDriver")
public class PlatformUserCommonDriverServiceImpl implements PlatformUserCommonDriverService{
	
	@Autowired
	public PlatformUserCommonDriverMapper commonDriverMapper;
	
	@Autowired
	private DictionaryService dictionaryService;
	/**
	 * 添加常用司机
	 */
	public boolean insertPlatformUserCommonDriver(
			PlatformUserCommonDriver commonDriver) {
		commonDriver.setCreateTime(new Date());
		if(commonDriverMapper.inserPlatformUserCommonDriver(commonDriver) > 0){
			return true;
		}
		return false;
	}
	/**
	 * 获取常用司机
	 */
	public Page<?> selectBySelectedCondition(
			PlatformUserCommonDriver commonDriver, Page<?> page) {
		int results = commonDriverMapper.countRows(commonDriver);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<PlatformUserCommonDriver> commonDrivers = commonDriverMapper.selectBySelectedCondition(commonDriver, page);
		for (int i = 0; i < commonDrivers.size(); i++) {
			commonDrivers.get(i).setVehicleTypeName(dictionaryService.selectByPrimaryId(Long.parseLong(commonDrivers.get(i).getVehicleType()),DictionaryType.VEHICLE_TYPE).getName());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, commonDrivers);
		page.setParams(map);
		return page;
	}
	/**
	 * 删除我的常用司机
	 */
	public boolean deleteCommonDriver(List<Long> commonDriver) {
		if(commonDriverMapper.deleteCommonDriver(commonDriver) >0){
			return true;
		}
		return false;
	}
	
	/**
	 * 添加常用司机
	 */
	public int insertCommonDriver(PlatformUserCommonDriver commonDriver){
		String phone = commonDriver.getPhoneNumber();
		Long userId = commonDriver.getUserId();
		PlatformUserCommonDriver driver = commonDriverMapper.selectByPhoneAndUserId(phone, userId);
		if(driver != null){
			return 0;
		}
		commonDriver.setCreateTime(new Date());
		return commonDriverMapper.inserPlatformUserCommonDriver(commonDriver);
	}
	public boolean selectPlatformCommonDriverId(
			PlatformUserCommonDriver commonDriver) {
		if(commonDriverMapper.selectPlatformCommonDriverId(commonDriver) > 0){
			return true;
		}
		return false;
	}
	public boolean selectPlatformCommonDriverPhone(
			PlatformUserCommonDriver commonDriver) {
		if(commonDriverMapper.selectPlatformCommonDriverPhone(commonDriver) > 0){
			return true;
		}
		return false;
	}
}
