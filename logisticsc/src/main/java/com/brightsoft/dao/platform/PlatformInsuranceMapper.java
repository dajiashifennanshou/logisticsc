package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.PlatformInsurance;
import com.brightsoft.utils.Page;

public interface PlatformInsuranceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformInsurance record);

    int insertSelective(PlatformInsurance record);

    PlatformInsurance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformInsurance record);

    int updateByPrimaryKey(PlatformInsurance record);
    
    int updateByInsOrderNumSelective(PlatformInsurance platformInsurance);
    
    List<PlatformInsurance> selectByCondition(@Param("userId")long userId,@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page);
    
    int countRowsByCondition(@Param("userId")long userId,@Param("searchParams")SearchParams searchParams);

	int countplatformInsuranceRows(@Param("platformInsurance")PlatformInsurance platformInsurance);

	/**查询保险所有信息*/
	List<PlatformInsurance> OperSogoListfindall(@Param("page") Page<?> page,@Param("platformInsurance")PlatformInsurance platformInsurance);
	
	/**通过id查看保险信息
	 * @param id */
	PlatformInsurance OperSogoById(@Param("id")long id);
	
	/**
	 * 货运交易系统：
	 * 分页查询保单信息
	 * @param searchParams
	 * @param page
	 * @return
	 */
	List<PlatformInsurance> selectInsByParams(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page);
	
	/**
	 * 货运交易系统：
	 * 获取保单记录数
	 * @param searchParams
	 * @return
	 */
	int countRowsByParams(@Param("searchParams")SearchParams searchParams);
	
	PlatformInsurance selectByInsOrderNum(String insOrderNum);
	
	int updateInsuranceStatus(@Param("insOrderNum")String insOrderNum,@Param("insStatus")Integer insStatus);
}