package com.yc.Dao;

import java.math.BigInteger;
import java.util.List;

import com.yc.Entity.PlatformOrderEvaluation;
import com.yc.Tool.ISqlDao;
/**
 * 评价
 * @Author:luojing
 * @2016年7月28日 下午5:18:04
 */
public interface PlatformOrderEvaluationDao extends ISqlDao<PlatformOrderEvaluation> {
	
	/**
	 * 根据线路ID查询
	 * @Author:luojing
	 * @2016年7月28日 下午5:24:18
	 */
	public Integer getCount(BigInteger linId);
	
	List<PlatformOrderEvaluation> getEvaluateInfo(BigInteger linId,BigInteger userId);
	
}
