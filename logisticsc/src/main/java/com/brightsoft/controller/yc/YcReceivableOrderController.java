package com.brightsoft.controller.yc; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.Constant.ErrorCode;
import com.brightsoft.entity.ResultEntity;
import com.brightsoft.entity.YcReceivableOrder;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.yc.IYcReceivableOrderService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengRuntimeException;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;   

/** 
* YcReceivableOrder控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcReceivableOrderController {  
	@Autowired 
	private IYcReceivableOrderService iYcReceivableOrderService; 


	
	/** 
	 * 获取单条记录 
	 * Auther:FENG 
	 */ 
	@RequestMapping("toFinanceListPage") 
	public String toFinanceListPage(HttpServletRequest request,HttpServletResponse response) {  
	
		return "/Clound/Finance/Finance";
	}
	/** 
	 * 审核页面
	 * Auther:FENG 
	 */ 
	@RequestMapping("toCheckFinanceListPage") 
	public String toCheckFinanceListPage(HttpServletRequest request,HttpServletResponse response) {  
	
		return "/Clound/Finance/check_Finance";
	} 
	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcReceivableOrderSingle") 
	public void getYcReceivableOrderSingle(YcReceivableOrder ro,HttpServletRequest request,HttpServletResponse response) {  
		try{ 
			ro=iYcReceivableOrderService.getSingleInfo(ro);
			FengUtil.OUTPRINTRESULT("获取成功", Constant.RESULT_SUCCESS_CODE, ro, response);
		}catch(Exception e){ 
			e.printStackTrace();
			FengUtil.OUTPRINTRESULT("异常..", Constant.RESULT_ERROR_CODE, ro, response);
		} 
	} 
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcReceivableOrderList") 
	public void getYcReceivableOrderList(HttpServletRequest request,HttpServletResponse response,YcReceivableOrder crt,Integer rows, Integer page) {  
		ResultEntity re=new ResultEntity();
		try{ 
			SysUser su=new SysUser();
			su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			if(su==null){
				re.setMsg(ErrorCode.NOTLOGIN.getName());
				re.setCode(ErrorCode.NOTLOGIN.getValue());
				FengUtil.RuntimeExceptionFeng();
			}
			Pager<YcReceivableOrder> pager=new Pager<YcReceivableOrder>(page, rows);
			pager=iYcReceivableOrderService.getListPagerInfo(pager, crt);
			FengUtil.OUTPRINTObject("获取成功", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){ 
			e.printStackTrace();
			FengUtil.ERRORDATA(re, response);
		} 
	} 
	/** 
	* 列表页面 
	* Auther:FENG 
	*/ 
	@RequestMapping("toListYcReceivableOrderPage") 
	public String toListYcReceivableOrderPage(HttpServletRequest request,HttpServletResponse response) {  
		return"YcReceivableOrder/list_yc_receivable_order";
	} 
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcReceivableOrder") 
	public void addYcReceivableOrder(YcReceivableOrder ro, HttpServletRequest request,HttpServletResponse response) {  
		try{ 
			SysUser su=new SysUser();
			su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			if(su==null){
				FengUtil.FengRuntimeException("你还未登录..");
			}
			ro.setCreateTime(DateUtil.getDateTimeFormatString());
			ro.setBranchNo(su.getBranchNo());
			ro.setCreateUser(su.getRealname());
			if(iYcReceivableOrderService.addSingleInfo(ro)>0){
				FengUtil.OUTADDSUCCESS("添加成功", Constant.RESULT_SUCCESS_CODE, "add_finance", "finance_lst", response);
			}else{
				FengUtil.OUTADDERROR("添加失败", 1, response);
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTADDERROR(e.getMsg(), 1, response);
		}catch(Exception e){ 
			e.printStackTrace();
			FengUtil.OUTADDERROR("异常", 1, response);

		} 
	} 
	/** 
	* 添加页面 
	* Auther:FENG 
	*/ 
	@RequestMapping("toAddYcReceivableOrderPage") 
	public String toAddYcReceivableOrderPage(HttpServletRequest request,HttpServletResponse response) {  
		return "/Clound/Finance/add_Finance"; 
	} 
	/** 
	* 修改方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcReceivableOrder") 
	public void modYcReceivableOrder(YcReceivableOrder ro,HttpServletRequest request,HttpServletResponse response) {  
		ResultEntity re=new ResultEntity();
		try{ 
			SysUser su=new SysUser();
			su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			if(su==null){
				re.setMsg("你还未登录..");
				FengUtil.RuntimeExceptionFeng("你还未登录..");
			}
			ro.setUpdateTime(DateUtil.getDateTimeFormatString());
			ro.setUpdateUser(su.getRealname());
			if(iYcReceivableOrderService.modSingleInfo(ro)>0){
				
				FengUtil.OUTADDSUCCESS("修改成功", Constant.RESULT_SUCCESS_CODE, "add_finance", "finance_lst", response);
			}else{
				FengUtil.OUTADDERROR("修改失败", 1, response);
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTADDERROR(e.getMsg(), 1, response);
		}catch(Exception e){ 
			FengUtil.OUTADDERROR("异常", 1, response);
		} 
	} 
	/** 
	* 修改页面 
	* Auther:FENG 
	*/ 
	@RequestMapping("toModYcReceivableOrderPage") 
	public String toModYcReceivableOrderPage(Integer rowId,HttpServletRequest request,HttpServletResponse response) {  
		request.setAttribute("id", rowId);
		return "/Clound/Finance/mod_Finance"; 
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcReceivableOrder") 
	public void delYcReceivableOrder(HttpServletRequest request,HttpServletResponse response) {  
		try{ 

		}catch(Exception e){ 

		} 
	} 
}
