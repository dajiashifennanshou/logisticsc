package com.brightsoft.service.platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.platform.InsComTsTypeMapper;
import com.brightsoft.dao.platform.InsComTypeMapper;
import com.brightsoft.dao.platform.InsuranceCompanyMapper;
import com.brightsoft.dao.platform.InsuranceRateMapper;
import com.brightsoft.dao.platform.InsuranceTsTypeMapper;
import com.brightsoft.dao.platform.InsuranceTypeMapper;
import com.brightsoft.model.InsComTsType;
import com.brightsoft.model.InsComType;
import com.brightsoft.model.InsuranceCompany;
import com.brightsoft.model.InsuranceRate;
import com.brightsoft.model.InsuranceTsType;
import com.brightsoft.model.InsuranceType;
import com.brightsoft.model.SysUser;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.insurance.InsType;

@Service
public class InsuranceManageService {

	@Autowired
	private InsuranceTypeMapper insuranceTypeMapper;
	
	@Autowired
	private InsuranceCompanyMapper insuranceCompanyMapper;
	
	@Autowired
	private InsuranceTsTypeMapper insuranceTsTypeMapper;
	
	@Autowired
	private InsComTsTypeMapper insComTsTypeMapper;
	
	@Autowired
	private InsComTypeMapper insComTypeMapper;
	
