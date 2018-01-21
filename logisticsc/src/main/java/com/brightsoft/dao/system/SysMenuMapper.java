package com.brightsoft.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.SysMenu;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
    List<SysMenu> selectByUserId(Long id);
    
    List<SysMenu> selectMenus(@Param("state")Integer state);
    
    /**
     * 根据roleid获取菜单
     * Author:FENG
     * 2016年7月16日
     * @param roleId
     * @return
     */
    List<SysMenu> getMeaunsByRoleId(String roleId);
    
    List<SysMenu> selectMenusByRole(@Param("id")Long id,@Param("state")Integer state);
}