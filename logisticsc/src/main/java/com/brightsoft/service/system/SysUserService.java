package com.brightsoft.service.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.SysUser;
import com.brightsoft.utils.Page;

public interface SysUserService {
	public SysUser selectByPrimaryKey(Long id);

	public int insert(SysUser user);

	public void deleteByPrimaryKey(String id);

	public boolean updateByPrimaryKey(SysUser record);

	public List<SysUser> selectAll();

	public SysUser selectByNameAndPwd(String LoginName, String LoginPass);

	public List<SysUser> selectByPage(Page<SysUser> page);

	public int getSysUsersCount(Page<SysUser> page);
	
	public SysUser selectByName(@Param("loginname") String loginname);
	
	public int insertUserAndRole(SysUser user);
}
