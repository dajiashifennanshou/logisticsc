package com.yc.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.CompanyOutletsLine;
import com.yc.Entity.LcPlatformOrder;
import com.yc.Entity.LcPlatformOrderAdditionalServer;
import com.yc.Entity.LcPlatformOrderCargo;
import com.yc.Entity.TmsWayBillOrder;
import com.yc.Service.PlatformOrderService;
import com.yc.Service.TmsLineInfoService;
import com.yc.Service.TmsWayBillOrderService;
import com.yc.Service.Impl.XzqhServiceImpl;
import com.yc.Tool.DES;
import com.yc.Tool.DateUtil;
import com.yc.Tool.FengUtil;
import com.yc.Tool.LogUtil;
import com.yc.Tool.Pager;
import com.yc.Tool.StrUtil;
/**
 * 订单查询
 * @Author:luojing
 * @2016年7月5日 下午1:37:43
 */
@Controller
public class PlatformOrderController {
	
	@Autowired
	private PlatformOrderService iLcPlatformOrderService;
	@Autowired
	private TmsLineInfoService iTmsLineInfoService;
	@Autowired
	private XzqhServiceImpl xzqhServiceImpl;
	@Autowired
	private TmsWayBillOrderService wayBillOrderService;
	
	/**
	 * 获取专线运输费用
	 * @Author:luojing
	 * @2016年7月5日 下午2:59:56
	 */
	@RequestMapping("app/getSpecialTransportation")
	public void getSpecialTransportation(HttpServletRequest request,HttpServletResponse response){
		try{
			Integer dealerId = Integer.parseInt(DES.decrypt(request.getParameter("dealerId")));
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			Integer page = Integer.parseInt(request.getParameter("page"));
			Integer rows = Integer.parseInt(request.getParameter("rows"));
			if(StrUtil.VString(startTime) && StrUtil.VString(endTime)){
				startTime = DateUtil.startTime(Long.valueOf(startTime));
				endTime = DateUtil.endTime(Long.valueOf(endTime));
			}
			Pager<TmsWayBillOrder> pager = new Pager<TmsWayBillOrder>(page, rows);
			pager = wayBillOrderService.getSpecialTransportation(pager, dealerId, startTime, endTime);
			FengUtil.OUTAPPPageObject(Constant.APP_SUCCESS, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"暂无结果", response);
		}
	}
	
	/**
	 * 线上、线下查询订单详情（运单号查询）
	 * Author:luojing
	 * 2016年6月28日 下午2:23:30
	 */
	@RequestMapping("app/getUserOrderDetails")
	public void getUserOrderDetails(HttpServletRequest request,HttpServletResponse response){
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			LcPlatformOrder order = (LcPlatformOrder) FengUtil.getObject(request.getParameterMap(),new LcPlatformOrder());
			if(order!=null){
				order = iLcPlatformOrderService.getOrder(order);
				//省
				order.setConsignee_province(xzqhServiceImpl.selectValueById(order.getConsignee_province()).getName());
				//市
				order.setConsignee_city(xzqhServiceImpl.selectValueById(order.getConsignee_city()).getName());
				//县
				order.setConsignee_county(xzqhServiceImpl.selectValueById(order.getConsignee_county()).getName());
				//省
				order.setConsignor_province(xzqhServiceImpl.selectValueById(order.getConsignor_province()).getName());
				//市
				order.setConsignor_city(xzqhServiceImpl.selectValueById(order.getConsignor_city()).getName());
				//县
				order.setConsignor_county(xzqhServiceImpl.selectValueById(order.getConsignor_county()).getName());
				map.put("order", order);
				//公司，网点，线路信息
				CompanyOutletsLine col = iTmsLineInfoService.getCompanyOutletsLine(new CompanyOutletsLine(order.getTms_line_id()));
				col.setProvince(xzqhServiceImpl.selectValueById(col.getProvince()).getName());
				col.setCity(xzqhServiceImpl.selectValueById(col.getCity()).getName());
				col.setCounty(xzqhServiceImpl.selectValueById(col.getCounty()).getName());
				map.put("col", col);
				//增值服务
				LcPlatformOrderAdditionalServer server = iLcPlatformOrderService.getOrderAdditionalServer(new LcPlatformOrderAdditionalServer(order.getId()));
				map.put("server", server);
				//订单商品信息
				List<LcPlatformOrderCargo> cargoList = iLcPlatformOrderService.getOrderCargoList(new LcPlatformOrderCargo(order.getId()));
				map.put("cargo", cargoList);
				FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, map, response);
			}else{
				FengUtil.OUTAPPERROR(Constant.APP_ERROR,"暂无结果", response);
			}
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"暂无结果", response);
		}
	}
}
