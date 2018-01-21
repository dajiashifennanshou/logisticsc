package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String username;

    private String password;

    private Integer admintype;

    private Long adminstatus;

    private Date creattime;

    private Long organizationId;

    private Date lastlogintime;

    private String lastloginip;

    private Date finallogintime;

    private String finalloginip;

    private String realname;

    private Long roleId;

    private Long effect;
    
    private String branchNo;
    
    private Integer isBranch;
    
    

    
    public Integer getIsBranch() {
		return isBranch;
	}

	public void setIsBranch(Integer isBranch) {
		this.isBranch = isBranch;
	}

	public String getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAdmintype() {
        return admintype;
    }

    public void setAdmintype(Integer admintype) {
        this.admintype = admintype;
    }

    public Long getAdminstatus() {
        return adminstatus;
    }

    public void setAdminstatus(Long adminstatus) {
        this.adminstatus = adminstatus;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getLastloginip() {
        return lastloginip;
    }

    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip == null ? null : lastloginip.trim();
    }

    public Date getFinallogintime() {
        return finallogintime;
    }

    public void setFinallogintime(Date finallogintime) {
        this.finallogintime = finallogintime;
    }

    public String getFinalloginip() {
        return finalloginip;
    }

    public void setFinalloginip(String finalloginip) {
        this.finalloginip = finalloginip == null ? null : finalloginip.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getEffect() {
        return effect;
    }

    public void setEffect(Long effect) {
        this.effect = effect;
    }
}