package com.brightsoft.controller.payment.yeepay;

import java.io.BufferedInputStream;  
import java.io.BufferedReader;  
import java.io.ByteArrayOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.net.URI;  
import java.net.URL;  
import java.net.URLConnection;  
  











import org.apache.commons.httpclient.HttpClient;  
import org.apache.commons.httpclient.HttpStatus;  
import org.apache.commons.httpclient.methods.PostMethod;  

import com.brightsoft.model.SysUser;
import com.brightsoft.utils.yeepay.JaxbUtil;
import com.brightsoft.utils.yeepay.JaxbUtil.CollectionWrapper;
import com.brightsoft.utils.yeepay.POSBody;
import com.brightsoft.utils.yeepay.POSCODMS;
import com.brightsoft.utils.yeepay.POSHealer;
import com.brightsoft.utils.yeepay.POSRequestHealer;
import com.brightsoft.utils.yeepay.POSResponseHealer;
  
/** 
 * 测试调用一些meeting第三方接口 
 * @author Jack.Song 
 */  
public class TestMeetingInterface {  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
          
        String url = "http://localhost:8080/logisticsc/yeePayPost/getPostOrder.pay";
        POSCODMS poscodms = new POSCODMS();
        POSRequestHealer posHealer = new POSRequestHealer();
        posHealer.setVersion("1");
        posHealer.setServiceCode("2");
        
        POSResponseHealer posResponseHealer = new POSResponseHealer();
        posResponseHealer.setVersion("1");
        //posHealer.setServiceCode("2");
        //poscodms.setResponseHead(posResponseHealer);
        poscodms.setRequestHead(posHealer);
        POSBody posBody = new POSBody();
        //poscodms.setSessionBody(posBody);
        JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class, POSRequestHealer.class);  
        String retXml = requestBinder.toXml(poscodms, "utf-8");  
        System.out.println("xml:"+retXml);  
        
        JaxbUtil resultBinder = new JaxbUtil(POSCODMS.class,CollectionWrapper.class);  
        POSCODMS hotelObj = resultBinder.fromXml(retXml); 
        System.out.println();
   //     TestMeetingInterface tmi = new TestMeetingInterface();  
   //     System.out.println(tmi.post(url,"NewFile.xml"));  
          
        /*//判断当前系统是否支持Java AWT Desktop扩展 
        if(java.awt.Desktop.isDesktopSupported()){ 
            try { 
                URI path = tmi.getClass().getResource("/listSummaryMeeting.xml").toURI(); 
                System.out.println(path); 
                //创建一个URI实例 
//              java.net.URI uri = java.net.URI.create(path);  
                //获取当前系统桌面扩展 
                java.awt.Desktop dp = java.awt.Desktop.getDesktop(); 
                //判断系统桌面是否支持要执行的功能 
                if(dp.isSupported(java.awt.Desktop.Action.BROWSE)){ 
                    //获取系统默认浏览器打开链接 
                    dp.browse(path);     
                } 
            } catch (Exception e) { 
                e.printStackTrace(); 
            }              
        }*/  
    }  
      
      
  
    /**  
     * 发送xml数据请求到server端  
     * @param url xml请求数据地址  
     * @param xmlString 发送的xml数据流  
     * @return null发送失败，否则返回响应内容  
     */    
    public String post(String url,String xmlFileName){    
        //关闭   
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");     
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");     
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "stdout");    
          
        //创建httpclient工具对象   
        HttpClient client = new HttpClient();    
        //创建post请求方法   
        PostMethod myPost = new PostMethod(url);    
        //设置请求超时时间   
        client.setConnectionTimeout(300*1000);  
        String responseString = null;    
        try{    
            //设置请求头部类型   
            myPost.setRequestHeader("Content-Type","text/xml");  
            myPost.setRequestHeader("charset","utf-8");  
              
            //设置请求体，即xml文本内容，注：这里写了两种方式，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式   
//          myPost.setRequestBody(xmlString);   
              
            InputStream body=this.getClass().getResourceAsStream("/"+xmlFileName);  
            myPost.setRequestBody(body);  
//            myPost.setRequestEntity(new StringRequestEntity(xmlString,"text/xml","utf-8"));     
            int statusCode = client.executeMethod(myPost);    
            if(statusCode == HttpStatus.SC_OK){    
                BufferedInputStream bis = new BufferedInputStream(myPost.getResponseBodyAsStream());    
                byte[] bytes = new byte[1024];    
                ByteArrayOutputStream bos = new ByteArrayOutputStream();    
                int count = 0;    
                while((count = bis.read(bytes))!= -1){    
                    bos.write(bytes, 0, count);    
                }    
                byte[] strByte = bos.toByteArray();    
                responseString = new String(strByte,0,strByte.length,"utf-8");    
                bos.close();    
                bis.close();    
            }    
        }catch (Exception e) {    
            e.printStackTrace();    
        }    
        myPost.releaseConnection();    
        return responseString;    
    }    
      
    /** 
     * 用传统的URI类进行请求 
     * @param urlStr 
     */  
    public void testPost(String urlStr) {  
        try {  
            URL url = new URL(urlStr);  
            URLConnection con = url.openConnection();  
            con.setDoOutput(true);  
            con.setRequestProperty("Pragma:", "no-cache");  
            con.setRequestProperty("Cache-Control", "no-cache");  
            con.setRequestProperty("Content-Type", "text/xml");  
  
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());      
            String xmlInfo = getXmlInfo();  
            System.out.println("urlStr=" + urlStr);  
//            System.out.println("xmlInfo=" + xmlInfo);   
            out.write(new String(xmlInfo.getBytes("UTF-8")));  
            out.flush();  
            out.close();  
            BufferedReader br = new BufferedReader(new InputStreamReader(con  
                    .getInputStream()));  
            String line = "";  
            for (line = br.readLine(); line != null; line = br.readLine()) {  
                System.out.println(line);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    private String getXmlInfo() {  
        StringBuilder sb = new StringBuilder();  
        sb.append("<?xml version='1.0' encoding='UTF-8'?>");  
        sb.append("<Message>");  
        sb.append(" <header>");  
        sb.append("     <action>readMeetingStatus</action>");  
        sb.append("     <service>meeting</service>");  
        sb.append("     <type>xml</type>");  
        sb.append("     <userName>admin</userName>");  
        sb.append("     <password>admin</password>");  
        sb.append("     <siteName>box</siteName>");  
        sb.append(" </header>");  
        sb.append(" <body>");  
        sb.append("     <confKey>43283344</confKey>");  
        sb.append(" </body>");  
        sb.append("</Message>");  
          
        return sb.toString();  
    }  
}  
