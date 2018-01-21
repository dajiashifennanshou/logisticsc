package com.brightsoft.common.enums;

/**
 * 付款方式
 * @author yangshoubao
 *
 */
public enum PayModeEnum {

	IMMEDIATELY_PAY("现付", 0),
	ARRIVE_PAY("到付", 1),
	/*ADVANCE_PAY("预付",2),*/
	BACK_PAY("回付",3),
	MONTH_SETTLEMENT("月结",4);
	/*DEDUCTION_CARGO_MONEY("扣货款",5),*/
	/*MULTIPLE_PAY("多笔付",6);*/
	
	private String name;
	
	private Integer value;
	
	PayModeEnum(String name, Integer value){
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}
	
	
}
