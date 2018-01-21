package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.IYcInGoodsDao;
import com.yc.Entity.YcInGoods;
import com.yc.Service.IYcInGoodsService;
import com.yc.Tool.Pager; 
/** 
* YcInGoods服务层 
* Auther:FENG 
*/ 
@Service 
public class YcInGoodsServiceImpl implements IYcInGoodsService { 

	@Autowired
	private IYcInGoodsDao iYcInGoodsDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	@Override
	public YcInGoods getSingleInfo(YcInGoods ycingoods) {
		//TODO Auto-generated method stub
		return iYcInGoodsDao.getSingleInfo(ycingoods);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
	public Pager<YcInGoods> getListPagerInfo(Pager<YcInGoods> pager,YcInGoods ycingoods) {
		//TODO Auto-generated method stub
		Integer sum=iYcInGoodsDao.getSumCount(ycingoods);
		Map<String,Object> map=pager.getElestMap(ycingoods);
		pager.setObjectList(iYcInGoodsDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	@Override
	public Integer delSingleInfo(YcInGoods ycingoods) {
		//TODO Auto-generated method stub
		return iYcInGoodsDao.delSingleInfo(ycingoods);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	@Override
	public Integer getSumCount(YcInGoods ycingoods) {
		//TODO Auto-generated method stub
		return iYcInGoodsDao.getSumCount(ycingoods);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer modSingleInfo(YcInGoods ycingoods) {
		//TODO Auto-generated method stub
		return iYcInGoodsDao.modSingleInfo(ycingoods);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer addSingleInfo(YcInGoods ycingoods) {
		//TODO Auto-generated method stub
		return iYcInGoodsDao.addSingleInfo(ycingoods);
	}
}
