package com.brightsoft.service.platform;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.platformBondConfigureMapper;
import com.brightsoft.model.platformBondConfigure;

@Service
public class platformBondConfigureServiceImpl {
	
	@Autowired
	private platformBondConfigureMapper bondConfigureMapper;
	
	
	public platformBondConfigure selectBond(){
		return bondConfigureMapper.selectBond();
	}
	
	public boolean updateBond(platformBondConfigure bondConfigure){
		bondConfigure.setTime(new Date());
		if(null== bondConfigure.getId() ||bondConfigure.getId() < 0){
			if(bondConfigureMapper.insertSelective(bondConfigure) > 0){
				return true;
			}
		}else{
			if(bondConfigureMapper.updateByPrimaryKeySelective(bondConfigure)>0){
				return true;
			}
		}
		return false;
	}
}
