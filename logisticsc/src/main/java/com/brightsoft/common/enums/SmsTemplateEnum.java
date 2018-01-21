package com.brightsoft.common.enums;

public enum SmsTemplateEnum {

	ORDER_REC_REF("接单/拒单模板","order_rec_ref"),
	TAKE_CARGO("派车提货短信模板","take_cargo"),
	NEGOTIATING_PRICES("议价短信模板","negotiating_prices"),
	IN_STORAGE("开单入库短信模板","in_storage"),
	OUT_STORAGE("发车出库短信模板","out_storage"),
	EPIBOLY_OUT_STORAGE("外包出库短信模板","epiboly_out_storage"),
	UNLOAD_IN_STORAGE("卸货入库短信模板","unload_in_storage"),
	DELIVERY_OUT_STORAGE("配送出库短信模板","delivery_out_storage"),
	TRANSFER_OUT_STORAGE("中转出库短信模板","transfer_out_storage"),
	SIGN_IN("签收短信模板","sign_in"),
	ABNORMAL_CHECK_IN("异常登记短信模块","abnormal_check_in"),
	ABNORMAL_HANDLER("异常处理短信模块","abnormal_handler");
	
	private String name;
	private String value;
	private SmsTemplateEnum(String name, String value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}

