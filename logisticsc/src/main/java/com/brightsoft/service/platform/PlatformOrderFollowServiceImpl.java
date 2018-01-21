package com.brightsoft.service.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.OrderStatusEnum;
import com.brightsoft.dao.platform.PlatformOrderFollowMapper;
import com.brightsoft.model.PlatformOrderFollow;
import com.brightsoft.model.PlatformOrderTracking;

@Service("PlatformOrderFollow")
public class PlatformOrderFollowServiceImpl {
	
	@Autowired
	public PlatformOrderFollowMapper followMapper;
	
	@Autowired
	private XzqhServiceImpl xzqhServiceImpl;
	
	public int insert(PlatformOrderFollow platformOrderFollow){
		return followMapper.insert(platformOrderFollow);
	}
	
	/**
	 * 获取订单跟踪数据
	 * @param orderTracking
	 * @return
	 */
	public PlatformOrderTracking getOrderTracking(PlatformOrderTracking orderTracking ){
		PlatformOrderTracking platformOrderTracking = null;
		platformOrderTracking = followMapper.selectOrder(orderTracking);
		if(null !=platformOrderTracking){
			platformOrderTracking.setStartCounty(xzqhServiceImpl.selectValueById(platformOrderTracking.getStartCounty()).getName());
			platformOrderTracking.setStartProvince(xzqhServiceImpl.selectValueById(platformOrderTracking.getStartProvince()).getName());
			platformOrderTracking.setStartCity(xzqhServiceImpl.selectValueById(platformOrderTracking.getStartCity()).getName());
			platformOrderTracking.setEndProvince(xzqhServiceImpl.selectValueById(platformOrderTracking.getEndProvince()).getName());
			platformOrderTracking.setEndCity(xzqhServiceImpl.selectValueById(platformOrderTracking.getEndCity()).getName());
			platformOrderTracking.setEndCounty(xzqhServiceImpl.selectValueById(platformOrderTracking.getEndCounty()).getName());
			List<PlatformOrderFollow> follows = followMapper.selectOrderFollow(orderTracking);
			for (int i = 0; i < follows.size(); i++) {
				OrderStatusEnum[] orderStatusEnums = OrderStatusEnum.values();
				for (OrderStatusEnum orderStatusEnum : orderStatusEnums) {
					if(follows.get(i).getStatus() == orderStatusEnum.getValue()){
						follows.get(i).setStatusName(orderStatusEnum.getName());
						break;
					}
				}
			}
			platformOrderTracking.setFollows(follows);
		}
		return platformOrderTracking;
	}
}
