package com.yc.Service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.PlatformOrderDao;
import com.yc.Entity.LcPlatformOrder;
import com.yc.Entity.LcPlatformOrderAdditionalServer;
import com.yc.Entity.LcPlatformOrderCargo;
import com.yc.Service.PlatformOrderService;
@Service
public class PlatformOrderServiceImpl implements PlatformOrderService {

	@Autowired
	private PlatformOrderDao iLcPlatformOrderDao;

	@Override
	public LcPlatformOrder getOrder(LcPlatformOrder order) {
		// TODO Auto-generated method stub
		return iLcPlatformOrderDao.getOrder(order);
	}
	@Override
	public LcPlatformOrderAdditionalServer getOrderAdditionalServer(LcPlatformOrderAdditionalServer server) {
		// TODO Auto-generated method stub
		return iLcPlatformOrderDao.getOrderAdditionalServer(server);
	}
	@Override
	public List<LcPlatformOrderCargo> getOrderCargoList(LcPlatformOrderCargo cargo) {
		// TODO Auto-generated method stub
		return iLcPlatformOrderDao.getOrderCargoList(cargo);
	}
	@Override
	public Integer updatePlatformOrder(LcPlatformOrder order) {
		// TODO Auto-generated method stub
		return iLcPlatformOrderDao.updatePlatformOrder(order);
	}
}
