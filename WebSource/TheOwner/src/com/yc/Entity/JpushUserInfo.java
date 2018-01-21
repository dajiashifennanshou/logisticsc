package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;

/** JpushUserInfo
	ID    BIGINT(20)
	USERID    BIGINT(20)
	PHONE    VARCHAR(30)
	KEY    VARCHAR(50)
*/
public class JpushUserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private BigInteger userId;
	private String phone;
	private String regisId;
	private Integer type;

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setUserId(BigInteger userId){
		this.userId=userId;
	}
	public BigInteger getUserId(){
		return userId;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setRegisId(String regisId){
		this.regisId=regisId;
	}
	public String getRegisId(){
		return regisId;
	}
}

