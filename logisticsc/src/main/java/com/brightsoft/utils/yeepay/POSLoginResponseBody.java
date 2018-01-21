package com.brightsoft.utils.yeepay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class POSLoginResponseBody extends POSBody{
	
	private String Employee_ID;//登录号
	private String Employee_Name;//员工名称
	private String Company_Code;//公司编码
	private String Company_Name;//公司编码
	public String getCompany_Name() {
		return Company_Name;
	}

	@XmlElement(name = "Company_Name")
	public void setCompany_Name(String company_Name) {
		Company_Name = company_Name;
	}
	private String Company_Tel;//单位电话
	private String Company_Addr;//单位地址
	
	public String getEmployee_ID() {
		return Employee_ID;
	}
	
	@XmlElement(name = "Employee_ID")
	public void setEmployee_ID(String employee_ID) {
		Employee_ID = employee_ID;
	}
	public String getEmployee_Name() {
		return Employee_Name;
	}
	
	@XmlElement(name = "Employee_Name")
	public void setEmployee_Name(String employee_Name) {
		Employee_Name = employee_Name;
	}
	public String getCompany_Code() {
		return Company_Code;
	}
	
	@XmlElement(name = "Company_Code")
	public void setCompany_Code(String company_Code) {
		Company_Code = company_Code;
	}
	public String getCompany_Tel() {
		return Company_Tel;
	}
	
	@XmlElement(name = "Company_Tel")
	public void setCompany_Tel(String company_Tel) {
		Company_Tel = company_Tel;
	}
	public String getCompany_Addr() {
		return Company_Addr;
	}
	
	@XmlElement(name = "Company_Addr")
	public void setCompany_Addr(String company_Addr) {
		Company_Addr = company_Addr;
	}
}
