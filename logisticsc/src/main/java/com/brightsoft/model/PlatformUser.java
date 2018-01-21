package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

import com.brightsoft.utils.Const;

/**
 * 用户表
 * @author ThinkPad
 *
 */
public class PlatformUser implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String loginName;

    private String password;

    private String mobile;

    private String email;

    private String address;

    private String trueName;

    private Integer userType;

    private String credType;

    private String credNumber;

    private String cashPassword;

    private Integer status;

    private Long companyId;

    private Double balance;

    private Integer points;
    
    private String salesmanNo;
    
    private String theSelesmanNo;
    
    private Long temporaryCompanyId;
    
    private Long vehicleType;
    
    private String dotAddress;
    
    private Integer userStatus;
    
    private String companyName;
    
    private Date createTime;
    
    private String apply_state;
    
    private String apply_type;
    
    private Date time;
    
    private String staeTime;
    
    private String endTime;
    
    private String companyCode;
    
    private String outletsName;
    
    private String bankNumber;
    private String branchNo;
    private String joinName;
    
    
    
	public String getJoinName() {
		return joinName;
	}

	public void setJoinName(String joinName) {
		this.joinName = joinName;
	}

	public String getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getOutletsName() {
		return outletsName;
	}

	public void setOutletsName(String outletsName) {
		this.outletsName = outletsName;
	}

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getApply_state() {
		return apply_state;
	}

	public void setApply_state(String apply_state) {
		this.apply_state = apply_state;
	}

	public String getApply_type() {
		return apply_type;
	}

	public void setApply_type(String apply_type) {
		this.apply_type = apply_type;
	}

	public String getStaeTime() {
		return staeTime;
	}

	public void setStaeTime(String staeTime) {
		this.staeTime = staeTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserStatusStr() {
		return userStatusStr;
	}

	public void setUserStatusStr(String userStatusStr) {
		this.userStatusStr = userStatusStr;
	}

	private String userStatusStr;

    /*********************************/
    private PlatformUserCompany platformUserCompany;
    
    private String bankaccountnumber;
    
	public String getBankaccountnumber() {
		return bankaccountnumber;
	}

	public void setBankaccountnumber(String bankaccountnumber) {
		this.bankaccountnumber = bankaccountnumber;
	}

	public String getCompanyName() {
		return companyName;
	}

    public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	private Long outletsId;

    public Long getOutletsId() {
		return outletsId;
	}

	public void setOutletsId(Long outletsId) {
		this.outletsId = outletsId;
	}

	public PlatformUserCompany getPlatformUserCompany() {
		return platformUserCompany;
	}

	public void setPlatformUserCompany(PlatformUserCompany platformUserCompany) {
		this.platformUserCompany = platformUserCompany;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getCredType() {
        return credType;
    }

    public void setCredType(String credType) {
        this.credType = credType == null ? null : credType.trim();
    }

    public String getCredNumber() {
        return credNumber;
    }

    public void setCredNumber(String credNumber) {
        this.credNumber = credNumber == null ? null : credNumber.trim();
    }

    public String getCashPassword() {
        return cashPassword;
    }

    public void setCashPassword(String cashPassword) {
        this.cashPassword = cashPassword == null ? null : cashPassword.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
    	switch (status) {
		case Const.STATE_YES:
			this.userStatusStr = "已启用";
			break;
		case Const.STATE_NO:
			this.userStatusStr = "未启用";
			break;

		default:
			break;
		}
        this.status = status;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

	public String getSalesmanNo() {
		return salesmanNo;
	}

	public void setSalesmanNo(String salesmanNo) {
		this.salesmanNo = salesmanNo;
	}

	public String getTheSelesmanNo() {
		return theSelesmanNo;
	}

	public void setTheSelesmanNo(String theSelesmanNo) {
		this.theSelesmanNo = theSelesmanNo;
	}

	public Long getTemporaryCompanyId() {
		return temporaryCompanyId;
	}

	public void setTemporaryCompanyId(Long temporaryCompanyId) {
		this.temporaryCompanyId = temporaryCompanyId;
	}

	public Long getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(Long vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getDotAddress() {
		return dotAddress;
	}

	public void setDotAddress(String dotAddress) {
		this.dotAddress = dotAddress;
	}
    
}