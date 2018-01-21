package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.IYcStorageChargeDao;
import com.yc.Entity.YcStorageCharge;
import com.yc.Service.IYcStorageChargeService;
import com.yc.Tool.Pager; 
/** 
* YcStorageCharge服务层 
* Auther:FENG 
*/ 
@Service 
public class YcStorageChargeServiceImpl implements IYcStorageChargeService { 

	@Autowired
	private IYcStorageChargeDao iYcStorageChargeDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	@Override
	public YcStorageCharge getSingleInfo(YcStorageCharge ycstoragecharge) {
		//TODO Auto-generated method stub
		return iYcStorageChargeDao.getSingleInfo(ycstoragecharge);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
	public Pager<YcStorageCharge> getListPagerInfo(Pager<YcStorageCharge> pager,YcStorageCharge ycstoragecharge) {
		//TODO Auto-generated method stub
		Integer sum=iYcStorageChargeDao.getSumCount(ycstoragecharge);
		Map<String,Object> map=pager.getElestMap(ycstoragecharge);
		pager.setObjectList(iYcStorageChargeDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	@Override
	public Integer delSingleInfo(YcStorageCharge ycstoragecharge) {
		//TODO Auto-generated method stub
		return iYcStorageChargeDao.delSingleInfo(ycstoragecharge);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	@Override
	public Integer getSumCount(YcStorageCharge ycstoragecharge) {
		//TODO Auto-generated method stub
		return iYcStorageChargeDao.getSumCount(ycstoragecharge);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer modSingleInfo(YcStorageCharge ycstoragecharge) {
		//TODO Auto-generated method stub
		return iYcStorageChargeDao.modSingleInfo(ycstoragecharge);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer addSingleInfo(YcStorageCharge ycstoragecharge) {
		//TODO Auto-generated method stub
		return iYcStorageChargeDao.addSingleInfo(ycstoragecharge);
	}
}
