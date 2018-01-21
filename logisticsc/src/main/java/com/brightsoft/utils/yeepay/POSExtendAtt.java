package com.brightsoft.utils.yeepay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class POSExtendAtt {
	
	private String Employee_ID;//登录号

	public String getEmployee_ID() {
		return Employee_ID;
	}

	@XmlElement(name = "Employee_ID")
	public void setEmployee_ID(String employee_ID) {
		Employee_ID = employee_ID;
	}

	private POSLoginResponseBody posLoginResponseBody;
	private POSOrderResponseBody posOrderResponseBody;

	public POSOrderResponseBody getPosOrderResponseBody() {
		return posOrderResponseBody;
	}

	@XmlElement(name = "Item",type=POSOrderResponseBody.class)
	public void setPosOrderResponseBody(POSOrderResponseBody posOrderResponseBody) {
		this.posOrderResponseBody = posOrderResponseBody;
	}

	public POSLoginResponseBody getPosLoginResponseBody() {
		return posLoginResponseBody;
	}

	@XmlElement(name = "ExtendAtt",type=POSLoginResponseBody.class)
	public void setPosLoginResponseBody(POSLoginResponseBody posLoginResponseBody) {
		this.posLoginResponseBody = posLoginResponseBody;
	}
}
