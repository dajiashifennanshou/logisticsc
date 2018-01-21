package com.brightsoft.service.platform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.dao.platform.PlatformEvaluationCount;
import com.brightsoft.dao.platform.PlatformOrderEvaluationMapper;
import com.brightsoft.dao.platform.PlatformOrderMapper;
import com.brightsoft.dao.platform.PlatformUserCompanyMapper;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformCompanyinfo;
import com.brightsoft.model.PlatformDeliverGoods;
import com.brightsoft.model.PlatformUserEvaluation;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.WebConstant;

@Service
public class PlatformLogisticsCompanyServiceImpl {
	
	@Autowired
	private PlatformUserCompanyMapper companyMapper;
	
	@Autowired
	private XzqhServiceImpl xzqhServiceImpl;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private PlatformOrderMapper orderMapper;
	
	@Autowired
	private PlatformOrderEvaluationMapper evaluationMapper;
	
	/**
	 * 获取公司图片文件对象
	 */
	public static ResourceBundle bundle = PropertyResourceBundle.getBundle("companyDocumentsConfig");
	
	@Autowired
	private OutletsInfoMapper infoMapper;
	
	public Page<?> selectUserEvaluation(PlatformUserEvaluation evaluation,Page<?> page){
		int results = evaluationMapper.userEvaluation(evaluation);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		PlatformEvaluationCount evaluationCount = new PlatformEvaluationCount();
		List<PlatformUserEvaluation> evaluations = evaluationMapper.selectUserEvaluation(page, evaluation);
		double praisePeople=0,commonlyPeople=0,badPeople=0;
		for (int i = 0; i < evaluations.size(); i++) {
			evaluations.get(i).setStartProvince(xzqhServiceImpl.selectValueById(evaluations.get(i).getStartProvince()).getName());
			evaluations.get(i).setStartCity(xzqhServiceImpl.selectValueById(evaluations.get(i).getStartCity()).getName());
			evaluations.get(i).setStartCounty(xzqhServiceImpl.selectValueById(evaluations.get(i).getStartCounty()).getName());
			evaluations.get(i).setEndProvince(xzqhServiceImpl.selectValueById(evaluations.get(i).getEndProvince()).getName());
			evaluations.get(i).setEndCity(xzqhServiceImpl.selectValueById(evaluations.get(i).getEndCity()).getName());
			evaluations.get(i).setEndCounty(xzqhServiceImpl.selectValueById(evaluations.get(i).getEndCounty()).getName());
		}
		List<PlatformUserEvaluation> platformUserEvaluations = evaluationMapper.selectUserEvaluationCount(evaluation);
		for (int i = 0; i < platformUserEvaluations.size(); i++) {
			if(platformUserEvaluations.get(i).getEvaluateLevel() >3  ){
				praisePeople=praisePeople+1;
			}else if(platformUserEvaluations.get(i).getEvaluateLevel() >2 && platformUserEvaluations.get(i).getEvaluateLevel() < 4){
				commonlyPeople=praisePeople+1;
			}else if(platformUserEvaluations.get(i).getEvaluateLevel() ==1){
				badPeople=badPeople+1;
			}
		}
		evaluationCount.setEvaluations(evaluations);
		evaluationCount.setPraisePeople((int)praisePeople);
		double praise = (praisePeople/results)*10;
		double commonly = (commonlyPeople/results)*10;
		double bad = (badPeople/results)*10;
		evaluationCount.setPraise((int)(praise)*10);
		evaluationCount.setCommonlyPeople((int)commonlyPeople);
		evaluationCount.setCommonly((int)commonly*10);
		evaluationCount.setBadPeople((int)badPeople);	
		evaluationCount.setBad((int)bad*10);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, evaluationCount);
		page.setParams(map);
		return page;
	}
	/**
	 * 查看网点
	 * @param outletsInfo
	 * @param page
	 * @return
	 */
	public Page<?> selectOutletsInfo(OutletsInfo outletsInfo,Page<?> page){
		int results = infoMapper.countoutletsInfo(outletsInfo);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<OutletsInfo> outletsInfos =infoMapper.selectoutletsInfo(outletsInfo, page);
		for (int i = 0; i < outletsInfos.size(); i++) {
			outletsInfos.get(i).setProvince(xzqhServiceImpl.selectValueById(outletsInfos.get(i).getProvince()).getName());
			outletsInfos.get(i).setCity(xzqhServiceImpl.selectValueById(outletsInfos.get(i).getCity()).getName());
			outletsInfos.get(i).setCounty(xzqhServiceImpl.selectValueById(outletsInfos.get(i).getCounty()).getName());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, outletsInfos);
		page.setParams(map);
		return page;
	}
	/**
	 * 获取物流提供商公司信息
	 * @param companyId
	 * @return
	 */
	public PlatformCompanyinfo selectLogistics(Long companyId){
		PlatformCompanyinfo companyinfo = companyMapper.selectLogisticsCompanyinfo(companyId);
		companyinfo.setLogo(bundle.getString("baseUrl")+companyinfo.getLogo().replace("\"", "/"));
		return companyinfo;
	}
	/**
	 * 获取店铺线路信息
	 */
	public Page<?> selectCompanCondition(
			PlatformDeliverGoods deliverGoods, Page<?> page) {
		int results = orderMapper.companCountRows(deliverGoods);
		int totalPage = (results+page.getLimit()-1)/page.getLimit();
		page.setStart((page.getPageIndex()-1)*page.getLimit());
		List<PlatformDeliverGoods> platformDeliverGoods = orderMapper.selectCompanCondition(deliverGoods, page);
		for (int i = 0; i < platformDeliverGoods.size(); i++) {
			platformDeliverGoods.get(i).setStartProvinceValue(xzqhServiceImpl.selectValueById(platformDeliverGoods.get(i).getStartProvince()).getName());
			platformDeliverGoods.get(i).setStartCityValue(xzqhServiceImpl.selectValueById(platformDeliverGoods.get(i).getStartCity()).getName());
			platformDeliverGoods.get(i).setStartCountyValue(xzqhServiceImpl.selectValueById(platformDeliverGoods.get(i).getStartCounty()).getName());
			platformDeliverGoods.get(i).setEndProvinceValue(xzqhServiceImpl.selectValueById(platformDeliverGoods.get(i).getEndProvince()).getName());
			platformDeliverGoods.get(i).setEndCityValue(xzqhServiceImpl.selectValueById(platformDeliverGoods.get(i).getEndCity()).getName());
			platformDeliverGoods.get(i).setEndCountyValue(xzqhServiceImpl.selectValueById(platformDeliverGoods.get(i).getEndCounty()).getName());
			platformDeliverGoods.get(i).setTransportMode(dictionaryService.selectByPrimaryId(Long.parseLong(platformDeliverGoods.get(i).getTransportMode()), DictionaryType.TRANSPORT_MODE).getName());
			platformDeliverGoods.get(i).setCountOrder(orderMapper.countOrderLine(platformDeliverGoods.get(i).getId()));
			platformDeliverGoods.get(i).setCountOrderEvaluation(evaluationMapper.countLineEvaluation(platformDeliverGoods.get(i).getId()));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WebConstant.RESULTS, results);
		map.put(WebConstant.TOTALPAGE, totalPage);
		map.put(WebConstant.ROWS, platformDeliverGoods);
		page.setParams(map);
		return page;
	}
	/**
	 * 获取单个网点信息
	 * @param id
	 * @return
	 */
	public OutletsInfo selectOutletsId(Long id){
		OutletsInfo outletsInfo = infoMapper.selectById(id);
		return outletsInfo;
	}
}
