package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
    /**
     * 通过用户id获取权限信息
     * @param userId
     * @return
     */
    List<Menu> selectByUserId(long userId);
    
    List<Menu> selectByRoleId(long roleId);
    
    List<Menu> selectAllMenu();
 }