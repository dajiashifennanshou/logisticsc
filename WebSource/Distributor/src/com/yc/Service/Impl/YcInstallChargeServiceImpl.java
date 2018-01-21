package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.IYcGoodsTypeDao;
import com.yc.Dao.IYcInstallChargeDao;
import com.yc.Entity.YcInstallCharge;
import com.yc.Service.IYcInstallChargeService;
import com.yc.Tool.Pager;

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
	

	public List<YcInstallCharge> getYcInstallCostBy(String type,String branchNo) {
		// TODO Auto-generated method stub
		return iYcInstallChargeDao.getYcInstallCostBy(type,branchNo);
	}

	@Override
	public Pager<YcInstallCharge> getListPagerInfo(Pager<YcInstallCharge> pager, YcInstallCharge t) {
		// TODO Auto-generated method stub
		return null;
	}
}
