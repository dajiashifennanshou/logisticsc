package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class SystemLineInfo implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String companyName;

    private String startOutletsName;

    private String endOutletsName;

    private String startProvince;

    private String startCity;

    private String startCounty;

    private String endProvince;

    private String endCity;

    private String endCounty;

    private String serverType;

    private Long transportMode;

    private Integer timeLong;

    private Double heavyCargoPriceLow;

    private Double heavyCargoPriceMid;

    private Double heavyCargoPriceHigh;

    private Double bulkyCargoPriceLow;

    private Double bulkyCargoPriceMid;

    private Double bulkyCargoPriceHigh;

    private Double lowestPrice;

    private Integer releaseState;

    private Date createTime;

    private Integer isTakeCargo;

    private Integer isGiveCargo;

    private Long startOutlets;

    private Long endOutlets;

    private Integer status;

    private Long createPersonId;

    private Long outletsId;

    private Double evaluateLevel;

    private Double remainMoney;

    private Integer recommended;

    private String qq;

    private Long companyId;
    
    private String phone;
    
    /****/
    private String startProvinceValue;
    
    private String startCityValue;
    
    private String startCountyValue;
    
    private String endProvinceValue;
    
    private String endCityValue;
    
    public String getTransModeValue() {
		return transModeValue;
	}

	public void setTransModeValue(String transModeValue) {
		this.transModeValue = transModeValue;
	}

	private String endCountyValue;
    
    private String transModeValue;

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

    public String getStartOutletsName() {
        return startOutletsName;
    }

    public void setStartOutletsName(String startOutletsName) {
        this.startOutletsName = startOutletsName == null ? null : startOutletsName.trim();
    }

    public String getEndOutletsName() {
        return endOutletsName;
    }

    public void setEndOutletsName(String endOutletsName) {
        this.endOutletsName = endOutletsName == null ? null : endOutletsName.trim();
    }

    public String getStartProvince() {
        return startProvince;
    }

    public void setStartProvince(String startProvince) {
        this.startProvince = startProvince == null ? null : startProvince.trim();
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity == null ? null : startCity.trim();
    }

    public String getStartCounty() {
        return startCounty;
    }

    public void setStartCounty(String startCounty) {
        this.startCounty = startCounty == null ? null : startCounty.trim();
    }

    public String getEndProvince() {
        return endProvince;
    }

    public void setEndProvince(String endProvince) {
        this.endProvince = endProvince == null ? null : endProvince.trim();
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity == null ? null : endCity.trim();
    }

    public String getEndCounty() {
        return endCounty;
    }

    public void setEndCounty(String endCounty) {
        this.endCounty = endCounty == null ? null : endCounty.trim();
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType == null ? null : serverType.trim();
    }

    public Long getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(Long transportMode) {
        this.transportMode = transportMode;
    }

    public Integer getTimeLong() {
        return timeLong;
    }

    public void setTimeLong(Integer timeLong) {
        this.timeLong = timeLong;
    }

    public Double getHeavyCargoPriceLow() {
        return heavyCargoPriceLow;
    }

    public void setHeavyCargoPriceLow(Double heavyCargoPriceLow) {
        this.heavyCargoPriceLow = heavyCargoPriceLow;
    }

    public Double getHeavyCargoPriceMid() {
        return heavyCargoPriceMid;
    }

    public void setHeavyCargoPriceMid(Double heavyCargoPriceMid) {
        this.heavyCargoPriceMid = heavyCargoPriceMid;
    }

    public Double getHeavyCargoPriceHigh() {
        return heavyCargoPriceHigh;
    }

    public void setHeavyCargoPriceHigh(Double heavyCargoPriceHigh) {
        this.heavyCargoPriceHigh = heavyCargoPriceHigh;
    }

    public Double getBulkyCargoPriceLow() {
        return bulkyCargoPriceLow;
    }

    public void setBulkyCargoPriceLow(Double bulkyCargoPriceLow) {
        this.bulkyCargoPriceLow = bulkyCargoPriceLow;
    }

    public Double getBulkyCargoPriceMid() {
        return bulkyCargoPriceMid;
    }

    public void setBulkyCargoPriceMid(Double bulkyCargoPriceMid) {
        this.bulkyCargoPriceMid = bulkyCargoPriceMid;
    }

    public Double getBulkyCargoPriceHigh() {
        return bulkyCargoPriceHigh;
    }

    public void setBulkyCargoPriceHigh(Double bulkyCargoPriceHigh) {
        this.bulkyCargoPriceHigh = bulkyCargoPriceHigh;
    }

    public Double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(Double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public Integer getReleaseState() {
        return releaseState;
    }

    public void setReleaseState(Integer releaseState) {
        this.releaseState = releaseState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getStartOutlets() {
        return startOutlets;
    }

    public void setStartOutlets(Long startOutlets) {
        this.startOutlets = startOutlets;
    }

    public Long getEndOutlets() {
        return endOutlets;
    }

    public void setEndOutlets(Long endOutlets) {
        this.endOutlets = endOutlets;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreatePersonId() {
        return createPersonId;
    }

    public void setCreatePersonId(Long createPersonId) {
        this.createPersonId = createPersonId;
    }

    public Long getOutletsId() {
        return outletsId;
    }

    public void setOutletsId(Long outletsId) {
        this.outletsId = outletsId;
    }

    public Double getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(Double evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }

    public Double getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(Double remainMoney) {
        this.remainMoney = remainMoney;
    }

    public Integer getRecommended() {
        return recommended;
    }

    public void setRecommended(Integer recommended) {
        this.recommended = recommended;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

	public String getStartProvinceValue() {
		return startProvinceValue;
	}

	public void setStartProvinceValue(String startProvinceValue) {
		this.startProvinceValue = startProvinceValue;
	}

	public String getStartCityValue() {
		return startCityValue;
	}

	public void setStartCityValue(String startCityValue) {
		this.startCityValue = startCityValue;
	}

	public String getStartCountyValue() {
		return startCountyValue;
	}

	public void setStartCountyValue(String startCountyValue) {
		this.startCountyValue = startCountyValue;
	}

	public String getEndProvinceValue() {
		return endProvinceValue;
	}

	public void setEndProvinceValue(String endProvinceValue) {
		this.endProvinceValue = endProvinceValue;
	}

	public String getEndCityValue() {
		return endCityValue;
	}

	public void setEndCityValue(String endCityValue) {
		this.endCityValue = endCityValue;
	}

	public String getEndCountyValue() {
		return endCountyValue;
	}

	public void setEndCountyValue(String endCountyValue) {
		this.endCountyValue = endCountyValue;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getIsTakeCargo() {
		return isTakeCargo;
	}

	public void setIsTakeCargo(Integer isTakeCargo) {
		this.isTakeCargo = isTakeCargo;
	}

	public Integer getIsGiveCargo() {
		return isGiveCargo;
	}

	public void setIsGiveCargo(Integer isGiveCargo) {
		this.isGiveCargo = isGiveCargo;
	}
}