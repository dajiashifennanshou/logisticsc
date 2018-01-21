package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformUserBankCard;

public interface PlatformUserBankCardMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(PlatformUserBankCard record);
//
//    int insertSelective(PlatformUserBankCard record);
//
//    PlatformUserBankCard selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(PlatformUserBankCard record);
//
//    int updateByPrimaryKey(PlatformUserBankCard record);
	
	/**
	 * 添加银行卡信息
	 * @param bankCard
	 * @return
	 */
	public int insertUserBankCard(PlatformUserBankCard bankCard);
	/**
	 * 查看当前用户银行卡
	 * @param userId
	 * @return
	 */
	public int selectUserBankCard(@Param("userId")Long userId);
	/**
	 * 查看当前用户银行卡
	 * @param userId
	 * @return
	 */
	public List<PlatformUserBankCard> selectBankCard(@Param("userId")Long userId);
	/**
	 * 根据当前用户ID 获取银行卡信息
	 * @param userId
	 * @return
	 */
	public PlatformUserBankCard selectBankCardId(@Param("id")Long id);
	/**
	 * 修改银行卡信息
	 * @param id
	 * @return
	 */
	public int updateUserBankCard(PlatformUserBankCard bankCard);
	/**
	 * 获取当前用户银行卡详细信息
	 * @param id
	 * @param userId
	 * @return
	 */
	public PlatformUserBankCard selectUserBanCard(@Param("id")Long id,@Param("userId")Long userId);
}