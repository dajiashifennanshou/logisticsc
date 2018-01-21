package com.brightsoft.service.system;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.system.sysBankMapper;
import com.brightsoft.model.sysBank;

@Service
public class sysBankServiceImpl {
	
	@Autowired
	private sysBankMapper bankMapper;
	
	public boolean bangding(sysBank bank){
		bank.setBankTime(new Date());
		if(null == bank.getId() || bank.getId() <= 0){
			if(bankMapper.insertSelective(bank)>0){
				return true;
			}
		}else{
			if(bankMapper.updateByPrimaryKeySelective(bank)>0){
				return true;
			}
		}
		return false;
	}
	
	public sysBank selectBankSysUserId(Long sysUserId){
		sysBank bank =null;
		bank = bankMapper.selectSysuserId(sysUserId);
		if(null == bank){
			return null;
		}else{
			return bank;
		}
	}
}
