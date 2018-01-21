package com.brightsoft.service.yc.impl; 
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.ITmsWayBillOrderCargoInfoDao;
import com.brightsoft.entity.TmsWayBillOrderCargoInfo;
import com.brightsoft.service.yc.ITmsWayBillOrderCargoInfoService;
import com.brightsoft.utils.yc.Pager;
/** 
* TmsWayBillOrderCargoInfo服务层 
* Auther:FENG 
*/ 
@Service 
public class TmsWayBillOrderCargoInfoServiceImpl implements ITmsWayBillOrderCargoInfoService { 

	@Autowired
	private ITmsWayBillOrderCargoInfoDao iTmsWayBillOrderCargoInfoDao;
	
	public Pager<TmsWayBillOrderCargoInfo> getWayBillOrderCargoInfoPager(Pager<TmsWayBillOrderCargoInfo> pager,TmsWayBillOrderCargoInfo wbc) {
		// TODO Auto-generated method stub
		Map<String,Object> map = pager.getElestMap(wbc);
		pager.setRecordCount(1);
		pager.setObjectList(iTmsWayBillOrderCargoInfoDao.getWayBillOrderCargoInfoPager(map));
		return pager;
	}
}
