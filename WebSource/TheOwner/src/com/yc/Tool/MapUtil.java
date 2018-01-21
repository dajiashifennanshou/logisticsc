package com.yc.Tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSONObject;

/**
 * 地图
 * Author:luojing
 * 2016年6月29日 上午9:24:11
 */
public class MapUtil {
	
	private static final String BAIDU_KEY="PsE0wyi3uivu9HdQq22olGuMKLIQ7gsX";
	private static  CloseableHttpClient httpclient=HttpClients.createDefault();
	/**
	 * 获取经纬度
	 * @Author:luojing
	 * @2016年7月28日 上午11:52:13
	 */
	public static Map<String,String> getLngAndLat1(String address){
		BufferedReader in = null;  
        try {  
        	
        	HttpPost httppost = new HttpPost("http://api.map.baidu.com/geocoder/v2/?address="+ address +"&output=json&key="+ BAIDU_KEY);  
        	CloseableHttpResponse response = httpclient.execute(httppost);
        	System.out.println(response.getEntity().toString()+"--");
            //将地址转换成utf-8的16进制  
            /*address = URLEncoder.encode(address, "UTF-8");  
            URL tirc = new URL("http://api.map.baidu.com/geocoder?address="+ address +"&output=json&key="+ BAIDU_KEY);  
            Long l=new Date().getTime();
            in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));  
            System.out.println(new Date().getTime()-l);
            String res;
            StringBuilder sb = new StringBuilder("");  
            while((res = in.readLine())!=null){  
                sb.append(res.trim());  
            }  
            String str = sb.toString();  
            Map<String,String> map = null;  
            if(StrUtil.VObject(str)){  
                int lngStart = str.indexOf("lng\":");  
                int lngEnd = str.indexOf(",\"lat");  
                int latEnd = str.indexOf("},\"precise");  
                if(lngStart > 0 && lngEnd > 0 && latEnd > 0){  
                    String lng = str.substring(lngStart+5, lngEnd);  
                    String lat = str.substring(lngEnd+7, latEnd);  
                    map = new HashMap<String,String>();  
                    map.put("lng", lng);  
                    map.put("lat", lat);  
                    return map;  
                }  
            }  */
           
        }catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
//            try {  
//                in.close();  
//            } catch (IOException e) {  
//                e.printStackTrace();  
//            }  
        }  
        return null;  
	}
	public static Map<String,Double> getLngAndLat(String address){
		Map<String,Double> map=new HashMap<String, Double>();
		 String url = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&ak=PsE0wyi3uivu9HdQq22olGuMKLIQ7gsX";
	        String json = loadJSON(url);
	        JSONObject obj = JSONObject.parseObject(json);
	        if(obj.get("status").toString().equals("0")){
	        	double lng=obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
	        	double lat=obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
	        	map.put("lng", lng);
	        	map.put("lat", lat);
	        	System.out.println("经度："+lng+"---纬度："+lat);
	        }else{
	        	System.out.println("未找到相匹配的经纬度！");
	        }
		return map;
	}
	
	 public static String loadJSON (String url) {
	        StringBuilder json = new StringBuilder();
	        try {
	            URL oracle = new URL(url);
	            URLConnection yc = oracle.openConnection();
	            BufferedReader in = new BufferedReader(new InputStreamReader(
	                                        yc.getInputStream()));
	            String inputLine = null;
	            while ( (inputLine = in.readLine()) != null) {
	                json.append(inputLine);
	            }
	            in.close();
	        } catch (MalformedURLException e) {
	        } catch (IOException e) {
	        }
	        return json.toString();
	    }
	/** 
	 * 计算地球上任意两点(经纬度)距离 
	 * @param long1 
	 *            第一点经度 
	 * @param lat1 
	 *            第一点纬度 
	 * @param long2 
	 *            第二点经度 
	 * @param lat2 
	 *            第二点纬度 
	 * @return 返回距离 单位：米 
	 */  
	public static double Distance(double long1, double lat1, double long2,  
	        double lat2) {  
	    double a, b, R;  
	    R = 6378137; // 地球半径  
	    lat1 = lat1 * Math.PI / 180.0;  
	    lat2 = lat2 * Math.PI / 180.0;  
	    a = lat1 - lat2;  
	    b = (long1 - long2) * Math.PI / 180.0;  
	    double d;  
	    double sa2, sb2;  
	    sa2 = Math.sin(a / 2.0);  
	    sb2 = Math.sin(b / 2.0);  
	    d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));  
	    return d;  
	}  


	public static void main(String[] args) throws IOException {
		MapUtil t = new MapUtil();
		Map<String,Double> map = t.getLngAndLat("四川省成都市天府四街长虹大厦");
		/*map.get("lng");
    	map.get("lat");*/
		//double d = t.Distance(104.072114, 30.604413, map.get("lng"), map.get("lat"));
		System.out.println(map);
    	
	}
}
