package com.yc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.PlatformBankNameDao;
import com.yc.Entity.PlatformBankName;
import com.yc.Service.PlatformBankNameService;
/**
 * 查询开户行
 * @Author:luojing
 * @2016年7月19日 下午7:30:39
 */
@Service
public class platformBankNameServiceImpl implements PlatformBankNameService {

	@Autowired
	private PlatformBankNameDao platformBankNameDao;
	@Override
	public List<PlatformBankName> selectProvinceName() {
		// TODO Auto-generated method stub
		return platformBankNameDao.selectProvinceName();
	}

	@Override
	public List<PlatformBankName> selectCityName(PlatformBankName pfb) {
		// TODO Auto-generated method stub
		return platformBankNameDao.selectCityName(pfb);
	}

	@Override
	public List<PlatformBankName> selectHeadName(PlatformBankName pfb) {
		// TODO Auto-generated method stub
		return platformBankNameDao.selectHeadName(pfb);
	}

	@Override
	public List<PlatformBankName> selectBranchName(PlatformBankName pfb) {
		// TODO Auto-generated method stub
		return platformBankNameDao.selectBranchName(pfb);
	}

}
