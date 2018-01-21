package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class platformBondConfigure implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String money;

    private Date time;

    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
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