package com.brightsoft.model;

import java.io.Serializable;

public class InsComType implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long insComId;

    private Long insTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInsComId() {
        return insComId;
    }

    public void setInsComId(Long insComId) {
        this.insComId = insComId;
    }

    public Long getInsTypeId() {
        return insTypeId;
    }

    public void setInsTypeId(Long insTypeId) {
        this.insTypeId = insTypeId;
    }
}