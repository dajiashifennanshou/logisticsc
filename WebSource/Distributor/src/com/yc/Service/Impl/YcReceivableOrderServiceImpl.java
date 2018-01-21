package com.yc.Service.Impl; 
import com.yc.Entity.YcReceivableOrder; 
import com.yc.Tool.Pager; 
import com.yc.Dao.IYcReceivableOrderDao; 
import com.yc.Service.IYcReceivableOrderService; 
import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;
import java.util.Map; 
/** 
* YcReceivableOrder服务层 
* Auther:FENG 
*/ 
@Service 
public class YcReceivableOrderServiceImpl implements IYcReceivableOrderService { 

	@Autowired
	private IYcReceivableOrderDao iYcReceivableOrderDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	@Override
	public YcReceivableOrder getSingleInfo(YcReceivableOrder ycreceivableorder) {
		//TODO Auto-generated method stub
		return iYcReceivableOrderDao.getSingleInfo(ycreceivableorder);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
	public Pager<YcReceivableOrder> getListPagerInfo(Pager<YcReceivableOrder> pager,YcReceivableOrder ycreceivableorder) {
		//TODO Auto-generated method stub
		Integer sum=iYcReceivableOrderDao.getSumCount(ycreceivableorder);
		Map<String,Object> map=pager.getElestMap(ycreceivableorder);
		pager.setObjectList(iYcReceivableOrderDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	@Override
	public Integer delSingleInfo(YcReceivableOrder ycreceivableorder) {
		//TODO Auto-generated method stub
		return iYcReceivableOrderDao.delSingleInfo(ycreceivableorder);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	@Override
	public Integer getSumCount(YcReceivableOrder ycreceivableorder) {
		//TODO Auto-generated method stub
		return iYcReceivableOrderDao.getSumCount(ycreceivableorder);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer modSingleInfo(YcReceivableOrder ycreceivableorder) {
		//TODO Auto-generated method stub
		return iYcReceivableOrderDao.modSingleInfo(ycreceivableorder);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer addSingleInfo(YcReceivableOrder ycreceivableorder) {
		//TODO Auto-generated method stub
		return iYcReceivableOrderDao.addSingleInfo(ycreceivableorder);
	}
}
