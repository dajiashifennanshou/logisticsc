package com.brightsoft.service.tms.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.tms.RoleMenuMapper;
import com.brightsoft.model.RoleMenu;

@Service
public class TmsRoleMenuService {

	@Autowired
	private RoleMenuMapper roleMenuMapper;
	
	/**
	 * 批量插入
	 * @param roleMenu
	 * @return
	 */
	public int insert(List<RoleMenu> list){
		return roleMenuMapper.insertBatch(list);
	}
	/**
	 * 批量删除
	 * @param roleIds
	 * @return
	 */
	public int deleteByRoleId(List<Long> roleIds){
		return roleMenuMapper.deleteBatchByRoleId(roleIds);
	}
}
