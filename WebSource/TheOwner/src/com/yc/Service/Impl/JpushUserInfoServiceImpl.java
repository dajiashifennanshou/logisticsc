package com.yc.Service.Impl; 
import com.yc.Entity.JpushUserInfo; 
import com.yc.Tool.Pager; 
import com.yc.Dao.JpushUserInfoDao; 
import com.yc.Service.JpushUserInfoService; 
import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired; 
import java.util.Map; 
/** 
* JpushUserInfo服务层 
* Auther:FENG 
*/ 
@Service 
public class JpushUserInfoServiceImpl implements JpushUserInfoService { 

	@Autowired
	private JpushUserInfoDao iJpushUserInfoDao;

	/** 
	* 获取单页记录 
	* Auther:FENG 
	*/ 
	@Override
	public JpushUserInfo getSingleInfo(JpushUserInfo jpushuserinfo) {
		//TODO Auto-generated method stub
		return iJpushUserInfoDao.getSingleInfo(jpushuserinfo);
	}
	/** 
	* 修改信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer modSingleInfo(JpushUserInfo jpushuserinfo) {
		//TODO Auto-generated method stub
		return iJpushUserInfoDao.modSingleInfo(jpushuserinfo);
	}
	/** 
	* 添加信息 
	* Auther:FENG 
	*/ 
	@Override
	public Integer addSingleInfo(JpushUserInfo jpushuserinfo) {
		//TODO Auto-generated method stub
		return iJpushUserInfoDao.addSingleInfo(jpushuserinfo);
	}
}
