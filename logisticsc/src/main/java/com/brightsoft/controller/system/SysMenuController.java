package com.brightsoft.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.brightsoft.model.SysMenu;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.system.SysMenuService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {

	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 根据角色id查询菜单
	 * @return
	 */
	@RequestMapping("/getMenusByRole")
	@ResponseBody
	public List<SysMenu> getMenusByRole(Long roleId) {

		SysMenu treeMenu = sysMenuService.getMenusByRole(roleId,Const.STATE_YES);
		return treeMenu.getSubMenus();
	}
	
	/**
	 * 根据角色id查询菜单
	 * @return
	 */
	@RequestMapping("/getAllMenus")
	@ResponseBody
	public List<SysMenu> getAllMenus() {

		SysMenu treeMenu = sysMenuService.getMenus(null);
		treeMenu.setMenuname("系统菜单");
		List<SysMenu> menuList = new ArrayList<SysMenu>();
		menuList.add(treeMenu);
		return menuList;
	}
	/*@RequestMapping("/getMenusByRole/{id}")
	public ModelAndView getMenusByRole(@PathVariable("id")Long id) {

		ModelAndView mv = new ModelAndView();
		List<SysMenu> list = sysMenuService.getMenusByRole(id,1);
		String menuJson = JSON.toJSONString(list,
				SerializerFeature.DisableCircularReferenceDetect);

		mv.addObject("treeList", menuJson);
		mv.setViewName("/system/sys_role_menu");
			return mv;
	}*/
}
