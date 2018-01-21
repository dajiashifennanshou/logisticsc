package com.brightsoft.controller.tms.platform.startsitemanager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.common.enums.PayModeEnum;
import com.brightsoft.common.enums.ReceiptStatusEnum;
import com.brightsoft.common.enums.WayBillOrderStatusEnum;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.ReceiptBillSearchParams;
import com.brightsoft.controller.vo.WayBillSearchParams;
import com.brightsoft.model.AdditionalServerConf;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.User;
import com.brightsoft.model.WayBillOrder;
import com.brightsoft.model.WayBillOrderCargoInfo;
import com.brightsoft.model.WayBillOrderCostInfo;
import com.brightsoft.repeat.RepeatSubmission;
import com.brightsoft.service.tms.platform.AdditionalServerServiceImpl;
import com.brightsoft.service.tms.platform.CustomWayBillCostService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.startsitemanager.LadingOrderCargoInfoService;
import com.brightsoft.service.tms.platform.startsitemanager.LadingOrderCostInfoService;
import com.brightsoft.service.tms.platform.startsitemanager.LadingOrderService;
import com.brightsoft.service.tms.platform.startsitemanager.WayBillOrderCargoService;
import com.brightsoft.service.tms.platform.startsitemanager.WayBillOrderCostService;
import com.brightsoft.service.tms.platform.startsitemanager.WayBillOrderService;
import com.brightsoft.utils.ExportExcel;
import com.brightsoft.utils.Result;

