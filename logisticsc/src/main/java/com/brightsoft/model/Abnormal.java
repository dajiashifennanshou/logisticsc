package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Abnormal implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String wayBillNumber;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date abnormalDate;

    private String foundPerson;

    private String abnormalNode;

    private String abnormalType;

    private String content;

    private Integer cargoDiffNumber;

    private Integer cargoDamageNumber;

    private String dutySite;

    private String operationPerson;
    
    private AbnormalHandle abnormalHandle;
    
    private WayBillOrder wayBillOrder;
    
    private String cargoName;
    
    private String cargoPackage;
    
    private Integer cargoPiece;
    
    private String abImgPath;
    
    private Integer abnormalStatus;
    
    /***********附加字段*****************/
    private String abnormalDateStr;
    
    /*******运单信息*****************/
    private String orderNumber;

    private String startOutletsName;

    private String targetOutletsName;

    private String consignor;

    private String consignorMobile;

    private String consignee;

    private String consigneeMobile;
    
    private Double agencyFund;
    
    private Double advanceCost;
    
    private Double total;
    
    private Integer payMethod;
    
    private Date wayBillOrderTime;
    
    /*******异常处理*****************/
    private Date handleDate;

    private Double compensationMoney;

    private String customerOpinion;

    private String replyOpinion;

    private String handleResult;

    private String preventionMeasures;
    /***********附加信息****************/
    private Double money;
    
    private String abnormalTypeName;
    
    private String abnormalNodeName;
    
    private String payMethodName;
    
    private String dutySiteName;

	public String getDutySiteName() {
		return dutySiteName;
	}

	public void setDutySiteName(String dutySiteName) {
		this.dutySiteName = dutySiteName;
	}

	public String getPayMethodName() {
		return payMethodName;
	}

	public void setPayMethodName(String payMethodName) {
		this.payMethodName = payMethodName;
	}

	public Double getAgencyFund() {
		return agencyFund;
	}

	public void setAgencyFund(Double agencyFund) {
		this.agencyFund = agencyFund;
	}

	public Double getAdvanceCost() {
		return advanceCost;
	}

	public void setAdvanceCost(Double advanceCost) {
		this.advanceCost = advanceCost;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	public String getAbnormalTypeName() {
		return abnormalTypeName;
	}

	public void setAbnormalTypeName(String abnormalTypeName) {
		this.abnormalTypeName = abnormalTypeName;
	}

	public String getAbnormalNodeName() {
		return abnormalNodeName;
	}

	public void setAbnormalNodeName(String abnormalNodeName) {
		this.abnormalNodeName = abnormalNodeName;
	}

	public WayBillOrder getWayBillOrder() {
		return wayBillOrder;
	}

	public void setWayBillOrder(WayBillOrder wayBillOrder) {
		this.wayBillOrder = wayBillOrder;
	}

	public AbnormalHandle getAbnormalHandle() {
		return abnormalHandle;
	}

	public void setAbnormalHandle(AbnormalHandle abnormalHandle) {
		this.abnormalHandle = abnormalHandle;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWayBillNumber() {
		return wayBillNumber;
	}

	public void setWayBillNumber(String wayBillNumber) {
		this.wayBillNumber = wayBillNumber;
	}

	public Date getAbnormalDate() {
		return abnormalDate;
	}

	public void setAbnormalDate(Date abnormalDate) {
        this.abnormalDate = abnormalDate;
    }

    public String getFoundPerson() {
        return foundPerson;
    }

    public void setFoundPerson(String foundPerson) {
        this.foundPerson = foundPerson == null ? null : foundPerson.trim();
    }

    public String getAbnormalNode() {
        return abnormalNode;
    }

    public void setAbnormalNode(String abnormalNode) {
        this.abnormalNode = abnormalNode == null ? null : abnormalNode.trim();
    }

    public String getAbnormalType() {
        return abnormalType;
    }

    public void setAbnormalType(String abnormalType) {
        this.abnormalType = abnormalType == null ? null : abnormalType.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getCargoDiffNumber() {
        return cargoDiffNumber;
    }

    public void setCargoDiffNumber(Integer cargoDiffNumber) {
        this.cargoDiffNumber = cargoDiffNumber;
    }

    public Integer getCargoDamageNumber() {
        return cargoDamageNumber;
    }

    public void setCargoDamageNumber(Integer cargoDamageNumber) {
        this.cargoDamageNumber = cargoDamageNumber;
    }

    public String getDutySite() {
        return dutySite;
    }

    public void setDutySite(String dutySite) {
        this.dutySite = dutySite == null ? null : dutySite.trim();
    }

    public String getOperationPerson() {
        return operationPerson;
    }

    public void setOperationPerson(String operationPerson) {
        this.operationPerson = operationPerson == null ? null : operationPerson.trim();
    }

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getCargoPackage() {
		return cargoPackage;
	}

	public void setCargoPackage(String cargoPackage) {
		this.cargoPackage = cargoPackage;
	}

	public Integer getCargoPiece() {
		return cargoPiece;
	}

	public void setCargoPiece(Integer cargoPiece) {
		this.cargoPiece = cargoPiece;
	}

	public String getAbImgPath() {
		return abImgPath;
	}

	public void setAbImgPath(String abImgPath) {
		this.abImgPath = abImgPath;
	}

	public Integer getAbnormalStatus() {
		return abnormalStatus;
	}

	public void setAbnormalStatus(Integer abnormalStatus) {
		this.abnormalStatus = abnormalStatus;
	}

	public String getAbnormalDateStr() {
		return abnormalDateStr;
	}

	public void setAbnormalDateStr(String abnormalDateStr) {
		this.abnormalDateStr = abnormalDateStr;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getConsignor() {
		return consignor;
	}

	public void setConsignor(String consignor) {
		this.consignor = consignor;
	}

	public String getConsignorMobile() {
		return consignorMobile;
	}

	public void setConsignorMobile(String consignorMobile) {
		this.consignorMobile = consignorMobile;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	public Double getCompensationMoney() {
		return compensationMoney;
	}

	public void setCompensationMoney(Double compensationMoney) {
		this.compensationMoney = compensationMoney;
	}

	public String getCustomerOpinion() {
		return customerOpinion;
	}

	public void setCustomerOpinion(String customerOpinion) {
		this.customerOpinion = customerOpinion;
	}

	public String getReplyOpinion() {
		return replyOpinion;
	}

	public void setReplyOpinion(String replyOpinion) {
		this.replyOpinion = replyOpinion;
	}

	public String getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}
	
	public String getStartOutletsName() {
		return startOutletsName;
	}

	public void setStartOutletsName(String startOutletsName) {
		this.startOutletsName = startOutletsName;
	}

	public String getTargetOutletsName() {
		return targetOutletsName;
	}

	public void setTargetOutletsName(String targetOutletsName) {
		this.targetOutletsName = targetOutletsName;
	}

	public String getPreventionMeasures() {
		return preventionMeasures;
	}

	public void setPreventionMeasures(String preventionMeasures) {
		this.preventionMeasures = preventionMeasures;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getWayBillOrderTime() {
		return wayBillOrderTime;
	}

	public void setWayBillOrderTime(Date wayBillOrderTime) {
		this.wayBillOrderTime = wayBillOrderTime;
	}

}