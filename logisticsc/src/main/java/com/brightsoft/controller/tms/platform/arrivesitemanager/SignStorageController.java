package com.brightsoft.controller.tms.platform.arrivesitemanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.DepartListSearchParams;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.arrivesitemanager.SignRecordService;
import com.brightsoft.service.tms.platform.startsitemanager.DepartListService;
import com.brightsoft.service.tms.platform.startsitemanager.WayBillOrderService;

/**
 * 签到入库 管理 Controller
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("tms/signstorage")
public class SignStorageController extends BaseController{

	@Autowired
	private DepartListService departListService;
	
	@Autowired
	private WayBillOrderService wayBillOrderService;
	
	@Autowired
	private OutletsService outletsService;
	@Autowired
	private SignRecordService signRecordService;
	/**
	 * 跳转到 车辆签到入库页面
	 * @return
	 */
	@RequestMapping("/tosignstoragepage")
	public String toSignStoragePage(){
		return "/tms/platform/arrivesitemanager/sign-storage";
	}
	
	/**
	 * 获取 到站网点 的 发车单列表
	 * @param params
	 * @param session
	 * @return
	 */
	@RequestMapping("/getarrivedepartlist")
	@ResponseBody
	public Object getArriveDepartList(DepartListSearchParams params, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user.getOutletsId() == null||user.getOutletsId().equals("")){
			List<OutletsInfo> outs =outletsService.selectOutlesNotBind(user.getCompanyId());
			String ids = "";
			for(int i =0;i<outs.size();i++){
				if(i == 0){
					ids += outs.get(i).getId();
				}else{
					ids += ","+outs.get(i).getId();
				}
			}
			if(outs == null || outs.size() == 0){
				params.setOutletsIds("no");
			}else{
				params.setOutletsIds(ids);
			}
		}else{
			params.setOutletsIds(user.getOutletsId()+"");
		}
		return departListService.selectByParamsArrive(params);
	}
	
	/**
	 * 跳转到 部分入库页面
	 * @return
	 */
	@RequestMapping("/topartstoragepage")
	public String toPartStoragePage(String departListData, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		Long outletsId = user.getOutletsId();
		if(outletsId != null){
			request.setAttribute("outletsInfo", outletsService.selectById(outletsId));
		}
		request.setAttribute("departListData", departListData);
		return "/tms/platform/arrivesitemanager/part-storage";
	}
	
	/**
	 * 车辆签到
	 * @param departLists
	 * @return
	 */
	@RequestMapping("/sign")
	@ResponseBody
	public String sign(String departLists, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = departListService.saveSign(departLists, user);
		if(rows > 0){
			return SUCCESS_STATUS;
		}else{
			return FAILURE_STATUS;
		}
	}
	
	/**
	 * 验证途径网点是否卸货完毕
	 * @param departLists
	 * @return
	 */
	@RequestMapping("/validwayoutletsisunloadcargo")
	@ResponseBody
	public boolean validWayOutletsIsUnloadCargo(String departLists, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		return departListService.validWayOutletsIsUnloadCargo(departLists, user);
	}
	
	/**
	 * 获取运单信息
	 * @param wayBillNumbers
	 * @return
	 */
	@RequestMapping("/getwaybillorderlist")
	@ResponseBody
	public Object getWayBillOrderList(String wayBillNumbers){
		return wayBillOrderService.selectByWayBillNumbers(wayBillNumbers);
	}
	
	/**
	 * 整车入库
	 * @param departLists
	 * @return
	 */
	@RequestMapping("/wholevehiclestorage")
	@ResponseBody
	public String wholeVehicleStorage(String departLists, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = departListService.saveWholeVehicleStorage(departLists, user);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 运单部分入库
	 * @param wayBillNumber
	 * @return
	 */
	@RequestMapping("/waybillpartstorage")
	@ResponseBody
	public String wayBillPartStorage(String departNumber, String wayBillNumber, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = departListService.savePartStorage(departNumber, wayBillNumber, user);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	@RequestMapping({"/signallvehicle"})
	   @ResponseBody
	   public Object signAllVehicle(String departNumber, HttpSession session)
	   {
	     User user = (User)session.getAttribute("tms_user_session");
	     return this.signRecordService.saveSignRecordAllVehicle(departNumber, user);
	   }
}
