package com.brightsoft.controller.vo;

/**
 * 应付款账单 查询 条件 
 * @author yangshoubao
 *
 */
public class ReceivablePayBillSearchParams extends BaseSearchParams{

	private String costSubject; // 费用项目
	
	private String isCompleted; // 结算状态

	public String getCostSubject() {
		return costSubject;
	}

	public void setCostSubject(String costSubject) {
		this.costSubject = costSubject;
	}

	public String getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(String isCompleted) {
		this.isCompleted = isCompleted;
	}
	
}
