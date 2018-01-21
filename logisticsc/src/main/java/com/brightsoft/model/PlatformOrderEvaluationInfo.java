package com.brightsoft.model;

import java.io.Serializable;

/**
 * 查询评价详情
 * @author ThinkPad
 *
 */
public class PlatformOrderEvaluationInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private PlatformOrderMineEvaluation evaluation;
	
	private PlatformOrderEvaluationReply evaluationReply;
	
	public PlatformOrderMineEvaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(PlatformOrderMineEvaluation evaluation) {
		this.evaluation = evaluation;
	}

	public PlatformOrderEvaluationReply getEvaluationReply() {
		return evaluationReply;
	}

	public void setEvaluationReply(PlatformOrderEvaluationReply evaluationReply) {
		this.evaluationReply = evaluationReply;
	}
	
}
