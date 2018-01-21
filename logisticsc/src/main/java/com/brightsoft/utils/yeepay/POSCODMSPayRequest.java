package com.brightsoft.utils.yeepay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "COD-MS")
@XmlType(propOrder = {  "requestHead", "posPayRequestBody"}) 
public class POSCODMSPayRequest {

	//private POSHealer sessionHead;
	
	private POSRequestHealer requestHead;
	
	private POSPayRequestBody posPayRequestBody;

	public POSPayRequestBody getPosPayRequestBody() {
		return posPayRequestBody;
	}

	@XmlElement(name = "SessionBody")
	public void setPosPayRequestBody(POSPayRequestBody posPayRequestBody) {
		this.posPayRequestBody = posPayRequestBody;
	}
	
	

	
//	private POSExtendAtt posExtendAtt;
//
//
//	public POSExtendAtt getPosExtendAtt() {
//		return posExtendAtt;
//	}
//
//	@XmlElement(name = "SessionBody",type=POSExtendAtt.class)
//	public void setPosExtendAtt(POSExtendAtt posExtendAtt) {
//		this.posExtendAtt = posExtendAtt;
//	}

	public POSRequestHealer getRequestHead() {
		return requestHead;
	}

	@XmlElement(name = "SessionHead")
	public void setRequestHead(POSRequestHealer requestHead) {
		this.requestHead = requestHead;
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
