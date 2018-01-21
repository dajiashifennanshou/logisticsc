package com.brightsoft.controller.tms.platform.syssetting;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.model.LineInfo;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformDictionary;
import com.brightsoft.model.User;
import com.brightsoft.service.platform.DictionaryService;
import com.brightsoft.service.system.platform.PosManagerService;
import com.brightsoft.service.tms.platform.LineInfoService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/lineinfo")
public class LineInfoController extends BaseController{

	@Autowired
	private LineInfoService lineInfoService;
	
	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private PosManagerService posManagerService;
	
	/**
	 * 跳转到线路显示页面
	 * @param session
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresAuthentication
	@RequiresPermissions("/tms/lineinfo/list.shtml")
	public ModelAndView toListLine(HttpSession session){
		ModelAndView mav = new ModelAndView("/tms/platform/systemmanager/lineinfo/listlineinfo");
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user!=null){
			Long outletsId = user.getOutletsId();
			List<OutletsInfo> outlets = outletsService.selectByCompanyId(user.getCompanyId());
			OutletsInfo startOutlets = null;
			if(outletsId != null){
				startOutlets = outletsService.selectById(outletsId);
			}
			List<PlatformDictionary> transportModes = dictionaryService.selectDictDataByType(DictionaryType.TRANSPORT_MODE);
			List<PlatformDictionary> serverTypes = dictionaryService.selectDictDataByType(DictionaryType.SERVER_TYPE);
			mav.addObject("startOutlets", outletsId!=null?startOutlets:outlets);
			mav.addObject("endOutlets", outlets);
			mav.addObject("transportModes", transportModes);
			mav.addObject("outletsId", outletsId);
			mav.addObject("serverTypes", serverTypes);
		}
		return mav;
	}
	/**
	 * 查询线路信息
	 * @param request
	 * @param response
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result toRoutePlan(LineInfo lineInfo,Page<?> page,HttpServletRequest request,
			HttpServletResponse response){
		Result result = new Result();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(null!=user){
			if(user.getOutletsId() != null){
				result = lineInfoService.selectByOutletsIdCondition(user.getOutletsId(), lineInfo, page);
			}else{
				result = lineInfoService.selectByCompanyIdCondition(user.getCompanyId(), lineInfo, page);
			}
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 线路信息添加
	 * @param lineInfo
	 * @param request
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Result addLineInfo(LineInfo lineInfo,HttpServletRequest request){
		Result result = new Result();
		
		if(lineInfo != null && lineInfo.getReleaseState().intValue() == 1){
			boolean flag = posManagerService.validOutletsIsBindCard(lineInfo.getStartOutlets());
			if(!flag){
				result.setMsg("线路发站网点未绑定银行卡，不能发布线路");
				result.setResult(false);
				return result;
			}
		}
		
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		/*if(user.getOutletsId()==null){
			result.setMsg("请选择网点");
		}else{*/
		if(null!=user){
			lineInfo.setCreateTime(DateTools.getYMDHMS());
			lineInfo.setCreatePersonId(user.getId());
//			lineInfo.setOutletsId(user.getOutletsId());
			lineInfo.setStatus(Const.TMS_LINE_STATUS_VALID);
			result = lineInfoService.insertLineInfo(user,lineInfo);
			
		}	
//		}
		return result;
	}
	
	/**
	 * 删除线路信息
	 * @param lineId
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteLine(String lineId,HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(lineId!=null){
			long lid = Long.parseLong(lineId);
			Map<String, Object> map = lineInfoService.deleteLine(user,lid);
			result.setResult((Boolean)map.get("success"));
			result.setMsg((String)map.get("msg"));
		}
		return result;
	}
	
	/**
	 * 更新线路信息
	 * @param lineInfo
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result updateLineInfo(LineInfo lineInfo,HttpSession session){
		Result result = new Result();
		
		if(lineInfo != null && lineInfo.getReleaseState().intValue() == 1){
			boolean flag = posManagerService.validOutletsIsBindCard(lineInfo.getStartOutlets());
			if(!flag){
				result.setMsg("线路发站网点未绑定银行卡，不能发布线路");
				result.setResult(false);
				return result;
			}
		}
		
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			result = lineInfoService.updateLineInfo(user,lineInfo);
		}else{
			result.setMsg("请先登录");
		}
		return result;
	}

	/**
	 * 省市县
	 * @param outletsId
	 * @return
	 */
	@RequestMapping("/rdxzqh")
	@ResponseBody
	public Result getProvinceCityCounty(Long outletsId){
		Result result = new Result();
		if(outletsId!=null){
			OutletsInfo outletsInfo = outletsService.selectById(outletsId);
			result.setData(outletsInfo);
			result.setResult(true);
		}
		return result;
	}
}
