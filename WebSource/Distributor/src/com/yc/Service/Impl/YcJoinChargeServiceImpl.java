package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.IYcJoinChargeDao;
import com.yc.Entity.YcJoinCharge;
import com.yc.Service.IYcJoinChargeService;
import com.yc.Tool.Pager; 
/** 
* YcJoinCharge服务层 
* Auther:FENG 
*/ 
@Service 
public class YcJoinChargeServiceImpl implements IYcJoinChargeService { 

	@Autowired
	private IYcJoinChargeDao iYcJoinChargeDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	@Override
	public YcJoinCharge getSingleInfo(YcJoinCharge ycjoincharge) {
		//TODO Auto-generated method stub
		return iYcJoinChargeDao.getSingleInfo(ycjoincharge);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
	public Pager<YcJoinCharge> getListPagerInfo(Pager<YcJoinCharge> pager,YcJoinCharge ycjoincharge) {
		//TODO Auto-generated method stub
		Integer sum=iYcJoinChargeDao.getSumCount(ycjoincharge);
		Map<String,Object> map=pager.getElestMap(ycjoincharge);
		pager.setObjectList(iYcJoinChargeDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	@Override
	public Integer delSingleInfo(YcJoinCharge ycjoincharge) {
		//TODO Auto-generated method stub
		return iYcJoinChargeDao.delSingleInfo(ycjoincharge);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	@Override
	public Integer getSumCount(YcJoinCharge ycjoincharge) {
		//TODO Auto-generated method stub
		return iYcJoinChargeDao.getSumCount(ycjoincharge);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer modSingleInfo(YcJoinCharge ycjoincharge) {
		//TODO Auto-generated method stub
		return iYcJoinChargeDao.modSingleInfo(ycjoincharge);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer addSingleInfo(YcJoinCharge ycjoincharge) {
		//TODO Auto-generated method stub
		return iYcJoinChargeDao.addSingleInfo(ycjoincharge);
	}
}