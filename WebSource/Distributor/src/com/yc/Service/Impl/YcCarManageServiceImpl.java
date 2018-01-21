package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.IYcCarManageDao;
import com.yc.Dao.IYcEmployeeDao;
import com.yc.Entity.YcCarManage;
import com.yc.Entity.YcEmployee;
import com.yc.Service.IYcCarManageService;
import com.yc.Tool.FengUtil;
import com.yc.Tool.Pager; 
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
	
	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	@Override
	public YcCarManage getSingleInfo(YcCarManage yccarmanage) {
		//TODO Auto-generated method stub
		return iYcCarManageDao.getSingleInfo(yccarmanage);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
	public Pager<YcCarManage> getListPagerInfo(Pager<YcCarManage> pager,YcCarManage yccarmanage) {
		//TODO Auto-generated method stub
		Map<String,Object> map=pager.getElestMap(yccarmanage);
		pager.setObjectList(iYcCarManageDao.getListPagerInfo(map));
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	@Override
	public Integer delSingleInfo(YcCarManage yccarmanage) {
		//TODO Auto-generated method stub
		return iYcCarManageDao.delSingleInfo(yccarmanage);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	@Override
	public Integer getSumCount(YcCarManage yccarmanage) {
		//TODO Auto-generated method stub
		return iYcCarManageDao.getSumCount(yccarmanage);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer modSingleInfo(YcCarManage yccarmanage) {
		//TODO Auto-generated method stub
		return iYcCarManageDao.modSingleInfo(yccarmanage);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer addSingleInfo(YcCarManage yccarmanage) {
		//TODO Auto-generated method stub
		return iYcCarManageDao.addSingleInfo(yccarmanage);
	}
	
	@Override
	public List<YcCarManage> getYcCarAllList(YcCarManage car) {
		//获取司机姓名
		List<YcCarManage> lst=new ArrayList<YcCarManage>();
		for(YcCarManage c:iYcCarManageDao.getYcCarAllList(car)){
			//司机姓名的集合
			List<String> lName=new ArrayList<>();
			for(String did:c.getDriverId().split(",")){
				YcEmployee ye=new YcEmployee();
				ye.setId(new BigInteger(did));
				ye=iYcEmployeeDao.getSingleInfo(ye);
				lName.add(ye.getEmployeeName());
			}
			c.setDreverName(lName.toString());
			lst.add(c);
		}
		// TODO Auto-generated method stub
		return lst;
	}
	@Override
	public Integer modCarByNo(YcCarManage ycm) {
		// TODO Auto-generated method stub
		return iYcCarManageDao.modCarByNo(ycm);
	}
	@Override
	public Integer modCarStatusByStowage(Integer s, String sNo) {
		// TODO Auto-generated method stub
		Integer result=iYcCarManageDao.modCarStatusByStowage(s, sNo);
		if(result<1){
			FengUtil.RuntimeExceptionFeng("修改车辆状态时失败");
		}
		return result;
	}
}
