package com.brightsoft.utils.yeepay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.brightsoft.utils.DateTools;
@XmlRootElement
public class POSResponseHealer extends POSHealer{
	
	public POSResponseHealer(){
		srcSysID = "yeepay";
		dstSysID = "xslwl56";
	}

	private String resultCode;
	
	private String resultMsg;
	
	private String respTime = DateTools.getCurrentDateStr();
	
	private String Result_Code;
	private String Result_Msg;

	public String getResult_Code() {
		return Result_Code;
	}

	@XmlElement(name = "Result_Code")
	public void setResult_Code(String result_Code) {
		Result_Code = result_Code;
	}

	public String getResult_Msg() {
		return Result_Msg;
	}
	
	@XmlElement(name = "Result_Msg")
	public void setResult_Msg(String result_Msg) {
		Result_Msg = result_Msg;
	}

	public String getResultCode() {
		return resultCode;
	}

	@XmlElement(name = "ResultCode")
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	@XmlElement(name = "ResultMsg")
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getRespTime() {
		return respTime;
	}

	@XmlElement(name = "RespTime")
	public void setRespTime(String respTime) {
		this.respTime = respTime;
	}


	
}
