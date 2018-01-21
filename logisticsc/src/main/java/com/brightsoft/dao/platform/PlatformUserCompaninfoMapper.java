package com.brightsoft.dao.platform;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformUserCompaninfo;

public interface PlatformUserCompaninfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformUserCompaninfo record);

    int insertSelective(PlatformUserCompaninfo record);

    PlatformUserCompaninfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformUserCompaninfo record);

    int updateByPrimaryKey(PlatformUserCompaninfo record);
    
    /**
     * 根据公司id获取公司信息
     * @param companyId
     * @return
     */
    PlatformUserCompaninfo selectByCompanyId(@Param("companyId")Long companyId);
}