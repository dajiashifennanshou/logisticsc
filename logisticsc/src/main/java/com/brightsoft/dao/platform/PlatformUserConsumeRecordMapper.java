package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;

import com.brightsoft.controller.vo.ConsumeRecordSearchParams;
import com.brightsoft.model.PlatformUserConsumeRecord;
import com.brightsoft.utils.Page;

public interface PlatformUserConsumeRecordMapper {

    /**
	 * 我的消费记录
	 * @param commonDriver
	 * @param page
	 * @return
	 */
	public List<PlatformUserConsumeRecord> selectBySelectedCondition(@Param("page")Page<?> page,@Param("userId")Long userId,@Param("consumeType") String consumeType);
	/**
	 * 获取总数
	 * @param commonDriver
	 * @return
	 */
    public int countRows(@Param("userId")Long userId,@Param("consumeType") String consumeType);
    
    /**
     * 获取保险消费记录
     * @param searchParams
     * @param page
     * @return
     */
    public List<PlatformUserConsumeRecord> selectInsRecByParams(@Param("searchParams")SearchParams searchParams,@Param("consumeType")Integer consumeType,@Param("page")Page<?> page);
    
    /**
     * 获取保险消费记录总数
     * @param searchParams
     * @return
     */
    public int countRows4InsByParams(@Param("searchParams")SearchParams searchParams,@Param("consumeType")Integer consumeType);
    
    public List<PlatformUserConsumeRecord> selectByParams(ConsumeRecordSearchParams params);
    
    public int selectByParamsCount(ConsumeRecordSearchParams params);
    /**
     * 增加我的消费记录
     * @param consumeRecord
     * @return
     */
    public int insertConsumeRecord(PlatformUserConsumeRecord consumeRecord);
}