package com.brightsoft.service.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.brightsoft.common.enums.SmsTemplateEnum;
import com.brightsoft.dao.platform.SmsTemplateMapper;
import com.brightsoft.model.SmsTemplate;
import com.brightsoft.utils.PureNetUtil;
import com.brightsoft.utils.sms.SmsParams;

public class SmsManagerService {

	/** 订单号 */
	private final String ORDER_NUMBER = "orderNumber";
	/** 运单号 */
	private final String WAYBILL_NUMBER = "waybillNumber";
	/** 公司名称 */
	private final String COMPANY = "company";
	/** 网点名称 */
	private final String OUTLETS_NAME = "outletsName";
	/** 订单状态 */
	private final String ORDER_STATUS = "orderStatus";
	/** 车牌号 */
	private final String CAR_NUMBER = "carNumber";
	/** 签收人 */
	private final String SIGN_MAN = "signMan";
	/** 联系人电话 */
	private final String LINK_PHONE = "linkPhone";
	/** 确认运费 */
	private final String FREIGHT = "freight";
	
	private String appkey;
	
	private String uri;
	
	private String tpl_id;
	
	@Autowired
	private SmsTemplateMapper smsTemplateMapper;
	
	public boolean sendSms(String phone, SmsParams smsParams, SmsTemplateEnum smsTemplateEnum){
		// 获取 短信模板内容
		String templateContent = null;
		try {
			SmsTemplate smsTemplate = smsTemplateMapper.getSmsTemplateByAbbr(smsTemplateEnum.getValue());
			if(smsTemplate == null){
				System.out.println("获取短信模板内容失败");
				return false;
			}
			if(smsTemplate.getIsEnabled() != null && smsTemplate.getIsEnabled() != 1){
				System.out.println("该短信模板已禁用");
				return false;
			}
			templateContent = smsTemplate.getTemplateContent();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		boolean flag = false;
		List<String> params = needReplaceCode(templateContent); 
		StringBuffer sb = new StringBuffer();
		sb.append(uri);
		sb.append("?mobile="+phone);
		sb.append("&tpl_id="+tpl_id);
		try {
			sb.append("&tpl_value="+URLEncoder.encode("#code#="+getSmsContent(params,smsParams,templateContent),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		sb.append("&key="+appkey);
		System.out.println(sb.toString());
		String result = PureNetUtil.get(sb.toString());
		JSONObject obj=JSONObject.parseObject(result);
        String errorCode=obj.getString("error_code");
        if(errorCode.equals("0")){
        	flag = true;
        	System.out.println("短信发送成功："+obj.toJSONString());
        }else{
        	System.out.println("短信发送失败："+obj.toJSONString());
        }
		return flag;
	}
	
	/**
	 * 方法描述：替换模板参数
	 * @param params
	 * @param map
	 * @param templateContent
	 */
	public String getSmsContent(List<String> params,SmsParams smsParams, String templateContent){
		Iterator<String> it = params.iterator();
		while(it.hasNext()){
			String param = it.next();
			if(ORDER_NUMBER.equals(param)){
				templateContent = templateContent.replace("{"+param+"}", smsParams.getOrderNumber());
			}else if(WAYBILL_NUMBER.equals(param)){
				templateContent = templateContent.replace("{"+param+"}", smsParams.getWaybillNumber());
			}else if(COMPANY.equals(param)){
				templateContent = templateContent.replace("{"+param+"}", smsParams.getCompany());
			}else if(OUTLETS_NAME.equals(param)){
				templateContent = templateContent.replace("{"+param+"}", smsParams.getOutletsName());
			}else if(CAR_NUMBER.equals(param)){
				templateContent = templateContent.replace("{"+param+"}", smsParams.getCarNumber());
			}else if(ORDER_STATUS.equals(param)){
				templateContent = templateContent.replace("{"+param+"}", smsParams.getOrderStatus());
			}else if(SIGN_MAN.equals(param)){
				templateContent = templateContent.replace("{"+param+"}", smsParams.getSignMan());
			}else if(LINK_PHONE.equals(param)){
				templateContent = templateContent.replace("{"+param+"}", smsParams.getLinkPhone());
			}else if(FREIGHT.equals(param)){
				templateContent = templateContent.replace("{"+param+"}", smsParams.getFreight() + "");
			}
		}
		return templateContent;
	}
	
	/**
     * 需要替换的货运交易系统模板code
     * @param templateContent
     * @return
     */
    public static List<String> needReplaceCode(String templateContent){
        List<String> arrCode = new ArrayList<String>();
        if(StringUtils.isEmpty(templateContent)){
            return null;
        }
        Pattern pattern = Pattern.compile("(?<=\\{)(.+?)(?=\\})");
        Matcher matcher = pattern.matcher(templateContent);
        while (matcher.find()){
            arrCode.add(matcher.group());
        }
        return arrCode;
    }
	
	
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setTpl_id(String tpl_id) {
		this.tpl_id = tpl_id;
	}
	
}
