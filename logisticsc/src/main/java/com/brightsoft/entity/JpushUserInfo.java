package com.brightsoft.entity; 

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
	
	private String content;//推送内容
	private String platform;//货运交易系统  默认all
	private String target;//目标人群
	private String sendType;//发送类型，0 立即  1定时
	private String sendTime;//定时--存在发送时间
	private Integer timeFinish;//定速推送完成分钟数
	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getTimeFinish() {
		return timeFinish;
	}
	public void setTimeFinish(Integer timeFinish) {
		this.timeFinish = timeFinish;
	}
	public JpushUserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JpushUserInfo(BigInteger userId) {
		super();
		this.userId = userId;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
}

