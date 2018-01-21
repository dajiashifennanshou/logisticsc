package com.brightsoft.controller.vo;

/**
 * 订单 查询 条件 
 * @author yangshoubao
 *
 */
public class OrderSearchParams extends BaseSearchParams{

	private String outletsName;
	private String outletsIds;

	public String getOutletsName() {
		return outletsName;
	}

	public String getOutletsIds() {
		return outletsIds;
	}

	public void setOutletsIds(String outletsIds) {
		this.outletsIds = outletsIds;
	}

	public void setOutletsName(String outletsName) {
		this.outletsName = outletsName;
	}
	
}
