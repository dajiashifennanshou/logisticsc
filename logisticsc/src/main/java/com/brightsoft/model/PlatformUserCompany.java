package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司表
 * @author ThinkPad
 *
 */
public class PlatformUserCompany implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;
    
    private String companyCode;
    
    private String companyName;

    private String companyAddress;

    private String legalPerson;

    private String legalMobile;

    private String contacts;

    private String contactsMobile;

    private String postCode;

    private String companyPhone;

    private String companyFax;

    private String companyTaxNo;

    private String financeEmail;

    private String companyInfo;

    private String logo;

    private String companyPhoto;

    private String legalPhoto;

    private String cardPhoto;

    private String QQ;
    
    private String businessLicense;
    
    private Integer recommended;
    
    private String contacts1;
    
    private String contacts1Mobile;
    
    private String contacts2;
    
    private String contacts2Mobile;
    /*****************************/
    private Long companyId;//
    /*****************查询条件********************/
    private String condition;//查询条件
    
    /*****************tms管理员信息********************/
    private String tmsLoginName;
    
    private Integer tmsUserStatus;
    /*****************管理员信息********************/
    private String loginName;//用户名
     
    private String trueName;//姓名
    
    private String userType;//用户类型
    
    private Integer userStatus;//用户状态
    
    private Long userId;//用户id
    
    private Date createTime;
    
    private String startTime; //开始时间
    
    private String endTime; //结束时间
    
    
    
    public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getTmsLoginName() {
		return tmsLoginName;
	}

	public void setTmsLoginName(String tmsLoginName) {
		this.tmsLoginName = tmsLoginName;
	}

	public Integer getTmsUserStatus() {
		return tmsUserStatus;
	}

	public void setTmsUserStatus(Integer tmsUserStatus) {
		this.tmsUserStatus = tmsUserStatus;
	}

	public Integer getRecommended() {
		return recommended;
	}

	public void setRecommended(Integer recommended) {
		this.recommended = recommended;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getLegalMobile() {
        return legalMobile;
    }

    public void setLegalMobile(String legalMobile) {
        this.legalMobile = legalMobile == null ? null : legalMobile.trim();
    }

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactsMobile() {
		return contactsMobile;
	}

	public void setContactsMobile(String contactsMobile) {
		this.contactsMobile = contactsMobile;
	}

	public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getPostCode() {
		return postCode;
	}

	public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone == null ? null : companyPhone.trim();
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax == null ? null : companyFax.trim();
    }

    public String getCompanyTaxNo() {
        return companyTaxNo;
    }

    public void setCompanyTaxNo(String companyTaxNo) {
        this.companyTaxNo = companyTaxNo == null ? null : companyTaxNo.trim();
    }

    public String getFinanceEmail() {
        return financeEmail;
    }

    public void setFinanceEmail(String financeEmail) {
        this.financeEmail = financeEmail == null ? null : financeEmail.trim();
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo == null ? null : companyInfo.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getCompanyPhoto() {
        return companyPhoto;
    }

    public void setCompanyPhoto(String companyPhoto) {
        this.companyPhoto = companyPhoto == null ? null : companyPhoto.trim();
    }

    public String getLegalPhoto() {
        return legalPhoto;
    }

    public void setLegalPhoto(String legalPhoto) {
        this.legalPhoto = legalPhoto == null ? null : legalPhoto.trim();
    }

    public String getCardPhoto() {
        return cardPhoto;
    }

    public void setCardPhoto(String cardPhoto) {
        this.cardPhoto = cardPhoto == null ? null : cardPhoto.trim();
    }

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContacts1() {
		return contacts1;
	}

	public void setContacts1(String contacts1) {
		this.contacts1 = contacts1;
	}

	public String getContacts1Mobile() {
		return contacts1Mobile;
	}

	public void setContacts1Mobile(String contacts1Mobile) {
		this.contacts1Mobile = contacts1Mobile;
	}

	public String getContacts2() {
		return contacts2;
	}

	public void setContacts2(String contacts2) {
		this.contacts2 = contacts2;
	}

	public String getContacts2Mobile() {
		return contacts2Mobile;
	}

	public void setContacts2Mobile(String contacts2Mobile) {
		this.contacts2Mobile = contacts2Mobile;
	}
	
}