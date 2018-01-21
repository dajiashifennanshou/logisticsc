package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;
public class PlatformDictionary implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String name;
	private String abbr;
	private String type;
	private String level1;
	private String level2;
	private String level3;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setAbbr(String abbr){
		this.abbr=abbr;
	}
	public String getAbbr(){
		return abbr;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return type;
	}
	public void setLevel1(String level1){
		this.level1=level1;
	}
	public String getLevel1(){
		return level1;
	}
	public void setLevel2(String level2){
		this.level2=level2;
	}
	public String getLevel2(){
		return level2;
	}
	public void setLevel3(String level3){
		this.level3=level3;
	}
	public String getLevel3(){
		return level3;
	}
}

