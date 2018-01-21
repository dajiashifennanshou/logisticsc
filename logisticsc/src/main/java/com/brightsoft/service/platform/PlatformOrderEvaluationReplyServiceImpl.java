package com.brightsoft.service.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformOrderEvaluationMapper;
import com.brightsoft.dao.platform.PlatformOrderEvaluationReplyMapper;
import com.brightsoft.model.PlatformOrderEvaluationInfo;
import com.brightsoft.model.PlatformOrderEvaluationReply;
import com.brightsoft.model.PlatformOrderMineEvaluation;

@Service
public class PlatformOrderEvaluationReplyServiceImpl {

	@Autowired
	private PlatformOrderEvaluationReplyMapper platformOrderEvaluationReplyMapper;
	
	@Autowired
	private PlatformOrderEvaluationMapper platformOrderEvaluationMapper;
	
	/**
	 * 添加回复
	 * @param platformOrderEvaluationReply
	 * @return
	 */
	public int insert(PlatformOrderEvaluationReply platformOrderEvaluationReply){
		return platformOrderEvaluationReplyMapper.insert(platformOrderEvaluationReply);
	}
	/**
	 * 查看评价回复和相关评价信息
	 * @param id
	 * @return
	 */
	public PlatformOrderEvaluationInfo selectEvaluationReply(Long id){
		PlatformOrderEvaluationInfo evaluationInfo =new PlatformOrderEvaluationInfo();
		PlatformOrderEvaluationReply evaluationReply = platformOrderEvaluationReplyMapper.selectEvaluationReply(id);
		PlatformOrderMineEvaluation mineEvaluation = platformOrderEvaluationMapper.selectMineEvaluation(id);
		if(null != evaluationReply){
			evaluationInfo.setEvaluation(mineEvaluation);
			evaluationInfo.setEvaluationReply(evaluationReply);
		}else{
			evaluationInfo = null;
		}
		return evaluationInfo;
	}
	
	public PlatformOrderEvaluationInfo selectEvaluationReplySys(Long id){
		PlatformOrderEvaluationInfo evaluationInfo =new PlatformOrderEvaluationInfo();
		PlatformOrderEvaluationReply evaluationReply = platformOrderEvaluationReplyMapper.selectEvaluationReply(id);
		PlatformOrderMineEvaluation mineEvaluation = platformOrderEvaluationMapper.selectMineEvaluation(id);
		if(null != evaluationReply){
			evaluationInfo.setEvaluationReply(evaluationReply);
		}else if(null != evaluationInfo){
			evaluationInfo.setEvaluation(mineEvaluation);
		}
		return evaluationInfo;
	}
}
