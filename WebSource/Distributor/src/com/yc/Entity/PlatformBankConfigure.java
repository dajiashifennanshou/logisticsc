package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;

/** LcPlatformBankConfigure
	ID    BIGINT(11)
	MINSETTLEAMOUNT    VARCHAR(20)
	RISKRESERVEDAY    VARCHAR(4)
	MANUALSETTLE    VARCHAR(1)
	TIME    DATETIME(19)
	USERNAME    VARCHAR(20)
*/
public class PlatformBankConfigure implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String minsettleamount;
	private String riskreserveday;
	private String manualsettle;
	private String time;
	private String username;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setMinsettleamount(String minsettleamount){
		this.minsettleamount=minsettleamount;
	}
	public String getMinsettleamount(){
		return minsettleamount;
	}
	public void setRiskreserveday(String riskreserveday){
		this.riskreserveday=riskreserveday;
	}
	public String getRiskreserveday(){
		return riskreserveday;
	}
	public void setManualsettle(String manualsettle){
		this.manualsettle=manualsettle;
	}
	public String getManualsettle(){
		return manualsettle;
	}
	public void setTime(String time){
		this.time=time;
	}
	public String getTime(){
		return time;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return username;
	}
}

