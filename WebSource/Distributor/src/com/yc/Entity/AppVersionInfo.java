package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;

/** AppVersionInfo
	ID    BIGINT(20)
	VERSIONCODE    INT(11)
	VERSIONNAME    VARCHAR(50)
	RELEASETIME    DATETIME(19)
	FILESIZE    VARCHAR(50)
	LATESTMD5    VARCHAR(100)
	CHANGELOG    VARCHAR(200)
	FILEURI    VARCHAR(300)
*/
public class AppVersionInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private Integer versionCode;
	private String versionName;
	private String releaseTime;
	private String fileSize;
	private String latestMd5;
	private String changeLog;
	private String fileUri;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setVersionCode(Integer versionCode){
		this.versionCode=versionCode;
	}
	public Integer getVersionCode(){
		return versionCode;
	}
	public void setVersionName(String versionName){
		this.versionName=versionName;
	}
	public String getVersionName(){
		return versionName;
	}
	public void setReleaseTime(String releaseTime){
		this.releaseTime=releaseTime;
	}
	public String getReleaseTime(){
		return releaseTime;
	}
	public void setFileSize(String fileSize){
		this.fileSize=fileSize;
	}
	public String getFileSize(){
		return fileSize;
	}
	public void setLatestMd5(String latestMd5){
		this.latestMd5=latestMd5;
	}
	public String getLatestMd5(){
		return latestMd5;
	}
	public void setChangeLog(String changeLog){
		this.changeLog=changeLog;
	}
	public String getChangeLog(){
		return changeLog;
	}
	public void setFileUri(String fileUri){
		this.fileUri=fileUri;
	}
	public String getFileUri(){
		return fileUri;
	}
}

