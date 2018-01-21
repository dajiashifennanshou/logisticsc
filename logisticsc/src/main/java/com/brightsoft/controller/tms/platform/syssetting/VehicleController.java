package com.brightsoft.controller.tms.platform.syssetting;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.DriverInfo;
import com.brightsoft.model.LineInfo;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformDictionary;
import com.brightsoft.model.User;
import com.brightsoft.model.VehicleInfo;
import com.brightsoft.service.platform.DictionaryService;
import com.brightsoft.service.tms.platform.LineInfoService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.VehicleInfoService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/vehicle")
public class VehicleController{
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private VehicleInfoService vehicleInfoService;
	
	@Autowired
	private LineInfoService lineInfoService;
	
	/**
	 * 跳转到车辆信息显示页面
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresAuthentication
	@RequiresPermissions("vehicle:handle")
	public ModelAndView toListVehicle(){
		ModelAndView mav = new ModelAndView("/tms/platform/systemmanager/vehicleinfo/listvehicleinfo");
		return mav;
	}
	
	/**
	 * 查询车辆信息
	 * @param vehicleInfo
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result listVehicleInfo(SearchParams searchParams,Page<?> page,
			HttpServletRequest request){
		Result result = new Result();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(null!=user){
			if(user.getOutletsId() != null){
				result = vehicleInfoService.selectByOutletsIdAndCondition(user.getOutletsId(), searchParams, page);
			}else{
				result = vehicleInfoService.selectByCompanyIdAndCondition(user.getCompanyId(), searchParams, page);
			}
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 跳转到车辆新增页面
	 * @return
	 */
	@RequestMapping("/insert")
	public ModelAndView toAddVehicle(HttpServletRequest request,VehicleInfo vehicleInfo){
		ModelAndView mav = new ModelAndView("/tms/platform/systemmanager/vehicleinfo/addvehicleinfo");

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(null!=user && user.getOutletsId() != null){	
			List<PlatformDictionary> cooperationWays = dictionaryService.selectDictDataByType(DictionaryType.COOPERATION_WAY);
			List<PlatformDictionary> vehicleTypes = dictionaryService.selectDictDataByType(DictionaryType.VEHICLE_TYPE);
			List<PlatformDictionary> vehicleLongs = dictionaryService.selectDictDataByType(DictionaryType.VEHICLE_LONG);
			
			OutletsInfo outletsInfos = outletsService.selectOutletsInfoById(user.getOutletsId());
//			List<LineInfo> lineInfos = lineInfoService.selectByOutletsId(user.getOutletsId());
			mav.addObject("cooperationWays", cooperationWays);
			mav.addObject("vehicleTypes", vehicleTypes);
			mav.addObject("vehicleLongs", vehicleLongs);
			mav.addObject("outletsInfos", outletsInfos);
//			mav.addObject("lineInfos", lineInfos);
		}
		return mav;
	}
	
	/**
	 * 车辆添加
	 * @param vehicleInfo
	 * @param driverInfo
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax/procinsert")
	@ResponseBody
	public Result addVehicle(VehicleInfo vehicleInfo,DriverInfo driverInfo,HttpServletRequest request){
		Result result = new Result();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			if(user.getOutletsId() != null){
				vehicleInfo.setOutletsId(user.getOutletsId());
				vehicleInfo.setCreatePersionId(user.getId());
				vehicleInfo.setCreateTime(DateTools.getYMD());
				vehicleInfo.setStatus(Const.TMS_VEHICLE_STATUS_VALID);
				if(vehicleInfoService.insertVehicleInfo(vehicleInfo, driverInfo)>0){
					result.setResult(true);
				}
			}else{
				result.setMsg("请选择网点");
			}
		}
		return result;
	}
	

	/**
	 * 跳转到更新车辆信息页面
	 * @param vehicleId
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	public ModelAndView toUpdateVehicle(Long vehicleId,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/tms/platform/systemmanager/vehicleinfo/updatevehicleinfo");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(vehicleId!=null){
			VehicleInfo vehicleInfo = vehicleInfoService.selectVehicleByid(vehicleId);
			List<PlatformDictionary> cooperationWays = dictionaryService.selectDictDataByType(DictionaryType.COOPERATION_WAY);
			List<PlatformDictionary> vehicleTypes = dictionaryService.selectDictDataByType(DictionaryType.VEHICLE_TYPE);
			List<PlatformDictionary> vehicleLongs = dictionaryService.selectDictDataByType(DictionaryType.VEHICLE_LONG);
			List<LineInfo> lineInfos = lineInfoService.selectByOutletsId(user.getOutletsId());
			OutletsInfo outletsInfos = outletsService.selectOutletsInfoById(user.getOutletsId());
			mav.addObject("outletsInfos", outletsInfos);
			mav.addObject("cooperationWays", cooperationWays);
			mav.addObject("vehicleTypes", vehicleTypes);
			mav.addObject("vehicleLongs", vehicleLongs);
			mav.addObject("lineInfos", lineInfos);
			mav.addObject("vehicleInfo", vehicleInfo);
		}
		return mav;
	}
	
	/**
	 * 更新车辆信息
	 * @param lineInfo
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax/procupdate")
	@ResponseBody
	public Result updateVehicleInfo(VehicleInfo vehicleInfo,DriverInfo driverInfo,
			Long driverId,Long vehicleId,HttpServletRequest request){
		Result result = new Result();
		vehicleInfo.setId(vehicleId);
		driverInfo.setId(driverId);
		if(vehicleInfoService.updateVehicleInfo(vehicleInfo, driverInfo)>0){
			result.setResult(true);
		}
		return result;
	}

	/**
	 * 车辆删除
	 * @param vehicleId
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteLine(@RequestParam("vehicleIds[]")String[] vehicleIds,HttpServletRequest request){
		Result result = new Result();
		if(vehicleIds==null||vehicleIds.length==0){
			result.setResult(false);
			result.setData("vehicleId不能为空");
		}else{
			if(vehicleInfoService.deleteVehicle(vehicleIds))
				result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 获取车辆信息
	 * @return
	 */
	@RequestMapping("/getvehicleinfolist")
	@ResponseBody
	public Object getVehicleInfoList(HttpSession session){
		Long outletsId = ((User)session.getAttribute(SystemConstant.TMS_USER_SESSION)).getOutletsId();
		return vehicleInfoService.selectByOutletsId(outletsId);
	}
	
	/**
	 * 根据 车牌号 查询 车辆信息
	 * @param vehicleNumber
	 * @return
	 */
	@RequestMapping("/getvehicleinfobyvehiclenumber")
	@ResponseBody
	public Object getVehicleInfoByVehicleNumber(String vehicleNumber, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		Long outletsId = user.getOutletsId();
		if(outletsId == null){
			return null;
		}
		return vehicleInfoService.selectByVehicleNumberAndOutletsId(vehicleNumber, outletsId);
	}
	
	/**
	 * 验证 车辆是否已添加
	 * @param vehicleNumber
	 * @param session
	 * @return
	 */
	@RequestMapping("/validisexistvehicle")
	@ResponseBody
	public boolean validIsExistVehicle(String vehicleNumber, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user == null){
			return false;
		}
		Long outletsId = user.getOutletsId();
		if(outletsId == null){
			return false;
		}
		return vehicleInfoService.validIsExistVehicle(outletsId, vehicleNumber);
	}
}
