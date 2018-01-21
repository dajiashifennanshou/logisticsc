package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 货运交易系统用户申请表
 * @author ThinkPad
 *
 */
public class PlatformUserApply implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String applyName;

    private Integer applyType;

    private Integer applyState;

    private String applyFeedback;

    private Long userId;
    private Long version;
    
    private Date time;
    
    

    public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName == null ? null : applyName.trim();
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }


    public Integer getApplyState() {
		return applyState;
	}

	public void setApplyState(Integer applyState) {
		this.applyState = applyState;
	}

	public String getApplyFeedback() {
        return applyFeedback;
    }

    public void setApplyFeedback(String applyFeedback) {
        this.applyFeedback = applyFeedback == null ? null : applyFeedback.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}