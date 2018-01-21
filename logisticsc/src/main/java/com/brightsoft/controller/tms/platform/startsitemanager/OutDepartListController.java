package com.brightsoft.controller.tms.platform.startsitemanager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.common.enums.OutDepartListStatusEnum;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.OutDepartList;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.User;
import com.brightsoft.model.WayBillOrder;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.startsitemanager.OutDepartListService;
import com.brightsoft.service.tms.platform.startsitemanager.WayBillOrderService;
import com.brightsoft.utils.ExportExcel;

/**
 * 外包发车单管理
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/tms/outdepart")
public class OutDepartListController extends BaseController{

	@Autowired
	private OutDepartListService outDepartListService;
	
	@Autowired
	private WayBillOrderService wayBillOrderService;
	
	@Autowired
	private OutletsService outletsService;
	
	/**
	 * 跳转到 外包出库页面
	 * @return
	 */
	@RequestMapping("/tooutsourcepage")
	public String toOutSourcePage(HttpServletRequest request, String outDepartNumber){
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		request.setAttribute("outDepartNumber", outDepartNumber);
		List<OutletsInfo> outletsInfos = outletsService.selectByCompanyId(user.getCompanyId());
		request.setAttribute("outletsInfos", outletsInfos);
		return "/tms/platform/startsitemanager/out-source";
	}
	 @RequestMapping({"/exportoutdepartlist"})
	   public void exportOutDepartList(SearchParams params, HttpSession session, HttpServletResponse response) {
	      User user = (User)session.getAttribute("tms_user_session");
	      if(user != null) {
	         Long outletsId = user.getOutletsId();
	         if(outletsId != null) {
	            params.setOutletsId(outletsId);
	            String fileName = "外包发车";
	            String[] keys = new String[]{"outSourceTime", "outDepartNumber", "status", "startOutletsName", "carriageCompany", "startSitePerson", "startSitePhone", "endSitePerson", "endSitePhone", "outSourceCost", "currentPay", "backPay"};
	            String[] columnNames = new String[]{"外包时间", "外包发车单号", "外包状态", "出发网点", "承运单位 ", "发站联系人", "发站联系电话", "到站联系人", "到站联系电话", "外包费合计", "现付", "回付"};
	            List list = this.outDepartListService.selectExportByParams(params);
	            ByteArrayOutputStream os = new ByteArrayOutputStream();

	            try {
	               ExportExcel.createWorkBook(list, keys, columnNames).write(os);
	            } catch (IOException var27) {
	               var27.printStackTrace();
	            }

	            byte[] content = os.toByteArray();
	            ByteArrayInputStream is = new ByteArrayInputStream(content);
	            response.reset();
	            response.setContentType("application/vnd.ms-excel;charset=utf-8");
	            BufferedInputStream bis = null;
	            BufferedOutputStream bos = null;

	            try {
	               response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
	               ServletOutputStream e = response.getOutputStream();
	               bis = new BufferedInputStream(is);
	               bos = new BufferedOutputStream(e);
	               byte[] buff = new byte[2048];

	               int bytesRead;
	               while(-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	                  bos.write(buff, 0, bytesRead);
	               }
	            } catch (IOException var28) {
	               var28.printStackTrace();
	            } finally {
	               try {
	                  if(bis != null) {
	                     bis.close();
	                  }

	                  if(bos != null) {
	                     bos.close();
	                  }
	               } catch (IOException var26) {
	                  var26.printStackTrace();
	               }

	            }

	         }
	      }
	   }
	/**
	 * 跳转到外包清单页面
	 * @return
	 */
	@RequestMapping("/tooutdepartlistpage")
	public String toOutDepartListPage(String outDepartNumber, HttpServletRequest request){
		if(StringUtils.isNotEmpty(outDepartNumber)){
			OutDepartList outDepartList = outDepartListService.selectByOutDepartNumber(outDepartNumber);
			request.setAttribute("outDepartList", outDepartList);
		}
		return "/tms/platform/startsitemanager/out-depart-list";
	}
	
	/**
	 * 保存 外包发车单信息
	 * @param outDepartListJson
	 * @param wayBillOutSourceRecordJson
	 * @return
	 */
	@RequestMapping("/saveoutdepartlist")
	@ResponseBody
	public String saveOutDepartList(String outDepartListJson, String wayBillOutSourceRecordJson, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = 0;
		try {
			rows = outDepartListService.saveOutDepartList(outDepartListJson, wayBillOutSourceRecordJson, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 跳转到外包管理页面
	 * @return
	 */
	@RequestMapping("/tooutsourcemanagerpage")
	public String toOutSourceManagerPage(HttpServletRequest request){
		request.setAttribute("outDepartStatusList", getOutDepartStatus());
		return "/tms/platform/startsitemanager/out-depart-list-manager";
	}
	
	// 获取 外包发车单状态
	private List<Map<String, Object>> getOutDepartStatus(){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		OutDepartListStatusEnum[] statusEnums = OutDepartListStatusEnum.values();
		for (OutDepartListStatusEnum statusEnum : statusEnums) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", statusEnum.getValue());
			map.put("name", statusEnum.getName());
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 外包发车单搜索
	 * @param params
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Object search(SearchParams params, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		params.setOutletsId(user.getOutletsId());
		return outDepartListService.selectByParams(params);
	}
	
	/**
	 * 外包出库
	 * @param outDepartLists
	 * @return
	 */
	@RequestMapping("/outsource")
	@ResponseBody
	public String outSource(String outDepartLists, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int row = outDepartListService.saveOutSource(outDepartLists, user);
		if(row > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 作废
	 * @param outDepartNumbers
	 * @return
	 */
	@RequestMapping("/discard")
	@ResponseBody
	public String discard(String outDepartNumbers){
		int row = outDepartListService.updateDiscard(outDepartNumbers);
		if(row > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 跳转到 外包发车单 详细信息页面
	 * @param outDepartNumber 外包发车单号
	 * @return
	 */
	@RequestMapping("/tooutdepartlistdetail")
	public String toOutDepartListDetail(HttpServletRequest request){
		String outDepartNumber = request.getParameter("outDepartNumber");
		OutDepartList outDepartList = outDepartListService.selectByOutDepartNumber(outDepartNumber);
		List<WayBillOrder> wayBillOrders = wayBillOrderService.selectByOutDepartNumber(outDepartNumber);
		request.setAttribute("outDepartList", outDepartList);
		request.setAttribute("wayBillOrders", JSONArray.toJSONString(wayBillOrders));
		return "/tms/platform/startsitemanager/out-depart-list-detail";
	}
}
