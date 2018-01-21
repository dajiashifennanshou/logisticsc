package com.yc.Service;

import java.util.List;

import com.yc.Entity.PlatformBankName;


public interface PlatformBankNameService {
	
	public List<PlatformBankName> selectProvinceName();
	
	public List<PlatformBankName> selectCityName(PlatformBankName pfb);
	
	public List<PlatformBankName> selectHeadName(PlatformBankName pfb);
	
	public List<PlatformBankName> selectBranchName(PlatformBankName pfb);
}
