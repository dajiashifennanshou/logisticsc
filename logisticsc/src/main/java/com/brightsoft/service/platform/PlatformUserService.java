package com.brightsoft.service.platform;
import java.io.File;
import java.util.List;
import java.util.Map;

import com.brightsoft.controller.vo.PlatformUserSearchParams;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.PlatformUserMailbox;
import com.brightsoft.model.PlatformUserTemporaryCompany;
import com.brightsoft.model.sysVoUser;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.email.MailSenderInfo;

public interface PlatformUserService {
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public PlatformUser register(PlatformUser user);
	/**
	 * 用户登录
	 * @param login_name
	 * @param password
	 * @return
	 */
	public PlatformUser loginUser(String loginName,String password);
	/**
	 * 验证用户修改密码验证
	 * @param loginName
	 * @param password
	 * @return
	 */
	public boolean selectUserPassword(String loginName,String password);
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	public PlatformUser selectUser(Long id);
	
	public List<PlatformUser> getAllUser(PlatformUser pu);
	/**
	 * 修改用户资料
	 * @param id
	 * @return
	 */
	public PlatformUser updateUser(PlatformUser platformUser);
	/**
	 * 修改用户密码
	 * @param password
	 * @param id
	 * @return
	 */
	public PlatformUser updatePwd(String password,String newPassword,Long id);
	/**
	 * 绑定邮箱
	 * @param email
	 * @param id
	 * @return
	 */
	public boolean updateEmail(String email,String loginName);
	/**
	 * 绑定手机号
	 * @param mobile
	 * @param id
	 * @return
	 */
	public boolean updateMobile(String mobile,Long id);
	/**
	 * 用户是否存在
	 * @param id
	 * @return
	 */
	public boolean selectLoginName(String loginName);
	/**
	 * 发送验证码成功保存缓存中
	 * @param userName
	 * @param type
	 */
	public PlatformUserMailbox sendResetPwdVerCode(String loginName,String email);
	
	/**
     * 
     * @Title: sendPwdVerCodeEmail   
     * @Description:发送邮箱验证码
     * @param verCode
     * @return MailSenderInfo
     */
    public MailSenderInfo sendPwdVerCodeEmail(String email,String verCode);
    /**
     * 
     * @param verCode
     * @return
     */
    public PlatformUser bindingEmail(String verCode,PlatformUserMailbox userMailbox,Long id);
    
    /**
	 * 用户新增
	 * @param user
	 * @return
	 */
	public int insert(PlatformUser user);
	
	/**
	 * 禁用账号
	 * @return
	 */
	public int update2Forbid(List<Long> list);
	/**
	 * 专线注册
	 * @return
	 */
	public boolean insertDedicatedLineRegister(PlatformUser user,PlatformUserCompany company);
	/**
	 * 企业货主注册
	 * @return
	 */
	public boolean insertEnterprise(PlatformUser user,PlatformUserTemporaryCompany company);
	/**
	 * 图片路径
	 * @param dirName
	 * @param file
	 * @return
	 */
	public String uploadFile(String dirName, File file);

	
	
	public Result getVerifyUserList(PlatformUser platformUser,Page<?> page);

	/**
	 * 申请企业货主
	 * @param userId
	 * @param company
	 * @return
	 */
	public boolean applyEnterprise(Long userId,PlatformUserTemporaryCompany company);
	
	public boolean applydedicatedLine(Long userId,PlatformUserTemporaryCompany company);
	/**
	 * 申请物流提供商
	 * @param userId
	 * @param company
	 * @return
	 */
	public boolean applyProvider(Long userId,PlatformUserTemporaryCompany company);
	/**
	 * 生成手机验证码
	 * @param loginName
	 * @return
	 */
	public String verificationMessage(String loginName);
	
	public boolean verificationMobile(String loginName,String password);
	
	public Map<String,Object> doGetVerifyUser(Long userId);
	
	public Map<String,Object> UserCompanyEnterprise(Long userId);
	
	public boolean updateVerifyUser(Long id,PlatformUser platformUser,PlatformUserTemporaryCompany platformUserCompany,boolean pass);
	
	public PlatformUserTemporaryCompany selectPlatformUserTemporaryCompany(Long id);
	
	public Result getPlatformUserByUserType(sysVoUser sysVoUser,Page<?> page);

	public int updateByPrimaryKeySelective(PlatformUser platformUser);

	
	/*public boolean updateUserStatu(String loginName);*/
	
	public int selectUserTypeCompany();
	public int selectUserTypeProviders();
	
	/**
	 * 货运交易系统用户信息
	 * @param params
	 * @return
	 */
	public Result selectByParams(PlatformUserSearchParams params);
	
	public Result selectBankAccountUser(PlatformUserSearchParams params);
	
	public PlatformUser selectUserloginName(String loginName);
	
	public boolean verificationEmail(String loginName,String email);
	public MailSenderInfo sendEmail(String email, String verCode);

}
