package com.yc.Dao;

import java.util.List;

import com.yc.Entity.XzqhInfo;
import com.yc.Tool.ISqlDao;


public interface XzqhInfoMapper extends ISqlDao<XzqhInfo> {
    int deleteByPrimaryKey(String id);

    int insert(XzqhInfo record);

    int insertSelective(XzqhInfo record);

    XzqhInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(XzqhInfo record);

    int updateByPrimaryKey(XzqhInfo record);
    
    List<XzqhInfo> selectByPid(String pid);
}