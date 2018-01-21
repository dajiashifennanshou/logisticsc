package com.yc.Service.Impl; 
import java.math.BigInteger;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Canstant.enums.ApplyStateEnum;
import com.yc.Canstant.enums.ApplyTypeEnum;
import com.yc.Dao.PlatformUserApplyDao;
import com.yc.Dao.PlatformUserDao;
import com.yc.Dao.PlatformUserTemporaryCompanyDao;
import com.yc.Entity.PlatformUser;
import com.yc.Entity.PlatformUserApply;
import com.yc.Entity.PlatformUserTemporaryCompany;
import com.yc.Service.PlatformUserTemporaryCompanyService;
import com.yc.Tool.DateUtil; 
@Service 
public class PlatformUserTemporaryCompanyServiceImpl implements PlatformUserTemporaryCompanyService { 

	@Autowired
	private PlatformUserTemporaryCompanyDao tmpCompanyDao;
	@Autowired
	private PlatformUserDao userDao;
	@Autowired
	private PlatformUserApplyDao userApplyDao;

	@Override
	public boolean addTempCompan(BigInteger userId,PlatformUserTemporaryCompany company) {
		// TODO Auto-generated method stub
		if(null == company.getId()){
			//组织代码
			Random random = new Random();
			int code = random.nextInt(89999);
			String codeStr=String.valueOf(code+10000);
			company.setCompany_code(codeStr);
		}
		//添加公司临时信息
		if(tmpCompanyDao.addSingleInfo(company)>0){
			PlatformUser user = new PlatformUser();
			user.setId(userId);//用户ID
			user.setTemporary_company_id(company.getId());//临时公司ID
			if(userDao.modSingleInfo(user)>0){//修改用户临时公司ID
				PlatformUserApply apply = new PlatformUserApply();
				apply.setApply_name(ApplyTypeEnum.ENTERPRISEOWNER.getName());
				apply.setApply_type(ApplyTypeEnum.ENTERPRISEOWNER.getValue());
				apply.setApply_state(ApplyStateEnum.APPLYING.getValue());
				apply.setUser_id(userId);
				apply.setTime(DateUtil.getDateTimeFormatString());
				if(userApplyDao.addSingleInfo(apply)>0){
					//修改版本
					PlatformUserApply a = new PlatformUserApply();
					a.setVersion(apply.getId());
					a.setId(apply.getId());
					userApplyDao.modSingleInfo(a);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public PlatformUserTemporaryCompany getUserTemporaryCompany(PlatformUserTemporaryCompany company) {
		// TODO Auto-generated method stub
		return tmpCompanyDao.getSingleInfo(company);
	}
}
