package com.brightsoft.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.system.SysMenuMapper;
import com.brightsoft.model.SysMenu;
import com.brightsoft.model.SysUser;

@Service("sysMenuService")
public class SysMenuService {
	
	@Autowired
	public SysMenuMapper sysMenuMapper;

	List<SysMenu> selectMenus(int state){
		System.out.println("11111");
		return null;
	}
    
	/**
	 * 根据roleid获取 菜单集合
	 * Author:FENG
	 * 2016年7月16日
	 * @param roleId
	 * @return
	 */
	public List<SysMenu> getMeaunsByRoleId(String roleId){
		return sysMenuMapper.getMeaunsByRoleId(roleId);
	}
   public SysMenu getMenusByRole(Long id,Integer state){
	   
	   List<SysMenu> list = sysMenuMapper.selectMenusByRole(id, state);
	   List<SysMenu> allList = sysMenuMapper.selectMenus(state);
	   SysMenu treeMenu = new SysMenu();
	   treeMenu.setId(new Long(0));
	   SysMenu userTreeMenu = new SysMenu();
	   userTreeMenu.setId(new Long(0));
	   this.createTree(list, userTreeMenu);
	   this.setCheckedLeaf(userTreeMenu.getSubMenus(), allList);
	   this.createTree(allList, treeMenu); 
	   if(true){
		   
	   }
    	return treeMenu;
    }
   public List<SysMenu> getMenusByUser(SysUser sysUser) {
		// 第一步:获取用户的所有权限菜单
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", sysUser.getId());
		
		List<SysMenu> list = sysMenuMapper.selectByUserId(sysUser.getId());
		if(null == list || list.size() < 1)
			return null;
		
		SysMenu sysMenu = new SysMenu();
		//Collections.reverse(list);
		sysMenu.setId(new Long(0));
		this.createTree(list, sysMenu);
		
		return sysMenu.getSubMenus();	
	}
   
   public SysMenu getMenus(Integer state){
	   
	   List<SysMenu> allList = sysMenuMapper.selectMenus(state);

	   SysMenu treeMenu = new SysMenu();
	   treeMenu.setId(new Long(0));
	   this.createTree(allList, treeMenu);
    	
    	return treeMenu;
    }
	
	private void createTree(List<SysMenu> list,SysMenu treeSysMenu){
		
		for(int j=(list.size()-1);j>=0;j--){
			SysMenu sysMenu = list.get(j);
			if(sysMenu.getParentId().equals(treeSysMenu.getId()) ){
//				System.out.println(sysMenu.getParentId()+"--"+treeSysMenu.getId());
				treeSysMenu.getSubMenus().add(sysMenu);
				//list.remove(j);
				this.createTree(list, sysMenu);
				
			}
		}
	}
	
	private void setCheckedLeaf(List<SysMenu> userMenuList,List<SysMenu> allMenuList){		
		for(int i=0;i<userMenuList.size();i++){
			SysMenu sysMenu = userMenuList.get(i);
			if(sysMenu.getSubMenus()!=null&&sysMenu.getSubMenus().size()>0){
				this.setCheckedLeaf(sysMenu.getSubMenus(),allMenuList);
			}else{
				 for(int k=0;k<allMenuList.size();k++){
					   SysMenu allSysMenu = allMenuList.get(k);
					   if(allSysMenu.getId() == sysMenu.getId()){
						   allSysMenu.setChecked(true);
					   }
				   }
			}
		}
	}
}
