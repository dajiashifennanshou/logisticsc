package com.brightsoft.service.tms.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.tms.OutletsPriceRangeConfMapper;
import com.brightsoft.model.OutletsPriceRangeConf;

/**
 * 网点 提送货管理 配置 service
 * @author yangshoubao
 *
 */
@Service
public class OutletsPriceRangeConfService {

	@Autowired
	private OutletsPriceRangeConfMapper outletsPriceRangeConfMapper;
	
	public List<OutletsPriceRangeConf> selectByOutletsId(Long outletsId){
		return outletsPriceRangeConfMapper.selectNewByOutletsId(outletsId);
	}
}
