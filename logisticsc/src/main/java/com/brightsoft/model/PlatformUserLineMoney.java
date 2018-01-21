package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 线路充值表
 * @author ThinkPad
 *
 */
public class PlatformUserLineMoney implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long lineId;

    private Long userId;

    private Double money;

    private Date operTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
}