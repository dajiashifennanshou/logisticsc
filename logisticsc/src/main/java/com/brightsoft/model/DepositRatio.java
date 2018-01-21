package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DepositRatio implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long lineId;

    private Double depositRatio;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    private Date createTime;

    private Integer status;

    private Long createPersonId;
    
    private String note;
    
    private Long checkPersonId;
    
    private Date checkTime;
    /*****************************/
    private OutletsInfo outletsInfo;
    
    private LineInfo lineInfo;
    
    /************查询条件*****************/
    private String condition;//查询条件
    /****************************/
    private String startOutletsName;
    
    private String endOutletsName;
    
    private String lineName;
    
    /*************企业信息***************/
    private String companyName;//公司名称
    
    /*************网点信息***************/
    private String name;//网点名称

    /*************创建人信息*******************/
    private String loginName;//账号
    
    private String trueName;//姓名

    public LineInfo getLineInfo() {
		return lineInfo;
	}

	public void setLineInfo(LineInfo lineInfo) {
		this.lineInfo = lineInfo;
	}

	public OutletsInfo getOutletsInfo() {
		return outletsInfo;
	}

	public void setOutletsInfo(OutletsInfo outletsInfo) {
		this.outletsInfo = outletsInfo;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public Double getDepositRatio() {
		return depositRatio;
	}

	public void setDepositRatio(Double depositRatio) {
		this.depositRatio = depositRatio;
	}

	public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getCheckPersonId() {
		return checkPersonId;
	}

	public void setCheckPersonId(Long checkPersonId) {
		this.checkPersonId = checkPersonId;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
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

}