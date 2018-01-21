package com.yc.Tool;

import java.awt.Color;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javassist.Modifier;

public class FengUtil {
	

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
		res.setData(pager.getObjectList());
		PrintUtil.outprint(GsonUtil.toJson(res, OutJsonEntity.class), response);
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
	 * 执行成功,返回输出信息
	 * author luojing
	 * @param code 代码
	 * @param obj 返回数据
	 * @return
	 */
	public static void OUTAPPSUCCESS(Integer code,HttpServletResponse response){
		OutJsonEntity res=new OutJsonEntity();
		res.setCode(code);
		PrintUtil.outprint(GsonUtil.toJson(res, OutJsonEntity.class), response);
	}
	
	/**
	 * 执行失败,返回错误信息
	 * author luojing
	 * @param code 代码
	 * @return
	 */
	public static void OUTAPPERROR(Integer code,String MSG,HttpServletResponse response){
		OutJsonEntity res=new OutJsonEntity();
		res.setCode(code);
		res.setMsg(MSG);
		PrintUtil.outprint(GsonUtil.toJson(res, OutJsonEntity.class), response);
	}
	
	/**
	 * 执行失败,返回错误信息
	 * author luojing
	 * @param code 代码
	 * @return
	 */
	public static void OUTAPPERRORROLLBACK(Integer code,String MSG,HttpServletResponse response){
		OutJsonEntity res=new OutJsonEntity();
		res.setCode(code);
		res.setMsg(MSG);
		PrintUtil.outprint(GsonUtil.toJson(res, OutJsonEntity.class), response);
		//事务回滚
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}
	
	
	/*************************************************************************/
	
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
	  * 将用户信息保存到seesion里
	  * author Feng--java
	  * time 2015-9-9 下午01:25:17
	  * @param request
	  * @param solu
	  
	 public static void SETATTRBUTEUSER(HttpServletRequest request,SolMember solu){
		 HttpSession session=request.getSession(false);
		 session.setAttribute(Constant.ACCESSROUNDNUM, solu);
	 }*/
	 
	 /**
	  * 生成云仓编号
	  * Author:luojing
	  * 2016年6月12日 下午4:45:37
	  */
	 public static String YC_Branch_Number(){
		 String str = "YC-";
		 int i=(int)(Math.random()*99)+10; 
		 return str+i;
	 }
	 
	 /**
	  * 生成库区编号
	  * Author:luojing
	  * 2016年6月12日 下午4:45:37
	  */
	 public static String YC_Zone_Number(){
		 String str = "KQ-";
		 int i=(int)(Math.random()*99)+10; 
		 return str+i;
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
	   * 传人map,返回实体
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	   * @Author:luojing
	   * @2016年8月2日 下午1:52:16
	   */
	  public static Object getObject(Map<String, String[]> map,Object obj){
		  try{
			  if(!map.isEmpty()){
				 for(String str:map.keySet()){
					 String o = null;
					 if(!str.isEmpty()){
						 o = map.get(str)[0];
					 }
					 Field[] fd = obj.getClass().getDeclaredFields();
					 for(Field field:fd){
						 int mod = field.getModifiers();
						 if(Modifier.isAbstract(mod) || Modifier.isFinal(mod)){
							 continue;
						 }
						 if(field.getName().equals(str)){
							 if(!"password".equals(field.getName())){
								//解密
								o = DES.decrypt(o);
				    		 }
							 o = URLDecoder.decode(o,"UTF-8");//编码
							 field.setAccessible(true);
							 if(field.getType().getName().equals("java.lang.Integer")){
								 field.set(obj, Integer.parseInt(o));
							 }
							 if(field.getType().getName().equals("java.lang.Double")){
								 field.set(obj, Double.parseDouble(o));
							 }
							 if(field.getType().getName().equals("java.lang.Float")){
								 field.set(obj, Float.parseFloat(o));
							 }
							 if(field.getType().getName().equals("java.lang.Long")){
								 field.set(obj, Long.parseLong(o));
							 }
							 if(field.getType().getName().equals("java.lang.String")){
								 field.set(obj, o);
							 }
							 if(field.getType().getName().equals("java.math.BigInteger")){
								 field.set(obj, new BigInteger(o));
							 }
						 }
					 }
				 } 
			  }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return obj;
	  }
	  
	  /**
	   * 抛出一个异常
	   * Author:FENG
	   * 2016年6月17日
	   * @param msg
	   */
	  public static void RuntimeExceptionFeng(String msg){
		  throw new RuntimeException(msg);
	  }
	 
	 /**
	  * 获取本机MAC地址
	  * @Author:luojing
	  * @2016年7月14日 上午10:56:53
	  */
	 public static String getLocalMac() throws UnknownHostException, SocketException {
		//获取网卡，获取地址
		InetAddress ia = InetAddress.getLocalHost();
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i<mac.length; i++) {
			if(i!=0) {
				sb.append("-");
			}
			//字节转换为整数
			int temp = mac[i]&0xff;
			String str = Integer.toHexString(temp);
			if(str.length()==1) {
				sb.append("0"+str);
			}else {
				sb.append(str);
			}
		}
		return sb.toString().toUpperCase();
	}
	/**
	 * 生成UUID 
	 * @Author:luojing
	 * @2016年7月26日 上午10:30:40
	 */
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}
	
	/**
	 * 转码
	 * @Author:luojing
	 * @2016年8月3日 上午10:45:03
	 */
	public static String getEncode(String str){
		try {
			str = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 根据登录名读取cook值
	 * @Author:luojing
	 * @2016年8月3日 下午2:21:58
	 */
	public static String getCookie(HttpServletRequest request,String tel){
		String str = "";
		try{
			Cookie[] cook = request.getCookies();
			if(cook!=null){
				for(Cookie c:cook){
					if(c.getName().equals(tel)){
						str = c.getValue();
						c.setMaxAge(0);
						break;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return str;
	}
}
