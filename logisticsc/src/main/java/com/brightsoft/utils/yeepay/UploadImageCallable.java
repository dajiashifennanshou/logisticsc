package com.brightsoft.utils.yeepay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import javax.servlet.RequestDispatcher;

import com.alibaba.fastjson.JSON;

public class UploadImageCallable implements Callable<Map<String, String>> {
	
	private int n;
	
	private int m;
	
	//private final CountDownLatch countDownLatch;
	
	Map<String, Object> params;

	
	public UploadImageCallable(Map<String, Object> params) {
		super();
		this.params = params;
		
	}

//	public UploadImageCallable(CountDownLatch countDownLatch,int n, int m)
//	{
//		this.n = n;
//		this.m = m;
//		this.countDownLatch = countDownLatch;
//	}

	public Map<String, String> call() throws Exception {
		// TODO Auto-generated method stub
		ZGTUtils zgtUtils = new ZGTUtils();
		String uploadurl = ZGTUtils.getRequestUrl(ZGTUtils.UPLOADAPI_NAME);
		Map<String, String> responseDataMap = null;
		try {
			String data = zgtUtils.uploadFile(params, uploadurl);
			responseDataMap	= ZGTUtils.decryptData(JSON.parseObject(data).get("data").toString());
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return responseDataMap;
	}

}
