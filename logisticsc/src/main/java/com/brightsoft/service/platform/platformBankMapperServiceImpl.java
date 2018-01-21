package com.brightsoft.service.platform;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.platformBankMapper;
import com.brightsoft.model.platformBank;

@Service
public class platformBankMapperServiceImpl {

	@Autowired
	private platformBankMapper bankMapper;
	
	public boolean binding(platformBank bank){
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
	
	public platformBank selectBankUserId(Long userId){
		platformBank bank = null;
		bank = bankMapper.selectBankUserId(userId);
		if(null == bank){
			return  null;
		}else{
			return bank;
		}
	}
}
