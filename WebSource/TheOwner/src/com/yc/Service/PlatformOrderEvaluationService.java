package com.yc.Service; 
import java.math.BigInteger;
import java.util.List;

import com.yc.Entity.PlatformOrderEvaluation;
import com.yc.Tool.Pager; 
/** 
* LcPlatformOrderEvaluation服务接口层 
* Auther:FENG 
*/ 
public interface PlatformOrderEvaluationService  {  

	/**
	 * 根据用户id和线路id获取评价列表
	 * Author:FENG
	 * 2016年8月4日
	 * @param linId
	 * @param userId
	 * @return
	 */
	List<PlatformOrderEvaluation> getEvaluateInfo(BigInteger linId,BigInteger userId);
	
	/**
	 * 分页查询评价
	 * @Author:luojing
	 * @2016年8月19日 下午3:57:31
	 */
	Pager<PlatformOrderEvaluation> getPageEvaluateInfo(Pager<PlatformOrderEvaluation> pager,BigInteger linId,BigInteger userId);
	
	/**
	 * 添加对应信息
	 * Author:FENG
	 * 2016年5月11日
	 * @param t
	 * @return
	 */
	public Integer addSingleInfo(PlatformOrderEvaluation t);
	public PlatformOrderEvaluation getSingleInfo(PlatformOrderEvaluation t);
	public Integer getSumCount(PlatformOrderEvaluation t);
}
