package com.brightsoft.dao.tms;

import com.brightsoft.model.AbnormalHandle;

public interface AbnormalHandleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AbnormalHandle record);

    int insertSelective(AbnormalHandle record);

    AbnormalHandle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AbnormalHandle record);

    int updateByPrimaryKey(AbnormalHandle record);
    
    AbnormalHandle selectByAbnormalId(Long abnormalId);
}