package com.brightsoft.controller.vo;

import java.util.List;

/**
 * 运单 查询 条件 
 * @author yangshoubao
 *
 */
public class WayBillSearchParams extends BaseSearchParams{

	/** 起始网点 */
	private Long startOutlets;
	
	/** 目的网点 */
	private Long targetOutlets;
	
	/** 回单状态 */
	private String receiptStatus;
	
	/** 签收状态 */
	private String signStatus,out_store_status,transfer_status;
	
	/** 收货方式 */
	private String receiveType;
	
	/** 是否 只查询 代收货款运单 */
	private String isAgencyFund;
	
	/** 费用类型 */
	private String costType;
	
	/** 异常状态 */
	private String exceptionStatus;
	
	/** 状态 集合 */
	private List<Integer> statusList;
	
	/** 运单号集合 */
	private List<String> wayBillNumbers;
	
	/** 不等于的支付方式 */
	private String payMethodNo;
	
	/** 起始网点 状态集合 */
	private List<Integer> startStatusList;
	
	/** 起始网点 支付方式集合 */
	private List<Integer> startPayMethodList;
	
	/** 目的网点 状态集合 */
	private List<Integer> targetStatusList;
	
	/** 目的网点 支付方式集合 */
	private List<Integer> targetPayMethodList;
	
	/** 是否回单 */
	private Integer isReceipt;
	private String outletsIds;

	public Integer getIsReceipt() {
		return isReceipt;
	}

	public String getOut_store_status() {
		return out_store_status;
	}

	public void setOut_store_status(String out_store_status) {
		this.out_store_status = out_store_status;
	}

	public String getTransfer_status() {
		return transfer_status;
	}

	public void setTransfer_status(String transfer_status) {
		this.transfer_status = transfer_status;
	}

	public String getOutletsIds() {
		return outletsIds;
	}

	public void setOutletsIds(String outletsIds) {
		this.outletsIds = outletsIds;
	}

	public void setIsReceipt(Integer isReceipt) {
		this.isReceipt = isReceipt;
	}

	public List<Integer> getStartStatusList() {
		return startStatusList;
	}

	public void setStartStatusList(List<Integer> startStatusList) {
		this.startStatusList = startStatusList;
	}

	public List<Integer> getStartPayMethodList() {
		return startPayMethodList;
	}

	public void setStartPayMethodList(List<Integer> startPayMethodList) {
		this.startPayMethodList = startPayMethodList;
	}

	public List<Integer> getTargetStatusList() {
		return targetStatusList;
	}

	public void setTargetStatusList(List<Integer> targetStatusList) {
		this.targetStatusList = targetStatusList;
	}

	public List<Integer> getTargetPayMethodList() {
		return targetPayMethodList;
	}

	public void setTargetPayMethodList(List<Integer> targetPayMethodList) {
		this.targetPayMethodList = targetPayMethodList;
	}

	public String getPayMethodNo() {
		return payMethodNo;
	}

	public void setPayMethodNo(String payMethodNo) {
		this.payMethodNo = payMethodNo;
	}

	public String getExceptionStatus() {
		return exceptionStatus;
	}

	public void setExceptionStatus(String exceptionStatus) {
		this.exceptionStatus = exceptionStatus;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public List<String> getWayBillNumbers() {
		return wayBillNumbers;
	}

	public void setWayBillNumbers(List<String> wayBillNumbers) {
		this.wayBillNumbers = wayBillNumbers;
	}

	public String getIsAgencyFund() {
		return isAgencyFund;
	}

	public void setIsAgencyFund(String isAgencyFund) {
		this.isAgencyFund = isAgencyFund;
	}

	public String getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}

	public String getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}

	public List<Integer> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Integer> statusList) {
		this.statusList = statusList;
	}

	public String getReceiptStatus() {
		return receiptStatus;
	}

	public void setReceiptStatus(String receiptStatus) {
		this.receiptStatus = receiptStatus;
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
