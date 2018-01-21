package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 常发货物表
 * @author ThinkPad
 *
 */
public class PlatformUserCommonCargo implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String cargoName;

    private Double singleWeight;

    private Double singleVolume;

    private String packingInfo;

    private Integer state;

    private Date createTime;

    private Long userId;
    
    private String remarks;
    
    private String cargoBrand;
    private String model;
    
    private String packingInfoName;
    
    public String getPackingInfoName() {
		return packingInfoName;
	}

	public void setPackingInfoName(String packingInfoName) {
		this.packingInfoName = packingInfoName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCargoBrand() {
		return cargoBrand;
	}

	public void setCargoBrand(String cargoBrand) {
		this.cargoBrand = cargoBrand;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName == null ? null : cargoName.trim();
    }

    public Double getSingleWeight() {
        return singleWeight;
    }

    public void setSingleWeight(Double singleWeight) {
        this.singleWeight = singleWeight;
    }

    public Double getSingleVolume() {
        return singleVolume;
    }

    public void setSingleVolume(Double singleVolume) {
        this.singleVolume = singleVolume;
    }

    public String getPackingInfo() {
        return packingInfo;
    }

    public void setPackingInfo(String packingInfo) {
        this.packingInfo = packingInfo == null ? null : packingInfo.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}