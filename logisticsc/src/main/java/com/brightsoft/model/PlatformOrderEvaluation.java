package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

import com.brightsoft.utils.DateTools;
/**
 * 订单评价表
 * @author ThinkPad
 *
 */
public class PlatformOrderEvaluation implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String orderNumber;

    private String evaluateContent;

    private Date evaluateTime;

    private Integer evaluateLevel;

    private Long userId;

    private Integer state;
    private Integer status;
    
    /***************订单信息******************/
    private Date orderTime;//下单时间
    
    /***************用户信息******************/
    private String loginName;//用户名
    
    private String trueName;//姓名 
    
    /***************线路信息*****************/
    private String startOutletsName;//起始网点
    
    private String endOutletsName;//结束网点
    
    private String line;//线路起点到终点
    
    /***************回复信息*****************/
    private String replyContent;//回复内容
    
    private String replyTime;//回复时间

    /***************查询条件*****************/
    private Date startTime;//起始时间
    
    private Date endTime;//结束时间
    
    private String condition;//查询输入条件


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

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent == null ? null : evaluateContent.trim();
    }

    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public Integer getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(Integer evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
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

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}