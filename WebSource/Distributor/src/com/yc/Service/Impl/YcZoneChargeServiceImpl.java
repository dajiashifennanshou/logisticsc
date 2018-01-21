package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.IYcZoneChargeDao;
import com.yc.Entity.YcZoneCharge;
import com.yc.Service.IYcZoneChargeService;
import com.yc.Tool.Pager; 
/** 
* YcZoneCharge服务层 
* Auther:FENG 
*/ 
@Service 
public class YcZoneChargeServiceImpl implements IYcZoneChargeService { 

	@Autowired
	private IYcZoneChargeDao iYcZoneChargeDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	@Override
	public YcZoneCharge getSingleInfo(YcZoneCharge yczonecharge) {
		//TODO Auto-generated method stub
		return iYcZoneChargeDao.getSingleInfo(yczonecharge);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
	public Pager<YcZoneCharge> getListPagerInfo(Pager<YcZoneCharge> pager,YcZoneCharge yczonecharge) {
		//TODO Auto-generated method stub
		Integer sum=iYcZoneChargeDao.getSumCount(yczonecharge);
		Map<String,Object> map=pager.getElestMap(yczonecharge);
		pager.setObjectList(iYcZoneChargeDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	@Override
	public Integer delSingleInfo(YcZoneCharge yczonecharge) {
		//TODO Auto-generated method stub
		return iYcZoneChargeDao.delSingleInfo(yczonecharge);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	@Override
	public Integer getSumCount(YcZoneCharge yczonecharge) {
		//TODO Auto-generated method stub
		return iYcZoneChargeDao.getSumCount(yczonecharge);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer modSingleInfo(YcZoneCharge yczonecharge) {
		//TODO Auto-generated method stub
		return iYcZoneChargeDao.modSingleInfo(yczonecharge);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer addSingleInfo(YcZoneCharge yczonecharge) {
		//TODO Auto-generated method stub
		return iYcZoneChargeDao.addSingleInfo(yczonecharge);
	}
}
