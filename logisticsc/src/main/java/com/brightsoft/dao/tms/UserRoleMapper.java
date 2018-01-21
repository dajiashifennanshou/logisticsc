package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    
    int insertBatch(List<UserRole> list);

    int deleteByRoleId(Long roleId);
    
    int deleteByUserId(Long userId);
    
    int deleteByRoleIdAndUserId(@Param("userId")Long userId,@Param("roleId")Long roleId);
    
    int countUserByRoleId(List<Long> roleIdList);
}