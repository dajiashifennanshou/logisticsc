package com.yc.Dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.yc.Entity.LogisticsInfo;
import com.yc.Entity.PlatformDeliverGoods;
import com.yc.Entity.PlatformUserCompany;
import com.yc.Tool.ISqlDao;

public interface PlatformUserCompanyDao extends ISqlDao<PlatformUserCompany> {

	/**
	 * 首页初始查询附近的物流商信息	
	 * @Author:luojing
	 * @2016年7月27日 下午5:47:06
	 */
	public List<LogisticsInfo> getListLogisticsInfo(Map<String,Object> map);
	
	/**
	 * 发货人线路查询
	 * @Author:luojing
	 * @2016年7月28日 下午2:47:16
	 */
	public List<PlatformDeliverGoods> getPageLineInfo(Map<String,Object> map);
	
	/**
	 * 查询物流商信息（根据公司名称模糊）
	 * @Author:luojing
	 * @2016年7月29日 上午10:41:22
	 */
	public List<PlatformUserCompany> getPagerCompanyInfo(Map<String,Object> map);
	
	/**
	 * 查询单个物流商信息详情
	 * @Author:luojing
	 * @2016年8月3日 下午5:49:07
	 */
	public PlatformUserCompany getPlatformUserCompany(PlatformUserCompany com);
	
	/**
	 * 查询线路ID查询公司信息
	 * @Author:luojing
	 * @2016年8月3日 下午5:49:07
	 */
	public PlatformUserCompany getCompanyByLineId(BigInteger lineId);
	
	/**
	 * 查询常用物流商
	 * @Author:luojing
	 * @2016年8月3日 下午5:49:07
	 */
	public List<PlatformUserCompany> getCommonCompany(Map<String,Object> map);
}
