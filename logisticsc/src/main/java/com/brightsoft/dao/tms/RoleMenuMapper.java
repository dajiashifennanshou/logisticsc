package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.RoleMenu;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    RoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);
    
    //批量插入
    int insertBatch(List<RoleMenu> list);
    //批量删除
    int deleteBatchByRoleId(List<Long> roleIds);
    
    int deleteByRoleId(Long roleId);
}