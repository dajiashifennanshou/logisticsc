package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.Role;
import com.brightsoft.utils.Page;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    //获取总的记录数
	int countRows(@Param("userId")long userId,@Param("condition")String condition);

	//分页查询数据
	List<Role> selectByCondition(@Param("page")Page<?> page,@Param("userId")long userId,@Param("condition")String condition);

	//批量添加
	int insertBatch(List<Role> role);
	
	//批量删除
	int deleteBatch(List<Long> ids);
	
	List<Role> selectByLoginName(String loginName);
	
	//改：通过创建人Id获取角色信息
	List<Role> selectByCreatePersonId(Long createPersonId);
	
	/**
	 * 根据角色类型获取角色
	 * @param ownerType
	 * @return
	 */
	List<Role> selectByOwnerType(@Param("ownerType")Integer ownerType);
	
	/**
	 * 
	 * 方法描述：获取用户角色
	 * @return
	 * @author dub
	 * @version 2016年5月14日 下午3:12:09
	 */
	List<Role> selectByUserId(@Param("userId")Long userId);
	
	/**
	 * 
	 * 方法描述：通过platformType获取角色
	 * @param platformType
	 * @return
	 * @author dub
	 * @version 2016年5月30日 下午2:46:12
	 */
	List<Role> selectByPlatformType(@Param("platformType")Integer platformType);
	
	/**
	 * 
	 * 方法描述：获取企业特定类型角色
	 * @param companyId
	 * @param ownerType
	 * @return
	 * @author dub
	 * @version 2016年6月3日 上午11:03:01
	 */
	List<Role> selectByCmpIdAndOwnerType(@Param("companyId")Long companyId,@Param("ownerType")Integer ownerType);
	
}