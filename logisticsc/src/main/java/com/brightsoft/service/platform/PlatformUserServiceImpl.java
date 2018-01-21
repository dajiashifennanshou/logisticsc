package com.brightsoft.service.platform;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.Random;
import java.util.ResourceBundle;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.ApplyStateEnum;
import com.brightsoft.common.enums.ApplyTypeEnum;
import com.brightsoft.controller.vo.PlatformUserSearchParams;
import com.brightsoft.dao.platform.PlatformUserApplyMapper;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.dao.platform.PlatformUserMapper;
import com.brightsoft.dao.platform.PlatformUserTemporaryCompanyMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.dao.tms.RoleMapper;
import com.brightsoft.dao.tms.UserMapper;
import com.brightsoft.dao.tms.UserRoleMapper;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserApply;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.PlatformUserMailbox;
import com.brightsoft.model.PlatformUserTemporaryCompany;
import com.brightsoft.model.Role;
import com.brightsoft.model.User;
import com.brightsoft.model.UserRole;
import com.brightsoft.model.sysVoUser;
import com.brightsoft.service.tms.platform.TmsRoleService;
import com.brightsoft.service.tms.platform.TmsUserRoleService;
import com.brightsoft.service.tms.platform.TmsUserService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.MD5;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.SendMsg;
import com.brightsoft.utils.UploadFileUtil;
import com.brightsoft.utils.email.MailSender;
import com.brightsoft.utils.email.MailSenderInfo;
@Service("platformUserService")
public class PlatformUserServiceImpl implements PlatformUserService{
	
	@Autowired
	public PlatformUserMapper platformUserMapper;
	
	@Autowired
	private UserMapper UserMapper;
	
	@Autowired
	public PlatformUserCompanyMapper companyMapper;
	
	@Autowired
	public PlatformUserTemporaryCompanyMapper UserTemporaryCompanyMapper;
	
	@Autowired
	public PlatformUserApplyMapper applyMapper;
	
	@Autowired
	public TmsRoleService roleService;
	
	@Autowired
	public TmsUserService tmsUserService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	public TmsUserRoleService tmsUserRoleService;
	
	@Autowired 
	private TmsRoleService tmsRoleService;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	//邮箱发送访问配置路径
	ResourceBundle bundle = PropertyResourceBundle.getBundle("emailConfig");

