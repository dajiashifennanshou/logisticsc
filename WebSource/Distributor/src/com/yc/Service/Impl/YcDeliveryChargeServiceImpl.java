package com.yc.Service.Impl; 
import com.yc.Entity.YcDeliveryCharge;
import com.yc.Dao.IYcDeliveryChargeDao; 
import com.yc.Service.IYcDeliveryChargeService; 
import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired;
/** 
* YcDeliveryCharge服务层 
* Auther:FENG 
*/ 
@Service 
public class YcDeliveryChargeServiceImpl implements IYcDeliveryChargeService { 

	@Autowired
	private IYcDeliveryChargeDao iYcDeliveryChargeDao;

	@Override
	public YcDeliveryCharge getDeliveryCostByCarNo(String carNo,String branchNo) {
		// TODO Auto-generated method stub
		return iYcDeliveryChargeDao.getDeliveryCostByCarNo(carNo,branchNo);
	}
}
