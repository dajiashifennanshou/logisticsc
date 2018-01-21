package com.yc.Controller.yeepay;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

import com.alibaba.fastjson.JSON;
import com.yc.Tool.yeepay.AESUtil;
import com.yc.Tool.yeepay.Digest;

public class UpLoadApiServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
    public UpLoadApiServlet() {
        super();
    }
    
	public String formatStr(String text) {
		return text == null ? "" : text.trim();
	}
	
	//get请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	//post请求
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//UTF-8编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out	= response.getWriter();

		//获取请求参数
		String customernumber			= formatStr(request.getParameter("customernumber"));	
		String ledgerno				= formatStr(request.getParameter("ledgerno"));	
		String filetype				= formatStr(request.getParameter("filetype"));	
		String path			= formatStr(request.getParameter("path"));	
		String key = "574584H38Msx80980026QKzbX588Zv0xh95ph8ZG67dj7x69k5091xvm0013";
		
		StringBuffer signature = new StringBuffer();
		signature.append(customernumber).append(ledgerno).append(filetype);
		String hmac = Digest.hmacSign(signature.toString(), key);
		System.out.println(hmac);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("customernumber", customernumber);
		dataMap.put("ledgerno", ledgerno);
		dataMap.put("filetype", filetype);
		dataMap.put("hmac", hmac); // hmac 按照 properties 中声明的顺序进行签名
		String dataJsonString = com.alibaba.fastjson.JSON.toJSONString(dataMap); // map 中数据转为 json 格式
		String content = AESUtil.encrypt(dataJsonString, key);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customernumber", customernumber);
		params.put("data", content);// 加密 json 格式数据作为 value 赋值给 data 参数
		File file = new File(path);
		params.put("file", file);
		System.out.println(params);
		UpLoadApiServlet test = new UpLoadApiServlet();
		try {
			String data = test.uploadFile(params, "http://o2o.yeepay.com/zgt-api/api/uploadLedgerQualifications");
			String result = AESUtil.decrypt(JSON.parseObject(data).get("data").toString(), key);
			System.out.println(result);
		} catch (Throwable e) {
			e.printStackTrace();

		}

	}
		
    
	public static String mapToQueryString(Map<String, Object> parameters, String charSet) {
		String queryString = "";
		if (parameters != null && !parameters.isEmpty()) {
			for (String key : parameters.keySet()) {
				try {
					Object value = parameters.get(key);
					if (value instanceof String) {
						queryString += key + "=" + URLEncoder.encode(value == null ? "" : value.toString(), charSet)
								+ "&";
					}
				} catch (UnsupportedEncodingException e) {
					throw new IllegalArgumentException("invalid charset : " + charSet);
				}
			}
			if (queryString.length() > 0) {
				queryString = queryString.substring(0, queryString.length() - 1);
			}
		}
		return queryString;
	}

	
	public String uploadFile(Map<String, Object> params, String baseUrl) {
		if (!params.containsKey("file")) {
			throw new IllegalArgumentException("请上传图片");
		}
		File file = (File) params.get("file");
		if (!file.exists()) {
			throw new IllegalArgumentException("上传图片不存在");
		}
		if (baseUrl == null || baseUrl.trim().length() == 0) {
			throw new IllegalArgumentException("invalid url : " + baseUrl);
		}
		String queryString = mapToQueryString(params, "utf-8");
		int index = baseUrl.indexOf("?");
		if (index > 0) {
			baseUrl += "&" + queryString;
		} else {
			baseUrl += "?" + queryString;
		}
		PostMethod postMethod = new PostMethod(baseUrl);
		try {
			FilePart fp = new FilePart("file", file);
			Part[] parts = { fp };
			MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());
			postMethod.setRequestEntity(mre);
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 设置连接时间
			int status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				return postMethod.getResponseBodyAsString();
			} else {
				throw new RuntimeException("上传请求异常");
			}
		} catch (Exception e) {
			throw new RuntimeException("上传请求异常");
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
	}
	
	public static void main(String[] args) {
		//String[] person = { "ID_CARD_FRONT", "ID_CARD_BACK", "BANK_CARD_FRONT", "PERSON_PHOTO" };
		String[] person = { "ID_CARD_FRONT"};
		for (String p : person) {
			String customernumber = "10012438801";
			String ledgerno = "10012909010";
			String filetype = p;
			String key = "574584H38Msx80980026QKzbX588Zv0xh95ph8ZG67dj7x69k5091xvm0013";
			
//			String customernumber = "10040020653";
//	        String ledgerno = "10040022229";
//	        String filetype = p;
//	        String key = "Kgrj87D6h0Fh6jnm4a66hI7qU2n065488xzv0nKm0J99AE9X95n72Y3jt497";
	           
			StringBuffer signature = new StringBuffer();
			signature.append(customernumber).append(ledgerno).append(filetype);
			String hmac = Digest.hmacSign(signature.toString(), key);
			System.out.println(hmac);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("customernumber", customernumber);
			dataMap.put("ledgerno", ledgerno);
			dataMap.put("filetype", filetype);
			dataMap.put("hmac", hmac); // hmac 按照 properties 中声明的顺序进行签名
			String dataJsonString = com.alibaba.fastjson.JSON.toJSONString(dataMap); // map 中数据转为 json 格式
			String content = AESUtil.encrypt(dataJsonString, key);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("customernumber", customernumber);
			params.put("data", content);// 加密 json 格式数据作为 value 赋值给 data 参数
			File file = new File("/Users/wangjie/Desktop/demos/zgt-java/WebContent/path/feifei.jpg");
			params.put("file", file);
			System.out.println(params);
			UpLoadApiServlet test = new UpLoadApiServlet();
			try {
				String data = test.uploadFile(params, "http://o2o.yeepay.com/zgt-api/api/uploadLedgerQualifications");
				String result = AESUtil.decrypt(JSON.parseObject(data).get("data").toString(), key);
				System.out.println(result);
			} catch (Throwable e) {
				e.printStackTrace();

			}

		}

	}
	
}
