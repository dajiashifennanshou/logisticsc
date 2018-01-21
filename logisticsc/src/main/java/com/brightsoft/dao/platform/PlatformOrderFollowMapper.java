package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformOrderFollow;
import com.brightsoft.model.PlatformOrderTracking;

public interface PlatformOrderFollowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformOrderFollow record);

    int insertSelective(PlatformOrderFollow record);

    PlatformOrderFollow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformOrderFollow record);

    int updateByPrimaryKey(PlatformOrderFollow record);
    
    /**
	 * 获取订单跟踪 起始线路 结束线路
	 * @param orderTracking
	 * @return
	 */
	public PlatformOrderTracking selectOrder(@Param("orderTracking")PlatformOrderTracking orderTracking);
	/**
	 * 获取订单跟踪 节点信息
	 * @param orderTransactionFlow
	 * @return
	 */
	public List<PlatformOrderFollow> selectOrderFollow(@Param("orderTracking")PlatformOrderTracking orderTracking);
}