package com.brightsoft.service.platform;

import java.util.List;

import com.brightsoft.model.PlatformUserCommonContact;
import com.brightsoft.utils.Page;

public interface PlatformUserCommonContactService {
	
	/**
	 * 增加常用联系人
	 * @param commonContact
	 * @return
	 */
	public boolean inserPlatformUserCommonContact(PlatformUserCommonContact commonContact);
	
	/**
	 * 分页获取常用联系人
	 */
	public Page<?> selectBySelectedCondition(PlatformUserCommonContact commonContact, Page<?> page);
	/**
	 * 逻辑删除联系人
	 * @param commonContact
	 * @return
	 */
	public boolean updateCommonContact(List<Long> commonContact);
	
	public int insertCommonContact(PlatformUserCommonContact commonContact);
	
	public boolean selectPlatCommonContactId(PlatformUserCommonContact commonContact);
}
