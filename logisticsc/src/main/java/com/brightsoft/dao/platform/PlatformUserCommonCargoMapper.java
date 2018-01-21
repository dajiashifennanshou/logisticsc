package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformUserCommonCargo;
import com.brightsoft.utils.Page;

public interface PlatformUserCommonCargoMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(PlatformUserCommonCargo record);
//
//    int insertSelective(PlatformUserCommonCargo record);
//
//    PlatformUserCommonCargo selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(PlatformUserCommonCargo record);
//
//    int updateByPrimaryKey(PlatformUserCommonCargo record);
	
	/**
	 * 查询筛选条件我的常发货物
	 * @param commonCargo
	 * @param page
	 * @return
	 */
	public List<PlatformUserCommonCargo> selectBySelectedCondition(@Param("commonCargo") PlatformUserCommonCargo commonCargo,@Param("page")Page<?> page);
	/**
	 * 获取总数
	 * @param commonCargo
	 * @return
	 */
    public int countRows(@Param("commonCargo") PlatformUserCommonCargo commonCargo);
    /**
     * 增加我的常发货物
     * @param commonCargo
     * @return
     */
    public int insertCommonCargo(PlatformUserCommonCargo commonCargo);
    /**
     * 验证我的常发货物 是否存在用户
     * @param commonCargo
     * @return
     */
    public int selectCommonCargoId(PlatformUserCommonCargo commonCargo);
    /**
     * 逻辑删除我的常发货物
     * @param commonCargoId
     * @return
     */
    public int updateCommonCargo(@Param("commonCargoId") List<Long> commonCargoId,@Param("state") int state);
    
    public PlatformUserCommonCargo selectByName(String name);
    /**
     * 验证当前用户是否已存在 这个货物
     * @param commonCargo
     * @return
     */
    public int selectCommonCargoName(PlatformUserCommonCargo commonCargo);
    
    public PlatformUserCommonCargo selectByUserIdAndName(@Param("userId") Long userId, @Param("name") String name);
}