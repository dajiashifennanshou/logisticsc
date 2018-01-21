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
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.entity.YcGoods;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.service.yc.IYcGoodsService;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;   

/** 
* YcGoods控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcGoodsController {  
	@Autowired 
	private IYcGoodsService iYcGoodsService; 


	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcGoodsSingle") 
	public void getYcGoodsSingle(HttpServletRequest request,HttpServletResponse response) {  
 
	}
	/** 
	 * 获取指定运单号的记录 
	 * Auther:FENG 
	 */ 
	@RequestMapping("getGoodsByDeliveryNo") 
	public void getGoodsByDeliveryNo(String dNo,HttpServletRequest request,HttpServletResponse response) {  
		try{
			List<YcGoods> lst=new ArrayList<YcGoods>();
			lst=iYcGoodsService.getGoodsByDeliveryNo(dNo);
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, lst, response);
		}catch(Exception e ){
			FengUtil.OUTADDERROR("获取异常", Constant.RESULT_ERROR_CODE, response);
		}
	}
	/** 
	 * 获取指定运单号的分页记录 
	 * Auther:FENG 
	 */ 
	@RequestMapping("getGoodsPagerByDeliveryNo") 
	public void getGoodsPagerByDeliveryNo(String dNo,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response) {  
		try{
			Pager<YcGoods> p=new Pager<YcGoods>(page, rows);
			p.setRecordCount(1000);
			List<YcGoods> lst=new ArrayList<YcGoods>();
			lst=iYcGoodsService.getGoodsByDeliveryNo(dNo);
			p.setObjectList(lst);
			FengUtil.OUTPRINTObject("", Constant.RESULT_SUCCESS_CODE, p, response);
		}catch(Exception e ){
			FengUtil.OUTADDERROR("获取异常", Constant.RESULT_ERROR_CODE, response);
		}
	}
	/** 
	 * 获取指定经销商和指定网点的库区的未出库货物 
	 * Auther:FENG 
	 */ 
	@RequestMapping("getGoodsByDealerId") 
	public void getGoodsByDealerId(String joinerId,String outStorageStatus,String goodIds,HttpServletRequest request,HttpServletResponse response) {  
		try{
			String branchNo=FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY).toString();
			List<YcGoods> lst=new ArrayList<YcGoods>();
			lst=iYcGoodsService.getGoodsByDealerId(joinerId, branchNo,outStorageStatus,goodIds);
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, lst, response);
		}catch(Exception e ){
			e.printStackTrace();
			FengUtil.OUTADDERROR("获取异常", Constant.RESULT_ERROR_CODE, response);
		}
	}
	/** 
	 * 获取指定经销商和指定网点的库区的所有货物 
	 * Auther:FENG 
	 */ 
	@RequestMapping("getAllGoodsByDealerId") 
	public void getAllGoodsByDealerId(String joinerId,String branchNo,HttpServletRequest request,HttpServletResponse response) {  
		try{
			List<YcGoods> lst=new ArrayList<YcGoods>();
			lst=iYcGoodsService.getAllGoodsByDealerId(joinerId, branchNo);
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, lst, response);
		}catch(Exception e ){
			e.printStackTrace();
			FengUtil.OUTADDERROR("获取异常", Constant.RESULT_ERROR_CODE, response);
		}
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcGoodsList") 
	public void getYcGoodsList(HttpServletRequest request,HttpServletResponse response,YcGoods yg,Integer rows,Integer page) {  
		try{
			String branchNo=(String) FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY);
			Pager<YcGoods> pager=new Pager<YcGoods>(page, rows);
			yg.setBranchNo(branchNo);
			pager=iYcGoodsService.getListPagerInfo(pager, yg);
			FengUtil.OUTAPPPageObject(Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e ){
			e.printStackTrace();
			FengUtil.OUTADDERROR("获取异常", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	 * 获取指定经销商的库区货物分页记录 
	 * Auther:FENG 
	 */ 
	@RequestMapping("getYcGoodsListByDealer") 
	public void getYcGoodsListByDealer(HttpServletRequest request,HttpServletResponse response,YcGoods yg,Integer rows,Integer page) {  
		try{
			//传过来的id代表是经销商的id，会自动注入到YcGoods里
			String dealerId=request.getParameter("dealerId");
			yg.setId(new BigInteger(dealerId));
			List<YcGoods> lst=new ArrayList<YcGoods>();
			Pager<YcGoods> pager=new Pager<YcGoods>(page, rows);
			pager=iYcGoodsService.getYcGoodsListByDealerId(pager, yg);
			FengUtil.OUTAPPPageObject(Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e ){
			e.printStackTrace();
			FengUtil.OUTADDERROR("获取异常", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	 * 获取指定经销商的库区货物分页记录 
	 * Auther:FENG 
	 */ 
	@RequestMapping("getTmsGoodsListByDealer") 
	public void getTmsGoodsListByDealer(HttpServletRequest request,HttpServletResponse response,YcGoods yg,Integer rows,Integer page) {  
		try{
			PlatformUser pu=new PlatformUser();
			pu=(PlatformUser) FengUtil.GETDATABYSESSION(request, SystemConstant.USER_SESSION);
			if(pu==null){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			
			//传过来的id代表是经销商的id，会自动注入到YcGoods里
			yg.setId(new BigInteger(pu.getId().toString()));
			//List<YcGoods> lst=new ArrayList<YcGoods>();
			Pager<YcGoods> pager=new Pager<YcGoods>(page, rows);
			pager=iYcGoodsService.getYcGoodsListByDealerId(pager, yg);
			FengUtil.OUTAPPPAGETMS(Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e ){
			e.printStackTrace();
			FengUtil.OUTADDERROR("获取异常", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcGoods") 
	public void addYcGoods(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 修改方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcGoods") 
	public void modYcGoods(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcGoods") 
	public void delYcGoods(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
}
