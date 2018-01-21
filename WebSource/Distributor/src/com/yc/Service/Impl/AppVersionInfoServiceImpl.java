package com.yc.Service.Impl; 
import com.yc.Dao.AppVersionInfoDao; 
import com.yc.Service.AppVersionInfoService; 
import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired; 
/** 
* AppVersionInfo服务层 
* Auther:luojing
*/ 
@Service 
public class AppVersionInfoServiceImpl implements AppVersionInfoService { 

	@Autowired
	private AppVersionInfoDao versionInfoDao;

}
