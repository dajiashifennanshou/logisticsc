package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.IYcGoodsDao;
import com.brightsoft.entity.YcGoods;
import com.brightsoft.service.yc.IYcGoodsService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcGoods服务层 
* Auther:FENG 
*/ 
@Service 
public class YcGoodsServiceImpl implements IYcGoodsService { 

	@Autowired
	private IYcGoodsDao iYcGoodsDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcGoods getSingleInfo(YcGoods ycgoods) {
		//TODO Auto-generated method stub
		return iYcGoodsDao.getSingleInfo(ycgoods);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcGoods> getListPagerInfo(Pager<YcGoods> pager,YcGoods ycgoods) {
		//TODO Auto-generated method stub
		Integer sum=iYcGoodsDao.getSumCount(ycgoods);
		Map<String,Object> map=pager.getElestMap(ycgoods);
		pager.setObjectList(iYcGoodsDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcGoods ycgoods) {
		//TODO Auto-generated method stub
		return iYcGoodsDao.delSingleInfo(ycgoods);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcGoods ycgoods) {
		//TODO Auto-generated method stub
		return iYcGoodsDao.getSumCount(ycgoods);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcGoods ycgoods) {
		//TODO Auto-generated method stub
		return iYcGoodsDao.modSingleInfo(ycgoods);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	
	public Integer addSingleInfo(YcGoods ycgoods) {
		//TODO Auto-generated method stub
		return iYcGoodsDao.addSingleInfo(ycgoods);
	}
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<YcGoods> getGoodsByDeliveryNo(String dNo) {
		// TODO Auto-generated method stub
		List<YcGoods> result=iYcGoodsDao.getGoodsByDeliveryNo(dNo);
		if(result==null){
			//throw new RuntimeException("");
		}
		return result;
	}
	
	public List<YcGoods> getGoodsByDealerId(String deliveId, String branchNo,String outStorageStatus,String goodIds) {
		// TODO Auto-generated method stub
		return iYcGoodsDao.getGoodsByDealerId(deliveId, branchNo,outStorageStatus,goodIds);
	}
	
	public Integer modOutStatusByNo(Integer goodId) {
		// TODO Auto-generated method stub
		Integer result=iYcGoodsDao.modOutStatusByNo(goodId);
		if(result<1){
			FengUtil.RuntimeExceptionFeng("修改货物出库状态时失败!!");
		}
		return result;
	}
	
	public List<YcGoods> getAllGoodsByDealerId(String deliveId, String branchNo) {
		// TODO Auto-generated method stub
		return iYcGoodsDao.getAllGoodsByDealerId(deliveId, branchNo);
	}
	public Pager<YcGoods> getYcGoodsListByDealerId(Pager<YcGoods> pager, YcGoods t) {
		Integer sum=iYcGoodsDao.getSumCountByDealer(t);
		Map<String,Object> map=pager.getElestMap(t);
		List<YcGoods> lst=new ArrayList<YcGoods>();
		for(YcGoods yg:iYcGoodsDao.getYcGoodsListByDealerId(map)){
			yg.setCreateTime(DateUtil.getTime2Long(yg.getCreateTime())+"");
			lst.add(yg);
		}
		pager.setObjectList(lst);
		pager.setRecordCount(sum);
		return pager;
	}
	
}
