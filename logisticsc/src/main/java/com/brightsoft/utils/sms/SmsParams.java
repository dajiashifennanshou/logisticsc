package com.brightsoft.utils.sms;

public class SmsParams {

	/** 订单号 */
	private String orderNumber;
	/** 运单号 */
	private String waybillNumber;
	/** 公司名称 */
	private String company;
	/** 网点名称 */
	private String outletsName;
	/** 订单状态 */
	private String orderStatus;
	/** 车牌号 */
	private String carNumber;
	/** 签收人 */
	private String signMan;
	/** 联系人电话 */
	private String linkPhone;
	/** 确认运费 */
	private Double freight;
	
	public Double getFreight() {
		return freight;
	}
	public void setFreight(Double freight) {
		this.freight = freight;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getWaybillNumber() {
		return waybillNumber;
	}
	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getOutletsName() {
		return outletsName;
	}
	public void setOutletsName(String outletsName) {
		this.outletsName = outletsName;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getSignMan() {
		return signMan;
	}
	public void setSignMan(String signMan) {
		this.signMan = signMan;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
}