	/**
	 * 用户注册
	 */
	public PlatformUser register(PlatformUser user) {
		user.setMobile(user.getLoginName());
		user.setUserType(Const.PLATFORM_USER_TYPE_PERSONAL_CONSIGNOR);
		user.setStatus(Const.PLATFORMUSER_STATUS_1);
		user.setPassword(MD5.getMD5Code(user.getPassword()));
		user.setCreateTime(new Date());
		if(platformUserMapper.insertUser(user) > 0){
			return platformUserMapper.selectUser(user.getLoginName(), user.getPassword());
		}
		return null;
	}
	/**
	 * 用户登录
	 */
	public PlatformUser loginUser(String loginName, String password) {
		password = MD5.getMD5Code(password);
		return platformUserMapper.selectUser(loginName, password);
	}
	/**
	 * 修改用户密码
	 */
	public PlatformUser updatePwd(String password,String newPassword,Long id) {
		PlatformUser platformUser = null;
		password = MD5.getMD5Code(password);
		newPassword= MD5.getMD5Code(newPassword);
		platformUser = platformUserMapper.selectUserId(id);
		if(platformUser.getUserType()==Const.PLATFORM_USER_TYPE_PERSONAL_CONSIGNOR){
			if(platformUserMapper.updatePwd(password,newPassword,id) > 0){
				return platformUser;
			}
		}else if(platformUser.getUserType()==Const.PLATFORM_USER_TYPE_ENTERPRISE_CONSIGNOR){
			if(platformUserMapper.updatePwd(password,newPassword,id) > 0){
				return platformUser;
			}
		}else{
			if(platformUserMapper.updatePwd(password,newPassword,id) > 0){
				if(userMapper.updateUserPwd(platformUser.getLoginName(), newPassword)>0){
					return platformUser;			
				}
			}
		}
		return platformUser;
	}
	/**
	 * 绑定邮箱
	 */
	public boolean updateEmail(String email, String loginName) {
		if(platformUserMapper.updateEmail(email, loginName) > 0){
			return true;
		}
		return false;
	}
	/**
	 * 绑定手机号
	 */
	public boolean updateMobile(String mobile, Long id) {
		if(platformUserMapper.updateMobile(mobile, id) > 0){
			return true;
		}
		return false;
	}
	/**
	 * 获取用户信息
	 */
	public PlatformUser selectUser(Long id) {
		return platformUserMapper.selectUserId(id);
	}
	/**
	 * 修改用户资料
	 */
	public PlatformUser updateUser(PlatformUser user) {
		PlatformUser platformUser = new PlatformUser();
		if(platformUserMapper.updateUser(user) > 0){
			platformUser = platformUserMapper.selectUserId(user.getId());
			User tuser = new User();
			tuser.setLoginName(platformUser.getLoginName());
			tuser.setAddress(platformUser.getAddress());
			tuser.setTrueName(platformUser.getTrueName());
			userMapper.updateUser(tuser);
		}
		return platformUser;
	}
	/**
	 * 查询用户是否存在
	 */
	public boolean selectLoginName(String loginName) {
		if(platformUserMapper.selectLoginName(loginName) >0){
			return true;
		}
		return false;
	}
	/**
	 * 生成并发送验证码，发送成功则缓存 
	 */
	public PlatformUserMailbox sendResetPwdVerCode(String loginName,String email) {
		// 生成6为验证码
		String verCode = Integer.toString(new Random().nextInt(899999) + 100000);
		PlatformUserMailbox  resetPwdUser = new PlatformUserMailbox();
		// 判断邮箱发送验证码是否成功
		MailSender mailSender = new MailSender();
		MailSenderInfo msinfo = new MailSenderInfo();
		//发送验证码
		msinfo = sendPwdVerCodeEmail(email, verCode);
		if (mailSender.sendTextMail(msinfo)) {
			//缓存验证码
			resetPwdUser.setLoginName(loginName);
			resetPwdUser.setEmail(email);
			resetPwdUser.setEamilCode(verCode);
			resetPwdUser.setEamilCodeTime(DateUtils.addSeconds(new Date(),Integer.parseInt(bundle.getString("validationCodeTimeOut"))));
			return resetPwdUser;
		} 
		return resetPwdUser;
	}
	
	/**
	 * 发送邮箱验证码
	 */
	public MailSenderInfo sendPwdVerCodeEmail(String email, String verCode) {
		MailSenderInfo msinfo = new MailSenderInfo();
		msinfo.setMailServerHost(bundle.getString("smtp"));
		msinfo.setMailServerPort(bundle.getString("prot"));
		msinfo.setValidate(true);
		msinfo.setUserName(bundle.getString("userName"));
		msinfo.setPassword(bundle.getString("password"));
		msinfo.setFromAddress(bundle.getString("fromAddress"));
		msinfo.setToAddress(email);
		msinfo.setSubject("货运交易系统");
		msinfo.setContent("验证码为" + verCode);
		
		return msinfo;
	}
	/**
	 * 绑定邮箱
	 */
	public PlatformUser bindingEmail(String verCode,PlatformUserMailbox mailbox,Long id) {
		//验证码 时间是否过期
		Date eamilCodeTime = mailbox.getEamilCodeTime();
		Date nowTime = new Date();
		long timeDiff = (nowTime.getTime() - eamilCodeTime.getTime()) / 1000;
		if (0 < timeDiff) {
			return null;
		}else if(verCode.equals(mailbox.getEamilCode())){
			if(updateEmail(mailbox.getEmail(),mailbox.getLoginName())){
				return platformUserMapper.selectUserId(id);
			}
		}else{
			return null;
		}
		return null;
	}
	
