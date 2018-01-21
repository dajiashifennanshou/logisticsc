package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.IYcStorageBranchDao;
import com.brightsoft.entity.YcStorageBranch;
import com.brightsoft.service.yc.IYcStorageBranchService;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcStorageBranch服务层 
* * Auther:luojing 
*/ 
@Service 
public class YcStorageBranchServiceImpl implements IYcStorageBranchService { 

	@Autowired
	private IYcStorageBranchDao iYcStorageBranchDao;

	/** 
	* 获取单页记录 
	* luojing 
	*/ 
	
	public YcStorageBranch getSingleInfo(YcStorageBranch ycstoragebranch) {
		//TODO Auto-generated method stub
		return iYcStorageBranchDao.getSingleInfo(ycstoragebranch);
	}
	/** 
	* 获取分页记录 
	* luojing 
	*/ 
	
	public Pager<YcStorageBranch> getListPagerInfo(Pager<YcStorageBranch> pager,YcStorageBranch ycstoragebranch) {
		//TODO Auto-generated method stub
		Integer sum=iYcStorageBranchDao.getSumCount(ycstoragebranch);
		Map<String,Object> map=pager.getElestMap(ycstoragebranch);
		pager.setObjectList(iYcStorageBranchDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* luojing 
	*/ 
	
	public Integer delSingleInfo(YcStorageBranch ycstoragebranch) {
		//TODO Auto-generated method stub
		return iYcStorageBranchDao.delSingleInfo(ycstoragebranch);
	}
	/** 
	* 获取总记录数 
	* luojing 
	*/ 
	
	public Integer getSumCount(YcStorageBranch ycstoragebranch) {
		//TODO Auto-generated method stub
		return iYcStorageBranchDao.getSumCount(ycstoragebranch);
	}
	/** 
	* 修改信息 
	* luojing 
	*/ 
	
	public Integer modSingleInfo(YcStorageBranch ycstoragebranch) {
		//TODO Auto-generated method stub
		return iYcStorageBranchDao.modSingleInfo(ycstoragebranch);
	}
	/** 
	* 添加信息 
	* luojing 
	*/ 
	
	public Integer addSingleInfo(YcStorageBranch ycstoragebranch) {
		//TODO Auto-generated method stub
		return iYcStorageBranchDao.addSingleInfo(ycstoragebranch);
	}
	
	/**
	 * 删除多条
	 * Author:luojing
	 * 2016年6月13日 上午11:36:52
	 */
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		return iYcStorageBranchDao.delSelect(list);
	}
	
	/**
	 * 集合查询云仓编号
	 * Author:luojing
	 * 2016年6月14日 下午1:38:57
	 */
	
	public List<YcStorageBranch> getBranchNoList() {
		// TODO Auto-generated method stub
		try{
			List<YcStorageBranch> list = iYcStorageBranchDao.getBranchNoList();
			if(!list.isEmpty()){
				return list;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
