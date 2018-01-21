package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.IYcDeliveryOrderDao;
import com.yc.Dao.IYcGoodsDao;
import com.yc.Dao.IYcInstorageDao;
import com.yc.Dao.IYcOrderGoodsDao;
import com.yc.Dao.IYcStowageDeliveryDao;
import com.yc.Entity.LcPlatformOrder;
import com.yc.Entity.TempGoodsInfo;
import com.yc.Entity.YcDeliveryOrder;
import com.yc.Entity.YcGoods;
import com.yc.Entity.YcInstorage;
import com.yc.Entity.YcOrderGoods;
import com.yc.Entity.YcStowageDelivery;
import com.yc.Service.IYcDeliveryOrderService;
import com.yc.Service.PlatformOrderService;
import com.yc.Tool.DateUtil;
import com.yc.Tool.FengUtil;
import com.yc.Tool.Pager; 
/** 
* YcDeliveryOrder服务层 
* Auther:FENG 
*/ 
@Service 
public class YcDeliveryOrderServiceImpl implements IYcDeliveryOrderService { 

	@Autowired
	private IYcDeliveryOrderDao iYcDeliveryOrderDao;
	@Autowired
	private IYcGoodsDao iYcGodosDao;
	@Autowired
	private PlatformOrderService platformOrderService;
	@Autowired
	private IYcInstorageDao iYcInstorageDao;
	@Autowired
	private IYcStowageDeliveryDao IYcStowageDeliveryDao;
	@Autowired
	private IYcOrderGoodsDao iYcOrderGoodsDao;
	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	@Override
	public YcDeliveryOrder getSingleInfo(YcDeliveryOrder order) {
		//TODO Auto-generated method stub
		order=iYcDeliveryOrderDao.getSingleInfo(order);
		List<YcGoods> lstYc=iYcGodosDao.getGoodsByDeliveryNo(order.getDeliveryNo());
		order.setGoodsInfo(lstYc);
		return order;
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
	public Pager<YcDeliveryOrder> getListPagerInfo(Pager<YcDeliveryOrder> pager,YcDeliveryOrder ycdeliveryorder) {
		//TODO Auto-generated method stub
		Map<String,Object> map=pager.getElestMap(ycdeliveryorder);
		List<YcDeliveryOrder> lst=new ArrayList<YcDeliveryOrder>();
		//获取货物信息
		for(YcDeliveryOrder ydo:iYcDeliveryOrderDao.getListPagerInfo(map)){
			List<YcOrderGoods> ogList = iYcOrderGoodsDao.getOrderGoodsByDeliveryNo(ydo.getDeliveryNo());
			for(YcOrderGoods og : ogList){
				YcInstorage ins = iYcInstorageDao.getWaybillSource(og.getWayBillNo());
				if(ins.getWaybillSource()==1){
					//线下订单
					ydo.setIsExcepiton(0);//无异常
				}else{
					if(ydo.getWayBillNo()!=null){
						//线上订单是否异常
						LcPlatformOrder p = new LcPlatformOrder();
						p.setWay_bill_number(ydo.getWayBillNo());
						LcPlatformOrder pfo = platformOrderService.getOrder(p);
						if(pfo.getIs_payment()==0){
							ydo.setIsExcepiton(0);
						}else{
							ydo.setIsExcepiton(1);
						}
					}
				}
			}
			List<YcGoods> lstYc=iYcGodosDao.getGoodsByDeliveryNo(ydo.getDeliveryNo());
			ydo.setLstGoods(lstYc);
			lst.add(ydo);
		}
		pager.setObjectList(lst);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	@Override
	public Integer delSingleInfo(YcDeliveryOrder ycdeliveryorder) {
		//TODO Auto-generated method stub
		return iYcDeliveryOrderDao.delSingleInfo(ycdeliveryorder);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	@Override
	public Integer getSumCount(YcDeliveryOrder ycdeliveryorder) {
		//TODO Auto-generated method stub
		return iYcDeliveryOrderDao.getSumCount(ycdeliveryorder);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer modSingleInfo(YcDeliveryOrder ycdeliveryorder) {
		//TODO Auto-generated method stub
		return iYcDeliveryOrderDao.modSingleInfo(ycdeliveryorder);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer addSingleInfo(YcDeliveryOrder ycdeliveryorder) {
		//TODO Auto-generated method stub
		ycdeliveryorder.setOrderStatus(0);
		ycdeliveryorder.setSatisfaction(3);
		ycdeliveryorder.setEvaluateStatus(0);
		//配送单创建人
		ycdeliveryorder.setCreateTime(DateUtil.getDateTimeFormatString());
		iYcDeliveryOrderDao.addCustomer(ycdeliveryorder);
		return iYcDeliveryOrderDao.addSingleInfo(ycdeliveryorder);
	}
	
	
	/**
	 * 删除（修改评价状态）
	 * Author:luojing
	 * 2016年6月16日 上午11:15:24
	 */ 
	@Override
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
	@Override
	public List<YcDeliveryOrder> getYcDeliveryOrderAllList(YcDeliveryOrder deliveryOrder) {
		// TODO Auto-generated method stub、
		
		return iYcDeliveryOrderDao.getYcDeliveryOrderAllList(deliveryOrder);
	}
	@Override
	public List<YcDeliveryOrder> getYcDeliveryOrderByStowage(String stowage) {
		// TODO Auto-generated method stub
		return iYcDeliveryOrderDao.getYcDeliveryOrderByStowage(stowage);
	}
	@Override
	public Integer modOrderByNo(YcDeliveryOrder s) {
		// TODO Auto-generated method stub
		Integer result=iYcDeliveryOrderDao.modOrderByNo(s);
		if(result<1){
			FengUtil.RuntimeExceptionFeng("根据订单号修改订单时出错!");
		}
		return result;
	}
	@Override
	public Pager<YcDeliveryOrder> getOrderCostList(Pager<YcDeliveryOrder> pager, YcDeliveryOrder deliveryOrder,String startTime,String endTime) {
		// TODO Auto-generated method stub
		List<YcDeliveryOrder> list = new ArrayList<YcDeliveryOrder>();
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("dealerId", deliveryOrder.getDealerId());
		map.put("startTime", startTime);
		map.put("endTime",endTime);
		map.put("limitMax", pager.getLimitMax());
		map.put("limitMin", pager.getLimitMin());
		for(YcDeliveryOrder o:iYcDeliveryOrderDao.getOrderCostList(map)){
			o.setCreateTime(DateUtil.getTime2Long(o.getCreateTime()));
			list.add(o);
		}
		pager.setObjectList(list);
		return pager;
	}
	
	@Override
	public Pager<TempGoodsInfo> getPageOutStorageGoods(Pager<TempGoodsInfo> pager, BigInteger dealerId) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("limitMax", pager.getLimitMax());
		map.put("limitMin", pager.getLimitMin());
		map.put("dealerId", dealerId);
		List<TempGoodsInfo> list = new ArrayList<TempGoodsInfo>();
		List<YcStowageDelivery> sdList = IYcStowageDeliveryDao.getListPagerInfo(map);
		if(!sdList.isEmpty()){
			for(YcStowageDelivery ysd:sdList){
				TempGoodsInfo temp = new TempGoodsInfo();
				temp.setDeliveryNo(ysd.getDeliverNo());
				temp.setCreateTime(DateUtil.getTime2Long(ysd.getCreateTime()));
				temp.setGoods(iYcGodosDao.getGoodsByDeliveryNo(ysd.getDeliverNo()));
				list.add(temp);
			}
		}
		pager.setObjectList(list);
		return pager;
	}
	@Override
	public Integer updatePayStatus(YcDeliveryOrder order) {
		// TODO Auto-generated method stub
		return iYcDeliveryOrderDao.updatePayStatus(order);
	}
	
}
