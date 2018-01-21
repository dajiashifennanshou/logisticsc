package com.brightsoft.entity; 

import java.io.Serializable;

/** YcRuleInfo
	ID    INT(11)
	RTYPE    INT(2)
	RVALUE    INT UNSIGNED(5)
*/
public class YcRuleInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer rtype;
	private String rvalue;

	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public void setRtype(Integer rtype){
		this.rtype=rtype;
	}
	public Integer getRtype(){
		return rtype;
	}
	public void setRvalue(String rvalue){
		this.rvalue=rvalue;
	}
	public String getRvalue(){
		return rvalue;
	}
}

