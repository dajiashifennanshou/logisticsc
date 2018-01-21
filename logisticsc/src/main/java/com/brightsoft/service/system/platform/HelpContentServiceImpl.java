package com.brightsoft.service.system.platform;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.system.SysHelpContentMapper;
import com.brightsoft.model.SysHelpContent;

@Service
public class HelpContentServiceImpl {
	
	@Autowired
	private SysHelpContentMapper contentMapper;
	
	public boolean insertHelpContent(SysHelpContent content){
		content.setTime(new Date());
		content.setState(1);
		if(contentMapper.insert(content)>0){
			return true;
		}
		return false;
	}
	
	public boolean updateHelpContent(SysHelpContent content){
		if(contentMapper.updateByPrimaryKeySelective(content) > 0){
			return true;
		}
		return false;
	}
	public SysHelpContent selectContent(Long id){
		SysHelpContent content = contentMapper.selectHelpContent(id);
		return content;
	}
}
