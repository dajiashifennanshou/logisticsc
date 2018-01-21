package com.brightsoft.controller.tms.platform.arrivesitemanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.common.enums.ReceiveTypeEnum;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.WayBillSearchParams;
import com.brightsoft.model.DriverInfo;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.User;
import com.brightsoft.model.VehicleInfo;
import com.brightsoft.model.WayBillOutStoreRecord;
import com.brightsoft.repeat.RepeatSubmission;
import com.brightsoft.service.tms.platform.DriverInfoService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.VehicleInfoService;
import com.brightsoft.service.tms.platform.arrivesitemanager.WayBillOutStoreRecordService;
import com.brightsoft.service.tms.platform.startsitemanager.WayBillOrderService;

/**
 * 送货管理
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/tms/sendcargo")
public class SendCargoManagerController extends BaseController{

	@Autowired
	private WayBillOrderService wayBillOrderService;
	
	@Autowired
	private VehicleInfoService vehicleInfoService;
	
	@Autowired
	private WayBillOutStoreRecordService wayBillOutStoreRecordService;
	
	@Autowired
	private DriverInfoService driverInfoService;
	@Autowired
	private OutletsService outletsService;
	/**
	 * 跳转到 自提页面
	 * @return
	 */
	@RequestMapping("toselfpickpage")
	@RepeatSubmission(needSaveToken = true)
	public String toSelfPickPage(){
		return "/tms/platform/arrivesitemanager/self-pick";
	}
	
	/**
	 * 获取自提的运单
	 * @param 
	 * @return
	 */
	@RequestMapping("/getwaybillofselfpick")
	@ResponseBody
	public Object getWayBillOfSelfPick(WayBillSearchParams params, HttpSession session){
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
		return wayBillOrderService.selectByReceiveTypeAndCondition(params, ReceiveTypeEnum.SELF_PICK);
	}
	
	/**
	 * 跳转到 送货上门页面
	 * @return
	 */
	@RequestMapping("tohomedeliverypage")
	public String toHomeDeliveryPage(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		Long outletsId = user.getOutletsId();
		if(outletsId != null){
			List<VehicleInfo> vehicleInfos = vehicleInfoService.selectByOutletsId(user.getOutletsId());
			List<DriverInfo> driverInfos = driverInfoService.selectByOutletsId(user.getOutletsId());
			request.setAttribute("vehicleInfos", vehicleInfos);
			request.setAttribute("driverInfos", driverInfos);
		}
		return "/tms/platform/arrivesitemanager/home-delivery";
	}
	
	/**
	 * 获取送货上门的运单
	 * @param 
	 * @return
	 */
	@RequestMapping("/getwaybillofhomedelivery")
	@ResponseBody
	public Object getWayBillOfHomeDelivery(WayBillSearchParams params, HttpSession session){
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
		return wayBillOrderService.selectByReceiveTypeAndCondition(params, ReceiveTypeEnum.HOME_DELIVERY);
	}
	
	/**
	 * 更改配送方式
	 * @param receiveType
	 * @param wayBillNumbers
	 * @return
	 */
	@RequestMapping("/updatereceivetype")
	@ResponseBody
	public Object updateReceiveType(String receiveType, String wayBillNumbers){
		int rows = wayBillOrderService.updateReceiveType(receiveType, wayBillNumbers);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 跳转到中转运单页面
	 * @return
	 */
	@RequestMapping("/totransferwaybillpage")
	public String toTransferWayBillPage(){
		return "/tms/platform/arrivesitemanager/transfer-way-bill-manager";
	}
	
	/**
	 * 获取中转的运单
	 * @param 
	 * @return
	 */
	@RequestMapping("/getwaybilloftransfer")
	@ResponseBody
	public Object getWayBillOfTransfer(WayBillSearchParams params, HttpSession session){
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
		return wayBillOrderService.selectTransferWayBillByCondition(params);
	}
	
	/**
	 * 获取 签收运单列表
	 * @param params
	 * @param session
	 * @return
	 */
	@RequestMapping("/getsignwaybilllist")
	@ResponseBody
	public Object getSignWayBillList(WayBillSearchParams params, HttpSession session){
		/*ReceiveTypeEnum receiveTypeEnum = null;
		if(StringUtils.isNotEmpty(params.getReceiveType())){
			ReceiveTypeEnum[] typeEnums = ReceiveTypeEnum.values();
			for (ReceiveTypeEnum typeEnum : typeEnums) {
				if(typeEnum.getValue() == Integer.parseInt(params.getReceiveType())){
					receiveTypeEnum = typeEnum;
					break;
				}
			}
		}*/
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
		return wayBillOrderService.selectSignWayBillList(params);
		//return wayBillOrderService.selectByReceiveTypeAndCondition(params, receiveTypeEnum);
	}
	
	/**
	 * 保存运单出库记录
	 * @param wayBillOutStoreRecord
	 * @return
	 */
	@RequestMapping("/savewaybilloutstorerecord")
	@ResponseBody
	public String saveWayBillOutStoreRecord(WayBillOutStoreRecord wayBillOutStoreRecord, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = wayBillOutStoreRecordService.save(wayBillOutStoreRecord, user);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
}
