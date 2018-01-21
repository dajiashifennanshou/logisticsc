package com.brightsoft.utils.yc;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.brightsoft.Constant.Constant;
import com.brightsoft.entity.ResultEntity;




public class FengUtil {
	/**
	 * 上传文件
	 * @param file 要上传的文件
	 * @param path 上传文件的保存地址。从盘符到文件夹地址。
	 * @return
	 * @throws IOException 
	 */
	public static Map<String,Object> uploadFile(MultipartFile file,String folder) throws IOException {
		
		Map<String,Object> res=new HashMap<String, Object>();
		//http://www.cnblogs.com/rollenholt/p/3693087.html 上传文件参考网址
		//上传路径
		//String path="/sdffd";
		//获取文件信息
		 if (!file.isEmpty()) {
			 File nf=new File(folder);
			 if(!nf.exists()){
				 nf.mkdirs();
			 }
			 String name=file.getOriginalFilename();
			 String laterName=UUID.randomUUID()+name.substring(name.lastIndexOf("."));
			 File folds=new File(folder);
			 if(!folds.exists()){
				 folds.mkdir();
			 }
			 String path=folder+laterName;
			 
            try {
            	//位数
                byte[] bytes = file.getBytes();
                //创建文件流
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(path)));
                stream.write(bytes);
                stream.close();
               res.put("msg", "上传成功 " + path + " into " + path + "!");
               res.put("path", laterName);
               res.put("code", 0);
            } catch (Exception e) {
                res.put("msg", "上传失败 " + path + " => " + e.getMessage());
                res.put("code", 1);
                throw new RuntimeException("此文件是一个空对象..");
            }
        } else {
            //return "You failed to upload " + path + " because the file was empty.";
        	res.put("code", 1);
        	res.put("msg", "此文件是一个空对象..");
        	throw new RuntimeException("此文件是一个空对象..");
        }
		//调用方法将文件上传
		
		//返回页面
		return res;
	}
	
	/**
	 * 向session里赋值
	 * Author:FENG
	 * 2016年7月5日
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void SETDATABYSESSION(HttpServletRequest request,String key,Object value){
		request.getSession().setAttribute(key, value);
	}
	/**
	 * 获取session里的值
	 * Author:FENG
	 * 2016年7月5日
	 * @param request
	 * @param key
	 * @return
	 */
	public static Object GETDATABYSESSION(HttpServletRequest request,String key){
		Object obj=request.getSession().getAttribute(key);
		return obj;
	}
	/**
	 * 获取session里的值
	 * Author:FENG
	 * 2016年7月5日
	 * @param request
	 * @param key
	 * @return
	 */
	public static void EXITLOGIN(HttpServletRequest request,String key){
		request.getSession().setAttribute(key, null);
	}
	/**
	 * fanh错误信息
	 * Author:FENG
	 * 2016年7月23日
	 * @param msg
	 * @return
	 */
	public static ResultEntity ReturnError(String msg){
		return new ResultEntity(msg,1);
	}
	
	/**
	 * 返回输出实体--针对pager对象
	 * author Feng--java
	 * @param msg
	 * @param code
	 * @param data
	 * @return
	 */
	public static void OUTPRINTObject(String msg,Integer code,Pager<?> pager,HttpServletResponse response){
		OutJsonEntity res=new OutJsonEntity();
		res.setCode(code);
		res.setMsg(msg);
		res.setRows(pager.getObjectList());
		res.setRecords(pager.getRecordCount());
		//计算总页数
		int total = pager.getRecordCount() % pager.getPageSize() == 0 ? pager.getRecordCount()/pager.getPageSize() : pager.getRecordCount()/pager.getPageSize() + 1 ;
		res.setTotal(total);
		PrintUtil.outprint(GsonUtil.toJson(res, OutJsonEntity.class), response);
	}
	/**
	 * 返回错误码及错误提示
	 * author Feng--java
	 * @param msg
	 * @param code
	 * @param data
	 * @return
	 */
	public static void ERRORDATA(ResultEntity re,HttpServletResponse response){
		PrintUtil.outprint(GsonUtil.toJson(re, ResultEntity.class), response);
	}
	/*******************************tms接口******************************************/
	/**
	 * 执行成功,返回分页信息TMS
	 * author luojing
	 * @param code 代码
	 * @param obj 返回数据
	 * @return
	 */
	public static void OUTAPPPAGETMS(Integer code,Pager<?> pager,HttpServletResponse response){
		OutJsonEntity res=new OutJsonEntity();
		res.setCode(code);
		res.setRows(pager.getObjectList());
		res.setResults(pager.getRecordCount());
		res.setPage(pager.getPageIndex());
		//计算总页数
		int total = pager.getRecordCount() % pager.getPageSize() == 0 ? pager.getRecordCount()/pager.getPageSize() : pager.getRecordCount()/pager.getPageSize() + 1 ;
		res.setTotal(total);
		PrintUtil.outprint(GsonUtil.toJson(res, OutJsonEntity.class), response);
	}
	/**
	 * 执行成功,返回分页信息SYS
	 * author luojing
	 * @param code 代码
	 * @param obj 返回数据
	 * @return
	 */
	public static void OUTSYSPAGETMS(Integer code,Pager<?> pager,HttpServletResponse response){
		OutJsonEntity res=new OutJsonEntity();
		res.setCode(code);
		res.setRows(pager.getObjectList());
		res.setResults(pager.getRecordCount());
		//res.setPage(pager.getPageIndex());
		//计算总页数
		//int total = pager.getRecordCount() % pager.getPageSize() == 0 ? pager.getRecordCount()/pager.getPageSize() : pager.getRecordCount()/pager.getPageSize() + 1 ;
		//res.setTotal(total);
		PrintUtil.outprint(GsonUtil.toJson(res, OutJsonEntity.class), response);
	}

	/*******************************app接口******************************************/
	
	/**
	 * 执行成功,返回分页信息
	 * author luojing
	 * @param code 代码
	 * @param obj 返回数据
	 * @return
	 */
	public static void OUTAPPPageObject(Integer code,Pager<?> pager,HttpServletResponse response){
		OutJsonEntity res=new OutJsonEntity();
		res.setCode(code);
		res.setRows(pager.getObjectList());
		res.setRecords(pager.getRecordCount());
		//计算总页数
		int total = pager.getRecordCount() % pager.getPageSize() == 0 ? pager.getRecordCount()/pager.getPageSize() : pager.getRecordCount()/pager.getPageSize() + 1 ;
		res.setTotal(total);
		PrintUtil.outprint(GsonUtil.toJson(res, OutJsonEntity.class), response);
	}
	/**
	 * 获取string，如果第一个字符串是无效的，则返回第二个
	 * Author:FENG
	 * 2016年7月26日
	 * @param fis
	 * @param tow
	 * @return
	 */
	public static String GETORSTRING(String fis,String tow){
		if(StrUtil.VString(fis)){
			return fis;
		}
		return tow;
	}
	/**
	 * 执行成功,返回输出信息
	 * author luojing
	 * @param code 代码
	 * @param obj 返回数据
	 * @return
	 */
	public static void OUTAPPSUCCESS(Integer code,Object obj,HttpServletResponse response){
		OutJsonEntity res=new OutJsonEntity();
		res.setCode(code);
		res.setData(obj);
		PrintUtil.outprint(GsonUtil.toJson(res, OutJsonEntity.class), response);
	}
	
	/**
	 * 执行失败,返回错误信息
	 * author luojing
	 * @param code 代码
	 * @return
	 */
	public static void OUTAPPERROR(Integer code,HttpServletResponse response){
		OutJsonEntity res=new OutJsonEntity();
		res.setCode(code);
		PrintUtil.outprint(GsonUtil.toJson(res, OutJsonEntity.class), response);
	}
	
	/*************************************************************************/
	
	
	/**
	 * 返回输出实体,针对任意数据
	 * author Feng--java
	 * @param msg
	 * @param code
	 * @param data
	 * @return
	 */
	public static void OUTPRINTRESULT(String msg,Integer code,Object result,HttpServletResponse response){
		OutJsonEntity res=new OutJsonEntity();
		res.setCode(code);
		res.setMsg(msg);
		res.setData(result);
		PrintUtil.outprint(GsonUtil.toJson(res, OutJsonEntity.class), response);
	}
	/**
	 * 返回输出实体,针对添加窗口
	 * author Feng--java
	 * @param msg
	 * @param code
	 * @param data
	 * @return
	 */
	public static void OUTADDSUCCESS(String msg,Integer code,String cw,String lw,HttpServletResponse response){
		OutAddWindow res=new OutAddWindow();
		res.setCode(code);
		res.setMsg(msg);
		res.setCw(cw);
		res.setLw(lw);
		PrintUtil.outprint(GsonUtil.toJson(res, OutAddWindow.class), response);
	}
	/**
	 * 返回输出实体,针对添加窗口
	 * author Feng--java
	 * @param msg
	 * @param code
	 * @param data
	 * @return
	 */
	public static void REFRENSHLIST(Integer code,HttpServletResponse response){
		OutAddWindow res=new OutAddWindow();
		res.setCode(code);
		PrintUtil.outprint(GsonUtil.toJson(res, OutAddWindow.class), response);
	}
	/**
	 * 返回输出实体,针对添加窗口
	 * author Feng--java
	 * @param msg
	 * @param code
	 * @param data
	 * @return
	 */
	public static void OUTADDERROR(String msg,Integer code,HttpServletResponse response){
		OutAddWindow res=new OutAddWindow();
		res.setCode(code);
		res.setMsg(msg);
		PrintUtil.outprint(GsonUtil.toJson(res, OutAddWindow.class), response);
	}
	/**
	 * 返回输出实体,同事回滚事务
	 * author Feng--java
	 * @param msg
	 * @param code
	 * @param data
	 * @return
	 */
	public static void OUTROLLBACK(String msg,Integer code,HttpServletResponse response){
		OutAddWindow res=new OutAddWindow();
		res.setCode(code);
		res.setMsg(msg);
		PrintUtil.outprint(GsonUtil.toJson(res, OutAddWindow.class), response);
		//事务回滚
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}
	/**
	 * 事务回滚
	 * 
	 * Author:FENG
	 * 2016年7月25日
	 */
	public static void TRANEOLLBACK(){
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}
	public static String GETSALFKEY(HttpServletRequest request) {
		return request.getParameter("");
	}
	/**
	 * 获取绝对地址
	 * author Feng--java
	 * @param request
	 * @return
	 */
	public static String GETFILEPATH(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("/");
	}
	/**
	 * 获取网络地址
	 * author Feng--java
	 * @param request
	 * @return
	 */
	public static String GETHTTPPATH(HttpServletRequest request){
		return request.getRequestURL().toString();
	}

	/**
	 * 获取云仓编号
	 * Author:FENG
	 * 2016年7月13日
	 * @return
	 */
	public static String GETBRANCHNUMBER(HttpServletRequest request){
		String res="";
		res=request.getSession().getAttribute(Constant.BRANCHNOKEY).toString();
		return res;
	}
	/**
	 * 退出登录
	 * Author:FENG
	 * 2016年7月13日
	 * @param request
	 */
	public static void EXITSYSTEM(HttpServletRequest request){
		//清空session
		request.getSession().invalidate();
	}
	/**
	 * 获取订单编号
	 * author Feng--java
	 * @return
	 */
	public static String GETORDERNUMBER(){
		//return "ORDER" + System.currentTimeMillis();
		return System.currentTimeMillis()+"";
	}
	/**
	 * 随机生成颜色
	 * author Feng--java
	 * time 2015-9-6 下午01:45:25
	 * @param s
	 * @param e
	 * @return
	 */
	public static Color getRandColor(int s, int e) {
		  Random random = new Random();
		  if (s > 255) s = 255;
		  if (e > 255) e = 255;
		  int r = s + random.nextInt(e - s);
		  int g = s + random.nextInt(e - s);
		  int b = s + random.nextInt(e - s);
		  return new Color(r, g, b);
	}
	/**
	 * 生成验证码
	 * author Feng--java
	 * time 2015-9-6 下午01:45:37
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	 public static String AuthCode(){
		 Random random=new Random();
		String code="";
		for(Integer i=0;i<6;i++){
			 code+=random.nextInt(10);
		}
		return code;
	 }

	 
	
	 /**
	  * 生成配送单编号
	  * Author:tangjie
	  * 2016年6月12日 下午4:45:37
	  */
	 public static String PS_Number(){
		 String str = "PS-";
		 String str2=DateUtil.getDateTime();
		 int str3=(int)(Math.random()*99)+10; 
		 return str+str2+str3;
	 }
	 /**
	  * 生成载单编号
	  * Author:tangjie
	  * 2016年6月12日 下午4:45:37
	  */
	 public static String PZ_Number(){
		 String str = "PZ-";
		 String str2=DateUtil.getMiller()+"";
		 int i=(int)(Math.random()*99)+10; 
		 return str+str2+i;
	 }
	 /**
	  * 生成载单编号
	  * Author:tangjie
	  * 2016年6月12日 下午4:45:37
	  */
	 public static String WB_Number(){
		 String str = "WB-";
		 Date date = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		 return str+sdf.format(date);
	 }
	 /**
	  * 传入实体，返回map
	  * Author:luojing
	  * 2016年6月15日 下午3:00:32
	  */
	  public static Map<String,Object> getMap(Object obj){
		  Map<String,Object> map=new HashMap<String,Object>();
		  Class<? extends Object> c=obj.getClass();
		  try {
			  for(Field f:c.getDeclaredFields()){
				  f.setAccessible(true);
				  Object value=f.get(obj);
				  if(StrUtil.VObject(value)){
					  map.put(f.getName(), String.valueOf(value));
				  }
			  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	  }
	  /**
	   * 抛出一个异常
	   * Author:FENG
	   * 2016年6月17日
	   * @param msg
	 * @throws FengRuntimeException 
	   */
	  public static void RuntimeExceptionFeng(String msg){
		  throw new RuntimeException(msg);
	  }
	  /**
	   * 抛出一个异常-feng
	   * Author:FENG
	   * 2016年6月17日
	   * @param msg
	   * @throws FengRuntimeException 
	   */
	  public static void FengRuntimeException(String msg){
		  throw new FengRuntimeException(msg);
	  }
	  /**
	   * 抛出一个异常
	   * Author:FENG
	   * 2016年6月17日
	   * @param msg
	   * @throws FengRuntimeException 
	   */
	  public static void RuntimeExceptionFeng(){
		  throw new RuntimeException("手动异常、ByFeng");
	  }
	  
	  /**
	   * 生成验证码
	   * Author:luojing
	   * 2016年6月28日 下午3:04:18
	   */
	 public static Integer getCode(){
		 int i=(int)(Math.random()*999999); 
		 return i;
	 }
	 
}
