package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class SysConfig implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String itemName;

    private String itemAbbr;

    private Long updateUserId;

    private Date udpateDate;

    private String itemContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemAbbr() {
        return itemAbbr;
    }

    public void setItemAbbr(String itemAbbr) {
        this.itemAbbr = itemAbbr == null ? null : itemAbbr.trim();
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUdpateDate() {
        return udpateDate;
    }

    public void setUdpateDate(Date udpateDate) {
        this.udpateDate = udpateDate;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent == null ? null : itemContent.trim();
    }
}