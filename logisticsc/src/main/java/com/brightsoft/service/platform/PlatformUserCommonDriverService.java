package com.brightsoft.service.platform;

import java.util.List;

import com.brightsoft.model.PlatformUserCommonDriver;
import com.brightsoft.utils.Page;

public interface PlatformUserCommonDriverService {
	
	/**
	 *增加常用司机
	 * @param commonDriver
	 * @return
	 */
	public boolean insertPlatformUserCommonDriver(PlatformUserCommonDriver commonDriver);
	
	/**
	 * 分页获取常用司机
	 */
	public Page<?> selectBySelectedCondition(PlatformUserCommonDriver commonDriver, Page<?> page);
	/**
	 * 删除我的常用司机
	 * @param commonDriver
	 * @return
	 */
	public boolean deleteCommonDriver(List<Long> commonDriver);
	
	public int insertCommonDriver(PlatformUserCommonDriver commonDriver);
	
	public boolean selectPlatformCommonDriverId(PlatformUserCommonDriver commonDriver);
	
	public boolean selectPlatformCommonDriverPhone(PlatformUserCommonDriver commonDriver);
}
