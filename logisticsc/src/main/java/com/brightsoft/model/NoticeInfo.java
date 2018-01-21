package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

import com.brightsoft.utils.DateTools;

public class NoticeInfo implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String subject;

    private String content;

    private Integer noticeType;

    private Date createTime;

    private Integer status;

    private Long createPersonId;
    
    /*************************/
    private Date startTime;//起始时间
    
    private Date endTime;//结束时间
    
    private String noticeTypeString;//通知类型字符串

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
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

	public String getNoticeTypeString() {
		return noticeTypeString;
	}

	public void setNoticeTypeString(String noticeTypeString) {
		this.noticeTypeString = noticeTypeString;
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
    
}