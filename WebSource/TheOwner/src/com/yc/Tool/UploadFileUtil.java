package com.yc.Tool;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * 图片上传
 */
public class UploadFileUtil {

	/** 原图路径 */
	public static final String ORIGINALURL = "originalImageUrl";
	/**
	 * 获取文件对象
	 */
	public static ResourceBundle bundle = PropertyResourceBundle.getBundle("companyDocumentsConfig");

	/**
	 * @Title: createFileName
	 * @Description: 创建文件目录路径
	 * @param imageName
	 *            图片名
	 * @param dirName
	 *            文件目录
	 * @return String 返回文件目录路径
	 */
	public static String getOriginalFileDirName(String imageName, String dirName) {
		String newImageName = UUID.randomUUID().toString() + "."
				+ imageName.substring(imageName.lastIndexOf(".") + 1);
		String fileName = (dirName + newImageName).replaceAll("\\\\", "/");
		return fileName;
	}

	/**
	 * @Title: cutDir
	 * @Description:截取存储图片的本地路径
	 * @param dirName
	 *            存储图片的本地全路径
	 * @return String 返回存储图片的路径。 如：upload/topic/pic/original/1/5/xxxx.jpg
	 */
	public static String cutDir(String dirName) {
		return dirName.substring(dirName.indexOf(bundle.getString("uploadImageFile")));
	}
	/**
	 * @Title: uploadImage
	 * @Description: 上传图片
	 * @param dirName
	 *            存储图片路径
	 * @param file
	 *            临时文件 ; 如:xxx/xxxx.jpg
	 * @return Map<String,String> 返回存储图片本地相对路径。 如:
	 *         upload/topic/pic/original/1/5/xxxx.jpg
	 */
	public static Map<String, String> uploadImage(String dirName,File file) {
		// 获取当前图片路径
		String originalFileName = (dirName + file.getName());
		// 获取当前图片后缀名格式
		String suffixName = getFileSuffixName(originalFileName);
		Map<String, String> map = new HashMap<String, String>();
		if (suffixName.matches(bundle.getString("reg"))) {
			map.put(UploadFileUtil.ORIGINALURL, cutDir(originalFileName));
		}
		return map;
	}
	/**
	 * 获取图片类型
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffixName(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	}
	/**
	 * 获取二级目录存储路径
	 * @Title: getSecondPathByHashCode   
	 * @Description: 二级目录存储路径不存在时则创建
     * @param basePath 基础路径
	 * @param fileName 文件名
	 * @return String 文件夹绝对路径
	 */
    public static String getSecondPathByHashCode(String basePath, String fileName) {
        return getSecondPathByHashCode(basePath, fileName, false);
    }
    /**
	 * 获取二级目录存储路径
	 * @Title: getSecondPathByHashCode   
	 * @Description: 二级目录存储路径不存在时则创建
	 * @param basePath 基础路径
	 * @param fileName 文件名
	 * @param returnRelative 是否返回相对路径, 相对于参数<i>basePath</i>
	 * @return String 已存在的二级目录绝对或相对路径
	 */
	public static String getSecondPathByHashCode(String basePath, String fileName, boolean returnRelative) {
	    if (fileName.contains(".")) {
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        }
	    fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
	    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        int code = fileName.hashCode();
        int dir1 = code & 0xf;
        int dir2 = code >> 8 & 0xf;
        if (!basePath.endsWith("/") && !basePath.endsWith("\\")) {
            basePath += "/";
        }
        String targetDir = dir1 + "/" + dir2 + "/";
        String path = basePath + targetDir;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return returnRelative ? targetDir : path;
	}
	
	/**
	 * 图片路径
	 * @param dirName
	 * @param file
	 * @return
	 */
	public static String ImgUrl(String dirName, File file){
		Map<String, String> map = UploadFileUtil.uploadImage(dirName, file);
		String originalUrl = null;
		if (!map.isEmpty()) {
			//获取图片路径
			originalUrl = map.get(UploadFileUtil.ORIGINALURL);
		}
		return originalUrl;
	}
	
	private final static String OPERATOR_SYS_WIN = "Win";//window平台
	private final static String OPERATOR_SYS_LIN = "Lin";//linux平台
	
	public static void readImg(String resource,OutputStream os){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(genPath(resource));
			IOUtils.copy(fis, os);
		} catch (FileNotFoundException e) {
		}catch (IOException e) {
		}finally {
			try {
				if(fis!=null){
					fis.close();
				}
			} catch (IOException e) {
			}
		}
		
	}
	private static String genPath(String resource){
		String sys = System.getProperty("os.name");
		String des = sys.substring(0, 2);
		if(des.equals(OPERATOR_SYS_WIN)){
			return resource.replaceAll("/","\\\\");
		}
		return resource;
	}
}
