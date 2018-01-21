package com.brightsoft.service.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.XzqhInfoMapper;
import com.brightsoft.model.XzqhInfo;

@Service
public class XzqhServiceImpl {

	@Autowired
	private XzqhInfoMapper xzqhInfoMapper;
	
	/**
	 * 查询 行政区划信息
	 * @param pid
	 * @return
	 */
	public List<XzqhInfo> selectByPid(String pid){
		return xzqhInfoMapper.selectByPid(pid);
	}
	
	public XzqhInfo selectValueById(String id){
		return xzqhInfoMapper.selectByPrimaryKey(id);
	}
}
