package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 消费记录表
 * @author ThinkPad
 *
 */
public class PlatformUserConsumeRecord implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Date consumeTime;

    private Double money;

    private String consumeCard;

    private String remark;

    private String consumeType;

    private String chargeCard;

    private String consumeUser;

    private String chargeUser;

    private Long userId;
    
    private String orderNumber;
    
    // ----------------------
    
    private String userName;
    
    private String mobile;

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Date consumeTime) {
        this.consumeTime = consumeTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getConsumeCard() {
        return consumeCard;
    }

    public void setConsumeCard(String consumeCard) {
        this.consumeCard = consumeCard == null ? null : consumeCard.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(String consumeType) {
        this.consumeType = consumeType == null ? null : consumeType.trim();
    }

    public String getChargeCard() {
        return chargeCard;
    }

    public void setChargeCard(String chargeCard) {
        this.chargeCard = chargeCard == null ? null : chargeCard.trim();
    }

    public String getConsumeUser() {
        return consumeUser;
    }

    public void setConsumeUser(String consumeUser) {
        this.consumeUser = consumeUser == null ? null : consumeUser.trim();
    }

    public String getChargeUser() {
        return chargeUser;
    }

    public void setChargeUser(String chargeUser) {
        this.chargeUser = chargeUser == null ? null : chargeUser.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}