package com.brightsoft.controller.tms.platform.arrivesitemanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.TransferDepartList;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.arrivesitemanager.TransferDepartListService;

/**
 * 中转 管理 Controller
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("tms/transfer")
public class TransferManagerController extends BaseController{

	@Autowired
	private TransferDepartListService transferDepartListService;
	@Autowired
	private OutletsService outletsService;
	
	/**
	 * 跳转到 中转清单页面
	 * @return
	 */
	@RequestMapping("/totransferwaybilllist")
	public String toTransferWayBillListPage(){
		return "/tms/platform/arrivesitemanager/transfer-way-bill-list";
	}
	
	/**
	 * 保存 中转发车单信息
	 * @param outDepartListJson
	 * @param wayBillOutSourceRecordJson
	 * @return
	 */
	@RequestMapping("/savetransferlist")
	@ResponseBody
	public String saveTransferList(String transferDepartListJson, String wayBillTransferRecordJson, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = 0;
		try {
			rows = transferDepartListService.saveTransferDepartList(transferDepartListJson, wayBillTransferRecordJson, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 跳转到 中转列表页面
	 * @return
	 */
	@RequestMapping("/totransferlistmanager")
	public String toTransferListManagerPage(){
		return "/tms/platform/arrivesitemanager/transfer-list-manager";
	}
	
	/**
	 * 获取 中转列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/gettransferlist")
	@ResponseBody
	public Object getTransferList(BaseSearchParams params, HttpSession session){
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
		return transferDepartListService.selectByParams(params);
	}
	
	/**
	 * 跳转到 中转订单详情页面
	 * @return
	 */
	@RequestMapping("/totransferlistdetail")
	public String toTransferListDetailPage(HttpServletRequest request){
		String transferDepartNumber = request.getParameter("transferDepartNumber");
		TransferDepartList transferDepartList = transferDepartListService.selectByTransferDepartNumber(transferDepartNumber);
		request.setAttribute("transferDepartList", transferDepartList);
		request.setAttribute("wayBillOrders", JSONArray.toJSONString(transferDepartList.getWayBillOrders()));
		
		return "/tms/platform/arrivesitemanager/transfer-list-detail";
	}
}
