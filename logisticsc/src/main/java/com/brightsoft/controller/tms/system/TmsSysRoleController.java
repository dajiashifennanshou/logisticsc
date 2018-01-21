package com.brightsoft.controller.tms.system;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.model.Menu;
import com.brightsoft.model.SysMenu;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.system.SysMenuService;
import com.brightsoft.service.system.SysUserService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.sun.tools.classfile.StackMapTable_attribute.same_frame;

@Controller
@RequestMapping("/tms/sys/role")
public class TmsSysRoleController {

	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping("/list")
	public String toListRole(){
		return "/tms/system/listroleinfo";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Result search(Page<?> page){
		Result result = new Result();
		return result;
	}
	
	@RequestMapping("/getMenu")
	@ResponseBody
	public Result getMenu(HttpSession session){
		Result result = new Result();
		SysUser user = (SysUser)session.getAttribute(SystemConstant.TMS_SYS_USER_SESSION);
		if(user!=null){
			List<SysMenu> list = sysMenuService.getMenusByUser(user);
			result.setRows(list);
			result.setResult(true);
		}
		return result;
	}
}
