package com.brightsoft.controller.vo;

/**
 * 运单 查询 条件 
 * @author yangshoubao
 *
 */
public class ReceiptBillSearchParams extends BaseSearchParams{

	/** 回单状态 */
	private String receiptStatus;
	
	/** 起始时间 */
	private String startTime;
	
	/** 截至时间 */
	private String endTime;
	
	/** 是否回单 */
	private Integer isReceipt;
	
	/** 运单号 */
	private String wayBillNumber;
	
	/** 网点id */
	private Long startOutlets;

	public Long getStartOutlets() {
		return startOutlets;
	}

	public void setStartOutlets(Long startOutlets) {
		this.startOutlets = startOutlets;
	}

	public Integer getIsReceipt() {
		return isReceipt;
	}

	public void setIsReceipt(Integer isReceipt) {
		this.isReceipt = isReceipt;
	}

	public String getReceiptStatus() {
		return receiptStatus;
	}

	public void setReceiptStatus(String receiptStatus) {
		this.receiptStatus = receiptStatus;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getWayBillNumber() {
		return wayBillNumber;
	}

	public void setWayBillNumber(String wayBillNumber) {
		this.wayBillNumber = wayBillNumber;
	}
}
