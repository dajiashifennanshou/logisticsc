package com.brightsoft.utils.sms;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.brightsoft.utils.PureNetUtil;

public class SendSmsTool {
	
	private final String APPKEY="512f27381faffc337217bafe6317d7d3";	
	private final String URI="http://v.juhe.cn/sms/send";
	private final String TPL_ID="38015";
	
	/**************************/
	/** 订单号 */
	private final String ORDER_NUMBER = "orderNumber";
	/** 运单号 */
	private final String WAYBILL_NUMBER = "orderNumber";
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

	/**
	 * 方法描述：发送短信
	 * @param phone
	 * @param map
	 * @param templateContent
	 * @return
	 */
	public boolean sendSmsHttp(String phone, SmsParams smsParams, String templateContent){
		boolean flag = false;
		List<String> params = needReplaceCode(templateContent); 
		StringBuffer sb = new StringBuffer();
		sb.append(URI);
		sb.append("?mobile="+phone);
		sb.append("&tpl_id="+TPL_ID);
		try {
			sb.append("&tpl_value="+URLEncoder.encode("#code#="+getSmsContent(params,smsParams,templateContent),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		sb.append("&key="+APPKEY);
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
    
    /**
     * 方法描述：
     * @param args
     * @author dub
     * @version 2016年5月21日 下午2:32:50
     */
    public static void main(String[] args) {
    	/* TYPEID=13653;
		 String tpl_value="#code#="+verification;
         tpl_value=URLEncoder.encode(tpl_value, "utf-8");
         String url="http://v.juhe.cn/sms/send?mobile="+mobile+"&tpl_id="+TYPEID+"&tpl_value="
                 + tpl_value+"&key="+APPKEY;
        sendResult=PureNetUtil.get(url);*/
		//System.out.println(needReplaceCode("asdfasd{asdf}sadfa{asdfa}"));
//    	List<String> list = new ArrayList<String>();
//    	list.add(ORDER_NUMBER);
//    	list.add(LINK_PHONE);
//    	Map<String, Object> map = new HashMap<String, Object>();
//    	map.put(ORDER_NUMBER, "lisi");
//    	map.put(LINK_PHONE, "12028062123");
//    	System.out.println(getSmsContent(list, map,"asdfasdf{orderNumber},fsafda{linkPhone}"));
//    	System.out.println("sss".replace("s", "0"));
    	
//    	sendSmsHttp("18202806211", map, "你的货运交易系统订单号：{orderNumber},联系人电话：{linkPhone}");
//    	SendSmsTool s = new SendSmsTool();
//    	SmsParams smsParams = new SmsParams();
//    	smsParams.setCarNumber("13213");
//    	smsParams.setOrderNumber("asdfas");
//    	s.sendSmsHttp("18202806211", smsParams, smsTemplateEnum)
//    	String content = "您的货运交易系统订单号：{orderNumber}，已被{company}，{outletsName}，{orderStatus}";
//    	SendSmsTool s = new SendSmsTool();
//    	SmsParams smsParams = new SmsParams();
//    smsParams.setOrderNumber("18202807123");
//    smsParams.setCompany("/常德");
//    smsParams.setOutletsName("成都");
//    smsParams.setOrderStatus("一句但");
//    	s.sendSmsHttp("18349211072", smsParams, null);
    }
}

