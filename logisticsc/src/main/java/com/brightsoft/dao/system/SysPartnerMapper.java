package com.brightsoft.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.SysPartner;
import com.brightsoft.utils.Page;

public interface SysPartnerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysPartner record);

    int insertSelective(SysPartner record);

    SysPartner selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPartner record);

    int updateByPrimaryKey(SysPartner record);
    
    public List<SysPartner> selectByCondition(@Param("page")Page<?> page,@Param("searchParams")SearchParams searchParams);
    
    int countRows(@Param("searchParams")SearchParams searchParams);
    
    public int deletetPartner(@Param("ids")List<Long> ids);
    
    public List<SysPartner> selectSysPartnerList();
}