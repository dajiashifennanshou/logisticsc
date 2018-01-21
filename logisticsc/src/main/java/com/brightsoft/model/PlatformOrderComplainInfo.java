package com.brightsoft.model;

import java.io.Serializable;

/**
 * 投诉详情
 * @author ThinkPad
 *
 */
public class PlatformOrderComplainInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private PlatformOrderMineComplain complain;
	
	private PlatformOrderComlainHandle comlainHandle;

	public PlatformOrderMineComplain getComplain() {
		return complain;
	}

	public void setComplain(PlatformOrderMineComplain complain) {
		this.complain = complain;
	}

	public PlatformOrderComlainHandle getComlainHandle() {
		return comlainHandle;
	}

	public void setComlainHandle(PlatformOrderComlainHandle comlainHandle) {
		this.comlainHandle = comlainHandle;
	}
	
	
}
