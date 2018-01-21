package com.yc.Controller; 

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.TempGoodsInfo;
import com.yc.Entity.YcGoods;
import com.yc.Service.IYcDeliveryOrderService;
import com.yc.Service.IYcGoodsService;
import com.yc.Service.IYcZoneGoodsService;
import com.yc.Tool.DES;
import com.yc.Tool.FengUtil;
import com.yc.Tool.LogUtil;
import com.yc.Tool.Pager;   

/** 
* YcGoods控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcGoodsController {  
	@Autowired 
	private IYcGoodsService iYcGoodsService; 
	@Autowired 
	private IYcZoneGoodsService iYcZoneGoodsService; 
	@Autowired 
	private IYcDeliveryOrderService iYcDeliveryOrderService; 
	
	/** 
	 * 获取指定经销商和指定网点的库区的所有货物 
	 * Auther:FENG 
	 */ 
	@RequestMapping("app/getPageGoodsByDealerId") 
	public void getPageGoodsByDealerId(HttpServletRequest request,HttpServletResponse response) {  
		try{
			String dealerId = DES.decrypt(request.getParameter("dealerId"));
			Integer page = Integer.parseInt(request.getParameter("page"));
			Integer rows = Integer.parseInt(request.getParameter("rows")) ;
			Pager<YcGoods> pager = new Pager<YcGoods>(page, rows);
			pager = iYcGoodsService.getPageGoodsByDealerId(pager, dealerId);
			FengUtil.OUTAPPPageObject(Constant.APP_SUCCESS, pager, response);
		}catch(Exception e ){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"暂无结果", response);
		}
	}
	
	/**
	 * 入库货物信息查询,经销商查询
	 * Author:luojing
	 * 2016年6月20日 下午4:44:13
	 */
	@RequestMapping("app/getInStorageList")
	public void getInStorageList(HttpServletRequest request,HttpServletResponse response){
		try{
			BigInteger dealerId = new BigInteger(DES.decrypt(request.getParameter("dealerId")));
			Integer page = Integer.parseInt(request.getParameter("page"));
			Integer rows = Integer.parseInt(request.getParameter("rows")) ;
			Pager<TempGoodsInfo> pager = new Pager<TempGoodsInfo>(page, rows);
			pager = iYcZoneGoodsService.getInStorageList(pager, dealerId);
			FengUtil.OUTAPPPageObject(Constant.APP_SUCCESS, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"暂无结果", response);
		}
	}
	
	/** 
	* 查询出库商品信息
	* Auther:FENG 
	*/ 
	@RequestMapping("app/getOutGoodsList") 
	public void getOutGoodsList(HttpServletRequest request,HttpServletResponse response) {  
		try{
			BigInteger dealerId = new BigInteger(DES.decrypt(request.getParameter("dealerId")));
			Integer page = Integer.parseInt(request.getParameter("page"));
			Integer rows = Integer.parseInt(request.getParameter("rows")) ;
			Pager<TempGoodsInfo> pager = new Pager<TempGoodsInfo>(page, rows);
			pager = iYcDeliveryOrderService.getPageOutStorageGoods(pager, dealerId);
			FengUtil.OUTAPPPageObject(Constant.APP_SUCCESS, pager, response);
		}catch(Exception e){ 
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,"暂无结果", response);
		} 
	}
}
