package com.yc.Service;

import java.math.BigInteger;
import java.util.List;

import com.yc.Entity.PlatformDeliverGoods;
import com.yc.Entity.TmsLineInfo;
import com.yc.Tool.ISqlServer;

public interface TmsLineInfoService extends ISqlServer<TmsLineInfo> {

	/**
	 * 集合查询
	 * @Author:luojing
	 * @2016年7月27日 下午6:01:28
	 */
	public List<TmsLineInfo> getListLineInfo(TmsLineInfo line);
	
	
	/**
	 * 集合查询(根据公司ID查询)
	 * @Author:luojing
	 * @2016年7月27日 下午6:01:28
	 */
	public List<TmsLineInfo> getListLineInfoByComId1(BigInteger cId,String province,String city);
	
	/**
	 * 查询线路
	 * @Author:luojing
	 * @2016年8月4日 下午2:44:32
	 */
	public TmsLineInfo getLineInfo(TmsLineInfo line);
	
	/**
	 * 集合查询(根据公司ID查询)
	 * @Author:luojing
	 * @2016年7月27日 下午6:01:28
	 */
	public List<TmsLineInfo> getListLineInfoByComId2(BigInteger cId);
	
	/**
	 * 集合查询(根据公司ID查询)
	 * @Author:luojing
	 * @2016年7月27日 下午6:01:28
	 */
	public List<PlatformDeliverGoods> getListLineInfoByComId3(BigInteger cId);
}
