package com.brightsoft.service.tms.platform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.tms.NoticeInfoMapper;
import com.brightsoft.model.NoticeInfo;
import com.brightsoft.model.User;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.WebConstant;

@Service
public class NoticeService {

	@Autowired
	private NoticeInfoMapper noticeInfoMapper;
	
	/**
	 * 通过通知类型，获取通知信息
	 * @param page
	 * @param noticeType
	 * @return
	 */
	public Result selectByCondition(Page<?> page,NoticeInfo noticeInfo,User user){
		Result result = new Result();
		int results = noticeInfoMapper.countRows(noticeInfo,user);
		List<NoticeInfo> noticeInfos = noticeInfoMapper.selectByNoticeType(page, noticeInfo,user);
		result.setRows(noticeInfos);
		result.setResults(results);
		return result;
	}
}
