package com.yc.Service.Impl; 
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.TmsWayBillOrderCargoInfoDao;
import com.yc.Entity.TmsWayBillOrderCargoInfo;
import com.yc.Service.TmsWayBillOrderCargoInfoService;
import com.yc.Tool.Pager;
/** 
* TmsWayBillOrderCargoInfo服务层 
* Auther:FENG 
*/ 
@Service 
public class TmsWayBillOrderCargoInfoServiceImpl implements TmsWayBillOrderCargoInfoService { 

	@Autowired
	private TmsWayBillOrderCargoInfoDao iTmsWayBillOrderCargoInfoDao;
	
	@Override
	public Pager<TmsWayBillOrderCargoInfo> getWayBillOrderCargoInfoPager(Pager<TmsWayBillOrderCargoInfo> pager,TmsWayBillOrderCargoInfo wbc) {
		// TODO Auto-generated method stub
		Map<String,Object> map = pager.getElestMap(wbc);
		pager.setObjectList(iTmsWayBillOrderCargoInfoDao.getWayBillOrderCargoInfoPager(map));
		return pager;
	}
}
