package com.brightsoft.model;

import java.io.Serializable;

public class OutletsPriceRangeConf implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String county;

    private Double weight;

    private Double volume;

    private Integer status;

    private Long outletsId;

    /*****************************/
    private OutletsInfo outletsInfo;
//    private ArrayList<OutletsPriceRangeConf> outletsPriceRangeConfs;
    
    private String countyVal;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
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

	public String getCountyVal() {
		return countyVal;
	}

	public void setCountyVal(String countyVal) {
		this.countyVal = countyVal;
	}

	public OutletsInfo getOutletsInfo() {
		return outletsInfo;
	}

	public void setOutletsInfo(OutletsInfo outletsInfo) {
		this.outletsInfo = outletsInfo;
	}
}