package com.brightsoft.service.platform;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformUserLineConsumeRecordMapper;
import com.brightsoft.dao.platform.PlatformUserLineMoneyMapper;
import com.brightsoft.dao.platform.PlatformUserMapper;
import com.brightsoft.model.PlatformLineStorage;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserLineConsumeRecord;
import com.brightsoft.model.PlatformUserLineMoney;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.WebConstant;

@Service("platformUserLineMoneyService")
public class PlatformUserLineMoneyServiceImpl implements PlatformUserLineMoneyService{

	@Autowired
	public PlatformUserLineMoneyMapper lineMoneyMapper;
	
	@Autowired
	private XzqhServiceImpl xzqhServiceImpl;
	
	@Autowired 
	public PlatformUserMapper userMapper;
	
	@Autowired
	public PlatformUserLineConsumeRecordMapper consumeRecordMapper;
	
	/**
	 * 线路充值 ---新的线路
	 */
	public PlatformUser insertUserLineMoney(PlatformUserLineMoney lineMoney,PlatformUser user) {
		if(userMapper.updateUserBalance(user.getBalance()-lineMoney.getMoney(),user.getId()) > 0){
			lineMoney.setUserId(user.getId());
			lineMoney.setOperTime(new Date());
			PlatformUser platformUser =null;
			if(lineMoneyMapper.insertUserLineMoney(lineMoney) > 0){
				platformUser = userMapper.selectUserId(user.getId());
				if(platformUser != null ){
					return platformUser;
				}else {
					return null;
				}
			}
		}
		return null;
	}
	/**
	 * 线路预充值
	 */
	public PlatformUser doUserLineConsumeRecord(
			Long companyId,Long lineId,Long moneyId,PlatformUser user,double money) {
		if(userMapper.updateUserBalance(user.getBalance()-money,user.getId()) > 0){
			PlatformUserLineMoney lineMoney = null;
			PlatformUserLineConsumeRecord consumeRecord = new PlatformUserLineConsumeRecord();
			lineMoney = lineMoneyMapper.selectUserLineMoney(moneyId);
			if(null != lineMoney){
				if(lineMoneyMapper.updateUserLineMoney(moneyId,lineMoney.getMoney()+money) >0){
					consumeRecord.setConsumeTime(new Date());
					consumeRecord.setMoney(money);
					consumeRecord.setConsumeCard("用户");
					consumeRecord.setConsumeUser(user.getLoginName());
					consumeRecord.setChargeCard("线路物流商银行卡");
					consumeRecord.setChargeUser("线路银行卡名称");
					consumeRecord.setUserId(user.getId());
					consumeRecord.setCompanyId(companyId);
					consumeRecord.setTmsLineId(lineId);
					if(consumeRecordMapper.insertUserLineConsumeRecord(consumeRecord) > 0){
						user = userMapper.selectUserId(user.getId());
						if(user != null ){
							return user;
						}else {
							return null;
						}
					}
				}
			}
		}
		return null;
	}
	/**
	 * 获取用户线路充值余额
	 */
	public Page<?> selectBySelectedCondition(PlatformLineStorage lineStorage,
			Page<?> page) {
		int results = lineMoneyMapper.countRows(lineStorage);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<PlatformLineStorage> platformLineStorages = lineMoneyMapper.selectBySelectedCondition(lineStorage,page);
		for (int i = 0; i < platformLineStorages.size(); i++) {
			platformLineStorages.get(i).setStartProvince(xzqhServiceImpl.selectValueById(platformLineStorages.get(i).getStartProvince()).getName());
			platformLineStorages.get(i).setStartCity(xzqhServiceImpl.selectValueById(platformLineStorages.get(i).getStartCity()).getName());
			platformLineStorages.get(i).setEndProvince(xzqhServiceImpl.selectValueById(platformLineStorages.get(i).getEndProvince()).getName());
			platformLineStorages.get(i).setEndCity(xzqhServiceImpl.selectValueById(platformLineStorages.get(i).getEndCity()).getName());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results); //总数
		map.put(WebConstant.TOTALPAGE, totalPage); //总页数
		map.put(WebConstant.ROWS, platformLineStorages);
		page.setParams(map);
		return page;
	}
	/**
	 * 判断当前用户是否存在线路充值情况
	 */
	public PlatformUserLineMoney selectLineId(Long lineId, Long userId) {
		PlatformUserLineMoney lineMoney = null;
		lineMoney = lineMoneyMapper.selectlineId(lineId, userId);
		if(null != lineMoney){
			return lineMoney;
		}else{
			return lineMoney;
		}
	}
}
