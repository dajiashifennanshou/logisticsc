package com.brightsoft.utils;

import java.util.List;
import java.util.Map;

/**
 * 结果返回集
 */
public class Result {
	/**
	 * 返回结果状态.true表示成功,false表示失败
	 */
	private boolean result;

	/**
	 * 返回处理消息
	 */
	private String msg;

	/**
	 * 返回list数据集
	 */
	private List<?> rows;

	/**
	 * 返回list数据集总条数
	 */
	private int results;

	/**
	 * 返回一个普通对象
	 */
	private Object data;

	/**
	 * 返回一个Map对象
	 * 
	 */
	private Map<Object, Object> summary;
	
	public Result(List<?> rows, int results) {
		this.rows = rows;
		this.results = results;
	}

	public Result() {
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Map<Object, Object> getSummary() {
		return summary;
	}

	public void setSummary(Map<Object, Object> summary) {
		this.summary = summary;
	}

}
