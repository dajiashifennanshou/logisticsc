package com.brightsoft.dao.platform;

import java.util.List;

import com.brightsoft.model.XzqhInfo;

public interface XzqhInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(XzqhInfo record);

    int insertSelective(XzqhInfo record);

    XzqhInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(XzqhInfo record);

    int updateByPrimaryKey(XzqhInfo record);
    
    List<XzqhInfo> selectByPid(String pid);
}