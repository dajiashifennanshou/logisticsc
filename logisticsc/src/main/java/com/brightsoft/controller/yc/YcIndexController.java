package com.brightsoft.controller.yc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.dao.yc.IYcEmployeeDao;
import com.brightsoft.entity.YcDeliveryOrder;
import com.brightsoft.entity.YcEmployee;
import com.brightsoft.entity.YcStorageBranch;
import com.brightsoft.model.SysMenu;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.system.SysMenuService;
import com.brightsoft.service.yc.IYcDeliveryOrderService;
import com.brightsoft.service.yc.IYcStorageBranchService;
import com.brightsoft.utils.yc.FengUtil;
import com.brightsoft.utils.yc.StrUtil;


/**
 * 页面跳转 Controller
 * Author:luojing
 * 2016年6月6日 下午3:51:41
 */
@Controller
public class YcIndexController {
	@Autowired
	private IYcDeliveryOrderService isds;
	@Autowired
	private IYcStorageBranchService iycs;
	@Autowired
	private SysMenuService sysM;
	/**
	 * 主页
	 * Author:luojing
	 * 2016年6月6日 下午4:04:59
	 */
	@RequestMapping("yc-index")
	public String index(HttpServletRequest request){
		SysUser su= (SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
		if(su==null){
			return "/Clound/login";
		}
		if(su.getIsBranch()==1){
			return "/Clound/login";
		}
		YcStorageBranch ysb=new YcStorageBranch();
		System.out.println(su.getBranchNo());
		ysb.setBranchNo(su.getBranchNo());
		ysb=iycs.getSingleInfo(ysb);
		FengUtil.SETDATABYSESSION(request, Constant.BRANCHNOKEY, su.getBranchNo());
		request.setAttribute("branchName", ysb.getBranchName());
		return "/Clound/index";
	}
	
	/**
	 * 前往登录页面
	 * Author:FENG
	 * 2016年7月16日
	 * @return
	 */
	@RequestMapping("yc-login")
	public String toLogin(){
		
		return "/Clound/login";
	}
	/**
	 * 前往分账页面
	 * Author:FENG
	 * 2016年7月18日
	 * @return
	 */
	@RequestMapping("to_D_I_cost")
	public String toD_I_cost(){
		return "/system/install_delivery_cost";
	}
	/**
	 * 退出登录
	 * Author:FENG
	 * 2016年7月16日
	 * @param request
	 * @return
	 */
	@RequestMapping("exitLogin")
	public String exitLogin(HttpServletRequest request){
		//清除用户数据
		request.removeAttribute("user");
		return "/Clound/login";
	}

	/**
	 * 获取用户权限
	 * Author:luojing
	 * 2016年6月6日 下午4:04:59
	 */
	@RequestMapping("yc-getUserMeau")
	public void getUserMeau(HttpServletRequest request,HttpServletResponse response){
		try{
			SysUser su= (SysUser) FengUtil.GETDATABYSESSION(request, Constant.YCLOGINUSER);
			if(su==null){
				FengUtil.RuntimeExceptionFeng("你还未登录");
			}
			if(su.getIsBranch()==1){
				FengUtil.RuntimeExceptionFeng("请使用云仓管理系统账号登录..");
			}
			List<SysMenu> sm=sysM.getMeaunsByRoleId(su.getRoleId().toString());
			FengUtil.OUTAPPSUCCESS(Constant.RESULT_SUCCESS_CODE	, sm, response);
		}catch(Exception e){
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	}
	/**
	 * 主页
	 * Author:luojing
	 * 2016年6月6日 下午4:04:59
	 */
	@RequestMapping("yc-main")
	public String main(){
		return "/Clound/main";
	}
	
	/**
	 * 前往评价页面
	 * Author:FENG
	 * 2016年7月14日
	 * @return
	 */
	@RequestMapping("yc-pj")
	public String goToPingjia(){
		
		
		
		return "/Clound/pingjia";
	}
}
