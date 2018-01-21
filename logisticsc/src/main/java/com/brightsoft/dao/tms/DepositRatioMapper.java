package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.model.DepositRatio;
import com.brightsoft.model.User;
import com.brightsoft.utils.Page;

public interface DepositRatioMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DepositRatio record);

    int insertSelective(DepositRatio record);

    DepositRatio selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DepositRatio record);

    int updateByPrimaryKey(DepositRatio record);
    
    /**
     * 网点获取活动费率
     * @param depositRatio
     * @param page
     * @param outletsId
     * @return
     */
    List<DepositRatio> selectByOutletsIdAndCondition(@Param("depositRatio")DepositRatio depositRatio,@Param("page")Page<?> page, @Param("outletsId")Long outletsId);
    /**
     * 网点获取活动费率总记录数
     * @param depositRatio
     * @param outletsId
     * @return
     */
    int countRowsByOutletsId(@Param("depositRatio")DepositRatio depositRatio,@Param("outletsId")Long outletsId);
    /**
     * 专线获取活动费率
     * @param depositRatio
     * @param page
     * @param companyId
     * @return
     */
    List<DepositRatio> selectByCompanyIdAndCondition(@Param("depositRatio")DepositRatio depositRatio, @Param("page")Page<?> page,@Param("companyId")Long companyId);
    /**
     * 专线获取活动费率记录数
     * @param depositRatio
     * @param companyId
     * @return
     */
    int countRowsByCompanyId(@Param("depositRatio")DepositRatio depositRatio,@Param("companyId")Long companyId);
    /**
     * 获取结果总数
     * @param depositRatio
     * @param user
     * @return
     */
	int countRows(@Param("depositRatio")DepositRatio depositRatio, @Param("user")User user);

	/**
	 * 查询预存费信息
	 * @param page
	 * @param depositRatio
	 * @param user
	 * @return
	 */
	List<DepositRatio> selectByCondition(@Param("page")Page<?> page, @Param("depositRatio")DepositRatio depositRatio, @Param("user")User user);

	/**
	 * 查询预存费信息
	 * @param page
	 * @param depositRatio
	 * @return
	 */
	List<DepositRatio> selectBySelectedCondition(@Param("page")Page<?> page,@Param("depositRatio")DepositRatio depositRatio);
	/**
	 * 获取预存费的记录数
	 * @param depositRatio
	 * @return
	 */
	int countSelectedRows(@Param("depositRatio")DepositRatio depositRatio);
	/**
	 * 审核
	 * @param list
	 * @return
	 */
	int audit(List<DepositRatio> list);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int deleteBatch(List<Long> ids);

	public List<DepositRatio> selectByParams(BaseSearchParams params);
	
	public int selectByParamsCount(BaseSearchParams params);
	
	/**
	 * 查询当前正在进行的活动
	 * @return
	 */
	public DepositRatio selectCurrentDeposit();
}