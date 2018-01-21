package com.brightsoft.service.system.platform;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.dao.platform.PlatformUserMapper;
import com.brightsoft.dao.platform.platformBankAccountMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.dao.tms.PosBindMapper;
import com.brightsoft.dao.tms.UserMapper;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.PosBind;
import com.brightsoft.model.User;
import com.brightsoft.model.platformBankAccount;
import com.brightsoft.utils.MD5;
import com.brightsoft.utils.Result;
/**
 * POS机 管理 service
 * @author yangshoubao
 *
 */

@Service
public class PosManagerService {

	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	@Autowired
	private PosBindMapper posBindMapper;
	
	@Autowired
	private PlatformUserCompanyMapper platformUserCompanyMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PlatformUserMapper platformUserMapper;
	
	@Autowired
	private platformBankAccountMapper platformBankAccountMapper;
	
	/**
	 * 查询POS绑定列表
	 * @param params
	 * @return
	 */
	public Result selectPosBindList(BaseSearchParams params){
		Result result = new Result();
		List<OutletsInfo> outletsInfos = outletsInfoMapper.selectByParams(params);
		int count = 0;
		String condition = params.getCondition();
		if(StringUtils.isEmpty(condition)){
			count = outletsInfoMapper.selectByParamsCountNoCondition(params);
		}else{
			count = outletsInfoMapper.selectByParamsCountCondition(params);
		}
		result.setResults(count);
		result.setRows(outletsInfos);
		return result;
	}
	
	/**
	 * 绑定POS机
	 * @param posBind
	 * @return
	 */
	public int saveBindPos(PosBind posBind){
		// 生成登录账号
		Long outletsId = posBind.getOutletsId();
		PlatformUserCompany platformUserCompany = platformUserCompanyMapper.selectByOutletsId(outletsId);
		List<OutletsInfo> outletsInfos = outletsInfoMapper.selectByCompanyId(platformUserCompany.getId());
		List<Long> outletsIds = new ArrayList<Long>();
		for (OutletsInfo outletsInfo : outletsInfos) {
			outletsIds.add(outletsInfo.getId());
		}
		String loginAccount = null;
		PosBind posBindTemp = posBindMapper.selectNewestByOutletsIds(outletsIds);
		if(posBindTemp != null){
			loginAccount = (Integer.parseInt(posBindTemp.getLoginAccount()) + 1) + "";
		}else{
			loginAccount = platformUserCompany.getCompanyCode() + "001";
		}
		posBind.setLoginAccount(loginAccount);
		posBind.setCreateTime(new Date());
		posBind.setLoginPwd(MD5.getMD5Code(posBind.getLoginPwd()));
		return posBindMapper.insert(posBind);
	}
	
	/**
	 * 解绑POS机
	 * @param posBindId
	 * @return
	 */
	public int saveUnbindPos(Long posBindId){
		return posBindMapper.deleteByPrimaryKey(posBindId);
	}
	
	/**
	 * 验证 网点是否绑定银行卡
	 * @param outletsId
	 * @return
	 */
	public boolean validOutletsIsBindCard(Long outletsId){
		User user = userMapper.selectLogisManagerUserByOutletsId(outletsId);
		if(user == null){
			return false;
		}
		PlatformUser platformUser = platformUserMapper.selectUserloginName(user.getLoginName());
		platformBankAccount platformBankAccount = platformBankAccountMapper.selectByUserId(platformUser.getId());
		if(platformBankAccount == null){
			return false;
		}
		return true;
	}
	
	/**
	 * 验证POS机是否 已绑定
	 * @param posSn
	 * @return
	 */
	public boolean validPosIsBind(String posSn){
		PosBind posBind = posBindMapper.selectByPosSn(posSn);
		if(posBind == null){
			return false;
		}
		return true;
	}
}
