package com.brightsoft.utils.yeepay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "COD-MS")
@XmlType(propOrder = {"responseHead","posLoginRequestBody","posOrderRequestBody","posPayRequestBody","posPayResponseBody","posSignRequestBody","posSignResponseBody"}) 
public class POSCODMSLoginResponse {

	private POSResponseHealer responseHead;

	private POSExtendAtt posExtendAtt;


	public POSExtendAtt getPosExtendAtt() {
		return posExtendAtt;
	}

	@XmlElement(name = "SessionBody",type=POSExtendAtt.class)
	public void setPosExtendAtt(POSExtendAtt posExtendAtt) {
		this.posExtendAtt = posExtendAtt;
	}


	public POSResponseHealer getResponseHead() {
		return responseHead;
	}

	@XmlElement(name = "SessionHead",type=POSResponseHealer.class)
	public void setResponseHead(POSResponseHealer responseHead) {
		this.responseHead = responseHead;
	}




//	public POSHealer getSessionHead() {
//		return sessionHead;
//	}
//
//	@XmlElement(name = "SessionHead",type=POSHealer.class)
//	public void setSessionHead(POSHealer sessionHead) {
//		this.sessionHead = sessionHead;
//	}


//	public POSBody getSessionBody() {
//		return sessionBody;
//	}
//
//	@XmlElement(name = "SessionBody")
//	public void setSessionBody(POSBody sessionBody) {
//		this.sessionBody = sessionBody;
//	}

}
