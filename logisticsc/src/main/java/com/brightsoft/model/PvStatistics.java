package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class PvStatistics implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String ip;

    private Date visitTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }
}