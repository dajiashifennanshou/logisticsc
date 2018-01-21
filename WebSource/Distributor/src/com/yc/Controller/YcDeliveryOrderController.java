package com.yc.Controller; 
import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.LcPlatformUser;
import com.yc.Entity.YcDeliveryOrder;
import com.yc.Entity.YcGoods;
import com.yc.Entity.YcOrderGoods;
import com.yc.Service.IYcDeliveryOrderService;
import com.yc.Service.IYcGoodsService;
import com.yc.Service.IYcOrderGoodsService;
import com.yc.Tool.DES;
import com.yc.Tool.DateUtil;
import com.yc.Tool.FengUtil;
import com.yc.Tool.LogUtil;
import com.yc.Tool.Pager;
import com.yc.Tool.StrUtil;

/** 
* YcDeliveryOrder控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcDeliveryOrderController {  
	@Autowired 
	private IYcDeliveryOrderService iYcDeliveryOrderService; 
	@Autowired 
	private IYcOrderGoodsService iYcOrderGoodsService; 
	@Autowired 
	private IYcGoodsService iYcGoodsService ; 

	
	/** 
	* 获取单条记录 即：详情
	* Auther:FENG 
	*/ 
	@RequestMapping("app/getYcDeliveryOrderSingle") 
	public void getYcDeliveryOrderSingle(HttpServletRequest request,HttpServletResponse response) {  
		try{
			YcDeliveryOrder ycdo = (YcDeliveryOrder) FengUtil.getObject(request.getParameterMap(), new YcDeliveryOrder());
			//需要id或者其他属于本ycdo对象的数据作为条件
			ycdo=iYcDeliveryOrderService.getSingleInfo(ycdo);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, ycdo, response);
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"暂无结果", response);
		}
	} 
	/** 
	* 获取配送单分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("app/getYcDeliveryOrderList")
	public void getYcDeliveryOrderList(HttpServletRequest request,HttpServletResponse response) {  
		try{
			Integer page = Integer.parseInt(request.getParameter("page"));
			Integer rows = Integer.parseInt(request.getParameter("rows"));
			YcDeliveryOrder ycdo = (YcDeliveryOrder) FengUtil.getObject(request.getParameterMap(),new YcDeliveryOrder());
			//需要状态
			Pager<YcDeliveryOrder> pager=new Pager<YcDeliveryOrder>(page, rows);
			String status=StrUtil.getString(request.getParameter("status"), "0");
			status = DES.decrypt(status);//解密
			ycdo.setCustomerSug(FengUtil.getEncode(status));
			pager=iYcDeliveryOrderService.getListPagerInfo(pager, ycdo);
			FengUtil.OUTAPPPageObject(Constant.APP_SUCCESS, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"暂无结果", response);
		}
	} 
	
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("app/addYcDeliveryOrder") 
	@Transactional
	public void addYcDeliveryOrder(HttpServletRequest request,HttpServletResponse response) {  
		try{
			YcDeliveryOrder ycdo = (YcDeliveryOrder) FengUtil.getObject(request.getParameterMap(),new YcDeliveryOrder());
			//1、需要传递的值：运单号、货物id、云仓编号、经销商id,代收费用、等运单信息
			//生成配送单号
			String deliveryNo=FengUtil.PS_Number();
			//云仓编号
			String branchNo=StrUtil.getString(request.getParameter("branchNo"), "");
			branchNo = DES.decrypt(branchNo);
			//获取货物id货物id以【,】隔开，,运单号和货物id以：【货物id_运单号】的形式传递
			String goodsIds=StrUtil.getString(request.getParameter("goodsIds"), "");
			goodsIds = DES.decrypt(goodsIds);
			String[] goodsId=goodsIds.split(",");
			LcPlatformUser user = (LcPlatformUser) request.getSession().getAttribute(request.getParameter("token"));
			for(String id:goodsId){
				YcOrderGoods yog=new YcOrderGoods();
				yog.setDeliveryNo(deliveryNo);
				//返回ids集合，下标0为货物的id，1为货物所属的运单号
				String[] ids=id.split("_");
				BigInteger bid=new BigInteger(ids[0]);
				String wayNo=ids[1];
				yog.setZoneGoodsId(bid);
				yog.setWayBillNo(wayNo);
				yog.setCreateTime(DateUtil.getDateTimeFormatString());
				yog.setCreateUser(user.getTrue_name());
				//添加订单货物信息
				iYcOrderGoodsService.addSingleInfo(yog);
				YcGoods yg=new YcGoods();
				yg.setId(bid);
				yg.setOutStorageStatus(1);
				//根据id修改货物出库状态为为已出库，即已出库的货物无法再次出库
				iYcGoodsService.modSingleInfo(yg);
				//这里开始修改运单的状态为在途
			}
			//配送订单单号
			ycdo.setDeliveryNo(deliveryNo);
			ycdo.setDealerId(ycdo.getDealerId());
			ycdo.setBranchNo(branchNo);
			//总费用
			//ycdo.setSumCost(ycdo.getInstallCost());
			//ycdo.setDeliveryCost(new Float(0));
			ycdo.setInsdelPayStatus(0);//默认未支付
			ycdo.setCreateTime(DateUtil.getDateTimeFormatString());
			ycdo.setCreateUser(user.getTrue_name());
			//添加配送单信息
			Integer i =iYcDeliveryOrderService.addSingleInfo(ycdo);
			if(i>0){
				FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
			}else{
				FengUtil.RuntimeExceptionFeng("下单失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERRORROLLBACK(Constant.APP_ERROR,e.getMessage(), response);
		}
	} 

	/**
	 * 订单配送、安装费
	 * @Author:luojing
	 * @2016年7月5日 下午4:06:07
	 */
	@RequestMapping("app/getOrderCostList")
	public void getOrderCostList(HttpServletRequest request,HttpServletResponse response){
		try{
			YcDeliveryOrder ycdo = (YcDeliveryOrder) FengUtil.getObject(request.getParameterMap(),new YcDeliveryOrder());
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			Integer page = Integer.parseInt(request.getParameter("page"));
			Integer rows = Integer.parseInt(request.getParameter("rows"));
			if(StrUtil.VString(startTime) && StrUtil.VString(endTime)){
				startTime = DateUtil.startTime(Long.valueOf(startTime));
				endTime = DateUtil.endTime(Long.valueOf(endTime));
			}
			Pager<YcDeliveryOrder> pager = new Pager<YcDeliveryOrder>(page, rows);
			pager = iYcDeliveryOrderService.getOrderCostList(pager, ycdo,startTime,endTime);
			FengUtil.OUTAPPPageObject(Constant.APP_SUCCESS, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"暂无结果", response);
		}
	}

	
	
}
