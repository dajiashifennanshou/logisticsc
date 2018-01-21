package com.brightsoft.dao.platform;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformUserApply;
/**
 * 用户申请
 * @author ThinkPad
 *
 */
public interface PlatformUserApplyMapper {
    int deleteByPrimaryKey(Long id);
    
    int insert(PlatformUserApply record);

    int insertSelective(PlatformUserApply record);

    PlatformUserApply selectByPrimaryKey(Long id);
    PlatformUserApply selectByUserId(Long userId);

    int updateByPrimaryKeySelective(PlatformUserApply record);

    int updateByPrimaryKey(PlatformUserApply record);
    
    /**
     * 增加用户申请
     * @param apply
     * @return
     */
    public int inserPlatformUserApply(PlatformUserApply apply);
    /**
     * 查询验证当前用户是否申请中
     * @param userId
     * @return
     */
    public int selectMacState(@Param("userId")Long userId);
}