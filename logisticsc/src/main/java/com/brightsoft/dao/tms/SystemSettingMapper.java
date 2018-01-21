package com.brightsoft.dao.tms;

import com.brightsoft.model.SystemSetting;

public interface SystemSettingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemSetting record);

    int insertSelective(SystemSetting record);

    SystemSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemSetting record);

    int updateByPrimaryKey(SystemSetting record);
}