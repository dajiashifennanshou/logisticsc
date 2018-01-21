package com.brightsoft.service.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.platformBankCityMapper;
import com.brightsoft.model.platformBankCity;

@Service
public class PlatformBankCityServiceImpl {
	
	@Autowired
	private platformBankCityMapper bankCityMapper;
	
	public List<platformBankCity> selectBankProvince(){
		return bankCityMapper.selectBankProvince();
	}
	public List<platformBankCity> selectBankCity(String province){
		return bankCityMapper.selectBankCity(province);
	}
}
