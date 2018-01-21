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
import com.brightsoft.entity.YcDealer;
import com.brightsoft.service.yc.IYcDealerService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;   

/** 
* YcDealer控制器 
* Author:luojing 
*/ 
@Controller 
public class YcDealerController {  
	@Autowired 
	private IYcDealerService iYcDealerService; 

	/**
	 * 前往经销商管理页
	 * Author:luojing
	 * 2016年6月20日 下午1:36:22
	 */
	@RequestMapping("toDealer")
	public String toDealer(){
		return "/Clound/dealer/dealer";
	}
	
	/**
	 * 前往经销商添加页
	 * Author:luojing
	 * 2016年6月23日 上午9:42:10
	 */
	@RequestMapping("toAddDealer")
	public String toAddDealer(){
		return "/Clound/dealer/add_dealer";
	}
	
	/**
	 * 前往经销商修改页
	 * Author:luojing
	 * 2016年6月23日 上午9:42:10
	 */
	@RequestMapping("toModDealer")
	public String toModDealer(YcDealer deal,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("id", deal.getId());
		return "/Clound/dealer/mod_dealer";
	}
	
	/** 
	* 获取单条记录 
	* Author:luojing 
	*/ 
	@RequestMapping("getYcDealerSingle") 
	public void getYcDealerSingle(YcDealer deal,HttpServletRequest request,HttpServletResponse response) {  
		try{
			YcDealer de = iYcDealerService.getSingleInfo(deal);
			if(de!=null){
				FengUtil.OUTPRINTRESULT("获取数据成功", Constant.RESULT_SUCCESS_CODE, de, response);
			}else{
				FengUtil.OUTADDERROR("获取数据成功", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 
	* 获取分页记录 
	* Author:luojing 
	*/ 
	@RequestMapping("getYcDealerList") 
	public void getYcDealerList(YcDealer deal,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response) {  
		try{
			Pager<YcDealer> pager = new Pager<YcDealer>(page, rows);
			pager = iYcDealerService.getListPagerInfo(pager, deal);
			FengUtil.OUTPRINTObject("获取数据成功", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/** 
	 * 获取所有记录 
	 * Author:luojing 
	 */ 
	@RequestMapping("getAllDealer") 
	public void getAllDealer(YcDealer deal,HttpServletRequest request,HttpServletResponse response) {  
		try{
			List<YcDealer> lst=new ArrayList<YcDealer>();
			lst=iYcDealerService.getAllDealer();
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, lst, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 
	* 添加方法 
	* Author:luojing 
	*/ 
	@RequestMapping("addYcDealer") 
	public void addYcDealer(YcDealer deal,HttpServletRequest request,HttpServletResponse response) {  
		try{
			deal.setCreateTime(DateUtil.getDateTimeFormatString());
			deal.setCreateUser("奥巴马");
			deal.setStatus(0);
			Integer i = iYcDealerService.addSingleInfo(deal);
			if(i>0){
				FengUtil.OUTADDSUCCESS("添加成功", Constant.RESULT_SUCCESS_CODE, "add_dealer", "dealer_lst", response);
			}else{
				FengUtil.OUTADDERROR("添加失败",  Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("经销商重复添加", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 修改方法 
	* Author:luojing 
	*/ 
	@RequestMapping("modYcDealer") 
	public void modYcDealer(YcDealer deal,HttpServletRequest request,HttpServletResponse response) {  
		try{
			deal.setUpdateTime(DateUtil.getDateTimeFormatString());
			deal.setUpdateUser("普京");
			Integer i = iYcDealerService.modSingleInfo(deal);
			if(i>0){
				FengUtil.OUTADDSUCCESS("修改成功", Constant.RESULT_SUCCESS_CODE, "mod_dealer", "dealer_lst", response);
			}else{
				FengUtil.OUTADDERROR("修改失败",  Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	/** 
	* 删除方法 
	* Author:luojing 
	*/ 
	@RequestMapping("delYcDealer") 
	public void delYcDealer(String ids,HttpServletRequest request,HttpServletResponse response) {  
		try{
			List<BigInteger> list = new ArrayList<BigInteger>();
			for(String str : ids.split(",")){
				list.add(new BigInteger(str));
			}
			Integer i = iYcDealerService.delSelect(list);
			if(i>0){
				FengUtil.OUTPRINTRESULT("删除 "+i+" 条数据成功", Constant.RESULT_SUCCESS_CODE,"del", response);
			}else{
				FengUtil.OUTADDERROR(Constant.DEL_MSG_002, Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	/**
	 * 查询没有注册加盟商的经销商用户
	 * Author:luojing
	 * 2016年6月23日 下午5:22:22
	 */
	@RequestMapping("getUnRegisterDealer")
	public void getUnRegisterDealer(HttpServletRequest request,HttpServletResponse response){
		try{
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, iYcDealerService.getUnRegisterDealer(), response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
