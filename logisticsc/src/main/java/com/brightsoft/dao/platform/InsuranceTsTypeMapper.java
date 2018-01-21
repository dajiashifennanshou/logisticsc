package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.InsuranceTsType;
import com.brightsoft.utils.Page;
public interface InsuranceTsTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InsuranceTsType record);

    int insertSelective(InsuranceTsType record);

    InsuranceTsType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InsuranceTsType record);

    int updateByPrimaryKey(InsuranceTsType record);
    /**
     * 查询特殊类型
     * @param searchParams
     * @param page
     * @return
     */
    List<InsuranceTsType> selectByCondition(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page);
    /**
     * 获取记录数
     * @param searchParams
     * @return
     */
    int countRows(@Param("searchParams")SearchParams searchParams);
    /**
     * 获取所有特殊类型
     * @return
     */
    List<InsuranceTsType> selectInsTsType();
    /**
     * 更加公司Id获取特殊类型
     * @param companyId
     * @return
     */
    List<InsuranceTsType> selectByComId(@Param("companyId")Long companyId);
    /**
     * 根据公司tag获取特殊类型
     * @param companyTag
     * @return
     */
    List<InsuranceTsType> selectByComTag(@Param("companyTag")String companyTag);
    /**
     * 查询特殊类型标签是否存在
     * @param tsTypeTag
     * @return
     */
    int countByTsTypeTag(@Param("tsTypeTag")String tsTypeTag);
    /**
     * 批量删除特殊类型
     * @param tsTypeIds
     * @return
     */
    int deleteBatch(String[] tsTypeIds);
}