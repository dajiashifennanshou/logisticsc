package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.brightsoft.Constant.Constant;
import com.brightsoft.dao.yc.IYcDeliveryOrderDao;
import com.brightsoft.entity.ResultEntity;
import com.brightsoft.entity.YcDeliveryOrder;
import com.brightsoft.service.yc.IYcDeliveryOrderService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcDeliveryOrder服务层 
* Auther:FENG 
*/ 
@Service 
public class YcDeliveryOrderServiceImpl implements IYcDeliveryOrderService { 

	@Autowired
	private IYcDeliveryOrderDao iYcDeliveryOrderDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcDeliveryOrder getSingleInfo(YcDeliveryOrder ycdeliveryorder) {
		//TODO Auto-generated method stub
		return iYcDeliveryOrderDao.getSingleInfo(ycdeliveryorder);
	}
	/**
	 * 订单完成
	 * Author:FENG
	 * 2016年7月25日
	 * @param request
	 * @return
	 */
	@Transactional
	public ResultEntity deliveryOver(YcDeliveryOrder ycdeliveryorder){
		ResultEntity re=new ResultEntity();
		try{
			//前提：订单已支付，
			//步骤：1、更改订单状态为完成(需要订单号和订单状态)
			ycdeliveryorder.setOrderStatus(5);
			if(iYcDeliveryOrderDao.modOrderByNo(ycdeliveryorder)<1){
				re.setCode(1);
				re.setMsg("修改状态失败..");
			}
		}catch(Exception e){
			//事务回滚
			re.setCode(1);
			re.setMsg("异常");
			FengUtil.TRANEOLLBACK();
		}
		return re;
	}
	/**
	 * 根据配载单号查询子订单是否全都已经完成
	 * Author:FENG
	 * 2016年7月25日
	 * @return
	 */
	public boolean selectOrderIsOver(String stowageNo){
		Integer i=iYcDeliveryOrderDao.selectOrderIsOver(stowageNo);
		if(i<1){
			return true;
		}
		return false;
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	public Pager<YcDeliveryOrder> getListPagerInfo(Pager<YcDeliveryOrder> pager,YcDeliveryOrder ycdeliveryorder) {
		//TODO Auto-generated method stub
		Integer sum=iYcDeliveryOrderDao.getSumCount(ycdeliveryorder);
		Map<String,Object> map=pager.getElestMap(ycdeliveryorder);
		List<YcDeliveryOrder> list = iYcDeliveryOrderDao.getListPagerInfo(map);
		for(YcDeliveryOrder order:list){
			
		}
		pager.setObjectList(list);
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcDeliveryOrder ycdeliveryorder) {
		//TODO Auto-generated method stub
		return iYcDeliveryOrderDao.delSingleInfo(ycdeliveryorder);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcDeliveryOrder ycdeliveryorder) {
		//TODO Auto-generated method stub
		return iYcDeliveryOrderDao.getSumCount(ycdeliveryorder);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcDeliveryOrder ycdeliveryorder) {
		//TODO Auto-generated method stub
		return iYcDeliveryOrderDao.modSingleInfo(ycdeliveryorder);
	}
	
	/** 
	* 添加信息 
	* Auther:FENG 
	*/
	
	public Integer addSingleInfo(YcDeliveryOrder ycdeliveryorder) {
		//TODO Auto-generated method stub
		ycdeliveryorder.setOrderStatus(0);
		ycdeliveryorder.setSatisfaction(3);
		ycdeliveryorder.setEvaluateStatus(0);
		//配送单号
		ycdeliveryorder.setCreateTime(DateUtil.getDateTimeFormatString());
		Integer res=iYcDeliveryOrderDao.addCustomer(ycdeliveryorder);
		if(res<1){
			FengUtil.RuntimeExceptionFeng("添加常用联系人时失败!!");
		}
		res=iYcDeliveryOrderDao.addSingleInfo(ycdeliveryorder);
		if(res<1){
			FengUtil.RuntimeExceptionFeng("添加配送单信息时失败!!");
		}
		return res;
	}
	
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 删除（修改评价状态）
	 * Author:luojing
	 * 2016年6月16日 上午11:15:24
	 */ 
	
	public Integer modEvaluateStatus(List<BigInteger> list) {
		// TODO Auto-generated method stub
		try{
			if(!list.isEmpty()){
				return iYcDeliveryOrderDao.modEvaluateStatus(list);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<YcDeliveryOrder> getYcDeliveryOrderAllList(YcDeliveryOrder deliveryOrder) {
		// TODO Auto-generated method stub、
		return iYcDeliveryOrderDao.getYcDeliveryOrderAllList(deliveryOrder);
	}
	
	public List<YcDeliveryOrder> getYcDeliveryOrderByStowage(String stowage) {
		// TODO Auto-generated method stub
		return iYcDeliveryOrderDao.getYcDeliveryOrderByStowage(stowage);
	}
	/**
	 * 修改状态
	 */
	public Integer modOrderByNo(YcDeliveryOrder s) {
		// TODO Auto-generated method stub
		Integer result=iYcDeliveryOrderDao.modOrderByNo(s);
		if(result<1){
			FengUtil.RuntimeExceptionFeng("根据订单号修改订单时出错!");
		}
		return result;
	}
	/**
	 * 修改状态
	 */
	public Integer modOrderByStowageNo(YcDeliveryOrder s) {
		// TODO Auto-generated method stub
		Integer result=iYcDeliveryOrderDao.modOrderByStowageNo(s);
		if(result<1){
			FengUtil.RuntimeExceptionFeng("根据配载单号修改订单时出错!");
		}
		return result;
	}
	public List<YcDeliveryOrder> getYcDeliveryOrderAllListByStatus(Map<String, String> map) {
		// TODO Auto-generated method stub
		return iYcDeliveryOrderDao.getYcDeliveryOrderAllListByStatus(map);
	}
	
	
}
