package com.brightsoft.controller.yc; 
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.entity.YcOrderGoods;
import com.brightsoft.service.yc.IYcOrderGoodsService;
import com.brightsoft.utils.yc.FengUtil;   

/** 
* YcOrderGoods控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcOrderGoodsController {  
	@Autowired 
	private IYcOrderGoodsService iYcOrderGoodsService; 


	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcOrderGoodsSingle") 
	public void getYcOrderGoodsSingle(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcOrderGoodsList") 
	public void getYcOrderGoodsList(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	 * 条件获取所有记录
	 * Auther:FENG 
	 */ 
	@RequestMapping("getYcOrderGoodsListBy") 
	public void getYcOrderGoodsListBy(HttpServletRequest request,HttpServletResponse response,YcOrderGoods yog) {  
		try{
			
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	 * 根据订单号获取信息
	 * Auther:FENG 
	 */ 
	@RequestMapping("getYcOrderGoodsListByOrderNo") 
	public void getYcOrderGoodsListByOrderNo(HttpServletRequest request,HttpServletResponse response,YcOrderGoods yog) {  
		try{
			List<YcOrderGoods> lst=new ArrayList<YcOrderGoods>();
			lst=iYcOrderGoodsService.getOrderGoodsByDeliveryNo(yog.getDeliveryNo());
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, lst, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcOrderGoods") 
	public void addYcOrderGoods(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 修改方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcOrderGoods") 
	public void modYcOrderGoods(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcOrderGoods") 
	public void delYcOrderGoods(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
}
