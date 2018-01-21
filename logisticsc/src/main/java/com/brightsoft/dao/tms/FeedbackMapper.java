package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.Feedback;
import com.brightsoft.model.Role;
import com.brightsoft.model.User;
import com.brightsoft.utils.Page;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Long id);

    /**
     * 添加反馈信息
     * @param record
     * @return
     */
    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);
    
    /**
     * 网点查询获取反馈信息
     * @return
     */
    List<Feedback> selectByOutletsIdAndCondition(@Param("page")Page<?> page,@Param("searchParams")SearchParams searchParams,@Param("outletsId")Long outletsId);

    /**
     * 网点获取查询结果总数
     */
	int countRowsByOutletsId(@Param("searchParams")SearchParams searchParams,@Param("outletsId")Long outletsId);
	 /**
     * 网点查询获取反馈信息
     * @return
     */
    List<Feedback> selectByCompanyIdAndCondition(@Param("page")Page<?> page,@Param("searchParams")SearchParams searchParams,@Param("companyId")Long companyId);

    /**
     * 网点获取查询结果总数
     */
	int countRowsByCompanyId(@Param("searchParams")SearchParams searchParams,@Param("companyId")Long companyId);
	/**
     * 网点查询获取反馈信息
     * @return
     */
    List<Feedback> selectByCondition(@Param("page")Page<?> page,@Param("searchParams")SearchParams searchParams);

    /**
     * 网点获取查询结果总数
     */
	int countRows(@Param("searchParams")SearchParams searchParams);


}