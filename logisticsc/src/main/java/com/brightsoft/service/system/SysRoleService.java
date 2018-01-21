package com.brightsoft.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.system.SysRoleMapper;
import com.brightsoft.model.SysRole;


@Service("sysRoleService")
public class SysRoleService {

	@Autowired
	public SysRoleMapper sysRoleMapper;
	
	
	public List<SysRole> selectSysRoleList(Integer state)
	{
		return sysRoleMapper.selectSysRoleList(state);
	}
	
	public int updateRoleMenu(Long[] menus,Long roleId){
		int result = 1;
		//try {
			sysRoleMapper.deleteRoleMenuByRole(roleId);
			sysRoleMapper.insertRoleMenu(menus, roleId);
//		} catch (Exception e) {
//			result = 0;
//		}
		
		
		return result;
	}
	
	public int insertSelective(SysRole record){
		int result = sysRoleMapper.insert(record);
		return result;
	}
	
	public SysRole selectByPrimaryKey(Long id){
		
		return sysRoleMapper.selectByPrimaryKey(id);
	}
	
	public int updateByPrimaryKeySelective(SysRole record){
		return sysRoleMapper.updateByPrimaryKeySelective(record);
	}
	
}
