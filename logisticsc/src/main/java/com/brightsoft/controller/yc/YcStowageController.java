package com.brightsoft.controller.yc; 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.Constant.ErrorCode;
import com.brightsoft.entity.ResultEntity;
import com.brightsoft.entity.YcDeliveryCharge;
import com.brightsoft.entity.YcDeliveryOrder;
import com.brightsoft.entity.YcOrderGoods;
import com.brightsoft.entity.YcOutStorage;
import com.brightsoft.entity.YcStowage;
import com.brightsoft.entity.YcStowageDelivery;
import com.brightsoft.service.yc.IYcCarManageService;
import com.brightsoft.service.yc.IYcDeliveryChargeService;
import com.brightsoft.service.yc.IYcDeliveryOrderService;
import com.brightsoft.service.yc.IYcEmployeeService;
import com.brightsoft.service.yc.IYcOrderGoodsService;
import com.brightsoft.service.yc.IYcOutStorageService;
import com.brightsoft.service.yc.IYcStowageDeliveryService;
import com.brightsoft.service.yc.IYcStowageService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;
import com.brightsoft.utils.yc.StrUtil;

/** 
* YcStowage控制器 
* Auther:FENG 
* 配载单
*/ 
@Controller 
public class YcStowageController {  
	@Autowired 
	private IYcStowageService iYcStowageService; 
	@Autowired 
	private IYcStowageDeliveryService iYcStowageDeliveryService; 
	@Autowired 
	private IYcDeliveryOrderService iYcDeliveryOrderService ; 
	@Autowired 
	private IYcOrderGoodsService iYcOrderGoodsService ; 
	@Autowired 
	private IYcOutStorageService iYcOutStorageService  ; 
	@Autowired 
	private IYcEmployeeService iycEmployeeService   ; 
	@Autowired 
	private IYcCarManageService iYcCarManageService   ; 
	@Autowired 
	private IYcDeliveryChargeService iYcDeliveryChargeService    ; 
	
	


	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcStowageSingle") 
	public void getYcStowageSingle(HttpServletRequest request,HttpServletResponse response,YcStowage ys) {  
		try{
			ys=iYcStowageService.getSingleInfo(ys);
			FengUtil.OUTPRINTRESULT("成功", Constant.RESULT_SUCCESS_CODE, ys, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTPRINTRESULT("失败", Constant.RESULT_ERROR_CODE, null, response);
		}
	}
	
	/** 
	 * 获取更改状态
	 * Auther:FENG 
	 */ 
	@RequestMapping("modStatusByNo") 
	public void modStatu(HttpServletRequest request,HttpServletResponse response,YcStowage ys) {  
		try{
			iYcStowageService.modSingleInfo(ys);
			FengUtil.OUTPRINTRESULT("成功", Constant.RESULT_SUCCESS_CODE, ys, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTPRINTRESULT("失败", Constant.RESULT_ERROR_CODE, null, response);
		}
	}
	
	/** 
	 * 配载单完成
	 * Auther:FENG 
	 */ 
	@RequestMapping("YcStowageOver") 
	public void stowageOver(HttpServletRequest request,HttpServletResponse response,YcStowage ys) {  
		ResultEntity re=new ResultEntity();
		try{
			re=iYcStowageService.stowageOver(ys);
			if(re.getCode()==0){
				FengUtil.OUTPRINTRESULT("成功", Constant.RESULT_SUCCESS_CODE, ys, response);
			}else{
				FengUtil.ERRORDATA(re, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.ERRORDATA(re, response);
		}
	}
	/** 
	 * 开始配载
	 * Auther:FENG 
	 */ 
	@RequestMapping("startToDelivery") 
	@Transactional
	public void startToDelivery(HttpServletRequest request,HttpServletResponse response,String stowageNos) {  
		try{
			
			for(String stowageNo :stowageNos.split(",")){
				List<YcStowageDelivery> lstysd=new ArrayList<YcStowageDelivery>();
				//lstysd=iYcStowageDeliveryService.getSingleInfo(t);
				YcStowage nys=new YcStowage();
				nys.setStowageNo(stowageNo);
				nys.setStowageStatus(1);
				//--
				//获取配载订单表
				List<YcDeliveryOrder> lst=new ArrayList<YcDeliveryOrder>();
				lst=iYcDeliveryOrderService.getYcDeliveryOrderByStowage(stowageNo);
				for(YcDeliveryOrder e : lst){
					//添加出库记录
					//获取订单货物
					List<YcOrderGoods> lstyog=new ArrayList<YcOrderGoods>();
					lstyog=iYcOrderGoodsService.getOrderGoodsByDeliveryNo(e.getDeliveryNo());
					for(YcOrderGoods d: lstyog){
						YcOutStorage yos=new YcOutStorage();
						yos.setBranchNo(e.getBranchNo());
						yos.setDeliveryNo(d.getDeliveryNo());
						yos.setDealerId(e.getDealerId());
						yos.setJionType(1);
						yos.setZoneNo(d.getZoneNo());
						yos.setOutType(0);
						yos.setCreateTime(DateUtil.getDateTimeFormatString());
						yos.setCreateUser(Constant.SYSTEM_OPERSTION_PERSON);
						iYcOutStorageService.addSingleInfo(yos);
					}
				}
				//修改配载单状态为在途
				iYcStowageService.modSingleInfo(nys);
			}
			FengUtil.OUTADDSUCCESS("成功..", Constant.RESULT_SUCCESS_CODE, "", "stowage_lst", response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTPRINTRESULT(e.getLocalizedMessage(), Constant.RESULT_SUCCESS_CODE, null, response);
		}
	}
	
	
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcStowageList") 
	public void getYcStowageList(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows,YcStowage ys) {  
		ResultEntity re=new ResultEntity();
		try{
			String branchNo=(String) FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY);
			if(branchNo==null || branchNo==""){
				re.setMsg(ErrorCode.NOTLOGIN.getName());
				re.setCode(ErrorCode.NOTLOGIN.getValue());
				FengUtil.RuntimeExceptionFeng();
			}
			ys.setBranchNo(branchNo);
			Pager<YcStowage> pager=new Pager<YcStowage>(page, rows);
			pager=iYcStowageService.getListPagerInfo(pager, ys);
			FengUtil.OUTPRINTObject("成功", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.ERRORDATA(re, response);
		}
	} 
	/** 
	* 列表页面 
	* Auther:FENG 
	*/ 
	@RequestMapping("toListYcStowagePage") 
	public String toListYcStowagePage(HttpServletRequest request,HttpServletResponse response) {  
		return "/Clound/Stowage/Stowage";
	} 
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcStowage") 
	public void addYcStowage(HttpServletRequest request,HttpServletResponse response,YcStowage ys) {  
		try{
			//在添加时，imp里面会自动增加添加人和添加时间
			ResultEntity re=iYcStowageService.addStowageInfo(request, ys);
			FengUtil.OUTADDSUCCESS(re.getMsg(), re.getCode(), "add_stowage", "stowage_lst", response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("异常", 1, response);
		}
	} 
	/** 
	* 添加页面 
	* Auther:FENG 
	*/ 
	@RequestMapping("toAddYcStowagePage") 
	public String toAddYcStowagePage(HttpServletRequest request,HttpServletResponse response ) {   
		return "/Clound/Stowage/add_stowage";
	} 
	/** 
	* 修改配载单方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcStowage") 
	@Transactional
	public void modYcStowage(HttpServletRequest request,HttpServletResponse response,YcStowage ys) {  
		try{
			//需要获取之前的配送单
			String StowageNo=ys.getStowageNo();
			//根据车牌号获取配送费，需要在配载之前将配送费设置到每个配载所关联的订单里
			YcDeliveryCharge ydc= iYcDeliveryChargeService.getDeliveryCostByCarNo(ys.getCarNo(),Constant.NOW_BRANCHNO);
			//获取配送单编号集合
			String deliverNos=StrUtil.getString(request.getParameter("deliveryNo"), "");
			//之前的运单号集合
			String afterDNo=StrUtil.getString(request.getParameter("afterDNo"), "");
			for(String dNo:afterDNo.split(",")){
				//修改之前的安装工的状态
				iycEmployeeService.modUseStatusByOrderNo(0, dNo);
			}
			//修改配载单对应的之前的车辆的状态为未使用
			iYcCarManageService.modCarStatusByStowage(0, StowageNo);
			//分割配送订单编号号
			String[] deliverNo=deliverNos.split(",");
			//删除之前的配送单配载单关联数据
			YcStowageDelivery eysd=new YcStowageDelivery();
			eysd.setStowageNo(StowageNo);
			iYcStowageDeliveryService.delSingleInfo(eysd);
			//重新添加配送单配载单关联数据
			for(String de:deliverNo){
				//分割字符串第一个是配送单号，第二个是是经销商id
				String[] des=de.split("_");
				YcDeliveryOrder ydo=new YcDeliveryOrder();
				ydo.setDeliveryNo(des[0]);
				ydo.setOrderStatus(1);
				ydo.setDeliveryCost(ydc.getDeliveryCost());
				//修改订单状态为已配载
				iYcDeliveryOrderService.modOrderByNo(ydo);
				//根据配送id从request里规则为：【installer_】+id获取安装工人id
				String installerId=request.getParameter("installer_"+des[0]);
				YcStowageDelivery ysd=new YcStowageDelivery();
				ysd.setDeliverNo(des[0]);
				ysd.setStowageNo(StowageNo);
				//设置安装工人信息
				ysd.setEmployeeId(installerId);
				//添加配载单配送单关联表信息，并同时在添加时修改配送安装工人的状态
				iYcStowageDeliveryService.addSingleInfo(ysd);
			}
			ys.setStowageNo(StowageNo);
			iYcStowageService.modSingleInfo(ys);
			FengUtil.OUTADDSUCCESS("修改成功!", Constant.RESULT_SUCCESS_CODE, "mod_stowage", "stowage_lst", response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTROLLBACK("修改失败!",Constant.RESULT_ERROR_CODE, response);
		} 
	}
	/**
	 * 获取所有的配载单信息
	 * Author:FENG
	 * 2016年6月24日
	 * @param request
	 * @param response
	 */
	@RequestMapping("getAllStowageList")
	public void getAllStowageList(YcStowage ys,HttpServletRequest request,HttpServletResponse response){
		try{
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, iYcStowageService.getAllStowageList(ys), response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/** 
	* 修改页面 
	* Auther:FENG 
	*/ 
	@RequestMapping("toModYcStowagePage") 
	public String toModYcStowagePage(HttpServletRequest request,HttpServletResponse response) {
		String id=StrUtil.getString(request.getParameter("rowId"), "0");
		request.setAttribute("id", id);
		return "/Clound/Stowage/mod_stowage"; 
	} 
	/** 
	 * 详情页面
	 * Auther:FENG 
	 */ 
	@RequestMapping("toDetYcStowagePage") 
	public String toDetYcStowagePage(HttpServletRequest request,HttpServletResponse response) {  
		String id=StrUtil.getString(request.getParameter("rowId"), "0");
		request.setAttribute("id", id);
		return "/Clound/Stowage/det_stowage"; 
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcStowage") 
	public void delYcStowage(HttpServletRequest request,HttpServletResponse response,String ids) {  
		try{ 
			List<BigInteger> lst=new ArrayList<BigInteger>();
			for(String s :ids.split(",")){
				lst.add(new BigInteger(s));
			}
			iYcStowageService.delSelect(lst);
			FengUtil.REFRENSHLIST(Constant.RESULT_SUCCESS_CODE, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		} 
	} 
}
