package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;
public class PlatformStoreRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private BigInteger line_id;
	private BigInteger user_id;
	private Integer state;
	private String collection_time;
	
	private TmsLineInfo line;//线路信息
	/***************订单条数***************/
    private Integer countOrder;
    /***************订单评价********************/
    private Integer countOrderEvaluation;
    private Integer isCollect;
	public PlatformStoreRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlatformStoreRecord(BigInteger line_id, BigInteger user_id) {
		super();
		this.line_id = line_id;
		this.user_id = user_id;
	}
	public TmsLineInfo getLine() {
		return line;
	}
	public void setLine(TmsLineInfo line) {
		this.line = line;
	}
	public Integer getCountOrder() {
		return countOrder;
	}
	public void setCountOrder(Integer countOrder) {
		this.countOrder = countOrder;
	}
	public Integer getCountOrderEvaluation() {
		return countOrderEvaluation;
	}
	public void setCountOrderEvaluation(Integer countOrderEvaluation) {
		this.countOrderEvaluation = countOrderEvaluation;
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setLine_id(BigInteger line_id){
		this.line_id=line_id;
	}
	public BigInteger getLine_id(){
		return line_id;
	}
	public void setUser_id(BigInteger user_id){
		this.user_id=user_id;
	}
	public BigInteger getUser_id(){
		return user_id;
	}
	public void setState(Integer state){
		this.state=state;
	}
	public Integer getState(){
		return state;
	}
	public void setCollection_time(String collection_time){
		this.collection_time=collection_time;
	}
	public String getCollection_time(){
		return collection_time;
	}
	public Integer getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}
}

