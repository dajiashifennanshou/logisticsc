package com.brightsoft.utils.yc;

public class OutAddWindow {

	/**
	 * 返回码 1：成功，0：失败
	 */
	private Integer code;
	/**
	 * 返回信息
	 */
	private String msg;
	/**
	 * 将要关闭的窗口,即当前添加窗口的名称
	 */
	private String cw;
	/**
	 * 要前往的窗口
	 */
	private String lw;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}
	private Integer total;
	private Integer records;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCw() {
		return cw;
	}
	public void setCw(String cw) {
		this.cw = cw;
	}
	public String getLw() {
		return lw;
	}
	public void setLw(String lw) {
		this.lw = lw;
	}

	
	
}
