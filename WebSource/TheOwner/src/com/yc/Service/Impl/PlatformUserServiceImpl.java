package com.yc.Service.Impl; 
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Canstant.Constant;
import com.yc.Dao.PlatformUserDao;
import com.yc.Entity.PlatformUser;
import com.yc.Service.PlatformUserService;
import com.yc.Tool.DateUtil; 
/** 
* LcPlatformUser服务层 
* Auther:FENG 
*/ 
@Service 
public class PlatformUserServiceImpl implements PlatformUserService { 

	@Autowired
	private PlatformUserDao iLcPlatformUserDao;

	@Override
	public PlatformUser getPlatformUserInfo(PlatformUser user) {
		// TODO Auto-generated method stub
		return iLcPlatformUserDao.getSingleInfo(user);
	}

	@Override
	public Integer addUserInfo(PlatformUser user) {
		// TODO Auto-generated method stub
		user.setLogin_name(user.getMobile());
		user.setStatus(Constant.PLATFORMUSER_STATUS_1);//默认可用
		user.setUser_type(Constant.PLATFORM_USER_TYPE_PERSONAL_CONSIGNOR);//默认个人货主
		user.setCreate_time(DateUtil.getDateTimeFormatString());
		return iLcPlatformUserDao.addSingleInfo(user);
	}

	@Override
	public Integer updatePassword(PlatformUser user) {
		// TODO Auto-generated method stub
		return iLcPlatformUserDao.modSingleInfo(user);
	}

	@Override
	public PlatformUser getEleUserInfo(BigInteger id) {
		// TODO Auto-generated method stub
		return iLcPlatformUserDao.getEleUserInfo(id);
	}


}
