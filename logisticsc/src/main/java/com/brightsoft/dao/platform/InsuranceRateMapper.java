package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.InsuranceRate;
import com.brightsoft.utils.Page;

public interface InsuranceRateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InsuranceRate record);

    int insertSelective(InsuranceRate record);

    InsuranceRate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InsuranceRate record);

    int updateByPrimaryKey(InsuranceRate record);
    
    /**
     * 获取保险费用信息
     * @param searchParams
     * @param page
     * @return
     */
    List<InsuranceRate> selectByCondition(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page);
    
    /**
     * 获取记录数
     * @param searchParams
     * @return
     */
    int countRows(@Param("searchParams")SearchParams searchParams);
    
    /**
     * 根据公司tag,险种tag，特殊类型tag获取费率
     * @param insCompanyTag
     * @param insTypeTag
     * @param insTsTypeTag
     * @return
     */
    InsuranceRate selectByComTyTsTY(@Param("insCompanyTag")String insCompanyTag,@Param("insTypeTag")String insTypeTag,@Param("insTsTypeTag")String insTsTypeTag);
    /**
     * 批量删除费率
     * @param tsTypeIds
     * @return
     */
    int deleteBatch(String[] rateIds);
    int updateInsRate(InsuranceRate insuranceRate);
    int countByComAndType(@Param("comTag")String comTag,@Param("typeTag")String typeTag);
    int countByComAndTsType(@Param("comTag")String comTag,@Param("tsTypeTag")String tsTypeTag);
}