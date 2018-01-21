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
import com.brightsoft.entity.YcEmployee;
import com.brightsoft.entity.YcGoodsType;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.yc.IYcGoodsTypeService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;
import com.brightsoft.utils.yc.StrUtil;   

/** 
* YcGoodsType控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcGoodsTypeController {  
	@Autowired 
	private IYcGoodsTypeService iYcGoodsTypeService; 


	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcGoodsTypeSingle") 
	public void getYcGoodsTypeSingle(YcGoodsType ygt,HttpServletRequest request,HttpServletResponse response) {  
		try{
			ygt=iYcGoodsTypeService.getSingleInfo(ygt);
			FengUtil.OUTPRINTRESULT("成功", Constant.RESULT_SUCCESS_CODE, ygt, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTPRINTRESULT("成功", Constant.RESULT_SUCCESS_CODE, null, response);
		}
	} 
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcGoodsTypeList") 
	public void getYcGoodsTypeList(HttpServletRequest request,HttpServletResponse response,YcGoodsType ygt,Integer page,Integer rows) {  
		try{
			String branchNo=(String) FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY);
			if(!StrUtil.VString(branchNo)){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			ygt.setBranchNo(branchNo);
			Pager<YcGoodsType> pager = new Pager<YcGoodsType>(page, rows);
			pager = iYcGoodsTypeService.getListPagerInfo(pager, ygt);
			FengUtil.OUTPRINTObject("成功", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	 * 获取货物类型对应的安装配送费用
	 * Auther:FENG 
	 */ 
	@RequestMapping("getYcGoodsTypeListBy") 
	public void getYcGoodsTypeListBy(HttpServletRequest request,HttpServletResponse response,YcGoodsType ygt) {  
		try{
			SysUser su=new SysUser();
			su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			ygt.setBranchNo(su.getBranchNo());
			List<YcGoodsType> lst=new ArrayList<YcGoodsType>();
			lst=iYcGoodsTypeService.getGoodsTypeBy(ygt);
			FengUtil.OUTPRINTRESULT("",Constant.RESULT_SUCCESS_CODE, lst, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcGoodsType") 
	public void addYcGoodsType(HttpServletRequest request,HttpServletResponse response,YcGoodsType ygt) {  
		try{
			SysUser su=new SysUser();
			su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			ygt.setBranchNo(su.getBranchNo());
			ygt.setCreateTime(DateUtil.getDateTimeFormatString());
			ygt.setCreateUser(su.getRealname());
			iYcGoodsTypeService.addSingleInfo(ygt);
			FengUtil.OUTADDSUCCESS("", 0, "add_goods_type", "goods_type_list", response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 修改方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcGoodsType") 
	public void modYcGoodsType(HttpServletRequest request,HttpServletResponse response,YcGoodsType ygt) {  
		try{
			SysUser su=new SysUser();
			su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			ygt.setUpdateTime(DateUtil.getDateTimeFormatString());
			ygt.setUpdateUser(su.getRealname());
			iYcGoodsTypeService.modSingleInfo(ygt);
			FengUtil.OUTADDSUCCESS("", 0, "mod_goods_type", "goods_type_list", response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcGoodsType") 
	public void delYcGoodsType(String ids,HttpServletRequest request,HttpServletResponse response) {  
		try{
			String[] str = ids.split(",");
			List<BigInteger> list = new ArrayList<BigInteger>();
			for(int i=0;i<str.length;i++){
				list.add(new BigInteger(str[i]));
			}
			Integer i = iYcGoodsTypeService.delSelect(list);
			if(i>0){
				FengUtil.OUTPRINTRESULT("删除成功", Constant.RESULT_SUCCESS_CODE, "del", response);
			}else{
				FengUtil.OUTADDERROR("删除失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	/**
	 * 前往员工管理页面
	 * Author:luojing
	 * 2016年6月13日 下午6:17:49
	 */
	@RequestMapping("toYcGoodsType")
	public String toGoodsType(){
		return "/Clound/goodsType/goodsType";
	}
	/**
	 * 前往员工添加页面
	 * Author:luojing
	 * 2016年6月13日 下午6:17:49
	 */
	@RequestMapping("toAddYcGoodsType")
	public String toAddGoodsType(){
		return "/Clound/goodsType/add_goodsType";
	}
	/**
	 * 前往员工修改页面
	 * Author:luojing
	 * 2016年6月13日 下午6:17:49
	 */
	@RequestMapping("toModYcGoodsType")
	public String toModGoodsType(HttpServletRequest request,YcGoodsType ygt){
		request.setAttribute("id", ygt.getId());
		return "/Clound/goodsType/mod_goodsType";
	}
}
