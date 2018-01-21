package com.brightsoft.controller.vo;

/**
 * 消费记录 查询条件
 * @author yangshoubao
 *
 */
public class ConsumeRecordSearchParams extends BaseSearchParams{

	private String consumeType;

	public String getConsumeType() {
		return consumeType;
	}

	public void setConsumeType(String consumeType) {
		this.consumeType = consumeType;
	}
	
}
