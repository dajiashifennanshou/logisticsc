package com.yc.Entity;

import java.io.Serializable;
import java.util.List;
/**
 * 返回入库信息
 * @Author:luojing
 * @2016年7月12日 下午3:54:25
 */
public class TempGoodsInfo implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String wayBillNo;
	private String deliveryNo;
	private String phone;
	private String createTime;
	private Integer waybillSource;
	private List<YcGoods> goods;
	
	
	public Integer getWaybillSource() {
		return waybillSource;
	}
	public void setWaybillSource(Integer waybillSource) {
		this.waybillSource = waybillSource;
	}
	public String getWayBillNo() {
		return wayBillNo;
	}
	public void setWayBillNo(String wayBillNo) {
		this.wayBillNo = wayBillNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<YcGoods> getGoods() {
		return goods;
	}
	public void setGoods(List<YcGoods> goods) {
		this.goods = goods;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDeliveryNo() {
		return deliveryNo;
	}
	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
}
