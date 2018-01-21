package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 合作伙伴
 * @author ThinkPad
 *
 */
public class SysPartner implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String partnerName;

    private String pictureUrl;

    private String partnerUrl;
    
    private Date partnerTime;

    private Long userId;
    
    private String username;
    
    

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName == null ? null : partnerName.trim();
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public String getPartnerUrl() {
        return partnerUrl;
    }

    public void setPartnerUrl(String partnerUrl) {
        this.partnerUrl = partnerUrl == null ? null : partnerUrl.trim();
    }

    public Date getPartnerTime() {
        return partnerTime;
    }

    public void setPartnerTime(Date partnerTime) {
        this.partnerTime = partnerTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}