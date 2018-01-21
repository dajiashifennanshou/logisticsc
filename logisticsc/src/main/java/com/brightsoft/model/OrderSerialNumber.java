package com.brightsoft.model;

import java.io.Serializable;

public class OrderSerialNumber implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer numberType;

    private String companyCode;

    private String outletsNumber;

    private String dateStr;

    private String serialNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberType() {
        return numberType;
    }

    public void setNumberType(Integer numberType) {
        this.numberType = numberType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getOutletsNumber() {
        return outletsNumber;
    }

    public void setOutletsNumber(String outletsNumber) {
        this.outletsNumber = outletsNumber == null ? null : outletsNumber.trim();
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr == null ? null : dateStr.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }
}