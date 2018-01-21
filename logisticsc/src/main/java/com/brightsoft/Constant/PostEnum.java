package com.brightsoft.Constant;
/**
 *Good Luck
 *
*/
public enum PostEnum {

	YC_POST_PS("配送员","ps"),
	YC_POST_AZ("安装工","az"),
	YC_POST_SM("管理员","sm"),
	YC_POST_DS("董事","ds"),
	YC_POST_JL("经理","jl"),
	YC_POST_YY("运营","yy"),
	YC_POST_CS("测试","cs");
	private String name;
	private String value;
	
	PostEnum(String name, String value){
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
