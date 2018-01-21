package com.brightsoft.service.platform;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatfoemBankSplitConfigureMapper;
import com.brightsoft.model.PlatfoemBankSplitConfigure;
import com.sun.tools.doclets.internal.toolkit.resources.doclets;

@Service
public class PlatfoemBankSplitConfigureServiceImpl {
	
	@Autowired
	private PlatfoemBankSplitConfigureMapper mapper;
	
	public PlatfoemBankSplitConfigure selectPlatfoemBankSplitConfigure(PlatfoemBankSplitConfigure bankSplitConfigure){
		return mapper.selectSplitConfigure(bankSplitConfigure);
	}
	
	public boolean updateSplitConfigur(PlatfoemBankSplitConfigure bankSplitConfigure){
		bankSplitConfigure.setTime(new Date());
		if(null ==  bankSplitConfigure.getId() || bankSplitConfigure.getId() < 0){
			if(mapper.insertSelective(bankSplitConfigure)>0){
				return true;
			}
		}else{
			if(mapper.updateByPrimaryKeySelective(bankSplitConfigure) > 0){
				return true;
			}
		}
		return false;
	}
	/**
	 * 计算运费分账费用
	 * @param bankSplitConfigure
	 * @param paymentAmount
	 * @return
	 */
	public double splitCounterFee(PlatfoemBankSplitConfigure bankSplitConfigure,double paymentAmount){
		//计算手续费
		double counterFee=paymentAmount * (bankSplitConfigure.getValue()/1000);
		if(bankSplitConfigure.getCap() < counterFee){
			counterFee = bankSplitConfigure.getCap();
		}
		//计算分账费用
		double cost = paymentAmount - counterFee;
		return getDouble(cost);
	}
	public static double getDouble(double x){
		int y =(int)(x*100);
		x = (double)y/100;
		return x;
	}
}
