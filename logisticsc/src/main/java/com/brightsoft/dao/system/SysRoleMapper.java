package com.brightsoft.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.SysRole;
import com.brightsoft.model.SysUser;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    List<SysRole> selectSysRoleList(@Param("state")Integer state);
    
    int deleteRoleMenuByRole(@Param("roleId")Long roleId);
    
    void insertRoleMenu(@Param("menus")Long[] menus,@Param("roleId")Long roleId);
    
    int insertUserRole(@Param("userId") Long userId,
			@Param("roleId") Long roleId);
    
    int updateUserRole(@Param("userId") Long userId,
			@Param("roleId") Long roleId);
    
    List<Map<String, Long>> selectRoleIdByUser(@Param("userId") Long userId);
    
    
    
}