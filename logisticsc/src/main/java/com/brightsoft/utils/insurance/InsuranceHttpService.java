package com.brightsoft.utils.insurance;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.brightsoft.controller.vo.InsuranceBackData;
import com.brightsoft.model.PlatformInsurance;
import com.brightsoft.utils.dateConvertor.XdateConvert;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class InsuranceHttpService {
	
	private Logger logger = Logger.getLogger(InsuranceHttpService.class);
	private String configUri;//公司配置信息uri
	private String subUri;//投保请求uri
	private String userName;//用户名
	private String userPwd;//密码
	private String userKey;//
	private String backDataUrl;//回掉接口
	
	/*static{
		ResourceBundle bundle = PropertyResourceBundle.getBundle("insurance");
		userName = bundle.getString("userName");
		userPwd = bundle.getString("userPwd");
		userKey = bundle.getString("userKey");
		configUri = bundle.getString("configUri");
		subUri = bundle.getString("subUri");
		backDataUrl = bundle.getString("backDataUrl");
	}*/

	/**
	 * 获取公司配置信息
	 */
	public ConfigResult getComConfig(){
		ConfigInfo configInfo = new ConfigInfo();
		configInfo.setUserName(userName);
		configInfo.setUserPwd(userPwd);
		configInfo.setUserKey(userKey);
		XStream stream = new XStream(new DomDriver());
		stream.alias("document", ConfigResult.class);
		stream.alias("insCompany", InsCompany.class);
		stream.alias("insType", InsType.class);
		stream.alias("insTsType", InsTsType.class);
		ConfigResult configResult = (ConfigResult)postHttp(stream,configInfo,configUri);
		return  configResult;
	}
	/**
	 * 保单提交
	 * @param platformInsurance
	 * @return
	 */
	public boolean postInsInfo(PlatformInsurance platformInsurance){
		boolean flag = false;
		XStream stream = new XStream(new DomDriver());
		stream.registerConverter(new XdateConvert());  
		stream.alias("document", PlatformInsurance.class);
		stream.alias("document", OrderResult.class);
		platformInsurance.setUserPwd(userPwd);
		platformInsurance.setUserName(userName);
		platformInsurance.setUserKey(userKey);
		platformInsurance.setInsArea(0);
//		DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
//		String jine=decimalFormat.format(platformInsurance.getInsJinge());//format 返回的是字符串
//		platformInsurance.setInsJine( jine);
		platformInsurance.setBackDataUrl(backDataUrl);
		
		OrderResult orderResult = (OrderResult)postHttp(stream,platformInsurance, subUri);
		System.out.println(orderResult.getServerCode());
		//判断保单提交是否成功
		if(orderResult.getServerCode()==0){
			flag = true;
		}else if(orderResult.getServerCode()==10010){
			logger.error("用户名或密码错误");
		}else if(orderResult.getServerCode() == 10020){
			logger.error("key错误");
		}else if(orderResult.getServerCode() == 10030){
			logger.error("余额不足");
		}else if(orderResult.getServerCode() == 10040){
			logger.error("接口关闭");
		}else{
			logger.error("字段格式错误");
		}
		return flag;
	}
	/**
	 * 发送http请求
	 * @param obj
	 * @param uri
	 * @return
	 */
	public Object postHttp(XStream stream,Object obj,String uri){
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(uri);
		String target = postXml(obj,stream);
		Object ret = null;
		
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(target.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error("不支持的编码格式",e);
		}
		//提交请求
		post.setRequestBody(bais);
		post.getParams().setContentCharset("utf-8");
		try {
			client.executeMethod(post);
		} catch (Exception e) {
			logger.error("链接异常：", e);
		}
		try {
			InputStream in = post.getResponseBodyAsStream();
			String result = IOUtils.toString(in);
			ret = parseXmlToObjByStream(result,stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * obj to xml
	 * @param obj
	 * @param stream
	 * @return
	 */
	private String postXml(Object obj,XStream stream){
		String str = stream.toXML(obj);
		System.err.println(str);
		return str;
	}
	/**
	 * xml to obj
	 * @param xmlStr
	 * @param stream
	 * @return
	 */
	private Object parseXmlToObjByStream(String str,XStream stream){
		/*if(!str.substring(str.length()-9).equals("document>")){
			return stream.fromXML(str+"</document>");
		}*/
		return stream.fromXML(str);
	}
	
	public String getConfigUri() {
		return configUri;
	}
	public void setConfigUri(String configUri) {
		this.configUri = configUri;
	}
	public String getSubUri() {
		return subUri;
	}
	public void setSubUri(String subUri) {
		this.subUri = subUri;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getBackDataUrl() {
		return backDataUrl;
	}
	public void setBackDataUrl(String backDataUrl) {
		this.backDataUrl = backDataUrl;
	}
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
//		ConfigResult configResult = getComConfig();
//		postInsInfo(new PlatformInsurance());
//		System.out.println(configResult);
		/*String st = "123456";
		System.out.println(st.substring(st.length()-1));*/
		/*System.out.println(Math.round((t*100))/100);
		 * 
		math.r
		System.out.println(p);*/
		
		System.out.println();
		String str = "<?xml version='1.0' encoding='utf-8' ?>"
                                        +"<document><backInsStatus>0</backInsStatus><backInsValue></backInsValue><backInsTime>14593296652</backInsTime><userKey></userKey><insOrderNum>XLS160328035044621</insOrderNum><insSysBaodan>YNF16032815505015</insSysBaodan><insLastBaodan>hhhhhhhhh243124234</insLastBaodan ><insFileUrl>http://www.518560.com/insurance_test/upfile/YNF15073115371229.pdf</insFileUrl></document>";
		XStream stream = new XStream();
		stream.alias("document", InsuranceBackData.class);
		InsuranceBackData o = (InsuranceBackData)stream.fromXML(str);
	}
}
