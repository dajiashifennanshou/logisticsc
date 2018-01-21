package com.brightsoft.service.tms.platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.dao.platform.PlatformUserMapper;
import com.brightsoft.dao.platform.platformBankPaymentMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.dao.tms.ReceiveMoneyOrderMapper;
import com.brightsoft.dao.tms.RoleMapper;
import com.brightsoft.dao.tms.UserMapper;
import com.brightsoft.dao.tms.UserRoleMapper;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.Role;
import com.brightsoft.model.User;
import com.brightsoft.model.UserRole;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.MD5;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.Tools;


/**
 * 
 * @author dub
 * 1、删除用户：判断用户是否有子账户分账信息，只删除专线营运系统的用户，修改货运交易系统用户角色/类型 为普通用户
 * 2、添加专线营运系统用户，同时向货运交易系统添加普通用户，
 * 如果该用户已经在货运交易系统成为且只能为普通用户，则只向专线营运系统添加该用户信息，密码为货运交易系统该用户的密码，
 * 如果该用户已经在货运交易系统成为非普通用户，则不允许添加。
 * 3、如果专线营运系统添加的用户为网点管理员，货运交易系统的该用户也应该为网点管理员
 * （）
 */

@Service
public class TmsUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PlatformUserCompanyMapper companyMapper;
	
	@Autowired
	private PlatformUserMapper platformUserMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private TmsUserRoleService tmsUserRoleService;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private OutletsInfoMapper outletsInfoMapper;

	@Autowired
	private platformBankPaymentMapper platformBankPaymentMapper;
	
	@Autowired
	private ReceiveMoneyOrderMapper receiveMoneyOrderMapper;
	
	/**
	 * 网点查询用户信息
	 */
	public Result selectByOutletsIdCondition(SearchParams searchParams,Page<?> page,User user) {
		Result result = new Result();
		int results = userMapper.countRowsByOutletsId(searchParams,user);
		List<User> rows = userMapper.selectByOutletsIdCondition(searchParams,page, user);
		for(int i=0;i<rows.size();i++){
			User inner = rows.get(i); 
			if(inner!=null && inner.getLoginName().equals(user.getLoginName())){
				rows.remove(i);
			}
		}
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	
	/**
	 * 专线查询用户信息
	 */
	public Result selectByCompanyIdCondition(SearchParams searchParams,Page<?> page,User user) {
		Result result = new Result();
		int results = userMapper.countRowsByCompanyId(searchParams,user);
		List<User> rows = userMapper.selectByCompanyIdCondition(searchParams,page, user);
		for(int i=0;i<rows.size();i++){
			User inner = rows.get(i); 
			if(inner!=null && inner.getLoginName().equals(user.getLoginName())){
				rows.remove(i);
			}
		}
		result.setResults(results);
		result.setRows(rows);
		return result;
	}

	public User getTmsUser(Integer userType, String phone){
		// 1、判断系统用户类型是否是专线/物流商
		if(userType == Const.PLATFORM_USER_TYPE_PERSONAL_CONSIGNOR || 
				userType == Const.PLATFORM_USER_TYPE_ENTERPRISE_CONSIGNOR){
			// 2、如果不是专线、则使用货运交易系统用户登录的手机号，查询专线营运系统用户 保存到session中
			User user = userMapper.selectByPhone(phone);
			if(user == null){
				return null;
			}
			PlatformUserCompany company = companyMapper.selectByOutletsId(user.getOutletsId());
			user.setPlatformUserCompany(company);
			return user;
		}
		return null;
	}

	/**
	 * 添加用户
	 */
	public int insert(User user,List<Long> roleIdLst,Boolean isAutoRegister){
		int flag = 0;
		user.setStatus(Const.TMS_USER_STATUS_VALID);
		user.setCreateTime(DateTools.getYMDHMS());
		user.setUserStatus(1);
		String userNumber = userMapper.getMaxUserSerialByOutletsId(user.getOutletsId());
		user.setUserNumber(Tools.serialNumberTranslate(userNumber));
		
		//tms用户的类型默认操作员
		int tmsUserType = Const.TMS_USER_TYPE_OPERATOR;
		//默认货运交易系统用户类型为个人货主
		int platUserType = Const.PLATFORM_USER_TYPE_PERSONAL_CONSIGNOR;
		for(int i=0;i<roleIdLst.size();i++){
			Role role = roleMapper.selectByPrimaryKey(roleIdLst.get(i));
			//若当前添加用户为网点管理员，货运交易系统用户类型为网点管理员
			if(role.getOwnerType() == Const.TMS_ROLE_OWNER_SYS_LOGISTICSC_OUTLETS
					||role.getOwnerType() == Const.TMS_ROLE_OWNER_SYS_LINE_OUTLETS){
				tmsUserType = Const.TMS_USER_TYPE_OUTLETS;
				platUserType = Const.PLATFORM_USER_TYPE_DOT;
				break;
			}
				
		}
		user.setUserType(tmsUserType);
		if(userMapper.insert(user)>0){
			List<UserRole> userRoles = new ArrayList<UserRole>();
			for(int i=0;i<roleIdLst.size();i++){
				UserRole userRole = new UserRole();
				userRole.setRoleId(roleIdLst.get(i));
				userRole.setUserId(user.getId());
				userRoles.add(userRole);
			}
			if(tmsUserRoleService.insertBatch(userRoles)>0){
				PlatformUser platformUser = new PlatformUser();
				//是否添加的是货运交易系统用户
				if(!isAutoRegister){
					platformUser.setCompanyId(user.getCompanyId());
					platformUser.setEmail(user.getEmail());
					platformUser.setLoginName(user.getLoginName());
					platformUser.setMobile(user.getPhone());
					platformUser.setTrueName(user.getTrueName());
					platformUser.setPassword(user.getPassword());
					platformUser.setStatus(Const.PLATFORM_USER_STATUS_VALID);
					platformUser.setUserStatus(Const.PLATFORMUSER_USERSTATUS_ENABLE);
					platformUser.setUserType(platUserType);
					platformUser.setCreateTime(DateTools.getYMDHMS());
					flag = platformUserMapper.insert(platformUser);
				}else{
					platformUser.setCompanyId(user.getCompanyId());
					platformUser.setLoginName(user.getLoginName());
					platformUser.setUserType(platUserType);
					flag = platformUserMapper.updateUserByLoginName(platformUser);
				}
				
			}
		}
		return flag;
	}
	public int insertPlatformUser(User user,List<Long> list){
		int flag = 0;
		if(userMapper.insert(user)>0){
			List<UserRole> userRoles = new ArrayList<UserRole>();
			for(int i=0;i<list.size();i++){
				UserRole userRole = new UserRole();
				userRole.setRoleId(list.get(i));
				userRole.setUserId(user.getId());
				userRoles.add(userRole);
			}
			flag =tmsUserRoleService.insertBatch(userRoles);
		}
		return flag;
	}
	
	/**
	 * 登录验证
	 * @param user
	 * @return
	 */
	public User selectByLnameAndPwd(String loginName,String password){
		return userMapper.loginByLoginNameAndPwd(loginName,password);
	}
	
	/**
	 * 获取用户中最大的序列号
	 * @param outletsId
	 * @return
	 */
	public String selectUserSerialByOutletsId(long outletsId){
		return userMapper.getMaxUserSerialByOutletsId(outletsId); 
	}

	/**
	 * 用户删除
	 * @param ids
	 * @return
	 */
	public Map<String, Object> deleteBatch(String[] loginNames){
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < loginNames.length; i++) {
			User user = userMapper.selectByLoginName(loginNames[i]);
			//如果要删除的用户是网点管理员
			if(user != null 
					&& Const.TMS_USER_TYPE_OUTLETS == user.getUserType()){
				//分账是否有未完成的
				int refundCount = platformBankPaymentMapper.getTotalNotSplit(loginNames[i]);
				if(refundCount > 0){
					map.put("success", false);
					map.put("message", "该账号还有未分完账单");
					return map;
				}
				//pos转账是否有未完成的
				int transferCount = receiveMoneyOrderMapper.getTotalNotTransfer(loginNames[i]);
				if(transferCount > 0){
					map.put("success", false);
					map.put("message", "该账号pos机还有账单未转账");
					return map;
				}
				int rst = userMapper.delUserByLoginName(loginNames[i]);
				if(rst > 0){
					PlatformUser platformUser = new PlatformUser();
					platformUser.setUserType(Const.PLATFORM_USER_TYPE_PERSONAL_CONSIGNOR);
					platformUser.setLoginName(loginNames[i]);
					platformUserMapper.updateUserByLoginName(platformUser);
				}else{
					map.put("success", false);
					return map;
				}
			}else{
				int rst = userMapper.delUserByLoginName(loginNames[i]);
				if(rst > 0){
					PlatformUser platformUser = new PlatformUser();
					platformUser.setUserType(Const.PLATFORM_USER_TYPE_PERSONAL_CONSIGNOR);
					platformUser.setLoginName(loginNames[i]);
					platformUserMapper.updateUserByLoginName(platformUser);
				}else{
					map.put("success", false);
					return map;
				}
			}
		}
		map.put("success", true);
		return map;
	}
	
	/**
	 * 货运交易系统
	 * @param searchParams
	 * @param page
	 * @return
	 */
	public Result selectUser(SearchParams searchParams,Page<?> page){
		Result result = new Result();
		int results = userMapper.countUser(searchParams);
		List<User> rows = userMapper.selectUser(searchParams, page);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	
	/**
	 * 更改用户状态
	 * @param loginName
	 * @param userStatus
	 * @return
	 */
	public boolean updateUserStatus(String loginName,Integer userStatus){
		boolean flag = false;
		User  user = new User();
		user.setLoginName(loginName);
		user.setUserStatus(userStatus);
		if(userMapper.updateUserStatus(user)>0){
			flag = true;
		}
		return flag;
	}
	
	public Result updatePwd(String loginName,String newpassword,String repassword,String oldpassword){
		Result result = new Result();
		if(StringUtils.isNotBlank(loginName) 
				&& StringUtils.isNotBlank(oldpassword) 
				&& StringUtils.isNotBlank(repassword) 
				&& StringUtils.isNotBlank(newpassword)){
			if(newpassword.equals(repassword)){
				User user = userMapper.loginByLoginNameAndPwd(loginName, MD5.getMD5Code(oldpassword));
				if(user != null){
					userMapper.updateUserPwd(loginName, MD5.getMD5Code(newpassword));
					PlatformUser platformUser = platformUserMapper.selectUserloginName(loginName);
					if(platformUser != null){
						platformUserMapper.updatePwd(MD5.getMD5Code(oldpassword), MD5.getMD5Code(newpassword), platformUser.getId());
					}
					result.setResult(true);
					///
				}else{
					result.setMsg("密码不正确");
				}
			}else{
				result.setMsg("两次密码不一致");
			}
		}else{
			result.setMsg("必填项不能为空！");
		}
		return result;
	}
	
	public User selectMasterAccount(Long companyId){
		return userMapper.selectMasterAccount(companyId);
	}
	
	public List<User> selectAllUser(SearchParams searchParams, Page<?> page){
		return userMapper.getAllUser(searchParams, page);
	}
	
	public int selectAllResultTotal(SearchParams searchParams){
		return userMapper.getResultTotal(searchParams);
	}
	
	public User selectUserDetail(String loginName){
		User user = userMapper.selectByLoginName(loginName);
		if(user != null){
			List<Role> roleList = roleMapper.selectByLoginName(loginName);
			user.setRoleList(roleList);
			if(user.getOutletsId() != null){
				OutletsInfo outletsInfo = outletsInfoMapper.selectOutletsById(user.getOutletsId());
				user.setOutletsInfo(outletsInfo);
			}
		}
		return user;
	}
	
	public List<User> selectEnterpriseUserList(SearchParams searchParams,Page<?> page){
		List<User> userLst = userMapper.getEnterpriseUserList(searchParams, page);
		return userLst;
	}
	
	public int selectEnterPriseUserTotal(SearchParams searchParams){
		return userMapper.getEnterPriseUserTotal(searchParams);
	}
	
	/**
	 * 
	 * 方法描述：查询用户是否在专线营运系统存在
	 * 			是否在货运交易系统存在
	 * @return
	 * @author dub
	 * @version 2016年6月15日 上午10:54:40
	 */
	public Map<String, Object> userExist(String loginName){
		Map<String, Object> map = new HashMap<String, Object>();
		if(userMapper.selectByLoginName(loginName)==null){
			PlatformUser platformUser = platformUserMapper.selectUserloginName(loginName);
			if(platformUser != null){
				if(platformUser.getUserType() == Const.PLATFORM_USER_TYPE_PERSONAL_CONSIGNOR){
					map.put("exist", false);
				}else{
					map.put("exist", true);
					map.put("message", "该用户已在货运交易系统注册，并且非个人货主");
				}
			}else{
				map.put("exist", false);
			}
		}else{
			map.put("exist", true);
		}
		return map;
	}
	
}
 