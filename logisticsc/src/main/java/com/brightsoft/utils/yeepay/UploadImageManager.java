package com.brightsoft.utils.yeepay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class UploadImageManager {
	
	public static List<Future<Map<String, String>>> pushMessage(List<Map<String, Object>> list) throws InterruptedException, ExecutionException
	{
		ExecutorService exec = Executors.newCachedThreadPool();
		
		List<Future<Map<String, String>>> resultList =new ArrayList<Future<Map<String, String>>>();
		for(int i=0;i<list.size();i++){
			Map<String, Object> params = list.get(i);
			UploadImageCallable uploadImageCallable = new UploadImageCallable(params);
			Future<Map<String, String>> future = exec.submit(uploadImageCallable);
			resultList.add(future);
			 
		}
		exec.shutdown();
		return resultList;
		
	}
	
}
