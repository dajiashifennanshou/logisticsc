package com.brightsoft.controller.yc; 
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.entity.YcCarType;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.yc.IYcCarTypeService;
import com.brightsoft.utils.yc.FengUtil;   

/** 
* YcCarType控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcCarTypeController {  
	@Autowired 
	private IYcCarTypeService iYcCarTypeService; 


	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcCarTypeSingle") 
	public void getYcCarTypeSingle(HttpServletRequest request,HttpServletResponse response) {  
		try{ 

		}catch(Exception e){ 

		} 
	} 
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcCarTypeList") 
	public void getYcCarTypeList(HttpServletRequest request,HttpServletResponse response,YcCarType crt) {  
		try{ 

		}catch(Exception e){ 

		} 
	} 
	/** 
	 * 获取所有记录 
	 * Auther:FENG 
	 */ 
	@RequestMapping("getYcCarTypeListBy") 
	public void getYcCarTypeListBy(HttpServletRequest request,HttpServletResponse response,YcCarType crt) {  
		try{ 
			SysUser su=new SysUser();
			su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			crt.setBranchNo(su.getBranchNo());
			List<YcCarType> lst=new ArrayList<YcCarType>();
			lst=iYcCarTypeService.getYcCarTypeListBy(crt);
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, lst, response);
		}catch(Exception e){ 
			FengUtil.OUTADDERROR("", Constant.RESULT_ERROR_CODE, response);
		} 
	} 
	/** 
	* 列表页面 
	* Auther:FENG 
	*/ 
	@RequestMapping("toListYcCarTypePage") 
	public String toListYcCarTypePage(HttpServletRequest request,HttpServletResponse response) {  
		return"/Clound/YcCarType/list_yc_car_type";
	} 
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcCarType") 
	public void addYcCarType(HttpServletRequest request,HttpServletResponse response) {  
		try{ 

		}catch(Exception e){ 

		} 
	} 
	/** 
	* 添加页面 
	* Auther:FENG 
	*/ 
	@RequestMapping("toAddYcCarTypePage") 
	public String toAddYcCarTypePage(HttpServletRequest request,HttpServletResponse response) {  
		return "/Clound/YcCarType/add_yc_car_type"; 
	} 
	/** 
	* 修改方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcCarType") 
	public void modYcCarType(HttpServletRequest request,HttpServletResponse response) {  
		try{ 

		}catch(Exception e){ 

		} 
	} 
	/** 
	* 修改页面 
	* Auther:FENG 
	*/ 
	@RequestMapping("toModYcCarTypePage") 
	public String toModYcCarTypePage(HttpServletRequest request,HttpServletResponse response) {  
		return "YcCarType/mod_yc_car_type"; 
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcCarType") 
	public void delYcCarType(HttpServletRequest request,HttpServletResponse response) {  
		try{ 

		}catch(Exception e){ 

		} 
	} 
}
