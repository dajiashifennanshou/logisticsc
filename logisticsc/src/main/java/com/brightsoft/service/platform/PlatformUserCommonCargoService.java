package com.brightsoft.service.platform;

import java.util.List;

import com.brightsoft.model.PlatformUserCommonCargo;
import com.brightsoft.utils.Page;

public interface PlatformUserCommonCargoService {
	
	/**
	 * 分页获取常发货物
	 */
	public Page<?> selectBySelectedCondition(PlatformUserCommonCargo commonCargo, Page<?> page);
	/**
	 * 增加我的常发货物
	 * @param commonCargo
	 * @return
	 */
	public boolean insertCommonCargo(PlatformUserCommonCargo commonCargo);
	/**
	 * 逻辑删除我的常发货物
	 * @param commonCargoId
	 * @return
	 */
	public boolean updateCommonCargo(List<Long> commonCargoId);
	
	public int insertDiffCommonCargo(PlatformUserCommonCargo commonCargo);
	
	public boolean selectCommonCargoName(PlatformUserCommonCargo commonCargo);
}
