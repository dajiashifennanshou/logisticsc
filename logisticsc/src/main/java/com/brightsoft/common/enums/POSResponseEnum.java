package com.brightsoft.common.enums;

public enum POSResponseEnum {
	ERROR_LOGIN_PASSWORD("10","用户名或密码错误"),
	NO_USER("11","没有该用户"),
	FAIL_XML("4","报文验证失败"),
	SUCCESS("2","成功"),
	RECEIVE_FAIL("3","接收失败");
	
	private String result_code;
	private String result_msg;
	
	POSResponseEnum(String result_code,String result_msg){
		this.result_code = result_code;
		this.result_msg = result_msg;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getResult_msg() {
		return result_msg;
	}

	public void setResult_msg(String result_msg) {
		this.result_msg = result_msg;
	}
}
