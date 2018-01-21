package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.controller.vo.StatisticsHelper;
import com.brightsoft.model.PvStatistics;

public interface PvStatisticsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PvStatistics record);

    int insertSelective(PvStatistics record);

    PvStatistics selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PvStatistics record);

    int updateByPrimaryKey(PvStatistics record);
    
    int countPv();
    
    List<StatisticsHelper> countPvDay4Month();
}