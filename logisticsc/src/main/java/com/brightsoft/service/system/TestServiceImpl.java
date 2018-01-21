package com.brightsoft.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.system.TestMapper;
import com.brightsoft.model.Test;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Autowired
	public TestMapper testUserMapper;
	
	public Test asd(long id) {
		// TODO Auto-generated method stub
		 return testUserMapper.selectByPrimaryKey(id);
	}

}
