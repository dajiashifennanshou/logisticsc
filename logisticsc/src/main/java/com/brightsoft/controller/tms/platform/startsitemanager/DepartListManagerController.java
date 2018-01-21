package com.brightsoft.controller.tms.platform.startsitemanager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.common.enums.DepartListStatusEnum;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.DepartListSearchParams;
import com.brightsoft.model.DepartList;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.User;
import com.brightsoft.model.WayBillOrder;
import com.brightsoft.service.platform.DictionaryService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.startsitemanager.DepartListService;
import com.brightsoft.service.tms.platform.startsitemanager.WayBillOrderService;
import com.brightsoft.utils.ExportExcel;

/**
 * 发车单管理
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/tms/depart")
public class DepartListManagerController extends BaseController{

	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private DepartListService departListService;
	
	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private WayBillOrderService wayBillOrderService;
	
	/**
	 * 跳转到 生成发车单页面
	 * @return
	 */
	@RequestMapping("/todepartlistpage")
	public String toDepartListPage(String wayBillData, String departNumber, HttpServletRequest request){
		request.setAttribute("wayBillData", wayBillData);
		request.setAttribute("departNumber", departNumber);
		
		/*User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		Long companyId = user.getCompanyId();
		List<OutletsInfo> outletsInfos = outletsService.selectByCompanyId(companyId);
		request.setAttribute("outletsInfos", outletsInfos);*/
		return "/tms/platform/startsitemanager/depart-list";
	}
	@RequestMapping({"/exportdepartlistlist"})
	public void exportDepartListList(DepartListSearchParams params, HttpSession session, HttpServletResponse response) {
	      User user = (User)session.getAttribute("tms_user_session");
	      if(user != null) {
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
	            String fileName = "发车单";
	            String[] keys = new String[]{"operateTime", "departNumber", "startOutletsName", "wayOutletsName", "targetOutletsName", "receivableTotal","isCompletedFreight", "agencyFundTotal","isCompletedAgencyFund", "vehicleNumber", "driverName", "driverPhone", "shouldPayDriverCost"};
	            String[] columnNames = new String[]{"发车时间", "发车单号", "出发网点", "途径网点", "到达网点", "应收运费合计", "是否完结","代收货款合计", "是否完结", "车牌号", "司机姓名", "司机电话", "应付司机"};
	            List list = this.departListService.selectExportByParams(params);
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
	/**
	 * 获取车辆类型
	 * @return
	 */
	@RequestMapping("/getvehicletype")
	@ResponseBody
	public Object getVehicleType(){
		return dictionaryService.selectDictDataByType(DictionaryType.VEHICLE_TYPE);
	}
	
	/**
	 * 保存 发车清单信息
	 * @param departList
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String saveDepartListInfo(DepartList departList, HttpSession session){
		
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		departList.setOperatePerson(user.getId());
		int rows = departListService.saveDepartListInfo(departList);
		if(rows > 0){
			return SUCCESS_STATUS;
		}else{
			return FAILURE_STATUS;
		}
	}
	
	/**
	 * 跳转到发车管理页面
	 * @return
	 */
	@RequestMapping("/todepartlistmanagerpage")
	public String toDepartListManagerPage(HttpServletRequest request){
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user.getOutletsId() == null){
			List<OutletsInfo> outletsInfos = outletsService.selectByCompanyId(user.getCompanyId());
			request.setAttribute("outletsInfos", outletsInfos);
		}
		return "/tms/platform/startsitemanager/depart-list-manager";
	}
	
	/**
	 * 查询发车单列表信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Object search(DepartListSearchParams params, HttpSession session){
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
		return departListService.selectByParams(params);
	}
	
	/**
	 * 查询应收 发车单 列表
	 * @param session
	 * @return
	 */
	@RequestMapping("/getreceivabledepartlist")
	@ResponseBody
	public Object getReceivableDepartList(DepartListSearchParams params, HttpSession session){
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
		return departListService.selectByParams(params);
	}
	
	/**
	 * 获取发车单状态
	 * @return
	 */
	@RequestMapping("/getdepartliststatus")
	@ResponseBody
	public String getDepartListStatus(){
		String result = "[";
		DepartListStatusEnum[] enums = DepartListStatusEnum.values();
		for (int i = 0; i < enums.length; i++) {
			DepartListStatusEnum oe = enums[i];
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
	 * 发车出库(配载时直接发出出库)
	 * @param departList
	 * @return
	 */
	@RequestMapping("/savedeparture")
	@ResponseBody
	public String departure(DepartList departList, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		departList.setOperatePerson(user.getId());
		int rows = departListService.saveDeparture(departList, user);
		if(rows > 0){
			return SUCCESS_STATUS;
		}else{
			return FAILURE_STATUS;
		}
	}
	
	/**
	 * 发车出库(发车单管理-发车出库)
	 * @param departLists
	 * @return
	 */
	@RequestMapping("/updatedeparture")
	@ResponseBody
	public String departure(String departLists, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		int rows = departListService.updateDeparture(departLists, user);
		if(rows > 0){
			return SUCCESS_STATUS;
		}else{
			return FAILURE_STATUS;
		}
	}
	
	/**
	 * 获取发车单信息
	 * @param departNumber
	 * @return
	 */
	@RequestMapping("/getdepartlistinfo")
	@ResponseBody
	public Object getDepartListInfo(String departNumber){
		return departListService.selectByDepartBumber(departNumber);
	}
	
	/**
	 * 跳转到 发车单详细信息页面
	 * @param departNumber
	 * @return
	 */
	@RequestMapping("/todepartlistdetail")
	public String toDepartListDetailPage(String departNumber, String isReceiveMoney, HttpServletRequest request){
		DepartList departList = departListService.selectByDepartBumber(departNumber);
		request.setAttribute("isReceiveMoney", isReceiveMoney);
		request.setAttribute("departList", departList);
		return "/tms/platform/startsitemanager/depart-list-detail";
	}
	
	/**
	 * 作废 发车单
	 * @param departNumbers
	 * @return
	 */
	@RequestMapping("/abolish")
	@ResponseBody
	public String abolishDepartList(String departNumbers){
		int rows = departListService.updateStatusByDepartNumbers(departNumbers, DepartListStatusEnum.ABOLISHED);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 跳转到发车清单 打印页面
	 * @return
	 */
	@RequestMapping("/todepartlistpreview")
	public String toDepartListPreview(HttpServletRequest request){
		String departNumber = request.getParameter("departNumber");
		DepartList departList = departListService.selectByDepartBumber(departNumber);
		List<WayBillOrder> wayBillOrders = wayBillOrderService.selectByDepartNumber(departNumber);
		request.setAttribute("departList", departList);
		request.setAttribute("wayBillOrders", JSONArray.toJSONString(wayBillOrders));
		return "/tms/platform/startsitemanager/depart-list-preview";
	}
}
