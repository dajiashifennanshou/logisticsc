package com.yc.Service.Impl; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.PlatformUserDao;
import com.yc.Entity.LcPlatformUser;
import com.yc.Service.PlatformUserService; 
/** 
* LcPlatformUser服务层 
* Auther:FENG 
*/ 
@Service 
public class PlatformUserServiceImpl implements PlatformUserService { 

	@Autowired
	private PlatformUserDao iLcPlatformUserDao;

	@Override
	public LcPlatformUser getSingleInfo(LcPlatformUser user) {
		// TODO Auto-generated method stub
		return iLcPlatformUserDao.getSingleInfo(user);
	}

	@Override
	public Integer addUserInfo(LcPlatformUser user) {
		// TODO Auto-generated method stub
		return iLcPlatformUserDao.addUserInfo(user);
	}

	@Override
	public Integer updatePassword(LcPlatformUser user) {
		// TODO Auto-generated method stub
		return iLcPlatformUserDao.updatePassword(user);
	}
}
