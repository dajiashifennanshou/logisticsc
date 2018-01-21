package com.brightsoft.service.tms.platform.startsitemanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.CountCostModeEnum;
import com.brightsoft.dao.tms.LadingOrderCargoInfoMapper;
import com.brightsoft.model.LadingOrderCargoInfo;

@Service
public class LadingOrderCargoInfoService {
    
	@Autowired
	private LadingOrderCargoInfoMapper ladingOrderCargoInfoMapper;
	
	/**
	 * 根据运单号 查询提货单信息
	 * @param wayBillNumber
	 * @returno
	 */
	public List<LadingOrderCargoInfo> selectByWayBillNumber(String wayBillNumber){
		List<LadingOrderCargoInfo> ladingOrderCargoInfos = ladingOrderCargoInfoMapper.selectByWayBillNumber(wayBillNumber);
		for (LadingOrderCargoInfo ladingOrderCargoInfo : ladingOrderCargoInfos) {
			CountCostModeEnum[] enums = CountCostModeEnum.values();
			for (CountCostModeEnum modeEnum : enums) {
				if(ladingOrderCargoInfo.getCountCostMode() == modeEnum.getValue()){
					ladingOrderCargoInfo.setCountCostModeName(modeEnum.getName());
				}
			}
		}
		return ladingOrderCargoInfos;
	}
	
}