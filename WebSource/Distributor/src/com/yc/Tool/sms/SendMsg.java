package com.yc.Tool.sms;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class SendMsg {
	
	//平台短信发送类型 0 =验证码 1=初始密码 2=申请通过
	public static int PLATFORM_SENDTYPE_0=0;
	public static int PLATFORM_SENDTYPE_1=1;
	public static int PLATFORM_SENDTYPE_2=2;
	
	private static final String APPKEY="512f27381faffc337217bafe6317d7d3";
	
	/**
	 * 205405----号码异常/同一号码发送次数过于频繁
	 * 205404----发送失败，具体原因请参考返回reason
	 * @MethodName :sendcode
	 * @Description :
	 * @param :
	 * @return 
	 * @date :2016年1月14日
	 */
	 public static Map<String, Object> Verification(String mobile,String code,int sendType){
		 	Map<String, Object> map=new HashMap<String, Object>();
		 	String sendResult = null;
		 	int TYPEID;
	        try {
	        	if(sendType == PLATFORM_SENDTYPE_0){
	        		 TYPEID=38018;
	        		 String tpl_value="#code#="+code;
	 	            tpl_value=URLEncoder.encode(tpl_value, "utf-8");
	 	            String url="http://v.juhe.cn/sms/send?mobile="+mobile+"&tpl_id="+TYPEID+"&tpl_value="
	 	                    + tpl_value+"&key="+APPKEY;
	 	           sendResult=PureNetUtil.get(url);
	        	}else if(sendType == PLATFORM_SENDTYPE_1){
	        		TYPEID = 38017;
	        		String tpl_value="#code#="+code+"&#name#="+mobile;
	 	            tpl_value=URLEncoder.encode(tpl_value, "utf-8");
	 	            String url="http://v.juhe.cn/sms/send?mobile="+mobile+"&tpl_id="+TYPEID+"&tpl_value="
	 	                    + tpl_value+"&key="+APPKEY;
	 	            System.out.println(url);
	 	            sendResult=PureNetUtil.get(url);
	        	}else if(sendType == PLATFORM_SENDTYPE_2){
	        		TYPEID = 38016;
	        		String tpl_value="#code#="+code+"&#name#="+mobile;
	 	            tpl_value=URLEncoder.encode(tpl_value, "utf-8");
	 	            String url="http://v.juhe.cn/sms/send?mobile="+mobile+"&tpl_id="+TYPEID+"&tpl_value="
	 	                    + tpl_value+"&key="+APPKEY;
	 	            sendResult=PureNetUtil.get(url);
	        	}
	            if(sendResult==null){
	            	map.put("reason","网络异常");
	            	map.put("error_code", -1);
	            	return map;
	            }
	            JSONObject obj=JSONObject.parseObject(sendResult);
	            if(obj==null){
	            	map.put("reason","网络异常");
	            	map.put("error_code", -1);
	            	return map;
	            }
	            String error_code=obj.getString("error_code");
	            String reason=obj.getString("reason");
	            if(error_code==null||!error_code.equals("0")){
	            	map.put("reason", reason);
	            	map.put("error_code", error_code);
	            }else {
	            	map.put("reason", reason);
	            	map.put("codecode", code);
	            	map.put("error_code", 0);
				}
	        } catch (Exception e) {
	        	map.put("reason","网络异常");
	        	map.put("error_code", -1);
	        }
	        return map;
	    }
	 
	    public static void main(String[] args) {
	    }
}
