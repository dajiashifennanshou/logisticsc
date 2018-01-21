package com.yc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.TmsLineInfoDao;
import com.yc.Entity.CompanyOutletsLine;
import com.yc.Entity.TmsLineInfo;
import com.yc.Service.TmsLineInfoService;

@Service
public class TmsLineInfoServiceImpl implements TmsLineInfoService {

	@Autowired
	private TmsLineInfoDao iTmsLineInfoDao;
	@Override
	public TmsLineInfo getLineInfo(TmsLineInfo line) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CompanyOutletsLine getCompanyOutletsLine(CompanyOutletsLine col) {
		// TODO Auto-generated method stub
		return iTmsLineInfoDao.getCompanyOutletsLine(col);
	}
}
