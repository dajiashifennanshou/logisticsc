package com.brightsoft.service.tms.platform.financialmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.dao.tms.MoneyDiaryRecordMapper;
import com.brightsoft.model.MoneyDiaryRecord;
import com.brightsoft.utils.Result;

/**
 * 现金日记 service
 * @author yangshoubao
 *
 */
@Service
public class MoneyDiaryRecordService {

	@Autowired
	private MoneyDiaryRecordMapper moneyDiaryRecordMapper;
	
	/**
	 * 查询 现金日记列表
	 * @param searchParams
	 * @return
	 */
	public Result selectByParams(SearchParams searchParams){
		int count = moneyDiaryRecordMapper.selectByParamsCount(searchParams);
		List<MoneyDiaryRecord> list = moneyDiaryRecordMapper.selectByParams(searchParams);
		Result result = new Result();
		result.setResults(count);
		result.setRows(list);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("income", moneyDiaryRecordMapper.selectIncomeByParamsTotal(searchParams));
		data.put("expend", moneyDiaryRecordMapper.selectExpendByParamsTotal(searchParams));
		result.setData(data);
		return result;
	}
	
	/**
	 * 添加 现金日记
	 * @param moneyDiaryRecord
	 * @return
	 */
	public int insert(MoneyDiaryRecord moneyDiaryRecord){
		if(moneyDiaryRecord == null){
			return 0;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = moneyDiaryRecord.getDateStr();
		if(StringUtils.isNotEmpty(dateStr)){
			try {
				moneyDiaryRecord.setDate(format.parse(dateStr));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return moneyDiaryRecordMapper.insert(moneyDiaryRecord);
	}
}
