package com.brightsoft.service.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.platformBankNameMapper;
import com.brightsoft.model.platformBankName;
/**
 * 查询开户行
 * @Author:luojing
 * @2016年7月19日 下午7:30:39
 */
@Service
public class platformBankNameServiceImpl {
	
	@Autowired
	private platformBankNameMapper bankNameMapper;
	
	public List<platformBankName> selectProvinceName(){
		return bankNameMapper.selectProvinceName();
	}
	
	public List<platformBankName> selectCityName(String provinceName){
		return bankNameMapper.selectCityName(provinceName);
	}
	
	public List<platformBankName> selectHeadName(String provinceName,String cityName){
		return bankNameMapper.selectHeadName(provinceName, cityName);
	}
	
	public List<platformBankName> selectBranchName(String headName,String provinceName,String cityName){
		return bankNameMapper.selectBranchName(headName,provinceName,cityName);
	}
}
