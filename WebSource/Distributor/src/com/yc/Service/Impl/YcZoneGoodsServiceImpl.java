package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.IYcGoodsDao;
import com.yc.Dao.IYcInstorageDao;
import com.yc.Dao.IYcZoneGoodsDao;
import com.yc.Entity.LcPlatformOrder;
import com.yc.Entity.TempGoodsInfo;
import com.yc.Entity.YcInstallCharge;
import com.yc.Entity.YcInstorage;
import com.yc.Entity.YcZoneGoods;
import com.yc.Service.IYcZoneGoodsService;
import com.yc.Service.PlatformOrderService;
import com.yc.Tool.DateUtil;
import com.yc.Tool.Pager; 
/** 
* YcZoneGoods服务层 
* Auther:FENG 
*/ 
@Service 
public class YcZoneGoodsServiceImpl implements IYcZoneGoodsService { 

	@Autowired
	private PlatformOrderService iLcPlatformOrderService;
	@Autowired
	private IYcGoodsDao iYcGoodsDao;
	@Autowired
	private IYcInstorageDao IYcInstorageDao;
	@Override
	public Pager<TempGoodsInfo> getInStorageList(Pager<TempGoodsInfo> pager, BigInteger dealerId) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("limitMax", pager.getLimitMax());
		map.put("limitMin", pager.getLimitMin());
		map.put("dealerId", dealerId);
		List<TempGoodsInfo> inList = new ArrayList<TempGoodsInfo>();
		List<YcInstorage> list = IYcInstorageDao.getListByPage(map);
		for(YcInstorage zg:list){
//			LcPlatformOrder order = new LcPlatformOrder();
//			order.setWay_bill_number(zg.getWaybillNo());
//			if(order!=null){
//				
//			}
//			order=iLcPlatformOrderService.getOrder(order); 
			TempGoodsInfo in = new TempGoodsInfo();
			in.setPhone("4008915256");
			in.setWayBillNo(zg.getWaybillNo());
			in.setCreateTime(DateUtil.getTime2Long(zg.getCreateTime()));
			in.setWaybillSource(zg.getWaybillSource());
			in.setGoods(iYcGoodsDao.getGoodsByWayNo(zg.getWaybillNo()));
//			in.setPhone(zg.getPhone());
//			in.setCreateTime(DateUtil.getTime2Long(zg.getCreateTime()));
//			in.setGoods(iYcGoodsDao.getGoodsInfoByWayBillNo(in.getWayBillNo()));
			inList.add(in);
		}
		pager.setObjectList(inList);
		return pager;
	}

}
