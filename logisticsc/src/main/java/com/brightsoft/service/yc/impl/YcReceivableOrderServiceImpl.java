package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brightsoft.dao.yc.IYcDeliveryOrderDao;
import com.brightsoft.dao.yc.IYcReceivableOrderDao;
import com.brightsoft.entity.YcDeliveryOrder;
import com.brightsoft.entity.YcReceivableOrder;
import com.brightsoft.service.yc.IYcReceivableOrderService;
import com.brightsoft.utils.yc.FengRuntimeException;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcReceivableOrder服务层 
* Auther:FENG 
*/ 
@Service 
public class YcReceivableOrderServiceImpl implements IYcReceivableOrderService { 

	@Autowired
	private IYcReceivableOrderDao iYcReceivableOrderDao;

	@Autowired
	private IYcDeliveryOrderDao iYcDeliveryOrderDao;	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcReceivableOrder getSingleInfo(YcReceivableOrder ycreceivableorder) {
		//TODO Auto-generated method stub
		return iYcReceivableOrderDao.getSingleInfo(ycreceivableorder);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcReceivableOrder> getListPagerInfo(Pager<YcReceivableOrder> pager,YcReceivableOrder ycreceivableorder) {
		//TODO Auto-generated method stub
		Integer sum=iYcReceivableOrderDao.getSumCount(ycreceivableorder);
		Map<String,Object> map=pager.getElestMap(ycreceivableorder);
		pager.setObjectList(iYcReceivableOrderDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcReceivableOrder ycreceivableorder) {
		//TODO Auto-generated method stub
		return iYcReceivableOrderDao.delSingleInfo(ycreceivableorder);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcReceivableOrder ycreceivableorder) {
		//TODO Auto-generated method stub
		return iYcReceivableOrderDao.getSumCount(ycreceivableorder);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Transactional
	public Integer modSingleInfo(YcReceivableOrder ycreceivableorder) {
		//TODO Auto-generated method stub
		try{
			/**
			 * 如果未收款.和搁置，那么久将订单完成
			 */
			if(ycreceivableorder.getStatus()!=1){
				YcDeliveryOrder ydo=new YcDeliveryOrder();
				ydo.setDeliveryNo(ycreceivableorder.getOrderNumber());
				//设置未支付状态
				ydo.setOrderStatus(4);
				if(iYcDeliveryOrderDao.modOrderByNo(ydo)<1){
					FengUtil.FengRuntimeException("修改状态时失败//");
				}
			}else{
				//收款了，则为完成状态
				YcDeliveryOrder ydo=new YcDeliveryOrder();
				ydo.setDeliveryNo(ycreceivableorder.getOrderNumber());
				//设置已完成状态
				ydo.setOrderStatus(5);
				if(iYcDeliveryOrderDao.modOrderByNo(ydo)<1){
					FengUtil.FengRuntimeException("修改状态时失败//");
				}
			}
			return iYcReceivableOrderDao.modSingleInfo(ycreceivableorder);
		}catch(FengRuntimeException e){
			FengUtil.TRANEOLLBACK();
		}
		
		return 0;
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Transactional
	public Integer addSingleInfo(YcReceivableOrder ycreceivableorder) {
		try{
			/**
			 * 如果已收款，那么久将订单完成
			 */
			if(ycreceivableorder.getStatus()==1){
				YcDeliveryOrder ydo=new YcDeliveryOrder();
				ydo.setDeliveryNo(ycreceivableorder.getOrderNumber());
				//设置已完成状态
				ydo.setOrderStatus(5);
				if(iYcDeliveryOrderDao.modOrderByNo(ydo)<1){
					FengUtil.FengRuntimeException("修改状态时失败//");
				}
			}else{
				YcDeliveryOrder ydo=new YcDeliveryOrder();
				ydo.setDeliveryNo(ycreceivableorder.getOrderNumber());
				//设置已完成状态
				ydo.setOrderStatus(4);
				if(iYcDeliveryOrderDao.modOrderByNo(ydo)<1){
					FengUtil.FengRuntimeException("修改状态时失败//");
				}
			}
			//TODO Auto-generated method stub
			return iYcReceivableOrderDao.addSingleInfo(ycreceivableorder);
		}catch(FengRuntimeException e){
			FengUtil.TRANEOLLBACK();
		}
		
		return 0;
		
	}
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return iYcReceivableOrderDao.delSelect(list);
	}
}
