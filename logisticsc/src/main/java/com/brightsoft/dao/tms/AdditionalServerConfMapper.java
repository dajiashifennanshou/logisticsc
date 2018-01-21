package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.model.AdditionalServerConf;

public interface AdditionalServerConfMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdditionalServerConf record);

    int insertSelective(AdditionalServerConf record);

    AdditionalServerConf selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdditionalServerConf record);

    int updateByPrimaryKey(AdditionalServerConf record);
    
    /*AdditionalServerConf selectByCompanyId(Long companyId);*/
    
    AdditionalServerConf selectByOutletsId(Long outletsId);

	AdditionalServerConf selectByLineId(Long lineId);
	
	List<AdditionalServerConf> selectByParamsOfPlatform(BaseSearchParams params);
	
	int selectByParamsOfPlatformCount(BaseSearchParams params);
}