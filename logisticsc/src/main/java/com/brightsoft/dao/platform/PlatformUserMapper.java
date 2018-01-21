package com.brightsoft.dao.platform;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.PlatformUserSearchParams;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.sysVoUser;
import com.brightsoft.utils.Page;

public interface PlatformUserMapper {
	/**
	 * 用户注册
	 * @param loginName
	 * @param password
	 * @return
	 */
	public int insertUser(PlatformUser user);
	
	/**
	 * 获取 所有用户
	 * Author:FENG
	 * 2016年7月19日
	 * @param pu
	 * @return
	 */
	public List<PlatformUser> getAllUser(PlatformUser pu);
	/**
	 * 用户登录
	 * @param loginName
	 * @param password
	 * @return
	 */
	public PlatformUser selectUser(@Param("loginName")String loginName,@Param("password")String password);
	
	/**
	 * 修改用户密码验证
	 * @param loginName
	 * @param password
	 * @return
	 */
	public int selectUserPassword(@Param("loginName")String loginName,@Param("password")String password);
	/**
	 * 根据ID查询用户信息
	 * @param id
	 * @return
	 */
	public PlatformUser selectUserId(@Param("id")Long id);
	/**
	 * 修改用户基本信息
	 * @param user
	 * @return
	 */
	public int updateUser(PlatformUser user);
	/**
	 * 修改用户基本信息
	 * @param user
	 * @return
	 */
	public int updateByPrimaryKeySelective(PlatformUser user);
	
	public int updateUserPwd(@Param("loginName") String loginName,@Param("password") String password);
	/**
	 * 修改用户密码
	 * @param id
	 * @return
	 */
	public int updatePwd(@Param("password") String password,@Param("newPassword") String newPassword,@Param("id") Long id);
	/**
	 * 绑定邮箱
	 * @param email
	 * @param id
	 * @return
	 */
	public int updateEmail(@Param("email")String email,@Param("loginName")String loginName);
	/**
	 * 绑定手机
	 * @param mpbile
	 * @param id
	 * @return
	 */
	public int updateMobile(@Param("mobile") String mobile,@Param("id") Long id);
	/**
	 * 查询用户是否存在
	 * @param id
	 * @return
	 */
	public int selectLoginName(@Param("loginName") String loginName);
	/**
	 * 修改公司ID
	 * @param id
	 * @return
	 */
	public int updatecompanyId(@Param("companyId") Long companyId,@Param("id") Long id);
	
	/**
	 * 修改临时公司ID
	 * @param id
	 * @return
	 */
	public int updatetemporaryCompanyId(@Param("temporaryCompanyId") Long temporaryCompanyId,@Param("id") Long id);
	
	/**
	 * 新增用户
	 * @param platformUser
	 * @return
	 */
	public int insert(PlatformUser platformUser);
	/**
	 * 禁用账号
	 * @return
	 */
	public int update2Forbid(List<Long> list);
	/**
	 * 获取最大用户ID
	 * @return
	 */
	public Long selectMaxId();
	
	/**
     * 得到待审核用户
     * @param id
     * @return
     */
    public List<Map<String,Object>> getVerifyUserList(@Param("platformUser") PlatformUser platformUser,@Param("page")Page<?> page);
    
    public int countVerifyUserList(@Param("platformUser") PlatformUser platformUser);

	/**
	 * 修改用户取现密码
	 * @param platformUser
	 * @return
	 */
	public int updateCashPassword(PlatformUser platformUser);
	
	/**
	 * 删除用户状态
	 * @param platformUser
	 * @return
	 */
	public int updateUser4Del(String loginName);
	
	public Map<String,Object> doGetVerifyUser(Long userId);
	
	public Map<String,Object> selectUserCompany(Long userId);
	
	public List<Map<String,Object>> getPlatformUserByUserType(@Param("sysVoUser")sysVoUser sysVoUser,@Param("page")Page<?> page);
	
	
	public int getCountPlatformUserByUserType(@Param("sysVoUser")sysVoUser sysVoUser);
	/**
	 * 货运交易系统 用户总数
	 * @param userType
	 * @return
	 */
	public int selectUserType(@Param("userType")Integer userType);
	
	public List<PlatformUser> selectByParams(PlatformUserSearchParams params);
	
	public int selectByParamsCount(PlatformUserSearchParams params);
	/**
	 * 修改当前用余额
	 * @param balance
	 * @return
	 */
	public int updateUserBalance(@Param("balance") double balance,@Param("userId") Long userId);
	/**
	 * 根据用户名称 查询用户对象
	 * @param loginName
	 * @return
	 */
	public PlatformUser selectUserloginName(@Param("loginName")String loginName);
	
	int updateUserByLoginName(PlatformUser platformUser);
	
	/**
	 * 查询 存在子账号的用户信息
	 * @param params
	 * @return
	 */
	public List<PlatformUser> selectBankAccountUser(PlatformUserSearchParams params);
	public int selectBankAccountUserCount(PlatformUserSearchParams params);
}