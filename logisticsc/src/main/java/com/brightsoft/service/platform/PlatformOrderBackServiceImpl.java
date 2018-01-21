package com.brightsoft.service.platform;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.OrderStatusEnum;
import com.brightsoft.dao.platform.PlatformOrderBackMapper;
import com.brightsoft.dao.platform.PlatformOrderFollowMapper;
import com.brightsoft.dao.platform.PlatformOrderMapper;
import com.brightsoft.model.PlatformOrder;
import com.brightsoft.model.PlatformOrderFollow;
import com.brightsoft.model.PlatformUser;

@Service
public class PlatformOrderBackServiceImpl {
	
	@Autowired
	private PlatformOrderBackMapper backMapper;
	
	@Autowired
	private PlatformOrderMapper orderMapper;
	
	@Autowired
	private PlatformOrderFollowMapper followMapper;
	public boolean doBack(String orderNumber,PlatformUser user){
			//修改当前订单状态
			PlatformOrder record = new PlatformOrder();
			record.setOrderNumber(orderNumber);
			record.setState(OrderStatusEnum.CANCEL.getValue());
			if(orderMapper.updateByOrderNumberSelective(record) > 0){
				PlatformOrderFollow orderFollow = new PlatformOrderFollow();
				orderFollow.setOrderNumber(orderNumber);
				orderFollow.setStatus(OrderStatusEnum.CANCEL.getValue());
				orderFollow.setHandleTime(new Date());
				orderFollow.setHandleInfo(OrderStatusEnum.CANCEL.getName());
				orderFollow.setOperatePerson(user.getLoginName());
				if(followMapper.insertSelective(orderFollow)>0){
					return true;
				}
			}
		return false;
	}
}
