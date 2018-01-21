package com.brightsoft.service.platform;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformOrderBackMapper;
import com.brightsoft.model.PlatformOrderBack;

@Service
public class PlatformOrderBankServiceImpl {
	
	@Autowired
	private PlatformOrderBackMapper platformOrderBackMapper;

//	/**
//	 * 
//	 * 方法描述：获取预约退款记录
//	 * @param searchParams
//	 * @param page
//	 * @return
//	 * @author dub
//	 * @version 2016年5月12日 上午11:20:12
//	 */
//	public Result select4Appointment(SearchParams searchParams,Page<?> page){
//		Result result = new Result();
//		int results = platformOrderBackMapper.countRowsByRefundType(searchParams, Const.PLATFORM_ORDER_BACK_TYPE_0);
//		List<PlatformOrderBack> rows = platformOrderBackMapper.selectByRefundType(searchParams, page, Const.PLATFORM_ORDER_BACK_TYPE_0);
//		result.setResults(results);
//		result.setRows(rows);
//		return result;
//	}
//	
//	/**
//	 * 
//	 * 方法描述：获取运费退款记录数
//	 * @param searchParams
//	 * @param page
//	 * @return
//	 * @author dub
//	 * @version 2016年5月12日 上午11:21:47
//	 */
//	public Result select4Carriage(SearchParams searchParams,Page<?> page){
//		Result result = new Result();
//		int results = platformOrderBackMapper.countRowsByRefundType(searchParams, Const.PLATFORM_ORDER_BACK_TYPE_1);
//		List<PlatformOrderBack> rows = platformOrderBackMapper.selectByRefundType(searchParams, page, Const.PLATFORM_ORDER_BACK_TYPE_1);
//		result.setResults(results);
//		result.setRows(rows);
//		return result;
//	}
//	
//	/**
//	 * 
//	 * 方法描述：执行退款操作
//	 * @param orderNumber
//	 * @return
//	 * @author dub
//	 * @version 2016年5月12日 下午4:29:15
//	 */
//	public boolean update2Drawback(String orderNumber){
//		boolean flag = false;
//		if(platformOrderBackMapper.updateOrderBackState(orderNumber, Const.PLATFORM_ORDER_BACK_STATE_1)>0){
//			flag = true;
//		}
//		return flag;
//	}
//	
//	/**
//	 * 
//	 * 方法描述：取消退款
//	 * @param orderNumber
//	 * @return
//	 * @author dub
//	 * @version 2016年5月12日 下午4:29:01
//	 */
//	public boolean update2Cancel(String orderNumber){
//		boolean flag = false;
//		if(platformOrderBackMapper.updateOrderBackState(orderNumber, Const.PLATFORM_ORDER_BACK_STATE_2)>0){
//			flag = true;
//		}
//		return flag;
//	}
	
	/**
	 * 
	 * 方法描述：根据订单号获取退款记录
	 * @param orderNumber
	 * @return
	 * @author dub
	 * @version 2016年5月12日 下午5:12:00
	 */
	public PlatformOrderBack selectByOrderNumber(String orderNumber){
		return platformOrderBackMapper.selectByOrderNumber(orderNumber);
	}
}

