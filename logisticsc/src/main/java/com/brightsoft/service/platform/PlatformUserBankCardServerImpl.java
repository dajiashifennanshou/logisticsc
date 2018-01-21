package com.brightsoft.service.platform;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.dao.platform.PlatformDictionaryMapper;
import com.brightsoft.dao.platform.PlatformUserBankCardMapper;
import com.brightsoft.model.PlatformUserBankCard;
import com.brightsoft.utils.Const;

/**
 * 用户银行卡
 * @author ThinkPad
 *
 */
@Service("PlatformUserBankCard")
public class PlatformUserBankCardServerImpl {
	
	@Autowired
	public PlatformUserBankCardMapper bankCardMapper;
	
	@Autowired
	public PlatformDictionaryMapper dictionaryMapper;
	/**
	 * 增加银行卡
	 * @param bankCard
	 * @return
	 */
	public boolean insertUserBankCard(PlatformUserBankCard bankCard){
		bankCard.setCardState(Const.PLATFORM_USER_BANK_CARD_0);
		bankCard.setTime(new Date());
		bankCard.setBank(dictionaryMapper.selectCradId(bankCard.getCardType(),DictionaryType.CRAD_TYPE));
		if(bankCardMapper.insertUserBankCard(bankCard)>0){
			return true;
		}
		return false;
	}
	/**
	 * 获取用户银行卡
	 * @param userId
	 * @return
	 */
	public List<PlatformUserBankCard> selectBankCard(Long userId){
		List<PlatformUserBankCard> bankCards = bankCardMapper.selectBankCard(userId);
		return bankCards;
	}
	/**
	 * 获取当前银行卡ID 
	 * @param id
	 * @return
	 */
	public PlatformUserBankCard selectBankCardId(Long id){
		return bankCardMapper.selectBankCardId(id);
	}
	/**
	 * 根据用户ID 修改银行卡信息
	 * @param id
	 * @return
	 */
	public boolean updateUserBankCard(PlatformUserBankCard bankCard){
		bankCard.setBank(dictionaryMapper.selectCradId(bankCard.getCardType(),DictionaryType.CRAD_TYPE));
		if(bankCardMapper.updateUserBankCard(bankCard) > 0){
			return true;
		}
		return false;
	}
}
