package com.brightsoft.interceptor;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
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

import com.brightsoft.model.SysUser;
import com.brightsoft.service.system.SysUserService;



public class ShiroRealm extends AuthorizingRealm {
	private static Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
	private static final String ALGORITHM = "MD5";
	
	@Autowired
	private SysUserService userService;


	public void setUserService(SysUserService userService) {
		this.userService = userService;
	}

	public ShiroRealm() {
		super();
	}

	/**
	 * 认证回调函数, 登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		System.out.println(token.getUsername());
		SysUser user = userService.selectByNameAndPwd(token.getUsername(),
				token.getPassword().toString());
		System.out.println(user);
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getUsername(),
					"", getName());
		} else {
			throw new AuthenticationException();
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		/* 这里编写授权代码 */
		Set<String> roleNames = new HashSet<String>();
		Set<String> permissions = new HashSet<String>();
		roleNames.add("admin");
		permissions.add("index.shtml");
		permissions.add("userlist.shtml");
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
