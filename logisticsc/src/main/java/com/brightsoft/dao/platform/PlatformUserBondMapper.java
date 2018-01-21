package com.brightsoft.dao.platform;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformUserBond;

public interface PlatformUserBondMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformUserBond record);

    int insertSelective(PlatformUserBond record);

    PlatformUserBond selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformUserBond record);

    int updateByPrimaryKey(PlatformUserBond record);
    /**
     * 查看用户是否有交纳保证金记录
     * @param userId
     * @return
     */
    public int selectUserBond(@Param("userId") Long userId);
    /**
     * 增加保证金记录
     * @param bond
     * @return
     */
    public int inserUserBond(PlatformUserBond bond);
}