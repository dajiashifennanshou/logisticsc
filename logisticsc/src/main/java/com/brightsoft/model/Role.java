package com.brightsoft.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Role implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String roleName;

    private String roleDesc;
    
    private Long createPersonId;
    
    private Integer ownerType;
    
    private Timestamp createTime;
    
    private Integer roleStatus;
    
    private Integer platformType;
    /************菜单信息*************/
    private List<Menu> menus;//菜单信息
    
	public Integer getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}

	public Integer getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

	public Long getCreatePersonId() {
		return createPersonId;
	}

	public void setCreatePersonId(Long createPersonId) {
		this.createPersonId = createPersonId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getPlatformType() {
		return platformType;
	}

	public void setPlatformType(Integer platformType) {
		this.platformType = platformType;
	}

}