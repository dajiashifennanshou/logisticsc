package com.brightsoft.dao.platform;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformOrderEvaluationReply;

public interface PlatformOrderEvaluationReplyMapper {
//    int deleteByPrimaryKey(Long id);
//
	/**
	 * 添加回复
	 * @param record
	 * @return
	 */
    int insert(PlatformOrderEvaluationReply record);
    
    public PlatformOrderEvaluationReply selectEvaluationReply(@Param("id") Long id);
//
//    int insertSelective(PlatformOrderEvaluationReply record);
//
//    PlatformOrderEvaluationReply selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(PlatformOrderEvaluationReply record);
//
//    int updateByPrimaryKey(PlatformOrderEvaluationReply record);
}