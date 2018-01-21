package com.brightsoft.controller.vo;

/**
 * 发车单 查询 条件 
 * @author yangshoubao
 *
 */
public class DepartListSearchParams extends BaseSearchParams{

	/** 起始网点 */
	private Long startOutlets;
	
	/** 目的网点 */
	private Long targetOutlets;
	
	/** 途径网点 */
	private Long wayOutlets;
	
	/** 是否完结 */
	private String isCompleted;
	private String outletsIds;
	public String getOutletsIds() {
		return outletsIds;
	}

	public void setOutletsIds(String outletsIds) {
		this.outletsIds = outletsIds;
	}

	public Long getWayOutlets() {
		return wayOutlets;
	}

	public void setWayOutlets(Long wayOutlets) {
		this.wayOutlets = wayOutlets;
	}

	public String getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(String isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Long getStartOutlets() {
		return startOutlets;
	}

	public void setStartOutlets(Long startOutlets) {
		this.startOutlets = startOutlets;
	}

	public Long getTargetOutlets() {
		return targetOutlets;
	}

	public void setTargetOutlets(Long targetOutlets) {
		this.targetOutlets = targetOutlets;
	}
	
}
