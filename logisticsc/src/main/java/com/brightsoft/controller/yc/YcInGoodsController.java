package com.brightsoft.controller.yc; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.entity.YcDeliveryOrder;
import com.brightsoft.entity.YcInGoods;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.yc.IYcInGoodsService;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;
import com.brightsoft.utils.yc.StrUtil;   

/** 
* YcInGoods控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcInGoodsController {  
	@Autowired 
	private IYcInGoodsService iYcInGoodsService; 


	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcInGoodsSingle") 
	public void getYcInGoodsSingle(HttpServletRequest request,HttpServletResponse response,YcInGoods ycdo) {  
		try{
			ycdo=iYcInGoodsService.getSingleInfo(ycdo);
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, ycdo, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcInGoodsList") 
	public void getYcInGoodsList(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows,YcInGoods yig) {  
		try{
			ValitedLogin<SysUser> v=new ValitedLogin<SysUser>();
			SysUser pu= v.VALITEDLOGIN(request, Constant.YCLOGINUSER);
			if(pu==null){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			Pager<YcInGoods> pager=new Pager<YcInGoods>(page, rows);
			pager=iYcInGoodsService.getListPagerInfo(pager, yig);
			FengUtil.OUTPRINTObject("", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcInGoods") 
	public void addYcInGoods(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 修改方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcInGoods") 
	public void modYcInGoods(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	 * 详情页面方法 
	 * Auther:FENG 
	 */ 
	@RequestMapping("toDetYcInGoods") 
	public String toDetYcInGoods(HttpServletRequest request,HttpServletResponse response) {  
		String id=StrUtil.getString(request.getParameter("rowId"), "0");
		request.setAttribute("id", id);
		return "/Clound/in_storage/det_in_storage";
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcInGoods") 
	public void delYcInGoods(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
}
