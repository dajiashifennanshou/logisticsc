package com.brightsoft.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.SysNews;
import com.brightsoft.utils.Page;

public interface SysNewsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysNews record);

    int insertSelective(SysNews record);

    SysNews selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysNews record);

    int updateByPrimaryKeyWithBLOBs(SysNews record);

    int updateByPrimaryKey(SysNews record);
    
    public List<SysNews> selectByCondition(@Param("page")Page<?> page,@Param("searchParams")SearchParams searchParams);
    
    public int countRows(@Param("searchParams")SearchParams searchParams);
    
    public int deleteNews(@Param("ids")List<Long> ids);
    
    public List<SysNews> selectNewsList(SysNews news);
}