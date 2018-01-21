package com.brightsoft.service.tms.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformOrderEvaluationReplyMapper;
import com.brightsoft.model.PlatformOrderEvaluation;
import com.brightsoft.model.PlatformOrderEvaluationReply;
import com.brightsoft.model.User;
import com.brightsoft.service.platform.PlatformOrderEvaluationReplyServiceImpl;
import com.brightsoft.service.platform.PlatformOrderEvaluationServiceImpl;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class OrderEvaluationService {

	@Autowired
	private PlatformOrderEvaluationServiceImpl platformOrderEvaluationServiceImpl;
	
	@Autowired
	private PlatformOrderEvaluationReplyServiceImpl platformOrderEvaluationReplyServiceImpl;
	
	/**
	 * 查询订单评价信息
	 * @param page
	 * @param platformOrderEvaluation
	 * @param user
	 * @return
	 */
	public Result selectByCondition(Page<?> page,PlatformOrderEvaluation platformOrderEvaluation,User user){
		return platformOrderEvaluationServiceImpl.selectBySelectedCondition(user, platformOrderEvaluation, page);
	}
	
	/**
	 * 评价回复
	 * @return
	 */
	public int update2reply(PlatformOrderEvaluation platformOrderEvaluation,
			PlatformOrderEvaluationReply platformOrderEvaluationReply){
		int flag = 0;
		if(platformOrderEvaluationServiceImpl.update(platformOrderEvaluation)>0){
			flag = platformOrderEvaluationReplyServiceImpl.insert(platformOrderEvaluationReply);
		}
		return flag;
	}
}
