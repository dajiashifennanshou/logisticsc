package com.brightsoft.utils;

import java.util.HashMap;
import java.util.Map;

public  class QueryObject {
	
	private int limit=5; //条数
	private int pageIndex=1; //当前页码
	private int start=0; // 开始条数
	
	private Map<String,Object> params = new HashMap<String, Object>();
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public void addParams(String s, Object o) {
		this.params.put(s, o);
	}
}
