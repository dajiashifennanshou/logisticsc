package com.brightsoft.model;

import java.io.Serializable;

public class InsComTsType implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long insComId;

    private Long insTsTypeId;

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

    public Long getInsTsTypeId() {
        return insTsTypeId;
    }

    public void setInsTsTypeId(Long insTsTypeId) {
        this.insTsTypeId = insTsTypeId;
    }
}