package com.yc.Service.Impl; 
import com.yc.Dao.IYcStowageDeliveryDao; 
import com.yc.Service.IYcStowageDeliveryService; 
import org.springframework.stereotype.Service; 

import org.springframework.beans.factory.annotation.Autowired;

/** 
* YcStowageDelivery服务层 
* Auther:FENG 
*/ 
@Service 
public class YcStowageDeliveryServiceImpl implements IYcStowageDeliveryService { 

	@Autowired
	private IYcStowageDeliveryDao iYcStowageDeliveryDao;
}
