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
import com.brightsoft.entity.YcStorageCharge;
import com.brightsoft.service.yc.IYcStorageChargeService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;   

/** 
* 仓库收费标准控制器 
* Author:luojing 
*/ 
@Controller 
public class YcStorageChargeController {  
	@Autowired 
	private IYcStorageChargeService iYcStorageChargeService; 
	
	/**
	 * 前往仓库收费管理
	 * Author:luojing
	 * 2016年6月16日 下午5:27:31
	 */
	@RequestMapping("toStorageCharge")
	public String toStorageCharge(){
		return "/Clound/storage_charge/storage_charge";
	}
	
	/**
	 * 前往添加仓库收费
	 * Author:luojing
	 * 2016年6月16日 下午5:27:31
	 */
	@RequestMapping("toAddStorageCharge")
	public String toAddStorageCharge(){
		return "/Clound/storage_charge/add_storage_charge";
	}
	
	/**
	 * 前往修改仓库收费管理
	 * Author:luojing
	 * 2016年6月16日 下午5:27:31
	 */
	@RequestMapping("toModStorageCharge")
	public String toModStorageCharge(YcStorageCharge sc,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("id", sc.getId());
		return "/Clound/storage_charge/mod_storage_charge";
	}


	/** 
	* 获取单条记录 
	* Author:luojing 
	*/ 
	@RequestMapping("getYcStorageChargeSingle") 
	public void getYcStorageChargeSingle(YcStorageCharge sc,HttpServletRequest request,HttpServletResponse response) {  
		try{
			YcStorageCharge c = iYcStorageChargeService.getSingleInfo(sc);
			if(c!=null){
				FengUtil.OUTPRINTRESULT(Constant.GET_MSG_001, Constant.RESULT_SUCCESS_CODE, c, response);
			}else{
				FengUtil.OUTADDERROR(Constant.GET_MSG_002, Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	/** 
	* 获取分页记录 
	* Author:luojing 
	*/ 
	@RequestMapping("getYcStorageChargeList") 
	public void getYcStorageChargeList(YcStorageCharge sc,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response) {  
		try{
			Pager<YcStorageCharge> pager = new Pager<YcStorageCharge>(page, rows);
			pager = iYcStorageChargeService.getListPagerInfo(pager, sc);
			FengUtil.OUTPRINTObject(Constant.GET_MSG_001, Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	/** 
	* 添加方法 
	* Author:luojing 
	*/ 
	@RequestMapping("addYcStorageCharge") 
	public void addYcStorageCharge(YcStorageCharge sc,HttpServletRequest request,HttpServletResponse response) {  
		try{
			sc.setCreateTime(DateUtil.getDateTimeFormatString());
			sc.setCreateUser("刘德华");
			Integer i = iYcStorageChargeService.addSingleInfo(sc);
			if(i>0){
				FengUtil.OUTADDSUCCESS(Constant.ADD_MSG_001, Constant.RESULT_SUCCESS_CODE, "add_storage_charge", "storage_charge_lst", response);
			}else{
				FengUtil.OUTADDERROR(Constant.ADD_MSG_002,  Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("添加失败,数据重复添加",  Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 修改方法 
	* Author:luojing 
	*/ 
	@RequestMapping("modYcStorageCharge") 
	public void modYcStorageCharge(YcStorageCharge sc,HttpServletRequest request,HttpServletResponse response) {  
		try{
			sc.setUpdateTime(DateUtil.getDateTimeFormatString());
			sc.setUpdateUser("张学友");
			Integer i = iYcStorageChargeService.modSingleInfo(sc);
			if(i>0){
				FengUtil.OUTADDSUCCESS(Constant.UPDATE_MSG_001, Constant.RESULT_SUCCESS_CODE, "mod_storage_charge", "storage_charge_lst", response);
			}else{
				FengUtil.OUTADDERROR(Constant.UPDATE_MSG_002,  Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	/** 
	* 删除方法 
	* Author:luojing 
	*/ 
	@RequestMapping("delYcStorageCharge") 
	public void delYcStorageCharge(String ids,HttpServletRequest request,HttpServletResponse response) {  
		try{
			List<BigInteger> list = new ArrayList<BigInteger>();
			for(String str : ids.split(",")){
				list.add(new BigInteger(str));
			}
			Integer i = iYcStorageChargeService.delSelect(list);
			if(i>0){
				FengUtil.OUTADDERROR(Constant.DEL_MSG_001,  Constant.RESULT_SUCCESS_CODE, response);
			}else{
				FengUtil.OUTADDERROR(Constant.DEL_MSG_002,  Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
}
