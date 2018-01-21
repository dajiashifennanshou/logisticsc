package com.brightsoft.utils.yeepay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "COD-MS")
@XmlType(propOrder = {  "requestHead", "posReimburseRequestBody"})
public class POSCODMSReimburseRequest {

	public POSRequestHealer getRequestHead() {
		return requestHead;
	}

	@XmlElement(name = "SessionHead")
	public void setRequestHead(POSRequestHealer requestHead) {
		this.requestHead = requestHead;
	}

	public POSReimburseRequestBody getPosReimburseRequestBody() {
		return posReimburseRequestBody;
	}

	@XmlElement(name = "SessionBody")
	public void setPosReimburseRequestBody(
			POSReimburseRequestBody posReimburseRequestBody) {
		this.posReimburseRequestBody = posReimburseRequestBody;
	}

	private POSRequestHealer requestHead;
	
	private POSReimburseRequestBody posReimburseRequestBody;
}
