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
import com.brightsoft.entity.YcDeliveryCharge;
import com.brightsoft.service.yc.IYcDeliveryChargeService;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;
import com.brightsoft.utils.yc.StrUtil;   

/** 
* YcDeliveryCharge控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcDeliveryChargeController {  
	@Autowired 
	private IYcDeliveryChargeService iYcDeliveryChargeService; 


	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcDeliveryChargeSingle") 
	public void getYcDeliveryChargeSingle(HttpServletRequest request,HttpServletResponse response,YcDeliveryCharge ydc) {  
		try{ 
			ydc=iYcDeliveryChargeService.getSingleInfo(ydc);
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, ydc, response);
		}catch(Exception e){ 
			FengUtil.OUTADDERROR("", Constant.RESULT_ERROR_CODE, response);
		} 
	} 
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcDeliveryChargeList") 
	public void getYcDeliveryChargeList(HttpServletRequest request,HttpServletResponse response,YcDeliveryCharge crt,Integer page,Integer rows) {  
		try{
			Pager<YcDeliveryCharge> pager=new Pager<YcDeliveryCharge>(page, rows);
			pager=iYcDeliveryChargeService.getListPagerInfo(pager, crt);
			FengUtil.OUTPRINTObject("", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 列表页面 
	* Auther:FENG 
	*/ 
	@RequestMapping("toListYcDeliveryChargePage") 
	public String toListYcDeliveryChargePage(HttpServletRequest request,HttpServletResponse response) {  
		return"/Clound/DeliveryCharge/DeliveryCharge";
	} 
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcDeliveryCharge") 
	@Transactional
	public void addYcDeliveryCharge(YcDeliveryCharge ydc ,HttpServletRequest request,HttpServletResponse response) {  
		try{ 
			ydc.setBranchNo(Constant.NOW_BRANCHNO);
			iYcDeliveryChargeService.addSingleInfo(ydc);
			FengUtil.OUTADDSUCCESS("", Constant.RESULT_SUCCESS_CODE, "add_delivery", "delivery_lst", response);
		}catch(Exception e){ 
			FengUtil.OUTROLLBACK("", Constant.RESULT_ERROR_CODE, response);
		} 
	} 
	/** 
	* 添加页面 
	* Auther:FENG 
	*/ 
	@RequestMapping("toAddYcDeliveryChargePage") 
	public String toAddYcDeliveryChargePage(HttpServletRequest request,HttpServletResponse response) {  
		return "/Clound/DeliveryCharge/add_delivery_charge"; 
	} 
	/** 
	* 修改方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcDeliveryCharge") 
	@Transactional
	public void modYcDeliveryCharge(YcDeliveryCharge ydc ,HttpServletRequest request,HttpServletResponse response) {  
		try{ 
			iYcDeliveryChargeService.modSingleInfo(ydc);
			FengUtil.OUTADDSUCCESS("", Constant.RESULT_SUCCESS_CODE, "mod_delivery", "delivery_lst", response);
		}catch(Exception e){ 
			FengUtil.OUTROLLBACK("", Constant.RESULT_ERROR_CODE, response);
		} 
	} 
	/** 
	* 修改页面 
	* Auther:FENG 
	*/ 
	@RequestMapping("toModYcDeliveryChargePage") 
	public String toModYcDeliveryChargePage(HttpServletRequest request,HttpServletResponse response) {  
		String id=StrUtil.getString(request.getParameter("rowId"), "0");
		request.setAttribute("id", id);
		return "/Clound/DeliveryCharge/mod_delivery_charge"; 
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcDeliveryCharge") 
	public void delYcDeliveryCharge(HttpServletRequest request,HttpServletResponse response) {  
		try{
			String ids = request.getParameter("ids");
			String[] str = ids.split(",");
			List<BigInteger> list = new ArrayList<BigInteger>();
			for(int i=0;i<str.length;i++){
				list.add(new BigInteger(str[i]));
			}
			Integer i = iYcDeliveryChargeService.delSelect(list);
			if(i>0){
				FengUtil.OUTPRINTRESULT("删除成功", 0, "del", response);
			}else{
				FengUtil.OUTADDERROR("修改失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
}
