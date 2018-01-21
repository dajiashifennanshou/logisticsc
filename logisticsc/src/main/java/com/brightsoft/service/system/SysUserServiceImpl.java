package com.brightsoft.service.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.system.SysMenuMapper;
import com.brightsoft.dao.system.SysRoleMapper;
import com.brightsoft.dao.system.SysUserMapper;
import com.brightsoft.model.SysMenu;
import com.brightsoft.model.SysUser;
import com.brightsoft.utils.Page;

@Service("userService")
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	public SysUserMapper sysUserMapper;
	
	@Autowired
	public SysMenuMapper sysMenuMapper;
	@Autowired
	public SysRoleMapper sysRoleMapper;

	public SysUser selectByPrimaryKey(long id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	public int insert(SysUser user) {
		// TODO Auto-generated method stub
		return sysUserMapper.insert(user);
	}

	public void deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub

	}

	public boolean updateByPrimaryKey(SysUser record) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			sysRoleMapper.updateUserRole(record.getId(),record.getRoleId());
			sysUserMapper.updateByPrimaryKeySelective(record);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<SysUser> selectAll() {
		return sysUserMapper.selectAll();
	}

	public SysUser selectByNameAndPwd(String LoginName, String LoginPass) {
		// TODO Auto-generated method stub
		return sysUserMapper.selectByNameAndPwd(LoginName, LoginPass);
	}

	public List<SysUser> selectByPage(Page<SysUser> page) {
		// TODO Auto-generated method stub
		List<SysUser> list = sysUserMapper.selectByPage(page);
		return list;
	}

	public int getSysUsersCount(Page<SysUser> page) {
		// TODO Auto-generated method stub
		return sysUserMapper.getSysUsersCount(page);
	}

	public SysUser selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		SysUser user = sysUserMapper.selectByPrimaryKey(id);
		List<Map<String, Long>> list = sysRoleMapper.selectRoleIdByUser(id);
		if(list != null&&list.size()>0){
			Map<String, Long> map = list.get(0);
			if(map!=null){
				user.setRoleId(map.get("role_id"));
			}
			
		}
		return user;
	}
	

	public SysUser selectByName(@Param("loginname") String loginname){
		SysUser user = sysUserMapper.selectByName(loginname);
		return user;
	}
	
	public int insertUserAndRole(SysUser user){
		int result = 0;
		try {
			Long roleId = user.getRoleId();
			sysUserMapper.insert(user);			
			sysRoleMapper.insertUserRole(user.getId(), roleId);
			System.out.println(user.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return result;
	}
	
	
	/*
	private void createTree(List<SysMenu> list,List<SysMenu> treeList){
		for(int i=0;i<treeList.size();i++){
			SysMenu treeSysMenu = treeList.get(i);
			for(int j=(list.size()-1);j>0;j--){
				SysMenu sysMenu = list.get(j);
				if(sysMenu.getParentId() == treeSysMenu.getId()){
					treeSysMenu.getSubMenus().add(sysMenu);
					list.remove(j);
					this.createTree(list, treeSysMenu.getSubMenus());
					
				}
			}
		}
		if(list!=null && list.size()>0){	
			
		}else
		{
			
		}

	}
	*/
}