/**
 * 运单管理控制类
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/tms/waybill")
public class WayBillManagerController extends BaseController{

	@Autowired
	private WayBillOrderService wayBillOrderService;
	
	@Autowired
	private LadingOrderService ladingOrderService;
	
	@Autowired
	private LadingOrderCargoInfoService ladingOrderCargoInfoService;
	
	@Autowired
	private LadingOrderCostInfoService ladingOrderCostInfoService;

	@Autowired
	private AdditionalServerServiceImpl additionalServerService;
	
	@Autowired
	private WayBillOrderCargoService wayBillOrderCargoService;
	
	@Autowired
	private WayBillOrderCostService wayBillOrderCostService;
	
	@Autowired
	private CustomWayBillCostService customWayBillCostService;
	
	@Autowired
	private OutletsService outletsService;
	
	/**
	 * 跳转到 开单入库页面
	 * @return
	 */
	@RequestMapping("/towaybillpage")
	@RepeatSubmission(needSaveToken = true)
	public String toWayBillPage(String wayBillNumber, String orderNumber, HttpServletRequest request){
		request.setAttribute("wayBillNumber", wayBillNumber);
		request.setAttribute("orderNumber", orderNumber);
		
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		AdditionalServerConf additionalServer = additionalServerService.selectByOutletsId(user.getOutletsId());
		request.setAttribute("additionalServer", additionalServer);
		request.setAttribute("payMethods", getPayMethod());
		request.setAttribute("outletsId", user.getOutletsId());
		return "/tms/platform/startsitemanager/way-bill";
	}
	
	// 获取 付款方式
	private List<Map<String, Object>> getPayMethod(){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		PayModeEnum[] payModeEnums = PayModeEnum.values();
		for (PayModeEnum payModeEnum : payModeEnums) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", payModeEnum.getValue());
			map.put("name", payModeEnum.getName());
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 跳转到运单管理页面
	 * @return
	 */
	@RequestMapping("/towaybillmanagerpage")
	public String toWayBillManagerPage(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		List<OutletsInfo> outletsInfos = outletsService.selectByCompanyId(user.getCompanyId());
		request.setAttribute("outletsInfos", outletsInfos);
		return "/tms/platform/startsitemanager/way-bill-manager";
	}
	
	/**
	 * 保存运单信息
	 * @param wayBillOrder
	 * @param wayBillOrderCargo
	 * @param wayBillOrderCostInfo
	 * @return
	 */
	@RequestMapping("/savewaybillorder")
	@ResponseBody
	@RepeatSubmission(needUpdateToken = true)
	public String saveWayBillOrder(String wayBillOrder, String wayBillOrderCargo, String wayBillOrderCostInfo, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = wayBillOrderService.saveWayBillOrder(wayBillOrder, wayBillOrderCargo, wayBillOrderCostInfo, user);
		if(rows > 0){
			return SUCCESS_STATUS;
		}else{
			return FAILURE_STATUS;
		}
	}
	
	/**
	 * 验证运单是否可编辑
	 * @param id
	 * @return
	 */
	@RequestMapping("/validwaybillisedit")
	@ResponseBody
	public String validWayBillIsEdit(String wayBillNumber){
		boolean flag = wayBillOrderService.validWayBillIsEdit(wayBillNumber);
		if(flag){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 获取 运单状态
	 * @return
	 */
	@RequestMapping("/getwaybillstatus")
	@ResponseBody
	public String getWayBillStatus(){
		String result = "[";
		WayBillOrderStatusEnum[] enums = WayBillOrderStatusEnum.values();
		for (int i = 0; i < enums.length; i++) {
			WayBillOrderStatusEnum oe = enums[i];
			if(i < enums.length - 1){
				result += "{'name':'" + oe.getName() + "','value':'" + oe.getValue() + "'},";
			}else{
				result += "{'name':'" + oe.getName() + "','value':'" + oe.getValue() + "'}";
			}
		}
		result += "]";
		return result;
	}
	
	/**
	 * 运单查询
	 * @param waybillStatus 运单状态
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param condition 条件
	 * @return
	 */
	@RequestMapping("/searchwaybillorder")
	@ResponseBody
	public Result searchWayBillOrder(WayBillSearchParams params, HttpSession session){
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
		return wayBillOrderService.selectByParams(params);
	}
	
	
	
	/**
	 * 获取 提货单信息数据
	 * @param wayBillNumber
	 * @return
	 */
	@RequestMapping("/getladingorderinfo")
	@ResponseBody
	public Object getLadingOrderInfo(String wayBillNumber){
		return ladingOrderService.selectByWayBillNumber(wayBillNumber);
	}
	
	/**
	 * 获取 提货单货物信息数据
	 * @param wayBillNumber
	 * @return
	 */
	@RequestMapping("/getladingordercargoinfo")
	@ResponseBody
	public Object getLadingOrderCargoInfo(String wayBillNumber){
		return ladingOrderCargoInfoService.selectByWayBillNumber(wayBillNumber);
	}
	
	/**
	 * 获取 提货单费用信息数据
	 * @param wayBillNumber
	 * @return
	 */
	@RequestMapping("/getladingordercostinfo")
	@ResponseBody
	public Object getLadingOrderCostInfo(String wayBillNumber){
		return ladingOrderCostInfoService.selectByWayBillNumber(wayBillNumber);
	}
	
	/**
	 * 跳转到 发站管理 配载工作台 页面
	 * @return
	 */
	@RequestMapping("/tostowageworkbenchpage")
	public String toStowageWorkbenchPage(HttpServletRequest request, String departNumber){
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		request.setAttribute("departNumber", departNumber);
		List<OutletsInfo> outletsInfos = outletsService.selectByCompanyId(user.getCompanyId());
		request.setAttribute("outletsInfos", outletsInfos);
		return "/tms/platform/startsitemanager/stowage-workbench";
	}
	
	/**
	 * 作废运单
	 * @param wayBillNumbers 多个运单号号 用逗号隔开
	 * @return
	 */
	@RequestMapping("/abolishwaybill")
	@ResponseBody
	public String abolishWayBill(String wayBillNumbers, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = wayBillOrderService.saveAbolishWayBill(wayBillNumbers, user);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 获取配载运单
	 * @return
	 */
	@RequestMapping("/getstowagewaybill")
	@ResponseBody
	public Object getStowageWayBill(HttpSession session, String targetOutlets){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		return wayBillOrderService.selectStowageWayBill(user.getOutletsId(), targetOutlets);
	}
	
	/**
	 * 获取已配载运单
	 * @return
	 */
	@RequestMapping("/getwaybillsbydepartnumber")
	@ResponseBody
	public Object getWayBillsByDepartNumber(String departNumber){
		if(StringUtils.isEmpty(departNumber)){
			return null;
		}
		return wayBillOrderService.selectByDepartNumber(departNumber);
	}
	
	/**
	 * 获取已配载外包运单
	 * @return
	 */
	@RequestMapping("/getwaybillsbyoutdepartnumber")
	@ResponseBody
	public Object getWayBillsByOutDepartNumber(String outDepartNumber){
		if(StringUtils.isEmpty(outDepartNumber)){
			return null;
		}
		return wayBillOrderService.selectByOutDepartNumber(outDepartNumber);
	}
	
	/**
	 * 跳转到运单详细信息页面
	 * @return
	 */
	@RequestMapping("/towaybilldetailpage")
	public String toWayBillDetailPage(HttpServletRequest request){
		String wayBillNumber = request.getParameter("wayBillNumber");
		WayBillOrder wayBillOrder = wayBillOrderService.selectByWayBillNumber(wayBillNumber);
		
		request.setAttribute("wayBillOrder", wayBillOrder);
		request.setAttribute("wayBillOrderCargoInfosJson", JSONObject.toJSONString(wayBillOrder.getWayBillOrderCargoInfos()));
		request.setAttribute("wayBillOrderCostInfosJson", JSONArray.toJSONString(wayBillOrder.getWayBillOrderCostInfos()));
		return "/tms/platform/startsitemanager/way-bill-detail";
	}
	
	/**
	 * 跳转到编辑运单页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toeditwaybillpage")
	@RepeatSubmission(needSaveToken = true)
	public String toEditWayBillPage(HttpServletRequest request){
		String id = request.getParameter("id");
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		AdditionalServerConf additionalServer = additionalServerService.selectByOutletsId(user.getOutletsId());
		request.setAttribute("additionalServer", additionalServer);
		request.setAttribute("wayBillOrderId", id);
		request.setAttribute("payMethods", getPayMethod());
		request.setAttribute("outletsId", user.getOutletsId());
		return "/tms/platform/startsitemanager/way-bill";
	}
	
	/**
	 * 获取运单信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/getwaybillorderinfo")
	@ResponseBody
	public Object getWayBillOrderInfo(String id){
		return wayBillOrderService.selectById(Long.parseLong(id));
	}
	
	/**
	 * 获取运单货物信息
	 * @param wayBillOrderId
	 * @return
	 */
	@RequestMapping("/getwaybillordercargoinfo")
	@ResponseBody
	public Object getWayBillOrderCargoInfo(String wayBillOrderId){
		return wayBillOrderCargoService.selectByWayBillOrderId(Long.parseLong(wayBillOrderId));
	}
	
	/**
	 * 获取运单费用信息
	 * @param wayBillOrderId
	 * @return
	 */
	@RequestMapping("/getwaybillordercostinfo")
	@ResponseBody
	public Object getWayBillOrderCostInfo(String wayBillOrderId){
		return wayBillOrderCostService.selectByWayBillOrderId(Long.parseLong(wayBillOrderId));
	}
	
	/**
	 * 跳转到回单管理页面
	 * @return
	 */
	@RequestMapping("/toreceiptmanagerpage")
	public String toReceiptManagerPage(HttpServletRequest request){
		request.setAttribute("receiptStatusList", getReceiptStatus());
		return "/tms/platform/receiptmanager/receipt-manager";
	}
	
	/**
	 * 获取回单状态
	 * @return
	 */
	private List<Map<String, Object>> getReceiptStatus(){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		ReceiptStatusEnum[] receiptStatusEnums = ReceiptStatusEnum.values();
		for (ReceiptStatusEnum receiptStatusEnum : receiptStatusEnums) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", receiptStatusEnum.getValue());
			map.put("name", receiptStatusEnum.getName());
			result.add(map);
		}
		return result;
	}
	
	// ------------------------------------------------------------------------------------
	
	/**
	 * 运单查询
	 * @param params
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Object search(WayBillSearchParams params, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			Long outletsId = user.getOutletsId();
			if(outletsId != null){
				params.setOutletsId(user.getOutletsId());
			}
		}
		return wayBillOrderService.selectByParams(params);
	}
	
	/**
	 * 运单查询
	 * @param params
	 * @return
	 */
	@RequestMapping("/searchreceipt")
	@ResponseBody
	public Object searchReceipt(WayBillSearchParams params, HttpSession session){
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
		params.setIsReceipt(1);
		return wayBillOrderService.selectByParams(params);
	}
	
	/**
	 * 跳转到 到站管理-查看运单
	 * @return
	 */
	@RequestMapping("/tofindwaybillpage")
	public String toFindWayBillPage(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		List<OutletsInfo> outletsInfos = outletsService.selectByCompanyId(user.getCompanyId());
		request.setAttribute("outletsInfos", outletsInfos);
		return "/tms/platform/arrivesitemanager/find-way-bill";
	}
	
	/**
	 * 到站管理 查看运单
	 * @param params
	 * @return
	 */
	@RequestMapping("/findwaybill")
	@ResponseBody
	public Result findWayBill(WayBillSearchParams params, HttpSession session){
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
		return wayBillOrderService.selectByParamsEnd(params);
	}
	
	/**
	 * 获取自定义运单费用 列表
	 * @param session
	 * @return
	 */
	@RequestMapping("/getcustomcost")
	@ResponseBody
	public Object getCustomCost(HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		return customWayBillCostService.selectByCompanyId(user.getCompanyId());
	}
	
	/**
	 * 修改 回单状态
	 * @param wayBillNumber
	 * @param receiptStatus
	 * @return
	 */
	@RequestMapping("/updatereceiptstatus")
	@ResponseBody
	public String updateReceiptStatus(String wayBillNumber, String receiptStatus){
		int rows = wayBillOrderService.updateReceiptStatus(wayBillNumber, receiptStatus);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 导出运单
	 * @param params
	 * @param session
	 * @return
	 */
	@RequestMapping("/exportwaybill")
	public void exportWayBill(WayBillSearchParams params, HttpSession session, HttpServletResponse response){
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
		String fileName = "运单列表";
		String keys[] ={
				"orderNumber","wayBillNumber",
				"startOutlets","targetOutlets",
				"targetProvince","targetCity","targetCounty",
				"consignor","consignorMobile","consignorAddress","consignorCompany",
				"consignee","consigneeMobile","consigneeAddress","consigneeCompany",
				"sendType","receiveType","receiptSlip","receiptSlipNum",
				"cityDriverName","cityDriverPhone","cityVehicleNumber",
				"payMethod","status","wayBillOrderTime","remark"
				};
		String[] columnNames = new String[]{
				"订单号","运单号",
				"起始网点","目的网点",
				"目的省","目的市","目的县",
				"发货人","发货人手机号","发货人地址","发货人公司",
				"收货人","收货人手机号","收货人地址","收货人公司",
				"发货方式","收货方式","是否回单","回单份数",
				"城配司机","司机电话","车牌号",
				"付款方式","状态","下单时间","备注"
				};
		List<Map<String, Object>> list = wayBillOrderService.selectExportByParams(params);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExportExcel.createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        //设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
        	response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            ServletOutputStream out = response.getOutputStream();
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        }catch (final IOException e) {
            e.printStackTrace();
        } finally {
        	try {
        		if (bis != null)
    				bis.close();
                if (bos != null)
                    bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	
	/**
	 * 导出回单
	 * @param params
	 * @param session
	 * @param response
	 */
	@RequestMapping("/exportReceipt")
	public void exportReceipt(ReceiptBillSearchParams params,HttpSession session,HttpServletResponse response){
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
		String fileName = "回单列表";
		String keys[] ={
				"orderNumber","wayBillNumber",
				"status","receiptSlip","receiptStatus",
				"startOutlets","targetOutlets",
				"consignor","consignorMobile",
				"consignee"
				};
		String[] columnNames = new String[]{
				"订单号","运单号",
				"运单状态","是否回单",
				"回单状态","出发网点","到达网点",
				"发货人","发货电话","收货人"
				};
		List<Map<String, Object>> list = wayBillOrderService.selectExportReceiptByParams(params);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExportExcel.createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        //设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
        	response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            ServletOutputStream out = response.getOutputStream();
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        }catch (final IOException e) {
            e.printStackTrace();
        } finally {
        	try {
        		if (bis != null)
    				bis.close();
                if (bos != null)
                    bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	
	/**
	 * 跳转到运单 打印预览页面
	 * @return
	 */
	@RequestMapping("/towaybillpreview")
	public String toWayBillPreview(HttpServletRequest request){
		String wayBillNumber = request.getParameter("wayBillNumber");
		WayBillOrder wayBillOrder = wayBillOrderService.selectByWayBillNumber(wayBillNumber);
		request.setAttribute("wayBillOrder", wayBillOrder);
		if(wayBillOrder != null){
			List<WayBillOrderCargoInfo> wayBillOrderCargoInfos = wayBillOrderCargoService.selectByWayBillOrderId(wayBillOrder.getId());
			List<WayBillOrderCostInfo> wayBillOrderCostInfos = wayBillOrderCostService.selectByWayBillOrderId(wayBillOrder.getId());
			request.setAttribute("wayBillOrderCargoInfos", wayBillOrderCargoInfos);
			request.setAttribute("wayBillOrderCostInfos", JSONArray.toJSONString(wayBillOrderCostInfos));
		}
		return "/tms/platform/startsitemanager/way-bill-preview";
	}
}
