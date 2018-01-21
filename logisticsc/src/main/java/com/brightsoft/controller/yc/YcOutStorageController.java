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
import com.brightsoft.entity.ResultEntity;
import com.brightsoft.entity.YcOutStorage;
import com.brightsoft.entity.YcStowage;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.yc.IYcOutStorageService;
import com.brightsoft.service.yc.IYcStowageDeliveryService;
import com.brightsoft.service.yc.IYcStowageService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.LogUtil;
import com.brightsoft.utils.yc.Pager;
import com.brightsoft.utils.yc.StrUtil;   

/** 
* YcOutStorage控制器 
* Author:luojing 
* 2016年6月20日 上午10:02:35
*/ 
@Controller 
public class YcOutStorageController {  
	@Autowired 
	private IYcOutStorageService iYcOutStorageService; 
	@Autowired 
	private IYcStowageService iYcStowageService ; 
	


	/**
	 * 前往入库记录管理页
	 * Author:luojing
	 * 2016年6月20日 上午10:02:35
	 */
	@RequestMapping("toOutStorage")
	public String toOutStorage(){
		return "/Clound/out_storage/out_storage";
	}
	
	/**
	 * 前往入库记录添加页
	 * Author:luojing
	 * 2016年6月17日 下午3:40:28
	 */
	@RequestMapping("toAddOutStorage")
	public String toAddOutStorage(){
		return "/Clound/out_storage/add_out_storage";
	}
	/**
	 * 前往详情页面
	 * Author:luojing
	 * 2016年6月17日 下午3:40:28
	 */
	@RequestMapping("toDetOutStorage")
	public String toDetOutStorage(HttpServletRequest request){
		String id=StrUtil.getString(request.getParameter("rowId"), "0");
		request.setAttribute("id", id);
		return "/Clound/out_storage/det_out_storage";
	}
	
	
	/**
	 * 前往入库记录修改页
	 * Author:luojing
	 * 2016年6月17日 下午3:40:28
	 */
	@RequestMapping("toModOutStorage")
	public String toModOutStorage(YcOutStorage os,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("id", os.getId());
		return "/Clound/out_storage/mod_out_storage";
	}
	
	

	/** 
	* 获取单条记录 
	* Author:luojing 
	*/ 
	@RequestMapping("getYcOutstorageSingle") 
	public void getYcOutstorageSingle(YcOutStorage os,HttpServletRequest request,HttpServletResponse response) {  
		try{
			YcOutStorage ins = iYcOutStorageService.getSingleInfo(os);
			if(ins!=null){
				FengUtil.OUTPRINTRESULT("获取数据成功", Constant.RESULT_SUCCESS_CODE, ins, response);
			}else{
				FengUtil.OUTADDERROR("获取数据失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	/** 
	* 获取分页记录 
	* Author:luojing 
	*/ 
	@RequestMapping("getYcOutStorageList") 
	public void getYcOutStorageList(YcOutStorage os,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response) {  
		try{
			Pager<YcOutStorage> pager = new Pager<YcOutStorage>(page, rows);
			pager = iYcOutStorageService.getListPagerInfo(pager, os);
			FengUtil.OUTPRINTObject("", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	/** 
	* 添加方法 即配送出库
	* Author:luojing 
	*/ 
	@RequestMapping("addYcOutstorage") 
	@Transactional
	public void addYcOutstorage(YcOutStorage os,HttpServletRequest request,HttpServletResponse response) {
		try{
			ResultEntity re=iYcOutStorageService.outStowage(request,os);
			if(re.getCode()==0){
				FengUtil.OUTADDSUCCESS("出库成功..", Constant.RESULT_SUCCESS_CODE, "add_out_storage", "out_storage_lst", response);
			}else{
				FengUtil.OUTROLLBACK(re.getMsg(), Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e);
			FengUtil.OUTROLLBACK("出库失败..", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 修改方法 
	* Author:luojing 
	*/ 
	@RequestMapping("modYcOutstorage") 
	public void modYcOutstorage(YcOutStorage os,HttpServletRequest request,HttpServletResponse response) {  
		try{
			os.setUpdateTime(DateUtil.getDateTimeFormatString());
			os.setUpdateUser("张学友");
			Integer i = iYcOutStorageService.modSingleInfo(os);
			if(i>0){
				FengUtil.OUTADDSUCCESS("修改成功", Constant.RESULT_SUCCESS_CODE, "mod_out_storage", "out_storage_lst", response);
			}else{
				FengUtil.OUTADDERROR("修改失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	/** 
	* 删除方法 
	* Author:luojing 
	*/ 
	@RequestMapping("delYcOutStorage") 
	public void delYcOutStorage(String ids,HttpServletRequest request,HttpServletResponse response) {  
		try{
			List<BigInteger> list = new ArrayList<BigInteger>();
			for(String str : ids.split(",")){
				list.add(new BigInteger(str));
			}
			Integer i = iYcOutStorageService.delSelect(list);
			if(i>0){
				FengUtil.OUTADDERROR("修改成功", Constant.RESULT_SUCCESS_CODE, response);
			}else{
				FengUtil.OUTADDERROR("修改失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
}
