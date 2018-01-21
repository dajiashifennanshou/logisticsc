package com.yc.Service.Impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.TmsLineInfoDao;
import com.yc.Entity.PlatformDeliverGoods;
import com.yc.Entity.TmsLineInfo;
import com.yc.Service.TmsLineInfoService;
import com.yc.Tool.Pager;
/**
 * 线路信息
 * @Author:luojing
 * @2016年7月27日 下午5:56:26
 */
@Service
public class TmsLineInfoServiceImpl implements TmsLineInfoService {

	@Autowired
	private TmsLineInfoDao TmsLineInfo;
	@Override
	public TmsLineInfo getSingleInfo(TmsLineInfo t) {
		// TODO Auto-generated method stub
		return TmsLineInfo.getSingleInfo(t);
	}

	@Override
	public Integer getSumCount(TmsLineInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager<TmsLineInfo> getListPagerInfo(Pager<TmsLineInfo> pager, TmsLineInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delSingleInfo(TmsLineInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer modSingleInfo(TmsLineInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addSingleInfo(TmsLineInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TmsLineInfo> getListLineInfo(TmsLineInfo line) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TmsLineInfo> getListLineInfoByComId1(BigInteger cId, String province, String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TmsLineInfo getLineInfo(TmsLineInfo line) {
		// TODO Auto-generated method stub
		return TmsLineInfo.getLineInfo(line);
	}

	@Override
	public List<TmsLineInfo> getListLineInfoByComId2(BigInteger cId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlatformDeliverGoods> getListLineInfoByComId3(BigInteger cId) {
		// TODO Auto-generated method stub
		return null;
	}

}
