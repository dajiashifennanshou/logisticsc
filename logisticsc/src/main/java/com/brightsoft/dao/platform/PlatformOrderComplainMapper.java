package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformOrderComplain;
import com.brightsoft.model.PlatformOrderMineComplain;
import com.brightsoft.model.User;
import com.brightsoft.utils.Page;

public interface PlatformOrderComplainMapper {

	
//    int deleteByPrimaryKey(Long id);
//
//    int insert(PlatformOrderComplain record);
//
//    int insertSelective(PlatformOrderComplain record);
//
    PlatformOrderComplain selectByPrimaryKey(Long id);
//
	/**
	 * 投诉处理
	 * @param record
	 * @return
	 */
    int updateByPrimaryKeySelective(PlatformOrderComplain record);
//
    int updateByPrimaryKey(PlatformOrderComplain record);

	/**
	 * 获取查询结果总是
	 * @param platformOrderComplain
	 * @param user
	 * @return
	 */
	int countRows(@Param("platformOrderComplain")PlatformOrderComplain platformOrderComplain, @Param("user")User user);
	
	/**
	 * 查询投诉信息
	 * @param page
	 * @param platformOrderComplain
	 * @param user
	 * @return
	 */
	List<PlatformOrderComplain> selectByCondition(@Param("page")Page<?> page, @Param("platformOrderComplain")PlatformOrderComplain platformOrderComplain,@Param("user")User user);
	
	/**
	 * 货运交易系统查询投诉信息
	 * @param page
	 * @param PlatformOrderMineComplain
	 * @param user
	 * @return
	 */
	List<PlatformOrderMineComplain> selectByConditionPlatfrom(@Param("mineComplain")PlatformOrderMineComplain mineComplain,@Param("page")Page<?> page);
	/**
	 * 查看投诉详情
	 * @param complaintId
	 * @return
	 */
	public PlatformOrderMineComplain selectOrderMineComplain(@Param("complaintId")Long complaintId);
	/**
	 * 货运交易系统获取记录总数
	 * @param PlatformOrderMineComplain
	 * @param user
	 * @return
	 */
	int countRowsPlatfrom(@Param("mineComplain")PlatformOrderMineComplain mineComplain);
	
	/**
	 * 货运交易系统获取记录总数
	 * @param PlatformOrderMineComplain
	 * @param user
	 * @return
	 */
	int countRowsPlatfromList(@Param("mineComplain")PlatformOrderMineComplain mineComplain);
	/**
	 * 增加投诉
	 * @param complain
	 * @return
	 */
	public int insertComplain(PlatformOrderComplain complain);
	/**
	 * 订单是否已投诉
	 * @param orderNumber
	 * @return
	 */
	public int selectComplain(@Param("orderNumber")String orderNumber);
	
	
	List<PlatformOrderMineComplain> selectByConditionPlatfromList(@Param("mineComplain")PlatformOrderMineComplain mineComplain,@Param("page")Page<?> page);
}