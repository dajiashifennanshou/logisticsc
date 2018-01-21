package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformLineConsumeRecord;
import com.brightsoft.model.PlatformUserLineConsumeRecord;
import com.brightsoft.utils.Page;

public interface PlatformUserLineConsumeRecordMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(PlatformUserLineConsumeRecord record);
//
//    int insertSelective(PlatformUserLineConsumeRecord record);
//
//    PlatformUserLineConsumeRecord selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(PlatformUserLineConsumeRecord record);
//
//    int updateByPrimaryKey(PlatformUserLineConsumeRecord record);
	
	 /**
		 * 查询筛选条件我的预充值消费记录
		 * @param commonDriver
		 * @param page
		 * @return
		 */
		public List<PlatformLineConsumeRecord> selectBySelectedCondition(@Param("consumeRecord") PlatformLineConsumeRecord consumeRecord,@Param("page")Page<?> page);
		/**
		 * 获取总数
		 * @param commonDriver
		 * @return
		 */
	    public int countRows(@Param("consumeRecord") PlatformLineConsumeRecord consumeRecord);
	    /**
	     * 增加线路充值记录表
	     * @param record
	     * @return
	     */
	    public int insertUserLineConsumeRecord(PlatformUserLineConsumeRecord record);
	
}