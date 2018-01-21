package com.yc.Dao;

import java.util.List;

import com.yc.Entity.PlatformBankName;
import com.yc.Tool.ISqlDao;
/**
 * 查询开户行
 * @Author:luojing
 * @2016年7月19日 下午7:32:09
 */
public interface PlatformBankNameDao extends ISqlDao<PlatformBankName> {
	
	public List<PlatformBankName> selectProvinceName();
	
	public List<PlatformBankName> selectCityName(PlatformBankName pfb);
	
	public List<PlatformBankName> selectHeadName(PlatformBankName pfb);
	
	public List<PlatformBankName> selectBranchName(PlatformBankName pfb);
}
