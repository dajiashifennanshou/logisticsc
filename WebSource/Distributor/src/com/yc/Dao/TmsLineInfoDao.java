package com.yc.Dao;

import com.yc.Entity.CompanyOutletsLine;
import com.yc.Entity.TmsLineInfo;
import com.yc.Tool.ISqlDao;

public interface TmsLineInfoDao extends ISqlDao<TmsLineInfo> {

	
	/**
	 * 返回公司，网点，线路信息
	 * @Author:luojing
	 * @2016年7月5日 上午11:24:08
	 */
	public CompanyOutletsLine getCompanyOutletsLine(CompanyOutletsLine col);
}
