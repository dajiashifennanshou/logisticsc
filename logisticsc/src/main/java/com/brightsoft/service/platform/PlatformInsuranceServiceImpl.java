package com.brightsoft.service.platform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.InsuranceStatusEnum;
import com.brightsoft.controller.vo.InsuranceBackData;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.platform.PlatformInsuranceMapper;
import com.brightsoft.model.InsuranceRate;
import com.brightsoft.model.PlatformInsurance;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.WebConstant;
import com.brightsoft.utils.insurance.InsuranceHttpService;

@Service
public class PlatformInsuranceServiceImpl {

	@Autowired
	private PlatformInsuranceMapper platformInsuranceMapper;
	
	@Autowired
	private InsuranceHttpService insuranceHttpService;
	
	@Autowired
	private InsuranceManageService insuranceManageService;
	
	@Autowired
	private PlatformUserConsumeRecordService platformUserConsumeRecordService;
	
	/**
	 * 保存保单	
	 * @param platformInsurance
	 * @return
	 */
	public boolean insertInsurance(PlatformInsurance platformInsurance){
		Boolean flag = false;
		Double insRate = null;
		Float lowestFee = null;
		InsuranceRate insuranceRate = null,
					insuranceRate1 = null;
		platformInsurance.setInsJine(((double)Math.round(platformInsurance.getInsJine()*100))/100);
		if(StringUtils.isNotBlank(platformInsurance.getInsTsType())){
			insuranceRate = insuranceManageService.selectInsRateByComAndTsType(platformInsurance.getInsComTag(), platformInsurance.getInsTsType());
		}
		if(StringUtils.isNotBlank(platformInsurance.getInsType())){
			insuranceRate1 = insuranceManageService.selectInsRateByComAndType(platformInsurance.getInsComTag(), platformInsurance.getInsType());
		}
		
		if(insuranceRate != null && insuranceRate1 !=null){
			insRate = insuranceRate.getInsRate()>=insuranceRate1.getInsRate()?insuranceRate.getInsRate():insuranceRate1.getInsRate();
			lowestFee = insuranceRate.getInsRate()>=insuranceRate1.getInsRate()?insuranceRate.getInsLowestPrice():insuranceRate1.getInsLowestPrice();
		}else if(insuranceRate != null){
			insRate = insuranceRate.getInsRate();
			lowestFee = insuranceRate.getInsLowestPrice();
		}else if(insuranceRate1 !=null){
			insRate = insuranceRate1.getInsRate();
			lowestFee = insuranceRate1.getInsLowestPrice();
		}
		
		double insFee = 10000*insRate*platformInsurance.getInsJine();
		if(insFee <= lowestFee){
			platformInsurance.setInsFee(((double)Math.round(lowestFee*100))/100);
		}
		platformInsurance.setInsFee(((double)Math.round(insFee*100))/100);
		platformInsurance.setInsStatus(InsuranceStatusEnum.saved.getValue());
		platformInsurance.setInsArea(0);
		platformInsurance.setInsCreateTime(DateTools.getYMDHMS());
		if(platformInsuranceMapper.insert(platformInsurance)>0){
			return true;
		}
//		
		//信息是否提交成功
//		if(insuranceHttpService.postInsInfo(platformInsurance)){
//			platformInsurance.setInsStatus(InsuranceStatusEnum.checking.getValue());
//			platformInsurance.setInsCreateTime(DateTools.getYMDHMS());
//			
//			//成功后服务器记录保单数据
//			if(platformInsuranceMapper.insert(platformInsurance)>0){
//				return true;
//			}
//		};
		return flag;
	}
	
	/**
	 * 
	 * 方法描述：支付成功更改保单状态
	 * @param order
	 * @return
	 * @author dub
	 * @version 2016年5月11日 下午8:23:55
	 */
	public boolean insert4Buy(String order){
		boolean flag = false;
		PlatformInsurance platformInsurance = platformInsuranceMapper.selectByInsOrderNum(order);
		if(platformInsurance != null){
			//信息是否提交成功
			if(insuranceHttpService.postInsInfo(platformInsurance)){
				//成功后服务器修改保单状态
				if(platformInsuranceMapper.updateInsuranceStatus(order,InsuranceStatusEnum.checking.getValue())>0){
					return true;
				}
			};
		}
		return flag;
	}
	
	/**
	 * 更新保单信息
	 * @param platformInsurance
	 * @return
	 */
	public void insertInsBackData(InsuranceBackData insuranceBackData){
		PlatformInsurance platformInsurance = new PlatformInsurance();
		platformInsurance.setInsOrderNum(insuranceBackData.getInsOrderNum());
		if(insuranceBackData.getBackInsStatus() == 0){
			platformInsurance.setInsDes(insuranceBackData.getBackInsValue());
			platformInsurance.setInsStatus(InsuranceStatusEnum.takeEffect.getValue());
			platformInsurance.setInsLastBaodan(insuranceBackData.getInsLastBaodan());
			platformInsurance.setInsSysBaodan(insuranceBackData.getInsSysBaodan());
			platformInsurance.setInsFileUrl(insuranceBackData.getInsFileUrl());
			platformInsurance.setInsDes(insuranceBackData.getBackInsValue());
		}else if(insuranceBackData.getBackInsStatus() == 100){
			platformInsurance.setInsStatus(InsuranceStatusEnum.cancellationInsurance.getValue());
			platformInsurance.setInsDes(insuranceBackData.getBackInsValue());
		}
		platformInsuranceMapper.updateByInsOrderNumSelective(platformInsurance);
	}
	/**
	 * 根据用户id查询用户保单信息
	 * @param userId
	 * @param params
	 * @param page
	 * @return
	 */
	public Page<?> selectByCondition(long userId,SearchParams params,Page<?> page){
		int results = platformInsuranceMapper.countRowsByCondition(userId,params);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<PlatformInsurance> rows = platformInsuranceMapper.selectByCondition(userId,params, page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, rows);
		page.setParams(map);
		return page;
	}
	
	/**
	 * 获取保险信息
	 * @param searchParams
	 * @param page
	 * @return
	 */
	public Result selectByParams(SearchParams searchParams,Page<?> page){
		Result result = new Result();
		int results = platformInsuranceMapper.countRowsByParams(searchParams);
		List<PlatformInsurance> rows = platformInsuranceMapper.selectInsByParams(searchParams, page);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	
	/**
	 * 根据id获取保单信息
	 * @param insId
	 * @return
	 */
	public PlatformInsurance selectById(Long insId){
		return platformInsuranceMapper.selectByPrimaryKey(insId);
	}
	
}
