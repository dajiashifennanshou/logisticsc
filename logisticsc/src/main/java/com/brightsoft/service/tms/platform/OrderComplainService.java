package com.brightsoft.service.tms.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.model.PlatformOrderComlainHandle;
import com.brightsoft.model.PlatformOrderComplain;
import com.brightsoft.model.User;
import com.brightsoft.service.platform.PlatformOrderComplainHandleServiceImpl;
import com.brightsoft.service.platform.PlatformOrderComplainServiceImpl;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class OrderComplainService {

	@Autowired
	private PlatformOrderComplainServiceImpl PlatformOrderComplainServiceImpl;
	
	@Autowired
	private PlatformOrderComplainHandleServiceImpl platformOrderComplainHandleServiceImpl;
	
	/**
	 * 查询投诉信息
	 * @return
	 */
	public Result selectByCondition(Page<?> page,PlatformOrderComplain platformOrderComplain,User user){
		return PlatformOrderComplainServiceImpl.selectByCondition(user, platformOrderComplain, page);
	}
	
	/**
	 * 处理投诉信息
	 * @return
	 */
	public int insert(PlatformOrderComplain platformOrderComplain,
			PlatformOrderComlainHandle platformOrderComlainHandle){
		int flag = 0;
		if(PlatformOrderComplainServiceImpl.update2handle(platformOrderComplain)>0){
			flag = platformOrderComplainHandleServiceImpl.insert(platformOrderComlainHandle);
		}
		return flag;
	}
}
