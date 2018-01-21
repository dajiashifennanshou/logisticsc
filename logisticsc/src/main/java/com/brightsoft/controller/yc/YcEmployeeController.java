package com.brightsoft.controller.yc; 
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.Constant.ErrorCode;
import com.brightsoft.entity.ResultEntity;
import com.brightsoft.entity.YcEmployee;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.system.PvStatisticsService;
import com.brightsoft.service.yc.IYcEmployeeService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengRuntimeException;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;
import com.brightsoft.utils.yc.StrUtil;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;   

/** 
* 员工管理控制器 
* Author:luojing 
*/ 
@Controller 
public class YcEmployeeController {  
	@Autowired 
	private IYcEmployeeService iYcEmployeeService; 

	@Autowired
	private PvStatisticsService ps;
	/**
	 * 前往员工管理页面
	 * Author:luojing
	 * 2016年6月13日 下午6:17:49
	 */
	@RequestMapping("toEmployee")
	public String toEmployee(){
		return "/Clound/employee/employee";
	}
	
	
	
	
	/**
	 * 前往员工添加页面
	 * Author:luojing
	 * 2016年6月13日 下午6:17:49
	 */
	@RequestMapping("toAddEmployee")
	public String toAddEmployee(){
		return "/Clound/employee/add_employee";
	}
	
