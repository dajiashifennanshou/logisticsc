package com.brightsoft.service.platform;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformUserCommonCargoMapper;
import com.brightsoft.model.PlatformUserCommonCargo;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.WebConstant;
@Service("platformUserCommonCargo")
public class PlatformUserCommonCargoServiceImpl implements PlatformUserCommonCargoService{
	
	@Autowired
	public PlatformUserCommonCargoMapper cargoMapper;
	/**
	 * 我的常发货物
	 */
	public Page<?> selectBySelectedCondition(
			PlatformUserCommonCargo commonCargo, Page<?> page) {
		int results = cargoMapper.countRows(commonCargo);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<PlatformUserCommonCargo> commonCargos = cargoMapper.selectBySelectedCondition(commonCargo, page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, commonCargos);
		page.setParams(map);
		return page;
	}
	/**
	 * 增加我的常发货物
	 */
	public boolean insertCommonCargo(PlatformUserCommonCargo commonCargo) {
		commonCargo.setCreateTime(new Date());
		commonCargo.setSingleWeight(0.0);
		commonCargo.setSingleVolume(0.0);
		
		PlatformUserCommonCargo commonCargoTemp = cargoMapper.selectByUserIdAndName(commonCargo.getUserId(), commonCargo.getCargoName());
		if(commonCargoTemp == null){
			int rows = cargoMapper.insertCommonCargo(commonCargo);
			if(rows > 0){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	/**
	 * 逻辑删除我的常发货物
	 */
	public boolean updateCommonCargo(List<Long> commonCargoId) {
		if(cargoMapper.updateCommonCargo(commonCargoId,Const.PLATFORMUSER_USER_CONSUME_COMMON_CARGO_STATE_0)>0){
			return true;
		}
		return false;
	}
	
	public int insertDiffCommonCargo(PlatformUserCommonCargo commonCargo) {
		String name = commonCargo.getCargoName();
		PlatformUserCommonCargo cargo = cargoMapper.selectByName(name);
		if(cargo != null){
			return 0;
		}
		return cargoMapper.insertCommonCargo(commonCargo);
	}
	public boolean selectCommonCargoName(PlatformUserCommonCargo commonCargo) {
		if(cargoMapper.selectCommonCargoName(commonCargo) > 0){
			return true;
		}
		return false;
	}
}
