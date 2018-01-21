package com.brightsoft.common.enums;

/**
 * 货运交易系统异常状态
 * @author yangshoubao
 *
 */
public enum AbnormalTypeEnum {

	DAMAGE_LIST("货损货差", 0),
	REFUSE_PAY_FREIGHT("拒付运费", 1),
	REFUSE_PAY_CARGO("拒付货款",2),
	FRIGHT_ABNORMAL("运费差异",3),
	UNMANNED_SIGN("无人签收",4),
	SECTION_SIGN("部分签收",5),
	OTHERS("其他",6);
	
	private String name;
	
	private Integer value;
	
	AbnormalTypeEnum(String name, Integer value){
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}
	public static void main(String[] args) {
		System.out.println(AbnormalTypeEnum.FRIGHT_ABNORMAL.getName());
	}
	
}
