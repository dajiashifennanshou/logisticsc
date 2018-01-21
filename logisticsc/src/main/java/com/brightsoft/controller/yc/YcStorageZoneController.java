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
import com.brightsoft.entity.YcJoinInfo;
import com.brightsoft.entity.YcRuleInfo;
import com.brightsoft.entity.YcStorageZone;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.yc.IYcJoinInfoService;
import com.brightsoft.service.yc.IYcRuleInfoService;
import com.brightsoft.service.yc.IYcStorageZoneService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;   

/**
 * 库区信息控制器 
 * Author:luojing
 * 2016年6月14日 上午11:35:23
 */
@Controller 
public class YcStorageZoneController {  
	@Autowired 
	private IYcStorageZoneService iYcStorageZoneService; 
	
	@Autowired
	private IYcRuleInfoService iYcRuleInfoService;
	@Autowired
	private IYcJoinInfoService iycjis;

	/**
	 * 前往库区信息管理页
	 * Author:luojing
	 * 2016年6月14日 上午11:36:19
	 */
	@RequestMapping("toStorageZone")
	public String toStorageZone(){
		return "/system/storage_zone";
	}
	
	/**
	 * 前往添加页面
	 * Author:luojing
	 * 2016年6月14日 上午11:49:36
	 */
	@RequestMapping("toAddStorageZone")
	public String toAddStorageZone(){
		return "/system/add_storage_zone";
	}


	/**
	 * 前往修改页面
	 * Author:luojing
	 * 2016年6月14日 上午11:49:36
	 */
	@RequestMapping("toModStorageZone")
	public String toModStorageZone(YcStorageZone sz,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("id", sz.getId());
		return "/system/mod_storage_zone";
	}
	
	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcStorageZoneSingle") 
	public void getYcStorageZoneSingle(YcStorageZone sz,HttpServletRequest request,HttpServletResponse response) {  
		try{
			YcStorageZone s = iYcStorageZoneService.getSingleInfo(sz);
			if(s!=null){
				FengUtil.OUTPRINTRESULT("获取数据成功", Constant.RESULT_SUCCESS_CODE, s, response);
			}else{
				FengUtil.OUTADDERROR("获取数据失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcStorageZoneList") 
	public void getYcStorageZoneList(YcStorageZone zone,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response) { 
		try{
			Pager<YcStorageZone> pager = new Pager<YcStorageZone>(page, rows);
			pager = iYcStorageZoneService.getListPagerInfo(pager, zone);
			FengUtil.OUTPRINTObject("成功", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	 * 获取分页记录 
	 * Auther:FENG 
	 */ 
	@RequestMapping("getTmsStorageZoneList") 
	public void getTmsStorageZoneList(YcStorageZone zone,Integer pageIndex,Integer limit,Integer start,HttpServletRequest request,HttpServletResponse response) { 
		try{
			SysUser su=(SysUser) FengUtil.GETDATABYSESSION(request, "user");
			if(su==null){
				FengUtil.RuntimeExceptionFeng("未登录");
			}
			zone.setStorageNo(su.getBranchNo());
			Pager<YcStorageZone> pager = new Pager<YcStorageZone>(start, limit,pageIndex);
			pager = iYcStorageZoneService.getListPagerInfo(pager, zone);
			FengUtil.OUTSYSPAGETMS(Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcStorageZone") 
	public void addYcStorageZone(YcStorageZone sz,HttpServletRequest request,HttpServletResponse response) { 
		try{
			SysUser su=(SysUser) FengUtil.GETDATABYSESSION(request, "user");
			if(su==null){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			sz.setStorageNo(su.getBranchNo());
			sz.setZoneNo(YC_Zone_Number(su.getBranchNo()));//库区编号
			sz.setCreateTime(DateUtil.getDateTimeFormatString());
			sz.setCreateUser("刘德华");
			Integer i = iYcStorageZoneService.addSingleInfo(sz);
			if(i>0){
				FengUtil.OUTADDSUCCESS("添加成功", Constant.RESULT_SUCCESS_CODE, "add_storage_zone", "zone_lst", response);
			}else{
				FengUtil.OUTADDERROR("添加失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("添加失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 修改方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcStorageZone") 
	public void modYcStorageZone(YcStorageZone sz,HttpServletRequest request,HttpServletResponse response) {  
		try{
			sz.setUpdateTime(DateUtil.getDateTimeFormatString());
			sz.setUpdateUser("张学友");
			Integer i = iYcStorageZoneService.modSingleInfo(sz);
			if(i>0){
				FengUtil.OUTADDSUCCESS("修改成功", Constant.RESULT_SUCCESS_CODE, "mod_storage_zone", "zone_lst", response);
			}else{
				FengUtil.OUTADDERROR("修改失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcStorageZone") 
	public void delYcStorageZone(String ids,HttpServletRequest request,HttpServletResponse response) {  
		try{
			String[] str = ids.split(",");
			List<BigInteger> list = new ArrayList<BigInteger>();
			for(int i=0;i<str.length;i++){
				list.add(new BigInteger(str[i]));
			}
			Integer i = iYcStorageZoneService.delSelect(list);
			if(i>0){
				FengUtil.OUTPRINTRESULT("删除"+i+"条数据成功", Constant.RESULT_SUCCESS_CODE, null, response);
			}else{
				FengUtil.OUTPRINTRESULT("删除失败,存在库区关联,无法删除", Constant.RESULT_ERROR_CODE, null, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("修改失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	
	/**
	 * 条件集合查询
	 * Author:luojing
	 * 2016年6月15日 下午3:04:43
	 */
	@RequestMapping("getStorageZone") 
	public void getStorageZone(YcStorageZone sz,HttpServletRequest request,HttpServletResponse response){
		try{
			List<YcStorageZone> list = iYcStorageZoneService.getStorageZone(sz);
			if(!list.isEmpty()){
				FengUtil.OUTPRINTRESULT("获取数据成功", Constant.RESULT_SUCCESS_CODE, list, response);
			}else{
				FengUtil.OUTADDERROR("获取数据失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("获取数据失败", Constant.RESULT_ERROR_CODE, response);
		}
	}
	 /**
	  * 生成库区编号
	  * Author:luojing
	  * 2016年6月12日 下午4:45:37
	  */
	 public  String YC_Zone_Number(String ycNo){
		 String str = "KQ-";
		 ycNo=ycNo.substring(ycNo.indexOf("-"));
		 YcRuleInfo yri=new YcRuleInfo();
		 yri.setRtype(1);;
		 yri=iYcRuleInfoService.getSingleInfo(yri);
		 iYcRuleInfoService.modSingleInfo(yri);
		 return str+ycNo+yri.getRvalue();
	 }
}
