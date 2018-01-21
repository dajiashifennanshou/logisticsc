package com.wrt.xinsilu.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池工具
 * @author wangsong
 * @date 2016-3-27
 */
public class Executor {
	/** 线程池对象 */
	private static ExecutorService defaultExecutor;
	/** 图片上传线池 */
	private static ExecutorService picUpload;

	static {
		defaultExecutor = Executors.newScheduledThreadPool(Runtime.getRuntime()
				.availableProcessors() * 3 + 2);
		picUpload = Executors.newSingleThreadExecutor();
	}

	/**
	 * 获取默认线程池
	 * <br>该线程池一般处理不占内存的线程。如普通网络请求，一般数据流读取，文件保存等等
	 * <br>该线程池最大线程CPU*3+2,是newScheduledThreadPool(*);
	 * @return  ExecutorService
	 */
	public static ExecutorService getDefaultExecutor() {
		if(defaultExecutor.isShutdown()){
			defaultExecutor = Executors.newScheduledThreadPool(Runtime.getRuntime()
					.availableProcessors() * 3 + 2);
		}
		return defaultExecutor;
	}

	/**
	 * 主要用于处理叫好内存的线程， 如图片上传
	 * <br>该线程池只能同时执行1个线程,是newSingleThreadExecutor();
	 * @return ExecutorService
	 */
	public static ExecutorService getSingleExecutor() {
		if(picUpload.isShutdown()){
			picUpload = Executors.newSingleThreadExecutor();
		}
		return picUpload;
	}

	/**
	 * 关闭线程池
	 * <br>只有在应用结束时才调用，其他情况别轻易调用
	 */
	public static void shutdown(){
		defaultExecutor.shutdown();
		picUpload.shutdown();
	}
}
