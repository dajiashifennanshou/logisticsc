package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.SystemLineInfo;
import com.brightsoft.utils.Page;

public interface SystemLineInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemLineInfo record);

    int insertSelective(SystemLineInfo record);

    SystemLineInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemLineInfo record);

    int updateByPrimaryKey(SystemLineInfo record);
    
    int countRows4Page(@Param("searchParams")SearchParams searchParams);
    
    List<SystemLineInfo> selectByCondition4Page(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page);

    int deleteBatch(List<Long> lineIds);
}