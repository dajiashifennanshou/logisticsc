package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.brightsoft.model.PlatformUserCommonDriver;
import com.brightsoft.utils.Page;

public interface PlatformUserCommonDriverMapper {
/*    int deleteByPrimaryKey(Long id);

    int insert(PlatformUserCommonDriver record);

    int insertSelective(PlatformUserCommonDriver record);

    PlatformUserCommonDriver selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformUserCommonDriver record);

    int updateByPrimaryKey(PlatformUserCommonDriver record);*/
    
    /**
     * 增加常用司机信息
     * @param commonDriver
     * @return
     */
    public int inserPlatformUserCommonDriver(PlatformUserCommonDriver commonDriver);
    
    public int selectPlatformCommonDriverId(PlatformUserCommonDriver commonDriver);
    public int selectPlatformCommonDriverPhone(PlatformUserCommonDriver commonDriver);
    /**
	 * 查询筛选条件我的常用司机
	 * @param commonDriver
	 * @param page
	 * @return
	 */
	public List<PlatformUserCommonDriver> selectBySelectedCondition(@Param("commonDriver") PlatformUserCommonDriver commonDriver,@Param("page")Page<?> page);
	/**
	 * 获取总数
	 * @param commonDriver
	 * @return
	 */
    public int countRows(@Param("commonDriver") PlatformUserCommonDriver commonDriver);
    /**
     * 删除我的常用司机
     * @param commonDriver
     * @return
     */
    public int deleteCommonDriver(@Param("commonDriver")List<Long> commonDriver);
    
    /**
     * 根据手机号 查询常用司机
     * @param phone
     * @return
     */
    public PlatformUserCommonDriver selectByPhoneAndUserId(@Param("phone") String phone, @Param("userId") Long userId);
}