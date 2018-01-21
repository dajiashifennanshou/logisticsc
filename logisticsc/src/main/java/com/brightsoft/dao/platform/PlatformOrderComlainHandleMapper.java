package com.brightsoft.dao.platform;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformOrderComlainHandle;

public interface PlatformOrderComlainHandleMapper {
//    int deleteByPrimaryKey(Long id);
//
	/**
	 * 添加投诉处理
	 * @param record
	 * @return
	 */
    int insert(PlatformOrderComlainHandle record);
//
//    int insertSelective(PlatformOrderComlainHandle record);
//
//    PlatformOrderComlainHandle selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(PlatformOrderComlainHandle record);
//
//    int updateByPrimaryKey(PlatformOrderComlainHandle record);
    /**
     * 查看投诉回复信息
     * @param complaintId
     * @return
     */
    public PlatformOrderComlainHandle selectComlainHandle(@Param("complaintId") Long complaintId);
    /**
     * 修改投诉回复满意程度
     * @param comlainHandle
     * @return
     */
    public int updateComlainHandle(PlatformOrderComlainHandle comlainHandle);
}