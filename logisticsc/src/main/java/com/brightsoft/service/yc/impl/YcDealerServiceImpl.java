package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.IYcDealerDao;
import com.brightsoft.entity.YcDealer;
import com.brightsoft.service.yc.IYcDealerService;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcDealer服务层 
* Auther:FENG 
*/ 
@Service 
public class YcDealerServiceImpl implements IYcDealerService { 

	@Autowired
	private IYcDealerDao iYcDealerDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcDealer getSingleInfo(YcDealer ycdealer) {
		//TODO Auto-generated method stub
		return iYcDealerDao.getSingleInfo(ycdealer);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcDealer> getListPagerInfo(Pager<YcDealer> pager,YcDealer ycdealer) {
		//TODO Auto-generated method stub
		Integer sum=iYcDealerDao.getSumCount(ycdealer);
		Map<String,Object> map=pager.getElestMap(ycdealer);
		pager.setObjectList(iYcDealerDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcDealer ycdealer) {
		//TODO Auto-generated method stub
		return iYcDealerDao.delSingleInfo(ycdealer);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcDealer ycdealer) {
		//TODO Auto-generated method stub
		return iYcDealerDao.getSumCount(ycdealer);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcDealer ycdealer) {
		//TODO Auto-generated method stub
		return iYcDealerDao.modSingleInfo(ycdealer);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	
	public Integer addSingleInfo(YcDealer ycdealer) {
		//TODO Auto-generated method stub
		return iYcDealerDao.addSingleInfo(ycdealer);
	}
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return iYcDealerDao.delSelect(list);
	}
	
	public List<YcDealer> getUnRegisterDealer() {
		// TODO Auto-generated method stub
		return iYcDealerDao.getUnRegisterDealer();
	}
	
	public List<YcDealer> getAllDealer() {
		// TODO Auto-generated method stub
		return iYcDealerDao.getAllDealer();
	}
}
