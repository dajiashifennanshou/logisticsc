package com.yc.Service;

import com.yc.Entity.CompanyOutletsLine;
import com.yc.Entity.TmsLineInfo;

public interface TmsLineInfoService {

	/**
	 * 查询线路信息
	 * @Author:luojing
	 * @2016年7月5日 上午11:06:32
	 */
	public TmsLineInfo getLineInfo(TmsLineInfo line);
	
	/**
	 * 返回公司，网点，线路信息
	 * @Author:luojing
	 * @2016年7月5日 上午11:24:08
	 */
	public CompanyOutletsLine getCompanyOutletsLine(CompanyOutletsLine col);
	
}
