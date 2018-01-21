package com.brightsoft.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.system.sysBankAccoutMapper;
import com.brightsoft.model.sysBankAccout;

@Service
public class sysBankAccoutServiceImpl {
	
	@Autowired
	private sysBankAccoutMapper accoutMapper;
	
	public sysBankAccout selectSysUserId(Long sysUserId){
		sysBankAccout accout = null;
		accout = accoutMapper.selectSysUserId(sysUserId);
		if(null == accout){
			return null;
		}else{
			return accout;
		}
	}
	
	public boolean inserBankAccount(sysBankAccout accout){
		if(accoutMapper.insertSelective(accout)>0){
			return true;
		}
		return false;
	}
	
	public boolean updateBankAccount(sysBankAccout accout){
		if(accoutMapper.updateByPrimaryKeySelective(accout)>0){
			return true;
		}
		return false;
	}
	
	public sysBankAccout selectSysBank(){
		return accoutMapper.selectSysBank();
	}
}