	/**
	 * 前往修改页
	 * Author:luojing
	 * 2016年6月14日 上午10:34:03
	 */
	@RequestMapping("toModEmployee")
	public String toModEmployee(YcEmployee emp,HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("id", emp.getId());
		return "/Clound/employee/mod_employee";
	}
	/**
	 * 获取司机信息，根据配载单里的车辆信息
	 * Author:feng
	 * 2016年6月14日 上午10:34:03
	 */
	@RequestMapping("getDriverInfo")
	public void getDriverInfo(String deliveryNo,HttpServletRequest request,HttpServletResponse response){
		try{
			List<YcEmployee> lst=new ArrayList<YcEmployee>();
			lst=iYcEmployeeService.getDriverInfoByDNo(deliveryNo);
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, lst, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	}
	/**
	 * 根据车牌号获取司机信息
	 * Author:feng
	 * 2016年6月14日 上午10:34:03
	 */
	@RequestMapping("getEmployeeByCarNo")
	public void getEmployeeByCarNo(String carNo,HttpServletRequest request,HttpServletResponse response){
		ResultEntity re=new ResultEntity();
		try{
			List<YcEmployee> lst=new ArrayList<YcEmployee>();
			String branchNo=(String) FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY);
			if(branchNo==null || branchNo==""){
				re.setMsg(ErrorCode.NOTLOGIN.getName());
				re.setCode(ErrorCode.NOTLOGIN.getValue());
				FengUtil.RuntimeExceptionFeng();
			}
			lst=iYcEmployeeService.getEmployeeByCarNo(carNo,branchNo);
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, lst, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.ERRORDATA(re, response);
		}
	}
	/**
	 * 获取安装工信息，根据配送单信息
	 * Author:feng
	 * 2016年6月14日 上午10:34:03
	 */
	@RequestMapping("getInstallerInfo")
	public void getInstallerInfo(String deliveryNo,HttpServletRequest request,HttpServletResponse response){
		try{
			List<YcEmployee> lst=new ArrayList<YcEmployee>();
			String branchNo=FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY).toString();
			if(!StrUtil.VString(branchNo)){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			lst=iYcEmployeeService.getInstallerInfoByDNo(deliveryNo,branchNo);
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, lst, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	}
	
	/**
	 * 获取单条记录  
	 * Author:luojing
	 * 2016年6月14日 上午10:28:13
	 */
	@RequestMapping("getYcEmployeeSingle") 
	public void getYcEmployeeSingle(YcEmployee em,HttpServletRequest request,HttpServletResponse response) { 
		try{
			String branchNo=FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY).toString();
			if(!StrUtil.VString(branchNo)){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			em.setBranchNo(branchNo);
			YcEmployee emp = iYcEmployeeService.getSingleInfo(em);
			if(emp != null){
				FengUtil.OUTPRINTRESULT("获取数据成功", Constant.RESULT_SUCCESS_CODE, emp, response);
			}else{
				FengUtil.OUTADDERROR("获取数据失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	}
	
	/**
	 * 多ID查询 
	 * Author:luojing
	 * 2016年6月14日 上午10:28:13
	 */
	@RequestMapping("getYcEmployeeInId") 
	public void getYcEmployeeInId(String ids,HttpServletRequest request,HttpServletResponse response) { 
		try{
			List<BigInteger> arryId = new ArrayList<BigInteger>();
			for(String str:ids.split(",")){
				arryId.add(new BigInteger(str));
			}
			String branchNo=FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY).toString();
			if(!StrUtil.VString(branchNo)){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			List<YcEmployee> list = iYcEmployeeService.getYcEmployeeInId(arryId,branchNo);
			if(!list.isEmpty()){
				FengUtil.OUTPRINTRESULT("获取数据成功", Constant.RESULT_SUCCESS_CODE, list, response);
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
	 * Author:luojing
	 * 2016年6月14日 上午10:28:05
	 */
	@RequestMapping("getYcEmployeeList") 
	public void getYcEmployeeList(YcEmployee emp,Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response) {
		try{
			String branchNo=FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY).toString();
			if(!StrUtil.VString(branchNo)){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			emp.setBranchNo(branchNo);
			Pager<YcEmployee> pager = new Pager<YcEmployee>(page, rows);
			pager = iYcEmployeeService.getListPagerInfo(pager, emp);
			FengUtil.OUTPRINTObject("成功", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/**
	 * 添加方法
	 * Author:luojing
	 * 2016年6月14日 上午10:27:54
	 */
	@RequestMapping("addYcEmployee") 
	public void addYcEmployee(YcEmployee emp,HttpServletRequest request,HttpServletResponse response) {
		try{
			String branchNo=FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY).toString();
			if(!StrUtil.VString(branchNo)){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			SysUser pu=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			if(pu==null){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			YcEmployee temp=new YcEmployee();
			temp.setCardNumber(emp.getCardNumber());
			temp=iYcEmployeeService.getSingleInfo(temp);
			if(temp!=null){
				FengUtil.RuntimeExceptionFeng("身份证号码重复");
			}
			emp.setBranchNo(branchNo);
			emp.setEntryTime(DateUtil.getDateTimeFormatString());
			emp.setCreateUser(pu.getRealname());
			Integer i = iYcEmployeeService.addSingleInfo(emp);
			if(i>0){
				FengUtil.OUTADDSUCCESS("添加成功", Constant.RESULT_SUCCESS_CODE, "add_employee", "employee_lst", response);
			}else{
				FengUtil.OUTADDERROR("添加失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(RuntimeException e){
			FengUtil.OUTADDERROR(e.getLocalizedMessage(), Constant.RESULT_ERROR_CODE, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("异常", Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/**
	 * 修改方法 
	 * Author:luojing
	 * 2016年6月14日 上午10:27:49
	 */
	@RequestMapping("modYcEmployee") 
	public void modYcEmployee(YcEmployee emp,HttpServletRequest request,HttpServletResponse response) {  
		try{
			emp.setUpdateTime(DateUtil.getDateTimeFormatString());
			emp.setUpdateUser("刘德华");
			Integer i = iYcEmployeeService.modSingleInfo(emp);
			if(i>0){
				FengUtil.OUTADDSUCCESS("修改成功", Constant.RESULT_SUCCESS_CODE, "mod_employee", "employee_lst", response);
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
	 * Author:luojing
	 * 2016年6月14日 上午10:27:43
	 */
	@RequestMapping("delYcEmployee") 
	public void delYcEmployee(YcEmployee ye,HttpServletRequest request,HttpServletResponse response) {  
		try{
			Integer i = iYcEmployeeService.delSingleInfo(ye);
			if(i>0){
				FengUtil.OUTPRINTRESULT("删除成功", 0, "del", response);
			}else{
				FengUtil.OUTADDERROR("删除失败", 1, response);
			}
 		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("删除失败", Constant.RESULT_ERROR_CODE, response);
		}
	} 

	
	/**
	 * 获取员工信息集合
	 * Author:luojing
	 * 2016年6月14日 下午4:41:41
	 */
	@RequestMapping("getEmployee")
	public void getEmployee(YcEmployee ye,HttpServletRequest request,HttpServletResponse response){
		try{
			String branchNo=FengUtil.GETDATABYSESSION(request, Constant.BRANCHNOKEY).toString();
			if(!StrUtil.VString(branchNo)){
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			ye.setBranchNo(branchNo);
			ye.setEmployeeName(StrUtil.getString(request.getParameter("ins"), null));
			List<YcEmployee> list =new ArrayList<YcEmployee>();
			list=iYcEmployeeService.getEmployee(ye);
			FengUtil.OUTPRINTRESULT("获取数据成功", Constant.RESULT_SUCCESS_CODE, list, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	}
}
