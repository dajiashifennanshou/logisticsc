package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.Constant.Constant;
import com.brightsoft.dao.yc.IYcEmployeeDao;
import com.brightsoft.dao.yc.IYcStowageDeliveryDao;
import com.brightsoft.entity.YcEmployee;
import com.brightsoft.entity.YcStowageDelivery;
import com.brightsoft.service.yc.IYcStowageDeliveryService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.Pager;

/** 
* YcStowageDelivery服务层 
* Auther:FENG 
*/ 
@Service 
public class YcStowageDeliveryServiceImpl implements IYcStowageDeliveryService { 

	@Autowired
	private IYcStowageDeliveryDao iYcStowageDeliveryDao;
	
	@Autowired
	private IYcEmployeeDao iYcEmployeeDao ;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcStowageDelivery getSingleInfo(YcStowageDelivery ycstowagedelivery) {
		//TODO Auto-generated method stub
		return iYcStowageDeliveryDao.getSingleInfo(ycstowagedelivery);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcStowageDelivery> getListPagerInfo(Pager<YcStowageDelivery> pager,YcStowageDelivery ycstowagedelivery) {
		//TODO Auto-generated method stub
		Integer sum=iYcStowageDeliveryDao.getSumCount(ycstowagedelivery);
		Map<String,Object> map=pager.getElestMap(ycstowagedelivery);
		pager.setObjectList(iYcStowageDeliveryDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcStowageDelivery ycstowagedelivery) {
		//TODO Auto-generated method stub
		return iYcStowageDeliveryDao.delSingleInfo(ycstowagedelivery);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcStowageDelivery ycstowagedelivery) {
		//TODO Auto-generated method stub
		return iYcStowageDeliveryDao.getSumCount(ycstowagedelivery);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcStowageDelivery ycstowagedelivery) {
		//TODO Auto-generated method stub
		return iYcStowageDeliveryDao.modSingleInfo(ycstowagedelivery);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	
	public Integer addSingleInfo(YcStowageDelivery ycstowagedelivery) {
		//TODO Auto-generated method stub
		//设置创建时间创建人
		ycstowagedelivery.setCreateTime(DateUtil.getDateTimeFormatString());
		ycstowagedelivery.setCreateUser(Constant.SYSTEM_OPERSTION_PERSON);
		//修改安装工的状态为工作中
		Integer inr=0;
		Integer index=0;
		for(String id:ycstowagedelivery.getEmployeeId().split(",")){
			index+=1;
			YcEmployee ye=new YcEmployee();
			ye.setId(new BigInteger(id));
			ye.setUseStatus(1);
			ye.setUpdateTime(DateUtil.getDateTimeFormatString());
			ye.setUpdateUser(Constant.SYSTEM_OPERSTION_PERSON);
			inr+=iYcEmployeeDao.modSingleInfo(ye);
		}
		//如果修改数量不匹配
		if(inr<index){
			throw new RuntimeException("修改安装工状态时失败..");
		}
		Integer result=iYcStowageDeliveryDao.addSingleInfo(ycstowagedelivery);
		if(result<=0){
			throw new RuntimeException("添加出现异常..");
		}
		return  result;
	}
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return iYcStowageDeliveryDao.delSelect(list);
	}
	
	public List<YcStowageDelivery> getYcDeliveryOrderAllList(YcStowageDelivery yc) {
		// TODO Auto-generated method stub
		return iYcStowageDeliveryDao.getYcDeliveryOrderAllList(yc);
	}
}
