package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.IYcOutGoodsDao;
import com.yc.Entity.YcOutGoods;
import com.yc.Service.IYcOutGoodsService;
import com.yc.Tool.Pager;

/** 
* YcOutGoods服务层 
* Auther:FENG 
*/ 
@Service 
public class YcOutGoodsServiceImpl implements IYcOutGoodsService { 

	@Autowired
	private IYcOutGoodsDao iYcOutGoodsDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcOutGoods getSingleInfo(YcOutGoods ycoutgoods) {
		//TODO Auto-generated method stub
		return iYcOutGoodsDao.getSingleInfo(ycoutgoods);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcOutGoods> getListPagerInfo(Pager<YcOutGoods> pager,YcOutGoods ycoutgoods) {
		//TODO Auto-generated method stub
		Integer sum=iYcOutGoodsDao.getSumCount(ycoutgoods);
		Map<String,Object> map=pager.getElestMap(ycoutgoods);
		pager.setObjectList(iYcOutGoodsDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcOutGoods ycoutgoods) {
		//TODO Auto-generated method stub
		return iYcOutGoodsDao.delSingleInfo(ycoutgoods);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcOutGoods ycoutgoods) {
		//TODO Auto-generated method stub
		return iYcOutGoodsDao.getSumCount(ycoutgoods);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcOutGoods ycoutgoods) {
		//TODO Auto-generated method stub
		return iYcOutGoodsDao.modSingleInfo(ycoutgoods);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	
	public Integer addSingleInfo(YcOutGoods ycoutgoods) {
		//TODO Auto-generated method stub
		return iYcOutGoodsDao.addSingleInfo(ycoutgoods);
	}
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return null;
	}
}
