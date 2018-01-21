package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.Constant.Constant;
import com.brightsoft.dao.yc.IYcDeliveryChargeDao;
import com.brightsoft.entity.YcDeliveryCharge;
import com.brightsoft.service.yc.IYcDeliveryChargeService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcDeliveryCharge服务层 
* Auther:FENG 
*/ 
@Service 
public class YcDeliveryChargeServiceImpl implements IYcDeliveryChargeService { 

	@Autowired
	private IYcDeliveryChargeDao iYcDeliveryChargeDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcDeliveryCharge getSingleInfo(YcDeliveryCharge ycdeliverycharge) {
		//TODO Auto-generated method stub
		return iYcDeliveryChargeDao.getSingleInfo(ycdeliverycharge);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcDeliveryCharge> getListPagerInfo(Pager<YcDeliveryCharge> pager,YcDeliveryCharge ycdeliverycharge) {
		//TODO Auto-generated method stub
		Integer sum=iYcDeliveryChargeDao.getSumCount(ycdeliverycharge);
		Map<String,Object> map=pager.getElestMap(ycdeliverycharge);
		pager.setObjectList(iYcDeliveryChargeDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcDeliveryCharge ycdeliverycharge) {
		//TODO Auto-generated method stub
		return iYcDeliveryChargeDao.delSingleInfo(ycdeliverycharge);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcDeliveryCharge ycdeliverycharge) {
		//TODO Auto-generated method stub
		return iYcDeliveryChargeDao.getSumCount(ycdeliverycharge);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcDeliveryCharge ycdeliverycharge) {
		//TODO Auto-generated method stub
		ycdeliverycharge.setUpdateTime(DateUtil.getDateTimeFormatString());
		ycdeliverycharge.setUpdateUser(Constant.SYSTEM_OPERSTION_PERSON);
		return iYcDeliveryChargeDao.modSingleInfo(ycdeliverycharge);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	
	public Integer addSingleInfo(YcDeliveryCharge ycdeliverycharge) {
		//TODO Auto-generated method stub
		ycdeliverycharge.setCreateTime(DateUtil.getDateTimeFormatString());
		ycdeliverycharge.setCreateUser(Constant.SYSTEM_OPERSTION_PERSON);
		return iYcDeliveryChargeDao.addSingleInfo(ycdeliverycharge);
	}
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return iYcDeliveryChargeDao.delSelect(list);
	}
	
	public YcDeliveryCharge getDeliveryCostByCarNo(String carNo,String branchNo) {
		// TODO Auto-generated method stub
		return iYcDeliveryChargeDao.getDeliveryCostByCarNo(carNo,branchNo);
	}
}
