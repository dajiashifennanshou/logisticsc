package com.brightsoft.service.tms.platform;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.tms.OutletsInfoMapper;
import com.brightsoft.dao.tms.UserMapper;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.User;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class OutletsService{

	@Autowired
	private OutletsInfoMapper outletsInfoMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 分页获取网点信息
	 */
	public Result selectBySelectedCondition(User user,OutletsInfo outletsInfo, Page<?> page) {
		Result result = new Result();
		if(user.getOutletsId() == null){
			int results = outletsInfoMapper.countRows(outletsInfo,user);
			List<OutletsInfo> rows = outletsInfoMapper.selectBySelectedCondition(user,outletsInfo, page);
			result.setResults(results);
			result.setRows(rows);
		}else{
			result.setMsg("你没有权限查询网点信息");
		}
		return result;
	}

	/**
	 * 添加网点信息
	 */
	public Result insertOutlets(User user,OutletsInfo outletsInfo) {	
		Result result = new Result();
		if(user.getOutletsId() == null){
			outletsInfo.setCreateTime(DateTools.getYMDHMS());
			outletsInfo.setStatus(Const.TMS_OUTLETS_STATUS_ENNABLE);
			if(outletsInfoMapper.insert(outletsInfo)>0){
				result.setResult(true);
			}
		}else{
			result.setMsg("你没有权限执行当前操作！");
		}
		
		return result;
	}

	/**
	 * 更新网点信息
	 */
	public Result updateOutletsInfo(User user,OutletsInfo outletsInfo) {
		Result result = new Result();
		if(user.getOutletsId() == null){
			if(outletsInfoMapper.updateByPrimaryKeySelective(outletsInfo)>0){
				result.setResult(true);
			}
		}else{
			result.setMsg("你没有权限执行当前操作！");
		}
		
		return result;
	}

	/**
	 * 批量删除网点
	 */
	public Result deleteOutlets(User user,List<Long> idList) {
		Result result = new Result();
		if(user.getOutletsId() == null){
			//判断当前网点能否删除
			for (Long id : idList) {
				int count = userMapper.getResultTotalByOutletsId(id);
				if(count>0){
					result.setMsg("当前网点不能删除");
					return result;
				}
			}
			if(outletsInfoMapper.deleteBatch(idList)>0){
				result.setResult(true);
			}
		}else{
			result.setMsg("你没有权限执行当前操作！");
		}
		return result;
	}
	
	/**
	 * 通过网点id获取网点信息
	 */
	public OutletsInfo selectOutletsInfoById(Long outletsInfoId) {
		return outletsInfoMapper.selectByPrimaryKey(outletsInfoId);
	}

	/**
	 * 通过主键ID 查询网点信息
	 * @param id
	 * @return
	 */
	public OutletsInfo selectByPrimaryKey(long id){
		return outletsInfoMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 通过企业ID查询最近一条 网点信息
	 * @param companyId
	 * @return
	 */
	public String selectLastByCompanyId(long companyId){
		return outletsInfoMapper.selectSerNumByCompanyId(companyId);
	}
	
	/**
	 * 获取起始网点信息
	 * @param companyId
	 * @return
	 */
	public List<OutletsInfo> selectStartOutletsByUser(User user){
		return outletsInfoMapper.selectStartOutletsByUser(user);
	}
	
	/**
	 * 获取到站网点信息
	 * @param companyId
	 * @return
	 */
	public List<OutletsInfo> selectEndOutletsByUser(User user){
		return outletsInfoMapper.selectEndOutletsByUser(user);
	}
	
	/**
	 * 通过id获取网点信息
	 * @return
	 */
	public OutletsInfo selectById(long outletsId){
		return outletsInfoMapper.selectById(outletsId);
	}
	
	/**
	 * 根据网点id获取网点信息
	 * @param outletsId
	 * @return
	 */
	/*public OutletsInfo selectOutletsById(long outletsId){
		return outletsInfoMapper.selectOutletsById(outletsId);
	}*/
	
	/**
	 * 获取企业网点信息
	 * @param companyId
	 * @return
	 */
	public List<OutletsInfo> selectByCompanyId(Long companyId) {
		return outletsInfoMapper.selectByCompanyId(companyId);
	}
	
	/**
	 * 查询所有网点信息
	 * @return
	 */
	public List<OutletsInfo> selectAll(){
		return outletsInfoMapper.selectAll();
	}

	/**
	 * 通过公司信息查询网点
	 * 2016年3月23日 下午4:05:01
	 * @param user
	 * @param outletsInfo
	 * @param page
	 * @return 
	 * @author zhouna
	 */
	public Result getlineshopCompang(User user, OutletsInfo outletsInfo,Page<?> page) {
		Result result = new Result();
		try {
			int results = outletsInfoMapper.countoutcompRows(outletsInfo,user);
			List<OutletsInfo> rows = outletsInfoMapper.selectBySelcompCondition(user,outletsInfo, page);
			result.setResults(results);
			result.setRows(rows);
			result.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
		}
		return result;
	}
	
	/**
	 * 货运交易系统：查询网点
	 * @return
	 */
	public Result selectAllOutlets(SearchParams searchParams,Page<?> page){
		Result result  = new Result();
		int results = outletsInfoMapper.countAllOutlets(searchParams);
		List<OutletsInfo> rows = outletsInfoMapper.selectAllOutlets(searchParams, page);
		result.setResults(results);
		result.setRows(rows);
		return result;
	}
	
	/**
	 * 禁用网点
	 * @param outletsId
	 * @return
	 */
	public boolean updateToDisabledOutlets(Long outletsId){
		if(outletsInfoMapper.updateStatus(outletsId, Const.TMS_OUTLETS_STATUS_DISABLED)>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 启用网点
	 * @param outletsId
	 * @return
	 */
	public boolean updateToEnableOutlets(Long outletsId){
		if(outletsInfoMapper.updateStatus(outletsId, Const.TMS_OUTLETS_STATUS_ENNABLE)>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 网点编号生成
	 * @param source
	 * @return
	 */
	public String serialNumberTranslate(String source){
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isBlank(source)){
			sb.append("01");
		}else{
			int number = Integer.parseInt(source);
			number += 1;
			for(int i=0;i<2-(number+"").length();i++){
				sb.append("0");
			}
			sb.append(number);
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * 方法描述：获取未绑定用户的网点
	 * @param companyId
	 * @return
	 * @author dub
	 * @version 2016年5月14日 下午4:21:24
	 */
	public List<OutletsInfo> selectOutlesNotBind(Long companyId){
		return outletsInfoMapper.selectOutletsNotBind(companyId);
	}
}
