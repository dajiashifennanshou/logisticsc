package com.brightsoft.controller.tms.platform.syssetting;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.model.DriverInfo;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.DriverInfoService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/driver")
public class DriverInfoController {

	@Autowired
	private DriverInfoService driverInfoService;
	
	/**
	 * 跳转到司机显示页面
	 * @return
	 */
	@RequestMapping("/list")
	public String toListDriver(){
		return "/tms/platform/systemmanager/driverinfo/listdriverinfo";
	}
	/**
	 * 查询司机信息
	 * @param driverInfo
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result listDriverInfo(DriverInfo driverInfo,Page<?> page,
			HttpServletRequest request){
		Result result = new Result();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(null!=user){
			if(user.getOutletsId()!=null){
				result = driverInfoService.selectByOutletsIdAndCondition(driverInfo, page, user.getOutletsId());
			}else{
				result = driverInfoService.selectByCompanyIdAndCondition(driverInfo, page, user.getCompanyId());
			}
			result.setResult(true);
		}	
		return result;
	}
	
	/**
	 * 添加司机
	 * @param driverInfo
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Result addDriver(DriverInfo driverInfo,HttpServletRequest request){
		Result result = new Result();
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user!=null){
			if(user.getOutletsId() != null){
				driverInfo.setCreatePersonId(user.getId());
				driverInfo.setOutletsId(user.getOutletsId());
				driverInfo.setCreateTime(DateTools.getYMDHMS());
				driverInfo.setStatus(Const.TMS_DRIVER_STATUS_VALID);
				if(driverInfoService.insertDriverInfo(driverInfo)>0){
					result.setResult(true);
				}
			}else{
				result.setMsg("请选择网点");
			}
			
		}
		return result;
	}
	
	/**
	 * 删除司机
	 * @param driverId
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteDriver(@RequestParam("driverId[]")String driverId[]){
		Result result = new Result();
		if(driverId!=null&&driverId.length!=0){
			List<Long> desList = new ArrayList<Long>();
			for(int i=0,j=driverId.length;i<j;i++){
				desList.add(Long.parseLong(driverId[i]));
			}
			if(driverInfoService.deleteDriverById(desList)>0){
				result.setResult(true);
			}else{
				result.setResult(false);
				result.setMsg("记录未能成功被删除");
			}
		}
		return result;
	}
	/**
	 * 更新司机信息
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result updateDriver(DriverInfo driverInfo){
		Result result = new Result();
		if(driverInfoService.updateDriverInfo(driverInfo)>0){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 获取司机列表
	 * @param session
	 * @return
	 */
	@RequestMapping("/getdriverlist")
	@ResponseBody
	public Object getDriverList(HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		Long outletsId = user.getOutletsId();
		if(outletsId == null){
			Long companyId = user.getCompanyId();
			return driverInfoService.selectByCompanyId(companyId);
		}else{
			return driverInfoService.selectByOutletsId(outletsId);
		}
	}
	
	/**
	 * 获取司机信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getdriverinfo")
	@ResponseBody
	public Object getDriverInfo(Long id){
		return driverInfoService.selectById(id);
	}
}
