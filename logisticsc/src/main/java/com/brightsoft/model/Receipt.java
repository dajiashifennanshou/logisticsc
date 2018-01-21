package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class Receipt implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String wayBillNo;

    private Date receiveTime;

    private Integer receiptState;

    private Long wayBillId;

    private String receivePeison;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWayBillNo() {
        return wayBillNo;
    }

    public void setWayBillNo(String wayBillNo) {
        this.wayBillNo = wayBillNo == null ? null : wayBillNo.trim();
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getReceiptState() {
        return receiptState;
    }

    public void setReceiptState(Integer receiptState) {
        this.receiptState = receiptState;
    }

    public Long getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(Long wayBillId) {
        this.wayBillId = wayBillId;
    }

    public String getReceivePeison() {
        return receivePeison;
    }

    public void setReceivePeison(String receivePeison) {
        this.receivePeison = receivePeison == null ? null : receivePeison.trim();
    }
}