	@Autowired
	private InsuranceRateMapper insuranceRateMapper;
	/**
	 * 获取保险公司
	 * @param searchParams
	 * @param page
	 * @return
	 */
	public Result selectInsComByCondition(SearchParams searchParams ,Page<?> page){
		Result result = new Result();
		int results = insuranceCompanyMapper.countRows(searchParams);
		List<InsuranceCompany> rows = insuranceCompanyMapper.selectByCondition(searchParams, page);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	/**
	 * 获取险种
	 * @param searchParams
	 * @param page
	 * @return
	 */
	public Result selectInsTypeByCondition(SearchParams searchParams ,Page<?> page){
		Result result = new Result();
		int results = insuranceTypeMapper.countRows(searchParams);
		List<InsuranceType> rows = insuranceTypeMapper.selectByCondition(searchParams, page);
		result.setRows(rows);
		result.setResults(results);
		return result;
	}
	/**
	 * 获取特殊类型
	 * @param searchParams
	 * @param page
	 * @return
	 */
	public Result selectInsTsTypeByCondition(SearchParams searchParams ,Page<?> page){
		Result result = new Result();
		int results = insuranceTsTypeMapper.countRows(searchParams);
		List<InsuranceTsType> rows = insuranceTsTypeMapper.selectByCondition(searchParams, page);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	/**
	 * 获取保险费率
	 * @param searchParams
	 * @param page
	 * @return
	 */
	public Result selectInsRateByCondition(SearchParams searchParams,Page<?> page){
		Result result = new Result();
		int results = insuranceRateMapper.countRows(searchParams);
		List<InsuranceRate> rows = insuranceRateMapper.selectByCondition(searchParams, page);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	/**
	 * 添加险种
	 * @param insuranceType
	 * @param user
	 * @return
	 */
	public boolean insertInsType(InsuranceType insuranceType,SysUser user){
		boolean flag = false;
		insuranceType.setCreateTime(DateTools.getYMDHMS());
		insuranceType.setInsStatus(1);
		insuranceType.setCreatePersonId(user.getId());
		if(insuranceTypeMapper.insert(insuranceType)>0){
			flag = true;
		}
		return flag;
	}
	/**
	 * 天机特殊类型
	 * @param insuranceTsType
	 * @param user
	 * @return
	 */
	public boolean insertInsTsType(InsuranceTsType insuranceTsType,SysUser user){
		boolean flag = false;
		insuranceTsType.setCreateTime(DateTools.getYMDHMS());
		insuranceTsType.setInsStatus(1);
		insuranceTsType.setCreatePersonId(user.getId());
		if(insuranceTsTypeMapper.insert(insuranceTsType)>0){
			flag = true;
		}
		return flag;
	}
	/**
	 * 添加保险公司
	 * @param insuranceCompany
	 * @param insTypeIds
	 * @param insTsTypeIds
	 * @param user
	 * @return
	 */
	public boolean insertInsCom(InsuranceCompany insuranceCompany,String[] insTypeIds,String[] insTsTypeIds,SysUser user){
		boolean flag = false;
		insuranceCompany.setCreateTime(DateTools.getYMDHMS());
		insuranceCompany.setInsStatus(1);
		insuranceCompany.setCreatePersonId(user.getId());
		if(insuranceCompanyMapper.insert(insuranceCompany)>0){
			if(insTypeIds != null && insTypeIds.length >0){
				ArrayList<InsComType> typeList = new ArrayList<InsComType>();
				for (String insTypeId : insTypeIds) {
					InsComType insComType = new InsComType();
					insComType.setInsTypeId(Long.parseLong(insTypeId));
					insComType.setInsComId(insuranceCompany.getId());
					typeList.add(insComType);
				}
				if(insComTypeMapper.insertBatch(typeList)>0){
					if(insTsTypeIds != null  && insTsTypeIds.length >0){
						ArrayList<InsComTsType> tsTypeList = new ArrayList<InsComTsType>();
						for (String insTsTypeId : insTsTypeIds) {
							InsComTsType insComTsType = new InsComTsType();
							insComTsType.setInsTsTypeId(Long.parseLong(insTsTypeId));
							insComTsType.setInsComId(insuranceCompany.getId());
							tsTypeList.add(insComTsType);
						}
						if(insComTsTypeMapper.insertBatch(tsTypeList)>0){
							flag = true;
						}
					}else{
						flag = true;
					}
				}
			}else{
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * 更新保险公司
	 * @return
	 */
	public boolean updateInsCom(InsuranceCompany insuranceCompany,String[] insTypeIds,String[] insTsTypeIds){
		boolean flag = false;
		String[] str = new String[1];
		str[0] = insuranceCompany.getId()+"";
		insComTypeMapper.deleteBatchByComIds(str);
		insComTsTypeMapper.deleteBatchByComIds(str);
		if(insuranceCompanyMapper.updateByPrimaryKeySelective(insuranceCompany)>0){
			if(insTypeIds != null && insTypeIds.length >0){
				ArrayList<InsComType> typeList = new ArrayList<InsComType>();
				for (String insTypeId : insTypeIds) {
					InsComType insComType = new InsComType();
					insComType.setInsTypeId(Long.parseLong(insTypeId));
					insComType.setInsComId(insuranceCompany.getId());
					typeList.add(insComType);
				}
				if(insComTypeMapper.insertBatch(typeList)>0){
					if(insTsTypeIds != null && insTsTypeIds.length>0){
						ArrayList<InsComTsType> tsTypeList = new ArrayList<InsComTsType>();
						for (String insTsTypeId : insTsTypeIds) {
							InsComTsType insComTsType = new InsComTsType();
							insComTsType.setInsTsTypeId(Long.parseLong(insTsTypeId));
							insComTsType.setInsComId(insuranceCompany.getId());
							tsTypeList.add(insComTsType);
						}
						if(insComTsTypeMapper.insertBatch(tsTypeList)>0){
							flag = true;
						}
					}else{
						flag = true;
					}
				}else{
					flag = true;
				}
			}else{
				flag = true;
			}
			
		}
		return flag;
	}
	/**
	 * 更新险种
	 * @param insuranceType
	 * @return
	 */
	public boolean updateInsType(InsuranceType insuranceType){
		boolean flag = false;
		if(insuranceTypeMapper.updateByPrimaryKeySelective(insuranceType)>0){
			flag = true;
		}
		return flag;
	}
	/**
	 * 更新特殊类型
	 * @param insuranceTsType
	 * @return
	 */
	public boolean updateInsTsType(InsuranceTsType insuranceTsType){
		boolean flag = false;
		if(insuranceTsTypeMapper.updateByPrimaryKeySelective(insuranceTsType)>0){
			flag = true;
		}
		return flag;
	}
	/**
	 * 更新保险费率
	 * @param insuranceRate
	 * @return
	 */
	public boolean updateInsRate(InsuranceRate insuranceRate){
		boolean flag = false;
		if(insuranceRateMapper.updateInsRate(insuranceRate)>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 获取险种和特殊类型
	 * @return
	 */
	public HashMap<String, Object> selectTyAndTsTy(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<InsuranceType> typeList = insuranceTypeMapper.selectInsType();
		List<InsuranceTsType> tsTypeList = insuranceTsTypeMapper.selectInsTsType();
		map.put("typeList", typeList);
		map.put("tsTypeList", tsTypeList);
		return map;
	}
	
	/**
	 * 获取险种和类型
	 * @param companyId
	 * @param feeType
	 * @return
	 */
	public HashMap<String, Object> selectTyAndTsTyByCompanyId(Long companyId,Integer feeType){
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(feeType == 0){
			List<InsuranceType> typeList = insuranceTypeMapper.selectByComId(companyId);
			map.put("typeList", typeList);
		}else{
			List<InsuranceTsType> tsTypeList = insuranceTsTypeMapper.selectByComId(companyId);
			map.put("tsTypeList", tsTypeList);
		}
		return map;
	}
	
	/**
	 * 通过保险公司id获取所有险种
	 * @param companyId
	 * @return
	 */
	public List<InsuranceType> selectInsType(Long companyId){
		return insuranceTypeMapper.selectByComId(companyId);
	}
	/**
	 * 通过保险公司id获取特殊类型
	 * @param companyId
	 * @return
	 */
	public List<InsuranceTsType> selectInsTsType(Long companyId){
		return insuranceTsTypeMapper.selectByComId(companyId);
	}
	/**
	 * 获取所有保险公司
	 * @return
	 */
	public List<InsuranceCompany> selectInsCompany(){
		return insuranceCompanyMapper.selectInsCompany();
	}
	/**
	 * 添加保险费率
	 * @param insuranceRate
	 * @param user
	 * @return
	 */
	public boolean insertInsRate(InsuranceRate insuranceRate,SysUser user){
		boolean flag = false;
		insuranceRate.setInsStatus(1);
		insuranceRate.setCreatePersonId(user.getId());
		insuranceRate.setCreateTime(DateTools.getYMDHMS());
		if(insuranceRateMapper.insertSelective(insuranceRate)>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 通过公司标签获取险种
	 * @param companyTag
	 * @return
	 */
	public  List<InsuranceType> selectTypeByCompanyTag(String companyTag){
		return insuranceTypeMapper.selectByComTag(companyTag);
	}
	
	/**
	 * 根据公司标签获取特殊类型
	 * @param companyTag
	 * @return
	 */
	public List<InsuranceTsType> selectTsTypeByCompanyTag(String companyTag){
		return insuranceTsTypeMapper.selectByComTag(companyTag);
	}
	
	/**
	 * 判断公司是否存在
	 * @param comTag
	 * @return
	 */
	public boolean selectComByComTag(String comTag){
		boolean flag = false;
		if(insuranceCompanyMapper.countByComTag(comTag)>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 判断公司是否存在
	 * @param typeTag
	 * @return
	 */
	public boolean selectTypeByTypeTag(String typeTag){
		boolean flag = false;
		if(insuranceTypeMapper.countByTypeTag(typeTag)>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 判断特殊类型是否存在
	 * @param tsTypeTag
	 * @return
	 */
	public boolean selectTsTypeByTsTypeTag(String tsTypeTag){
		boolean flag = false;
		if(insuranceTsTypeMapper.countByTsTypeTag(tsTypeTag)>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 判断保险费率是否存在
	 * @param tsTypeTag
	 * @return
	 */
	public boolean selectRateByComAndTypeTag(String comTag,String typeTag){
		boolean flag = false;
		if(insuranceRateMapper.countByComAndType(comTag, typeTag)>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 判断保险费率是否存在
	 * @param tsTypeTag
	 * @return
	 */
	public boolean selectRateByComAndTsTypeTag(String comTag,String tsTypeTag){
		boolean flag = false;
		if(insuranceRateMapper.countByComAndTsType(comTag, tsTypeTag)>0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 删除保险公司
	 * @param comIds
	 * @return
	 */
	public boolean deleteCom(String[] comIds){
		insuranceCompanyMapper.deleteBatch(comIds);
		insComTypeMapper.deleteBatchByComIds(comIds);
		insComTsTypeMapper.deleteBatchByComIds(comIds);
		return true;
	}
	
	/**
	 * 删除险种
	 * @param comIds
	 * @return
	 */
	public boolean deleteType(String[] typeIds){
		insComTypeMapper.deleteBatchByTypeIds(typeIds);
		insuranceTypeMapper.deleteBatch(typeIds);
		return true;
	}
	
	/**
	 * 删除特殊类型
	 * @param comIds
	 * @return
	 */
	public boolean deleteTsType(String[] tsTypeIds){
		insComTsTypeMapper.deleteBatchByTsTypeIds(tsTypeIds);
		insuranceTsTypeMapper.deleteBatch(tsTypeIds);
		return true;
	}
	
	/**
	 * 删除保险费率
	 * @param comIds
	 * @return
	 */
	public boolean deleteInsRate(String[] rateIds){
		insuranceRateMapper.deleteBatch(rateIds);
		return true;
	}
	public InsuranceRate selectInsRateByComAndType(String insComTag,String insTypeTag){
		return insuranceRateMapper.selectByComTyTsTY(insComTag, insTypeTag, null);
	}
	public InsuranceRate selectInsRateByComAndTsType(String insComTag,String insTsTypeTag){
		return insuranceRateMapper.selectByComTyTsTY(insComTag, null, insTsTypeTag);
	}
	/**
	 * 获取所有险种
	 * @return
	 */
	public List<InsType> selectAllType(){
		return insuranceTypeMapper.selectAllType();
	}
}
