package com.yc.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OutletsInfo implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String outletsNumber;
    
    private String name;

    private Long type;

    private Long nature;

    private String province;

    private String city;

    private String county;

    private String address;

    private String contactPerson;

    private String mobile;

    private String phone;

    private String email;

    private String remark;

    private Integer status;

    private Date createTime;

    private Long createPersonId;

    private Long companyId;
    
    /*********************************/
    private PlatformUserCompany platformUserCompany;
    
    /***************条件查询*******************/
    private String condition;//搜索条件
    
    /***************公司信息*******************/
    private String companyName;//公司名称
    
    private String companyCode; //公司组织代码
    
    /***************管理员信息*******************/
    private String trueName;//姓名
    
    private String loginName;//账号
 
    /***************字典信息***********************/
    private String provinceValue;//省完整名称
    
    private String cityValue;//市完整名称
    
    private String countyValue;//区完整名称
    
    private String typeValue;//网点类型
    
    private String natureValue;//网点性质
    



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

	public String getProvinceValue() {
		return provinceValue;
	}

	public void setProvinceValue(String provinceValue) {
		this.provinceValue = provinceValue;
	}

	public String getCityValue() {
		return cityValue;
	}

	public void setCityValue(String cityValue) {
		this.cityValue = cityValue;
	}

	public String getCountyValue() {
		return countyValue;
	}

	public void setCountyValue(String countyValue) {
		this.countyValue = countyValue;
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

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getNature() {
		return nature;
	}

	public void setNature(Long nature) {
		this.nature = nature;
	}

	public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson == null ? null : contactPerson.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatePersonId() {
        return createPersonId;
    }

    public void setCreatePersonId(Long createPersonId) {
        this.createPersonId = createPersonId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

	public String getOutletsNumber() {
		return outletsNumber;
	}

	public void setOutletsNumber(String outletsNumber) {
		this.outletsNumber = outletsNumber;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getNatureValue() {
		return natureValue;
	}

	public void setNatureValue(String natureValue) {
		this.natureValue = natureValue;
	}

}