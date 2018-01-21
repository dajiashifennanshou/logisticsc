package com.yc.Service.Impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.PlatformUserCommonContactDao;
import com.yc.Entity.PlatformUserCommonContact;
import com.yc.Service.PlatformUserCommonContactService;
import com.yc.Tool.DateUtil;
import com.yc.Tool.Pager;
@Service
public class PlatformUserCommonContactServiceImpl implements PlatformUserCommonContactService {

	@Autowired
	private PlatformUserCommonContactDao platformUserCommonContactDao;

	@Override
	public Pager<PlatformUserCommonContact> getPageCommonContact(Pager<PlatformUserCommonContact> pager,PlatformUserCommonContact cc) {
		// TODO Auto-generated method stub
		Map<String,Object> map = pager.getElestMap(cc);
		pager.setObjectList(platformUserCommonContactDao.getListPagerInfo(map));
		return pager;
	}

	@Override
	public Integer addCommonContact(PlatformUserCommonContact cc) {
		// TODO Auto-generated method stub
		cc.setState(1);//状态
		cc.setCreate_time(DateUtil.getDateTimeFormatString());
		return platformUserCommonContactDao.addSingleInfo(cc);
	}

	@Override
	public PlatformUserCommonContact getCommonContact(PlatformUserCommonContact cc) {
		// TODO Auto-generated method stub
		return platformUserCommonContactDao.getSingleInfo(cc);
	}

	@Override
	public Integer delCommonContact(PlatformUserCommonContact cc) {
		// TODO Auto-generated method stub
		return platformUserCommonContactDao.delSingleInfo(cc);
	}
}
