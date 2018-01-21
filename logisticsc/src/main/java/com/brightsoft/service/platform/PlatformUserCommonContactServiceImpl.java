package com.brightsoft.service.platform;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformUserCommonContactMapper;
import com.brightsoft.model.PlatformUserCommonContact;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.WebConstant;

@Service("platformUserCommonContact")
public class PlatformUserCommonContactServiceImpl implements PlatformUserCommonContactService{
	
	@Autowired
	public PlatformUserCommonContactMapper commonContactMapper;
	/**
	 * 增加常用联系人
	 */
	public boolean inserPlatformUserCommonContact(
			PlatformUserCommonContact commonContact) {
		commonContact.setCreateTime(new Date());
		commonContact.setState(Const.PLATFORM_USER__COMMON_CONTACT_1);
		if(commonContactMapper.insertPlatformUserCommonContact(commonContact) > 0){
			return true;
		}
		return false;
	}
	/**
	 * 获取常用联系人
	 */
	public Page<?> selectBySelectedCondition(
			PlatformUserCommonContact commonContact, Page<?> page) {
		int results = commonContactMapper.countRows(commonContact);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<PlatformUserCommonContact> commonContacts = commonContactMapper.selectBySelectedCondition(commonContact, page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, commonContacts);
		page.setParams(map);
		return page;
	}
	/**
	 * 逻辑删除我的常用联系人
	 */
	public boolean updateCommonContact(List<Long> commonContact) {
		if(commonContactMapper.updateCommonContact(commonContact, Const.PLATFORM_USER__COMMON_CONTACT_0) >0){
			return true;
		}
		return false;
	}
	
	/**
	 * 添加常用联系人
	 * @param commonContact
	 * @return
	 */
	public int insertCommonContact(PlatformUserCommonContact commonContact){
		String phone = commonContact.getPhoneNumber();
		Long userId = commonContact.getUserId();
		PlatformUserCommonContact contact = commonContactMapper.selectByPhoneAndUserId(phone, userId);
		if(contact != null){
			return 0;
		}
		commonContact.setCreateTime(new Date());
		commonContact.setState(Const.PLATFORM_USER__COMMON_CONTACT_1);
		return commonContactMapper.insertPlatformUserCommonContact(commonContact);
	}
	public boolean selectPlatCommonContactId(
			PlatformUserCommonContact commonContact) {
		if(commonContactMapper.selectPlatCommonContactId(commonContact) > 0){
			return true;
		}
		return false;
	}
}
