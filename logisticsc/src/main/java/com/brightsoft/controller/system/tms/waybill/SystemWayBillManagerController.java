package com.brightsoft.controller.system.tms.waybill;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.controller.base.BaseController;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.service.tms.platform.OutletsService;

/**
 * 专线营运系统 运单管理
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/tms/sys/waybill")
public class SystemWayBillManagerController extends BaseController{

	@Autowired
	private OutletsService outletsService;
	
	/**
	 * 跳转到 运单管理
	 * @return
	 */
	@RequestMapping("/towaybillmanagerpage")
	public String toWayBillManagerPage(HttpServletRequest request){
		List<OutletsInfo> outletsInfos = outletsService.selectAll();
		request.setAttribute("outletsInfos", outletsInfos);
		return "/system/tms/waybill/way-bill-manager";
	}
	
	/**
	 * 查询所有网点信息
	 * @return
	 */
	@RequestMapping("/getalloutletsinfo")
	@ResponseBody
	public Object getAllOutletsInfo(){
		return outletsService.selectAll();
	}
}
