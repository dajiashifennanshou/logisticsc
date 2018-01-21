package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class Feedback implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String title;

    private String content;

    private Long createPersonId;

    private Date createTime;

    private String contentReply;

    private Date replyTime;

    private Long replyPersonId;

    private Integer status;
    
    private Long outletsId;
    /****************************/
    private User user;//
    
    private SysUser replyUser;//

	public SysUser getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(SysUser replyUser) {
		this.replyUser = replyUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreatePersonId() {
		return createPersonId;
	}

	public void setCreatePersonId(Long createPersonId) {
		this.createPersonId = createPersonId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContentReply() {
		return contentReply;
	}

	public void setContentReply(String contentReply) {
		this.contentReply = contentReply;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public Long getReplyPersonId() {
		return replyPersonId;
	}

	public void setReplyPersonId(Long replyPersonId) {
		this.replyPersonId = replyPersonId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getOutletsId() {
		return outletsId;
	}

	public void setOutletsId(Long outletsId) {
		this.outletsId = outletsId;
	}
    
}