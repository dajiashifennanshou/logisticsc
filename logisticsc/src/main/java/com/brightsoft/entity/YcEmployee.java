package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;


/** YcEmployee
	ID    BIGINT(20)
	EMPLOYEENAME    VARCHAR(20)
	GENDER    INT(11)
	AGE    INT(11)
	JOBNUMBER    VARCHAR(30)
	POSITION    VARCHAR(30)
	TERM    INT(11)
	PHONE    VARCHAR(30)
	CARDNUMBER    VARCHAR(30)
	ADDRESS    VARCHAR(50)
	ENTRYTIME    DATETIME(19)
	UPDATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcEmployee implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String employeeName;
	private Integer gender;
	private Integer age;
	private String jobNumber;
	private String position;
	private Integer term;
	private String phone;
	private String branchNo;
	private String cardNumber;
	private String address;
	private String entryTime;
	private String updateTime;
	private String createUser;
	private String updateUser;
	private String remark;
	private Integer useStatus;
	
	private String postName;

	
	
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public Integer getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setEmployeeName(String employeeName){
		this.employeeName=employeeName;
	}
	public String getEmployeeName(){
		return employeeName;
	}
	public void setGender(Integer gender){
		this.gender=gender;
	}
	public Integer getGender(){
		return gender;
	}
	public void setAge(Integer age){
		this.age=age;
	}
	public Integer getAge(){
		return age;
	}
	public void setJobNumber(String jobNumber){
		this.jobNumber=jobNumber;
	}
	public String getJobNumber(){
		return jobNumber;
	}
	public void setPosition(String position){
		this.position=position;
	}
	public String getPosition(){
		return position;
	}
	public void setTerm(Integer term){
		this.term=term;
	}
	public Integer getTerm(){
		return term;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setCardNumber(String cardNumber){
		this.cardNumber=cardNumber;
	}
	public String getCardNumber(){
		return cardNumber;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public String getAddress(){
		return address;
	}
	public void setEntryTime(String entryTime){
		this.entryTime=entryTime;
	}
	public String getEntryTime(){
		return entryTime;
	}
	public void setUpdateTime(String updateTime){
		this.updateTime=updateTime;
	}
	public String getUpdateTime(){
		return updateTime;
	}
	public void setCreateUser(String createUser){
		this.createUser=createUser;
	}
	public String getCreateUser(){
		return createUser;
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
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
}

