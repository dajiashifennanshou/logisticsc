package com.brightsoft.utils.yeepay;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class POSRequestHealer extends POSHealer{
	
	private String reqTime;
	


	public String getReqTime() {
		return reqTime;
	}

	@XmlElement(name = "ReqTime")
	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}

}
