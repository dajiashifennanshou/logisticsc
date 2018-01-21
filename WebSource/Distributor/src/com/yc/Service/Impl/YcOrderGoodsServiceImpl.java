package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Canstant.Constant;
import com.yc.Dao.IYcOrderGoodsDao;
import com.yc.Entity.YcOrderGoods;
import com.yc.Service.IYcOrderGoodsService;
import com.yc.Tool.DateUtil;
import com.yc.Tool.Pager; 
/** 
* YcOrderGoods服务层 
* Auther:FENG 
*/ 
@Service 
public class YcOrderGoodsServiceImpl implements IYcOrderGoodsService { 

	@Autowired
	private IYcOrderGoodsDao iYcOrderGoodsDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	@Override
	public YcOrderGoods getSingleInfo(YcOrderGoods ycordergoods) {
		//TODO Auto-generated method stub
		return iYcOrderGoodsDao.getSingleInfo(ycordergoods);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
	public Pager<YcOrderGoods> getListPagerInfo(Pager<YcOrderGoods> pager,YcOrderGoods ycordergoods) {
		//TODO Auto-generated method stub
		Integer sum=iYcOrderGoodsDao.getSumCount(ycordergoods);
		Map<String,Object> map=pager.getElestMap(ycordergoods);
		pager.setObjectList(iYcOrderGoodsDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	@Override
	public Integer delSingleInfo(YcOrderGoods ycordergoods) {
		//TODO Auto-generated method stub
		return iYcOrderGoodsDao.delSingleInfo(ycordergoods);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	@Override
	public Integer getSumCount(YcOrderGoods ycordergoods) {
		//TODO Auto-generated method stub
		return iYcOrderGoodsDao.getSumCount(ycordergoods);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer modSingleInfo(YcOrderGoods ycordergoods) {
		//TODO Auto-generated method stub
		return iYcOrderGoodsDao.modSingleInfo(ycordergoods);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer addSingleInfo(YcOrderGoods ycordergoods) {
		//TODO Auto-generated method stub
		Integer result=iYcOrderGoodsDao.addSingleInfo(ycordergoods);
		//执行扣减库存
		//待做
		if(result<=0){
			throw new RuntimeException("添加失败..");
		}
		return result;
	}
	@Override
	public List<YcOrderGoods> getOrderGoodsByDeliveryNo(String deliveryNo) {
		// TODO Auto-generated method stub
		return iYcOrderGoodsDao.getOrderGoodsByDeliveryNo(deliveryNo);
	}
}
