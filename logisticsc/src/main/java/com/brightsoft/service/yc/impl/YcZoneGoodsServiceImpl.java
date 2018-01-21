package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.IYcGoodsDao;
import com.brightsoft.dao.yc.IYcZoneGoodsDao;
import com.brightsoft.entity.YcGoods;
import com.brightsoft.entity.YcGoodsStatistics;
import com.brightsoft.entity.YcZoneGoods;
import com.brightsoft.service.yc.IYcZoneGoodsService;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager; 

/** 
* YcZoneGoods服务层 
* Auther:FENG 
*/ 
@Service 
public class YcZoneGoodsServiceImpl implements IYcZoneGoodsService { 

	@Autowired
	private IYcZoneGoodsDao iYcZoneGoodsDao;

	@Autowired
	private IYcGoodsDao iYcGoodsDao;
	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcZoneGoods getSingleInfo(YcZoneGoods yczonegoods) {
		//TODO Auto-generated method stub
		return iYcZoneGoodsDao.getSingleInfo(yczonegoods);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcZoneGoods> getListPagerInfo(Pager<YcZoneGoods> pager,YcZoneGoods yczonegoods) {
		//TODO Auto-generated method stub
		Integer sum=iYcZoneGoodsDao.getSumCount(yczonegoods);
		Map<String,Object> map=pager.getElestMap(yczonegoods);
		List<YcZoneGoods> lst=new ArrayList<YcZoneGoods>();
		YcGoods yg=new YcGoods();
		for(YcZoneGoods yzg:iYcZoneGoodsDao.getListPagerInfo(map)){
//			yg.setWayBillNo(yzg.getWayBillNo());
//			List<YcGoods> lstg=iYcGoodsDao.getGoodsByElse(yg);
//			yzg.setGoodsLst(lstg);
			lst.add(yzg);
		}
		pager.setObjectList(lst);
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcZoneGoods yczonegoods) {
		//TODO Auto-generated method stub
		return iYcZoneGoodsDao.delSingleInfo(yczonegoods);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcZoneGoods yczonegoods) {
		//TODO Auto-generated method stub
		return iYcZoneGoodsDao.getSumCount(yczonegoods);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcZoneGoods yczonegoods) {
		//TODO Auto-generated method stub
		return iYcZoneGoodsDao.modSingleInfo(yczonegoods);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	
	public Integer addSingleInfo(YcZoneGoods yczonegoods) {
		//TODO Auto-generated method stub
		return iYcZoneGoodsDao.addSingleInfo(yczonegoods);
	}
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return iYcZoneGoodsDao.delSelect(list);
	}
	public Integer reSetGoodsSum(String branchNo, String goodsId, String dealerId) {
		// TODO Auto-generated method stub
		return iYcZoneGoodsDao.reSetGoodsSum(branchNo, goodsId, dealerId);
	}
	
	
	public Pager<YcGoodsStatistics> getGoodsStatistics(Pager<YcGoodsStatistics> pager, YcGoodsStatistics gs) {
		// TODO Auto-generated method stub
		Integer count=iYcZoneGoodsDao.getGoodsStatisticsCount(gs);
		Map<String,Object> map=pager.getElestMap(gs);
		pager.setObjectList(iYcZoneGoodsDao.getGoodsStatistics(map));
		pager.setRecordCount(count);
		return pager;
	}
	
	public Integer getGoodsStatisticsSumCount(YcGoodsStatistics gs) {
		// TODO Auto-generated method stub
		Integer i=0;
		try{
			i = iYcZoneGoodsDao.getGoodsStatisticsSumCount(gs);
			if(i==null){i=0;}
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	public Integer modWayBillStatusByNo(String wayBillNo) {
		// TODO Auto-generated method stub
		Integer result=iYcZoneGoodsDao.modWayBillStatusByNo(wayBillNo);
		if(result<=0){
			FengUtil.RuntimeExceptionFeng("修改储货表出库状态时异常!");
		}
		return result;
	}
}
