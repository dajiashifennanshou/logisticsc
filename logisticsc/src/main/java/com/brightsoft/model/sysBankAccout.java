package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class sysBankAccout implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String customernumber;

    private String ledgerno;

    private String requestid;

    private String hmac;

    private Date time;

    private Long sysUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomernumber() {
        return customernumber;
    }

    public void setCustomernumber(String customernumber) {
        this.customernumber = customernumber == null ? null : customernumber.trim();
    }

    public String getLedgerno() {
        return ledgerno;
    }

    public void setLedgerno(String ledgerno) {
        this.ledgerno = ledgerno == null ? null : ledgerno.trim();
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid == null ? null : requestid.trim();
    }

    public String getHmac() {
        return hmac;
    }

    public void setHmac(String hmac) {
        this.hmac = hmac == null ? null : hmac.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }
}