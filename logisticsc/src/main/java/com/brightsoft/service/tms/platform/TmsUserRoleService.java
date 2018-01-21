package com.brightsoft.service.tms.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.tms.UserRoleMapper;
import com.brightsoft.model.UserRole;

@Service
public class TmsUserRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	public int insert(UserRole userRole){
		return userRoleMapper.insert(userRole);
	}
	
	public int insertBatch(List<UserRole> list){
		return userRoleMapper.insertBatch(list);
	}
	
}
