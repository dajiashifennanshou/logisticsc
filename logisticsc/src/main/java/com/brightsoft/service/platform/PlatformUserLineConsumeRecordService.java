package com.brightsoft.service.platform;

import com.brightsoft.model.PlatformLineConsumeRecord;
import com.brightsoft.utils.Page;

public interface PlatformUserLineConsumeRecordService {
	/**
	 * 分页获我的消费记录
	 */
	public Page<?> selectBySelectedCondition(PlatformLineConsumeRecord consumeRecord,Page<?> page);
	
}
