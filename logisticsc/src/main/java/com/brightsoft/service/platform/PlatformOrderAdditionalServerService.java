package com.brightsoft.service.platform;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformOrderAdditionalServerMapper;
import com.brightsoft.model.PlatformOrderAdditionalServer;

/**
 * 
 * 货运交易系统订单附加服务service
 */
@Service
public class PlatformOrderAdditionalServerService {

	@Autowired
	private PlatformOrderAdditionalServerMapper additionalServerMapper;
	
	/**
	 * 根据订单号 查询订单增值服务信息
	 * @param orderId
	 * @return
	 */
	public PlatformOrderAdditionalServer selectByOrderId(String orderId){
		if(StringUtils.isEmpty(orderId)){
			return null;
		}
		return additionalServerMapper.selectByOrderId(Long.parseLong(orderId));
	}
	
	public PlatformOrderAdditionalServer selectByOrderNumber(String orderNumber){
		if(StringUtils.isEmpty(orderNumber)){
			return null;
		}
		return additionalServerMapper.selectByOrderNumber(orderNumber);
	}
}
