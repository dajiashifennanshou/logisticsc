package com.brightsoft.entity; 

import java.io.Serializable;


/** YcGoodsType
	ID    INT(10)
	PARENTSOFT    INT(10)
	SOFTNAME    VARCHAR(50)
	CREATETIME    DATETIME(19)
	CREATENAME    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATENAME    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcGoodsType implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer parentSoft;
	private String softName;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;
	private String branchNo;

	
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public void setParentSoft(Integer parentSoft){
		this.parentSoft=parentSoft;
	}
	public Integer getParentSoft(){
		return parentSoft;
	}
	
	public String getSoftName() {
		return softName;
	}
	public void setSoftName(String softName) {
		this.softName = softName;
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

