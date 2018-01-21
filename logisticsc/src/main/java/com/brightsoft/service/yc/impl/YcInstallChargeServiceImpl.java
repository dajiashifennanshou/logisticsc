package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.IYcGoodsTypeDao;
import com.brightsoft.dao.yc.IYcInstallChargeDao;
import com.brightsoft.entity.YcGoodsType;
import com.brightsoft.entity.YcInstallCharge;
import com.brightsoft.service.yc.IYcInstallChargeService;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcInstallCharge服务层 
* Auther:FENG 
*/ 
@Service 
public class YcInstallChargeServiceImpl implements IYcInstallChargeService { 

	@Autowired
	private IYcInstallChargeDao iYcInstallChargeDao;

	@Autowired
	private IYcGoodsTypeDao iYcGoodsTypeDao;
	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcInstallCharge getSingleInfo(YcInstallCharge ycinstallcharge) {
		//TODO Auto-generated method stub
		ycinstallcharge=iYcInstallChargeDao.getSingleInfo(ycinstallcharge);
		if(ycinstallcharge==null){
			throw new RuntimeException("未获取到数据");
		}
		return ycinstallcharge;
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcInstallCharge> getListPagerInfo(Pager<YcInstallCharge> pager,YcInstallCharge ycinstallcharge) {
		//TODO Auto-generated method stub
		Integer sum=iYcInstallChargeDao.getSumCount(ycinstallcharge);
		Map<String,Object> map=pager.getElestMap(ycinstallcharge);
		List<YcInstallCharge> lst=new ArrayList<YcInstallCharge>();
		for(YcInstallCharge yc:iYcInstallChargeDao.getListPagerInfo(map)){
			YcGoodsType yt=new YcGoodsType();
//			yt.setId(yc.getFirstLvCode());
//			yt=iYcGoodsTypeDao.getSingleInfo(yt);
//			if(yt!=null){
//				yc.setFirstLvName(yt.getSoftName());
//			}
			yt.setId(yc.getTwoLvCode());
			yt=iYcGoodsTypeDao.getSingleInfo(yt);
			if(yt!=null){
				yc.setTowLvName(yt.getSoftName());
			}
			lst.add(yc);
		}
		pager.setObjectList(lst);
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcInstallCharge ycinstallcharge) {
		//TODO Auto-generated method stub
		return iYcInstallChargeDao.delSingleInfo(ycinstallcharge);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcInstallCharge ycinstallcharge) {
		//TODO Auto-generated method stub
		return iYcInstallChargeDao.getSumCount(ycinstallcharge);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcInstallCharge ycinstallcharge) {
		//TODO Auto-generated method stub
		return iYcInstallChargeDao.modSingleInfo(ycinstallcharge);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	
	public Integer addSingleInfo(YcInstallCharge ycinstallcharge) {
		//TODO Auto-generated method stub
	
		Integer result=iYcInstallChargeDao.addSingleInfo(ycinstallcharge);
		if(result<0){
			throw new RuntimeException("添加失败");
		}
		return result;
	}
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return iYcInstallChargeDao.delSelect(list);
	}
	
	public List<YcInstallCharge> getYcInstallCostBy(String type,String branchNo) {
		// TODO Auto-generated method stub
		return iYcInstallChargeDao.getYcInstallCostBy(type,branchNo);
	}
}
