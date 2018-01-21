package com.brightsoft.utils;
import java.util.HashMap;
import java.util.Map;
 
/**
 * 对分页的基本数据进行一个简单的封装
 * 2015年8月31日 上午10:50:17
 * @author zhouyi
 */
public class Page<T> {
 
	private int limit=10; //条数
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