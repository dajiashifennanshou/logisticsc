package com.yc.Service.Impl; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.IYcGoodsDao;
import com.yc.Dao.IYcInstallChargeDao;
import com.yc.Entity.YcGoods;
import com.yc.Service.IYcGoodsService;
import com.yc.Tool.DateUtil;
import com.yc.Tool.FengUtil;
import com.yc.Tool.Pager; 
/** 
* YcGoods服务层 
* Auther:FENG 
*/ 
@Service 
public class YcGoodsServiceImpl implements IYcGoodsService { 

	@Autowired
	private IYcGoodsDao iYcGoodsDao;
	@Autowired
	private IYcInstallChargeDao iYcInstallChargeDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	@Override
	public YcGoods getSingleInfo(YcGoods ycgoods) {
		//TODO Auto-generated method stub
		return iYcGoodsDao.getSingleInfo(ycgoods);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
	public Pager<YcGoods> getListPagerInfo(Pager<YcGoods> pager,YcGoods ycgoods) {
		//TODO Auto-generated method stub
		Integer sum=iYcGoodsDao.getSumCount(ycgoods);
		Map<String,Object> map=pager.getElestMap(ycgoods);
		List<YcGoods> lst=new ArrayList<YcGoods>();
		//获取安装费用
		for(YcGoods yg:iYcGoodsDao.getListPagerInfo(map)){
			yg.setInstallCost(iYcInstallChargeDao.getYcSingleCostBy(yg.getGoodsType()).getInstallCharge());
			lst.add(yg);
		}
		pager.setObjectList(lst);
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	@Override
	public Integer delSingleInfo(YcGoods ycgoods) {
		//TODO Auto-generated method stub
		return iYcGoodsDao.delSingleInfo(ycgoods);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	@Override
	public Integer getSumCount(YcGoods ycgoods) {
		//TODO Auto-generated method stub
		return iYcGoodsDao.getSumCount(ycgoods);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer modSingleInfo(YcGoods ycgoods) {
		//TODO Auto-generated method stub
		return iYcGoodsDao.modSingleInfo(ycgoods);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer addSingleInfo(YcGoods ycgoods) {
		//TODO Auto-generated method stub
		return iYcGoodsDao.addSingleInfo(ycgoods);
	}
	@Override
	public List<YcGoods> getGoodsByDeliveryNo(String dNo) {
		// TODO Auto-generated method stub
		List<YcGoods> result=iYcGoodsDao.getGoodsByDeliveryNo(dNo);
		if(result==null){
			//throw new RuntimeException("");
		}
		return result;
	}
	@Override
	public List<YcGoods> getGoodsByDealerId(String dealerId, String branchNo,String outStorageStatus,String goodIds) {
		// TODO Auto-generated method stub
		return iYcGoodsDao.getGoodsByDealerId(dealerId, branchNo,outStorageStatus,goodIds);
	}
	@Override
	public Integer modOutStatusByNo(Integer goodId) {
		// TODO Auto-generated method stub
		Integer result=iYcGoodsDao.modOutStatusByNo(goodId);
		if(result<1){
			FengUtil.RuntimeExceptionFeng("修改货物出库状态时失败!!");
		}
		return result;
	}
	@Override
	public Pager<YcGoods> getPageGoodsByDealerId(Pager<YcGoods> pager,String dealerId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("limitMax", pager.getLimitMax());
		map.put("limitMin", pager.getLimitMin());
		map.put("dealerId", dealerId);
		List<YcGoods> list = new ArrayList<YcGoods>();
		for(YcGoods g:iYcGoodsDao.getPageGoodsByDealerId(map)){
			g.setCreateTime(DateUtil.getTime2Long(g.getCreateTime()));
			list.add(g);
		}
		pager.setObjectList(list);
		return pager;
	}
	
}
