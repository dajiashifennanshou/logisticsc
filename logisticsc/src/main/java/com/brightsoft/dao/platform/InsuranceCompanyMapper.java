package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.InsuranceCompany;
import com.brightsoft.utils.Page;

public interface InsuranceCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InsuranceCompany record);

    int insertSelective(InsuranceCompany record);

    InsuranceCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InsuranceCompany record);

    int updateByPrimaryKey(InsuranceCompany record);
    /**
     * 查询保险公司信息
     * @param searchParams
     * @param page
     * @return
     */
    List<InsuranceCompany> selectByCondition(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page);
    /**
     * 获取记录数
     * @param searchParams
     * @return
     */
    int countRows(@Param("searchParams")SearchParams searchParams);
    /**
     * 获取所有保险公司
     * @return
     */
    List<InsuranceCompany> selectInsCompany();
    /**
     * 查询公司是否存在
     * @param comTag
     * @return
     */
    int countByComTag(@Param("comTag")String comTag);
    /**
     * 批量删除保险公司
     * @param comIds
     * @return
     */
    int deleteBatch(String[] comIds);
}