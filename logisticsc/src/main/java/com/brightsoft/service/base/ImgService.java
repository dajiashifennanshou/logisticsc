package com.brightsoft.service.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ImgService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private final static String OPERATOR_SYS_WIN = "Win";//window平台
	private final static String OPERATOR_SYS_LIN = "Lin";//linux平台
	
	public void readImg(String resource,OutputStream os){
		
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(genPath(resource));
			IOUtils.copy(fis, os);
		} catch (FileNotFoundException e) {
			logger.error("图片下载：文件路径找不到", e);
		}catch (IOException e) {
			logger.error("图片下载：文件拷贝异常", e);
		}finally {
			try {
				if(fis!=null){
					fis.close();
				}
			} catch (IOException e) {
				logger.error("图片下载：流关闭异常", e);
			}
		}
		
	}
	private String genPath(String resource){
		String sys = System.getProperty("os.name");
		String des = sys.substring(0, 2);
		if(des.equals(OPERATOR_SYS_WIN)){
			return resource.replaceAll("/","\\\\");
		}
		return resource;
	}
}
