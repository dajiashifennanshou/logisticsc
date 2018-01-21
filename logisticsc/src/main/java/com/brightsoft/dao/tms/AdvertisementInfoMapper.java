package com.brightsoft.dao.tms;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.common.enums.PublishTypeEnum;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.AdvertisementInfo;
import com.brightsoft.utils.Page;

public interface AdvertisementInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdvertisementInfo record);

    int insertSelective(AdvertisementInfo record);

    AdvertisementInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdvertisementInfo record);

    int updateByPrimaryKey(AdvertisementInfo record);
    
    /**
     * 获取广告信息列表
     * @param page
     * @return
     */
    List<AdvertisementInfo> selectByTypeAndCondition(@Param("page")Page<?> page,@Param("searchParams")SearchParams searchParams,@Param("type")Integer type);
    List<AdvertisementInfo> selectByCondition(@Param("page")Page<?> page,@Param("searchParams")SearchParams searchParams);
    /**
     * 获取查询总记录数
     * @return
     */
    int countRowsByType(@Param("searchParams")SearchParams searchParams,@Param("type")Integer type);
    int countRows(@Param("searchParams")SearchParams searchParams);
    
    /**
     * 验证添加广告的日期是否可用
     * @return
     */
    int verifyDate(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("adPosition")Integer adPosition);
    /**
     * 查询广告的所有信息，包括web方的位置名称
     * 2016年3月18日 下午1:42:26
     * @param page
     * @param advertisementInfo
     * @return 
     * @author zhouna
     */
	List<?> FinallAdvertInfo(@Param("page")Page<?> page, @Param("advertisementInfo")AdvertisementInfo advertisementInfo);
	/**
	 * 查询广告的总数量
	 * 2016年3月18日 下午1:41:58
	 * @param advertisementInfo
	 * @return 
	 * @author zhouna
	 */
	int countadvertRows(@Param("advertisementInfo")AdvertisementInfo advertisementInfo);

	/***
	 * 通过id删除
	 * 2016年3月23日 上午11:09:29
	 * @param idslist
	 * @return 
	 * @author zhouna
	 */
	int advertManagementDelId(@Param("ids")ArrayList<Long> idslist);

	/**
	 * 添加广告信息
	 * 2016年3月23日 下午1:01:06
	 * @param advertisementInfo
	 * @return 
	 * @author zhouna
	 */
	int advertManagementsave(@Param("advertisementInfo")AdvertisementInfo advertisementInfo);
	/**
	 * 通过类型获取广高信息
	 * @param type
	 * @return
	 */
	int selectByPublishType(@Param("type")Integer type);

}