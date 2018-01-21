package com.yc.Entity; 

import java.io.Serializable;
import java.math.*;

import java.math.BigInteger;


/** LcPlatformOrderAdditionalServer
	ID    BIGINT(20)
	IS_RECEIPT    INT(11)
	IS_IM_EX_STORE    INT(11)
	IS_LOAD_CARGO    INT(11)
	LOAD_CARGO_FLOOR    INT(11)
	LOAD_CARGO_IS_ELEVATOR    INT(11)
	IS_UNLOAD_CARGO    INT(11)
	UNLOAD_CARGO_FLOOR    INT(11)
	UNLOAD_CARGO_IS_ELEVATOR    INT(11)
	IS_COLLECTION_DELIVERY    INT(11)
	COLLECTION_DELIVERY_MONEY    DOUBLE(22,31)
	ORDER_ID    BIGINT(20)
*/
public class LcPlatformOrderAdditionalServer implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private Integer is_receipt;
	private Integer is_im_ex_store;
	private Integer is_load_cargo;
	private Integer load_cargo_floor;
	private Integer load_cargo_is_elevator;
	private Integer is_unload_cargo;
	private Integer unload_cargo_floor;
	private Integer unload_cargo_is_elevator;
	private Integer is_collection_delivery;
	private Double collection_delivery_money;
	private BigInteger order_id;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setIs_receipt(Integer is_receipt){
		this.is_receipt=is_receipt;
	}
	public Integer getIs_receipt(){
		return is_receipt;
	}
	public void setIs_im_ex_store(Integer is_im_ex_store){
		this.is_im_ex_store=is_im_ex_store;
	}
	public Integer getIs_im_ex_store(){
		return is_im_ex_store;
	}
	public void setIs_load_cargo(Integer is_load_cargo){
		this.is_load_cargo=is_load_cargo;
	}
	public Integer getIs_load_cargo(){
		return is_load_cargo;
	}
	public void setLoad_cargo_floor(Integer load_cargo_floor){
		this.load_cargo_floor=load_cargo_floor;
	}
	public Integer getLoad_cargo_floor(){
		return load_cargo_floor;
	}
	public void setLoad_cargo_is_elevator(Integer load_cargo_is_elevator){
		this.load_cargo_is_elevator=load_cargo_is_elevator;
	}
	public Integer getLoad_cargo_is_elevator(){
		return load_cargo_is_elevator;
	}
	public void setIs_unload_cargo(Integer is_unload_cargo){
		this.is_unload_cargo=is_unload_cargo;
	}
	public Integer getIs_unload_cargo(){
		return is_unload_cargo;
	}
	public void setUnload_cargo_floor(Integer unload_cargo_floor){
		this.unload_cargo_floor=unload_cargo_floor;
	}
	public Integer getUnload_cargo_floor(){
		return unload_cargo_floor;
	}
	public void setUnload_cargo_is_elevator(Integer unload_cargo_is_elevator){
		this.unload_cargo_is_elevator=unload_cargo_is_elevator;
	}
	public Integer getUnload_cargo_is_elevator(){
		return unload_cargo_is_elevator;
	}
	public void setIs_collection_delivery(Integer is_collection_delivery){
		this.is_collection_delivery=is_collection_delivery;
	}
	public Integer getIs_collection_delivery(){
		return is_collection_delivery;
	}
	public void setCollection_delivery_money(Double collection_delivery_money){
		this.collection_delivery_money=collection_delivery_money;
	}
	public Double getCollection_delivery_money(){
		return collection_delivery_money;
	}
	public void setOrder_id(BigInteger order_id){
		this.order_id=order_id;
	}
	public BigInteger getOrder_id(){
		return order_id;
	}
}

