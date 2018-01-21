package com.brightsoft.service.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.SmsTemplateMapper;
import com.brightsoft.model.SmsTemplate;
import com.brightsoft.utils.Page;

@Service
public class SmsTemplateServiceImpl {

	@Autowired
	private SmsTemplateMapper smsTemplateMapper;
	
	/**
	 * 
	 * 方法描述：获取短息模板
	 * @return
	 * @author dub
	 * @version 2016年5月19日 下午5:49:08
	 */
	public List<SmsTemplate> selectSmsTemplate(Page<?> page){
		return smsTemplateMapper.getSmsTemplateList(page);
	}
	
	/**
	 * 
	 * 方法描述：获取短息模板记录总数
	 * @return
	 * @author dub
	 * @version 2016年5月19日 下午5:49:27
	 */
	public int selectTemplateListCount(){
		return smsTemplateMapper.getSmsTemplateListCount();
	}
	
	/**
	 * 
	 * 方法描述：更新短息模板
	 * @param smsTemplate
	 * @return
	 * @author dub
	 * @version 2016年5月19日 下午5:50:49
	 */
	public boolean updateSmsTemplate(SmsTemplate smsTemplate){
		smsTemplateMapper.updateSelective(smsTemplate);
		return true;
	}
	
	/**
	 * 方法描述：启用/禁用模板
	 * @param templateId
	 * @param isEnabled
	 * @return
	 * @author dub
	 * @version 2016年5月19日 下午6:53:41
	 */
	public boolean updateEnabled(Long templateId,Boolean isEnabled){
		boolean flag = false;
		SmsTemplate smsTemplate = new SmsTemplate();
		smsTemplate.setId(templateId);
		if(isEnabled){
			smsTemplate.setIsEnabled(1);
			if(smsTemplateMapper.updateSelective(smsTemplate)>0){flag = true;}
		}else{
			smsTemplate.setIsEnabled(0);
			if(smsTemplateMapper.updateSelective(smsTemplate)>0){flag = true;}
		}
		return flag;
	}
	
	/**
	 * 方法描述：根据模板编码获取模板信息
	 * @param templateAbbr
	 * @return
	 * @author dub
	 * @version 2016年5月20日 上午11:12:12
	 */
	public SmsTemplate selectTemplateContentByAbbr(String templateAbbr){
		return smsTemplateMapper.getSmsTemplateByAbbr(templateAbbr);
	}
}

