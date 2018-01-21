package com.brightsoft.service.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformUserMapper;
import com.brightsoft.dao.platform.platformBankAccountMapper;
import com.brightsoft.dao.tms.UserMapper;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.User;
import com.brightsoft.model.platformBankAccount;

@Service
public class platformBankAccountServiceImpl {

	@Autowired
	private  platformBankAccountMapper accountMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PlatformUserMapper platformUserMapper;
	
	public boolean insertBankAccount(platformBankAccount account){
		if(accountMapper.insertSelective(account)>0){
			return true;
		}
		return false;
	}
	
	public platformBankAccount selectBankAccountUserId(Long userId){
		return accountMapper.selectBankAccountUserId(userId);
	}
	
	public boolean updateBankAccount(platformBankAccount account){
		if(accountMapper.updateByPrimaryKeySelective(account)>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 根据网点 查询 该网点绑定的子账号
	 * @param outletsId
	 * @return
	 */
	public platformBankAccount selectByOutlets(Long outletsId){
		User user = userMapper.selectLogisManagerUserByOutletsId(outletsId);
		if(user == null){
			return null;
		}
		PlatformUser platformUser = platformUserMapper.selectUserloginName(user.getLoginName());
		return accountMapper.selectByUserId(platformUser.getId());
	}
}
