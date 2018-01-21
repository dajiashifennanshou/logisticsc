package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class platformBankConfigure implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String minsettleamount;

    private String riskreserveday;

    private String manualsettle;

    private Date time;

    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMinsettleamount() {
        return minsettleamount;
    }

    public void setMinsettleamount(String minsettleamount) {
        this.minsettleamount = minsettleamount == null ? null : minsettleamount.trim();
    }

    public String getRiskreserveday() {
        return riskreserveday;
    }

    public void setRiskreserveday(String riskreserveday) {
        this.riskreserveday = riskreserveday == null ? null : riskreserveday.trim();
    }

    public String getManualsettle() {
        return manualsettle;
    }

    public void setManualsettle(String manualsettle) {
        this.manualsettle = manualsettle == null ? null : manualsettle.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
}