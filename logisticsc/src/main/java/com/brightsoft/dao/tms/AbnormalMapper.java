package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.Abnormal;
import com.brightsoft.model.User;
import com.brightsoft.utils.Page;

public interface AbnormalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Abnormal record);

    int insertSelective(Abnormal record);

    Abnormal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Abnormal record);

    int updateByPrimaryKey(Abnormal record);
    
    List<Abnormal> selectByCondition(@Param("page")Page<?> page,@Param("user")User user,@Param("searchParams")SearchParams searchParams);
    
    int countAbnormal(@Param("user")User user,@Param("searchParams")SearchParams searchParams);
    
    int updateByWayBillNumberSelective(Abnormal record);
}