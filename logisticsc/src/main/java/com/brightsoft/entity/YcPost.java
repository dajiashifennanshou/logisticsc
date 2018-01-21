package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;


/** YcPost
	ID    BIGINT(20)
	POSTNAME    VARCHAR(50)
	POSTDESC    VARCHAR(50)
	POSTLEVEL    INT(11)
	BELONGLEVEL    INT(11)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcPost implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String postName;
	private String postDesc;
	private Integer postLevel;
	private Integer belongLevel;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setPostName(String postName){
		this.postName=postName;
	}
	public String getPostName(){
		return postName;
	}
	public void setPostDesc(String postDesc){
		this.postDesc=postDesc;
	}
	public String getPostDesc(){
		return postDesc;
	}
	public void setPostLevel(Integer postLevel){
		this.postLevel=postLevel;
	}
	public Integer getPostLevel(){
		return postLevel;
	}
	public void setBelongLevel(Integer belongLevel){
		this.belongLevel=belongLevel;
	}
	public Integer getBelongLevel(){
		return belongLevel;
	}
	public void setCreateTime(String createTime){
		this.createTime=createTime;
	}
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateUser(String createUser){
		this.createUser=createUser;
	}
	public String getCreateUser(){
		return createUser;
	}
	public void setUpdateTime(String updateTime){
		this.updateTime=updateTime;
	}
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateUser(String updateUser){
		this.updateUser=updateUser;
	}
	public String getUpdateUser(){
		return updateUser;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
}

