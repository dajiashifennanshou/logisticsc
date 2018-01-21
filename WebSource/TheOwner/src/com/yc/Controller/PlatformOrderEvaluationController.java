package com.yc.Controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.PlatformOrder;
import com.yc.Entity.PlatformOrderEvaluation;
import com.yc.Entity.PlatformUser;
import com.yc.Service.PlatformOrderEvaluationService;
import com.yc.Service.PlatformOrderService;
import com.yc.Service.PlatformUserService;
import com.yc.Tool.DateUtil;
import com.yc.Tool.FengRuntimeException;
import com.yc.Tool.FengUtil;
import com.yc.Tool.Pager;
/**
 * 平台订单评价
 * @Author:luojing
 * @2016年7月27日 下午2:17:35
 */
@Controller
public class PlatformOrderEvaluationController {
	
	
	@Autowired
	private PlatformOrderEvaluationService iLcPlatformOrderEvaluationService;
	
	@Autowired
	private PlatformOrderService platformOrderService;
	@Autowired
	private PlatformUserService pus;
	/**
	 * 集合查询评价详情（某条线路）->线路下的订单
	 * Author:luojing
	 * 2016年6月28日 下午2:15:18
	 */
	@RequestMapping("app/getEvaluateInfo")
	public void getEvaluateInfo(BigInteger linId,Integer page,Integer rows,BigInteger userId,HttpServletRequest request,HttpServletResponse response){
		try{
			//需要线路id，用户id
			Pager<PlatformOrderEvaluation> pager = new Pager<>(page, rows);
			pager = iLcPlatformOrderEvaluationService.getPageEvaluateInfo(pager, linId, userId);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, pager, response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,"暂无结果", response);
		}
	}
	/**
	 * 添加评价-对订单
	 * Author:luojing
	 * 2016年6月28日 下午2:15:18
	 */
	@RequestMapping("app/addEvaluateInfo")
	public void add(PlatformOrderEvaluation pe,String orderNumber,BigInteger userId,HttpServletRequest request,HttpServletResponse response){
		try{
			if(userId==null){
				FengUtil.RuntimeExceptionFeng("未获取到用户信息...");
			}
			if(orderNumber==null){
				FengUtil.RuntimeExceptionFeng("未获取到订单信息...");
			}
			
			//需要订单号，用户id,评价信息
			pe.setOrder_number(orderNumber);
			if(iLcPlatformOrderEvaluationService.getSumCount(pe)>0){
				FengUtil.RuntimeExceptionFeng("此订单已评价...");
			}
			pe.setUser_id(userId);
			pe.setState(1);
			pe.setEvaluate_time(DateUtil.getDateTimeFormatString());
//			PlatformOrder po=new PlatformOrder();
//			po.setState(11);
//			if(platformOrderService.modSingleInfo(po)<1){
//				FengUtil.RuntimeExceptionFeng("修改订单状态时失败。。");
//			}
			if(iLcPlatformOrderEvaluationService.addSingleInfo(pe)<1){
				FengUtil.RuntimeExceptionFeng("添加失败。。");
			}
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,"暂无结果", response);
		}
	}
	
	
}
