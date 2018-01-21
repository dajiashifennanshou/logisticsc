package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.InsuranceType;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.insurance.InsType;

public interface InsuranceTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InsuranceType record);

    int insertSelective(InsuranceType record);

    InsuranceType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InsuranceType record);

    int updateByPrimaryKey(InsuranceType record);
    /**
     * 查询保险类型
     * @param searchParams
     * @param page
     * @return
     */
    List<InsuranceType> selectByCondition(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page);
    /**
     * 获取险种总记录数
     * @param searchParams
     * @return
     */
    int countRows(@Param("searchParams")SearchParams searchParams);
    /**
     * 获取所有保险类型
     * @return
     */
    List<InsuranceType> selectInsType();
    /**
     * 通过保险公司id获取保险类型
     */
    List<InsuranceType> selectByComId(@Param("companyId")Long companyId);
    /**
     * 通过保险公司tag获取险种类型
     * @param companyTag
     * @return
     */
    List<InsuranceType> selectByComTag(@Param("companyTag")String companyTag);
    /**
     * 查询险种类型是否存在
     * @param typeTag
     * @return
     */
    int countByTypeTag(@Param("typeTag")String typeTag);
    /**
     * 批量删除险种
     * @param typeIds
     * @return
     */
    int deleteBatch(String[] typeIds);
    
    List<InsType> selectAllType();
} 