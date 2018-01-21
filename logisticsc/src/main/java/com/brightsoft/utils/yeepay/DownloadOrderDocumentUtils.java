package com.brightsoft.utils.yeepay;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.brightsoft.utils.yeepay.ZGTUtils;

public class DownloadOrderDocumentUtils {
	
	public static final String CHARSET = "UTF-8";
	
	//超时时间设置为1小时
	public static final int TIMEOUT = 60*60*1000;
	
	public static final String DOWNLOADORDERDOCUMENTAPI_NAME	= "DownloadOrderDocumentApi";

	//4.21 对账文件下载接口请求的hmac签名顺序
	public static final String[] REQUEST_HMAC_ORDER 	= {"operator_no", "checkDate", "orderType"};
	
	private static String operator_no			= "";
	private static String keyForHmac			= "";
	private static String keyForAes				= "";
	private static String requestUrl			= "";
	
	static {
		//请求地址
		requestUrl		= ZGTUtils.getMerchantInfo().getValue(DOWNLOADORDERDOCUMENTAPI_NAME);
		//从配置文件中获取商户编号
		operator_no 	= ZGTUtils.getCustomernumber();
		//从配置文件中获取商户密钥
		keyForHmac 		= ZGTUtils.getKeyForHmac();
		//aes密钥，为商户密钥的前16位
		keyForAes		= ZGTUtils.getKeyForAes();
	}

	//生成data
	public static String buildCertify_token(Map<String, String> params) {

		//对于null的value，置成""
		for(String key : params.keySet()) {
			params.put(key, formatStr(params.get(key)));
		}
		
		//添加商户编号
		params.put("operator_no", operator_no);

		//生成hmac
		StringBuffer buffer	= new StringBuffer();
		for(int i = 0; i < REQUEST_HMAC_ORDER.length; i++) {
			buffer.append(params.get(REQUEST_HMAC_ORDER[i]));
		}
		String hmac			= ZGTUtils.buildHmac(buffer.toString(), keyForHmac);

		//放入刚刚生成的hmac
		params.put("hmac", hmac);

		return ZGTUtils.buildData(params);
	}


	//对账接口定制post请求方法
	public static InputStream httpPost(String authorize_no, String certify_token) {
		
		InputStream result		= null;

		//参数组装
		List<NameValuePair> pairs	= new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("authorize_no", authorize_no));
		pairs.add(new BasicNameValuePair("certify_token", certify_token));

		CloseableHttpClient httpClient 	= HttpClients.createDefault();
		HttpPost httpPost 				= null;
		CloseableHttpResponse response	= null;
		
		//超时设置
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(TIMEOUT).setSocketTimeout(TIMEOUT).build();

		try {
			httpPost = new HttpPost(requestUrl);
			httpPost.setEntity(new UrlEncodedFormEntity(pairs));
			httpPost.setConfig(requestConfig);
			response = httpClient.execute(httpPost);
			
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			
			HttpEntity entity 	= response.getEntity();
			result				= entity.getContent(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源  
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static String getPathOfDownloadOrderDocument(Map<String, String> params, String path) {
		
		String time					= String.valueOf(System.currentTimeMillis());
		String fileName				= "ZGTOrderDocument" + "_" + time + "." + params.get("fileType");
		String filePath				= path + File.separator + fileName;

		String authorize_no			= operator_no;
		String certify_token		= buildCertify_token(params);
		InputStream	responseStream	= httpPost(authorize_no, certify_token);

		try {
			File file	= new File(filePath);
			file.getParentFile().mkdirs();
			file.createNewFile();
	
			FileOutputStream orderFile 	= new FileOutputStream(filePath);
			BufferedInputStream bis 	= new BufferedInputStream(responseStream);
			
			byte[] by = new byte[1024];
			while (true) {
				int i = bis.read(by);
				if (i == -1) {
					break;
				}
				orderFile.write(by, 0, i);
			}
			orderFile.close();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return filePath;
	}
	
	//获取请求地址
	public static String getRequestUrl(String apiName) {
		return requestUrl;
	}
	
	//getter
	public static String getOperator_no() {
		return operator_no;
	}
	
	//getter
	public static String getKeyForHmac() {
		return keyForHmac;
	}
	
	//getter
	public static String getKeyForAes() {
		return keyForAes;
	}

	//字符串格式化
	public static String formatStr(String text) {
		return text == null ? "" : text;
	}
}
