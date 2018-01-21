package com.brightsoft.utils.yc;

public class OutJsonEntity {

	/**
	 * 返回码 1：成功，0：失败
	 */
	private Integer code;
	/**
	 * 返回信息
	 */
	private String msg;
	/**
	 * 返回数据（集合）
	 */
	//数据
	private Object data;
	//数据
	private Object rows;
	private Integer page;
	//总页数
	private Integer total;
	//总记录数
	private Integer records;
	
	/**
	 * 返回list数据集总条数
	 */
	private int results;
	
	
	public int getResults() {
		return results;
	}
	public void setResults(int results) {
		this.results = results;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
