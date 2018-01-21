package com.brightsoft.controller.tms.platform.syssetting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.OutletsPriceRangeConf;
import com.brightsoft.model.User;
import com.brightsoft.model.XzqhInfo;
import com.brightsoft.service.platform.XzqhServiceImpl;
import com.brightsoft.service.tms.platform.OutletsConfService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/range/conf")
public class OutletsConfController {

	@Autowired
	private XzqhServiceImpl xzqhServiceImpl;
	
	@Autowired
	private OutletsConfService outletsConfService;
	
	@Autowired
	private OutletsService outletsService;
	
	/**
	 * 跳转到提送货信息页面
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresAuthentication
	@RequiresPermissions("/tms/range/conf/list.shtml")
	public String toListConf(){
		return "/tms/platform/systemmanager/outletsconf/listoutletsconf";
	}
	/**
	 * 查询提送货信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result toOutletsConf(SearchParams searchParams,Page<?> page,HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user!=null){
			if(user.getOutletsId() != null){
				result = outletsConfService.selectByOutletsIdAndCondition(searchParams, page, user.getOutletsId());
			}else{
				result = outletsConfService.selectByCompanyIdAndCondition(searchParams, page, user.getCompanyId());
			}
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 添加提送货信息
	 * @param outletsPriceRangeConf
	 * @param request
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Result addOutletsConf(OutletsPriceRangeConf outletsPriceRangeConf,HttpServletRequest request){
		Result result = new Result();
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user.getOutletsId()==null){
			result.setMsg("请选择网点");
		}else{
			outletsPriceRangeConf.setOutletsId(user.getOutletsId());
			outletsPriceRangeConf.setStatus(1);
			if(outletsConfService.insert(outletsPriceRangeConf)>0){
				result.setResult(true);
			}
		}
		return result;
	}
	
	/**
	 * 更新提送货信息
	 * @param outletsPriceRangeConf
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result updateConf(OutletsPriceRangeConf outletsPriceRangeConf){
		Result result = new Result();
		if(outletsConfService.updateConf(outletsPriceRangeConf)>0){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 删除信息
	 * @param confIds
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteConf(@RequestParam("confIds[]")String confIds[]){
		Result result = new Result();
		if(confIds!=null&&confIds.length>0){
			List<Long> ids = new ArrayList<Long>();
			for (String confId : confIds) {
				ids.add(Long.parseLong(confId));
			}
			if(outletsConfService.deleteConf(ids)>0){
				result.setResult(true);
			}
		}
		return result;
	}
	
	/**
	 * 通过区域pid获取区域信息
	 * @param xzqhPid
	 * @return
	 */
	@RequestMapping("/render")
	@ResponseBody
	public Result getRenderItmes(HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user!=null){
			if(user.getOutletsId()!=null){
				OutletsInfo outletsInfo = outletsService.selectById(user.getOutletsId());
				String city = outletsInfo.getCity();
				List<XzqhInfo> xzqhs =  xzqhServiceImpl.selectByPid(city);
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put("xzqhs", xzqhs);
				map.put("outletsInfo", outletsInfo);
				result.setSummary(map);
				result.setResult(true);
			}
		}
		return result;
	}
	
	/**
	 * 验证 提送货管理 区域配置是否 已存在
	 * @param county
	 * @return
	 */
	@RequestMapping("/validisexistcounty")
	@ResponseBody
	public boolean validIsExistCounty(String county, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user == null){
			return false;
		}
		Long outletsId = user.getOutletsId();
		if(outletsId == null){
			return false;
		}
		return outletsConfService.validIsExistCounty(outletsId, county);
	}
}
