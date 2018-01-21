package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformOrderEvaluation;
import com.brightsoft.model.PlatformOrderMineEvaluation;
import com.brightsoft.model.PlatformUserEvaluation;
import com.brightsoft.model.User;
import com.brightsoft.utils.Page;

public interface PlatformOrderEvaluationMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(PlatformOrderEvaluation record);
//
//    int insertSelective(PlatformOrderEvaluation record);
//
    PlatformOrderEvaluation selectByPrimaryKey(Long id);
//
	/**
	 * 更新评论状态
	 * @param record
	 * @return
	 */
    int updateByPrimaryKeySelective(PlatformOrderEvaluation record);
//
//    int updateByPrimaryKey(PlatformOrderEvaluation record);
	
	/**
	 * 查询评论信息
	 * @param page
	 * @param platformOrderEvaluation
	 * @param user
	 * @return
	 */
	List<PlatformOrderEvaluation> selectByCondition(@Param("page")Page<?> page,@Param("platformOrderEvaluation")PlatformOrderEvaluation platformOrderEvaluation,@Param("user")User user);
	
	/**
	 * 获取记录总数
	 * @param platformOrderEvaluation
	 * @param user
	 * @return
	 */
	int countRows(@Param("platformOrderEvaluation")PlatformOrderEvaluation platformOrderEvaluation,@Param("user")User user);
	/**
	 * 店铺获取评价信息
	 * @param page
	 * @param evaluation
	 * @return
	 */
	List<PlatformUserEvaluation> selectUserEvaluation(@Param("page")Page<?> page,@Param("evaluation") PlatformUserEvaluation evaluation);
	/**
	 * 店铺评价 总数
	 * @param evaluation
	 * @return
	 */
	List<PlatformUserEvaluation> selectUserEvaluationCount(@Param("evaluation") PlatformUserEvaluation evaluation);
	/**
	 * 获取店铺评价总数
	 * @param page
	 * @param evaluation
	 * @return
	 */
	int userEvaluation(@Param("evaluation") PlatformUserEvaluation evaluation);
	/**
	 * 货运交易系统查询评论信息
	 * @param page
	 * @param PlatformOrderMineEvaluation
	 * @param user
	 * @return
	 */
	List<PlatformOrderMineEvaluation> selectByConditionPlatfrom(@Param("evaluation")PlatformOrderMineEvaluation evaluation,@Param("page")Page<?> page);
	
	/**
	 * 货运交易系统获取记录总数
	 * @param PlatformOrderMineEvaluation
	 * @param user
	 * @return
	 */
	int countRowsPlatfrom(@Param("evaluation")PlatformOrderMineEvaluation evaluation);
	/**
	 * 线路评价总数
	 * @param id
	 * @return
	 */
	public int countLineEvaluation(@Param("id")Long id);
	/**
	 * 增加评价
	 * @param evaluation
	 * @return
	 */
	public int insertEvaluation(PlatformOrderEvaluation evaluation);
	/**
	 * 获取当前评价和相关信息
	 * @param evaId
	 * @return
	 */
	public PlatformOrderMineEvaluation selectMineEvaluation(@Param("evaId") Long evaId);
	/**
	 * 验证是否已存在评价
	 * @param orderNumber
	 * @return
	 */
	public int selectEvaluation(@Param("orderNumber")String orderNumber);
	/**
	 * 查询线路总评价和评价数量
	 * @param orderNumber
	 * @return
	 */
	public int selectTotalAndAvgByLineId(Long lineId);
	
	
	
	/**
	 * 货运交易系统评价管理
	 * @param evaluation
	 * @param page
	 * @return
	 */
	List<PlatformOrderMineEvaluation> selectByConditionPlatfromList(@Param("evaluation")PlatformOrderMineEvaluation evaluation,@Param("page")Page<?> page);
	int countRowsPlatfromList(@Param("evaluation")PlatformOrderMineEvaluation evaluation);
}