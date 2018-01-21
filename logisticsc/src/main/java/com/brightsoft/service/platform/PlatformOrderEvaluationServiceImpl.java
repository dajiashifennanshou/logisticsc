package com.brightsoft.service.platform;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformOrderEvaluationMapper;
import com.brightsoft.dao.platform.PlatformOrderMapper;
import com.brightsoft.dao.tms.LineInfoMapper;
import com.brightsoft.model.LineInfo;
import com.brightsoft.model.PlatformOrderEvaluation;
import com.brightsoft.model.PlatformOrderMineEvaluation;
import com.brightsoft.model.User;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.WebConstant;

@Service
public class PlatformOrderEvaluationServiceImpl {

	@Autowired
	private PlatformOrderEvaluationMapper platformOrderEvaluationMapper;
	
	@Autowired
	private XzqhServiceImpl xzqhServiceImpl;
	
	@Autowired
	private PlatformOrderMapper orderMapper;
	
	@Autowired
	private LineInfoMapper lineInfoMapper;
	
	/**
	 * 查询评论信息
	 * @param user
	 * @param platformOrderEvaluation
	 * @param page
	 * @return
	 */
	public Result selectBySelectedCondition(User user,PlatformOrderEvaluation platformOrderEvaluation, Page<?> page) {
		Result result = new Result();
		int results = platformOrderEvaluationMapper.countRows(platformOrderEvaluation,user);
		List<PlatformOrderEvaluation> list = platformOrderEvaluationMapper.selectByCondition(page,platformOrderEvaluation,user);
		result.setRows(list);
		result.setResults(results);
		return result;
	}
	/**
	 * 货运交易系统管理
	 * @param mineEvaluation
	 * @param page
	 * @return
	 */
	public Result selectByConditionPlatfromList(PlatformOrderMineEvaluation mineEvaluation,Page<?> page){
		Result result = new Result();
		List<PlatformOrderMineEvaluation> list = platformOrderEvaluationMapper.selectByConditionPlatfromList(mineEvaluation, page);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setStartProvince(xzqhServiceImpl.selectValueById(list.get(i).getStartProvince()).getName());
			list.get(i).setStartCity(xzqhServiceImpl.selectValueById(list.get(i).getStartCity()).getName());
			list.get(i).setStartCounty(xzqhServiceImpl.selectValueById(list.get(i).getStartCounty()).getName());
			list.get(i).setEndProvince(xzqhServiceImpl.selectValueById(list.get(i).getEndProvince()).getName());
			list.get(i).setEndCity(xzqhServiceImpl.selectValueById(list.get(i).getEndCity()).getName());
			list.get(i).setEndCounty(xzqhServiceImpl.selectValueById(list.get(i).getEndCounty()).getName());
		}
		int results = platformOrderEvaluationMapper.countRowsPlatfromList(mineEvaluation);
		result.setResults(results);
		result.setRows(list);
		return result;
	}
	/**
	 * 货运交易系统查询评论信息
	 * @param user
	 * @param platformOrderEvaluation
	 * @param page
	 * @return
	 */
	public Page<?> selectByConditionPlatfrom(PlatformOrderMineEvaluation mineEvaluation,Page<?> page) {
		int results = platformOrderEvaluationMapper.countRowsPlatfrom(mineEvaluation);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<PlatformOrderMineEvaluation> list = platformOrderEvaluationMapper.selectByConditionPlatfrom(mineEvaluation, page);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setStartProvince(xzqhServiceImpl.selectValueById(list.get(i).getStartProvince()).getName());
			list.get(i).setStartCity(xzqhServiceImpl.selectValueById(list.get(i).getStartCity()).getName());
			list.get(i).setStartCounty(xzqhServiceImpl.selectValueById(list.get(i).getStartCounty()).getName());
			list.get(i).setEndProvince(xzqhServiceImpl.selectValueById(list.get(i).getEndProvince()).getName());
			list.get(i).setEndCity(xzqhServiceImpl.selectValueById(list.get(i).getEndCity()).getName());
			list.get(i).setEndCounty(xzqhServiceImpl.selectValueById(list.get(i).getEndCounty()).getName());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, list);
		page.setParams(map);
		return page;
	}
	/**
	 * 更新评论状态
	 * @param platformOrderEvaluation
	 * @return
	 */
	public boolean updatesys(Long id){
		PlatformOrderEvaluation platformOrderEvaluation = null;
		platformOrderEvaluation = platformOrderEvaluationMapper.selectByPrimaryKey(id);
		if(null == platformOrderEvaluation){
			return false;
		}else{
			if(platformOrderEvaluation.getStatus()== null || platformOrderEvaluation.getStatus()==Const.STATE_YES){
				platformOrderEvaluation.setStatus(Const.STATE_NO);
			}else if(platformOrderEvaluation.getStatus()==Const.STATE_NO){
				platformOrderEvaluation.setStatus(Const.STATE_YES);
			}
			if(platformOrderEvaluationMapper.updateByPrimaryKeySelective(platformOrderEvaluation) > 0){
				return true;
			}
		}
		
		return false;
	}
	public int update(PlatformOrderEvaluation platformOrderEvaluation){
		return platformOrderEvaluationMapper.updateByPrimaryKeySelective(platformOrderEvaluation);
	}
	/**
	 * 增加评价信息
	 * @return
	 */
	public boolean inserEvaluation(PlatformOrderEvaluation evaluation){
		evaluation.setEvaluateTime(new Date());
		evaluation.setState(Const.PLATFORMUSER_ORDER_EVALUATION_STATE_NOT_REPLIED);
		if(platformOrderEvaluationMapper.insertEvaluation(evaluation)> 0){
			LineInfo lineInfo = new LineInfo();
			lineInfo.setEvaluateLevel(platformOrderEvaluationMapper.selectTotalAndAvgByLineId(orderMapper.selectTmsLineId(evaluation.getOrderNumber())));
			lineInfo.setId(orderMapper.selectTmsLineId(evaluation.getOrderNumber()));
			if(lineInfoMapper.updateByPrimaryKeySelective(lineInfo)>0){
				return true;
			}
		}
		return false;
	}
	
	public PlatformOrderEvaluation selectByPrimaryKey(Long id){
		return platformOrderEvaluationMapper.selectByPrimaryKey(id);
	}
}
