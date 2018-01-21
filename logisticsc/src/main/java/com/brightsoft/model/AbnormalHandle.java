package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class AbnormalHandle implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

    private Long abnormalId;

    private Date handleDate;

    private Double compensationMoney;

    private String customerOpinion;

    private String replyOpinion;

    private String handleResult;
    
    private String preventionMeasures;

    private String handlePerson;
    /************/
    private String handleDateStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAbnormalId() {
        return abnormalId;
    }

    public void setAbnormalId(Long abnormalId) {
        this.abnormalId = abnormalId;
    }

    public Date getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }

    public Double getCompensationMoney() {
        return compensationMoney;
    }

    public void setCompensationMoney(Double compensationMoney) {
        this.compensationMoney = compensationMoney;
    }

    public String getCustomerOpinion() {
        return customerOpinion;
    }

    public void setCustomerOpinion(String customerOpinion) {
        this.customerOpinion = customerOpinion == null ? null : customerOpinion.trim();
    }

    public String getReplyOpinion() {
        return replyOpinion;
    }

    public void setReplyOpinion(String replyOpinion) {
        this.replyOpinion = replyOpinion == null ? null : replyOpinion.trim();
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult == null ? null : handleResult.trim();
    }

    public String getHandlePerson() {
        return handlePerson;
    }

    public void setHandlePerson(String handlePerson) {
        this.handlePerson = handlePerson == null ? null : handlePerson.trim();
    }

	public String getHandleDateStr() {
		return handleDateStr;
	}

	public void setHandleDateStr(String handleDateStr) {
		this.handleDateStr = handleDateStr;
	}

	public String getPreventionMeasures() {
		return preventionMeasures;
	}

	public void setPreventionMeasures(String preventionMeasures) {
		this.preventionMeasures = preventionMeasures;
	}
}