package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.IYcCarManageDao;
import com.brightsoft.dao.yc.IYcCarTypeDao;
import com.brightsoft.dao.yc.IYcEmployeeDao;
import com.brightsoft.entity.YcCarManage;
import com.brightsoft.entity.YcCarType;
import com.brightsoft.entity.YcEmployee;
import com.brightsoft.service.yc.IYcCarManageService;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcCarManage服务层 
* Auther:FENG 
*/ 
@Service 
public class YcCarManageServiceImpl implements IYcCarManageService { 
	@Autowired
	private IYcEmployeeDao iYcEmployeeDao;
	@Autowired
	private IYcCarManageDao iYcCarManageDao ;
	@Autowired
	private IYcCarTypeDao iyCarTypeDao;
	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	public YcCarManage getSingleInfo(YcCarManage yccarmanage) {
		//TODO Auto-generated method stub
		return iYcCarManageDao.getSingleInfo(yccarmanage);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcCarManage> getListPagerInfo(Pager<YcCarManage> pager,YcCarManage yccarmanage) {
		//TODO Auto-generated method stub
		Integer sum=iYcCarManageDao.getSumCount(yccarmanage);
		Map<String,Object> map=pager.getElestMap(yccarmanage);
		//获取司机姓名
		List<YcCarManage> lst=new ArrayList<YcCarManage>();
		for(YcCarManage c:iYcCarManageDao.getListPagerInfo(map)){
			YcCarType yct=new YcCarType();
			yct.setId(c.getCarType());
			yct=iyCarTypeDao.getSingleInfo(yct);
			//yct.setBranchNo(yccarmanage.getBranchNo());
			//司机姓名的集合
			String dName="";
			for(String did:c.getDriverId().split(",")){
				YcEmployee ye=new YcEmployee();
				ye.setId(new BigInteger(did));
				ye.setBranchNo(yccarmanage.getBranchNo());
				ye=iYcEmployeeDao.getSingleInfo(ye);
				if(ye!=null){
					dName+=ye.getEmployeeName()+"-"+ye.getPhone()+"<br/>";
				}
			}
			if(yct!=null){
				c.setCarTypeName(yct.getTypeName());
			}
			c.setDreverName(dName);
			lst.add(c);
		}
		pager.setObjectList(lst);
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcCarManage yccarmanage) {
		//TODO Auto-generated method stub
		return iYcCarManageDao.delSingleInfo(yccarmanage);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcCarManage yccarmanage) {
		//TODO Auto-generated method stub
		return iYcCarManageDao.getSumCount(yccarmanage);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcCarManage yccarmanage) {
		//TODO Auto-generated method stub
		return iYcCarManageDao.modSingleInfo(yccarmanage);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	
	public Integer addSingleInfo(YcCarManage yccarmanage) {
		//TODO Auto-generated method stub
		return iYcCarManageDao.addSingleInfo(yccarmanage);
	}
	
	/**
	 * 多条删除
	 * Author:luojing
	 * 2016年6月17日 上午9:46:04
	 */
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return iYcCarManageDao.delSelect(list);
	}
	
	public List<YcCarManage> getYcCarAllList(YcCarManage car) {
		//获取司机姓名
		List<YcCarManage> lst=new ArrayList<YcCarManage>();
		for(YcCarManage c:iYcCarManageDao.getYcCarAllList(car)){
			//司机姓名的集合
			String lName="";
			for(String did:c.getDriverId().split(",")){
				YcEmployee ye=new YcEmployee();
				ye.setId(new BigInteger(did));
				ye.setBranchNo(car.getBranchNo());
				ye=iYcEmployeeDao.getSingleInfo(ye);
				if(ye!=null){
					lName+=ye.getEmployeeName()+"-"+ye.getPhone()+"<br/>";
				}
			}
			c.setDreverName(lName);
			lst.add(c);
		}
		// TODO Auto-generated method stub
		return lst;
	}
	
	public Integer modCarByNo(YcCarManage ycm) {
		// TODO Auto-generated method stub
		return iYcCarManageDao.modCarByNo(ycm);
	}
	
	public Integer modCarStatusByStowage(Integer s, String sNo) {
		// TODO Auto-generated method stub
		Integer result=iYcCarManageDao.modCarStatusByStowage(s, sNo);
		if(result<1){
			FengUtil.RuntimeExceptionFeng("修改车辆状态时失败");
		}
		return result;
	}
}
