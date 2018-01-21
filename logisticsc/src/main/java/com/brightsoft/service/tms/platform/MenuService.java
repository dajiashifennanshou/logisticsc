package com.brightsoft.service.tms.platform;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.tms.MenuMapper;
import com.brightsoft.model.Menu;

@Service
public class MenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	
	public List<Menu> selectById(Long roleId){
		List<Menu> list =  menuMapper.selectByRoleId(roleId);
		//去重
		for ( int i = 0 ; i < list.size() - 1 ; i ++ ) {
			for ( int j = list.size() - 1 ; j > i; j -- ) {
				if (list.get(j).getId()==list.get(i).getId()) {
					list.remove(j);
				}
			}
		} 
		if(null == list || list.size() < 1)
			return null;
		
		Menu Menu = new Menu();
//		Collections.reverse(list);
		this.createTree(list, Menu);
		this.sortForMenu(Menu);
		return Menu.getSubMenus();
	}
 	
	public List<Menu> selectByUserId(long userId){
		List<Menu> list =  menuMapper.selectByUserId(userId);
		//去重
		for ( int i = 0 ; i < list.size() - 1 ; i ++ ) {
			for ( int j = list.size() - 1 ; j > i; j -- ) {
				if (list.get(j).getId()== list.get(i).getId()) {
					list.remove(j);
				}
			}
		} 
		
		if(null == list || list.size() < 1)
			return null;
		
		Menu Menu = new Menu();
		//Collections.reverse(list);
		this.createTree(list, Menu);
		this.sortForMenu(Menu);
		return Menu.getSubMenus();
	}
	
	private void createTree(List<Menu> list,Menu treeSysMenu){
		for(int i=0, j=list.size();i<j;i++){
			Menu Menu = list.get(i);
			if(Menu.getParentId() == treeSysMenu.getId()){
				treeSysMenu.getSubMenus().add(Menu);
				//list.remove(j);
				this.createTree(list, Menu);
				
			}
		}
	}
	

	 public Menu selectByRoleId(Long userId,Long roleId){
		 Menu treeMenu = new Menu();
		   Menu userTreeMenu = new Menu();
		   List<Menu> list = new ArrayList<Menu>();
		   List<Menu> allList = new ArrayList<Menu>();
		   if(roleId != null){
			   if(userId == 0){
				   list = menuMapper.selectByRoleId(roleId);
				   allList = menuMapper.selectAllMenu();
			   }else{
				   list = menuMapper.selectByRoleId(roleId);
				   allList = menuMapper.selectByUserId(userId);
			   }
		   }else{
			   if(userId == 0){
				   list = menuMapper.selectByUserId(userId);
				   allList = menuMapper.selectAllMenu();
			   }else{
				   allList = menuMapper.selectByUserId(userId);
				   this.createTree1(allList, treeMenu); 
				   this.sortForMenu(treeMenu);
				   return treeMenu;
//				   treeMenu.setSubMenus(allList);
//				   return treeMenu;
			   }
		   }
		   
//		   menuMapper.selectAllMenu();
		   
		   this.createTree1(list, userTreeMenu);
		   this.setCheckedLeaf(userTreeMenu.getSubMenus(), allList);
		   this.createTree1(allList, treeMenu); 
		   this.sortForMenu(treeMenu);
		   if(true){
			   
		   }
	    	return treeMenu;
	    }
	   
	   public Menu getMenus(Integer state){
		   
		   List<Menu> allList = menuMapper.selectAllMenu();

		   Menu treeMenu = new Menu();
		   this.createTree1(allList, treeMenu);
		   this.sortForMenu(treeMenu);
		   return treeMenu;
	    }
		
	   //菜单分组
		private void createTree1(List<Menu> list,Menu treeSysMenu){
			
			for(int i = 0,j=list.size();i<j;i++){
				Menu sysMenu = list.get(i);

				if(sysMenu.getParentId() == treeSysMenu.getId()){
					
					treeSysMenu.getSubMenus().add(sysMenu);
					//list.remove(j);
					this.createTree(list, sysMenu);
					
				}
			}
		}
		
		//check子节点
		private void setCheckedLeaf(List<Menu> userMenuList,List<Menu> allMenuList){		
			for(int i=0;i<userMenuList.size();i++){
				Menu sysMenu = userMenuList.get(i);
				if(sysMenu.getSubMenus()!=null&&sysMenu.getSubMenus().size()>0){
					this.setCheckedLeaf(sysMenu.getSubMenus(),allMenuList);
				}else{
					 for(int k=0;k<allMenuList.size();k++){
						   Menu allSysMenu = allMenuList.get(k);
						   if(allSysMenu.getId() == sysMenu.getId()){
							   allSysMenu.setChecked(true);
						   }
					   }
				}
			}
		}
		
		//菜单排序
		public void sortForMenu(Menu menu){
			List<Menu> menuList = menu.getSubMenus();
			for (int i = 0; i < menuList.size(); i++) {
				for (int j = i; j < menuList.size(); j++) {
					if(menuList.get(i).getSortIndex()>menuList.get(j).getSortIndex()){
						Menu mn = menuList.get(i);
						menuList.set(i, menuList.get(j));
						menuList.set(j, mn);
					}
				}
				Menu subMenu = menuList.get(i);
				if(subMenu.getSubMenus() != null && !subMenu.getSubMenus().isEmpty()){
					sortForMenu(subMenu);
				}
			}
		}
}