	/**
	 * 新增用户
	 */
	public int insert(PlatformUser platformUser) {
		return platformUserMapper.insert(platformUser);
	}
	/**
	 * 禁用账号
	 */
	public int update2Forbid(List<Long> list) {
		return platformUserMapper.update2Forbid(list);
	}
	/**
	 * 专线注册
	 */
	public boolean insertDedicatedLineRegister(PlatformUser user,
			PlatformUserCompany company) {
		user.setMobile(user.getLoginName());
		user.setUserType(Const.PLATFORM_USER_TYPE_LINE_COMPANY);
		user.setStatus(Const.PLATFORMUSER_STATUS_1);
		user.setPassword(MD5.getMD5Code(user.getPassword()));
		if(platformUserMapper.insertUser(user) > 0){
			Random random = new Random();
			int code = random.nextInt(89999);
			String codeStr=String.valueOf(code+10000);
			company.setCompanyCode(codeStr);
			if(companyMapper.insertCompanyInfo(company)>0){
				Long userId = platformUserMapper.selectMaxId();
				Long companyId = companyMapper.selectCompanyMax();
				if(platformUserMapper.updatecompanyId(companyId,userId) >0){
					List<Role> roleList = roleService.selectByCreatePersonId(Const.PLATFORM_USER_ROLE_LINE,null);
					List<Long> list = new ArrayList<Long>();
					if(roleList != null&&!roleList.isEmpty())
					list.add(roleList.get(0).getId());
					User userTms = new User();
					userTms.setLoginName(user.getLoginName());
					userTms.setPassword(user.getPassword());
					userTms.setCompanyId(companyId);
					userTms.setEmail(user.getEmail());
					userTms.setPhone(user.getMobile());
					userTms.setUserStatus(Const.TMSUSER_USERSTATUS_ENABLE);
					userTms.setUserType(Const.TMS_USER_TYPE_ENTERPRISE);
					userTms.setStatus(Const.TMS_USER_STATUS_VALID);
					if(tmsUserService.insertPlatformUser(userTms, list)>0){
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * 企业货主注册
	 */
	public boolean insertEnterprise(PlatformUser user,
			PlatformUserTemporaryCompany company) {
		user.setMobile(user.getLoginName());
		user.setUserType(Const.PLATFORM_USER_TYPE_ENTERPRISE_CONSIGNOR);
		user.setStatus(Const.PLATFORMUSER_STATUS_0);
		user.setPassword(MD5.getMD5Code(user.getPassword()));
		if(platformUserMapper.insertUser(user) > 0){
			Random random = new Random();
			int code = random.nextInt(89999);
			String codeStr=String.valueOf(code+10000);
			company.setCompanyCode(codeStr);
			if(UserTemporaryCompanyMapper.insertPlatformUserTemporaryCompany(company)>0){
				Long userId = platformUserMapper.selectMaxId();
				Long temporaryCompanyId = UserTemporaryCompanyMapper.selectCompanyMax();
				if(platformUserMapper.updatetemporaryCompanyId(temporaryCompanyId,userId) >0){
					PlatformUserApply apply = new PlatformUserApply();
					apply.setApplyName(ApplyTypeEnum.ENTERPRISEOWNER.getName());
					apply.setApplyType(ApplyTypeEnum.ENTERPRISEOWNER.getValue());
					apply.setApplyState(ApplyStateEnum.APPLYING.getValue());
					apply.setUserId(userId);
					if(applyMapper.inserPlatformUserApply(apply)>0){
						Long id = apply.getId();
						apply.setVersion(id);
						applyMapper.updateByPrimaryKeySelective(apply);
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * 图片路径
	 * @param dirName
	 * @param file
	 * @return
	 */
	public String uploadFile(String dirName, File file){
		Map<String, String> map = UploadFileUtil.uploadImage(dirName, file);
		String originalUrl = null;
		if (!map.isEmpty()) {
			//获取图片路径
			originalUrl = map.get(UploadFileUtil.ORIGINALURL);
		}
		return originalUrl;
	}

	
	public Result getVerifyUserList(PlatformUser platformUser,Page<?> page){
		Result result = new Result();
		List<Map<String,Object>> maps = platformUserMapper.getVerifyUserList(platformUser, page);
		int results = platformUserMapper.countVerifyUserList(platformUser);
		result.setResults(results);
		result.setRows(maps);
		return result;
	}
	/**
	 * 申请专线
	 * @param userId
	 * @param company
	 * @return
	 */
	public boolean applydedicatedLine(Long userId,PlatformUserTemporaryCompany company){
		if(null == company.getId() || company.getId() <= 0){
			Random random = new Random();
			int code = random.nextInt(89999);
			String codeStr=String.valueOf(code+10000);
			company.setCompanyCode(codeStr);
		}
		PlatformUserCompany userCompany = companyMapper.selectCompanyInfo(company.getId());
		if(company.getLogo() == null){
			company.setLogo(userCompany.getLogo());
		}
		if(company.getBusinessLicense() == null){
			company.setBusinessLicense(userCompany.getBusinessLicense());
		}
		if(company.getCompanyPhoto() == null){
			company.setCompanyPhoto(userCompany.getCompanyPhoto());
		}
		if(company.getLegalPhoto() == null){
			company.setLegalPhoto(userCompany.getLegalPhoto());
		}
		if(company.getCardPhoto() == null ){
			company.setCardPhoto(userCompany.getCardPhoto());
		}
		if(UserTemporaryCompanyMapper.insertPlatformUserTemporaryCompany(company)>0){
			Long temporaryCompanyId = UserTemporaryCompanyMapper.selectCompanyMax();
			if(platformUserMapper.updatetemporaryCompanyId(temporaryCompanyId,userId) >0){
				PlatformUserApply apply = new PlatformUserApply();
				apply.setApplyName(ApplyTypeEnum.SALESMAN.getName());
				apply.setApplyType(ApplyTypeEnum.SALESMAN.getValue());
				apply.setApplyState(ApplyStateEnum.APPLYING.getValue());
				apply.setUserId(userId);
				apply.setTime(new Date());
				if(applyMapper.inserPlatformUserApply(apply)>0){
					Long id = apply.getId();
					apply.setVersion(id);
					applyMapper.updateByPrimaryKeySelective(apply);
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 申请企业货主
	 */
	public boolean applyEnterprise(Long userId, PlatformUserTemporaryCompany company) {
		if(null == company.getId() || company.getId() <= 0){
			Random random = new Random();
			int code = random.nextInt(89999);
			String codeStr=String.valueOf(code+10000);
			company.setCompanyCode(codeStr);
		}
		PlatformUserCompany userCompany = companyMapper.selectCompanyInfo(company.getId());
		if(company.getLogo() == null){
			company.setLogo(userCompany.getLogo());
		}
		if(company.getBusinessLicense() == null){
			company.setBusinessLicense(userCompany.getBusinessLicense());
		}
		if(company.getCompanyPhoto() == null){
			company.setCompanyPhoto(userCompany.getCompanyPhoto());
		}
		if(company.getLegalPhoto() == null){
			company.setLegalPhoto(userCompany.getLegalPhoto());
		}
		if(company.getCardPhoto() == null ){
			company.setCardPhoto(userCompany.getCardPhoto());
		}
		if(UserTemporaryCompanyMapper.insertPlatformUserTemporaryCompany(company)>0){
			Long temporaryCompanyId = UserTemporaryCompanyMapper.selectCompanyMax();
			if(platformUserMapper.updatetemporaryCompanyId(temporaryCompanyId,userId) >0){
				PlatformUserApply apply = new PlatformUserApply();
				apply.setApplyName(ApplyTypeEnum.ENTERPRISEOWNER.getName());
				apply.setApplyType(ApplyTypeEnum.ENTERPRISEOWNER.getValue());
				apply.setApplyState(ApplyStateEnum.APPLYING.getValue());
				apply.setUserId(userId);
				apply.setTime(new Date());
				if(applyMapper.inserPlatformUserApply(apply)>0){
					Long id = apply.getId();
					apply.setVersion(id);
					applyMapper.updateByPrimaryKeySelective(apply);
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 申请物流商
	 */
	public boolean applyProvider(Long userId,
			PlatformUserTemporaryCompany company) {
		if(null == company.getId() || company.getId() <= 0){
			Random random = new Random();
			int code = random.nextInt(89999);
			String codeStr=String.valueOf(code+10000);
			company.setCompanyCode(codeStr);
		}
		PlatformUserCompany userCompany = companyMapper.selectCompanyInfo(company.getId());
		if(company.getLogo() == null){
			company.setLogo(userCompany.getLogo());
		}
		if(company.getBusinessLicense() == null){
			company.setBusinessLicense(userCompany.getBusinessLicense());
		}
		if(company.getCompanyPhoto() == null){
			company.setCompanyPhoto(userCompany.getCompanyPhoto());
		}
		if(company.getLegalPhoto() == null){
			company.setLegalPhoto(userCompany.getLegalPhoto());
		}
		if(company.getCardPhoto() == null ){
			company.setCardPhoto(userCompany.getCardPhoto());
		}
		if(UserTemporaryCompanyMapper.insertPlatformUserTemporaryCompany(company)>0){
			Long temporaryCompanyId = UserTemporaryCompanyMapper.selectCompanyMax();
			if(platformUserMapper.updatetemporaryCompanyId(temporaryCompanyId,userId) >0){
				PlatformUserApply apply = new PlatformUserApply();
				apply.setApplyName(ApplyTypeEnum.LOGISTICSPROVIDERS.getName());
				apply.setApplyType(ApplyTypeEnum.LOGISTICSPROVIDERS.getValue());
				apply.setApplyState(ApplyStateEnum.APPLYING.getValue());
				apply.setUserId(userId);
				apply.setTime(new Date());
				if(applyMapper.inserPlatformUserApply(apply)>0){
					Long id = apply.getId();
					apply.setVersion(id);
					applyMapper.updateByPrimaryKeySelective(apply);
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 生成手机验证码 并发送短信
	 */
	public String verificationMessage(String loginName) {
		Random random = new Random();
		int code = random.nextInt(899999);
		String codeStr=String.valueOf(code+100000);
		SendMsg.sendVerification(loginName,codeStr,Const.PLATFORM_SENDTYPE_0);
		return codeStr;
	}
	/**
	 * 初始密码
	 * @param loginName
	 * @return
	 */
	public boolean verificationMobile(String loginName,String password){
		password = MD5.getMD5Code(password);
		PlatformUser platformUser = platformUserMapper.selectUserloginName(loginName);
		if(platformUser.getUserType() == Const.PLATFORM_USER_TYPE_PERSONAL_CONSIGNOR){
			if(platformUserMapper.updateUserPwd(loginName,password) > 0){
				return true;
			}
		}else if(platformUser.getUserType() == Const.PLATFORM_USER_TYPE_ENTERPRISE_CONSIGNOR){
			if(platformUserMapper.updateUserPwd(loginName,password) > 0){
				return true;
			}
		}else{
			if(platformUserMapper.updateUserPwd(loginName,password) > 0){
				if(UserMapper.updateUserPwd(loginName,password) > 0){
					return true;
				}
			}
		}
		return false;
	}
	public boolean verificationEmail(String loginName,String email){
		// 生成6为验证码
		String verCode = Integer.toString(new Random().nextInt(899999) + 100000);
		// 判断邮箱发送验证码是否成功
		MailSender mailSender = new MailSender();
		MailSenderInfo msinfo = new MailSenderInfo();
		//发送验证码
		msinfo = sendEmail(email, verCode);
		String password = MD5.getMD5Code(verCode);
		if(platformUserMapper.updateUserPwd(password,loginName) > 0){
			if (mailSender.sendTextMail(msinfo)) {
				return true;
			}
		}
		return false;	
	}
	/**
	 * 发送邮箱验初始密码
	 */
	public MailSenderInfo sendEmail(String email, String verCode) {
		MailSenderInfo msinfo = new MailSenderInfo();
		msinfo.setMailServerHost(bundle.getString("smtp"));
		msinfo.setMailServerPort(bundle.getString("prot"));
		msinfo.setValidate(true);
		msinfo.setUserName(bundle.getString("userName"));
		msinfo.setPassword(bundle.getString("password"));
		msinfo.setFromAddress(bundle.getString("fromAddress"));
		msinfo.setToAddress(email);
		msinfo.setSubject("中工储运");
		msinfo.setContent("初始密码为" + verCode+"如有疑问，请致电18000510004");
		
		return msinfo;
	}
	public PlatformUserTemporaryCompany selectPlatformUserTemporaryCompany(Long id){
		return UserTemporaryCompanyMapper.selectByPrimaryKey(id);
	}
	
	public Map<String,Object> doGetVerifyUser(Long userId){
		Map<String,Object> map = platformUserMapper.doGetVerifyUser(userId);
		return map;
	}
	//查看公司信息
	public Map<String, Object> UserCompanyEnterprise(Long userId) {
		Map<String,Object> map = platformUserMapper.selectUserCompany(userId);
		return map;
	}
	
	public boolean updateVerifyUser(Long id,PlatformUser platformUser,PlatformUserTemporaryCompany platformUserTemporaryCompany,boolean pass){
		boolean result = false;
		Long companyId;
		try {
			PlatformUser platformUser2 = platformUserMapper.selectUserId(id);
			PlatformUserApply platformUserApply=applyMapper.selectByUserId(platformUser.getId());
			PlatformUserApply platformUserApply2 = new PlatformUserApply();
			BeanUtils.copyProperties(platformUserApply, platformUserApply2);
			if(pass){
				platformUserApply2.setApplyState(100);
				platformUserApply2.setApplyFeedback("审核通过了");
				platformUserApply2.setTime(new Date());
				if(platformUser.getCompanyId() !=null){
					PlatformUserCompany companyInfo = companyMapper.selectCompanyInfo(platformUser.getCompanyId());
					companyId = companyInfo.getId();
					BeanUtils.copyProperties(platformUserTemporaryCompany, companyInfo);
					companyInfo.setId(companyId);
					companyMapper.updateCompanyInfo(companyInfo);
				}else{
					PlatformUserCompany companyInfo = new PlatformUserCompany();
					BeanUtils.copyProperties(platformUserTemporaryCompany, companyInfo);
					//companyInfo.setCompanyAddress(companyAddress);
					companyInfo.setId(null);
					platformUser.setStatus(Const.STATE_YES);
					companyMapper.insertCompanyInfo(companyInfo);
					companyId=companyInfo.getId();
					platformUser.setCompanyId(companyId);
					platformUser.setCompanyName(companyInfo.getCompanyName());
				}
				if(platformUserApply.getApplyType() == 1 && platformUser2.getUserType() == 1){
					List<Long> list = new ArrayList<Long>();
					Role roleLgs = tmsRoleService.selectSysLogisticsc();
					list.add(roleLgs.getId());
					User userTms = new User();
					userTms.setLoginName(platformUser.getLoginName());
					userTms.setPassword(platformUser.getPassword());
					userTms.setCompanyId(companyId);
					userTms.setUserType(Const.TMS_USER_TYPE_LOGISTICSC);
					userTms.setPhone(platformUser.getMobile());
					userTms.setUserStatus(Const.TMSUSER_USERSTATUS_ENABLE);
					userTms.setUserType(Const.TMS_USER_TYPE_LOGISTICSC);
					userTms.setStatus(Const.TMS_USER_STATUS_VALID);
					userTms.setCreateTime(new Date());
					if(tmsUserService.insertPlatformUser(userTms, list)<=0){
						return false;
					}
				}else if(platformUserApply.getApplyType() == 1){
//					List<Role> roleList = tmsRoleService.selectByCreatePersonId(Const.PLATFORM_USER_ROLE_LOGISTICSC);
					List<Long> list = new ArrayList<Long>();
					Role roleLgs = tmsRoleService.selectSysLogisticsc();
					User user = userMapper.selectByLoginName(platformUser.getLoginName());
					if(user!=null){
						//修改用户类型
						User userst = new User();
						userst.setId(user.getId());
						userst.setUserType(Const.TMS_USER_TYPE_LOGISTICSC);
						userMapper.updateByPrimaryKeySelective(userst);
						//删除用户角色
						userRoleMapper.deleteByUserId(user.getId());
						UserRole userRole = new UserRole();
						userRole.setUserId(user.getId());
						userRole.setRoleId(roleLgs.getId());
						//添加用户角色
						tmsUserRoleService.insert(userRole);
						
						//如果由专线申请为物流商
						//该专线下所有专线网点管理员角色变更为物流商网点管理员
						if(platformUser.getUserType() == Const.PLATFORM_USER_TYPE_LINE_COMPANY){
								List<OutletsInfo> outletsLst = outletsInfoMapper.selectByCompanyId(user.getCompanyId());
								for (OutletsInfo outletsInfo : outletsLst) {
									User tmsUser = userMapper.selectManagerUserByOutletsId(outletsInfo.getId());
									if(tmsUser != null){
										List<Role> roleLst = roleMapper.selectByUserId(tmsUser.getId());
										if(roleLst!=null && roleLst.size()>0){
											for (Role role : roleLst) {
												if(role.getOwnerType() == Const.TMS_ROLE_OWNER_SYS_LINE_OUTLETS){
													userRoleMapper.deleteByRoleIdAndUserId(user.getId(), role.getId());
													UserRole userRole2 = new UserRole();
													userRole2.setUserId(tmsUser.getId());
													List<Role> rllst = roleMapper.selectByOwnerType(Const.TMS_ROLE_OWNER_SYS_LOGISTICSC_OUTLETS);
													userRole2.setRoleId(rllst.get(0).getId());
													userRoleMapper.insert(userRole2);
												}
											}
										}
									}
								}
						}
					}
				}else if(platformUserApply.getApplyType() == 2){
					List<Long> list = new ArrayList<Long>();
					Role roleSL = tmsRoleService.selectSysSpecialLine();
					list.add(roleSL.getId());
					User userTms = new User();
					userTms.setLoginName(platformUser.getLoginName());
					userTms.setPassword(platformUser.getPassword());
					userTms.setCompanyId(companyId);
					userTms.setUserType(Const.TMS_USER_TYPE_ENTERPRISE);
					userTms.setPhone(platformUser.getMobile());
					userTms.setUserStatus(Const.TMSUSER_USERSTATUS_ENABLE);
					userTms.setUserType(Const.TMS_USER_TYPE_ENTERPRISE);
					userTms.setStatus(Const.TMS_USER_STATUS_VALID);
					userTms.setCreateTime(new Date());
					if(tmsUserService.insertPlatformUser(userTms, list)<=0){
						if(userTms!=null){
							//删除用户角色
							userRoleMapper.deleteByUserId(userTms.getId());
							Role roleLgs = tmsRoleService.selectSysSpecialLine();
							UserRole userRole = new UserRole();
							userRole.setUserId(userTms.getId());
							userRole.setRoleId(roleLgs.getId());
							//添加用户角色
							tmsUserRoleService.insert(userRole);
						}
						return false;
					}
				}
				if(platformUserApply.getApplyType() == 1){
					platformUser.setUserType(4);
				}else if(platformUserApply.getApplyType() == 0){
					platformUser.setUserType(2);
				}else if(platformUserApply.getApplyType() == 2){
					platformUser.setUserType(3);
				}
				UserTemporaryCompanyMapper.updateTemporaryCompany(platformUserTemporaryCompany);
				platformUserMapper.updateByPrimaryKeySelective(platformUser);
			}else{
					platformUser.setUserType(1);
					platformUserApply2.setApplyState(50);
					platformUserApply2.setApplyFeedback("审核未通过");
					platformUserApply2.setTime(new Date());
					if(platformUser.getCompanyId() !=null){
						PlatformUserCompany companyInfo = companyMapper.selectCompanyInfo(platformUser.getCompanyId());
						companyId = companyInfo.getId();
						BeanUtils.copyProperties(platformUserTemporaryCompany, companyInfo);
						companyInfo.setId(companyId);
						companyMapper.updateCompanyInfo(companyInfo);
					}else{
						PlatformUserCompany companyInfo = new PlatformUserCompany();
						BeanUtils.copyProperties(platformUserTemporaryCompany, companyInfo);
						//companyInfo.setCompanyAddress(companyAddress);
						companyInfo.setId(null);
						platformUser.setStatus(Const.STATE_YES);
						companyMapper.insertCompanyInfo(companyInfo);
						companyId=companyInfo.getId();
						platformUser.setCompanyId(companyId);
						platformUser.setCompanyName(companyInfo.getCompanyName());
					}
					UserTemporaryCompanyMapper.updateTemporaryCompany(platformUserTemporaryCompany);
					platformUserMapper.updateByPrimaryKeySelective(platformUser);
			}
			platformUserApply2.setId(null);
			applyMapper.insert(platformUserApply2);
			
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/**
	 * 验证用户修改密码
	 */
	public boolean selectUserPassword(String loginName, String password) {
		password = MD5.getMD5Code(password);
		if(platformUserMapper.selectUserPassword(loginName, password) > 0){
			return true;
		}
		return false;
	}
	
/*	public List<Map<String,Object>> getPlatformUserByUserType(int userType,Page<?> page){
		return platformUserMapper.getPlatformUserByUserType(userType,page);
	}
	public int getCountPlatformUserByUserType(int userType,Page<?> page){
		return platformUserMapper.getCountPlatformUserByUserType(userType,page);
	}*/
	
	public Result getPlatformUserByUserType(sysVoUser sysVoUser,Page<?> page){
		Result result = new Result();
		List<Map<String,Object>> list = platformUserMapper.getPlatformUserByUserType(sysVoUser,page);
		int results = platformUserMapper.getCountPlatformUserByUserType(sysVoUser);
		result.setResults(results);
		result.setRows(list);
		return result;
	}
	/**
	 * 物流商，专线会员
	 * 更新用户状态
	 * @param loginName
	 * @param userStatus
	 * @return
	 *//*
	public boolean updateUserStatus(String loginName,Integer userStatus) {
		boolean flag = false;
		PlatformUser platformUser = new PlatformUser();
		platformUser.setLoginName(loginName);
		platformUser.setUserStatus(userStatus);
		if(platformUserMapper.updateUserStatus(platformUser)>0){
			User user = new User();
			user.setLoginName(loginName);
			user.setUserStatus(userStatus);
			if(UserMapper.updateUserStatus(user)>0){
				flag = true;
			}
		}
		return flag;
	}*/

	
	public int updateByPrimaryKeySelective(PlatformUser platformUser){
		return platformUserMapper.updateByPrimaryKeySelective(platformUser);
	}

	/**
	 * 获取专线总数
	 */
	public int selectUserTypeCompany() {
		return platformUserMapper.selectUserType(Const.PLATFORM_USER_TYPE_LINE_COMPANY);
	}
	/**
	 * 获取物流提供商总数
	 */
	public int selectUserTypeProviders() {
		return platformUserMapper.selectUserType(Const.PLATFORM_USER_TYPE_LINE_PROVIDER);
	}
	
	/**
	 * 查询货运交易系统用户信息
	 */
	public Result selectByParams(PlatformUserSearchParams params) {
		List<PlatformUser> users = platformUserMapper.selectByParams(params);
		int count = platformUserMapper.selectByParamsCount(params);
		Result result = new Result();
		result.setResults(count);
		result.setRows(users);
		return result;
	}
	
	/**
	 * 查询 存在子账号的用户信息
	 * @param params
	 * @return
	 */
	public Result selectBankAccountUser(PlatformUserSearchParams params){
		Result result = new Result();
		result.setRows(platformUserMapper.selectBankAccountUser(params));
		result.setResults(platformUserMapper.selectBankAccountUserCount(params));
		return result;
	}
	
	public PlatformUser selectUserloginName(String loginName){
		return platformUserMapper.selectUserloginName(loginName);
	}
	public List<PlatformUser> getAllUser(PlatformUser pu) {
		// TODO Auto-generated method stub
		return platformUserMapper.getAllUser(pu);
	}
}
