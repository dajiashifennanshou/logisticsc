package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.User;
import com.brightsoft.utils.Page;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    
    /**
     * 通过用户名获取用户信息
     * @param loginName
     * @return
     */
    User selectByLoginName(String loginName);
 
    /**
     * 获取总记录数
     * @param user
     * @param usr
     * @return
     */
	int countRowsByCompanyId(@Param("searchParams")SearchParams searchParams,@Param("user")User user);

	int countRowsByOutletsId(@Param("searchParams")SearchParams searchParams,@Param("user")User user);
	/**
	 *	查询用户信息
	 * @param page
	 * @param user
	 * @param usr
	 * @return
	 */
	List<User> selectByCompanyIdCondition(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page,@Param("user")User user);
	
	List<User> selectByOutletsIdCondition(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page,@Param("user")User user);
	
	User selectByPhone(String phone);
	
	//登录
	User loginByLoginNameAndPwd(@Param("loginName")String loginName,@Param("password")String password);
	
	/**
	 * 通过网点id获取最大用户序列号
	 * @param outletsId
	 * @return
	 */
	String getMaxUserSerialByOutletsId(long outletsId);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int deleteBatch(String[] loginNames);
	
	List<User> selectUser(@Param("searchParams")SearchParams searchParams,@Param("page")Page<?> page);
	
	int countUser(@Param("searchParams")SearchParams searchParams);
	
	/**
	 * 更新物流商，专线 用户状态
	 * @param user
	 * @return
	 */
	int updateUserStatus(User user);
	
	/**
	 * 修改用户密码
	 * @param loginName
	 * @param password
	 * @return
	 */
	int updateUserPwd(@Param("loginName")String loginName,@Param("password")String password);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	int updateUser(User user);
	
	/**
	 * 
	 * 方法描述：根据线路id获取网点子账户
	 * @param lineId
	 * @return
	 * @author dub
	 * @version 2016年5月13日 下午7:49:17
	 */
	User selectOutletsSubAccountByLineId(Long lineId);
	
	/**
	 * 
	 * 方法描述：根据线路id获取物流商子账户
	 * @param lineId
	 * @return
	 * @author dub
	 * @version 2016年5月13日 下午7:49:45
	 */
	User selectLogisticscSubAccountByLineId(Long lineId);
	
	/**
	 * 
	 * 方法描述：获取网点管理员
	 * @param outletsId
	 * @return
	 * @author dub
	 * @version 2016年6月3日 下午2:19:51
	 */
	User selectManagerUserByOutletsId(Long outletsId);
	
	User selectMasterAccount(@Param("companyId")Long companyId);
	
	/**
	 * 查询物流商 管理员用户
	 * @param outletsId
	 * @return
	 */
	User selectLogisManagerUserByOutletsId(Long outletsId);
	
	/**
	 * 方法描述：查询所有tms用户
	 * @return
	 * @author dub
	 * @version 2016年6月14日 上午11:10:35
	 */
	List<User> getAllUser(@Param("searchParams")SearchParams searchParams, @Param("page")Page<?> page);
	
	/**
	 * 方法描述：查询所有tms用户总记录数
	 * @return
	 * @author dub
	 * @version 2016年6月14日 上午11:10:35
	 */
	int getResultTotal(@Param("searchParams")SearchParams searchParams);
	
	List<User> getEnterpriseUserList(@Param("searchParams")SearchParams searchParams, @Param("page")Page<?> page);
	
	int getEnterPriseUserTotal(@Param("searchParams")SearchParams searchParams);
	
	int getResultTotalByOutletsId(Long outletsId);
	
	int delUserByLoginName(String loginName);
}