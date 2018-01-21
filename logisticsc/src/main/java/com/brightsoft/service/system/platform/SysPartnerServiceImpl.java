package com.brightsoft.service.system.platform;

import java.util.Date;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.system.SysPartnerMapper;
import com.brightsoft.model.SysPartner;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class SysPartnerServiceImpl {
	@Autowired
	public SysPartnerMapper partnerMapper;
	
	//邮箱发送访问配置路径
		ResourceBundle bundle = PropertyResourceBundle.getBundle("emailConfig");
	
	/**
	 * 增加合作伙伴
	 * @param partner
	 * @return
	 */
	public boolean insertPartner(SysPartner partner){
		partner.setPartnerTime(new Date());
		if(partnerMapper.insert(partner) > 0){
			return true;
		}
		return false;
	}
	
	public Result doGetPartner(SearchParams searchParams,Page<?> page) {
		Result result = new  Result();
		try {
			int results = partnerMapper.countRows(searchParams);
			List<SysPartner> list = partnerMapper.selectByCondition(page,searchParams);
			result.setResults(results);
			result.setRows(list);
			result.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
		}
		return result;
	}
	
	public boolean updateParner(SysPartner partner){
		if(partnerMapper.updateByPrimaryKeySelective(partner) > 0){
			return true;
		}
		return false;
	}
	
	public boolean deleteParner(List<Long> ids){
		if(partnerMapper.deletetPartner(ids) > 0){
			return true;
		}
		return false;
	}
	
	public List<SysPartner> selectListParner(){
		List<SysPartner> list = partnerMapper.selectSysPartnerList();
		return list;
	}
}
