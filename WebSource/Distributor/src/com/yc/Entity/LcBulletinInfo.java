package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;
public class LcBulletinInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String title;
	private String content;
	private String url;
	private String create_time;
	private String release_time;
	private Integer status;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setUrl(String url){
		this.url=url;
	}
	public String getUrl(){
		return url;
	}
	public void setCreate_time(String create_time){
		this.create_time=create_time;
	}
	public String getCreate_time(){
		return create_time;
	}
	public void setRelease_time(String release_time){
		this.release_time=release_time;
	}
	public String getRelease_time(){
		return release_time;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
}

