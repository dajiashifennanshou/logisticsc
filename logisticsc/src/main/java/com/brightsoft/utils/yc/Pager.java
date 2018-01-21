package com.brightsoft.utils.yc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat.Value;

public class Pager<T>
{
  private Integer pageIndex;//当前页
  private Integer pageSize;//每页行数
  private Integer recordCount;//总记录数
  private List<T> objectList;//返回对象
  private String sidx; //排序字段
  private String sord;//排序方式
  private Integer limitMin;
  private Integer limitMax;
  private Integer total;
  
  public Pager(Integer _pageIndex, Integer _pageSize)
  {
    this.pageIndex = _pageIndex<0?1:_pageIndex;
    this.pageSize = _pageSize;
    this.limitMin = Integer.valueOf(0);
    if (this.pageIndex.intValue() != 1)
      this.limitMin = Integer.valueOf((this.pageIndex.intValue() - 1) * this.pageSize.intValue());

    this.limitMax = this.pageSize;
  }
  public Pager(Integer _start, Integer _limit,Integer _pageIndex)
  {
	  this.limitMin=_start;
	  this.limitMax=_limit;
	  this.pageIndex=_pageIndex;
  }
  
  public Pager(Integer _pageIndex, Integer _pageSize,String _sidx,String _sord)
  {
    this.pageIndex = _pageIndex<0?1:_pageIndex;
    this.pageSize = _pageSize;
    this.limitMin = Integer.valueOf(0);
    if (this.pageIndex.intValue() != 1)
      this.limitMin = Integer.valueOf((this.pageIndex.intValue() - 1) * this.pageSize.intValue());
    this.limitMax = this.pageSize;
    this.sidx = _sidx;
    this.sord = _sord;
  }

  public Integer getPageIndex() {
    return this.pageIndex;
  }
  
  /**
   * 获取分页条件map，作为mapping的参数
   * Author:FENG
   * 2016年5月13日
   * @return
   */
  public Map<String,Object> getElestMap(T t){
	  Map<String,Object> map=new HashMap<String,Object>();
	  map.put("limitMax", limitMax);
	  map.put("limitMin", limitMin);
	  map.put("sidx", sidx);
	  map.put("sord", sord);
	  Class<? extends Object> c=t.getClass();
	  try {
		  for(Field f:c.getDeclaredFields()){
			  f.setAccessible(true);
			  Object value=f.get(t);
			  if(StrUtil.VObject(value)){
				  map.put(f.getName(), String.valueOf(value));
			  }
		  }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return map;
  }
  
  public void setPageindex(Integer pageIndex) {
    this.pageIndex = pageIndex;
  }

  public Integer getPageSize() {
    return this.pageSize;
  }

  public void setpageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getRecordCount() {
    return this.recordCount;
  }

  public void setRecordCount(Integer recordCount) {
    this.recordCount = recordCount;
  }

  public List<T> getObjectList() {
    return this.objectList;
  }

  public void setObjectList(List<T> objectList) {
    this.objectList = objectList;
  }

  public Integer getLimitMin() {
    return this.limitMin;
  }

  public void setLimitMin(Integer limitMin) {
    this.limitMin = limitMin;
  }

  public Integer getLimitMax() {
    return this.limitMax;
  }

  public void setLimitMax(Integer limitMax) {
    this.limitMax = limitMax;
  }

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}
}