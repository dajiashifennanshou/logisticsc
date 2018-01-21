package com.yc.Dao;

import com.yc.Entity.AdditionalServerConf;
import com.yc.Tool.ISqlDao;


public interface AdditionalServerConfMapper extends ISqlDao<AdditionalServerConf> {
    int deleteByPrimaryKey(Long id);

    int insert(AdditionalServerConf record);

    int insertSelective(AdditionalServerConf record);

    AdditionalServerConf selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdditionalServerConf record);

    int updateByPrimaryKey(AdditionalServerConf record);
    
    /*AdditionalServerConf selectByCompanyId(Long companyId);*/
    
    AdditionalServerConf selectByOutletsId(Long outletsId);

	AdditionalServerConf selectByLineId(Long lineId);
	
}