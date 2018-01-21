package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class LineInfo implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String serverType;

    private Long startOutlets;
    
    private String startOutletsName;
    
    private Long endOutlets;
    
    private String endOutletsName;

    private String transportMode;

    private Double timeLong;

    private Double heavyCargoPriceLow;
    
    private Double heavyCargoPriceMid;
    
    private Double heavyCargoPriceHigh;
    
    private Double bulkyCargoPriceLow;

    private Double bulkyCargoPriceMid;
    
    private Double bulkyCargoPriceHigh;

    private Double lowestPrice;

    private Integer status;

    private Date createTime;

    private Long createPersonId;
    
    private Long outletsId;
    
    private Double remainMoney;
    
    private Integer evaluateLevel;
    
    private Integer recommended;

    private Integer releaseState=0;//发布状态默认否
    
    private String lineName;
    /****************************/
    private PlatformUserCompany platformUserCompany;
    
    private OutletsInfo outletsInfo;
    
    private OutletsInfo startOutletsObj;
    
    private OutletsInfo endOutletsObj;
    
    private Integer evalTotal;//评价总数
    
    private Double evalAvg;//评价点平均星级
    
    public Integer getEvalTotal() {
		return evalTotal;
	}

	public void setEvalTotal(Integer evalTotal) {
		this.evalTotal = evalTotal;
	}

	public Double getEvalAvg() {
		return evalAvg;
	}

	public void setEvalAvg(Double evalAvg) {
		this.evalAvg = evalAvg;
	}

	/***************运输方式******************/
    private String transModeValue;
    
    private String serverTypeValue;
    /**************查询条件****************/
    private Long searchStart;//起始网点
    
    private Long searchEnd;//到站网点
    
    private String condition;//输入条件
    
    /**************线路所属网点对象属性****************/
    private String contactPerson;
    
    private String mobile;

    private String phone;

    private String email;
    
	public String getServerTypeValue() {
		return serverTypeValue;
	}

	public void setServerTypeValue(String serverTypeValue) {
		this.serverTypeValue = serverTypeValue;
	}

	public OutletsInfo getStartOutletsObj() {
		return startOutletsObj;
	}

	public void setStartOutletsObj(OutletsInfo startOutletsObj) {
		this.startOutletsObj = startOutletsObj;
	}

	public OutletsInfo getEndOutletsObj() {
		return endOutletsObj;
	}

	public void setEndOutletsObj(OutletsInfo endOutletsObj) {
		this.endOutletsObj = endOutletsObj;
	}

	public PlatformUserCompany getPlatformUserCompany() {
		return platformUserCompany;
	}

	public void setPlatformUserCompany(PlatformUserCompany platformUserCompany) {
		this.platformUserCompany = platformUserCompany;
	}

	public OutletsInfo getOutletsInfo() {
		return outletsInfo;
	}

	public void setOutletsInfo(OutletsInfo outletsInfo) {
		this.outletsInfo = outletsInfo;
	}

	public Integer getRecommended() {
		return recommended;
	}

	public void setRecommended(Integer recommended) {
		this.recommended = recommended;
	}

	public Double getRemainMoney() {
		return remainMoney;
	}

	public void setRemainMoney(Double remainMoney) {
		this.remainMoney = remainMoney;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getStartOutletsName() {
		return startOutletsName;
	}

	public void setStartOutletsName(String startOutletsName) {
		this.startOutletsName = startOutletsName;
	}

	public String getEndOutletsName() {
		return endOutletsName;
	}

	public void setEndOutletsName(String endOutletsName) {
		this.endOutletsName = endOutletsName;
	}
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
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

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode == null ? null : transportMode.trim();
    }

    public Double getTimeLong() {
        return timeLong;
    }

    public void setTimeLong(Double timeLong) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Long getSearchStart() {
		return searchStart;
	}

	public void setSearchStart(Long searchStart) {
		this.searchStart = searchStart;
	}

	public Long getSearchEnd() {
		return searchEnd;
	}

	public void setSearchEnd(Long searchEnd) {
		this.searchEnd = searchEnd;
	}

	public String getTransModeValue() {
		return transModeValue;
	}

	public void setTransModeValue(String transModeValue) {
		this.transModeValue = transModeValue;
	}

	public Integer getEvaluateLevel() {
		return evaluateLevel;
	}

	public void setEvaluateLevel(Integer evaluateLevel) {
		this.evaluateLevel = evaluateLevel;
	}
    
}