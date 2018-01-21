package com.yc.Service.Impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.PlatformUserCommonDriverDao;
import com.yc.Entity.PlatformUserCommonDriver;
import com.yc.Service.PlatformUserCommonDriverService;
import com.yc.Tool.Pager;
@Service
public class PlatformUserCommonDriverServiceImpl implements PlatformUserCommonDriverService {
	
	@Autowired
	private PlatformUserCommonDriverDao platformUserCommonDriverDao;

	@Override
	public Pager<PlatformUserCommonDriver> getPagerCommonDriver(Pager<PlatformUserCommonDriver> pager,
			PlatformUserCommonDriver cd) {
		// TODO Auto-generated method stub
		Map<String,Object> map = pager.getElestMap(cd);
		pager.setObjectList(platformUserCommonDriverDao.getListPagerInfo(map));
		return pager;
	}

	@Override
	public PlatformUserCommonDriver getCommonDriver(PlatformUserCommonDriver cd) {
		// TODO Auto-generated method stub
		return platformUserCommonDriverDao.getSingleInfo(cd);
	}

	@Override
	public Integer addCommonDriver(PlatformUserCommonDriver cd) {
		// TODO Auto-generated method stub
		return platformUserCommonDriverDao.addSingleInfo(cd);
	}

	@Override
	public Integer delCommonDriver(PlatformUserCommonDriver cd) {
		// TODO Auto-generated method stub
		return platformUserCommonDriverDao.delSingleInfo(cd);
	}

}
