package com.brightsoft.service.yc.impl; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.ILcPlatformUserDao;
import com.brightsoft.entity.LcPlatformUser;
import com.brightsoft.service.yc.IlcPlatformUserService;

/** 
* LcPlatformUser服务层 
* Auther:FENG 
*/ 
@Service 
public class LcPlatformUserServiceImpl implements IlcPlatformUserService {

	@Autowired
	private ILcPlatformUserDao iLcPlatformUserDao;

	public LcPlatformUser getSingleInfo(LcPlatformUser user){
		// TODO Auto-generated method stub
		return iLcPlatformUserDao.getSingleInfo(user);
	}

	public Integer addUserInfo(LcPlatformUser user) {
		// TODO Auto-generated method stub
		return iLcPlatformUserDao.addUserInfo(user);
	}

	public Integer updatePassword(LcPlatformUser user) {
		// TODO Auto-generated method stub
		return iLcPlatformUserDao.updatePassword(user);
	}
}
