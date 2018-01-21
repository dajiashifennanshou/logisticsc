package com.brightsoft.controller.yc; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.entity.YcOutGoods;
import com.brightsoft.service.yc.IYcOutGoodsService;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;   

/** 
* YcOutGoods控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcOutGoodsController {  
	@Autowired 
	private IYcOutGoodsService iYcOutGoodsService; 


	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcOutGoodsSingle") 
	public void getYcOutGoodsSingle(HttpServletRequest request,HttpServletResponse response) {  
		try{ 

		}catch(Exception e){ 

		} 
	} 
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcOutGoodsList") 
	public void getYcOutGoodsList(HttpServletRequest request,HttpServletResponse response,YcOutGoods crt,Integer page,Integer rows) {  
		try{ 
			Pager<YcOutGoods> pager=new Pager<YcOutGoods>(page, rows);
			pager=iYcOutGoodsService.getListPagerInfo(pager, crt);
			FengUtil.OUTPRINTObject("", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){ 
			FengUtil.OUTADDERROR("获取出库货物信息失败!!", Constant.RESULT_ERROR_CODE, response);
		} 
	} 
	/** 
	* 列表页面 
	* Auther:FENG 
	*/ 
	@RequestMapping("toListYcOutGoodsPage") 
	public String toListYcOutGoodsPage(HttpServletRequest request,HttpServletResponse response) {  
		return"/Clound/YcOutGoods/list_yc_out_goods";
	} 
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcOutGoods") 
	public void addYcOutGoods(HttpServletRequest request,HttpServletResponse response) {  
		try{ 

		}catch(Exception e){ 

		} 
	} 
	/** 
	* 添加页面 
	* Auther:FENG 
	*/ 
	@RequestMapping("toAddYcOutGoodsPage") 
	public String toAddYcOutGoodsPage(HttpServletRequest request,HttpServletResponse response) {  
		return "/Clound/YcOutGoods/add_yc_out_goods"; 
	} 
	/** 
	* 修改方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcOutGoods") 
	public void modYcOutGoods(HttpServletRequest request,HttpServletResponse response) {  
		try{ 

		}catch(Exception e){ 

		} 
	} 
	/** 
	* 修改页面 
	* Auther:FENG 
	*/ 
	@RequestMapping("toModYcOutGoodsPage") 
	public String toModYcOutGoodsPage(HttpServletRequest request,HttpServletResponse response) {  
		return "/Clound/YcOutGoods/mod_yc_out_goods"; 
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcOutGoods") 
	public void delYcOutGoods(HttpServletRequest request,HttpServletResponse response) {  
		try{ 

		}catch(Exception e){ 

		} 
	} 
}
