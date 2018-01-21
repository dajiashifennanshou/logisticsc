package com.brightsoft.controller.vo;

/**
 * 收款订单 查询 条件 
 * @author yangshoubao
 *
 */
public class ReceiveMoneyOrderSearchParams extends BaseSearchParams{

	private String costType;

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}
	
}
