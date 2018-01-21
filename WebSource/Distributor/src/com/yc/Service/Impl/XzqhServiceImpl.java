package com.yc.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.XzqhInfoMapper;
import com.yc.Entity.XzqhInfo;


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
