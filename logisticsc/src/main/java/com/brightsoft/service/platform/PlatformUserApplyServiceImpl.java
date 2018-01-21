package com.brightsoft.service.platform;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.PaymentTypeEnum;
import com.brightsoft.dao.platform.PlatformUserApplyMapper;
import com.brightsoft.dao.platform.PlatformUserBankCardMapper;
import com.brightsoft.dao.platform.PlatformUserBondMapper;
import com.brightsoft.dao.platform.PlatformUserMapper;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserBond;

@Service("PlatformUserApply")
public class PlatformUserApplyServiceImpl {
	
	@Autowired
	public PlatformUserApplyMapper applyMapper;
	
	@Autowired
	public PlatformUserMapper platformUserMapper;
	
	@Autowired
	public PlatformUserBankCardMapper bankCardMapper;
	
	@Autowired
	public PlatformUserBondMapper bondMapper;

	
	/**
	 * 验证当前用户是否存在申请
	 * @param id
	 * @return
	 */
	public boolean selectMaxSatet(Long userId){
		if(applyMapper.selectMacState(userId) == 0){
			return true;
		}
		return false;
	}
	/**
	 * 获取保证金
	 * @param userId
	 * @return true 已交保证金
	 */
	public boolean selectBond(Long userId){
		if(bondMapper.selectUserBond(userId) > 0){
			return true;
		}
		return false;
	}
	/**
	 * 获取用户绑定银行卡数量
	 * @param userId
	 * @return true 绑定银行卡
	 */
	public boolean selectCard(Long userId){
		if(bankCardMapper.selectUserBankCard(userId) > 0){
			return true;
		}
		return false;
	}
	/**
	 * 增加保证金支付记录
	 * @param bond
	 * @return
	 */
	public PlatformUser insertUserBond(PlatformUserBond bond){
		
		PlatformUser user = null;
		user = platformUserMapper.selectUserId(bond.getUserId());
		if(null != user){
			bond.setPaymentType(PaymentTypeEnum.BALANCEPAY.getValue());
			bond.setPaymentTime(new Date());
			if(platformUserMapper.updateUserBalance(user.getBalance()-bond.getPaymentMoney(),user.getId()) > 0){
				if(bondMapper.inserUserBond(bond) > 0){
					user = platformUserMapper.selectUserId(bond.getUserId());
					if(user != null ){
						return user;
					}else {
						return null;
					}
				}
			}
		}
		return null;
	}
}
