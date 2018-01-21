package com.brightsoft.utils.yeepay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "COD-MS")
@XmlType(propOrder = {  "requestHead", "posCancelOrderRequestBody"})
public class POSCODMSCancelOrderRequest {

	public POSRequestHealer getRequestHead() {
		return requestHead;
	}

	@XmlElement(name = "SessionHead")
	public void setRequestHead(POSRequestHealer requestHead) {
		this.requestHead = requestHead;
	}


	public POSCancelOrderRequestBody getPosCancelOrderRequestBody() {
		return posCancelOrderRequestBody;
	}

	@XmlElement(name = "SessionBody")
	public void setPosCancelOrderRequestBody(
			POSCancelOrderRequestBody posCancelOrderRequestBody) {
		this.posCancelOrderRequestBody = posCancelOrderRequestBody;
	}


	
	

	private POSRequestHealer requestHead;
	
	private POSCancelOrderRequestBody posCancelOrderRequestBody;
}
