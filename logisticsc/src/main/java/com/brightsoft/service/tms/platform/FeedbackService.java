package com.brightsoft.service.tms.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.tms.FeedbackMapper;
import com.brightsoft.model.Feedback;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackMapper feedbackMapper;
	
	/**
	 * 网点查询获取反馈信息
	 * @param page
	 * @param feedback
	 * @param user
	 * @return
	 */
	public Result selectByOutletsIdAndCondition(Page<?> page,SearchParams searchParams,Long outletsId){
		Result result = new Result();
		int results = feedbackMapper.countRowsByOutletsId(searchParams, outletsId);
		List<Feedback> list = feedbackMapper.selectByOutletsIdAndCondition(page, searchParams, outletsId);
		result.setResults(results);
		result.setRows(list);
		return result;
	}
	
	/**
	 * 专线获取反馈信息
	 * @param page
	 * @param searchParams
	 * @param outletsId
	 * @return
	 */
	public Result selectByCompanyIdAndCondition(Page<?> page,SearchParams searchParams,Long companyId){
		Result result = new Result();
		int results = feedbackMapper.countRowsByCompanyId(searchParams, companyId);
		List<Feedback> list = feedbackMapper.selectByCompanyIdAndCondition(page, searchParams, companyId);
		result.setResults(results);
		result.setRows(list);
		return result;
	}
	
	/**
	 * 货运交易系统：
	 * 获取反馈信息
	 * @return
	 */
	public Result selectByCondition(Page<?> page,SearchParams searchParams){
		Result result = new Result();
		int results = feedbackMapper.countRows(searchParams);
		List<Feedback> list = feedbackMapper.selectByCondition(page, searchParams);
		result.setResults(results);
		result.setRows(list);
		return result;
	}
	
	/**
	 * 添加反馈信息
	 * @return
	 */
	public int insert(Feedback feedback){
		return feedbackMapper.insert(feedback);
	}
	
	/**
	 * 运营：回复
	 * @param feedback
	 * @return
	 */
	public boolean update2Reply(Feedback feedback){
		boolean flag = false;
		if(feedbackMapper.updateByPrimaryKeySelective(feedback)>0){
			flag = true;
		}
		return flag;
	}
}
