package com.brightsoft.controller.yc; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.entity.YcGoodsStatistics;
import com.brightsoft.entity.YcZoneGoods;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.yc.IYcZoneGoodsService;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;
import com.brightsoft.utils.yc.StrUtil;   

/** 
* YcZoneGoods控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcZoneGoodsController {  
	@Autowired 
	private IYcZoneGoodsService iYcZoneGoodsService; 

	/**
	 * 前往我的货物管理
	 * Author:luojing
	 * 2016年6月20日 下午3:57:33
	 */
	@RequestMapping("toMyGoods")
	public String toMyGoods(){
		return "/Clound/my_goods/my_goods";
	}
	
	/**
	 * 前往库存盘点页
	 * Author:luojing
	 * 2016年6月20日 下午3:57:33
	 */
	@RequestMapping("toGoodsStatistics")
	public String toGoodsStatistics(HttpServletRequest request){
		String branchNo=(String) FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY);
		if(!StrUtil.VString(branchNo)){
			FengUtil.RuntimeExceptionFeng("你还未登录..");
		}
		return "/Clound/goods_statistics/goods_statistics";
	}
	/**
	 * 前往库存调整页
	 * Author:luojing
	 * 2016年6月20日 下午3:57:33
	 */
	@RequestMapping("toGoodsStatistics_change")
	public String toGoodsStatisticsChange(HttpServletRequest request){
		String branchNo=(String) FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY);
		if(!StrUtil.VString(branchNo)){
			FengUtil.RuntimeExceptionFeng("你还未登录..");
		}
		return "/Clound/goods_statistics/goods_statistics_change";
	}
	/**
	 * 前往库存查询
	 * Author:luojing
	 * 2016年6月20日 下午3:57:33
	 */
	@RequestMapping("toGoodsStatistics_serch")
	public String toGoodsStatisticsSerch(HttpServletRequest request){
		String branchNo=(String) FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY);
		if(!StrUtil.VString(branchNo)){
			FengUtil.RuntimeExceptionFeng("你还未登录..");
		}
		return "/Clound/goods_statistics/goods_statistics_serch";
	}
	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcZoneGoodsSingle") 
	public void getYcZoneGoodsSingle(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcZoneGoodsList") 
	public void getYcZoneGoodsList(YcZoneGoods good,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response) {  
		try{
			String branchNo=(String) FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY);
			if(!StrUtil.VString(branchNo)){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			good.setBranchNo(branchNo);
			Pager<YcZoneGoods> pager = new Pager<YcZoneGoods>(page, rows);
			pager = iYcZoneGoodsService.getListPagerInfo(pager, good);
			FengUtil.OUTPRINTObject("", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcZoneGoods") 
	public void addYcZoneGoods(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 修改方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcZoneGoods") 
	public void modYcZoneGoods(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcZoneGoods") 
	public void delYcZoneGoods(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	
	/**
	 * 根据经销商/专线ID分页查询
	 * Author:luojing
	 * 2016年6月20日 下午4:44:13
	 */
	@RequestMapping("getGoodsStatistics")
	public void getGoodsStatistics(YcGoodsStatistics gs,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response){
		try{
			String branchNo=(String) FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY);
			if(!StrUtil.VString(branchNo)){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			gs.setBranchNo(branchNo);
			Pager<YcGoodsStatistics> pager = new Pager<YcGoodsStatistics>(page, rows);
			pager = iYcZoneGoodsService.getGoodsStatistics(pager, gs);
			FengUtil.OUTPRINTObject("", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 条件查询获物数量
	 * Author:luojing
	 * 2016年6月21日 上午9:57:51
	 */
	@RequestMapping("getGoodsStatisticsSumCount")
	public void getGoodsStatisticsSumCount(YcGoodsStatistics gs,HttpServletRequest request,HttpServletResponse response){
		try{
			if(gs.getBranchNo()==""){gs.setBranchNo(null);}
			if(gs.getZoneNo()==""){gs.setZoneNo(null);}
			Integer i = iYcZoneGoodsService.getGoodsStatisticsSumCount(gs);
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, i, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
