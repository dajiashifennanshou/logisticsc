package com.brightsoft.service.yc.impl; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.IYcPostDao;
import com.brightsoft.entity.YcPost;
import com.brightsoft.service.yc.IYcPostService;
import com.brightsoft.utils.yc.Pager; 
/** 
* YcPost服务层 
* Auther:FENG 
*/ 
@Service 
public class YcPostServiceImpl implements IYcPostService { 

	@Autowired
	private IYcPostDao iYcPostDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	
	public YcPost getSingleInfo(YcPost ycpost) {
		//TODO Auto-generated method stub
		return iYcPostDao.getSingleInfo(ycpost);
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	
	public Pager<YcPost> getListPagerInfo(Pager<YcPost> pager,YcPost ycpost) {
		//TODO Auto-generated method stub
		Integer sum=iYcPostDao.getSumCount(ycpost);
		Map<String,Object> map=pager.getElestMap(ycpost);
		pager.setObjectList(iYcPostDao.getListPagerInfo(map));
		pager.setRecordCount(sum);
		return pager;
	}
	/** 
	* 删除记录 
	* Auther:FENG 
	*/ 
	
	public Integer delSingleInfo(YcPost ycpost) {
		//TODO Auto-generated method stub
		return iYcPostDao.delSingleInfo(ycpost);
	}
	/** 
	* 获取总记录数 
	* Auther:FENG 
	*/ 
	
	public Integer getSumCount(YcPost ycpost) {
		//TODO Auto-generated method stub
		return iYcPostDao.getSumCount(ycpost);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	
	public Integer modSingleInfo(YcPost ycpost) {
		//TODO Auto-generated method stub
		return iYcPostDao.modSingleInfo(ycpost);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	
	public Integer addSingleInfo(YcPost ycpost) {
		//TODO Auto-generated method stub
		return iYcPostDao.addSingleInfo(ycpost);
	}
	
	public Integer delSelect(List<BigInteger> list) {
		// TODO Auto-generated method stub
		try{
			if(!list.isEmpty()){
				return iYcPostDao.delSelect(list);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 职位集合查询
	 * Author:luojing
	 * 2016年6月16日 下午4:16:25
	 */
	
	public List<YcPost> getPost(YcPost post) {
		// TODO Auto-generated method stub
		try{
			List<YcPost> list = iYcPostDao.getPost(post);
			if(!list.isEmpty()){
				return list;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
