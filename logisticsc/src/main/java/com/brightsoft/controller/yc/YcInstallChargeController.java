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
import com.brightsoft.entity.YcInstallCharge;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.yc.IYcInstallChargeService;
import com.brightsoft.utils.yc.DateUtil;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.Pager;
import com.brightsoft.utils.yc.StrUtil;   

/** 
* YcInstallCharge控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcInstallChargeController {  
	@Autowired 
	private IYcInstallChargeService iYcInstallChargeService; 

	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcInstallChargeSingle") 
	public void getYcInstallChargeSingle(HttpServletRequest request,HttpServletResponse response,YcInstallCharge ic) {  
		try{
			ic=iYcInstallChargeService.getSingleInfo(ic);
			FengUtil.OUTPRINTRESULT("成功", Constant.RESULT_SUCCESS_CODE, ic, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTPRINTRESULT("成功", Constant.RESULT_SUCCESS_CODE, null, response);
		}
	} 
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcInstallChargeList") 
	public void getYcInstallChargeList(HttpServletRequest request,YcInstallCharge yc,HttpServletResponse response,Integer page,Integer rows) {  
		try{
			SysUser su=new SysUser();
			su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			if(su==null){
				FengUtil.RuntimeExceptionFeng("你还未登录");
			}
			yc.setBranchNo(su.getBranchNo());
			Pager<YcInstallCharge> pager=new Pager<YcInstallCharge>(page, rows);
			pager=iYcInstallChargeService.getListPagerInfo(pager, yc);
			FengUtil.OUTPRINTObject("成功", Constant.RESULT_SUCCESS_CODE, pager, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	}
	/** 
	 * 根据货物类型获取货物安装费用
	 * Auther:FENG 
	 */ 
	@RequestMapping("getYcInstallCostBy") 
	public void getYcInstallCostBy(HttpServletRequest request,HttpServletResponse response) {  
		try{
			SysUser su=new SysUser();
			su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			if(su==null){
				FengUtil.RuntimeExceptionFeng("请登录..");
			}
			String type=StrUtil.getString(request.getParameter("type"), "");
			List<YcInstallCharge> lst=iYcInstallChargeService.getYcInstallCostBy(type,su.getBranchNo());
			/*//计算费用
			float sum=0;
			for(YcInstallCharge yic:lst){
				sum+=yic.getInstallCharge();
			}*/
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE,lst, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("失败", Constant.RESULT_ERROR_CODE, response);
		}
	}
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcInstallCharge") 
	public void addYcInstallCharge(HttpServletRequest request,HttpServletResponse response,YcInstallCharge ic) {  
		try{
			SysUser su=new SysUser();
			su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			if(su==null){
				FengUtil.RuntimeExceptionFeng("你还未登录");
			}
			ic.setBranchNo(su.getBranchNo());
			ic.setCreateUser(su.getRealname());
			ic.setCreateTime(DateUtil.getDateTimeFormatString());
			iYcInstallChargeService.addSingleInfo(ic);
			FengUtil.OUTADDSUCCESS("添加成功!", Constant.RESULT_SUCCESS_CODE, "add_install", "install_lst", response);
		}catch(RuntimeException e){
			FengUtil.OUTADDERROR(e.getLocalizedMessage(),Constant.RESULT_ERROR_CODE, response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("添加失败!",Constant.RESULT_ERROR_CODE, response);
		}
	} 
	@RequestMapping("testInstall")
	public void testInstall(){
		for(int i=9;i<107;i++){
			YcInstallCharge ic=new YcInstallCharge();
			ic.setBranchNo("YC-00007");
			ic.setDeliverCharge(10);
			ic.setInstallCharge(0);
			ic.setTwoLvCode(i);
			iYcInstallChargeService.addSingleInfo(ic);
		}
		
	}
	/** 
	* 修改方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcInstallCharge") 
	public void modYcInstallCharge(HttpServletRequest request,HttpServletResponse response,YcInstallCharge ic) {  
		try{
			SysUser su=new SysUser();
			su=(SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			if(su==null){
				FengUtil.RuntimeExceptionFeng("你还未登录");
			}
			ic.setUpdateTime(DateUtil.getDateTimeFormatString());
			ic.setUpdateUser(su.getRealname());
			iYcInstallChargeService.modSingleInfo(ic);
			FengUtil.OUTADDSUCCESS("修改成功!", Constant.RESULT_SUCCESS_CODE, "mod_install", "install_lst", response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTADDERROR("修改失败!",Constant.RESULT_ERROR_CODE, response);
		}
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcInstallCharge") 
	public void delYcInstallCharge(HttpServletRequest request,HttpServletResponse response) {  
		try{
			String ids = request.getParameter("ids");
			String[] str = ids.split(",");
			List<BigInteger> list = new ArrayList<BigInteger>();
			for(int i=0;i<str.length;i++){
				list.add(new BigInteger(str[i]));
			}
			Integer i = iYcInstallChargeService.delSelect(list);
			if(i>0){
				FengUtil.OUTPRINTRESULT("删除成功", 0, "del", response);
			}else{
				FengUtil.OUTADDERROR("修改失败", Constant.RESULT_ERROR_CODE, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	/** 
	 * 到添加页面
	 * Auther:FENG 
	 */ 
	@RequestMapping("toAddYcInstallChargePage") 
	public String toAddYcInstallChargePage(HttpServletRequest request,HttpServletResponse response) {  
		return "/Clound/InstallCharge/add_install_charge";
	} 
	/** 
	 * 到修改页面
	 * Auther:FENG 
	 */ 
	@RequestMapping("toModYcInstallChargePage") 
	public String toModYcInstallChargePage(HttpServletRequest request,HttpServletResponse response) {  
		String id=StrUtil.getString(request.getParameter("rowId"), "0");
		request.setAttribute("id", id);
		System.out.println(id);
		return "/Clound/InstallCharge/mod_install_charge";
	} 
	/** 
	 * 到列表页面
	 * Auther:FENG 
	 */ 
	@RequestMapping("toListYcInstallChargePage") 
	public String toListYcInstallChargePage(HttpServletRequest request,HttpServletResponse response) {  
		return "/Clound/InstallCharge/InstallCharge";
	} 
	
}
