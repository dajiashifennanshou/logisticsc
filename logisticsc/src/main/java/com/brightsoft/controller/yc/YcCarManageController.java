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
import com.brightsoft.Constant.ErrorCode;
import com.brightsoft.entity.ResultEntity;
import com.brightsoft.entity.YcCarManage;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.yc.IYcCarManageService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengRuntimeException;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;   

/**
 * 汽车信息管理Controller
 * Author:luojing
 * 2016年6月14日 下午3:14:49
 */
@Controller 
public class YcCarManageController {  
	@Autowired 
	private IYcCarManageService iYcCarManageService; 

	/**
	 * 前往车辆管理页
	 * Author:luojing
	 * 2016年6月14日 下午3:58:02
	 */
	@RequestMapping("toCarManage")
	public String toCarManage(){
		return "/Clound/CarManage/car_manage";
	}
	
	/**
	 * 前往添加页
	 * Author:luojing
	 * 2016年6月14日 下午3:59:30
	 */
	@RequestMapping("toAddCar")
	public String toAddCar(){
		return "/Clound/CarManage/add_car";
	}
	
	/**
	 * 前往修改页
	 * Author:luojing
	 * 2016年6月14日 下午3:59:30
	 */
	@RequestMapping("toModCar")
	public String toModCar(YcCarManage car,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("id", car.getId());
		return "/Clound/CarManage/mod_car";
	}

	/** 
	* 获取单条记录 
	* Author:luojing
	*/ 
	@RequestMapping("getYcCarManageSingle") 
	public void getYcCarManageSingle(YcCarManage car,HttpServletRequest request,HttpServletResponse response) {  
		try{
			YcCarManage c = iYcCarManageService.getSingleInfo(car);
			if(c!=null){
				FengUtil.OUTPRINTRESULT("获取数据成功", Constant.RESULT_SUCCESS_CODE, c, response);
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
	@RequestMapping("getYcCarManageList") 
	public void getYcCarManageList(YcCarManage car,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response) {  
		ResultEntity re=new ResultEntity();
		try{
			SysUser su=new SysUser();
			su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			if(su==null){
				re.setMsg(ErrorCode.NOTLOGIN.getName());
				re.setCode(ErrorCode.NOTLOGIN.getValue());
				FengUtil.RuntimeExceptionFeng();
			}
			car.setBranchNo(su.getBranchNo());
			Pager<YcCarManage> pager = new Pager<YcCarManage>(page, rows);
			pager = iYcCarManageService.getListPagerInfo(pager, car);
			FengUtil.OUTPRINTObject("成功", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			FengUtil.ERRORDATA(re, response);
			e.printStackTrace();
		}
	} 
	/** 
	 * 获取所有信息
	 * Author:luojing
	 */ 
	@RequestMapping("getYcCarAllList") 
	public void getYcCarAllList(YcCarManage car,HttpServletRequest request,HttpServletResponse response) {  
		try{
			String branchNo=(String) FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY);
			if(branchNo==null){
				throw new FengRuntimeException("你还未登录");
			}
			car.setBranchNo(branchNo);
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, iYcCarManageService.getYcCarAllList(car), response);
		}catch(RuntimeException e){
			FengUtil.OUTADDERROR(e.getLocalizedMessage(), 1, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("异常", 1, response);
		}
	} 
	/** 
	* 添加方法 
	* Author:luojing
	*/ 
	@RequestMapping("addYcCarManage") 
	public void addYcCarManage(YcCarManage car,HttpServletRequest request,HttpServletResponse response) {  
		try{
			SysUser su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			if(su==null){
				throw new RuntimeException("你还未登录");
			}
			String branchNo=(String) FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY);
			car.setCreateTime(DateUtil.getDateTimeFormatString());
			car.setCreatUser(su.getRealname());
			car.setCarStatus(0);
			car.setBranchNo(branchNo);
			Integer i = iYcCarManageService.addSingleInfo(car);
			if(i>0){
				FengUtil.OUTADDSUCCESS("添加成功", Constant.RESULT_SUCCESS_CODE, "add_car", "car_lst", response);
			}else{
				FengUtil.OUTADDERROR("添加失败",  Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("车牌号重复添加", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 修改方法 
	* Author:luojing
	*/ 
	@RequestMapping("modYcCarManage") 
	public void modYcCarManage(YcCarManage car,HttpServletRequest request,HttpServletResponse response) {  
		try{
			car.setUpdateTime(DateUtil.getDateTimeFormatString());
			car.setUpdateUser("张学友");
			Integer i = iYcCarManageService.modSingleInfo(car);
			if(i>0){
				FengUtil.OUTADDSUCCESS("修改成功", Constant.RESULT_SUCCESS_CODE, "mod_car", "car_lst", response);
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
	@RequestMapping("delYcCarManage") 
	public void delYcCarManage(String ids,HttpServletRequest request,HttpServletResponse response) {  
		try{
			String[] str = ids.split(",");
			List<BigInteger> list = new ArrayList<BigInteger>();
			for(int i=0;i<str.length;i++){
				list.add(new BigInteger(str[i]));
			}
			Integer i = iYcCarManageService.delSelect(list);
			if(i>0){
				FengUtil.OUTPRINTRESULT("删除成功", Constant.RESULT_SUCCESS_CODE, "del", response);
			}else{
				FengUtil.OUTADDERROR("删除失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("车辆正在执行任务，无法删除", Constant.RESULT_ERROR_CODE, response);
		}
	} 
}
