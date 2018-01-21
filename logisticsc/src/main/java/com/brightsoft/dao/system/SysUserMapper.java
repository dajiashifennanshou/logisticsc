package com.brightsoft.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.SysUser;
import com.brightsoft.utils.Page;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    List<SysUser> selectAll();

	List<SysUser> selectByPage(Page<SysUser> page);

	SysUser selectByNameAndPwd(@Param("loginname") String loginname,
			@Param("loginpass") String loginpass);
	
	SysUser selectByName(@Param("loginname") String loginname);

	int getSysUsersCount(Page<SysUser> page);
}