package com.brightsoft.service.tms.platform.startsitemanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.tms.WayBillOrderCargoInfoMapper;
import com.brightsoft.model.WayBillOrderCargoInfo;

/**
 * 运单货物 service
 * @author yangshoubao
 *
 */
@Service
public class WayBillOrderCargoService {

	@Autowired
	private WayBillOrderCargoInfoMapper wayBillOrderCargoInfoMapper;
	
	/**
	 * 根据运单ID 查询运单货物信息
	 * @param wayBillOrderId
	 * @return
	 */
	public List<WayBillOrderCargoInfo> selectByWayBillOrderId(Long wayBillOrderId){
		return wayBillOrderCargoInfoMapper.selectByWayBillOrderId(wayBillOrderId);
	}
}
