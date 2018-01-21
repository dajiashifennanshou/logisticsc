package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String userNumber;
    
    private String loginName;

    private String password;

    private String trueName;

    private Long outletsId;

    private String phone;

    private String qq;

    private String email;
    
    private Long companyId;
    
    private Integer userType;
    
    private Integer status;
    
    private Integer userStatus;
    
    private String address;
    
    private Date createTime;

    /***********信息*****************/
    private String bankaccountnumber;
    
    private PlatformUserCompany platformUserCompany;//所属公司
    
    private OutletsInfo outletsInfo;//所谓网点
    
    private List<Role> roleList;//所属角色
    
    private String companyName;//公司名称 
    
    private String companyCode;//组织代码

    /***********所属网点信息*****************/
    private String outletsName;//网点名称

    /***********查询条件*****************/
    private String condition;//查询条件

	public String getBankaccountnumber() {
		return bankaccountnumber;
	}

	public void setBankaccountnumber(String bankaccountnumber) {
		this.bankaccountnumber = bankaccountnumber;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public Long getOutletsId() {
        return outletsId;
    }

    public void setOutletsId(Long outletsId) {
        this.outletsId = outletsId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOutletsName() {
		return outletsName;
	}

	public void setOutletsName(String outletsName) {
		this.outletsName = outletsName;
	}

	public PlatformUserCompany getPlatformUserCompany() {
		return platformUserCompany;
	}

	public void setPlatformUserCompany(PlatformUserCompany platformUserCompany) {
		this.platformUserCompany = platformUserCompany;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public OutletsInfo getOutletsInfo() {
		return outletsInfo;
	}

	public void setOutletsInfo(OutletsInfo outletsInfo) {
		this.outletsInfo = outletsInfo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}