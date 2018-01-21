package com.brightsoft.interceptor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.brightsoft.exception.CompanyIsForbidException;
import com.brightsoft.exception.RoleIsDisabledException;
import com.brightsoft.model.Menu;
import com.brightsoft.model.Role;
import com.brightsoft.model.User;
import com.brightsoft.service.system.SysUserService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.TmsRoleService;
import com.brightsoft.service.tms.platform.TmsUserService;

public class TmsShiroRealm extends AuthorizingRealm {
	private static Logger logger = LoggerFactory.getLogger(TmsShiroRealm.class);
	private static final String ALGORITHM = "MD5";

	@Autowired
	private SysUserService userService;
	
	@Autowired
	private TmsUserService tmsUserService;
	
	@Autowired
	private TmsRoleService tmsRoleService;
	
	@Autowired
	private OutletsService outletsService;


	public void setUserService(SysUserService userService) {
		this.userService = userService;
	}

	public TmsShiroRealm() {
		super();
	}

	/**
	 * 认证回调函数, 登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken){
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = tmsUserService.selectByLnameAndPwd(token.getUsername(),String.valueOf(token.getPassword()));
		if(user != null){
			//判断用户是否被禁用
			if(user.getUserStatus() == 0){
				throw new LockedAccountException();
			}else{
				List<Role> roleList = tmsRoleService.selectUserRoleByUserId(user.getId());
				//判断角色是否被禁用
				if(roleList != null && !roleList.isEmpty()){
					int count = 0;
					for (Role role : roleList) {
						count += role.getRoleStatus()==1?1:0;
					}
					if(count == 0){
						throw new RoleIsDisabledException();
					}else{
						//判断物流商/专线是否被禁用
						User masterUser = tmsUserService.selectMasterAccount(user.getCompanyId());
						if(masterUser.getUserStatus() == 0){
							throw new CompanyIsForbidException();
						}
					}
				}
			}
		}else{
			//用户名或密码错误
			throw new UnknownAccountException();
		}
		return new SimpleAuthenticationInfo(user.getLoginName(),String.valueOf(user.getPassword()), getName());
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		/* 这里编写授权代码 */
		String loginName = principals.fromRealm(getName()).iterator().next().toString();
		List<Role> roles = tmsRoleService.selectByLoginName(loginName);
		Set<String> roleNames = new HashSet<String>();
		Set<String> permissions = new HashSet<String>();
		if(roles != null && !roles.isEmpty()){
			for (Role role : roles) {
				//保存可用角色权限
				if(role.getRoleStatus() == 1){
					roleNames.add(role.getRoleName());
					List<Menu> menu = role.getMenus();
					for (Menu mnu : menu) {
						List<Menu> subMenu = mnu.getSubMenus();
						for (Menu subMnu : subMenu) {
							permissions.add(subMnu.getMenuUrl());
						}
					}
				}
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
		info.setStringPermissions(permissions);
		return info;
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	// @PostConstruct
	// public void initCredentialsMatcher() {//MD5加密
	// HashedCredentialsMatcher matcher = new
	// HashedCredentialsMatcher(ALGORITHM);
	// setCredentialsMatcher(matcher);
	// }
}
