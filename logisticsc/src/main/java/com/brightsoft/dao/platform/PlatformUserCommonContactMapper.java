package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformUserCommonContact;
import com.brightsoft.utils.Page;

public interface PlatformUserCommonContactMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(PlatformUserCommonContact record);
//
//    int insertSelective(PlatformUserCommonContact record);
//
//    PlatformUserCommonContact selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(PlatformUserCommonContact record);
//
//    int updateByPrimaryKey(PlatformUserCommonContact record);
	
	/**
	 * 根据手机号 查询常用联系人信息
	 * @param phone
	 * @return
	 */
	public PlatformUserCommonContact selectByPhoneAndUserId(@Param("phone") String phone, @Param("userId") Long userId);
	
	/**
	 * 增加常用联系人
	 * @param commonContact
	 * @return
	 */
	public int insertPlatformUserCommonContact(PlatformUserCommonContact commonContact);
	/**
	 * 验证当前用户时候已添加当前用户
	 * @param commonContact
	 * @return
	 */
	public int selectPlatCommonContactId(PlatformUserCommonContact commonContact);
	
	/**
	 * 查询筛选条件我的联系人
	 * @param commonContact
	 * @param page
	 * @return
	 */
	public List<PlatformUserCommonContact> selectBySelectedCondition(@Param("commonContact") PlatformUserCommonContact commonContact,@Param("page")Page<?> page);
	/**
	 * 获取总数
	 * @param commonContact
	 * @return
	 */
    public int countRows(@Param("commonContact") PlatformUserCommonContact commonContact);
    /**
     * 逻辑删除我的常用联系人
     * @param commonConsigneeId
     * @param state
     * @return
     */
    public int updateCommonContact(@Param("commonContact") List<Long> commonContact,@Param("state") int state);
}