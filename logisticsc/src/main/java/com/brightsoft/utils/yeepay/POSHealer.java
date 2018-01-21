package com.brightsoft.utils.yeepay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
public class POSHealer {

	private String version="1.0";
	
	private String serviceCode;
	
	private String transactionID;
	
	protected String srcSysID = "";
	
	protected String dstSysID;
	
	private String hmac="";

	
	private POSExtendAtt posExtendAtt;


	public POSExtendAtt getPosExtendAtt() {
		return posExtendAtt;
	}

	@XmlElement(name = "ExtendAtt",type=POSExtendAtt.class)
	public void setPosExtendAtt(POSExtendAtt posExtendAtt) {
		this.posExtendAtt = posExtendAtt;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	@XmlElement(name = "ServiceCode")
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getTransactionID() {
		return transactionID;
	}

	@XmlElement(name = "TransactionID")
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getSrcSysID() {
		return srcSysID;
	}

	@XmlElement(name = "SrcSysID")
	public void setSrcSysID(String srcSysID) {
		this.srcSysID = srcSysID;
	}

	public String getDstSysID() {
		return dstSysID;
	}

	@XmlElement(name = "DstSysID")
	public void setDstSysID(String dstSysID) {
		this.dstSysID = dstSysID;
	}

	public String getVersion() {
		return version;
	}

	
	@XmlElement(name = "Version")
	public void setVersion(String version) {
		this.version = version;
	}
	

	public String getHmac() {
		return hmac;
	}

	@XmlElement(name = "HMAC")
	public void setHmac(String hmac) {
		this.hmac = hmac;
	}
}
