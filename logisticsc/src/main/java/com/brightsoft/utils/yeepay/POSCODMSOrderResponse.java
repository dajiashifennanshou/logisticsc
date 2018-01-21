package com.brightsoft.utils.yeepay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "COD-MS")
@XmlType(propOrder = {  "requestHead", "responseHead","posLoginRequestBody","posOrderRequestBody","posPayRequestBody","posPayResponseBody","posSignRequestBody","posSignResponseBody"}) 
public class POSCODMSOrderResponse {

	//private POSHealer sessionHead;
	
	private POSRequestHealer requestHead;
	
	private POSResponseHealer responseHead;
	
	//private POSBody sessionBody;
	
	private POSLoginRequestBody posLoginRequestBody;
	private POSOrderRequestBody posOrderRequestBody;
	private POSPayRequestBody posPayRequestBody;
	private POSPayResponseBody posPayResponseBody;
	private POSSignRequestBody posSignRequestBody;
	private POSSignResponseBody posSignResponseBody;
	
	public POSSignRequestBody getPosSignRequestBody() {
		return posSignRequestBody;
	}

	@XmlElement(name = "SessionBody")
	public void setPosSignRequestBody(POSSignRequestBody posSignRequestBody) {
		this.posSignRequestBody = posSignRequestBody;
	}

	public POSSignResponseBody getPosSignResponseBody() {
		return posSignResponseBody;
	}

	@XmlElement(name = "SessionBody")
	public void setPosSignResponseBody(POSSignResponseBody posSignResponseBody) {
		this.posSignResponseBody = posSignResponseBody;
	}

	public POSPayRequestBody getPosPayRequestBody() {
		return posPayRequestBody;
	}

	@XmlElement(name = "SessionBody")
	public void setPosPayRequestBody(POSPayRequestBody posPayRequestBody) {
		this.posPayRequestBody = posPayRequestBody;
	}
	
	public POSPayResponseBody getPosPayResponseBody() {
		return posPayResponseBody;
	}

	@XmlElement(name = "SessionBody")
	public void setPosPayResponseBody(POSPayResponseBody posPayResponseBody) {
		this.posPayResponseBody = posPayResponseBody;
	}

	public POSOrderRequestBody getPosOrderRequestBody() {
		return posOrderRequestBody;
	}

	@XmlElement(name = "SessionBody")
	public void setPosOrderRequestBody(POSOrderRequestBody posOrderRequestBody) {
		this.posOrderRequestBody = posOrderRequestBody;
	}

	public POSLoginRequestBody getPosLoginRequestBody() {
		return posLoginRequestBody;
	}

	@XmlElement(name = "SessionBody")
	public void setPosLoginRequestBody(POSLoginRequestBody posLoginRequestBody) {
		this.posLoginRequestBody = posLoginRequestBody;
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
