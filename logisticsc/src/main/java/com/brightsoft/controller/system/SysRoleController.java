package com.brightsoft.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.model.SysRole;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.system.SysRoleService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/sysRole")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;
	
	
	@RequestMapping("/sys_role_list")
	@ResponseBody
	public Result selectSysRoleList(Page<SysRole> page){
		Result ret = new Result();
		
		List<SysRole> list = sysRoleService.selectSysRoleList(null);
		ret.setRows(list);
		ret.setResult(true);
		return ret;
	}
	
	
	@RequestMapping("/sys_role_select")
	@ResponseBody
	public List<SysRole> getSysRoleSelect(){
		Result ret = new Result();
		
		List<SysRole> list = sysRoleService.selectSysRoleList(null);

		return list;
	}
	
	@RequestMapping(value = "/updateRoleMenu")
	@ResponseBody
	public Result updateRoleMenu( @RequestParam(value = "menus[]")Long[] menus,Long roleId) { 
		Result reusResult = new Result();
		try {
			sysRoleService.updateRoleMenu(menus, roleId);
			reusResult.setResult(true);
		} catch (Exception e) {
			// TODO: handle exception
			reusResult.setResult(false);
		}
		return reusResult;
	}
	
	@RequestMapping("/addSysRole")
	@ResponseBody
	public Result addSysRole(SysRole sysRole) {
		Result ret = new Result();
		//System.out.println(MD5.getMD5Code(sysRole.getRolename()));
		try {
			sysRole.setRolestate(Const.STATE_YES);
			sysRoleService.insertSelective(sysRole);
			ret.setResult(true);
		} catch (Exception e) {
			// TODO: handle exception
			ret.setResult(false);
		}
			return ret;
	}
	
	
	
	/**
	 * 修改角色
	 * @return
	 */
	@RequestMapping("/editSysRoleById/{id}")
	public ModelAndView editeSysRoleById(@PathVariable("id")Long id) {
		ModelAndView mv = new ModelAndView();
		
		SysRole sysRole = sysRoleService.selectByPrimaryKey(id);
		
		mv.addObject("sysRole", sysRole);
		mv.setViewName("/system/sys_role_edit");
		return mv;
	}
	
	@RequestMapping("/updateSysRole")
	@ResponseBody
	public Result updateSysRole(SysRole sysRole) {
		Result ret = new Result();
		//System.out.println(MD5.getMD5Code(sysRole.getRolename()));
		try {
			sysRoleService.updateByPrimaryKeySelective(sysRole);
			ret.setResult(true);
		} catch (Exception e) {
			// TODO: handle exception
			ret.setResult(false);
		}
			return ret;
	}
	
	@RequestMapping("/updateSysRoleState")
	@ResponseBody
	public Result updateSysRoleState(SysRole sysRole) {
		Result ret = new Result();
		//System.out.println(MD5.getMD5Code(sysRole.getRolename()));
		if(sysRole.getRolestate() == Const.STATE_YES){
			sysRole.setRolestate(Const.STATE_NO);
		}else{
			sysRole.setRolestate(Const.STATE_YES);
		}
		try {
			sysRoleService.updateByPrimaryKeySelective(sysRole);
			ret.setResult(true);
		} catch (Exception e) {
			// TODO: handle exception
			ret.setResult(false);
		}
			return ret;
	}
	
}
