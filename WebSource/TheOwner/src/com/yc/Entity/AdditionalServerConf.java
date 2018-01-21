package com.yc.Entity;

import java.io.Serializable;

public class AdditionalServerConf implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private Double heavyCargo;

    private Double bulkyCargo;

    private Double noUpstairsLowPrice;

    private Double elevatorAdditional;

    private Double noElevatorAdditional;

    private Double goUpstairsLowPrice;

    private Integer isLoadAndUnload;

    private Integer isImExStore;

    private Double inExPrice;

    private Integer isCollectionDelivery;

    private Double collectionDeliveryRatio;
    
    private Integer isLineInsurance;
    
    private Double lineInsuranceRatio;

    private Integer isReceipt;

    private Long companyId;
    
    private Integer isImmediatePay;

    private Integer isArrivePay;

    private Integer isAdvancePay;

    private Integer isCommonReceipt;

    private Double commonReceiptRate;

    private Integer isAddTaxReceipt;

    private Double addTaxReceiptRate;

    private Integer isNoReceipt;
    
    private Long outletsId;
    
    private String outletsName;
    
    private String companyName;

    public String getOutletsName() {
		return outletsName;
	}

	public void setOutletsName(String outletsName) {
		this.outletsName = outletsName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getOutletsId() {
		return outletsId;
	}

	public void setOutletsId(Long outletsId) {
		this.outletsId = outletsId;
	}

	public Integer getIsImmediatePay() {
		return isImmediatePay;
	}

	public void setIsImmediatePay(Integer isImmediatePay) {
		this.isImmediatePay = isImmediatePay;
	}

	public Integer getIsArrivePay() {
		return isArrivePay;
	}

	public void setIsArrivePay(Integer isArrivePay) {
		this.isArrivePay = isArrivePay;
	}

	public Integer getIsAdvancePay() {
		return isAdvancePay;
	}

	public void setIsAdvancePay(Integer isAdvancePay) {
		this.isAdvancePay = isAdvancePay;
	}

	public Integer getIsCommonReceipt() {
		return isCommonReceipt;
	}

	public void setIsCommonReceipt(Integer isCommonReceipt) {
		this.isCommonReceipt = isCommonReceipt;
	}

	public Double getCommonReceiptRate() {
		return commonReceiptRate;
	}

	public void setCommonReceiptRate(Double commonReceiptRate) {
		this.commonReceiptRate = commonReceiptRate;
	}

	public Integer getIsAddTaxReceipt() {
		return isAddTaxReceipt;
	}

	public void setIsAddTaxReceipt(Integer isAddTaxReceipt) {
		this.isAddTaxReceipt = isAddTaxReceipt;
	}

	public Double getAddTaxReceiptRate() {
		return addTaxReceiptRate;
	}

	public void setAddTaxReceiptRate(Double addTaxReceiptRate) {
		this.addTaxReceiptRate = addTaxReceiptRate;
	}

	public Integer getIsNoReceipt() {
		return isNoReceipt;
	}

	public void setIsNoReceipt(Integer isNoReceipt) {
		this.isNoReceipt = isNoReceipt;
	}

	public Integer getIsLineInsurance() {
		return isLineInsurance;
	}

	public void setIsLineInsurance(Integer isLineInsurance) {
		this.isLineInsurance = isLineInsurance;
	}

	public Double getLineInsuranceRatio() {
		return lineInsuranceRatio;
	}

	public void setLineInsuranceRatio(Double lineInsuranceRatio) {
		this.lineInsuranceRatio = lineInsuranceRatio;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getHeavyCargo() {
        return heavyCargo;
    }

    public void setHeavyCargo(Double heavyCargo) {
        this.heavyCargo = heavyCargo;
    }

    public Double getBulkyCargo() {
        return bulkyCargo;
    }

    public void setBulkyCargo(Double bulkyCargo) {
        this.bulkyCargo = bulkyCargo;
    }

    public Double getNoUpstairsLowPrice() {
        return noUpstairsLowPrice;
    }

    public void setNoUpstairsLowPrice(Double noUpstairsLowPrice) {
        this.noUpstairsLowPrice = noUpstairsLowPrice;
    }

    public Double getElevatorAdditional() {
        return elevatorAdditional;
    }

    public void setElevatorAdditional(Double elevatorAdditional) {
        this.elevatorAdditional = elevatorAdditional;
    }

    public Double getNoElevatorAdditional() {
        return noElevatorAdditional;
    }

    public void setNoElevatorAdditional(Double noElevatorAdditional) {
        this.noElevatorAdditional = noElevatorAdditional;
    }

    public Double getGoUpstairsLowPrice() {
        return goUpstairsLowPrice;
    }

    public void setGoUpstairsLowPrice(Double goUpstairsLowPrice) {
        this.goUpstairsLowPrice = goUpstairsLowPrice;
    }

    public Integer getIsLoadAndUnload() {
        return isLoadAndUnload;
    }

    public void setIsLoadAndUnload(Integer isLoadAndUnload) {
        this.isLoadAndUnload = isLoadAndUnload;
    }

    public Integer getIsImExStore() {
        return isImExStore;
    }

    public void setIsImExStore(Integer isImExStore) {
        this.isImExStore = isImExStore;
    }

    public Double getInExPrice() {
        return inExPrice;
    }

    public void setInExPrice(Double inExPrice) {
        this.inExPrice = inExPrice;
    }

    public Integer getIsCollectionDelivery() {
        return isCollectionDelivery;
    }

    public void setIsCollectionDelivery(Integer isCollectionDelivery) {
        this.isCollectionDelivery = isCollectionDelivery;
    }

    public Double getCollectionDeliveryRatio() {
        return collectionDeliveryRatio;
    }

    public void setCollectionDeliveryRatio(Double collectionDeliveryRatio) {
        this.collectionDeliveryRatio = collectionDeliveryRatio;
    }

    public Integer getIsReceipt() {
        return isReceipt;
    }

    public void setIsReceipt(Integer isReceipt) {
        this.isReceipt = isReceipt;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}