package com.yc.Service.Impl; 
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.BulletinInfoDao;
import com.yc.Entity.LcBulletinInfo;
import com.yc.Service.BulletinInfoService;
import com.yc.Tool.Pager; 
/** 
* lc_bulletin_info服务层 
* Auther:FENG 
*/ 
@Service 
public class BulletinInfoServiceImpl implements BulletinInfoService {
	
	@Autowired
	private BulletinInfoDao bulletinInfoDao;

	@Override
	public Pager<LcBulletinInfo> getListPagerInfo(Pager<LcBulletinInfo> pager) {
		// TODO Auto-generated method stub
		 Map<String,Object> map=new HashMap<String,Object>();
		 map.put("limitMax", pager.getLimitMax());
		 map.put("limitMin", pager.getLimitMin());
		 pager.setObjectList(bulletinInfoDao.getListPagerInfo(map));
		 return pager;
	}
	
}
