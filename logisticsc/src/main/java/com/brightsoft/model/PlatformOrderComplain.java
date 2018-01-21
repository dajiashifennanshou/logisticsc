package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

import com.brightsoft.utils.DateTools;
/**
 * 订单投诉表
 * @author ThinkPad
 *
 */
public class PlatformOrderComplain implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;
	private String outletsIds;

    private String orderNumber;

    private String complaintContent;

    private Date complaintTime;

    private Integer state;

    private Long userId;
    
    private Integer status;
    /***********线路信息************/
    private String line;//线路起点到终点
    
    /************投诉人信息*****************/
    private String loginName;//投诉人用户名
    
    /***********回复信息*****************/
    private String handlePeople;//处理人
    
    private String handleContent;//处理内容
    
    private String handleTime;//处理时间
    
    private Integer handleSatisfiedDegree;
    
    /*************查询条件************************/
    private Date startTime;//起始时间
    
    private Date endTime;//结束时间
    
    private String condition;//输入条件

    public Integer getHandleSatisfiedDegree() {
		return handleSatisfiedDegree;
	}

	public String getOutletsIds() {
		return outletsIds;
	}

	public void setOutletsIds(String outletsIds) {
		this.outletsIds = outletsIds;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setHandleSatisfiedDegree(Integer handleSatisfiedDegree) {
		this.handleSatisfiedDegree = handleSatisfiedDegree;
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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getComplaintContent() {
        return complaintContent;
    }

    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent == null ? null : complaintContent.trim();
    }

    public Date getComplaintTime() {
        return complaintTime;
    }

    public void setComplaintTime(Date complaintTime) {
        this.complaintTime = complaintTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = DateTools.string2Date(startTime);
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = DateTools.string2Date(endTime);
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getHandlePeople() {
		return handlePeople;
	}

	public void setHandlePeople(String handlePeople) {
		this.handlePeople = handlePeople;
	}

	public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}

	public String getHandleContent() {
		return handleContent;
	}

	public void setHandleContent(String handleContent) {
		this.handleContent = handleContent;
	}

}