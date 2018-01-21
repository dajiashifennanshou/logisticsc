package com.yc.Service;

import java.math.BigInteger;

import com.yc.Entity.LogisticsInfo;
import com.yc.Entity.PlatformDeliverGoods;
import com.yc.Entity.PlatformUserCompany;
import com.yc.Tool.Pager;

public interface PlatformUserCompanyService {

	/**
	 * 首页初始查询附近的物流商信息	
	 * @Author:luojing
	 * @2016年7月27日 下午5:47:06
	 */
	public Pager<LogisticsInfo> getPageLogisticsInfo(Pager<LogisticsInfo> pager,LogisticsInfo log);
	
	/**
	 * 发货人线路查询
	 * @Author:luojing
	 * @2016年7月28日 下午2:41:29
	 */
	public Pager<PlatformDeliverGoods> getPageLineInfo(Pager<PlatformDeliverGoods> pager,PlatformDeliverGoods pdg);
	
	/**
	 * 查询物流商信息（根据公司名称模糊）
	 * @Author:luojing
	 * @2016年7月29日 上午10:41:22
	 */
	public Pager<PlatformUserCompany> getPagerCompanyInfo(Pager<PlatformUserCompany> pager,PlatformUserCompany com);
	
	/**
	 * 查询单个物流商信息详情
	 * @Author:luojing
	 * @2016年8月3日 下午5:49:07
	 */
	public PlatformUserCompany getPlatformUserCompany(PlatformUserCompany com,BigInteger userId);
	
	/**
	 * 查询常用物流商
	 * @Author:luojing
	 * @2016年8月3日 下午5:49:07
	 */
	public Pager<PlatformUserCompany> getCommonCompany(Pager<PlatformUserCompany> pager,BigInteger userId);
	
	/**
	 * 查询公司信息
	 * @Author:luojing
	 * @2016年8月18日 上午11:03:36
	 */
	public PlatformUserCompany getUserCompany(PlatformUserCompany com);
	
}
