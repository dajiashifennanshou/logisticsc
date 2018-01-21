package com.brightsoft.controller.tms.platform.syssetting;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformDictionary;
import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.model.User;
import com.brightsoft.model.XzqhInfo;
import com.brightsoft.service.platform.DictionaryService;
import com.brightsoft.service.platform.PlatformUserCompanyService;
import com.brightsoft.service.platform.XzqhServiceImpl;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/outlets")
public class OutletsManageController {

	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private XzqhServiceImpl xzqhServiceImpl;
	
	@Autowired
	private PlatformUserCompanyService platformUserCompanyService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	/**
	 * 跳转到网点显示页面
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresAuthentication
	@RequiresPermissions("/tms/outlets/list.shtml")
	public ModelAndView toListOutlets(HttpSession session){
		ModelAndView mav = new ModelAndView();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user!=null){
			List<PlatformDictionary> outletsTypes = dictionaryService.selectDictDataByType(DictionaryType.OUTLETS_TYPE);
			List<PlatformDictionary> outletsNatures = dictionaryService.selectDictDataByType(DictionaryType.OUTLETS_NATURE);
			PlatformUserCompany company = platformUserCompanyService.selectCompanyInfo(user.getCompanyId());
			mav.addObject("company", company);
			mav.addObject("outletsTypes", outletsTypes);
			mav.addObject("outletsNatures", outletsNatures);
			mav.setViewName("/tms/platform/systemmanager/outletsinfo/listoutletsinfo");
//		}else{
//			mav.setViewName("/tms/platform/unauthorized");
		}
		return mav;
	}
	
	/**
	 * 查询网点信息
	 * @param currentPage
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result selectOutletsInfo4Page(OutletsInfo outletsInfo,Page<?> page,
			HttpServletRequest request){
		Result result = new Result();
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		if(null != user){
			if(user.getOutletsId() != null){
				List<OutletsInfo> outletsLst = new ArrayList<OutletsInfo>();
				OutletsInfo oi = outletsService.selectOutletsInfoById(user.getOutletsId());
				PlatformUserCompany platformUserCompany = platformUserCompanyService.selectCompanyInfo(user.getCompanyId());
				oi.setCompanyName(platformUserCompany.getCompanyName());
				oi.setCompanyCode(platformUserCompany.getCompanyCode());
				outletsLst.add(oi);
				result.setRows(outletsLst);
				result.setResults(1);
				result.setResult(true);
			}else{
				result = outletsService.selectBySelectedCondition(user,outletsInfo, page);
				result.setResult(true);
			}
			
		}
		return result;
	}
	
	/**
	 * 网点信息添加
	 * @param lineInfo
	 * @param request
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Result addOutlets(OutletsInfo outletsInfo,HttpServletRequest request){
		Result result = new Result();
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		if(null!=user){
			outletsInfo.setCreatePersonId(user.getId());
			outletsInfo.setCompanyId(user.getCompanyId());
			String outletsNum = outletsService.selectLastByCompanyId(user.getCompanyId());
			outletsInfo.setOutletsNumber(outletsService.serialNumberTranslate(outletsNum));
			result = outletsService.insertOutlets(user,outletsInfo);
		}	
		return result;
	}
	
	/**
	 * 删除网点信息
	 * @param lineId
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteLine(@RequestParam(value = "outletsId[]")String[] outletsId,HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(outletsId!=null&&outletsId.length!=0){
			List<Long> desList = new ArrayList<Long>();
			for(int i=0,j=outletsId.length;i<j;i++){
				desList.add(Long.parseLong(outletsId[i]));
			}
			result = outletsService.deleteOutlets(user,desList);
		}
		return result;
	}
	/**
	 * 更新网点信息
	 * @param lineInfo
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result updateLineInfo(OutletsInfo outletsInfo,HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			if(StringUtils.isBlank(outletsInfo.getName())
					||null==outletsInfo.getType()
					||null==outletsInfo.getNature()
					||StringUtils.isBlank(outletsInfo.getProvince())
					||StringUtils.isBlank(outletsInfo.getCity())
					||StringUtils.isBlank(outletsInfo.getCounty())
					||StringUtils.isBlank(outletsInfo.getContactPerson())
					||StringUtils.isBlank(outletsInfo.getPhone())
					||StringUtils.isBlank(outletsInfo.getAddress())){
				
				result.setResult(false); 
				result.setMsg("必填项不能为空");
			}else{
				outletsInfo.setCreateTime(DateTools.getYMDHMS());
				result = outletsService.updateOutletsInfo(user,outletsInfo);
			}
		}
		return result;
	}
	
	/**
	 * 通过区域pid获取区域信息
	 * @param xzqhPid
	 * @return
	 */
	@RequestMapping("/ajax/xzqh")
	@ResponseBody
	public Result getXzqhByPid(String pid){
		Result result = new Result();
		List<XzqhInfo> xzqhInfos = new ArrayList<XzqhInfo>();
		if(StringUtils.isBlank(pid)){
			xzqhInfos = xzqhServiceImpl.selectByPid(SystemConstant.PROVINCE_PID);
		}else{
			xzqhInfos = xzqhServiceImpl.selectByPid(pid);
		}
		result.setData(xzqhInfos);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 禁用网点
	 * @return
	 */
	@RequestMapping("/forbid")
	@ResponseBody
	public Result disabledOutlets(Long outletsId){
		Result result = new Result();
		if(outletsService.updateToDisabledOutlets(outletsId)){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 启用网点
	 * @return
	 */
	@RequestMapping("/enable")
	@ResponseBody
	public Result ennableOutlets(Long outletsId){
		Result result = new Result();
		if(outletsService.updateToEnableOutlets(outletsId)){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 通过登录用户获取网点信息
	 * @param request
	 * @return
	 */
	/*@RequestMapping("/getoutlets")
	@ResponseBody
	public Result getOutletsInfo(HttpServletRequest request){
		Result result = new Result();
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			List<OutletsInfo> list = new ArrayList<OutletsInfo>();
			if(user.getOutletsId()!=null){
				list.add(outletsService.selectOutletsById(user.getOutletsId()));
			}else{
				list = outletsService.selectByCompanyId(user.getCompanyId());
			}
			result.setRows(list);
			result.setData(user);
			result.setResult(true);
		}
		return result;
	}*/
	
	/**
	 * 通过网点id获取网点信息
	 * @param outletsId
	 * @return
	 */
	@RequestMapping("/getoutlets")
	@ResponseBody
	public Result getOutletsInfo(Long outletsId){
		Result result = new Result();
		if(outletsId != null){
			result.setData(outletsService.selectById(outletsId));
			result.setResult(true);
		}
		return result;
	}
	
	/*@RequestMapping("/getcmp")
	@ResponseBody
	public Result getCompanyInfo(HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			PlatformUserCompany company = platformUserCompanyService.selectCompanyInfo(user.getCompanyId());
			result.setData(company);
			result.setResult(true);
		}
		return result;
	}*/
	
}
