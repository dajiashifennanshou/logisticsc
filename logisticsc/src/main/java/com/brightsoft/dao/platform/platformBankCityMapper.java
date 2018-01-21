package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.platformBankCity;

public interface platformBankCityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(platformBankCity record);

    int insertSelective(platformBankCity record);

    platformBankCity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(platformBankCity record);

    int updateByPrimaryKey(platformBankCity record);
    
    /**
     * 获取省
     * @return
     */
    public List<platformBankCity> selectBankProvince();
    /**
     * 获取城市
     */
    public List<platformBankCity> selectBankCity(@Param("province") String province);
}