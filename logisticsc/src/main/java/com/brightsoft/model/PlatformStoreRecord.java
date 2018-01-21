package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 线路收藏表
 * @author ThinkPad
 *
 */
public class PlatformStoreRecord implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long lineId;

    private Long userId;

    private Integer state;

    private Date collectionTime;

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

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }
}