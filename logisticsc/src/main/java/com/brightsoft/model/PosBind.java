package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class PosBind implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long outletsId;

    private String posSn;

    private String loginAccount;

    private String loginPwd;

    private Date createTime;
    
    private Long posBindId;

    public Long getPosBindId() {
		return posBindId;
	}

	public void setPosBindId(Long posBindId) {
		this.posBindId = posBindId;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOutletsId() {
        return outletsId;
    }

    public void setOutletsId(Long outletsId) {
        this.outletsId = outletsId;
    }

    public String getPosSn() {
        return posSn;
    }

    public void setPosSn(String posSn) {
        this.posSn = posSn == null ? null : posSn.trim();
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount == null ? null : loginAccount.trim();
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}