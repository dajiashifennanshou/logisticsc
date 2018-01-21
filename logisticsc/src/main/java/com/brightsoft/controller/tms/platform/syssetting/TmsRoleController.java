package com.brightsoft.controller.tms.platform.syssetting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.model.Menu;
import com.brightsoft.model.Role;
import com.brightsoft.model.RoleMenu;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.MenuService;
import com.brightsoft.service.tms.platform.TmsRoleService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.DateTools;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/role")
public class TmsRoleController {

	@Autowired
	private TmsRoleService tmsRoleService;
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/getRole")
	public ModelAndView toListRoleMenu(){
		//ModelAndView mav = new ModelAndView("/tms/platform/systemmanager/tms_role");
		ModelAndView mav = new ModelAndView("/tms/platform/systemmanager/roleinfo/listroleinfo");
		return mav;
	}
	
	@RequestMapping("/getRoleMenu")
	public ModelAndView toListRoleInfo(){
		//ModelAndView mav = new ModelAndView("/tms/platform/systemmanager/roleinfo/listroleinfo");
		ModelAndView mav = new ModelAndView("/tms/platform/systemmanager/tms_role_menu");
		return mav;
	}
	
	/**
	 * 获取权限信息
	 * @param page
	 * @param condition
	 * @param request
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result getRoleInfo(Page<?> page,String condition,HttpServletRequest request){
		Result result = new Result();
		HttpSession session  = request.getSession();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user!=null){
			result = tmsRoleService.selectByCondition(page, user, condition);
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 添加角色
	 * @param role
	 * @param menuIds
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Result insert(Role role,@RequestParam("menuIds[]")String menuIds[],HttpServletRequest request){
		Result result = new Result();
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		if(null != user){
			role.setCreatePersonId(user.getId());
			role.setCreateTime(DateTools.getYMDHMS());
			role.setPlatformType(Const.TMS_ROLE_PLATFORM_TYPE_CUSTOMER);
			List<RoleMenu> list = new ArrayList<RoleMenu>();
			for (String menuId : menuIds) {
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setMenuId(Long.parseLong(menuId));
				list.add(roleMenu);
			}
			role.setOwnerType(Const.TMS_ROLE_OWNER_CUSTOMER);
			if(tmsRoleService.insert(role, list)>0){
				result.setResult(true);
			}
		}
		return result;
	}
	
	/**
	 * 更新角色信息
	 * @param role
	 * @param menuIds
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result updateRole(Role role,@RequestParam("menuIds[]")String menuIds[],HttpServletRequest request){
		Result result = new Result();
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		if(null != user){
			result = tmsRoleService.updateRoleAndPerm(role, menuIds);
		}
		return result;
	}
	
	/**
	 * 添加角色
	 * @param role
	 * @param menuIds
	 * @return
	 */
//	@RequestMapping("/insert")
//	@ResponseBody
//	public Result insert(Role role/*,@RequestParam("menuIds[]")String menuIds[]*/,HttpServletRequest request){
//		Result result = new Result();
//		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
//		if(null != user){
//			role.setCreatePersonId(user.getId());
//			/*role.setCreateTime(DateTools.getYMDHMS());
//			List<RoleMenu> list = new ArrayList<RoleMenu>();
//			for (String menuId : menuIds) {
//				RoleMenu roleMenu = new RoleMenu();
//				roleMenu.setMenuId(Long.parseLong(menuId));
//				list.add(roleMenu);
//			}
//			if(tmsRoleService.insert(role, list)>0){
//				result.setResult(true);
//			}*/
//			if(tmsRoleService.insertRole(role)){
//				result.setResult(true);
//			}
//		}
//		return result;
//	}
	
	/**
	 * 删除角色
	 * @param roleIds
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result toDeleteRole(@RequestParam("roleIds[]")String roleIds[]){
		Result result = new Result();
		if(roleIds!=null&&roleIds.length>0){
			List<Long> list = new ArrayList<Long>();
			for (String roleId : roleIds) {
				list.add(Long.parseLong(roleId));
			}
			Map<String, Object> map = tmsRoleService.deleteBatch(list);
			result.setResult((Boolean)map.get("success"));
			result.setMsg((String)map.get("msg"));
		}
		return result;
	}
	/**
	 * 获取当前用户权限信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/rolemenu")
	@ResponseBody
	public Result getRoleMenu(HttpServletRequest request){
		Result result = new Result();
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			List<Menu> list = menuService.selectByUserId(user.getId());
			result.setRows(list);
			result.setResult(true);
		}
		return result;
	}

	/**
	 * 获取创建的角色信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajax/getrole")
	@ResponseBody
	public Result getRole(HttpServletRequest request, Long outletsId){
		Result result = new Result();
		User user = (User)request.getSession().getAttribute(SystemConstant.TMS_USER_SESSION);
		if(null != user){
			List<Role> list = tmsRoleService.getRole(user.getId(),user.getUserType(), outletsId);
			result.setRows(list);
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 跳转到角色显示页面
	 * @return
	 */
	@RequestMapping("/listRole")
	public String toListRole(){
		return "/tms/platform/systemmanager/tms_role";
	}
/*	
	@RequestMapping("/getRole")
	@ResponseBody
	public Result getRole(HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			result.setRows(tmsRoleService.selectByCreatePersonId(user.getId()));
			result.setResult(true);
		}
		return result;
	}*/
	
	/**
	 * 禁用角色
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/forbid")
	@ResponseBody
	public Result forbidRole(Long roleId){
		return tmsRoleService.updateRoleStatusById(roleId, Const.TMS_ROLE_STATUS_FORBID);
	}
	
	/**
	 * 启用角色
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/enable")
	@ResponseBody
	public Result enableRole(Long roleId){
		return tmsRoleService.updateRoleStatusById(roleId, Const.TMS_ROLE_STATUS_ENABLE);
	}
	
	/**
	 * 更新角色信息
	 * @param role
	 * @return
	 */
	@RequestMapping("/updateRole")
	@ResponseBody
	public Result updateRole(Role role){
		Result result = new Result();
		if(tmsRoleService.updateRole(role)){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 根据用户id获取角色
	 * @param session
	 * @return
	 */
	@RequestMapping("/searchRole")
	@ResponseBody
	public Result searchRole(HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			result.setRows(tmsRoleService.selectByCreatePersonId(user.getId(),user.getUserType()));
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 根据角色id获取菜单信息
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/searchRoleMenu")
	@ResponseBody
	public List<Menu> searchRoleMenu(Long roleId,HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		List<Menu> menuList = null;
		if(user!=null){
			Menu menu =  menuService.selectByRoleId(user.getId(),roleId);
			if(menu!=null){
				menuList = menu.getSubMenus();
			}
		}
		
		return menuList;
	}
	
	/**
	 * 更新角色菜单信息
	 * @param menus
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/updateRoleMenu")
	@ResponseBody
	public Result updateRoleMenu( @RequestParam(value = "menus[]")Long[] menus,Long roleId) { 
		Result reusResult = new Result();
		try {
			tmsRoleService.updateRoleMenu(menus, roleId);
			reusResult.setResult(true);
		} catch (Exception e) {
			reusResult.setResult(false);
		}
		return reusResult;
	}
}