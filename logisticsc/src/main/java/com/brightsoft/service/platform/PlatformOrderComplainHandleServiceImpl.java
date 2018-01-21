package com.brightsoft.service.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformOrderComlainHandleMapper;
import com.brightsoft.dao.platform.PlatformOrderComplainMapper;
import com.brightsoft.model.PlatformOrderComlainHandle;
import com.brightsoft.model.PlatformOrderComplain;
import com.brightsoft.model.PlatformOrderComplainInfo;
import com.brightsoft.model.PlatformOrderMineComplain;
import com.brightsoft.utils.Const;

@Service
public class PlatformOrderComplainHandleServiceImpl {

	@Autowired
	private PlatformOrderComlainHandleMapper platformOrderComlainHandleMapper;
	
	@Autowired
	private PlatformOrderComplainMapper platformOrderComplainMapper;
	
	/**
	 * 投诉处理
	 * @return
	 */
	public int insert(PlatformOrderComlainHandle platformOrderComlainHandle){
		return platformOrderComlainHandleMapper.insert(platformOrderComlainHandle);
	}
	/**
	 * 查看投诉回复信息
	 * @param complaintId
	 * @return
	 */
	public PlatformOrderComplainInfo selectComlainHandle(Long complaintId){
		PlatformOrderComplainInfo complainInfo = new PlatformOrderComplainInfo();
		PlatformOrderComlainHandle comlainHandle = platformOrderComlainHandleMapper.selectComlainHandle(complaintId);
		PlatformOrderMineComplain complain = platformOrderComplainMapper.selectOrderMineComplain(complaintId);
		if(null != comlainHandle){
			complainInfo.setComlainHandle(comlainHandle);
			complainInfo.setComplain(complain);
		}else{
			complainInfo = null;
		}
		return complainInfo;
	}
	/**
	 * 修改投诉回复满意度
	 * @param comlainHandle
	 * @return
	 */
	public boolean updateComlainHandle(PlatformOrderComlainHandle comlainHandle){
		if(platformOrderComlainHandleMapper.updateComlainHandle(comlainHandle) >0){
			PlatformOrderComplain record = new PlatformOrderComplain();
			record.setId(comlainHandle.getComplaintId());
			record.setState(Const.PLATFORMUSER_ORDER_COMPLAIN_STATE_FEEDBACKED);
			if(platformOrderComplainMapper.updateByPrimaryKeySelective(record) >0){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
}
