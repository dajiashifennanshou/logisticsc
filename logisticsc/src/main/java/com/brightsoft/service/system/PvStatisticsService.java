package com.brightsoft.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.StatisticsHelper;
import com.brightsoft.dao.tms.PvStatisticsMapper;

@Service
public class PvStatisticsService {

	@Autowired
	private PvStatisticsMapper pvStatisticsMapper;
	
	public int selectPvAmount(){
		return pvStatisticsMapper.countPv();
	}
	
	/**
	 * 获取pv按月统计
	 * @return
	 */
	public List<StatisticsHelper> selectPvStatistics(){
		return pvStatisticsMapper.countPvDay4Month();
	}
	
}
