package com.yc.Service.Impl; 
import com.yc.Dao.IYcStowageDao; 
import com.yc.Service.IYcStowageService; 
import org.springframework.stereotype.Service; 

import org.springframework.beans.factory.annotation.Autowired;
/** 
* YcStowage服务层 
* Auther:FENG 
*/ 
@Service 
public class YcStowageServiceImpl implements IYcStowageService { 

	@Autowired
	private IYcStowageDao iYcStowageDao;
}
