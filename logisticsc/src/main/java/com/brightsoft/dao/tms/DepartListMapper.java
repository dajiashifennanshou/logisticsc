package com.brightsoft.dao.tms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.DepartListSearchParams;
import com.brightsoft.model.DepartList;

public interface DepartListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DepartList record);

    int insertSelective(DepartList record);

    DepartList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DepartList record);

    int updateByPrimaryKey(DepartList record);
    
    List<DepartList> selectByOutletsAndStatus(@Param("outletsId") Long outletsId, @Param("status") Integer status);
    
    //List<DepartList> selectByParam(Map<String, Object> param);
    
    //int selectByParamCount(Map<String, Object> param);
    
    DepartList selectByDepartNumber(String departNumber);
    
    int updateByDepartNumber(DepartList record);
    
    int updateStatusByDepartNumber(@Param("departNumber") String departNumber, @Param("status") Integer status, @Param("date")String date);
    
    List<DepartList> selectByWayOutletsAndStatusList(Map<String, Object> params);
    
    List<DepartList> selectByParams(DepartListSearchParams params);
    
    int selectByParamsCount(DepartListSearchParams params);
    
    double selectShouldByParamsTotal(DepartListSearchParams params);
    
    double selectActualByParamsTotal(DepartListSearchParams params);
    
    /**
     * 查询入库的发车单
     * @param params
     * @return
     */
    List<DepartList> selectStoreDepartByParams(DepartListSearchParams params);
    
    DepartList selectByWayBillNumber(String wayBillNumber);

	List<DepartList> selectByParamsArrive(DepartListSearchParams params);

	Object selectShouldByParamsTotalArrive(DepartListSearchParams params);

	Object selectActualByParamsTotalArrive(DepartListSearchParams params);

	int selectByParamsCountArrive(DepartListSearchParams params);

	List<DepartList> selectExportByParams(DepartListSearchParams params);
}