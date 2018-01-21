package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.OutDepartList;

public interface OutDepartListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OutDepartList record);

    int insertSelective(OutDepartList record);

    OutDepartList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OutDepartList record);

    int updateByPrimaryKey(OutDepartList record);
    
    List<OutDepartList> selectByParams(SearchParams params);
    
    int selectByParamsCount(SearchParams params);
    
    OutDepartList selectByOutDepartNumber(String outDepartNumber);

	int updateStatusByOutDepartNumber(@Param("outDepartNumber") String outDepartNumber, @Param("status") int status);
	
	List<OutDepartList> selectByOutletsAndStatus(@Param("startOutlets") Long startOutlets, @Param("status") int status);

	List selectExportByParams(SearchParams params);
}