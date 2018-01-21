package com.yc.Service.Impl; 
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.IYcOutStorageDao;
import com.yc.Entity.YcOutStorage;
import com.yc.Service.IYcOutStorageService;
import com.yc.Tool.Pager; 
/** 
* YcOutStorage服务层 
* Auther:FENG 
*/ 
@Service 
public class YcOutStorageServiceImpl implements IYcOutStorageService { 

	@Autowired
	private IYcOutStorageDao iYcOutStorageDao;
	
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
	public Pager<YcOutStorage> getListPagerInfo(Pager<YcOutStorage> pager,YcOutStorage ycoutstorage) {
		//TODO Auto-generated method stub
		Integer sum=iYcOutStorageDao.getSumCount(ycoutstorage);
		Map<String,Object> map=pager.getElestMap(ycoutstorage);
		pager.setObjectList(iYcOutStorageDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	
}
