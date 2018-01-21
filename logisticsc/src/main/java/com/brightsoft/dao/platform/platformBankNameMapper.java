package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.platformBankName;

public interface platformBankNameMapper {
    int deleteByPrimaryKey(Long id);

    int insert(platformBankName record);

    int insertSelective(platformBankName record);

    platformBankName selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(platformBankName record);

    int updateByPrimaryKey(platformBankName record);
    
    public List<platformBankName> selectProvinceName();
    
    public List<platformBankName> selectCityName(@Param("provinceName") String provinceName);
    
    public List<platformBankName> selectHeadName(@Param("provinceName") String provinceName,@Param("cityName") String cityName);
    
    public List<platformBankName> selectBranchName(@Param("headName") String headName,@Param("provinceName") String provinceName,@Param("cityName") String cityName);
}