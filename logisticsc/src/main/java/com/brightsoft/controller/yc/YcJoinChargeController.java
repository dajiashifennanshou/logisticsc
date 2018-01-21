package com.brightsoft.controller.yc; 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.entity.YcJoinCharge;
import com.brightsoft.service.yc.IYcJoinChargeService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;   

/** 
* YcJoinCharge控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcJoinChargeController {  
	@Autowired 
	private IYcJoinChargeService iYcJoinChargeService; 
	
	/**
	 * 前往加盟收费标准管理页
	 * Author:luojing
	 * 2016年6月17日 上午10:13:48
	 */
	@RequestMapping("toJoinCharge")
	public String toJoinCharge(){
		return "/Clound/join_charge/join_charge";
	}
	
	/**
	 * 前往添加加盟收费标准管理页
	 * Author:luojing
	 * 2016年6月17日 上午10:13:48
	 */
	@RequestMapping("toAddJoinCharge")
	public String toAddJoinCharge(){
		return "/Clound/join_charge/add_join_charge";
	}
	
	/**
	 * 前往修改加盟收费标准管理页
	 * Author:luojing
	 * 2016年6月17日 上午10:13:48
	 */
	@RequestMapping("toModJoinCharge")
	public String toModJoinCharge(YcJoinCharge jc,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("id", jc.getId());
		return "/Clound/join_charge/mod_join_charge";
	}


	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcJoinChargeSingle") 
	public void getYcJoinChargeSingle(YcJoinCharge jc,HttpServletRequest request,HttpServletResponse response) {  
		try{
			YcJoinCharge join = iYcJoinChargeService.getSingleInfo(jc);
			if(join!=null){
				FengUtil.OUTPRINTRESULT("获取数据成功", Constant.RESULT_SUCCESS_CODE, join, response);
			}else{
				FengUtil.OUTADDERROR("获取数据失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcJoinChargeList") 
	public void getYcJoinChargeList(YcJoinCharge jc,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response) {  
		try{
			Pager<YcJoinCharge> pager = new Pager<YcJoinCharge>(page, rows);
			pager = iYcJoinChargeService.getListPagerInfo(pager, jc);
			FengUtil.OUTPRINTObject("成功", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcJoinCharge") 
	public void addYcJoinCharge(YcJoinCharge jc,HttpServletRequest request,HttpServletResponse response) {  
		try{
			jc.setCreateTime(DateUtil.getDateTimeFormatString());
			jc.setCreateUser("张学友");
			Integer i = iYcJoinChargeService.addSingleInfo(jc);
			if(i>0){
				FengUtil.OUTADDSUCCESS("添加成功", Constant.RESULT_SUCCESS_CODE, "add_join_charge", "join_charge_lst", response);
			}else{
				FengUtil.OUTADDERROR("添加失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("重复添加数据", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 修改方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcJoinCharge") 
	public void modYcJoinCharge(YcJoinCharge jc,HttpServletRequest request,HttpServletResponse response) {  
		try{
			jc.setUpdateTime(DateUtil.getDateTimeFormatString());
			jc.setUpdateUser("刘德华");
			Integer i = iYcJoinChargeService.modSingleInfo(jc);
			if(i>0){
				FengUtil.OUTADDSUCCESS("修改成功", Constant.RESULT_SUCCESS_CODE, "mod_join_charge", "join_charge_lst", response);
			}else{
				FengUtil.OUTADDERROR("修改失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("修改失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcJoinCharge") 
	public void delYcJoinCharge(String ids,HttpServletRequest request,HttpServletResponse response) {  
		try{
			List<BigInteger> list = new ArrayList<BigInteger>();
			for(String str : ids.split(",")){
				list.add(new BigInteger(str));
			}
			Integer i = iYcJoinChargeService.delSelect(list);
			if(i>0){
				FengUtil.OUTADDERROR("删除成功", Constant.RESULT_SUCCESS_CODE, response);
			}else{
				FengUtil.OUTADDERROR("删除失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 	
}
